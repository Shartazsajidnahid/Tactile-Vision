package Mapper;
import java.util.ArrayList;
import java.util.List;

import java.util.Vector;

import static java.util.Map.entry;


public class Convert {

    private boolean notSymbol = false, SymbolBefore = false;
    private boolean thirdBracket = false, firstBracket = false;

    private Mapper.BanglaDictionary BD = new Mapper.BanglaDictionary();
    private int index = 0;
    private List<String> word = new ArrayList<String>();

    public void translate(List<String> binfile){
        List<String> bangla_sentence = new ArrayList<String>();

        for (String x : binfile) {
//             System.out.println(x.length());
            if(x.equals("\n")){
                bangla_sentence.add(convert());
                word = new ArrayList<>();
                bangla_sentence.add("\n");
            }
            else if(x.equals("space")){                         //take one word before space and translate
                bangla_sentence.add(convert());
                word = new ArrayList<>();
            }
            else word.add(x);
        }
        bangla_sentence.add(convert());
        String line = String.join(" ", bangla_sentence);
        System.out.println(line);
    }

    public String convert(){
        String bangla = "";
        for (index=0; index<word.size(); index++) {
            bangla += map_value(word.get(index));
            if(index<word.size())System.out.println(word.get(index) + " : " + bangla);
        }
        return bangla;
    }

    private String map_value(String braille) {
        String bangla = "";

        if (braille.equals(BD.getEnglishPrefix()) && (index == 0)) {
            return handleEnglish();
        }
//        else if(BD.getTwelveDotPrefix().containsKey(braille)){
//            bangla = handleTwelveDotPrefix(braille);
//        }
        else if (braille.equals("000100") ) { // 2 joint letter
            return twoJointLetter();
        }
         else if(braille.equals("000101") ){ // 3 4 joint letter
            return threeJointLetter();
        }
         else if(braille.equals(BD.getNumberPrefix()) && (index == 0)){
             return handleBanglaNumbers(braille);
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

    private String twoJointLetter() {
        String bangla = "";
        if (index + 2 < word.size()) {
            bangla+= BD.getConsonant().get(word.get(index+1)) + "্" + BD.getConsonant().get(word.get(index+2));
            index+=2;
        }
        else{
            index++;
            while(index<word.size()){
                bangla+= BD.getConsonant().get(word.get(index));
            }
        }
        return bangla;
    }

    private String threeJointLetter(){
        String bangla = "";
        if (index + 3 < word.size()) {
            bangla+= BD.getConsonant().get(word.get(index+1)) + "্" + BD.getConsonant().get(word.get(index+2))+ "্" + BD.getConsonant().get(word.get(index+3));
            index+=3;
        }
        else{
            index++;
            while(index<word.size()){
                bangla+= BD.getConsonant().get(word.get(index));
            }
        }
        return bangla;
    }

    private String handleEnglish() {
        String bangla = "";
        for (; index<word.size()-1; index++) {
            bangla += BD.getEnglish_alphabet().get(word.get(index+1));
        }
        return bangla;
    }

    private String handleBanglaNumbers(String braille) {
        String bangla = "";
        for (; index<word.size(); index++) {
            if(BD.getNumbers().containsKey(word.get(index))){
                bangla += BD.getNumbers().get(word.get(index));
            }
        }
//        else if(BD.getMath_operator().containsKey(braille)){
//            return BD.getMath_operator().get(braille);
//        }
        return bangla;
    }

    private String calc_vowel(String braille_vowel) {
        String bangla = "";
        if ((index == 0) ){
            bangla = BD.vowel.get(braille_vowel);
        }
        else if (braille_vowel.equals("100000")){
            if(BD.getVowel().containsKey(word.get(index+1))){
                bangla =  BD.getVowel().get(word.get(index+1));
                index++;
            }else{
                bangla = BD.getVowel().get(braille_vowel);
            }
        }
        else if(!braille_vowel.equals("100000") ){
            if((index+1 < word.size()) && BD.getVowel().containsKey(word.get(index+1))){
                bangla = BD.symbols.get(BD.vowel.get(braille_vowel)) +  BD.getVowel().get(word.get(index+1));
                index++;
            }else{
                bangla =  BD.symbols.get(BD.vowel.get(braille_vowel));
            }
        }
        else{
            bangla =  BD.symbols.get(BD.vowel.get(braille_vowel));
        }
        return bangla;
    }



    private String calc_consonant(String braille_cons){
        String bangla = "";
        if(BD.getConsonant().containsKey(braille_cons)){
            return BD.consonant.get(braille_cons);
        }
        return bangla;
    }


//    private String handleTwelveDots(String braille) {
//        String bangla = "";
//        System.out.println("hey i am 123" + twelveletterFirstPart);
//        twelveletterFirstPart+=braille;
//        bangla = BD.getTwelveDots().get(twelveletterFirstPart);
//
//        //if( bangla.equals("[") ) thirdBracket = true;
//        return  bangla;
//    }
//
//    private String handleTwelveDotPrefix(String braille) {
//        String bangla = "";
//        if(braille.equals("010000")){
//            twelveletterFirstPart = braille;
//            twelveletterPrefix = true;
//            return bangla;
//        }
//        else if(braille.equals("000001") || braille.equals("000011") || braille.equals("001011")){
//            if(firstletter){
//                twelveletterFirstPart = braille;
//                twelveletterPrefix = true;
//                return bangla;
//            }
//            else{
//                return BD.getTwelveDotPrefix().get(braille);
//            }
//        }
//        else if(braille.equals("001010")){
//            return calc_vowel(braille);
//        }
//        else if(braille.equals("011011")){
//            if(thirdBracket){
//                twelveletterFirstPart = braille;
//                twelveletterPrefix = true;
//                thirdBracket = false;
//                return bangla;
//            }
//            else{
//                return firstBracketEquals(braille);
//            }
//
//        }
//        System.out.println("hey 12prefix" + twelveletterFirstPart);
//        return bangla;
//    }
//
//    private String firstBracketEquals(String braille) {
//        if(firstBracket){
//            return ")";
//        }
//        else{
//            firstBracket = true;
//            return "(";
//        }
//    }


}
