import javax.imageio.ImageIO;
import javax.xml.transform.Templates;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
public class ReferenceDotFinder2 {
    private static final int[] row = { -1, -1, -1, 0, 0, 1, 1, 1 };
    private static final int[] col = { -1, 0, 1, -1, 1, -1, 0, 1 };
    private int dx=25;
    private int dy=34;
    private int cd=77;
    int no=44;
    private int bottomMargin=0;
    private int currCenterX;
    private int currCenterY;
    ArrayList<List> binarySequence;
    BufferedImage image;
    ArrayList<String> lineList= new ArrayList<>();
    void findBlackPixel(BufferedImage image1) throws IOException {

        image= new BufferedImage(image1.getWidth(),image1.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        image.getGraphics().drawImage(image1, 0, 0, null);
        BufferedImage image2= new BufferedImage(image.getWidth(),image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        image2.getGraphics().drawImage(image, 0, 0, null);

        int height=image.getHeight();
        int width= image.getWidth();
        int blueP= 0xFF0000FF;
        int dCount=0;
        for(int j=0;j<height;j++){
            for(int i=0;i<width;i++){
                int flag=0;
                if(image2.getRGB(i,j)<=0xFFAAAAAA){
                    image2.setRGB(i,j,0xFF00FF00);

                    ArrayList<List> d=new ArrayList<>(floodFill(i,j,image2));
                    if(d.size()>=25){
                        dCount++;
                        ArrayList<Integer> centers= new ArrayList<>(findDotCenter(d));
                        int dotCenterX=centers.get(0);
                        int dotCenterY=centers.get(1);
                        charFind(dotCenterX,dotCenterY);
                        j=j+210;
                        for(int x=0;x< image.getWidth();x++){
                            image.setRGB(x,j,0xFF0000FF);
                        }

                    }
                }
            }
        }
        //ArrayList<List> tst=floodFill(1273,1112,image2);
        //System.out.println(tst.size());
        for(int i=0;i<lineList.size();i++){
            System.out.print( lineList.get(i) +" ");
        }
        ImageIO.write(image2 , "png", new File("dotFinding3.png"));

    }
    ArrayList<List> floodFill(int x11,int y11,BufferedImage image2) {
        int x=x11;
        int y=y11;
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
                int x2=x1+col[i];
                int y2=y1+row[i];
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
                image2.getRGB(x,y)<0xFFAAAAAA);
    }
    ArrayList<Integer> findDotCenter(ArrayList<List> d){
        ArrayList<Integer> retList= new ArrayList<>();
        int leftMostX= (int) d.get(0).get(0);
        int leftMostY= (int) d.get(0).get(1);
        int rightMostX= (int) d.get(0).get(0);
        int rightMostY= (int) d.get(0).get(1);
        int upperMostX= (int) d.get(0).get(0);
        int upperMostY= (int) d.get(0).get(1);
        int lowerMostX= (int) d.get(0).get(0);
        int lowerMostY= (int) d.get(0).get(1);
        int lId=-1;
        int rId=-1;
        int uId=-1;
        int dId=-1;
        for(int k=0;k<d.size();k++){

            if(leftMostX>=(int)d.get(k).get(0)){
                leftMostX=(int)d.get(k).get(0);
                lId=k;
            }
            if(rightMostX<=(int)d.get(k).get(0)){
                rightMostX=(int)d.get(k).get(0);
                rId=k;
            }
            if(upperMostY<=(int)d.get(k).get(1)){
                upperMostY=(int)d.get(k).get(1);
                uId=k;
                bottomMargin=upperMostY;
            }
            if(lowerMostY>=(int)d.get(k).get(1)){
                lowerMostY=(int)d.get(k).get(1);
                dId=k;
            }

        }
        int dotCenterX=(int)d.get(lId).get(0)+(int)d.get(rId).get(0)
                +(int)d.get(uId).get(0)+(int)d.get(dId).get(0);
        int dotCenterY=(int)d.get(lId).get(1)+(int)d.get(rId).get(1)
                +(int)d.get(uId).get(1)+(int)d.get(dId).get(1);
        dotCenterX/=4;
        dotCenterY/=4;
        retList.add(dotCenterX);
        retList.add(dotCenterY);
        return retList;
    }
    ArrayList<Integer> possibleCenters(int x1, int y1){
        return new ArrayList<>();
    }
    void charFind(int x1, int y1) throws IOException {

        BufferedImage image3= new BufferedImage(image.getWidth(),image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        image3.getGraphics().drawImage(image, 0, 0, null);
        // left
        int cx1=x1+dx/2;
        int cy1=y1+dy;
        // right
        int cx2=x1-dx/2;
        int cy2=y1+dy;

        int cnt1=0;
        int cnt2=0;
        int realCenterX=cx1;
        int realCenterY=cy1;
        ArrayList<Integer> returnedList=new ArrayList<>(countValidity(cx1,cy1));
        cnt1=returnedList.get(0);
//        realCenterX=returnedList.get(1);
//        realCenterY=returnedList.get(2);
        returnedList=new ArrayList<>(countValidity(cx2,cy2));
        cnt2=returnedList.get(0);
        if(cnt1<cnt2){
//            realCenterX=returnedList.get(1);
//            realCenterY=returnedList.get(2);
            realCenterX=cx2;
            realCenterY=cy2;
        }
        currCenterX=realCenterX;
        currCenterY=realCenterY;
        readLine(realCenterX,realCenterY);
        System.out.println("ek line pora hoise");

    }
    void readLine(int cx,int cy) throws IOException {
        lineList=new ArrayList<>();
        int nx=cx;
        int ny=cy;
        int mul=0;
        ArrayList<Integer> returnedList;
        while(cx-mul*cd>=0){
            nx=cx-mul*cd;
            returnedList= new ArrayList<>(countValidity(nx,cy));
//            if(returnedList.get(0)!=0) {
//                cx = returnedList.get(1);
//                cy = returnedList.get(2);
//            }
            mul++;
        }
        mul=1;
        while(cx+mul*cd<image.getWidth()){
            nx=cx+mul*cd;
            returnedList= new ArrayList<>(countValidity(nx,cy));
//            if(returnedList.get(0)!=0) {
//                cx = returnedList.get(1);
//                cy = returnedList.get(2);
//            }
            mul++;
        }
        lineList.add("\n");
        return;
    }
    ArrayList<Integer> countValidity(int xCoordinate, int yCoordinate) throws IOException {
        int x1=xCoordinate-dx/2;
        int y1=yCoordinate-dy;
        int x2=xCoordinate-dx/2;
        int y2=yCoordinate;
        int x3=xCoordinate-dx/2;
        int y3=yCoordinate+dy;
        int x4=xCoordinate+dx/2;
        int y4=yCoordinate-dy;
        int x5=xCoordinate+dx/2;
        int y5=yCoordinate;
        int x6=xCoordinate+dx/2;
        int y6=yCoordinate+dy;
        BufferedImage image3= new BufferedImage(image.getWidth(),image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        image3.getGraphics().drawImage(image, 0, 0, null);
        int cnt1=0;
        ArrayList<List> dotCenters= new ArrayList<>();
        int dCX;
        int dCY;
        ArrayList<Integer> centers;
        centers=searchArea(x1,y1,image3);
        String s=new String();
        int currX=0;
        int currY=0;
        if(centers!=null){
            currX+=(centers.get(0)+dx/2);
            currY+=(centers.get(1)+dy);
            cnt1++;
            s.concat("1");
        }else{
            s.concat("0");
        }
        centers=searchArea(x2,y2,image3);
        if(centers!=null){
            currX+=(centers.get(0)+dx/2);
            currY+=(centers.get(1));
            cnt1++;
            s.concat("1");
        }else{
            s.concat("0");
        }
        centers=searchArea(x3,y3,image3);
        if(centers!=null){
            currX+=(centers.get(0)+dx/2);
            currY+=(centers.get(1)-dy);
            cnt1++;
            s.concat("1");
        }else{
            s.concat("0");
        }
        centers=searchArea(x4,y4,image3);
        if(centers!=null){
            currX+=(centers.get(0)-dx/2);
            currY+=(centers.get(1)+dy);
            cnt1++;
        }else{
            s.concat("0");
        }
        centers=searchArea(x5,y5,image3);
        if(centers!=null){
            currX+=(centers.get(0)-dx/2);
            currY+=(centers.get(1));
            cnt1++;
            s.concat("1");
        }else{
            s.concat("0");
        }
        centers=searchArea(x6,y6,image3);
        if(centers!=null){
            currX+=(centers.get(0)-dx/2);
            currY+=(centers.get(1)-dy);
            cnt1++;
            s.concat("1");
        }else{
            s.concat("0");
        }
        if(cnt1!=0){
            currX/=cnt1;
            currY/=cnt1;
        }
        if(currX !=0 && currY!=0) {
            image3.setRGB(currX, currY, 0xFF00FF00);
            image3.setRGB(currX + 1, currY, 0xFF00FF00);
            image3.setRGB(currX - 1, currY, 0xFF00FF00);
            image3.setRGB(currX + 2, currY, 0xFF00FF00);
            image3.setRGB(currX - 2, currY, 0xFF00FF00);
            image3.setRGB(currX, currY - 1, 0xFF00FF00);
            image3.setRGB(currX, currY + 1, 0xFF00FF00);
            image3.setRGB(currX, currY - 2, 0xFF00FF00);
            image3.setRGB(currX, currY + 2, 0xFF00FF00);
            image3.setRGB(currX, currY + 3, 0xFF00FF00);
            image3.setRGB(currX, currY - 3, 0xFF00FF00);
            image3.setRGB(currX+3, currY , 0xFF00FF00);
            image3.setRGB(currX-3, currY , 0xFF00FF00);
            image3.setRGB(currX, currY - 4, 0xFF00FF00);
            image3.setRGB(currX, currY + 4, 0xFF00FF00);
            image3.setRGB(currX+4, currY , 0xFF00FF00);
            image3.setRGB(currX-4, currY , 0xFF00FF00);
        }

        String nme="outputImages/dotFinding";
        nme+=String.valueOf(no);
        no++;
        nme+=".png";
//        ImageIO.write(image3 , "png", new File(nme));
        ArrayList<Integer> retList= new ArrayList<>();
        retList.add(cnt1);
        retList.add(currX);
        retList.add(currY);
        lineList.add(s);
        return retList;
    }

    ArrayList<Integer> searchArea(int x, int y, BufferedImage image3) throws IOException {
        BufferedImage image4= new BufferedImage(image.getWidth(),image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        image4.getGraphics().drawImage(image, 0, 0, null);
        for(int i=-9;i<=9;i++){
            for(int j=-9;j<=9;j++){

                int x1=x+i;
                int y1=y+j;
                if(x1<0 || y1<0 || x1>=image.getWidth() || y1>=image.getHeight())continue;
                image4.setRGB(x1,y1,0xFF00FF00);

                if(image3.getRGB(x1,y1)<0xFFAAAAAA){
                    ArrayList<List> aList=new ArrayList<>(floodFill(x1,y1,image3));
                    if(aList.size()>=25){

                        ArrayList<Integer> centers=new ArrayList<>(findDotCenter(aList));
                        return centers;

                    }
                }
            }
        }
        String nme="prob";
        nme+=String.valueOf(no);
        no++;
        nme+=".png";
//        ImageIO.write(image4 , "png", new File(nme));
        return null;
    }


}
