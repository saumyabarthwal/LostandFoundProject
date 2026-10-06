package com.example.project2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView lostRecyclerView;
    RecyclerView foundRecyclerView;

    TextView tvLostCount;
    TextView tvFoundCount;

    ProgressBar progressBar;

    ItemAdapter lostAdapter;
    ItemAdapter foundAdapter;

    ArrayList<Item> lostItems = new ArrayList<>();
    ArrayList<Item> foundItems = new ArrayList<>();

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_home,
                container,
                false);

        lostRecyclerView =
                view.findViewById(
                        R.id.lostRecyclerView);

        foundRecyclerView =
                view.findViewById(
                        R.id.foundRecyclerView);

        tvLostCount =
                view.findViewById(
                        R.id.tvLostCount);

        tvFoundCount =
                view.findViewById(
                        R.id.tvFoundCount);

        progressBar =
                view.findViewById(
                        R.id.progressBar);

        lostRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));

        foundRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));

        progressBar.setVisibility(View.VISIBLE);

        loadItems();

        progressBar.setVisibility(View.GONE);

        return view;
    }

    private void loadItems() {

        lostItems.clear();
        foundItems.clear();

        for (Item item :
                ItemRepository.itemList) {

            if (item.getType().equals("Lost")) {

                lostItems.add(item);

            } else if (item.getType().equals("Found")) {

                foundItems.add(item);
            }
        }

        tvLostCount.setText(
                "Lost Items: " + lostItems.size());

        tvFoundCount.setText(
                "Found Items: " + foundItems.size());

        lostAdapter =
                new ItemAdapter(
                        getContext(),
                        lostItems);

        foundAdapter =
                new ItemAdapter(
                        getContext(),
                        foundItems);

        lostRecyclerView.setAdapter(
                lostAdapter);

        foundRecyclerView.setAdapter(
                foundAdapter);
    }

    @Override
    public void onResume() {

        super.onResume();

        if (getView() != null) {
            loadItems();
        }
    }
}