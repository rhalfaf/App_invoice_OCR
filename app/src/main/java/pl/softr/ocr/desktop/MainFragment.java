package pl.softr.ocr.desktop;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import pl.softr.ocr.database.entity.CompleteInvoice;
import pl.softr.ocr.databinding.FragmentMainBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    private MainFragmentViewModel viewModel;
    private FragmentMainBinding binding;
    private DesktopRVAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainActivityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        binding = FragmentMainBinding.inflate(inflater,container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainFragmentViewModel.class);
        adapter = new DesktopRVAdapter(new ArrayList<>(), requireContext());
        binding.rvLastInvoices.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        binding.rvLastInvoices.setLayoutManager(new LinearLayoutManager(requireActivity()));
        binding.rvLastInvoices.setAdapter(adapter);
        viewModel.getLastInvoices(5).observe(getViewLifecycleOwner(), new Observer<List<CompleteInvoice>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<CompleteInvoice> completeInvoices) {
                adapter.setDataSet(completeInvoices);
                adapter.notifyDataSetChanged();
            }
        });
        binding.btnAddInvoice.setOnClickListener(addInvoiceClick);
    }

    View.OnClickListener addInvoiceClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NavDirections action = MainFragmentDirections.actionMainActivityFragmentToAddInvoice2();
            Navigation.findNavController(v).navigate(action);
        }
    };


}