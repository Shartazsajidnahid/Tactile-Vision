import com.example.dotidentification.ImageRead;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

public class App {
    public static void main(String[] args) throws IOException {
        ImageRead obj= new ImageRead();
        BufferedImage img1= obj.ImageRead2();
        referenceDotFinder obj3= new referenceDotFinder();
        obj3.findBlackPixel(img1);

    }
}
