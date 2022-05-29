import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

import static java.awt.Color.HSBtoRGB;
import static java.awt.Color.RGBtoHSB;

public class Enhancement1 {
    public void function1(BufferedImage image) throws IOException {
        BufferedImage image2= new BufferedImage(image.getWidth(),image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        image2.getGraphics().drawImage(image, 0, 0, null);
        apply(image2);
    }
    float calcMean(BufferedImage image){
        float sum1=0;
        for(int i=0;i<image.getWidth();i++){
            for(int j=0;j<image.getHeight();j++){
                float[] arr= new float[3];
                arr=RGBtoHSB(new Color(image.getRGB(i,j)).getRed(),
                        new Color(image.getRGB(i,j)).getBlue(),
                        new Color(image.getRGB(i,j)).getGreen(),null);
                float sum2= arr[0]+arr[1]+arr[2];
                sum2/=3;
                sum1+=sum2;
            }
        }
        sum1/=(image.getHeight()*image.getWidth());
        return sum1;
    }
    float calcDev(BufferedImage image, float mean1){
        float sum1=0;
        for(int i=0;i<image.getWidth();i++){
            for(int j=0;j<image.getHeight();j++){
                float[] arr= new float[3];
                arr=RGBtoHSB(new Color(image.getRGB(i,j)).getRed(),
                        new Color(image.getRGB(i,j)).getBlue(),
                        new Color(image.getRGB(i,j)).getGreen(),null);
                float sum2= arr[0]+arr[1]+arr[2];
                sum2/=3;
                sum1+= ((mean1-sum2) * (mean1-sum2));

            }
        }
        sum1/=(image.getHeight()*image.getWidth());
        sum1= (float) Math.sqrt(sum1);
    return sum1;
    }
    int classify(float mean, float dev){
        float D=Math.abs(4*dev);
        float t =5;
        if(D<1/t){
            return 0;
        }
        else return 1;
    }
    float calculateGAMMA(float dev){
        return (float)-(Math.log(dev)/Math.log(2));
    }
    void apply(BufferedImage image) throws IOException {
        BufferedImage image2= new BufferedImage(image.getWidth(),image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        float  mean= calcMean(image);

        float dev = calcDev(image,mean);
        float gamma= calculateGAMMA(dev);
        System.out.println(gamma);
        for(int i=0;i<image.getWidth();i++){
            for(int j=0;j<image.getHeight();j++){
                float[] arr= new float[3];
                arr=RGBtoHSB(new Color(image.getRGB(i,j)).getRed(),
                        new Color(image.getRGB(i,j)).getGreen(),
                        new Color(image.getRGB(i,j)).getBlue(),null);
                float[] arr2 = new float[3];
                float[] arr3=arrPow(arr,gamma);
                float[] arr4=calcC(mean,arr,gamma);
                for(int k=0;k<3;k++){
                    arr2[k]=arr3[k]/ arr4[k];
                }
                //System.out.println(arr2[0] + " "+ arr2[1] + " "+arr2[2]);
                image2.setRGB(i,j,HSBtoRGB(arr2[0],arr2[1],arr2[2]));
            }
        }
        ImageIO.write(image2 , "png", new File("/home/sakib/Desktop/Academics/SPL/SPL-2-Tactile-Vision-main/roughDotIdentification/Dotidentifier2/dotFind2.png"));

    }
    float [] calcC(float mean, float[] arr, float gamma){
        float[] c;
        int h=heaveSide(((float)0.5-mean));
        float[] kArr=calcK(mean, arr, gamma);
        c=arrAdd(kArr,-1);
        float []c2=arrMul(c,(float)h);
        float []c3=arrAdd(c2,1);
        //System.out.println(c3[0]);
        return c3;
    }
    float[] calcK(float mean, float[] arr, float gamma){
        float k=0;
        float[] pArr = arrPow(arr,gamma);
        float[] arr2= arrSub(pArr,1);
        float[] arr3=arrMul(arr2,(float)Math.pow(mean,gamma));
        return arr3;
    }
    float[] arrPow(float[] arr,float gamma){
        float[] pArr= new float[3];
        for(int i=0;i<3;i++){
            pArr[i]= (float) Math.pow(arr[i],gamma);
        }
        return pArr;
    }
    float[] arrSub(float[] arr, float d){
        float[] nArr=new float[3];
        for(int i=0;i<3;i++){
            nArr[i]=d-arr[i];
        }
        return nArr;
    }
    float[] arrAdd(float[] arr, float d){
        float[] nArr=new float[3];
        for(int i=0;i<3;i++){
            nArr[i]=d+arr[i];
        }
        return nArr;
    }
    float[] arrMul(float[] arr, float d){
        float[] nArr=new float[3];
        for(int i=0;i<3;i++){
            nArr[i]=arr[i]*d;
        }
        return nArr;
    }
    int heaveSide(float x){
        if(x<=0)return 0;
        else return 1;
    }

}
