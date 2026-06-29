
public abstract class BaseRegistration implements CourseRegistration {
    private final String courseCode;
    private final String studentName;
    private final int credits;

    protected BaseRegistration(String courseCode, String studentName, int credits) {
        if (courseCode.isEmpty() || studentName.isEmpty()) {
            throw new IllegalArgumentException("Kurscode und Studierendenname dürfen nicht leer sein");
        }
        if (credits <= 0) {
            throw new IllegalArgumentException("Credits müssen positiv sein");
        }
        this.courseCode = courseCode;
        this.studentName = studentName;
        this.credits = credits;
    }

    public String courseCode() { return courseCode; }
    public String studentName() { return studentName; }
    public int credits() { return credits; }

    public abstract ExamType examType();
}
