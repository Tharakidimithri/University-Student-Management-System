import java.util.ArrayList;
import java.util.List;

public class Student {
    private String id;
    private String name;
    private List<Module> modules;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.modules = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Module> getModules() {
        return modules;
    }

    /**
     * Adds a module with specified marks to the student's list of modules.
     * @param marks1 Marks for the first module.
     * @param marks2 Marks for the second module.
     * @param marks3 Marks for the third module.
     */

    public void addModule(int marks1, int marks2, int marks3) {
        Module module = new Module(marks1, marks2, marks3);
        modules.add(module);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", modules=" + modules +
                '}';
    }

    /**
     * Validates the student ID.
     * @param studentId The student ID to validate.
     * @return True if the ID is valid (length 8 and starts with 'w'), false otherwise.
     */
    public static boolean isValidStudentId(String studentId) {
        if (studentId == null || studentId.length() != 8) {
            return false;
        }

        // Check if the first character is 'w'
        if (studentId.charAt(0) != 'w') {
            return false;
        }

        // Check if the remaining characters are digits
        for (int i = 1; i < studentId.length(); i++) {
            if (!Character.isDigit(studentId.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}

