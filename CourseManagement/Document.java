package UserPackage;

import java.awt.image.BufferedImage;
import java.util.List;

public class Document {

    private String studentID, documentID, marks, comment;
    private List<BufferedImage> inputImage;
    private List<BufferedImage> outputImage;

    public Document(String studentID, String documentID, String marks, String comment, List<BufferedImage> inputImage, List<BufferedImage> outputImage) {
        this.studentID = studentID;
        this.documentID = documentID;
        this.marks = marks;
        this.comment = comment;
        this.inputImage = inputImage;
        this.outputImage = outputImage;
    }
   ;

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<BufferedImage> getInputImage() {
        return inputImage;
    }

    public void setInputImage(List<BufferedImage> inputImage) {
        this.inputImage = inputImage;
    }

    public List<BufferedImage> getOutputImage() {
        return outputImage;
    }

    public void setOutputImage(List<BufferedImage> outputImage) {
        this.outputImage = outputImage;
    }

    public void addinputImage(BufferedImage image){
        this.inputImage.add(image);
    }
    public void addoutputImage(BufferedImage image){
        this.outputImage.add(image);
    }
    public void removeinputImage( BufferedImage image){
        this.inputImage.remove(image);
    }
    public void removeoutputImage(BufferedImage image){
        this.outputImage.remove(image);
    }
}
