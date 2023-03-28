package repository;

import org.hibernate.Session;
import entity.Student;
import org.hibernate.SessionFactory;

import java.util.List;

public class DataAccessStudent implements DataAccessObject {
    private SessionFactory factory;

    public DataAccessStudent(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void addStudent(Student student) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
    }

    @Override
    public Student getStudent(Integer id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Student student = session.get(Student.class, id);
        session.getTransaction().commit();
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        Session session = factory.getCurrentSession();
        List<Student> students;
        session.beginTransaction();
        students = session.createQuery("from entity.Student").getResultList();
        session.getTransaction().commit();
        return students;
    }


    @Override
    public boolean remove(Integer id) {
        boolean key = false;
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Student student = session.get(Student.class, id);
        if (student != null) {
            session.remove(student);
            key = true;
        }
        session.getTransaction().commit();
        return key;
    }

    @Override
    public void change(Integer id, Student student) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Student oldStudent = session.get(Student.class, id);
        oldStudent.setName(student.getName());
        oldStudent.setEmail(student.getEmail());
        session.getTransaction().commit();
    }


}
