package pl.softr.ocr.invoices.addInvoice;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pl.softr.ocr.database.entity.Buyer;
import pl.softr.ocr.database.entity.CompleteInvoice;
import pl.softr.ocr.database.entity.Invoice;
import pl.softr.ocr.database.entity.InvoiceGeneralInfo;
import pl.softr.ocr.database.entity.InvoicePosition;
import pl.softr.ocr.database.entity.Seller;
import pl.softr.ocr.database.repositories.InvoiceRepository;
import pl.softr.ocr.databinding.FragmentAddInvoiceBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddInvoiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddInvoiceFragment extends Fragment {

    private FragmentAddInvoiceBinding binding;
    private ImageButton wrap;
    private AddInvoiceViewModel viewModel;
    private List<InvoicePosition> positions;

    private InvoiceGeneralInfo generalInfo;
    private Seller seller;
    private Buyer buyer;
    private Invoice invoice;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AddInvoicePositionsItemAdapter adapter;

    public AddInvoiceFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AddInvoiceFragment newInstance(String param1, String param2) {
        AddInvoiceFragment fragment = new AddInvoiceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(AddInvoiceViewModel.class);
        binding = FragmentAddInvoiceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new AddInvoicePositionsItemAdapter(new ArrayList<>());
        binding.positionsList.rvPositionsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.positionsList.rvPositionsList.setAdapter(adapter);
        viewModel.getInvoicePositions().observe(getViewLifecycleOwner(), positions -> {
            adapter.setDataSet(positions);
        });
        binding.addInvoiceBottomButtons.saveInvoice.setOnClickListener(saveInvoiceClick);
        binding.positionsList.btnAddInvoicePosition.setOnClickListener(addInvoicePosition);
        binding.addInvoiceBottomButtons.cancelEdit.setOnClickListener(cancelClick);

    }

    private View.OnClickListener saveInvoiceClick = v -> {
        viewModel.saveInvoice(new CompleteInvoice(readGeneralInfo(), readSeller(), readBuyer(), readPositions()));
    };

    private View.OnClickListener cancelClick = v -> {
        requireActivity().onBackPressed();
    };

    private View.OnClickListener addInvoicePosition = v -> {
        int index = viewModel.addInvoicePosition(new InvoicePosition());
        adapter.notifyItemChanged(index);
    };



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private InvoiceGeneralInfo readGeneralInfo() {
        String invoiceType = binding.invoiceDetailsInclude.spInvoiceType.getSelectedItem().toString();
        String invoiceNumber = binding.invoiceDetailsInclude.etInvoiceNumber.getText().toString();
        String createDate = binding.invoiceDetailsInclude.etInvoiceCteateDate.getText().toString();
        String createPlace = binding.invoiceDetailsInclude.etInvoiceCreatePleace.getText().toString();
        String sellDate = binding.invoiceDetailsInclude.etSellDate.getText().toString();
        return new InvoiceGeneralInfo(invoiceType, invoiceNumber, createDate, createPlace, sellDate);
    }

    private Seller readSeller() {
        String sellerName = binding.sellerInclude.etSellerName.getText().toString();
        String sellerNIP = binding.sellerInclude.etSellerNIP.getText().toString();
        String sellerAddress = binding.sellerInclude.etSellerAddress.getText().toString();
        String sellerPostalCode = binding.sellerInclude.etSellerPostalCode.getText().toString();
        String sellerCity = binding.sellerInclude.etSellerCity.getText().toString();
        String sellerBankAccount = binding.sellerInclude.etSellerBankAccountNumber.getText().toString();
        return new Seller(sellerName, sellerNIP, sellerAddress, sellerPostalCode, sellerCity, sellerBankAccount);
    }

    private Buyer readBuyer() {
        String buyerName = binding.buyerInclude.etBuyerName.getText().toString();
        String buyerNIP = binding.buyerInclude.etBuyerNIP.getText().toString();
        String buyerAddress = binding.buyerInclude.etBuyerAddress.getText().toString();
        String buyerPostalCode = binding.buyerInclude.etBuyerPostalCode.getText().toString();
        String buyerCity = binding.buyerInclude.etBuyerCity.getText().toString();
        return new Buyer(buyerName, buyerNIP, buyerAddress, buyerPostalCode, buyerCity);
    }

    private List<InvoicePosition> readPositions() {
        return adapter.getDataSet();
    }
}