package pl.softr.ocr.utils;

import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;

import pl.softr.ocr.invoices.invoice.AddInvoicePositionsItemAdapter;

public class EditTextValidator {

    private final List<TextView> invalidFields;
    private final String emptyFieldError = "Pole wymagane";
    private boolean isFormValid = true;
    private boolean isListEmpty;


    public EditTextValidator() {
        this.invalidFields = new LinkedList<>();
    }

    public boolean isNotEmpty(TextView textView) {
        if (textView.getText().toString().isEmpty()) {
            textView.setError(emptyFieldError);
            invalidFields.add(textView);
            isFormValid = false;
            return false;
        }
        return true;
    }

    public boolean validateList(RecyclerView rvPositionsList) {
        int positionsNumber = rvPositionsList.getChildCount();
        if (positionsNumber == 0) {
            isListEmpty = true;
            isFormValid = false;
            return false;
        }
        for (int i = 0; i < positionsNumber; i++) {
            AddInvoicePositionsItemAdapter.ViewHolder holder = (AddInvoicePositionsItemAdapter.ViewHolder) rvPositionsList.findViewHolderForAdapterPosition(i);
            if (holder != null) {
                String textValue = holder.getPositionName().getText().toString();
                holder.getPositionName().setError(textValue.isEmpty() ? emptyFieldError : null);
                isFormValid = false;
                invalidFields.add(holder.getPositionName());
            }
        }
        return isFormValid;
    }

    public boolean isFormValid() {
        return isFormValid;
    }

    public boolean isListEmpty() {
        return isListEmpty;
    }

    public List<TextView> getInvalidFields() {
        return invalidFields;
    }
}
