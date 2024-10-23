package DAOImp;

import beans.Instructor;
import DAO.InstructorDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class InstructorDAOImp implements InstructorDAO {

    @Override
    public void saveInstructor(Instructor instructor) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(instructor);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateInstructor(Instructor instructor) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(instructor);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Instructor getInstructorById(int instructorId) {
        Instructor instructor = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            instructor = session.get(Instructor.class, instructorId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instructor;
    }

    @Override
    public void deleteInstructor(int instructorId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, instructorId);
            if (instructor != null) {
                session.delete(instructor);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Instructor> getAllInstructors() {
        List<Instructor> instructors = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            instructors = session.createQuery("from Instructor", Instructor.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instructors != null ? instructors : List.of();
    }

    @Override
    public List<Instructor> getInstructorsByCourseId(int courseId) {
        // Implement as needed based on your database schema
        return null;
    }
}

