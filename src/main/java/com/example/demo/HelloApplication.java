package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        PDDocument document = new PDDocument();
        PDPage p1= new PDPage();
        PDPageContentStream contentStream = new PDPageContentStream(document, p1);
        contentStream.beginText();
        contentStream.setLeading(14.5f);
        contentStream.setFont(getFont(document), 12);
        contentStream.newLineAtOffset(25, 700);

        String text = "পূর্ব দিকে গেলে সময় ১০\n" +
       " বারো ঘন্টা বাড়ে আর পশ্চিম দিকে\n" +


        "গেলে সময় বারো ঘন্টা কমে\n" +


        "অর্থাৎ একই দ্রাঘীমারেখায়\n" +


       " ভঅহজ ডিগ্রিতে সময়ের ব্যবধান\n"+


        "দেখা যায় ২৪ ঘন্টা| এর জন্য\n"+


        "তারিখ ও বারের যে সমস্যা হয়\n" +


        "তা সমাধান কল্পে ১৮৮৪ সালে\n" +


        "আমেরিকা যুক্তরাষ্্‌টরের ওয়াশিং-\n" +


               " ঢ়নে দ্রাঘীমা ও পময় সম্পর্কিত\n" +


        "এক আন্তর্জাতিক সম্মেলনে\n"+


  "১৮০ ডিগ্রি দ্রাঘীমারেখা\n" +


        "আন্তর্জাতিক তারিখরেখা হিসেবে\n" +


       " স্থির করা হয়| পৃথিবীর মান-";

        String[] lines = text.split("\r?\n|\r");

        ArrayList<String> list = new ArrayList<>();
        list.addAll(List.of(lines));



        //Adding text in the form of string
//        contentStream.showText(list.get(0));
//        contentStream.newLine();
//        contentStream.showText(list.get(3));
//        contentStream.newLine();
        for(int i=0;i<list.size();i++){
            if(i==3) continue;
            contentStream.showText(list.get(i));
            contentStream.newLine();
        }
//        contentStream.showText(text1);
        //Ending the content stream
        contentStream.endText();

        System.out.println("Content added");

        //Closing the content stream
        contentStream.close();

        document.addPage(p1);
        //Saving the document
        document.save("my_doc1.pdf");

        System.out.println("PDF created");

        //Closing the document
        document.close();
        launch();
    }
    private static PDType0Font getFont(PDDocument pdDocument) throws IOException {
        //times-new-roman
        //sagarnormal
        PDType0Font font = PDType0Font.load(pdDocument, new File("Fonts\\sagarnormal.ttf"));
        return font;
    }



}