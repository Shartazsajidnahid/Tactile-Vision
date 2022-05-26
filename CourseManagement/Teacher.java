package UserPackage;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends AuthUser{

    private List<Course> courses;

    public Teacher(String fullname, String id, String address, String email, String phoneNo, List<Course> courses) {
        super(fullname, id, address, email, phoneNo);
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    public void addCourses(Course course) {
        this.courses.add(course);
    }

    public List<Document> searchDocument(Course course){
        List<Document> foundDoc = new ArrayList<Document>();

        //search from DB using courseID

        return foundDoc;
    }
}
