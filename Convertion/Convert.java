package Mapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import static java.util.Map.entry;


public class Convert {
    Vector<String> rooms = new Vector<>() {{
        add("101000");
        add("011111");
        add("space");
        add("100101");
        add("111000");
        add("001110");
        add("101100");
    }};
    BanglaDictionary BD = new BanglaDictionary();

    public void translate(){
        List<String> word = new ArrayList<String>();
        List<String> bangla_sentence = new ArrayList<String>();

        for (String x : rooms) {
            if(x=="space"){                         //take one word before space and translate
                bangla_sentence.add(convert(word));
                word = new ArrayList<>();
                continue;
            }
            word.add(x);
        }
        bangla_sentence.add(convert(word));
        for (String x : bangla_sentence) {
            System.out.println(x);
        }
    }

    public String convert(List<String> word){
        String bangla = "";

        for (String x : word) {
            bangla += map_value(x);
        }
//        System.out.println(bangla);
//        System.out.println("প"+"ই");
        return bangla;
    }

    public String map_value(String braille){
        String bangla = "";
        if(BD.vowel.containsKey(braille)){
            return BD.symbols.get(BD.vowel.get(braille));
        }
        bangla = BD.consonant.get(braille);

        return bangla;
    }
}
