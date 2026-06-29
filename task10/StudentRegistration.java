public class StudentRegistration extends BaseRegistration {

    public ExamType examType;

    public StudentRegistration(String courseCode, String studentName, int credits, ExamType examType) {
        super(courseCode, studentName, credits);
        this.examType = examType;
    }

    public ExamType examType() {
        return examType;
    }
}
