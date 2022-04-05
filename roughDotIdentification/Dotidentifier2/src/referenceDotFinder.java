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
import java.util.Queue;
public class referenceDotFinder {
    int movex[]={0,0,1,1,1,-1,-1,-1};
    int movey[]={1,-1,1,-1,0,0,1,-1};
    ArrayList <ArrayList> visited;
    BufferedImage image;
    void findBlackPixel(BufferedImage image1) throws IOException {

        visited= new ArrayList<>();
        image= new BufferedImage(image1.getWidth(),image1.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        image.getGraphics().drawImage(image1, 0, 0, null);
        BufferedImage image2= new BufferedImage(image.getWidth(),image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        image2.getGraphics().drawImage(image, 0, 0, null);
        int height=image.getHeight();
        int width= image.getWidth();
        ArrayList<Boolean> tempList= new ArrayList();
        int blueP= 0xFF0000FF;
//        for(int i=0;i<image.getWidth();i++) {
//            for (int j = 0; j < image.getHeight(); j++) {
//                if(image3.getRGB(i,j)!=0xFFFFFFFF)
//                    image3.setRGB(i,j,blueP);
//            }
//        }
////
        for(int i=0;i<height;i++)tempList.add(Boolean.FALSE);
//        Collections.fill(tempList,Boolean.FALSE);
        for(int i=0;i<width;i++){
            visited.add(tempList);
        }
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                    int pixel= image.getRGB(i,j);
                    Color color= new Color(pixel, true);
                    if(image.getRGB(i,j)!=0xFFFFFFFF){
//                        System.out.println(i + " " + j );
                        //image2.setRGB(i,j,blueP);
                        int sx=i;
                        int sy=j;

                        ArrayList<Integer> d=new ArrayList<>();
                        d.add(sx);
                        d.add(sy);
                        ArrayList<ArrayList> pixelsBlack=new ArrayList<>();
                        pixelsBlack.add(new ArrayList<>(d));
                        d.clear();
                        recFindBlack(sx,sy,pixelsBlack);
                        System.out.println(pixelsBlack.size());
                        if(pixelsBlack.size()>=15){
                            System.out.println("This is a dot!");
                            for(int x=0;x<pixelsBlack.size();x++){
                                Integer xy= (Integer) pixelsBlack.get(x).get(0);
                                Integer xy2= (Integer) pixelsBlack.get(x).get(1);
                                int xc=xy.intValue();
                                int yc=xy2.intValue();
                                image2.setRGB(xc,yc,blueP);
                            }
                        }
                        else{
                            for(int x=0;x<pixelsBlack.size();x++){
                                Integer xy= (Integer) pixelsBlack.get(x).get(0);
                                Integer xy2= (Integer) pixelsBlack.get(x).get(1);
                                int xc=xy.intValue();
                                int yc=xy2.intValue();
                                visited.get(xc).set(yc,Boolean.FALSE);
                            }
                        }
                        pixelsBlack.clear();
                    }
            }
        }
        ImageIO.write(image2 , "png", new File("image.png"));
//        ImageIO.write(image3 , "png", new File("image3.png"));
    }
    void recFindBlack(int x,int y,ArrayList<ArrayList> pixelsBlack) {
        visited.get(x).set(y, Boolean.TRUE);
        Queue<ArrayList> q
                = new LinkedList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(x);
        temp.add(y);
        q.add(new ArrayList<>(temp));
        temp.clear();
        while (!q.isEmpty()) {
//            System.out.println(q.element().get(0)+" "+q.element().get(1));
            int X = (int)q.element().get(0);
            int Y = (int)q.element().get(1);
            q.remove();
            for (int i = 0; i < 8; i++) {
                int newX = X + movex[i];
                int newY = Y + movey[i];
                if (Math.min(newX, newY) >= 0 && newX < image.getWidth() && newY < image.getHeight()) {
                    if (image.getRGB(newX, newY) != 0xFFFFFFFF) {
                        if (visited.get(newX).get(newY).equals(Boolean.FALSE)) {
                            visited.get(newX).set(newY, Boolean.TRUE);
                            temp.clear();
                            temp.add(newX);
                            temp.add(newY);
                            pixelsBlack.add(new ArrayList<>(temp));

                            q.add(new ArrayList<>(temp));
                        }
                    }
                }
            }
        }
    }

}
