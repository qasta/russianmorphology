package org.apache.lucene.russian.morphology;

/**
 * This helper class allow encode suffix of russian word
 * to long value and decode from it.
 * Assumed that suffix contains only small russian letters and dash.
 * Also assumed that letter � and � coinsed.
 */
public class RussianSuffixDecoderEncoder {
    public static final int RUSSIAN_SMALL_LETTER_OFFSET = 1071;
    public static final int SUFFIX_LENGTH = 7;
    private static final int EE_CHAR = 34;
    private static final int E_CHAR = 6;
    private static final int DASH_CHAR = 45;
    private static final int DASH_CODE = 33;


    static public Long encode(String string) {
        if (string.length() > 12) throw new SuffixToLongException("Suffix length should not be greater then " + 12);
        long result = 0L;
        for (int i = 0; i < string.length(); i++) {
            int c = 0 + string.charAt(i) - RUSSIAN_SMALL_LETTER_OFFSET;
            if (c == 45 - RUSSIAN_SMALL_LETTER_OFFSET) {
                c = DASH_CODE;
            }
            if (c == EE_CHAR) c = E_CHAR;
            if (c < 0 || c > 33) throw new WrongCharaterException(); 
            result = result * 35L + c;
        }
        return result;
    }

    static public String decode(Long suffixN) {
        String result = "";
        while (suffixN > 35) {
            long c = suffixN % 35 + RUSSIAN_SMALL_LETTER_OFFSET;
            if (c == DASH_CODE + RUSSIAN_SMALL_LETTER_OFFSET) c = DASH_CHAR;
            result = (char) c + result;
            suffixN /= 35;
        }
        long c = suffixN + RUSSIAN_SMALL_LETTER_OFFSET;
        if (c == DASH_CODE + RUSSIAN_SMALL_LETTER_OFFSET) c = DASH_CHAR;
        result = (char) c + result;
        return result;
    }
}