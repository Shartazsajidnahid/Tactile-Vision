package Mapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import static java.util.Map.entry;


public class Convert {
    Vector<String> rooms = new Vector<>() {{
        add("100000");
        add("101000");
        add("011111");
        add("space");
        add("111010");
        add("001110");
        add("101100");
        add("100000");
        add("010100");
        add("111110");
        add("space");
        add("000100");
        add("110110");
        add("111010");
        add("001110");
        add("101100");
        add("space");
        add("000100");
        add("011100");
        add("110000");
        add("001110");
        add("011110");
        add("000101");
        add("101110");
        add("011110");
        add("111010");
        add("101111");
        add("space");
        add("000100");
        add("011100");
        add("110000");
        add("001110");
        add("011110");
        add("000101");

        add("101000");
        add("111101");
        add("101100");
        add("101111");
    }};

    private boolean notSymbol = false;
    private boolean firstletter = true;
    private boolean twoletterConjunct = false;
    private boolean three_four_letterConjunct = false;
    private List<String> jointLetters = new ArrayList<>();
    private BanglaDictionary BD = new BanglaDictionary();

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
        firstletter = true;
        for (String x : word) {
            bangla += map_value(x);
            if(firstletter == true) firstletter = false;
//            System.out.println("bangla: " + bangla);
        }

        return bangla;
    }

    private String map_value(String braille){
        String bangla = "";

        if(braille == "000100"){ // 2 joint letter
            twoletterConjunct = true;
        }
        else if(braille == "000101"){
            three_four_letterConjunct = true;
        }
        else if(BD.vowel.containsKey(braille)){
            return calc_vowel(braille);
        }
        else if(BD.consonant.containsKey(braille)){
            return calc_consonant(braille);
        }

        return bangla;
    }


    private String calc_consonant(String braille_cons){
        String bangla = "";
        if(twoletterConjunct) {
            return calc_two_joint(braille_cons);
        }
        if(three_four_letterConjunct){
            return calc_three_four_joint(braille_cons);
        }

        return BD.consonant.get(braille_cons);
    }

    private String calc_two_joint(String braille_cons) {
        String bangla = "";
        if(jointLetters.size() < 2) {
            jointLetters.add(BD.consonant.get(braille_cons));
        }

        if(jointLetters.size() == 2){
            bangla += jointLetters.get(0) + "্" + jointLetters.get(1);
            jointLetters = new ArrayList<>();
            twoletterConjunct = false;
        }
//        System.out.println("hey" + jointLetters.get(0) + " 2 "+ jointLetters.get(1));
        return bangla;
    }

    private String calc_three_four_joint(String braille_cons) {
        String bangla = "";
        if (jointLetters.size() < 4) {
            jointLetters.add(BD.consonant.get(braille_cons));
        }

        if (jointLetters.size() == 4) {
            bangla += jointLetters.get(0) + "্" + jointLetters.get(1) + "্" + jointLetters.get(2);
            String Fourjoint = jointLetters.get(0) + jointLetters.get(1) + jointLetters.get(2) + jointLetters.get(3);

            if (BD.fourletters.containsKey(Fourjoint)) {
                bangla += "্" ;
            }
            bangla += jointLetters.get(3);


            jointLetters = new ArrayList<>();
            three_four_letterConjunct = false;
        }
        return bangla;
    }

    private String calc_vowel(String braille_vowel){
        if(BD.special_vowel.containsKey(braille_vowel) && notSymbol){  //if special vowel and if no অ before
            notSymbol = false;
           return BD.vowel.get(braille_vowel);
        }

        else if(braille_vowel == "100000"){  //if found অ
            if(firstletter) return BD.vowel.get(braille_vowel);
            notSymbol = true;
            return "";
        }
        return BD.symbols.get(BD.vowel.get(braille_vowel));
    }
}
