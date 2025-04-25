import java.io.Serializable;

public abstract class Student implements Serializable, Comparable<Student> {
    private String name;
    private String studentID;
    private double GPA;

    public Student(String name, String studentID, double GPA) {
        this.name = formatName(name);
        this.studentID = validateStudentID(studentID);
        this.GPA = validateGPA(GPA);
    }

    private String formatName(String name) {
        String[] parts = name.trim().split("\\s+");
        StringBuilder formatted = new StringBuilder();
        for (String part : parts) {
            if (!part.isEmpty()) {
                formatted.append(Character.toUpperCase(part.charAt(0)))
                        .append(part.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return formatted.toString().trim();
    }

    private String validateStudentID(String studentID) {
        if (!studentID.matches("\\d{7}")) {
            throw new IllegalArgumentException("Student ID must be 7 digits.");
        }
        return studentID;
    }

    private double validateGPA(double GPA) {
        if (GPA < 0.0 || GPA > 4.0) {
            throw new IllegalArgumentException("GPA must be between 0.0 and 4.0.");
        }
        return GPA;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public double getGPA() {
        return GPA;
    }

    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }

    public abstract void displayStudentInfo();
}