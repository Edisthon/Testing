package com.studentmgt;

import com.studentmgt.dao.BookDao;
import com.studentmgt.dao.UserDao;
import com.studentmgt.model.Book;
import com.studentmgt.model.BookStatus;
import com.studentmgt.model.Gender;
import com.studentmgt.model.Role;
import com.studentmgt.model.User;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final UserDao userDao = new UserDao();
    private static final BookDao bookDao = new BookDao();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add User");
            System.out.println("2. Add Book");
            System.out.println("3. View All Users");
            System.out.println("4. View All Books");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    viewAllUsers();
                    break;
                case 4:
                    viewAllBooks();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addUser() {
        System.out.print("Enter person ID: ");
        String personId = scanner.nextLine();
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter gender (MALE/FEMALE): ");
        Gender gender = Gender.valueOf(scanner.nextLine().trim().toUpperCase());
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter username: ");
        String userName = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (STUDENT, MANAGER, TEACHER, DEAN, HOD, LIBRARIAN): ");
        Role role = Role.valueOf(scanner.nextLine().trim().toUpperCase());

        User user = new User();
        user.setPersonId(personId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setGender(gender);
        user.setPhoneNumber(phoneNumber);
        user.setUserName(userName);
        user.setPassword(password);
        user.setRole(role);

        userDao.saveUser(user);
        System.out.println("User added successfully!");
    }

    private static void addBook() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("Enter edition: ");
        int edition = scanner.nextInt();
        scanner.nextLine();

        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPublisher(publisher);
        book.setEdition(edition);
        book.setBookStatus(BookStatus.AVAILABLE);

        bookDao.saveBook(book);
        System.out.println("Book added successfully!");
    }

    private static void viewAllUsers() {
        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            System.out.println("ID: " + user.getPersonId() + ", Name: " + user.getFirstName() + " " + user.getLastName() + ", Username: " + user.getUserName());
        }
    }

    private static void viewAllBooks() {
        List<Book> books = bookDao.getAllBooks();
        for (Book book : books) {
            System.out.println("ID: " + book.getBookId() + ", Title: " + book.getTitle() + ", ISBN: " + book.getIsbn() + ", Status: " + book.getBookStatus());
        }
    }
}
