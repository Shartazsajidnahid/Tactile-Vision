package DotIdentifier;

import org.opencv.core.Core;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class App {
    public List<String> main(String filename) throws IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        DotIdentifier.ImageRead obj= new ImageRead();
        BufferedImage image1= obj.ImageRead2(filename);
//        BufferedImage image2= new BufferedImage(img1.getWidth(),img1.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
//        image2.getGraphics().drawImage(img1, 0, 0, null);
        image1= new WhiteKnight(245,image1).deployTheKnights();
        dotIdentifier obj2= new dotIdentifier();
        return obj2.findBlackPixel(image1);

//        ImageIO.write(image1,"png",new File("1s.png"));

//        FunctionsForMid obj3=new FunctionsForMid();
//        System.out.println(obj3.parseID("std_realID"));
//        System.out.println(obj3.parseTable("std_realID"));
    }
}
