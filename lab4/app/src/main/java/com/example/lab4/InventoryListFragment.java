package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Date;

public class InventoryListFragment extends Fragment {
    private ArrayList<Inventory> inventories;
    private InventoryAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inventories = new ArrayList<>();
        adapter = new InventoryAdapter(this.getContext(), inventories);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory_list, container, false);
        ListView listInventories = (ListView)view.findViewById(R.id.listInventory);

        listInventories.setAdapter(adapter);

        listInventories.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(getActivity(), InventoryActivity.class);

            Inventory current = (Inventory) adapter.getItem(position);
            intent.putExtra("title", current.getmTitle());
            intent.putExtra("id", current.getmId());
            intent.putExtra("date", current.getmDate());
            intent.putExtra("status", current.ismSolved());
            intent.putExtra("index", position);

            startActivityForResult(intent,0);
        });
        View fab = (View)view.findViewById(R.id.fab); fab.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InventoryActivity.class);
            startActivityForResult(intent,0);
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK ) {
            int index = data.getIntExtra("index", -1);
            Inventory inventory = new Inventory();

            if (index != -1) {
                inventory = inventories.get(index);
            }

            inventory.setmTitle(data.getStringExtra("title"));
            inventory.setmDate((Date) data.getSerializableExtra("date"));
            inventory.setmSolved(data.getBooleanExtra("status",false));

            if (index == -1) {
                inventories.add(inventory);
            }

            adapter.notifyDataSetChanged();
        }
        else {
            Toast.makeText(getActivity(), "Wrong result", Toast.LENGTH_SHORT).show(); }
    }
}
