import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Student[] students = new Student[5];
        for (int i = 0; i < students.length; i++) {
            String name;
            do {
                System.out.print("Enter the name for student " + (i+1) + ": ");
                name = scanner.nextLine();
            } while (!isValidName(name));
            int grade;
            do {
                System.out.print("Enter the grade for student " + (i+1) + ": ");
                grade = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            } while (grade < 1);
            students[i] = new Student(name, grade);
        }

        Student bestStudent = findBestStudent(students);
        writeToFile("best_student.txt", bestStudent.getName() + " is the best-performing student with a grade of " + bestStudent.getGrade() + "%");

        Random random = new Random();
        Student randomStudent = students[random.nextInt(students.length)];
        writeToFile("coding_challenge_winner.txt", randomStudent.getName() + " won the coding challenge for this year");

        scanner.close();
    }

    public static boolean isValidName(String name) {
        if (name.length() < 10) {
            System.out.println("Name must be at least 10 characters long");
            return false;
        }
        if (name.contains(" ")) {
            System.out.println("Name must not contain any spaces");
            return false;
        }
        if (!Character.isUpperCase(name.charAt(0))) {
            System.out.println("Name must start with a capital letter");
            return false;
        }
        return true;
    }

    public static Student findBestStudent(Student[] students) {
        Student bestStudent = students[0];
        for (int i = 1; i < students.length; i++) {
            if (students[i].getGrade() > bestStudent.getGrade()) {
                bestStudent = students[i];
            }
        }
        return bestStudent;
    }

    public static void writeToFile(String fileName, String content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

}

class Student {
    private String name;
    private int grade;

    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }
}

