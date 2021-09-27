package pl.softr.ocr.desktop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.softr.ocr.R;
import pl.softr.ocr.database.entity.CompleteInvoice;
import pl.softr.ocr.database.entity.InvoicePosition;
import pl.softr.ocr.invoices.invoice.OnInvoiceSelect;

public class DesktopRVAdapter extends RecyclerView.Adapter<DesktopRVAdapter.ViewHolder> {

    private List<CompleteInvoice> dataSet;
    private Context context;
    private OnInvoiceSelect invoiceSelect;

    public DesktopRVAdapter(List<CompleteInvoice> dataSet, Context context, OnInvoiceSelect onInvoiceSelect) {
        this.dataSet = dataSet;
        this.context = context;
        this.invoiceSelect = onInvoiceSelect;
    }

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
        Double netAmount = getNetAmount(position);
        String amount = context.getString(R.string.amount_pln, netAmount);
        holder.getGrossPrice().setText(amount);
    }



    private double getNetAmount(int position) {
        return dataSet.
                get(position).getPositions().stream()
                .peek(p -> {
                    if (p.getNetPrice() == null) {
                        p.setNetPrice(0.0);
                    }
                })
                .mapToDouble(InvoicePosition::getNetPrice).reduce(0, Double::sum);
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
            itemView.setOnClickListener(v -> {
                Long invoiceId = dataSet.get(getAdapterPosition()).getInvoice().getId();
                invoiceSelect.selectInvoice(invoiceId, itemView);
            });
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
