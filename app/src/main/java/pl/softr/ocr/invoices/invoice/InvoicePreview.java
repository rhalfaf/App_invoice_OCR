package pl.softr.ocr.invoices.invoice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.rxjava3.EmptyResultSetException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import pl.softr.ocr.R;
import pl.softr.ocr.database.entity.Buyer;
import pl.softr.ocr.database.entity.CompleteInvoice;
import pl.softr.ocr.database.entity.InvoiceGeneralInfo;
import pl.softr.ocr.database.entity.InvoicePosition;
import pl.softr.ocr.database.entity.SavedBuyer;
import pl.softr.ocr.database.entity.Seller;
import pl.softr.ocr.databinding.FragmentInvoicePreviewBinding;
import pl.softr.ocr.databinding.InvoicePreviewBuyerBinding;
import pl.softr.ocr.databinding.InvoicePreviewGeneralInfoBinding;
import pl.softr.ocr.databinding.InvoicePreviewSellerBinding;
import pl.softr.ocr.utils.DataPickerDialogFragment;
import pl.softr.ocr.utils.DateActual;
import pl.softr.ocr.utils.EditTextValidator;
import pl.softr.ocr.utils.OnDateSelectedListener;
import pl.softr.ocr.utils.PostalCodeInputFilter;

public class InvoicePreview extends Fragment {

    private FragmentInvoicePreviewBinding binding;
    private AddInvoiceViewModel viewModel;
    private Long invoiceId;
    private boolean editable;

    private static final String ARG_INVOICE_ID = "invoiceId";

    private Long mParam1;
    private AddInvoicePositionsItemAdapter adapter;
    private EditTextValidator validator;


    public InvoicePreview() {
    }

    public static InvoicePreview newInstance(String param1, String param2) {
        InvoicePreview fragment = new InvoicePreview();
        Bundle args = new Bundle();
        if (param1 != null) args.putString(ARG_INVOICE_ID, param1);
        if (!args.isEmpty()) fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getLong(ARG_INVOICE_ID);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        invoiceId = InvoicePreviewArgs.fromBundle(getArguments()).getInvoiceIdArg();
        editable = invoiceId == 0;
        viewModel = new ViewModelProvider(requireActivity()).get(AddInvoiceViewModel.class);
        binding = FragmentInvoicePreviewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new AddInvoicePositionsItemAdapter(new ArrayList<>(), editable, requireContext(), deletePosition);
        binding.positionsList.rvPositionsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.positionsList.rvPositionsList.setAdapter(adapter);
        setEditable();
        if (invoiceId != 0) {
            readInvoiceFromDb();
            //TODO change fragment title when invoice preview show
        } else {
            createNewInvoice();
            dummyData();
        }
    }

    private void setEditable() {
        InvoicePreviewGeneralInfoBinding b1 = binding.invoiceDetailsInclude;
        b1.spInvoiceType.setFocusable(editable);
        b1.spInvoiceType.setEnabled(editable);
        b1.etInvoiceNumber.setEnabled(editable);
        b1.etInvoiceNumber.setTextColor(Color.BLACK);
        b1.etInvoiceCreateDate.setEnabled(editable);
        b1.etInvoiceCreatePlace.setEnabled(editable);
        b1.etSellDate.setEnabled(editable);
        InvoicePreviewSellerBinding b2 = binding.sellerInclude;
        b2.etSellerName.setEnabled(editable);
        b2.etSellerNIP.setEnabled(editable);
        b2.etSellerAddress.setEnabled(editable);
        b2.etSellerPostalCode.setEnabled(editable);
        b2.etSellerCity.setEnabled(editable);
        b2.etSellerBankAccountNumber.setEnabled(editable);
        InvoicePreviewBuyerBinding b3 = binding.buyerInclude;
        b3.etBuyerName.setEnabled(editable);
        b3.etBuyerNIP.setEnabled(editable);
        b3.etBuyerAddress.setEnabled(editable);
        b3.etBuyerPostalCode.setEnabled(editable);
        b3.etBuyerCity.setEnabled(editable);


    }

    private void createNewInvoice() {
        InputFilter filter[] = {new PostalCodeInputFilter()};
        viewModel.getInvoicePositions().observe(getViewLifecycleOwner(), positions -> adapter.setDataSet(positions));
        binding.addInvoiceBottomButtons.saveInvoice.setOnClickListener(saveInvoiceClick);
        binding.sellerInclude.etSellerPostalCode.setFilters(filter);
        binding.buyerInclude.etBuyerPostalCode.setFilters(filter);
        binding.invoiceDetailsInclude.etInvoiceCreateDate.setText(DateActual.getDateFormatted(Calendar.getInstance()));
        binding.invoiceDetailsInclude.btnCreateDate.setOnClickListener(setCreateDate);
        binding.invoiceDetailsInclude.etSellDate.setText(DateActual.getDateFormatted(Calendar.getInstance()));
        binding.invoiceDetailsInclude.btnSellDateCalendar.setOnClickListener(setSellDate);
        binding.positionsList.btnAddInvoicePosition.setOnClickListener(addInvoicePosition);
        binding.addInvoiceBottomButtons.cancelEdit.setOnClickListener(cancelClick);

    }

