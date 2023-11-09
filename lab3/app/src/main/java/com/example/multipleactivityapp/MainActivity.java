package com.example.multipleactivityapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Item> items;
    private ListView listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Item> itemsList = new ArrayList<>();

        if (savedInstanceState != null) {
            itemsList = savedInstanceState.getParcelableArrayList("items");
        }

        items = itemsList;
        listItem = (ListView) findViewById(R.id.listItems);
        ItemAdapter adapter = new ItemAdapter(this, items);
        listItem.setAdapter(adapter);

        View fab = (View) findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
            startActivityForResult(intent, 0);
        });

        listItem.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
            intent.putExtra("index", position);
            intent.putExtra("item", items.get(position));
            startActivityForResult(intent, 0);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            int index = data.getIntExtra("index", -1);
            Item item = (Item) data.getParcelableExtra("item");
            if (index != -1) {
                items.set(index, item);
            } else {
                items.add(item);
                ListView listView = (ListView) findViewById(R.id.listItems);
                ItemAdapter adapter = new ItemAdapter(this, items);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        } else {
            Toast.makeText(this, "Wrong result", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("items", items);
    }
}