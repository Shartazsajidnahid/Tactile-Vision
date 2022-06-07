package DotIdentifier;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WhiteKnight {
    private int thresh_hold;
    BufferedImage image;
    public WhiteKnight(int thresh_hold, BufferedImage image){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        this.thresh_hold=thresh_hold;
        this.image= image;
    }
    void invert(){
        int height= image.getHeight();
        int width=image.getWidth();
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                int rgb = image.getRGB(j,i);
                Color color= new Color(rgb);
                int red= color.getRed();
                int blue=color.getBlue();
                int green=color.getGreen();
                int greyLevel= red+blue+green;
                greyLevel/=3;
                if(greyLevel<thresh_hold){
                    image.setRGB(j,i,0xFFFFFFFF);
                }
                else{
                    image.setRGB(j,i,0xFF000000);
                }
            }
        }

    }
    public BufferedImage deployTheKnights(){
        invert();
        BufferedImageAndMat obj3= new BufferedImageAndMat();
        Mat matImage= obj3.img2Mat(image);

        Mat dst = new Mat();

//        Imgproc.threshold(matImage, dst, 50, 255, Imgproc.THRESH_BINARY_INV);
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
                new Size((2*1), (2*1)));
        Imgproc.erode(matImage, dst, kernel);
        return obj3.mat2Img(dst);
    }

}
