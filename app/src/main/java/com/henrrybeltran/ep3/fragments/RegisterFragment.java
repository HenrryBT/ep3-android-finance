package com.henrrybeltran.ep3.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.henrrybeltran.ep3.R;
import com.henrrybeltran.ep3.model.SQLiteRepository;

public class RegisterFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private EditText etDescription;
    private EditText etAmount;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch swExpensesIncome;
    private Button btnRegisterMovement;

    public RegisterFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        binding = FragmentRegisterBinding.inflate(inflater, container, false);
//        binding.swExpensesIncome.setOnCheckedChangeListener(this);
//        binding.btnRegisterExpensesIncome.setOnClickListener(this);
//        return binding.getRoot();
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Toast.makeText(getActivity(), "Register Destroyed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getActivity(), "Register Fragment", Toast.LENGTH_SHORT).show();

//        etDescription = view.findViewById(R.id.et_description_form);
//        etAmount = view.findViewById(R.id.et_money_form);
//        swExpensesIncome = view.findViewById(R.id.sw_expenses_income);
//        btnRegisterMovement = view.findViewById(R.id.btn_register_expenses_income);
//
//        swExpensesIncome.setOnCheckedChangeListener(this);
//        btnRegisterMovement.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        String buttonFeedback = b ? "Register Income" : "Register Expenses";
        btnRegisterMovement.setText(buttonFeedback);
    }

    @Override
    public void onClick(View view) {
//        String descripcion = binding.etDescriptionForm.getText().toString();
//        float monto = Float.parseFloat(binding.etMoneyForm.getText().toString());
//
//        SQLiteRepository liteRepository = new SQLiteRepository(getActivity());
//
//        int movementSign = binding.swExpensesIncome.isChecked() ? 1 : -1;
//        int idTransaction = liteRepository.movementInsert(liteRepository, descripcion, monto, movementSign);

//        Toast.makeText(getActivity(), String.valueOf(idTransaction), Toast.LENGTH_SHORT).show();
    }
}