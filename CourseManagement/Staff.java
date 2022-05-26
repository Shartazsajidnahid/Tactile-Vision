package UserPackage;

public class Staff extends AuthUser{
    public Staff(String fullname, String id, String address, String email, String phoneNo) {
        super(fullname, id, address, email, phoneNo);
    }

    public void addInfo(){
        // select image
        // select student
        // assign
    }
    public void assignDoctoStudent(Document document, Student student){
        document.setStudentID(student.getId());
    }
}
