package DAOImp;

import beans.Assignment;
import DAO.AssignmentDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class AssignmentDAOImp implements AssignmentDAO {

    @Override
    public void saveAssignment(Assignment assignment) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(assignment);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAssignment(Assignment assignment) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(assignment);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Assignment getAssignmentById(int assignmentId) {
        Assignment assignment = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            assignment = session.get(Assignment.class, assignmentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return assignment;
    }

    @Override
    public void deleteAssignment(int assignmentId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Assignment assignment = session.get(Assignment.class, assignmentId);
            if (assignment != null) {
                session.delete(assignment);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Assignment> getAllAssignments() {
        List<Assignment> assignments = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            assignments = session.createQuery("from Assignment", Assignment.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return assignments;
    }

    @Override
    public List<Assignment> getAssignmentsByCourseId(int courseId) {
        // Implement as needed based on your database schema
        return null;
    }
}

