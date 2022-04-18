import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
public class referenceDotFinder2 {
    private static final int[] row = { -1, -1, -1, 0, 0, 1, 1, 1 };
    private static final int[] col = { -1, 0, 1, -1, 1, -1, 0, 1 };

    BufferedImage image;
    void findBlackPixel(BufferedImage image1) throws IOException {

        image= new BufferedImage(image1.getWidth(),image1.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        image.getGraphics().drawImage(image1, 0, 0, null);
        BufferedImage image2= new BufferedImage(image.getWidth(),image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        image2.getGraphics().drawImage(image, 0, 0, null);
        int height=image.getHeight();
        int width= image.getWidth();
        int blueP= 0xFF0000FF;
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                if(image.getRGB(i,j)<=0xFF010101){
                    ArrayList<List> d=new ArrayList<>(floodFill(i,j,image2));
                    if(d.size()>=25){
                        System.out.println("this is a dot");
                    }
                }
            }
        }
        //ArrayList<List> tst=floodFill(1273,1112,image2);
        //System.out.println(tst.size());
        ImageIO.write(image2 , "png", new File("/home/sakib/Desktop/Academics/SPL/SPL-2-Tactile-Vision-main/roughDotIdentification/Dotidentifier2/dotFinding2.png"));

    }
    ArrayList<List> floodFill(int x,int y,BufferedImage image2) {
        ArrayList<List> arr=new ArrayList<>();
        ArrayList<Integer> point= new ArrayList<>();
        point.add(x);
        point.add(y);
        arr.add(new ArrayList<>(point));
        Queue<ArrayList<Integer>> q = new LinkedList<>();
        q.add(new ArrayList<>(point));
        while(q.isEmpty()==false){
            point= new ArrayList<>(q.remove());
            int x1= point.get(0);
            int y1= point.get(1);
            image2.setRGB(x1,y1,0xFFFF0000);
            for(int i=0;i<row.length;i++){
                int x2=x1+row[i];
                int y2=y1+col[i];
                if(isGood(x2,y2,image2)){
                    point=new ArrayList<>();
                    point.add(x2);
                    point.add(y2);
                    arr.add(new ArrayList(point));
                    q.add(new ArrayList<>(point));
                    image2.setRGB(x2,y2,0xFFFF0000);
                }

            }
        }
        return arr;
    }
    boolean isGood(int x,int y,BufferedImage image2){
        return(x>=0 && y>=0 && x<image2.getWidth() && y<image2.getHeight() &&
                image2.getRGB(x,y)<=0xFF010101);
    }

}
