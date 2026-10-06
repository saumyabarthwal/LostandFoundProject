package com.example.project2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyItemsFragment extends Fragment {

    RecyclerView recyclerView;
    ItemAdapter adapter;

    public MyItemsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_my_items,
                container,
                false);

        recyclerView =
                view.findViewById(R.id.myItemsRecyclerView);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));

        adapter = new ItemAdapter(
                getContext(),
                ItemRepository.itemList);

        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}