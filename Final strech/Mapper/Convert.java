package Mapper;
import java.util.ArrayList;
import java.util.List;

import java.util.Vector;

import static java.util.Map.entry;


public class Convert {
    Vector<String> rooms = new Vector<>() {{
        add("000100");
        add("100110");
        add("110000");
        add("010100");
        add("011110");
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

    private boolean notSymbol = false, SymbolBefore = false;
    private boolean firstletter = true, twoletterConjunct = false,  three_four_letterConjunct = false;
    private boolean numberpref = false, twelveletterPrefix = false;
    private boolean thirdBracket = false, firstBracket = false;
    private List<String> jointLetters = new ArrayList<>();
    private String twelveletterFirstPart = "";
    private Mapper.BanglaDictionary BD = new Mapper.BanglaDictionary();

    public void translate(List<String> binfile){
        List<String> word = new ArrayList<String>();
        List<String> bangla_sentence = new ArrayList<String>();

        for (String x : binfile) {
//             System.out.println(x.length());
            if(x.equals("\n")){
                bangla_sentence.add(convert(word));
                word = new ArrayList<>();
                bangla_sentence.add("\n");
            }

            else if(x.equals("space")){                         //take one word before space and translate
                bangla_sentence.add(convert(word));
                word = new ArrayList<>();
//                for (String xy: word){
//                    System.out.print(xy + " : ");
//                }
//                System.out.println(convert(word));
                numberpref = false;
            }
            else word.add(x);
        }

        bangla_sentence.add(convert(word));

        String line = String.join(" ", bangla_sentence);
        System.out.println(line);
    }

    public String convert(List<String> word){
        String bangla = "";
        firstletter = true;

        for (String x : word) {
            bangla += map_value(x);
            if(firstletter == true) firstletter = false;
            System.out.println(x + " : " + bangla);
        }

        return bangla;
    }

    private String map_value(String braille){
        String bangla = "";

        if(numberpref) {
            bangla = handleNumbers(braille);
        }
        else if(twelveletterPrefix){
            bangla = handleTwelveDots(braille);
        }
        else if(BD.getTwelveDotPrefix().containsKey(braille)){
            bangla = handleTwelveDotPrefix(braille);
        }
        else if(braille.equals("000100")){ // 2 joint letter
            System.out.println( "two joint ");
            twoletterConjunct = true;
        }
        else if(braille.equals("000101") ){ // 3 4 joint letter
            three_four_letterConjunct = true;
        }
        else if(braille.equals(BD.getNumberPrefix())){
            bangla = handleNumberPrefix();
        }
        else if(BD.vowel.containsKey(braille)){
            bangla =  calc_vowel(braille);
        }
        else if(BD.consonant.containsKey(braille)){
            bangla =  calc_consonant(braille);
        }
        else if(BD.getPunctation().containsKey(braille)){
            bangla = BD.getPunctation().get(braille);
        }
        else if(BD.getOperator().containsKey(braille)){
            bangla = BD.getOperator().get(braille);
        }

        if(!BD.vowel.containsKey(braille)){
            SymbolBefore = false;
        }
        return bangla;
    }

    private String handleTwelveDots(String braille) {
        String bangla = "";
        twelveletterFirstPart+=braille;
        bangla = BD.getTwelveDots().get(twelveletterFirstPart);

        if(bangla.equals("[")) thirdBracket = true;
        return  bangla;
    }

    private String handleTwelveDotPrefix(String braille) {
        String bangla = "";
        if(braille.equals("000001") || (braille.equals("000011") || braille.equals("001011"))){
            if(firstletter){
                twelveletterFirstPart = braille;
                twelveletterPrefix = true;
                return bangla;
            }
            else{
                return BD.getTwelveDotPrefix().get(braille);
            }
        }
        else if(braille.equals("001010")){
            return calc_vowel(braille);
        }
        else if(braille.equals("011011")){
            if(thirdBracket){
                twelveletterFirstPart = braille;
                twelveletterPrefix = true;
                thirdBracket = false;
                return bangla;
            }
            else{
                return firstBracketEquals(braille);
            }

        }
        return bangla;
    }

    private String firstBracketEquals(String braille) {
        if(firstBracket){
            return ")";
        }
        else{
            firstBracket = true;
            return "(";
        }
    }

    private String handleNumbers(String braille) {

        if(BD.getNumbers().containsKey(braille)){
            return BD.getNumbers().get(braille);
        }
        else if(BD.getMath_operator().containsKey(braille)){
            return BD.getMath_operator().get(braille);
        }
        return "";
    }

    private String handleNumberPrefix() {

        if(firstletter){

            numberpref = true;
            return "";
        }
        else return BD.consonant.get(BD.getNumberPrefix());
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
        if(firstletter) return BD.vowel.get(braille_vowel);
        if(BD.special_vowel.containsKey(braille_vowel) && notSymbol){  //if special vowel and if no অ before
            notSymbol = false;
            return BD.vowel.get(braille_vowel);
        }
        else if(SymbolBefore){
            return BD.vowel.get(braille_vowel);
        }

        else if(braille_vowel == "100000"){  //if found অ
            notSymbol = true;
            return "";
        }
        SymbolBefore = true;
        return BD.symbols.get(BD.vowel.get(braille_vowel));
    }
}