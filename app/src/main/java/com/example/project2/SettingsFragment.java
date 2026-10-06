package com.example.project2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    EditText etName;
    EditText etEmail;
    RadioGroup languageGroup;
    Button btnSave;

    public SettingsFragment() {}

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_settings,
                container,
                false);

        etName = view.findViewById(R.id.etName);
        etEmail = view.findViewById(R.id.etEmail);
        languageGroup = view.findViewById(R.id.languageGroup);
        btnSave = view.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {

            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();

            if (name.isEmpty()) {
                etName.setError("Enter your name");
                return;
            }

            if (email.isEmpty()) {
                etEmail.setError("Enter your email");
                return;
            }

            Toast.makeText(
                    getContext(),
                    "Profile Saved Successfully!",
                    Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}