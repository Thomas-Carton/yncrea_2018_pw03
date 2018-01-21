package yncrea.pw03.app;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import yncrea.pw03.entity.Course;
import yncrea.pw03.entity.Student;
import yncrea.pw03.entity.Work;
import yncrea.pw03.service.StudentService;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Application {

    private static Random random = new SecureRandom();

    private static String[] allCoursesNames = new String[] { "physics", "mathematics", "english", "philosophy", "chemistry", "spanish", "statistics", "java", "web", "business intelligence" };

    private static String[] allWorksNames = new String[] { "homework", "exam", "practice", "lab" };


    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext("yncrea.pw03.config");
        StudentService studentService = context.getBean(StudentService.class);
        List<Student> students = getStudents();
        for (Student student : students) {
            studentService.saveStudent(student);
        }
        context.close();
    }


    private static List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Michu", "Germaine"));
        students.add(new Student("Michu", "Ghislain"));
        students.add(new Student("Dupont", "Marcel"));
        students.add(new Student("Lefebvre", "Jacky"));
        students.add(new Student("Duval", "Janine"));
        setCourses(students);
        return students;
    }


    private static void setCourses(List<Student> students) {
        for (Student student : students) {
            List<Course> courses = new ArrayList<>();
            List<String> choosenCourses = new ArrayList<>();
            final int limit = random.nextInt(5) + 1;
            for (int i = 0; i < limit; i++) {
                System.out.println("Course addition for student " + student.getLastname());
                String courseName = allCoursesNames[random.nextInt(allCoursesNames.length)];
                while (choosenCourses.contains(courseName)) {
                    courseName = allCoursesNames[random.nextInt(allCoursesNames.length)];
                }
                choosenCourses.add(courseName);
                final Course course = new Course(courseName, student);
                course.setValidated(random.nextBoolean());
                courses.add(course);
            }
            setWorks(courses);
            student.setCourses(courses);
        }
    }


    private static void setWorks(List<Course> courses) {
        for (Course course : courses) {
            List<Work> works = new ArrayList<>();
            for (int i = 0; i < random.nextInt(10) + 1; i++) {
                String workName = allWorksNames[random.nextInt(allWorksNames.length)];
                System.out.println("Add the work " + workName + " to the course "+course.getName());
                works.add(new Work(workName, random.nextInt(15)+5,course));
            }
            course.setWorks(works);
        }
    }
}
