import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        while (true) {
            System.out.println("\nWelcome to Student Management Services. What would you like to do?");
            System.out.println("1. Add Student\n2. Save Student Records\n3. Load Student Records");
            System.out.println("4. Search Student by ID\n5. Sort Students by GPA\n6. Sort Students by Name");
            System.out.println("7. Display All Students");
            System.out.println("8. Display Honor Students");
            System.out.println("9. Display Average GPA\n10. Exit\n");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            try {
                switch (choice) {
                    case 1 -> addStudent(scanner, manager);
                    case 2 -> manager.saveStudents();
                    case 3 -> manager.loadStudents();
                    case 4 -> {
                        System.out.print("Enter Student ID (7 digits): ");
                        String studentID = scanner.nextLine();
                        manager.searchStudentByID(studentID);
                    }
                    case 5 -> {
                        manager.sortStudentsByGPA();
                        System.out.println("Students sorted by GPA:");
                        manager.displayAllStudents();
                    }
                    case 6 -> {
                        manager.sortStudentsByName();
                        System.out.println("Students sorted by name:");
                        manager.displayAllStudents();
                    }
                    case 7 -> manager.displayAllStudents();
                    case 8 -> {
                        List<Student> honorStudents = manager.getHonorStudents();
                        if (honorStudents.isEmpty()) {
                            System.out.println("No honor students found.");
                        } else {
                            System.out.println("Honor Students:");
                            honorStudents.forEach(Student::displayStudentInfo);
                        }
                    }
                    case 9 -> {
                        double avgGPA = manager.getAverageGPA();
                        System.out.printf("Average GPA: %.2f%n", avgGPA);
                    }
                    case 10 -> {
                        scanner.close();
                        System.out.println("Exiting program.");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addStudent(Scanner scanner, StudentManager manager) {
        char type;
        while (true) {
            System.out.print("Enter type (U for Undergraduate, G for Graduate): ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.length() == 1) {
                type = input.charAt(0);
                if (type == 'U' || type == 'G') break;
            }
            System.out.println("Invalid input. Please enter 'U' or 'G'.");
        }

        String name;
        while (true) {
            System.out.print("Name (First and Last Name): ");
            name = scanner.nextLine().trim();
            if (name.split("\\s+").length >= 2) break;
            System.out.println("Please enter both first and last name.");
        }

        String studentID;
        while (true) {
            System.out.print("Student ID (7 digits): ");
            studentID = scanner.nextLine().trim();
            if (studentID.matches("\\d{7}")) break;
            System.out.println("Invalid ID. Please enter exactly 7 digits.");
        }

        double GPA;
        while (true) {
            try {
                System.out.print("GPA (0.0 - 4.0): ");
                GPA = Double.parseDouble(scanner.nextLine());
                if (GPA >= 0.0 && GPA <= 4.0) break;
                System.out.println("GPA must be between 0.0 and 4.0.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid GPA format.");
            }
        }

        if (type == 'U') {
            int yearLevel;
            while (true) {
                try {
                    System.out.print("Year Level: ");
                    yearLevel = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid year level.");
                }
            }
            manager.addStudent(new UndergraduateStudent(name, studentID, GPA, yearLevel));
        } else {
            System.out.print("Thesis Topic: ");
            String thesis = scanner.nextLine();
            manager.addStudent(new GraduateStudent(name, studentID, GPA, thesis));
        }

        System.out.println("Student added successfully.");
    }
}
