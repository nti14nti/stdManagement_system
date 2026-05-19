package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        StudentClass system = new StudentClass();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n=====================================");
            System.out.println(" STUDENT GRADE MANAGEMENT SYSTEM");
            System.out.println("=====================================");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grades");
            System.out.println("3. Display All Students");
            System.out.println("4. Class Statistics");
            System.out.println("5. Search Student");
            System.out.println("6. Exit");
            System.out.println("=====================================");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    scanner.nextLine(); // consume newline

                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();

                    

                    System.out.print("Enter student ID: ");
                    int id = scanner.nextInt();

                    system.addStudent(name, id);
                    break;

                case 2:
                    System.out.print("Enter student index: ");
                    int index = scanner.nextInt();

                    system.addGrade(index);
                    break;

                case 3:
                    system.displayAllStudents();
                    break;

                case 4:
                    system.displayClassStatistics();
                    break;

                case 5:
                    system.searchStudent();
                    break;

                case 6:
                    System.out.println("Exiting system... Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}