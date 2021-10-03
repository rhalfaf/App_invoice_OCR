package pl.softr.ocr.utils;

import android.text.InputFilter;
import android.text.Spanned;

public class PostalCodeInputFilter implements InputFilter {
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if (dstart < dend) return null;
        if (dstart == 6) return "";
        if (dest.length() == 2) {
            return "-";
        }
        return null;
    }
}