    private void readInvoiceFromDb() {
        viewModel.getInvoiceById(invoiceId).observe(getViewLifecycleOwner(), this::setView);
        binding.addInvoiceBottomButtons.saveInvoice.setVisibility(View.GONE);
        binding.addInvoiceBottomButtons.cancelEdit.setVisibility(View.GONE);
        binding.invoiceDetailsInclude.btnCreateDate.setVisibility(View.GONE);
        binding.invoiceDetailsInclude.btnSellDateCalendar.setVisibility(View.GONE);
        binding.positionsList.btnAddInvoicePosition.setVisibility(View.GONE);
    }

    private void setView(CompleteInvoice i) {
        setGeneralInfo(i.getGeneralInfo());
        setSeller(i.getSeller());
        setBuyer(i.getBuyer());
        setPositions(i.getPositions());
    }

    private void setGeneralInfo(InvoiceGeneralInfo info) {
        InvoicePreviewGeneralInfoBinding b = binding.invoiceDetailsInclude;
        b.etInvoiceNumber.setText(info.getSymbol());
        b.etInvoiceCreateDate.setText(info.getCreateDate());
        b.etInvoiceCreatePlace.setText(info.getCreatePlace());
        b.etSellDate.setText(info.getSellDate());
    }

    private void setSeller(Seller s) {
        InvoicePreviewSellerBinding b = binding.sellerInclude;
        b.etSellerName.setText(s.getName());
        b.etSellerNIP.setText(s.getNIP());
        b.etSellerAddress.setText(s.getAddress());
        b.etSellerPostalCode.setText(s.getPostalCode());
        b.etSellerCity.setText(s.getCity());
        b.etSellerBankAccountNumber.setText(s.getBankAccount());
    }

    private void setBuyer(Buyer br) {
        InvoicePreviewBuyerBinding b = binding.buyerInclude;
        b.etBuyerName.setText(br.getName());
        b.etBuyerNIP.setText(br.getNIP());
        b.etBuyerAddress.setText(br.getAddress());
        b.etBuyerPostalCode.setText(br.getPostalCode());
        b.etBuyerCity.setText(br.getCity());
    }

    private void setPositions(List<InvoicePosition> positions) {
        adapter.setDataSet(positions);
        adapter.notifyDataSetChanged();
    }


