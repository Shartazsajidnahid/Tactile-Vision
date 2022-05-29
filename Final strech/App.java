

import java.awt.image.BufferedImage;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        ImageRead obj= new ImageRead();
        BufferedImage img1= obj.ImageRead2();
        BufferedImage image2= new BufferedImage(img1.getWidth(),img1.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        image2.getGraphics().drawImage(img1, 0, 0, null);
//        Enhancement1 obj1= new Enhancement1();
//        obj1.function1(image2);
        dotIdentifier obj2= new dotIdentifier();
        obj2.findBlackPixel(image2);

//        FunctionsForMid obj3=new FunctionsForMid();
//        System.out.println(obj3.parseID("std_realID"));
//        System.out.println(obj3.parseTable("std_realID"));
    }
}
