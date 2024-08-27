public class Module {
    private int marks1;
    private int marks2;
    private int marks3;
    private String grade;

    public Module(int marks1, int marks2, int marks3) {
        this.marks1 = marks1;
        this.marks2 = marks2;
        this.marks3 = marks3;
        calculateGrade(); // Calculate the grade according to creation
    }

    public int getMarks1() {
        return marks1;
    }

    public void setMarks1(int marks1) {
        this.marks1 = marks1;
        calculateGrade();
    }

    public int getMarks2() {
        return marks2;
    }

    public void setMarks2(int marks2) {
        this.marks2 = marks2;
        calculateGrade();
    }

    public int getMarks3() {
        return marks3;
    }

    public void setMarks3(int marks3) {
        this.marks3 = marks3;
        calculateGrade();
    }

    public String getGrade() {
        return grade;
    }

    private void calculateGrade() {
        double average = (marks1 + marks2 + marks3) / 3.0;

        if (average >= 80) {
            grade = "Distinction";
        } else if (average >= 70) {
            grade = "Merit";
        } else if (average >= 40) {
            grade = "Pass";
        } else {
            grade = "Fail";
        }
    }
    // Private method to calculate the grade based on average marks
    @Override
    public String toString() {
        return "Module{" +
                "marks1=" + marks1 +
                ", marks2=" + marks2 +
                ", marks3=" + marks3 +
                ", grade='" + grade + '\'' +
                '}';
    }
}
