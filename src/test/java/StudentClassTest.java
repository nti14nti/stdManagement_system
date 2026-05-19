package org.example;

import org.example.StudentClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentClassTest {

    StudentClass system;

    @BeforeEach
    void setup() {
        system = new StudentClass();
    }

    // ---------------- Test Add Student ----------------
    @Test
    void testAddStudent() {

        system.addStudent("John Smith", 1001);

        assertEquals(1, system.studentCount);
    }

    // ---------------- Test Calculate Average ----------------
    @Test
    void testCalculateAverage() {

        system.addStudent("John", 1);

        system.studentGrades[0][0] = 80;
        system.studentGrades[0][1] = 90;
        system.gradeCounts[0] = 2;

        double avg = system.calculateAverage(0);

        assertEquals(85.0, avg, 0.001);
    }

    // ---------------- Test Empty Average ----------------
    @Test
    void testCalculateAverageEmpty() {

        system.addStudent("John", 1);

        double avg = system.calculateAverage(0);

        assertEquals(0.0, avg);
    }

    // ---------------- Test Multiple Students ----------------
    @Test
    void testMultipleStudents() {

        system.addStudent("John Smith", 1001);
        system.addStudent("Jane Doe", 1002);

        assertEquals(2, system.studentCount);

        assertEquals("Jane Doe", system.studentNames[1]);
    }

    // ---------------- Test Grade Storage ----------------
    @Test
    void testGradeStorage() {

        system.addStudent("John", 1);

        system.studentGrades[0][0] = 70;
        system.studentGrades[0][1] = 80;
        system.gradeCounts[0] = 2;

        assertEquals(2, system.gradeCounts[0]);
        assertEquals(70, system.studentGrades[0][0]);
    }

    // ---------------- Test Class Statistics (Smoke Test) ----------------
    @Test
    void testClassStatisticsRuns() {

        system.addStudent("John", 1);
        system.studentGrades[0][0] = 90;
        system.gradeCounts[0] = 1;

        system.displayClassStatistics(); // just ensures no crash
        assertTrue(true);
    }
}