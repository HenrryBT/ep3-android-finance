package com.henrrybeltran.ep3.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.henrrybeltran.ep3.adapters.ViewPagerAdapter;
import com.henrrybeltran.ep3.databinding.FragmentRegisterBinding;
import com.henrrybeltran.ep3.model.SQLiteRepository;

public class RegisterFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private FragmentRegisterBinding binding;

    public RegisterFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.swExpensesIncome.setOnCheckedChangeListener(this);
        binding.btnRegisterExpensesIncome.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        String buttonFeedback = b ? "Register Income" : "Register Expenses";
        binding.btnRegisterExpensesIncome.setText(buttonFeedback);
    }

    @Override
    public void onClick(View view) {
        String description = binding.etDescriptionForm.getText().toString();
        float amount = Float.parseFloat(binding.etMoneyForm.getText().toString());

        SQLiteRepository liteRepository = new SQLiteRepository(getActivity());

        int movementSign = binding.swExpensesIncome.isChecked() ? 1 : -1;
        int idTransaction = liteRepository.movementInsert(liteRepository, description, amount, movementSign);

        Toast.makeText(getActivity(), String.valueOf(idTransaction), Toast.LENGTH_SHORT).show();
    }
}