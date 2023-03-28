import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repository.DataAccessObject;
import repository.DataAccessStudent;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class MyApp {
    private static SessionFactory factory;
    private static DataAccessObject dataAccess;


    public static void main(String[] args) {
        factory = createSessionFactory();
        createTableEntities();
        dataAccess = new DataAccessStudent(factory);

        try (Reader reader = new InputStreamReader(System.in);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            startMassage();
            String command;
            while (true) {
                command = bufferedReader.readLine().trim();
                if (command.equalsIgnoreCase("-exit")) {
                    break;
                } else {
                    requestProcessing(command, bufferedReader);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (factory != null) factory.close();
        }
    }


    private static void requestProcessing(String command, BufferedReader bufferedReader) throws IOException {
        String name, email;
        switch (command) {
            case "-f":
                startMassage();
            case "-add":
                System.out.print("Enter name: ");
                name = bufferedReader.readLine().trim();
                System.out.print("Enter email: ");
                email = bufferedReader.readLine().trim();
                dataAccess.addStudent(new Student(name, email));
                System.out.println("The record is created");
                System.out.println("----------------------");
                break;

            case "-get-all":
                List<Student> students = dataAccess.getAllStudents();
                if (students != null) {
                    System.out.println("All students:");
                    students.forEach(System.out::println);
                } else System.out.println("There are no records");
                System.out.println("----------------------");
                break;

            case "-get":
                try {
                    System.out.print("Enter id: ");
                    Integer index = Integer.valueOf(bufferedReader.readLine().trim());
                    if (index != null) {
                        Student student = dataAccess.getStudent(index);
                        System.out.println(student);
                    } else System.err.println("Must be entered id");

                } catch (NumberFormatException e) {
                    System.err.println("An integer value must be entered!");
                }
                System.out.println("----------------------");
                break;

            case "-remove":
                try {
                    System.out.print("Enter id: ");
                    Integer index = Integer.valueOf(bufferedReader.readLine().trim());
                    if (index != null) {
                        if (dataAccess.remove(index)) {
                            System.out.printf("Record with %s deleted successfully\n", index);
                        } else System.err.println("Error deleting record");
                    } else System.err.println("Must be entered id");
                } catch (NumberFormatException e) {
                    System.err.println("An integer value must be entered!");
                }
                System.out.println("----------------------");
                break;

            case "-change":
                try {
                    System.out.print("Enter id: ");
                    Integer index = Integer.valueOf(bufferedReader.readLine().trim());
                    System.out.print("Enter name: ");
                    name = bufferedReader.readLine().trim();
                    System.out.print("Enter email: ");
                    email = bufferedReader.readLine().trim();
                    if (index != null) {
                        dataAccess.change(index, new Student(name, email));
                    } else System.err.println("Must be entered id");
                } catch (NumberFormatException e) {
                    System.err.println("An integer value must be entered!");
                }
                System.out.println("----------------------");
                break;
            default:
                System.err.println("You entered the wrong command, enter -f for help");
        }
    }


    //Create SessionFactory
    private static SessionFactory createSessionFactory() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        return factory;
    }

    //Start massage
    private static void startMassage() {
        System.out.println("You are connected to the database of students' e-mail addresses");
        System.out.println(" use the following commands to work: ");
        System.out.println("----------------------------------------------------------------");
        System.out.println(" -add       --> add a student and their email");
        System.out.println(" -get-all   --> get data of all students");
        System.out.println(" -get       --> obtaining student data by id");
        System.out.println(" -remove    --> deleting student data by id");
        System.out.println(" -change    --> changing student data");
        System.out.println(" -exit      --> exit the program");
        System.out.println("-----------------------------------------------------------------");
    }

    private static void createTableEntities() {
        String sqlCommand = null;
        Session session;
        try {
            sqlCommand = Files.lines(Paths.get("create_records.sql")).collect(Collectors.joining());
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sqlCommand).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
