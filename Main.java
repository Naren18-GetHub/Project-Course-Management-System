package main;

import DAO.AssignmentDAO;
import DAO.CourseDAO;
import DAO.InstructorDAO;
import DAO.StudentDAO;
import DAOImp.AssignmentDAOImp;
import DAOImp.CourseDAOImp;
import DAOImp.InstructorDAOImp;
import DAOImp.StudentDAOImp;
import beans.Course;
import beans.Instructor;
import beans.Student;
import beans.Assignment;
import util.HibernateUtil;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InstructorDAO instructorDAO = new InstructorDAOImp();
        CourseDAO courseDAO = new CourseDAOImp();
        StudentDAO studentDAO = new StudentDAOImp();
        AssignmentDAO assignmentDAO = new AssignmentDAOImp();

        while (true) {
            System.out.println("\n---- Course Management System ----");
            System.out.println("1. Add Instructor");
            System.out.println("2. Add Course");
            System.out.println("3. Add Student to Course");
            System.out.println("4. Add Assignment to Course");
            System.out.println("5. View All Courses");
            System.out.println("6. View Course Instructor");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    // Add Instructor
                    System.out.println("Enter Instructor Name:");
                    String instructorName = scanner.nextLine();
                    System.out.println("Enter Instructor Email:");
                    String instructorEmail = scanner.nextLine();

                    Instructor instructor = new Instructor();
                    instructor.setName(instructorName);
                    instructor.setEmail(instructorEmail);
                    instructorDAO.saveInstructor(instructor);

                    System.out.println("Instructor saved successfully!");
                    break;

                case 2:
                    // Add Course
                    System.out.println("Enter Course Name:");
                    String courseName = scanner.nextLine();
                    System.out.println("Enter Course Credits:");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Please enter a valid number for Course Credits:");
                        scanner.next(); // discard invalid input
                    }
                    int credits = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    // Fetch available instructors
                    List<Instructor> instructors = instructorDAO.getAllInstructors();
                    if (instructors.isEmpty()) {
                        System.out.println("No instructors available. Please add an instructor first.");
                        break;
                    }

                    System.out.println("Select Instructor by ID:");
                    for (Instructor inst : instructors) {
                        System.out.println(inst.getInstructorId() + ": " + inst.getName());
                    }
                    int instructorId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Instructor courseInstructor = instructorDAO.getInstructorById(instructorId);
                    if (courseInstructor != null) {
                        Course course = new Course();
                        course.setName(courseName);
                        course.setCredits(credits);
                        course.setInstructor(courseInstructor);
                        courseDAO.saveCourse(course);
                        System.out.println("Course saved successfully!");
                    } else {
                        System.out.println("Invalid Instructor ID.");
                    }
                    break;

                case 3:
                    // Add Student to Course
                    System.out.println("Enter Student Name:");
                    String studentName = scanner.nextLine();
                    System.out.println("Enter Student Email:");
                    String studentEmail = scanner.nextLine();

                    System.out.println("Select Course by ID to enroll student:");
                    List<Course> courses = courseDAO.getAllCourses();
                    if (courses == null || courses.isEmpty()) {
                        System.out.println("No courses available.");
                        break;
                    }
                    for (Course c : courses) {
                        System.out.println(c.getCourseId() + ": " + c.getName());
                    }
                    int courseId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Course selectedCourse = courseDAO.getCourseById(courseId);
                    if (selectedCourse != null) {
                        Student student = new Student();
                        student.setName(studentName);
                        student.setEmail(studentEmail);
                        studentDAO.saveStudent(student);

                        // Enroll the student in the course
                        selectedCourse.getStudents().add(student);
                        courseDAO.updateCourse(selectedCourse);
                        System.out.println("Student enrolled successfully!");
                    } else {
                        System.out.println("Invalid Course ID.");
                    }
                    break;

                case 4:
                    // Add Assignment to Course
                    System.out.println("Enter Assignment Description:");
                    String description = scanner.nextLine();
                    System.out.println("Enter Course ID for the Assignment:");
                    int assignCourseId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Course assignmentCourse = courseDAO.getCourseById(assignCourseId);
                    if (assignmentCourse != null) {
                        Assignment assignment = new Assignment();
                        assignment.setDescription(description);
                        assignment.setDueDate(new Date());
                        assignment.setCourse(assignmentCourse);

                        assignmentDAO.saveAssignment(assignment);
                        System.out.println("Assignment saved successfully!");
                    } else {
                        System.out.println("Course not found!");
                    }
                    break;

                case 5:
                    // View All Courses
                    courses = courseDAO.getAllCourses();
                    if (courses == null || courses.isEmpty()) {
                        System.out.println("No courses available.");
                    } else {
                        for (Course c : courses) {
                            System.out.println("Course: " + c.getName() + ", Instructor: " + c.getInstructor().getName());
                            for (Student s : c.getStudents()) {
                                System.out.println("  Student: " + s.getName());
                            }
                        }
                    }
                    break;

                case 6:
                    // View Course Instructor
                    System.out.println("Enter Course ID to view the Instructor:");
                    int viewCourseId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Course course = courseDAO.getCourseById(viewCourseId);
                    if (course != null) {
                        System.out.println("Course: " + course.getName() + ", Instructor: " + course.getInstructor().getName());
                    } else {
                        System.out.println("Invalid Course ID.");
                    }
                    break;

                case 7:
                    // Exit the program
                    HibernateUtil.shutdown();
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option! Please choose again.");
                    break;
            }
        }
    }
}