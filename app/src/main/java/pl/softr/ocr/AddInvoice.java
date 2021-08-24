package pl.softr.ocr;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import pl.softr.ocr.databinding.FragmentAddInvoiceBinding;
import pl.softr.ocr.databinding.FragmentInvoiceBinding;
import pl.softr.ocr.invoices.addInvoice.AddInvoicePositionsItemAdapter;
import pl.softr.ocr.invoices.addInvoice.PositionsItemModel;
import pl.softr.ocr.utils.Units;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddInvoice#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddInvoice extends Fragment {

    private FragmentAddInvoiceBinding binding;
    private ImageButton wrap;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AddInvoicePositionsItemAdapter adapter;

    public AddInvoice() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AddInvoice newInstance(String param1, String param2) {
        AddInvoice fragment = new AddInvoice();
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
        binding = FragmentAddInvoiceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<PositionsItemModel> testList = new ArrayList<>();
        testList.add(new PositionsItemModel("Pozycja testowa", 20.0, Units.godzia, 120.0, null, 23.0));
        testList.add(new PositionsItemModel("Pozycja testowa", 20.0, Units.godzia, 120.0, null, 23.0));
        adapter = new AddInvoicePositionsItemAdapter(testList);
        binding.positionsList.rvPositionsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.positionsList.rvPositionsList.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}