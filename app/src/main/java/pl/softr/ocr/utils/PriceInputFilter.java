package pl.softr.ocr.utils;

import android.text.InputFilter;
import android.text.Spanned;

public class PriceInputFilter implements InputFilter {

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if(countDecAfterDecimal(dest)>=2){
            return "";
        }
        return null;
    }

    private int countDecAfterDecimal(CharSequence c){
        int counter = -1;
        boolean isDot = false;
        for (int i = 0; i < c.length(); i++) {
            if(c.charAt(i) == '.'){
                isDot = true;
            }
            if(isDot){
                counter++;
            }
        }
        return counter;
    }
}
