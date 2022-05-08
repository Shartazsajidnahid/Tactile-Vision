package Mapper;

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
            entry("100011", "জ্ঞ"), entry("110111", "ড়"), entry("111011", "ঢ়"), entry("010001", "য়"), entry("010000011110", "ৎ"),
            entry("000100", "্‌"), entry("000011", "ং"), entry("000001", "ঃ"), entry("001000", "ঁ")
    );
    Map<String, String> symbols = Map.ofEntries(
            entry("অ", ""), entry("আ", "া"), entry("ই", "ি"), entry("ঈ", "ী"),
            entry("উ", "ু"), entry("ঊ", "ূ"), entry("এ", "ে"), entry("ঐ", "ৈ"),
            entry("ও", "ো"), entry("ঔ", "ৌ"), entry("ঋ", "ৃ")

    );

    Map<String, String> vowel = Map.ofEntries(
            entry("100000", "অ"), entry("001110", "আ"), entry("001010", "ঈ"), entry("110011", "ঊ"),
            entry("000010111010", "ঋ"), entry("100010", "এ"), entry("001100", "ঐ"), entry("010101", "ঔ")
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


}
