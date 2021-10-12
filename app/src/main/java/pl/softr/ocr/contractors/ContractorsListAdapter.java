package pl.softr.ocr.contractors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pl.softr.ocr.R;
import pl.softr.ocr.database.entity.SavedBuyer;

public class ContractorsListAdapter extends RecyclerView.Adapter<ContractorsListAdapter.ViewHolder> {

    private List<SavedBuyer> dataSet;

    public ContractorsListAdapter(List<SavedBuyer> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contractors_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getBuyerName().setText(dataSet.get(position).getName());
        holder.getBuyerAddress().setText(dataSet.get(position).getAddress());
        holder.getBuyerNIP().setText(dataSet.get(position).getNIP());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public List<SavedBuyer> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<SavedBuyer> dataSet) {
        this.dataSet = dataSet;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView buyerName;
        private TextView buyerAddress;
        private TextView buyerNIP;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            buyerName = itemView.findViewById(R.id.tvBuyerNameListItem);
            buyerAddress = itemView.findViewById(R.id.tvBuyerAddressListItem);
            buyerNIP = itemView.findViewById(R.id.tvBuyerNIPListItem);
        }

        public TextView getBuyerName() {
            return buyerName;
        }

        public TextView getBuyerAddress() {
            return buyerAddress;
        }

        public TextView getBuyerNIP() {
            return buyerNIP;
        }
    }
}
