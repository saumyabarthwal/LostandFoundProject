package com.example.project2;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddItemFragment extends Fragment {

    EditText etTitle;
    EditText etDescription;
    EditText etDate;

    RadioGroup radioGroup;
    RadioButton rbLost;
    RadioButton rbFound;

    Spinner spinnerCategory;

    Button btnChoosePhoto;
    Button btnAdd;

    ImageView imagePreview;

    Uri selectedImageUri = null;

    // Gallery photo picker
    ActivityResultLauncher<String> photoPicker =
            registerForActivityResult(
                    new ActivityResultContracts.GetContent(),
                    uri -> {

                        if (uri != null) {

                            selectedImageUri = uri;

                            imagePreview.setImageURI(uri);

                            imagePreview.setVisibility(View.VISIBLE);
                        }
                    });

    public AddItemFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_add_item,
                container,
                false);

        etTitle =
                view.findViewById(R.id.etTitle);

        etDescription =
                view.findViewById(R.id.etDescription);

        etDate =
                view.findViewById(R.id.etDate);

        radioGroup =
                view.findViewById(R.id.radioGroup);

        rbLost =
                view.findViewById(R.id.rbLost);

        rbFound =
                view.findViewById(R.id.rbFound);

        spinnerCategory =
                view.findViewById(R.id.spinnerCategory);

        btnChoosePhoto =
                view.findViewById(R.id.btnChoosePhoto);

        btnAdd =
                view.findViewById(R.id.btnAdd);

        imagePreview =
                view.findViewById(R.id.imagePreview);


        // Categories directly in Java
        String[] categories = {
                "Bag",
                "Book",
                "Mobile",
                "Laptop",
                "ID Card",
                "Wallet",
                "Bottle",
                "Keys",
                "Calculator",
                "Other"
        };

        ArrayAdapter<String> categoryAdapter =
                new ArrayAdapter<>(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        categories);

        categoryAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinnerCategory.setAdapter(categoryAdapter);


        // Choose photo
        btnChoosePhoto.setOnClickListener(v -> {

            photoPicker.launch("image/*");

        });


        // Post item
        btnAdd.setOnClickListener(v -> addItem());


        return view;
    }


    private void addItem() {

        String title =
                etTitle.getText().toString().trim();

        String description =
                etDescription.getText().toString().trim();

        String date =
                etDate.getText().toString().trim();


        if (TextUtils.isEmpty(title)) {

            etTitle.setError("Enter item title");

            return;
        }


        if (TextUtils.isEmpty(description)) {

            etDescription.setError("Enter description");

            return;
        }


        String type;

        if (rbLost.isChecked()) {

            type = "Lost";

        } else {

            type = "Found";
        }


        // Get selected category
        String category =
                spinnerCategory
                        .getSelectedItem()
                        .toString();


        String imageUri = "";

        if (selectedImageUri != null) {

            imageUri =
                    selectedImageUri.toString();
        }


        Item item = new Item(
                type,
                category,
                title,
                description,
                date,
                imageUri
        );


        // Add item to repository
        ItemRepository.itemList.add(item);


        // Success popup
        new AlertDialog.Builder(getContext())

                .setTitle("Item Posted Successfully!")

                .setMessage(
                        "Your "
                                + type.toLowerCase()
                                + " item has been added.")

                .setPositiveButton("OK", null)

                .show();


        // Clear form
        etTitle.setText("");

        etDescription.setText("");

        etDate.setText("");

        selectedImageUri = null;

        imagePreview.setImageDrawable(null);

        imagePreview.setVisibility(View.GONE);
    }
}