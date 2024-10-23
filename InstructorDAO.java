package DAO;

import beans.Instructor;
import java.util.List;

public interface InstructorDAO {

    // Save a new instructor
    void saveInstructor(Instructor instructor);

    // Update an existing instructor
    void updateInstructor(Instructor instructor);

    // Retrieve an instructor by ID
    Instructor getInstructorById(int instructorId);

    // Delete an instructor by ID
    void deleteInstructor(int instructorId);

    // Retrieve all instructors
    List<Instructor> getAllInstructors();

    // Retrieve instructors by course they teach
    List<Instructor> getInstructorsByCourseId(int courseId);
}
