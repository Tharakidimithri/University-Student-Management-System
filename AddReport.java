import java.util.*;

public class AddReport {

    private List<Student> students;

    // initialize with the list of students
    public AddReport(List<Student> students) {
        this.students = students;
    }

    // generates a summary report with total registrations and students scoring above 40 in each module
    public void generateSummary() {
        int totalRegistrations = students.size(); // get total number of students
        // count students scoring above 40 in Module 1
        long studentsAbove40Module1 = students.stream().filter(s -> s.getModules().stream().anyMatch(m -> m.getMarks1() > 40)).count();
        long studentsAbove40Module2 = students.stream().filter(s -> s.getModules().stream().anyMatch(m -> m.getMarks2() > 40)).count();
        long studentsAbove40Module3 = students.stream().filter(s -> s.getModules().stream().anyMatch(m -> m.getMarks3() > 40)).count();

        // Print out the summary
        System.out.println("Total student registrations: " + totalRegistrations);
        System.out.println("Total students scoring more than 40 marks in Module 1: " + studentsAbove40Module1);
        System.out.println("Total students scoring more than 40 marks in Module 2: " + studentsAbove40Module2);
        System.out.println("Total students scoring more than 40 marks in Module 3: " + studentsAbove40Module3);
    }

    //  a complete report with detailed student information
    public void generateCompleteReport() {
        List<StudentReport> studentReports = new ArrayList<>();
        for (Student student : students) {
            for (Module module : student.getModules()) {
                int total = module.getMarks1() + module.getMarks2() + module.getMarks3(); // calculate total marks
                double average = total / 3.0; // calculate average marks
                // create a new StudentReport and add it to the list
                studentReports.add(new StudentReport(student.getId(), student.getName(), module.getMarks1(), module.getMarks2(), module.getMarks3(), total, average, module.getGrade()));
            }
        }

        // sort student reports by average marks in descending order
        studentReports.sort(Comparator.comparingDouble(StudentReport::getAverage).reversed());

        // print out each student's report
        for (StudentReport report : studentReports) {
            System.out.println(report);
        }
    }

    // Inner class to represent a student's report
    private class StudentReport {
        private String id;
        private String name;
        private int marks1;
        private int marks2;
        private int marks3;
        private int total;
        private double average;
        private String grade;

        //  initialize the report details
        public StudentReport(String id, String name, int marks1, int marks2, int marks3, int total, double average, String grade) {
            this.id = id;
            this.name = name;
            this.marks1 = marks1;
            this.marks2 = marks2;
            this.marks3 = marks3;
            this.total = total;
            this.average = average;
            this.grade = grade;
        }

        // Getter for average marks
        public double getAverage() {
            return average;
        }

        // toString method to format the report details for printing
        @Override
        public String toString() {
            return "Student ID: " + id + ", Name: " + name + ", Marks1: " + marks1 + ", Marks2: " + marks2 + ", Marks3: " + marks3 +
                    ", Total: " + total + ", Average: " + average + ", Grade: " + grade;
        }
    }
}

