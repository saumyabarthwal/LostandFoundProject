package com.example.project2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemDetailsActivity extends AppCompatActivity {

    ImageView itemImage;

    TextView tvTitle;
    TextView tvType;
    TextView tvCategory;
    TextView tvDescription;
    TextView tvDate;

    Button btnContact;
    Button btnShare;

    String phone;
    String title;
    String type;
    String category;
    String description;
    String date;
    String imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_item_details);

        itemImage =
                findViewById(R.id.detailImage);

        tvTitle =
                findViewById(R.id.tvDetailTitle);

        tvType =
                findViewById(R.id.tvDetailType);

        tvCategory =
                findViewById(R.id.tvDetailCategory);

        tvDescription =
                findViewById(R.id.tvDetailDescription);

        tvDate =
                findViewById(R.id.tvDetailDate);

        btnContact =
                findViewById(R.id.btnDetailContact);

        btnShare =
                findViewById(R.id.btnDetailShare);

        Intent intent = getIntent();

        title =
                intent.getStringExtra("title");

        type =
                intent.getStringExtra("type");

        category =
                intent.getStringExtra("category");

        description =
                intent.getStringExtra("description");

        date =
                intent.getStringExtra("date");

        phone =
                intent.getStringExtra("phone");

        imageUri =
                intent.getStringExtra("imageUri");

        // Display information

        tvTitle.setText(title);

        tvType.setText(
                "Type: " + type);

        tvCategory.setText(
                "Category: " + category);

        tvDescription.setText(
                "Description: " + description);

        tvDate.setText(
                "Date: " + date);

        // Display photo

        if (imageUri != null
                && !imageUri.isEmpty()) {

            itemImage.setVisibility(
                    ImageView.VISIBLE);

            itemImage.setImageURI(
                    Uri.parse(imageUri));

        } else {

            itemImage.setVisibility(
                    ImageView.GONE);
        }

        // Contact

        btnContact.setOnClickListener(v -> {

            Intent dialIntent =
                    new Intent(Intent.ACTION_DIAL);

            dialIntent.setData(
                    Uri.parse("tel:" + phone));

            startActivity(dialIntent);
        });

        // Share

        btnShare.setOnClickListener(v -> {

            String text =
                    "Campus Lost & Found\n\n"
                            + "Item: " + title + "\n"
                            + "Type: " + type + "\n"
                            + "Category: " + category + "\n"
                            + "Description: "
                            + description;

            Intent shareIntent =
                    new Intent(Intent.ACTION_SEND);

            shareIntent.setType("text/plain");

            shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    text);

            startActivity(
                    Intent.createChooser(
                            shareIntent,
                            "Share Item"));
        });
    }
}