
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2a) TODO: CourseCatalog implementieren und Konstruktor vervollständigen
public class SimpleCourseCatalog implements CourseCatalog {
    private final List<CourseRegistration> registrations;

    public SimpleCourseCatalog() {
        registrations = new ArrayList<>();
    }

    public void addRegistration(CourseRegistration registration) {
        registrations.add(registration);
    }

    public int totalCredits() {
        int total = 0;

        for (CourseRegistration registration:registrations){
            total += registration.credits();
        }
        
        return total;
    }

    public Collection<CourseRegistration> registrationsForCourse(String courseCode) {
        Collection<CourseRegistration> reg = new ArrayList<>();

        for (CourseRegistration registration:registrations){
            if (registration.courseCode().equals(courseCode)){
                reg.add(registration);
            }
        }
        return reg;
    }

    public Map<ExamType, Integer> creditsByExamType() {
        Map<ExamType, Integer> map = new EnumMap<>(ExamType.class);
        for (CourseRegistration registration:registrations){
            ExamType typ = registration.examType();
            map.put(typ, map.getOrDefault(typ, 0)+registration.credits());
        }
        return map;
    }

    public CourseCatalog limitToStudents(int maxStudents) {
        SimpleCourseCatalog newcat = new SimpleCourseCatalog();
        Map<String, Integer> total = new HashMap<>();
        for (CourseRegistration registration:registrations){
            String code = registration.courseCode();
            int count = total.getOrDefault(code, 0);

            if (count < maxStudents){
                newcat.addRegistration(registration);
                total.put(code, count+1);
            }
        }
        return newcat;
    }

    public Collection<CourseRegistration> getRegistrations() {
        return registrations;
    }
}
