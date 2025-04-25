class UndergraduateStudent extends Student {
    private int yearLevel;

    public UndergraduateStudent(String name, String studentID, double GPA, int yearLevel) {
        super(name, studentID, GPA);
        this.yearLevel = yearLevel;
    }

    @Override
    public void displayStudentInfo() {
        System.out.printf("Undergraduate Student: %s, ID: %s, GPA: %.2f, Year: %d%n",
                getName(), getStudentID(), getGPA(), yearLevel);
    }
}