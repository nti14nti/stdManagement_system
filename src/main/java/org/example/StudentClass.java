package org.example;

import java.util.Scanner;

public class StudentClass {
    // Maximum limits
    private static final int MAX_STUDENTS = 100;
    private static final int MAX_GRADES = 5;

    // Arrays
    public String[] studentNames;
    public int[] studentIDs;
    public double[][] studentGrades;
    public int[] gradeCounts;

    // Current number of students
    int studentCount = 0;

    Scanner scanner = new Scanner(System.in);

    // Constructor
    public StudentClass() {

        studentNames = new String[MAX_STUDENTS];
        studentIDs = new int[MAX_STUDENTS];
        studentGrades = new double[MAX_STUDENTS][MAX_GRADES];
        gradeCounts = new int[MAX_STUDENTS];

        studentCount = 0;
    }

    // Add a student
    public void addStudent(String name, int id) {
        if (studentCount >= MAX_STUDENTS) {
            System.out.println("Student limit reached.");
            return;
        }
        studentNames[studentCount] = name;
        studentIDs[studentCount] = id;
        gradeCounts[studentCount] = 0;
        studentCount++;
        System.out.println("Student added successfully! " + name + " has been assigned ID: " + id);
    }

    // Add grade to a student
    public void addGrade(int studentIndex) {

        // 1. Check empty system
        if (studentCount == 0) {
            System.out.println("No students in the system yet. Please add a student first.");
            return;
        }

        // 2. Show students
        System.out.println("Select a student by index:");
        displayAllStudents();

        // 3. Validate index
        if (studentIndex < 0 || studentIndex >= studentCount) {
            System.out.println("Invalid student index.");
            return;
        }

        int remaining = MAX_GRADES - gradeCounts[studentIndex];

        // 4. Check max grades
        if (remaining == 0) {
            System.out.println("Student already has maximum grades (5). Cannot add more.");
            return;
        }

        // 5. Ask number of grades
        System.out.print("How many grades would you like to add? (Max: " + remaining + "): ");
        int count = scanner.nextInt();

        if (count < 1 || count > remaining) {
            System.out.println("Invalid number of grades.");
            return;
        }

        double sum = 0;

        // 6. Input grades
        for (int i = 0; i < count; i++) {

            System.out.print("Enter grade " + (i + 1) + ": ");
            double grade = scanner.nextDouble();

            if (grade < 0 || grade > 100) {
                System.out.println("Invalid grade (0-100). Try again.");
                i--;
                continue;
            }

            studentGrades[studentIndex][gradeCounts[studentIndex]] = grade;
            gradeCounts[studentIndex]++;

            sum += grade;
        }

        // 7. Final output
        System.out.print("\nGrades added successfully!\n");

        System.out.print(studentNames[studentIndex] + "'s grades: ");

        double total = 0;

        for (int i = 0; i < gradeCounts[studentIndex]; i++) {

            System.out.print(studentGrades[studentIndex][i]);

            if (i < gradeCounts[studentIndex] - 1) {
                System.out.print(", ");
            }

            total += studentGrades[studentIndex][i];
        }

        double avg = total / gradeCounts[studentIndex];

        System.out.printf("\nCurrent average: %.2f\n", avg);
    }

    // Display student information
    public void displayAllStudents() {

        if (studentCount == 0) {
            System.out.println("No students in the system.");
            return;
        }

        System.out.println("=====================================");
        System.out.println(" STUDENT LIST");
        System.out.println("=====================================");

        for (int i = 0; i < studentCount; i++) {

            System.out.println("ID: " + studentIDs[i] + " | Name: " + studentNames[i]);

            // Grades
            if (gradeCounts[i] == 0) {
                System.out.println("Grades: No grades entered yet");
                System.out.println("Average: 0.00");
            } else {

                System.out.print("Grades: ");

                for (int j = 0; j < gradeCounts[i]; j++) {

                    System.out.print(studentGrades[i][j]);

                    if (j < gradeCounts[i] - 1) {
                        System.out.print(", ");
                    }
                }

                System.out.println();

                double avg = calculateAverage(i);

                System.out.printf("Average: %.2f\n", avg);
            }

            System.out.println("-------------------------------------");
        }

        System.out.println("=====================================");
    }

    // Calculate average grade
    public double calculateAverage(int studentIndex) {

        // If student has no grades
        if (gradeCounts[studentIndex] == 0) {
            return 0.0;
        }

        double sum = 0.0;

        // Only loop through actual grades
        for (int i = 0; i < gradeCounts[studentIndex]; i++) {
            sum += studentGrades[studentIndex][i];
        }

        // Return average
        return sum / gradeCounts[studentIndex];
    }

