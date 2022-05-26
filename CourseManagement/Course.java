package UserPackage;

import javax.print.Doc;
import java.util.List;

public class Course {
    private String courseID, courseName;
    private List<Document> documents;

    public Course(String courseID, String courseName, List<Document> documents) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.documents = documents;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public void addDocuments(Document document){
        this.documents.add(document);
    }
    public void removeDocument(List<Document> documents){
        this.documents.remove(documents);
    }
}
