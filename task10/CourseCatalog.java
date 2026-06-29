
import java.util.Collection;
import java.util.Map;

public interface CourseCatalog {
    void addRegistration(CourseRegistration registration);
    int totalCredits();
    Collection<CourseRegistration> registrationsForCourse(String courseCode);
    Map<ExamType, Integer> creditsByExamType();
    CourseCatalog limitToStudents(int maxStudents);
    Collection<CourseRegistration> getRegistrations();
}
