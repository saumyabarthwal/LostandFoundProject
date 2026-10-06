package com.example.project2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter
        extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    Context context;
    ArrayList<Item> itemList;

    public ItemAdapter(Context context,
                       ArrayList<Item> itemList) {

        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_card, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ItemViewHolder holder,
            int position) {

        Item item = itemList.get(position);

        holder.tvType.setText(item.getType());

        holder.tvTitle.setText(item.getTitle());

        holder.tvCategory.setText(
                "Category: " + item.getCategory());

        holder.tvDescription.setText(
                item.getDescription());

        holder.tvDate.setText(
                "Date: " + item.getDate());

        // Show photo if available
        if (item.getImageUri() != null
                && !item.getImageUri().isEmpty()) {

            holder.itemImage.setVisibility(View.VISIBLE);

            holder.itemImage.setImageURI(
                    Uri.parse(item.getImageUri()));

        } else {

            holder.itemImage.setVisibility(View.GONE);
        }

        // Status
        if (item.isResolved()) {

            holder.tvStatus.setText(
                    "Status: Resolved");

        } else {

            holder.tvStatus.setText(
                    "Status: Active");
        }

        // DETAILS
        holder.btnDetails.setOnClickListener(v -> {

            Intent intent = new Intent(
                    context,
                    ItemDetailsActivity.class);

            intent.putExtra(
                    "title",
                    item.getTitle());

            intent.putExtra(
                    "type",
                    item.getType());

            intent.putExtra(
                    "category",
                    item.getCategory());

            intent.putExtra(
                    "description",
                    item.getDescription());

            intent.putExtra(
                    "date",
                    item.getDate());

            intent.putExtra(
                    "phone",
                    item.getPhone());

            intent.putExtra(
                    "imageUri",
                    item.getImageUri());

            context.startActivity(intent);
        });

        // RESOLVE
        holder.btnResolved.setOnClickListener(v -> {

            // Mark item as resolved
            item.setResolved(true);

            // Change Lost item to Found
            item.setType("Found");

            notifyItemChanged(position);

            new AlertDialog.Builder(context)
                    .setTitle("Item Resolved")
                    .setMessage(
                            "This item has been resolved and moved to Found Items.")
                    .setPositiveButton("OK", null)
                    .show();
        });

        // DELETE
        holder.btnDelete.setOnClickListener(v -> {

            new AlertDialog.Builder(context)
                    .setTitle("Delete Item?")
                    .setMessage(
                            "Are you sure you want to delete this item?")
                    .setPositiveButton(
                            "Delete",
                            (dialog, which) -> {

                                itemList.remove(position);

                                notifyItemRemoved(position);

                                Toast.makeText(
                                        context,
                                        "Item deleted",
                                        Toast.LENGTH_SHORT).show();
                            })
                    .setNegativeButton(
                            "Cancel",
                            null)
                    .show();
        });

        // CONTACT
        holder.btnContact.setOnClickListener(v -> {

            Intent intent =
                    new Intent(Intent.ACTION_DIAL);

            intent.setData(
                    Uri.parse("tel:" + item.getPhone()));

            context.startActivity(intent);
        });

        // SHARE
        holder.btnShare.setOnClickListener(v -> {

            String shareText =
                    "Campus Lost & Found\n\n"
                            + "Type: " + item.getType() + "\n"
                            + "Item: " + item.getTitle() + "\n"
                            + "Category: " + item.getCategory() + "\n"
                            + "Description: "
                            + item.getDescription();

            Intent intent =
                    new Intent(Intent.ACTION_SEND);

            intent.setType("text/plain");

            intent.putExtra(
                    Intent.EXTRA_TEXT,
                    shareText);

            context.startActivity(
                    Intent.createChooser(
                            intent,
                            "Share Item"));
        });
    }

    @Override
    public int getItemCount() {

        return itemList.size();
    }

    static class ItemViewHolder
            extends RecyclerView.ViewHolder {

        ImageView itemImage;

        TextView tvType;
        TextView tvTitle;
        TextView tvCategory;
        TextView tvDescription;
        TextView tvDate;
        TextView tvStatus;

        Button btnDetails;
        Button btnResolved;
        Button btnDelete;
        Button btnContact;
        Button btnShare;

        public ItemViewHolder(
                @NonNull View itemView) {

            super(itemView);

            itemImage =
                    itemView.findViewById(
                            R.id.itemImage);

            tvType =
                    itemView.findViewById(
                            R.id.tvType);

            tvTitle =
                    itemView.findViewById(
                            R.id.tvTitle);

            tvCategory =
                    itemView.findViewById(
                            R.id.tvCategory);

            tvDescription =
                    itemView.findViewById(
                            R.id.tvDescription);

            tvDate =
                    itemView.findViewById(
                            R.id.tvDate);

            tvStatus =
                    itemView.findViewById(
                            R.id.tvStatus);

            btnDetails =
                    itemView.findViewById(
                            R.id.btnDetails);

            btnResolved =
                    itemView.findViewById(
                            R.id.btnResolved);

            btnDelete =
                    itemView.findViewById(
                            R.id.btnDelete);

            btnContact =
                    itemView.findViewById(
                            R.id.btnContact);

            btnShare =
                    itemView.findViewById(
                            R.id.btnShare);
        }
    }
}