package com.studentmgt;

import com.studentmgt.dao.CourseDao;
import com.studentmgt.dao.EnrollmentDao;
import com.studentmgt.dao.StudentDao;
import com.studentmgt.model.Course;
import com.studentmgt.model.Enrollment;
import com.studentmgt.model.Student;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final StudentDao studentDao = new StudentDao();
    private static final CourseDao courseDao = new CourseDao();
    private static final EnrollmentDao enrollmentDao = new EnrollmentDao();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View Student");
            System.out.println("3. View All Students");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Add Course");
            System.out.println("7. View All Courses");
            System.out.println("8. Enroll Student in Course");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudent();
                    break;
                case 3:
                    viewAllStudents();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    addCourse();
                    break;
                case 7:
                    viewAllCourses();
                    break;
                case 8:
                    enrollStudentInCourse();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student email: ");
        String email = scanner.nextLine();
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        studentDao.saveStudent(student);
        System.out.println("Student added successfully!");
    }

    private static void viewStudent() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        Student student = studentDao.getStudent(id);
        if (student != null) {
            System.out.println("Student ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Email: " + student.getEmail());
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void viewAllStudents() {
        List<Student> students = studentDao.getAllStudents();
        for (Student student : students) {
            System.out.println("ID: " + student.getId() + ", Name: " + student.getName() + ", Email: " + student.getEmail());
        }
    }

    private static void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Student student = studentDao.getStudent(id);
        if (student != null) {
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new email: ");
            String email = scanner.nextLine();
            student.setName(name);
            student.setEmail(email);
            studentDao.updateStudent(student);
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();
        studentDao.deleteStudent(id);
        System.out.println("Student deleted successfully!");
    }

    private static void addCourse() {
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        Course course = new Course();
        course.setCourseName(courseName);
        courseDao.saveCourse(course);
        System.out.println("Course added successfully!");
    }

    private static void viewAllCourses() {
        List<Course> courses = courseDao.getAllCourses();
        for (Course course : courses) {
            System.out.println("ID: " + course.getId() + ", Name: " + course.getCourseName());
        }
    }

    private static void enrollStudentInCourse() {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        System.out.print("Enter course ID: ");
        int courseId = scanner.nextInt();

        Student student = studentDao.getStudent(studentId);
        Course course = new Course(); // In a real app, you'd fetch the course
        course.setId(courseId);

        if (student != null) {
            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(student);
            enrollment.setCourse(course);
            enrollmentDao.saveEnrollment(enrollment);
            System.out.println("Student enrolled successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }
}
