package com.example.lab4;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

public class InventoryFragment extends Fragment {
    private Inventory mInventory;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);
        mTitleField = (EditText) view.findViewById(R.id.inventory_title);
        mTitleField.setText(mInventory.getmTitle());

        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {  // empty
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mInventory.setmTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mDateButton = (Button) view.findViewById(R.id.inventory_date);
        mDateButton.setText(mInventory.getmDate().toString());
        mDateButton.setEnabled(false);
        mSolvedCheckBox = (CheckBox) view.findViewById(R.id.inventory_solved);
        mSolvedCheckBox.setChecked(mInventory.ismSolved());
        mSolvedCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> mInventory.setmSolved(isChecked));

        return view;
    }


    public void setmInventory(@NotNull Inventory inventory) {
        mInventory = inventory;
    }
    public @NotNull Inventory getmInventory() {
        return mInventory;
    }
}