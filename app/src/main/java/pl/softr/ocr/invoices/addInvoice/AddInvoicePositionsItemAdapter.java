package pl.softr.ocr.invoices.addInvoice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.softr.ocr.R;
import pl.softr.ocr.database.entity.InvoicePosition;

public class AddInvoicePositionsItemAdapter extends RecyclerView.Adapter<AddInvoicePositionsItemAdapter.ViewHolder> {

    private List<InvoicePosition> dataSet;

    public AddInvoicePositionsItemAdapter(List<InvoicePosition> dataSet) {
        this.dataSet = dataSet;
    }

    public List<InvoicePosition> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<InvoicePosition> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_invoice_positions_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getPositionName().setText(dataSet.get(position).getPositionName());
        holder.getPositionNameLabelWrap().setText(dataSet.get(position).getPositionName());
        holder.getPositionQuantity().setText(String.valueOf(dataSet.get(position).getPositionQuantity()));
        holder.getPositionQuantityLabelWrap().setText(String.valueOf(dataSet.get(position).getPositionQuantity()));
        holder.getPositionUnitLabelWrap().setText(holder.getPositionUnit().getSelectedItem().toString());
        holder.getPositionNetPrice().setText(String.valueOf(dataSet.get(position).getNetPrice()));
        holder.getPositionNetPriceLabelWrap().setText(String.valueOf(dataSet.get(position).getNetPrice()));
        holder.getPositionGrossPrice().setText(String.valueOf(dataSet.get(position).getGrossPrice()));
        holder.getPositionGrossPriceLabelWrap().setText(String.valueOf(dataSet.get(position).getGrossPrice()));
        holder.getPositionVatLabelWrap().setText(holder.getPositionVat().getSelectedItem().toString());
        holder.wrap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrap(holder);
            }
        });
    }

    private void wrap(ViewHolder holder) {
        if (holder.isWrapped) {
            holder.getPositionName().setVisibility(View.VISIBLE);
            holder.getPositionQuantity().setVisibility(View.VISIBLE);
            holder.getPositionUnit().setVisibility(View.VISIBLE);
            holder.getPositionNetPrice().setVisibility(View.VISIBLE);
            holder.getPositionGrossPrice().setVisibility(View.VISIBLE);
            holder.getPositionVat().setVisibility(View.VISIBLE);
            holder.getPositionNameLabelWrap().setVisibility(View.INVISIBLE);
            holder.getPositionQuantityLabelWrap().setVisibility(View.INVISIBLE);
            holder.getPositionUnitLabelWrap().setVisibility(View.INVISIBLE);
            holder.getPositionVatLabelWrap().setVisibility(View.INVISIBLE);
            holder.getPositionNetPriceLabelWrap().setVisibility(View.INVISIBLE);
            holder.getPositionGrossPriceLabelWrap().setVisibility(View.INVISIBLE);
            holder.setWrapped(false);
        } else {
            holder.getPositionName().setVisibility(View.GONE);
            holder.getPositionQuantity().setVisibility(View.GONE);
            holder.getPositionUnit().setVisibility(View.GONE);
            holder.getPositionNetPrice().setVisibility(View.GONE);
            holder.getPositionGrossPrice().setVisibility(View.GONE);
            holder.getPositionVat().setVisibility(View.GONE);
            holder.getPositionNameLabelWrap().setVisibility(View.VISIBLE);
            holder.getPositionQuantityLabelWrap().setVisibility(View.VISIBLE);
            holder.getPositionUnitLabelWrap().setVisibility(View.VISIBLE);
            holder.getPositionVatLabelWrap().setVisibility(View.VISIBLE);
            holder.getPositionNetPriceLabelWrap().setVisibility(View.VISIBLE);
            holder.getPositionGrossPriceLabelWrap().setVisibility(View.VISIBLE);
            holder.setWrapped(true);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final EditText positionName;
        private final TextView positionNameLabelWrap;
        private final EditText positionQuantity;
        private final TextView positionQuantityLabelWrap;
        private final Spinner positionUnit;
        private final TextView positionUnitLabelWrap;
        private final Spinner positionVat;
        private final TextView positionVatLabelWrap;
        private final EditText positionNetPrice;
        private final TextView positionNetPriceLabelWrap;
        private final EditText positionGrossPrice;
        private final TextView positionGrossPriceLabelWrap;
        private final ImageButton wrap;
        private boolean isWrapped = false;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            positionName = itemView.findViewById(R.id.position_name);
            positionQuantity = itemView.findViewById(R.id.position_quantity);
            positionUnit = itemView.findViewById(R.id.unit_spinner);
            positionNetPrice = itemView.findViewById(R.id.net_price);
            positionGrossPrice = itemView.findViewById(R.id.gross_price);
            positionVat = itemView.findViewById(R.id.vat_spinner);
            wrap = itemView.findViewById(R.id.btn_wrapp);
            positionNameLabelWrap = itemView.findViewById(R.id.pos_item_name_val_wrap);
            positionQuantityLabelWrap = itemView.findViewById(R.id.pos_item_quantity_val_wrap);
            positionUnitLabelWrap = itemView.findViewById(R.id.pos_item_unit_val_wrap);
            positionVatLabelWrap = itemView.findViewById(R.id.pos_item_vat_val_wrap);
            positionNetPriceLabelWrap = itemView.findViewById(R.id.pos_item_net_price_val_wrap);
            positionGrossPriceLabelWrap = itemView.findViewById(R.id.pos_item_gross_price_val_wrap);
        }

        public EditText getPositionName() {
            return positionName;
        }

        public EditText getPositionQuantity() {
            return positionQuantity;
        }

        public Spinner getPositionUnit() {
            return positionUnit;
        }

        public Spinner getPositionVat() {
            return positionVat;
        }

        public EditText getPositionNetPrice() {
            return positionNetPrice;
        }

        public EditText getPositionGrossPrice() {
            return positionGrossPrice;
        }

        public TextView getPositionNameLabelWrap() {
            return positionNameLabelWrap;
        }

        public TextView getPositionQuantityLabelWrap() {
            return positionQuantityLabelWrap;
        }

        public TextView getPositionUnitLabelWrap() {
            return positionUnitLabelWrap;
        }

        public TextView getPositionVatLabelWrap() {
            return positionVatLabelWrap;
        }

        public TextView getPositionNetPriceLabelWrap() {
            return positionNetPriceLabelWrap;
        }

        public TextView getPositionGrossPriceLabelWrap() {
            return positionGrossPriceLabelWrap;
        }

        public ImageButton getWrap() {
            return wrap;
        }

        public boolean isWrapped() {
            return isWrapped;
        }

        public void setWrapped(boolean wrapped) {
            isWrapped = wrapped;
        }
    }


}
