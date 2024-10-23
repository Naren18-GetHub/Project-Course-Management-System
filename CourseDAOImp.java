package DAOImp;

import beans.Course;
import DAO.CourseDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class CourseDAOImp implements CourseDAO {

    @Override
    public void saveCourse(Course course) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(course);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCourse(Course course) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(course);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Course getCourseById(int courseId) {
        Course course = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            course = session.get(Course.class, courseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public void deleteCourse(int courseId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Course course = session.get(Course.class, courseId);
            if (course != null) {
                session.delete(course);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> courses = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            courses = session.createQuery("from Course", Course.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public List<Course> getCoursesByInstructorId(int instructorId) {
        // Implement as needed based on your database schema
        return null;
    }
}