    private InvoiceGeneralInfo readGeneralInfo() {
        String invoiceType = binding.invoiceDetailsInclude.spInvoiceType.getSelectedItem().toString();
        String invoiceNumber = binding.invoiceDetailsInclude.etInvoiceNumber.getText().toString();
        String createDate = binding.invoiceDetailsInclude.etInvoiceCreateDate.getText().toString();
        String createPlace = binding.invoiceDetailsInclude.etInvoiceCreatePlace.getText().toString();
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

    OnDateSelectedListener createDateListener = new OnDateSelectedListener() {
        @Override
        public void onDateSelect(Calendar c) {
            binding.invoiceDetailsInclude.etInvoiceCreateDate.setText(DateActual.getDateFormatted(c));
        }
    };

    OnDateSelectedListener sellDateListener = new OnDateSelectedListener() {
        @Override
        public void onDateSelect(Calendar c) {
            binding.invoiceDetailsInclude.etSellDate.setText(DateActual.getDateFormatted(c));
        }
    };

    OnInvoicePositionDelete deletePosition = new OnInvoicePositionDelete() {
        @Override
        public void delete(InvoicePosition p) {
            int index = viewModel.deleteInvoicePosition(p);
            adapter.notifyItemRemoved(index);
        }
    };

    private final View.OnClickListener saveInvoiceClick = v -> {
        if (validateFragment()) {
            saveInvoiceInDb();
        } else if (validator != null && validator.isListEmpty()) {
            showMissingPositionsAlert();
        }
    };

    private final View.OnClickListener cancelClick = v -> new AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.ask_close_no_save))
            .setPositiveButton(getString(R.string.yes), (dialog, which) -> requireActivity().onBackPressed())
            .setNegativeButton(getString(R.string.no), (dialog, which) -> dialog.dismiss())
            .show();


    private void saveInvoiceInDb() {
        viewModel.saveInvoice(new CompleteInvoice(readGeneralInfo(), readSeller(), readBuyer(), readPositions()));
        boolean saveBuyer = binding.buyerInclude.cbSaveBuyer.isChecked();
        if (saveBuyer) {
            SavedBuyer s = SavedBuyer.mapToSavedBuyer(readBuyer());
            saveBuyer(s);
        } else {
            goBack();
        }
    }

    private void saveBuyer(SavedBuyer actual) {
        viewModel.isBuyerSaved(actual)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(savedBuyerObserver);
    }

    SingleObserver<SavedBuyer> savedBuyerObserver = new SingleObserver<SavedBuyer>() {
        @Override
        public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
        }

        @Override
        public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull SavedBuyer savedBuyer) {
            SavedBuyer actual = SavedBuyer.mapToSavedBuyer(readBuyer());
            showBuyerAlreadyExistAlert(savedBuyer, actual);
        }

        @Override
        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
            SavedBuyer actual = SavedBuyer.mapToSavedBuyer(readBuyer());
            if (e instanceof EmptyResultSetException) {
                viewModel.saveMyBuyer(actual);
                goBack();
            } else {
                e.printStackTrace();
            }
        }
    };

    private void showBuyerAlreadyExistAlert(SavedBuyer buyer, SavedBuyer actual) {
        new AlertDialog.Builder(requireContext())
                .setMessage(getString(R.string.ask_update_my_buyer, buyer.getNIP()))
                .setPositiveButton(getString(R.string.yes), (dialogInterface, i) -> {
                    viewModel.updateSavedBuyer(actual);
                    dialogInterface.dismiss();
                })
                .setNegativeButton(getString(R.string.no), (dialogInterface, i) -> dialogInterface.dismiss()).show().setOnDismissListener(dismissListener);
    }

    DialogInterface.OnDismissListener dismissListener = dialog -> goBack();

    private boolean validateFragment() {
        validator = new EditTextValidator();
        validator.isNotEmpty(binding.invoiceDetailsInclude.etInvoiceNumber);
        validator.isNotEmpty(binding.invoiceDetailsInclude.etInvoiceCreatePlace);
        validator.isNotEmpty(binding.sellerInclude.etSellerName);
        validator.isNotEmpty(binding.sellerInclude.etSellerNIP);
        validator.isNotEmpty(binding.sellerInclude.etSellerAddress);
        validator.isNotEmpty(binding.sellerInclude.etSellerPostalCode);
        validator.isNotEmpty(binding.sellerInclude.etSellerCity);
        validator.isNotEmpty(binding.buyerInclude.etBuyerName);
        validator.isNotEmpty(binding.buyerInclude.etBuyerNIP);
        validator.isNotEmpty(binding.buyerInclude.etBuyerAddress);
        validator.isNotEmpty(binding.buyerInclude.etBuyerPostalCode);
        validator.isNotEmpty(binding.buyerInclude.etBuyerCity);
        validator.validateList(binding.positionsList.rvPositionsList);
        validator.getInvalidFields().stream().findFirst().ifPresent(View::requestFocus);
        return validator.isFormValid();
    }


    private final View.OnClickListener addInvoicePosition = v -> {
        int index = viewModel.addInvoicePosition(new InvoicePosition());
        adapter.notifyItemChanged(index);
    };

    private final View.OnClickListener setCreateDate = v -> showDatePicker(createDateListener);

    private final View.OnClickListener setSellDate = v -> showDatePicker(sellDateListener);

    private void showMissingPositionsAlert() {
        new AlertDialog.Builder(getContext())
                .setMessage(getString(R.string.ask_save_no_positions))
                .setPositiveButton(getString(R.string.yes), (dialog, which) -> saveInvoiceInDb())
                .setNegativeButton(getString(R.string.no), (dialog, which) -> dialog.dismiss()).show();
    }

    private void showDatePicker(OnDateSelectedListener listener) {
        DataPickerDialogFragment dialogFragment = new DataPickerDialogFragment();
        dialogFragment.setDateSelectedListener(listener);
        dialogFragment.show(getParentFragmentManager(), "data picker");
    }

    private void goBack() {
        requireActivity().onBackPressed();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void dummyData() {
        binding.invoiceDetailsInclude.etInvoiceNumber.setText("A1/10/2021");
        binding.invoiceDetailsInclude.etInvoiceCreatePlace.setText("Warszawa");
        binding.sellerInclude.etSellerName.setText("Sprzedawca nazwa");
        binding.sellerInclude.etSellerNIP.setText("7758971176");
        binding.sellerInclude.etSellerAddress.setText("Sprzedawca address 99/99");
        binding.sellerInclude.etSellerPostalCode.setText("03-043");
        binding.sellerInclude.etSellerCity.setText("Warszawa");
        binding.sellerInclude.etSellerBankAccountNumber.setText("219998921220291281210912");
        binding.buyerInclude.etBuyerName.setText("Kupujący nazwa");
        binding.buyerInclude.etBuyerNIP.setText("7876543321");
        binding.buyerInclude.etBuyerAddress.setText("Kupujący adres 99/99");
        binding.buyerInclude.etBuyerPostalCode.setText("99-000");
        binding.buyerInclude.etBuyerCity.setText("Warszawa");
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


}