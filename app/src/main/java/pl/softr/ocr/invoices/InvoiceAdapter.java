package pl.softr.ocr.invoices;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.mlkit.vision.text.Text;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import pl.softr.ocr.R;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder> {

    private List<Text.TextBlock> dataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public InvoiceAdapter(List<Text.TextBlock> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.getTextView().setText(dataSet.get(position).getText());
    }

    @Override
    public int getItemCount() {
        if (dataSet == null) {
            return 0;
        }
        return dataSet.size();
    }
    public List<Text.TextBlock> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<Text.TextBlock> dataSet) {
        this.dataSet = dataSet;
    }

}
