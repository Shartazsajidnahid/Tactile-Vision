package Mapper;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class BanglaDictionary {

    Map<String, String> consonant = Map.ofEntries(
            entry("101000", "ক"), entry("101101", "খ"), entry("110110", "গ"), entry("110001", "ঘ"),
            entry("001101", "ঙ"),entry("100100", "চ"), entry("100001", "ছ"), entry("010110", "জ"),
            entry("101011", "ঝ"), entry("010010", "ঞ"),
            entry("011111", "ট"), entry("010111", "ঠ"), entry("110101", "ড"), entry("111111", "ঢ"), entry("001111", "ণ"),
            entry("011110", "ত"), entry("100111", "থ"), entry("100110", "দ"), entry("011101", "ধ"), entry("101110", "ন"),
            entry("111100", "প"), entry("110100", "ফ"), entry("110000", "ব"), entry("111001", "ভ"), entry("101100", "ম"),
            entry("101111", "য"), entry("111010", "র"), entry("111000", "ল"),
            entry("100101", "শ"), entry("011100", "স"), entry("111101", "ষ"), entry("110010", "হ"), entry("111110", "ক্ষ"),
            entry("100011", "জ্ঞ"), entry("110111", "ড়"), entry("111011", "ঢ়"), entry("010001", "য়"), entry("000010011110", "ৎ"),
            entry("000100", "্‌"), entry("000011", "ং"), entry("000001", "ঃ"), entry("001000", "ঁ")
    );
    Map<String, String> symbols = Map.ofEntries(
            entry("অ", ""), entry("আ", "া"), entry("ই", "ি"), entry("ঈ", "ী"),
            entry("উ", "ু"), entry("ঊ", "ূ"), entry("এ", "ে"), entry("ঐ", "ৈ"),
            entry("ও", "ো"), entry("ঔ", "ৌ"), entry("ঋ", "ৃ")
    );

    Map<String, String> vowel = Map.ofEntries(
            entry("100000", "অ"), entry("001110", "আ"), entry("001010", "ঈ"), entry("110011", "ঊ"),
            entry("000010111010", "ঋ"), entry("100010", "এ"), entry("001100", "ঐ"), entry("010101", "ঔ"),
            entry("010100", "ই"), entry("101001", "উ"),    entry("101010", "ও")
    );
    Map<String, String> numbers = Map.ofEntries(
            entry("010110", "০"), entry("100000", "১"), entry("110000", "২"), entry("100100", "৩"),
            entry("100110", "৪"), entry("100010", "৫"), entry("110100", "৬"), entry("110110", "৭"),
            entry("110010", "৮"), entry("010100", "৯")
    );

    Map<String, String> fourletters = Map.ofEntries(
            entry("কষময", "ক্ষ্ম্য"), entry("নতরয", "ন্ত্র্য")
    );

    Map<String, String> special_vowel = Map.ofEntries(
            entry("010100", "ই"), entry("101001", "উ"),    entry("101010", "ও")
    );

    Map<String, String> punctation = Map.ofEntries(
            entry("001001", "-"), entry("010011", "|"), entry("000001011001", "‘"), entry("001011001000", "’"),
            entry("000001011011", "["), entry("011000", ";"), entry("011010", "!"), entry("000011011011", "="),
            entry("001010001010", "*"), entry("011011001000", "]"), entry("010010", ":"), entry("001011", "\""),
            entry("011001", "?"), entry("011011", "("),  entry("010000", ","), entry("001100", "/"), entry("000010", "$")
    );

    Map<String, String> Operator = Map.ofEntries(
            entry("001001", "-"), entry("000011011011", "="), entry("001010001010", "*"), entry("001110", ">"), entry("110001", "<"),
            entry("010000", "."), entry("001100", "/"), entry("011011", "("), entry("001101", "+"), entry("100101", "%"),
            entry("000110", "^"), entry("011011001000", "]"), entry("000001011011", "["), entry("101010011011", ">="),
            entry("010101011011", "<="), entry("001000", ",")
    );

    Map<String, String> math_operator = Map.ofEntries(
            entry("011010", "+"), entry("001001", "-"), entry("011001", "x"), entry("010011", "%"), entry("001000","."), //multiplication dot
            entry("011011", "=")
    );

    Map<String, String> hosonto = Map.ofEntries(
            entry("011001", "?"), entry("011011", ")"), entry("001011", "্")
    );

    Map<String, String> dot = Map.ofEntries(
            entry("010000", ".")
    );

    Map<String, String> twelveDots = Map.ofEntries(
            entry("000011011011", "="), entry("001010001010", "*"), entry("000001011011", "["), entry("000001011001", "‘"),
            entry("001011001000", "’"),
            entry("011011001000", "]"), entry("000010111010", "ঋ"), entry("000010011110", "ৎ")
    );

    Map<String, String> twelveDotPrefix = Map.ofEntries(
            entry("000001", "ঃ"), entry("001010", "ঈ"), entry("000011", "ং"),
            entry("011011", "("), entry("001011", "\""),
            entry("000010", "")
    );

    Map<String, String> double_mapping = Map.ofEntries(
            entry("011001", "?"), entry("001011", "\""), entry("011011", "("), entry("010000", ",")
    );

    Map<String, String> english_numbers = Map.ofEntries(
            entry("010110", "0"), entry("100000", "1"),
            entry("110000", "2"), entry("100100", "3"),
            entry("100110", "4"), entry("100010", "5"),
            entry("110100", "6"), entry("110110", "7"),
            entry("110010", "8"), entry("010100", "9")
    );

    Map<String, String> english_alphabet = Map.ofEntries(entry("100000", "a"), entry("110000", "b"), entry("100100", "c"), entry("100110", "d"), entry("100010", "e"),
            entry("110100", "f"), entry("110110", "g"), entry("110010", "h"), entry("010100", "i"), entry("010110", "j"),
            entry("101000", "k"), entry("111000", "l"), entry("101100", "m"), entry("101110", "n"), entry("101010", "o"),
            entry("101001", "u"), entry("111001", "v"), entry("010111", "w"), entry("101101", "x"), entry("101111", "y"),
            entry("111100", "p"), entry("111110", "q"), entry("111010", "r"), entry("011100", "s"), entry("011110", "t"),
            entry("101011", "z")
    );
    private String numberPrefix = "001111";
    private String englishPrefix = "000001";

    public Map<String, String> getEnglish_numbers() {
        return english_numbers;
    }

    public Map<String, String> getEnglish_alphabet() {
        return english_alphabet;
    }

    public String getEnglishPrefix() {
        return englishPrefix;
    }

    public String getNumberPrefix() {
        return numberPrefix;
    }

    public Map<String, String> getTwelveDotPrefix() {
        return twelveDotPrefix;
    }

    public Map<String, String> getConsonant() {
        return consonant;
    }

    public Map<String, String> getSymbols() {
        return symbols;
    }

    public Map<String, String> getVowel() {
        return vowel;
    }

    public Map<String, String> getNumbers() {
        return numbers;
    }

    public Map<String, String> getFourletters() {
        return fourletters;
    }

    public Map<String, String> getSpecial_vowel() {
        return special_vowel;
    }

    public Map<String, String> getPunctation() {
        return punctation;
    }

    public Map<String, String> getOperator() {
        return Operator;
    }

    public Map<String, String> getMath_operator() {
        return math_operator;
    }

    public Map<String, String> getHosonto() {
        return hosonto;
    }

    public Map<String, String> getDot() {
        return dot;
    }

    public Map<String, String> getTwelveDots() {
        return twelveDots;
    }

    public Map<String, String> getDouble_mapping() {
        return double_mapping;
    }

}