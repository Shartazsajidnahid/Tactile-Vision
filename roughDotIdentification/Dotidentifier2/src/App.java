import com.example.dotidentification.ImageRead;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

public class App {
    public static void main(String[] args) throws IOException {
        ImageRead obj= new ImageRead();
        BufferedImage img1= obj.ImageRead2();
        BufferedImage image2= new BufferedImage(img1.getWidth(),img1.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        image2.getGraphics().drawImage(img1, 0, 0, null);
        referenceDotFinder obj3= new referenceDotFinder();
        int count1=0;
//        for(int i=0;i<image2.getWidth();i++){
//            for(int j=0;j<image2.getHeight();j++){
//                if(image2.getRGB(i,j)!=0xFFFFFFFF){
//                    System.out.println(i+" "+j);
//                    count1++;
//                }
//            }
//        }

        obj3.findBlackPixel(image2);

    }
}
