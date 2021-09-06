package pl.softr.ocr.desktop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pl.softr.ocr.R;
import pl.softr.ocr.database.entity.CompleteInvoice;

public class DesktopRVAdapter extends RecyclerView.Adapter<DesktopRVAdapter.ViewHolder> {

    private List<CompleteInvoice> dataSet = new ArrayList<>();

    public void setDataSet(List<CompleteInvoice> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_fragment_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getInvoiceSymbol().setText(dataSet.get(position).getGeneralInfo().getSymbol());
        holder.getBuyerName().setText(dataSet.get(position).getBuyer().getName());
        //TODO implement sum of all positions gross price
        holder.getGrossPrice().setText(String.valueOf(99.99));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView invoiceSymbol;
        private final TextView buyerName;
        private final TextView grossPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            invoiceSymbol = itemView.findViewById(R.id.tvInvoiceSymbol);
            buyerName = itemView.findViewById(R.id.tvBuyerName);
            grossPrice = itemView.findViewById(R.id.tvInvoiceAmount);

        }

        public TextView getInvoiceSymbol() {
            return invoiceSymbol;
        }

        public TextView getBuyerName() {
            return buyerName;
        }

        public TextView getGrossPrice() {
            return grossPrice;
        }
    }
}