    public void displayClassStatistics() {

        // 1. Check empty system
        if (studentCount == 0) {
            System.out.println("No students in the system.");
            return;
        }

        System.out.println("=====================================");
        System.out.println(" CLASS STATISTICS");
        System.out.println("=====================================");

        int studentsWithGrades = 0;

        double classSum = 0.0;

        double highestAvg = Double.MIN_VALUE;
        double lowestAvg = Double.MAX_VALUE;

        String highestStudent = "";
        String lowestStudent = "";

        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;

        // 2. Loop through all students
        for (int i = 0; i < studentCount; i++) {

            double avg = calculateAverage(i);

            // Only count students who have grades
            if (gradeCounts[i] > 0) {

                studentsWithGrades++;

                classSum += avg;

                // Highest
                if (avg > highestAvg) {
                    highestAvg = avg;
                    highestStudent = studentNames[i];
                }

                // Lowest
                if (avg < lowestAvg) {
                    lowestAvg = avg;
                    lowestStudent = studentNames[i];
                }

                // Grade distribution based on average
                if (avg >= 90) {
                    countA++;
                } else if (avg >= 80) {
                    countB++;
                } else if (avg >= 70) {
                    countC++;
                } else if (avg >= 60) {
                    countD++;
                } else {
                    countF++;
                }
            }
        }

        // 3. Class average
        double classAverage = (studentsWithGrades == 0)
                ? 0.0
                : classSum / studentsWithGrades;

        // 4. Output
        System.out.println("Total Students: " + studentCount);
        System.out.println("Students with Grades: " + studentsWithGrades);
        System.out.printf("Class Average: %.2f\n", classAverage);

        System.out.println();

        if (studentsWithGrades > 0) {
            System.out.printf("Highest Average: %.2f (%s)\n", highestAvg, highestStudent);
            System.out.printf("Lowest Average: %.2f (%s)\n", lowestAvg, lowestStudent);
        } else {
            System.out.println("Highest Average: N/A");
            System.out.println("Lowest Average: N/A");
        }

        System.out.println();
        System.out.println("Grade Distribution:");
        System.out.println("A (90-100): " + countA + " student");
        System.out.println("B (80-89): " + countB + " student");
        System.out.println("C (70-79): " + countC + " student");
        System.out.println("D (60-69): " + countD + " student");
        System.out.println("F (below 60): " + countF + " student");

        System.out.println("=====================================");
    }

    public void searchStudent() {

    if (studentCount == 0) {
        System.out.println("No students in the system.");
        return;
    }

    Scanner scanner = new Scanner(System.in);

    System.out.print("Search by (1) Name or (2) ID: ");
    int choice = scanner.nextInt();
    scanner.nextLine(); // consume newline

    boolean found = false;

    System.out.println();

    if (choice == 1) {

        System.out.print("Enter name to search: ");
        String searchName = scanner.nextLine().toLowerCase();

        System.out.println("\nFound matches:");

        for (int i = 0; i < studentCount; i++) {

            if (studentNames[i].toLowerCase().contains(searchName)) {

                found = true;
                printStudentDetails(i);
            }
        }

    } else if (choice == 2) {

        System.out.print("Enter ID to search: ");
        int searchId = scanner.nextInt();

        for (int i = 0; i < studentCount; i++) {

            if (studentIDs[i] == searchId) {

                found = true;
                System.out.println("\nFound match:");
                printStudentDetails(i);
                break;
            }
        }

    } else {
        System.out.println("Invalid choice.");
        return;
    }

    if (!found) {
        System.out.println("No students found matching your search.");
    }
}

    private void printStudentDetails(int i) {

        System.out.println("-------------------------------------");
        System.out.println("ID: " + studentIDs[i] + " | Name: " + studentNames[i]);

        if (gradeCounts[i] == 0) {
            System.out.println("Grades: No grades entered yet");
            System.out.println("Average: 0.00");
            return;
        }

        System.out.print("Grades: ");

        for (int j = 0; j < gradeCounts[i]; j++) {
            System.out.print(studentGrades[i][j]);

            if (j < gradeCounts[i] - 1) {
                System.out.print(", ");
            }
        }

        System.out.println();

        double avg = calculateAverage(i);
        System.out.printf("Average: %.2f\n", avg);
    }
}
