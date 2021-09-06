package pl.softr.ocr.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DataPickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    private Calendar date;
    private OnDateSelectedListener dateSelectedListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date.set(year,month,dayOfMonth);
        dateSelectedListener.onDateSelect(date);
    }

    public void setDateSelectedListener(OnDateSelectedListener dateSelectedListener) {
        this.dateSelectedListener = dateSelectedListener;
    }
}
