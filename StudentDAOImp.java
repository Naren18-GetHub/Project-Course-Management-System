package DAOImp;

import beans.Student;
import DAO.StudentDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class StudentDAOImp implements StudentDAO {

    @Override
    public void saveStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudentById(int studentId) {
        Student student = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            student = session.get(Student.class, studentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public void deleteStudent(int studentId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, studentId);
            if (student != null) {
                session.delete(student);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            students = session.createQuery("from Student", Student.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public List<Student> getStudentsByCourseId(int courseId) {
        List<Student> students = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            students = session.createQuery("select s from Student s join s.courses c where c.courseId = :courseId", Student.class)
                    .setParameter("courseId", courseId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }
}
