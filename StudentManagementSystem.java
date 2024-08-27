import java.io.*;
import java.util.*;

public class StudentManagementSystem {
    private static final int UOW_STUDENTS = 100;
    private static final String FILENAME = "data.csv";
    private List<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }


    public List<Student> getStudents() { // Task 03
            return students;
        }

    /**
     * prints number of available seats from this
     */
    public void checkAvailableSeats() {
        System.out.println("Available seats: " + (UOW_STUDENTS - students.size()));
    }

    /**
     * registers a student if the ID is valid and there are available seats.
     * @param id   The student's ID.
     * @param name The student's name.
     */
    public void registerNewStudent(String id, String name) {
        if (!Student.isValidStudentId(id)) {
            System.out.println("Invalid Student ID. Registration failed.");
            return;
        }
        if (students.size() >= UOW_STUDENTS) {
            System.out.println("No available seats.");
            return;
        }
        students.add(new Student(id, name));
        System.out.println("Student registered successfully.");
    }

    /**
     * adds module marks for a student.
     *
     * @param id     The student's ID.
     * @param marks1 Marks for Module 1.
     * @param marks2 Marks for Module 2.
     * @param marks3 Marks for Module 3.
     */
    public void addModuleMarks(String id, int marks1, int marks2, int marks3) {
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student not found.");
        } else {
            student.addModule(marks1, marks2, marks3);
            System.out.println("Module marks added successfully.");
        }
    }

    /**
     * delete the student by their ID.
     *
     * @param id The student's ID.
     */
    public void deleteStudent(String id) {
        students.removeIf(student -> student.getId().equals(id));
        System.out.println("Student deleted successfully.");
    }

    /**
     * find and prints the details of a student by their ID.
     *
     * @param id The student's ID.
     */
    public void findStudent(String id) {
        Student student = findStudentById(id);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    /**
     * stores the details of all students in a CSV file.
     */
    public void storeStudentDetails() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) {
            for (Student student : students) {
                writer.println(studentToCSVString(student));
            }
            System.out.println("Student details stored successfully.");
        } catch (IOException e) {
            System.err.println("Error storing student details: " + e.getMessage());
        }
    }

    /**
     * loads student details from a CSV file.
     */
    public void loadStudentDetails() {
        File file = new File(FILENAME);
        if (!file.exists()) {
            System.out.println("No data file found. Starting with an empty list.");
            return;
        }

        students.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                students.add(studentFromCSVString(line));
            }
            System.out.println("Student details loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading student details: " + e.getMessage());
        }
    }

    /**
     * sorts and prints the list of students by their names.
     */
    public void viewStudentsSortedByName() {
        sortStudentsByName(students);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    /**
     * find a student by their ID.
     *
     * @param id The student's ID.
     * @return The student if found, otherwise null.
     */
    private Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    /**
     * converts a student object to a CSV string.
     *
     * @param student The student.
     * @return the CSV string representing the student.
     */
    private String studentToCSVString(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(student.getId()).append(",")
                .append(student.getName()).append(",");
        for (Module module : student.getModules()) {
            sb.append(module.getMarks1()).append(",")
                    .append(module.getMarks2()).append(",")
                    .append(module.getMarks3()).append(",");
        }
        return sb.toString();
    }

    /**
     * creates student object from a CSV string.
     *
     * @param csvLine The CSV string.
     * @return The student.
     */
    private Student studentFromCSVString(String csvLine) {
        String[] parts = csvLine.split(",");
        String id = parts[0];
        String name = parts[1];
        Student student = new Student(id, name);
        for (int i = 2; i < parts.length; i += 3) {
            int marks1 = Integer.parseInt(parts[i]);
            int marks2 = Integer.parseInt(parts[i + 1]);
            int marks3 = Integer.parseInt(parts[i + 2]);
            student.addModule(marks1, marks2, marks3);
        }
        return student;
    }

    /**
     * sorts a list of students by their names.
     *
     * @param students The list of students.
     */
    private void sortStudentsByName(List<Student> students) {
        students.sort(Comparator.comparing(Student::getName));
    }
}
