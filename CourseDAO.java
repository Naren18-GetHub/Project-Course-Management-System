package DAO;

import beans.Course;
import java.util.List;

public interface CourseDAO {

    // Save a new course
    void saveCourse(Course course);

    // Update an existing course
    void updateCourse(Course course);

    // Retrieve a course by ID
    Course getCourseById(int courseId);

    // Delete a course by ID
    void deleteCourse(int courseId);

    // Retrieve all courses
    List<Course> getAllCourses();

    // Retrieve courses by instructor
    List<Course> getCoursesByInstructorId(int instructorId);
}
