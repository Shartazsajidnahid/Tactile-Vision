package Mapper;
import java.util.ArrayList;
import java.util.List;

import java.util.Vector;

import static java.util.Map.entry;


public class Convert {

    private boolean thirdBracket = false, firstBracket = false;
    private boolean curlyApostrophy = false;

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
        else if(BD.getTwelveDotPrefix().containsKey(braille)){
            bangla = handleTwelveDotPrefix(braille);
        }
        else{
            bangla = checkpost2(braille);
        }


        return bangla;
    }

    private String checkpost2(String braille){
         if (braille.equals("000100") ) { // 2 joint letter
            return twoJointLetter();
        }
        else if(braille.equals("000101") ){ // 3 4 joint letter
            return threeJointLetter();
        }
        else if(braille.equals(BD.getNumberPrefix()) && (index == 0)){
            return handleBanglaNumbers(braille);
        }
        else if(BD.vowel.containsKey(braille)){
            return calc_vowel(braille);
        }
        else if(BD.consonant.containsKey(braille)){
            return calc_consonant(braille);
        }
        else if(BD.getPunctation().containsKey(braille)){
            return BD.getPunctation().get(braille);
        }
        else if(BD.getOperator().containsKey(braille)){
            return BD.getOperator().get(braille);
        }
        return "";
    }

    private String handleTwelveDotPrefix(String braille) {
        String bangla = checkpost2(braille);
        String first6 = braille, second6="";

        if(index+1<word.size()){ //check if the prefix is last letter
            second6 = word.get(index+1);
        }
        else {
            return checkpost2(braille);
        }

        System.out.println("hi 12 : " + first6 + " " + second6);
        if(!BD.getTwelveDots().containsKey(first6+second6)){ // check if total 12 dots have a meaningful map or not
            return bangla;
        }

        if(first6.equals("000001")){
            if(index==word.size()-1){
                bangla = BD.getConsonant().get(first6);
            }
            else{
                if(second6.equals("011001")){
                    curlyApostrophy = true;
                }
                else if(second6.equals("011011")){
                    thirdBracket = true;
                }
                index++;
                return BD.getTwelveDots().get(first6+second6);
            }
        }
        else if(first6.equals("001011")){
            if(curlyApostrophy){
                curlyApostrophy = false;
                index++;
                return BD.getTwelveDots().get(first6+second6);
            }
            else{
                return BD.getPunctation().get(first6);
            }
        }
        else if(first6.equals("000011")){
            if(index==0){
                index++;
                return BD.getTwelveDots().get(first6+second6);
            }
            else{
                return BD.getConsonant().get(first6);
            }
        }
        else if(first6.equals("001010")){
            if(word.size()==1){
                index++;
                return BD.getTwelveDots().get(first6+second6);
            }
            else{
                return calc_vowel(first6);
            }
        }
        else if(first6.equals("011011")){
            if(thirdBracket){
                thirdBracket = false;
                index++;
                return BD.getTwelveDots().get(first6+second6);
            }
            else{
                if(firstBracket){
                    firstBracket = false;
                    return ")";
                }
                else return "(";

            }
        }
        else if(first6.equals("000010")){
            if(word.size()==1){
                return BD.getPunctation().get(first6);
            }
            else{
                index++;
                System.out.println("wow" + first6+second6);
                return BD.getTwelveDots().get(first6+second6);
            }
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
            bangla =  BD.getConsonant().get(braille_cons);
        }
        if(braille_cons.equals("010010") && (index==word.size()-1)){  //if ঞ found at last
            bangla = BD.getPunctation().get(braille_cons);
        }
        return bangla;
    }


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