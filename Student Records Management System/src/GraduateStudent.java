class GraduateStudent extends Student {
    private String thesisTopic;

    public GraduateStudent(String name, String studentID, double GPA, String thesisTopic) {
        super(name, studentID, GPA);
        this.thesisTopic = thesisTopic;
    }

    @Override
    public void displayStudentInfo() {
        System.out.printf("Graduate Student: %s, ID: %s, GPA: %.2f, Thesis: %s%n",
                getName(), getStudentID(), getGPA(), thesisTopic);
    }
}