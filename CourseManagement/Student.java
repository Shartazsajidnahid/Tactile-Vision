package UserPackage;

import java.util.List;

public class Student extends AuthUser{

    private String rollno, batch;

    public Student(String fullname, String id, String address, String email, String phoneNo, String rollno, String batch) {
        super(fullname, id, address, email, phoneNo);
        this.rollno = rollno;
        this.batch = batch;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public List<Document> checkMark(Course course){

        //
        return foundDoc;
    }
}
