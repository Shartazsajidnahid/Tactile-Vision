package DotIdentifier;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class BufferedImageAndMat {
    public BufferedImage mat2Img(Mat in)
    {
        BufferedImage out;
        byte[] data = new byte[in.cols()* in.rows() * (int)in.elemSize()];
        int type;
        in.get(0, 0, data);

        if(in.channels() == 1)
            type = BufferedImage.TYPE_BYTE_GRAY;
        else
            type = BufferedImage.TYPE_3BYTE_BGR;

        out = new BufferedImage(in.cols(), in.rows(), type);

        out.getRaster().setDataElements(0, 0, in.cols(), in.rows(), data);
        return out;
    }
    public Mat img2Mat(BufferedImage image){
        byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        Mat ret= new Mat(image.getHeight(),image.getWidth(), CvType.CV_8UC3);
        ret.put(0,0,pixels);
        return ret;
    }
}
