package DAO;

import beans.Assignment;
import java.util.List;

public interface AssignmentDAO {

    // Save a new assignment
    void saveAssignment(Assignment assignment);

    // Update an existing assignment
    void updateAssignment(Assignment assignment);

    // Retrieve an assignment by ID
    Assignment getAssignmentById(int assignmentId);

    // Delete an assignment by ID
    void deleteAssignment(int assignmentId);

    // Retrieve all assignments
    List<Assignment> getAllAssignments();

    // Retrieve assignments by course
    List<Assignment> getAssignmentsByCourseId(int courseId);
}
