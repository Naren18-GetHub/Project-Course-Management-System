package DAO;

import beans.Student;
import java.util.List;

public interface StudentDAO {

    // Save a new student
    void saveStudent(Student student);

    // Update an existing student
    void updateStudent(Student student);

    // Retrieve a student by ID
    Student getStudentById(int studentId);

    // Delete a student by ID
    void deleteStudent(int studentId);

    // Retrieve all students
    List<Student> getAllStudents();

    // Retrieve students by course
    List<Student> getStudentsByCourseId(int courseId);
}
