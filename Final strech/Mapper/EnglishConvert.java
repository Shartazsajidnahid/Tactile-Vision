package Mapper;

import java.util.Map;

public class EnglishConvert {
    private Convert convertion;
    public EnglishConvert(Convert convertion){
        this.convertion = convertion;
    }

    public String handleEnglish(String braille, Map<String,String> English, Map<String,String> English_numbers) {
        String bangla = "";

        if(convertion.isNumberpref()) {
            if(English_numbers.containsKey(braille))
                bangla = English_numbers.get(braille);
        }
        else{
            if(English.containsKey(braille))
                bangla = English.get(braille);
        }
        return bangla;
    }

}
