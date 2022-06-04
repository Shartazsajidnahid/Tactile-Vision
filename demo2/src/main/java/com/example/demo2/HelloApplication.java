package com.example.demo2;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.itextpdf.text.Font.FontFamily.HELVETICA;
import static com.itextpdf.text.pdf.PdfName.TIMES_BOLD;
import static java.awt.Font.TRUETYPE_FONT;
import static java.awt.Font.createFont;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Document doc = new Document();
        try
        {

            BaseFont base = BaseFont.createFont("Siyam Rupali ANSI.ttf", BaseFont.WINANSI,BaseFont.EMBEDDED);
            com.itextpdf.text.Font font = new com.itextpdf.text.Font(base,12);
//generate a PDF at the specified location
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("Motivation.pdf"));
            System.out.println("PDF created.");
//opens the PDF
            doc.open();
            Paragraph p= new Paragraph();


            p.setFont(font);
            String s=convertFromUtf8ToIso("কিরেভাই") ;
            p.add(s);
//adds paragraph to the PDF file
            doc.add(p);
//close the PDF file
            doc.close();
//closes the writer
            writer.close();
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        launch();
    }
    private static String convertFromUtf8ToIso(String s1) {
        if(s1 == null) {
            return null;
        }
        String s = new String(s1.getBytes(StandardCharsets.UTF_8));
        byte[] b = s.getBytes(StandardCharsets.ISO_8859_1);
        return new String(b, StandardCharsets.ISO_8859_1);
    }
}