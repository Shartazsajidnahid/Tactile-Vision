package com.example.demo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TextToImage {
    public void testTOimage() throws IOException {
        String text="পূর্ব দিকে গেলে সময় ১০ \n" +
                "বারো ঘন্টা বাড়ে আর পশ্চিম দিকে \n" +
                "গেলে সময় বারো ঘন্টা কমে \n" +
                "অর্থাৎ একই দ্রাঘীমারেখায় \n" +
                "১৮০ ডিগ্ওিতে সময়ের ব্যবধান \n" +
                "দেখা যায় ২৪ ঘন্টা। এর জন্য \n" +
                "তারিখ ও বারের যে সমস্যা হয় \n" +
                "তা সমাধান কল্পে ১৮৮৪ সালে \n" +
                "আমেরিকা যুক্তরাষ্্\u200Cটরের ওয়াশিং- \n" +
                "টনে দ্রাঘীমা ও পময় সম্পর্কিত \n" +
                "এক আন্তর্জাতিক সম্মেলনে \n" +
                "১৮০ ডিগ্রি দ্রাঘীমারেখা \n" +
                "আন্তর্জাতিক তারিখরেখা হিসেবে \n" +
                "স্থির করা হয়। পৃথিবীর মান- \n" +
                "চিত্রে প্রশান্ত মহাসাগরের \n" +
                "উপর দিয়ে ১৮০ ডিগ্রি দ্রাছীমা \n" +
                "অনুসরণ করে আন্তর্জাতিক \n" +
                "তারিখরেখা প্রবর্তন করা হয়েছে। \n" +
                "পৈশচিমগামি জাহাজ আন্তর্জাতিক \n" +
                "তারিখরেখা অতিক্রম করলে এক দিন \n" +
                "যোগ হয় আর পূর্বগামি জাহাজ \n" +
                "আন্তর্জাতিক তারিখরেখা অতিক্রম \n" +
                "করলে এক দিন বিয়োগ করতে হয়। \n" +
                "প্রতিপাদ স্থান: পৃথিবী গোল \n" +
                "হওয়ায় এর কোনো একঢি স্থানে \n";
        File f1= new File("text.png");
        BufferedImage image= new BufferedImage(1000,1000, BufferedImage.TYPE_3BYTE_BGR);
        image.getGraphics().setColor(Color.WHITE);
        Graphics2D yo= (Graphics2D) image.getGraphics();

        yo.drawImage(image,1,2, null);
        AlphaComposite alpha= AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1);
        yo.setComposite(alpha);
        yo.setColor(Color.WHITE);
        yo.setBackground(Color.WHITE);
        System.out.println("sadss");
        yo.setFont(new Font(Font.SANS_SERIF,Font.BOLD,32));
        FontMetrics fMetrics= yo.getFontMetrics();
        Rectangle2D rect= fMetrics.getStringBounds(text,yo);
        int centerX=(image.getWidth()-(int)rect.getWidth())/2;
        int centerY= image.getHeight()/2;
        yo.drawString(text,centerX,centerY);
        ImageIO.write(image,"png",f1);
        System.out.println("sadss");


    }
}
