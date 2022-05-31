import java.awt.*;
import java.awt.image.BufferedImage;

public class WhiteKnight {
    private int thresh_hold;
    BufferedImage image;
    public WhiteKnight(int thresh_hold, BufferedImage image){
        this.thresh_hold=thresh_hold;
        this.image=image;
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

}
