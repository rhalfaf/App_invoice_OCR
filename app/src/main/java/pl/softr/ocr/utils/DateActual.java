package pl.softr.ocr.utils;

import java.util.Calendar;
import java.util.Locale;

public class DateActual {

    private Calendar c;

    public DateActual(Calendar c) {
        this.c = c;
    }

    public static String getDateFormatted(Calendar c){
        return String.format(Locale.US,"%d/%d/%d", c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH));
    }
}
