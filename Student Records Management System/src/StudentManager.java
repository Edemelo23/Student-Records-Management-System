import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class StudentManager {
    private LinkedList<Student> students = new LinkedList<>();
    private static final String FILE_NAME = "students.dat";

    public void addStudent(Student student) {
        students.add(student);
    }

    public void saveStudents() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(students);
            System.out.println("Students saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadStudents() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (LinkedList<Student>) in.readObject();
            System.out.println("Students loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading students: " + e.getMessage());
        }
    }

    public void searchStudentByID(String studentID) {
        students.stream()
                .filter(s -> s.getStudentID().equals(studentID))
                .findFirst()
                .ifPresentOrElse(Student::displayStudentInfo,
                        () -> System.out.println("Student not found."));
    }

    public void sortStudentsByGPA() {
        Sorter.mergeSort(students, Sorter.getGPAComparator());
    }

    public void sortStudentsByName() {
        Sorter.mergeSort(students, Sorter.getNameComparator());
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
            return;
        }
        students.stream().forEach(Student::displayStudentInfo);
    }

    public List<Student> getHonorStudents() {
        return students.stream()
                .filter(s -> s.getGPA() >= 3.5)
                .collect(Collectors.toList());
    }

    public Map<String, List<Student>> groupByDegreeType() {
        return students.stream().collect(Collectors.groupingBy(s ->
                (s instanceof UndergraduateStudent) ? "Undergraduate" : "Graduate"
        ));
    }

    public double getAverageGPA() {
        return students.stream()
                .mapToDouble(Student::getGPA)
                .average()
                .orElse(0.0);
    }

    public List<String> getUppercaseNames() {
        return students.stream()
                .map(s -> s.getName().toUpperCase())
                .collect(Collectors.toList());
    }

    public TreeSet<Student> getStudentsByGPA() {
        return students.stream()
                .collect(Collectors.toCollection(() -> new TreeSet<>(Sorter.getGPAComparator())));
    }

    public Student binarySearch(String studentID) {
        List<Student> list = students.stream()
                .sorted(Comparator.comparing(Student::getStudentID))
                .collect(Collectors.toList());

        Student key = new UndergraduateStudent("Temp", studentID, 0.0, 1);

        int index = Collections.binarySearch(list, key, Comparator.comparing(Student::getStudentID));
        return index >= 0 ? list.get(index) : null;
    }

    public int countStudentsByType(Class<? extends Student> type) {
        return (int) students.stream()
                .filter(type::isInstance)
                .count();
    }
}
