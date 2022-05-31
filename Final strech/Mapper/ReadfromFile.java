package Mapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;


public class ReadfromFile {

    public List<String> read() throws IOException {
        List<String> listOfStrings  = new ArrayList<String>();

        BufferedReader bf = new BufferedReader(new java.io.FileReader("data_02.txt"));


        // read entire line as string
        String line = bf.readLine();

        // checking for end of file
        while (line != null) {
            listOfStrings.add(line);
            line = bf.readLine();
        }

        bf.close();

        // storing the data in arraylist to array
        String[] array  = listOfStrings.toArray(new String[0]);
        List<String> binfile = new ArrayList<>();

        for (String str : array) {
            String[] rooms = str.split(" ");
            for (String s : rooms) {
                // System.out.println(s);
                binfile.add(s);
                // System.out.println("hey");
            }
            binfile.add("\n");
//            System.out.println("Nahid");
        }
        return binfile;
    }
    public ReadfromFile() throws IOException, IOException {
    }
}