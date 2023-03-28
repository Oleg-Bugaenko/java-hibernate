package repository;

import entity.Student;

import java.util.List;

public interface DataAccessObject {

    void addStudent(Student student);

    Student getStudent(Integer id);

    List<Student> getAllStudents();

    boolean remove(Integer id);

    void change(Integer id, Student student);

}
