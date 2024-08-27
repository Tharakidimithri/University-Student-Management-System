import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem(); // make a new student management system
        Scanner scanner = new Scanner(System.in); // get the user input

        while (true) { // menu loop
            System.out.println("Menu:");
            System.out.println("1. Check available seats");
            System.out.println("2. Register student");
            System.out.println("3. Delete student");
            System.out.println("4. Find a student");
            System.out.println("5. Store student details in a CSV file");
            System.out.println("6. Load student details from the CSV file");
            System.out.println("7. View the list of students based on their names");
            System.out.println("8. Add module marks for a student");
            System.out.println("9. Exit");
            System.out.println("C: Generate summary report");
            System.out.println("D: Generate complete report");
            System.out.println("Welcome to Student Management system");
            System.out.println("Please enter your choice here : ");

            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "1":
                    sms.checkAvailableSeats();
                    break;
                case "2":
                    String id;
                    while (true) {
                        System.out.print("Enter student ID: ");
                        id = scanner.nextLine();
                        if (!Student.isValidStudentId(id)) {
                            System.out.println("Invalid Student ID. Please try again.");
                        } else {
                            break;
                        }
                    }
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    sms.registerNewStudent(id, name);
                    break;
                case "3":
                    System.out.print("Enter student ID to delete: ");
                    id = scanner.nextLine();
                    sms.deleteStudent(id);
                    break;
                case "4":
                    System.out.print("Enter student ID to find: ");
                    id = scanner.nextLine();
                    sms.findStudent(id);
                    break;
                case "5":
                    sms.storeStudentDetails();
                    break;
                case "6":
                    sms.loadStudentDetails();
                    break;
                case "7":
                    sms.viewStudentsSortedByName();
                    break;
                case "8":
                    System.out.print("Enter student ID to add module marks: ");
                    id = scanner.nextLine();
                    System.out.print("Enter marks for Module 1: ");
                    int marks1 = scanner.nextInt();
                    System.out.print("Enter marks for Module 2: ");
                    int marks2 = scanner.nextInt();
                    System.out.print("Enter marks for Module 3: ");
                    int marks3 = scanner.nextInt();
                    scanner.nextLine();
                    sms.addModuleMarks(id, marks1, marks2, marks3);
                    break;
                case "9":
                    System.out.println("Exiting...");
                    return;
                case "C":
                    AddReport summaryReport = new AddReport(sms.getStudents());
                    summaryReport.generateSummary();
                    break;
                case "D":
                    AddReport completeReport = new AddReport(sms.getStudents());
                    completeReport.generateCompleteReport();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}





