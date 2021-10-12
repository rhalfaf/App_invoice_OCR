package pl.softr.ocr.invoices.invoice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.internal.TextWatcherAdapter;

import java.util.List;
import java.util.Locale;

import pl.softr.ocr.R;
import pl.softr.ocr.database.entity.InvoicePosition;
import pl.softr.ocr.utils.PriceInputFilter;
import pl.softr.ocr.utils.Units;

public class AddInvoicePositionsItemAdapter extends RecyclerView.Adapter<AddInvoicePositionsItemAdapter.ViewHolder> {

    private List<InvoicePosition> dataSet;
    private final boolean editable;
    private final Context mContext;
    private final OnInvoicePositionDelete positionDeleteCallback;

    public AddInvoicePositionsItemAdapter(List<InvoicePosition> dataSet, boolean editable, Context context, OnInvoicePositionDelete positionDeleteCallback) {
        this.dataSet = dataSet;
        this.editable = editable;
        this.mContext = context;
        this.positionDeleteCallback = positionDeleteCallback;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invoice_preview_positions_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InputFilter[] filters = {new PriceInputFilter()};
        InvoicePosition invoice = dataSet.get(position);
        holder.getPositionName().setEnabled(editable);
        holder.getPositionQuantity().setEnabled(editable);
        holder.getPositionUnit().setEnabled(editable);
        holder.getPositionUnit().setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, Units.values()));
        holder.getPositionNetPrice().setEnabled(editable);
        holder.getPositionVat().setEnabled(editable);
        holder.getPositionGrossPrice().setEnabled(editable);
        holder.getPositionGrossPrice().setFilters(filters);
        holder.getPositionNetPrice().setFilters(filters);
        holder.getPositionName().setText(invoice.getPositionName());
        holder.getPositionNameLabelWrap().setText(invoice.getPositionName());
        holder.getPositionQuantity().setText(String.valueOf(
                invoice.getPositionQuantity() == null ? "0" : invoice.getPositionQuantity()
        ));
        holder.getPositionQuantityLabelWrap().setText(String.valueOf(
                invoice.getPositionQuantity() == null ? "0" : invoice.getPositionQuantity()
        ));
        holder.getPositionNetPrice().setText(String.valueOf(
                invoice.getNetPrice() == null ? "0" : invoice.getNetPrice()
        ));
        holder.getPositionNetPriceLabelWrap().setText(String.valueOf(
                invoice.getNetPrice() == null ? "0" : invoice.getNetPrice()
        ));
        holder.getPositionGrossPrice().setText(String.valueOf(
                invoice.getGrossPrice() == null ? "0" : invoice.getGrossPrice()
        ));
        holder.getPositionGrossPriceLabelWrap().setText(String.valueOf(
                invoice.getGrossPrice() == null ? "0" : invoice.getGrossPrice()
        ));
        holder.getPositionUnitLabelWrap().setText(holder.getPositionUnit().getSelectedItem().toString());
        holder.getPositionVatLabelWrap().setText(holder.getPositionVat().getSelectedItem().toString());
        holder.wrap.setOnClickListener(v -> wrap(holder));
        holder.getDeletePositionBtn().setVisibility(editable ? View.VISIBLE : View.GONE);
        setTextWatchers(holder);


        holder.getPositionVat().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    int vat = Integer.parseInt(parent.getSelectedItem().toString());
                    recalculateGross(vat);
                    dataSet.get(holder.getAdapterPosition()).setVAT(vat);
                } catch (NumberFormatException | NullPointerException e) {
                    Log.e("Spinner", e.getMessage());
                }
            }

            private void recalculateGross(int vat) {
                if (!holder.getPositionNetPrice().toString().isEmpty()) {
                    try {
                        double net = Double.parseDouble(holder.getPositionNetPrice().getText().toString());
                        holder.getPositionGrossPrice().setText(String.valueOf(net + net * vat / 100));
                    } catch (NumberFormatException | NullPointerException e) {
                        Log.e("Spinner", e.getMessage());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void wrap(ViewHolder holder) {
        if (holder.isWrapped()) {
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


    @SuppressLint("RestrictedApi")
    private void setTextWatchers(ViewHolder holder) {

        final int adapterPosition = holder.getAdapterPosition();
        holder.getPositionName().addTextChangedListener(new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(@NonNull Editable s) {
                String value = s.toString().isEmpty() ? "" : s.toString();
                dataSet.get(adapterPosition).setPositionName(value);

            }
        });

        holder.getPositionQuantity().addTextChangedListener(new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(@NonNull Editable s) {
                String value = s.toString().isEmpty() ? "0" : s.toString();
                dataSet.get(adapterPosition).setPositionQuantity(Double.parseDouble(value));

            }
        });

        holder.getPositionUnit().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dataSet.get(adapterPosition).setPositionUnit((Units) holder.getPositionUnit().getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        holder.getPositionNetPrice().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditText netPrice = holder.getPositionNetPrice();
                if (netPrice.isFocused()) {
                    if (!netPrice.getText().toString().isEmpty()) {
                        try {
                            double net = Double.parseDouble(netPrice.getText().toString());
                            int vat = Integer.parseInt(holder.getPositionVat().getSelectedItem().toString());
                            double gross = net + (net * vat / 100);
                            setGrossPrice(gross);

                        } catch (NumberFormatException | NullPointerException e) {
                            Log.e("Net price onTextChange", e.getMessage());
                        }
                    } else {
                        holder.getPositionGrossPrice().setText("0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String value = s.toString().isEmpty() ? "0" : s.toString();
                dataSet.get(adapterPosition).setNetPrice(Double.parseDouble(value));
            }

            private void setGrossPrice(double gross) {
                String grossPrice = String.format(Locale.UK, "%.2f", gross);
                holder.getPositionGrossPrice().setText(grossPrice);
            }
        });

        holder.getPositionGrossPrice().addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditText grossPrice = holder.getPositionGrossPrice();
                if (grossPrice.isFocused()) {
                    if (!grossPrice.getText().toString().isEmpty()) {
                        try {
                            double gross = Double.parseDouble(grossPrice.getText().toString());
                            int vat = Integer.parseInt(holder.getPositionVat().getSelectedItem().toString());
                            double net = (gross / (1 + ((double) vat / 100)));
                            setNetPrice(net);

                        } catch (NumberFormatException | NullPointerException e) {
                            Log.e("Gross price onTextChange", e.getMessage());

                        }
                    } else {
                        holder.getPositionNetPrice().setText("0");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String value = s.toString().isEmpty() ? "0" : s.toString();
                if (!value.isEmpty()) {
                    dataSet.get(adapterPosition).setGrossPrice(Double.valueOf(value));
                }
            }

            private void setNetPrice(double net) {
                String netPrice = String.format(Locale.UK, "%.2f", net);
                holder.getPositionNetPrice().setText(netPrice);
            }
        });
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

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
        private final Button deletePosition;
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
            deletePosition = itemView.findViewById(R.id.delete_position);
            deletePosition.setOnClickListener(v -> positionDeleteCallback.delete(dataSet.get(getAdapterPosition())));
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

        public boolean isWrapped() {
            return isWrapped;
        }

        public void setWrapped(boolean wrapped) {
            isWrapped = wrapped;
        }

        public Button getDeletePositionBtn() {
            return deletePosition;
        }
    }


}
