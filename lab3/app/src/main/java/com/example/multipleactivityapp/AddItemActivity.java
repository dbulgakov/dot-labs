package com.example.multipleactivityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.textfield.TextInputEditText;

public class AddItemActivity extends AppCompatActivity {

    private int index = 0;
    private Item item;
    private TextInputEditText item_title_name;
    private TextInputEditText item_title_class;
    private TextInputEditText item_size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = this.getIntent();
        index = intent.getIntExtra("index",-1);
        item = (Item) intent.getParcelableExtra("item");

        if (item != null) {
            item_title_name = (TextInputEditText) findViewById(R.id.item_title_name);
            item_title_class = (TextInputEditText) findViewById(R.id.item_title_class);
            item_size = (TextInputEditText) findViewById(R.id.item_size);

            item_title_name.setText(item.itemName);
            item_title_class.setText(item.itemClassification);
            item_title_class.setText(item.itemSize);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        if (item.getItemId() == R.id.action_save) {
            Item newItem = new Item();

            newItem.itemName = ((TextInputEditText) findViewById(R.id.item_title_name)).getText().toString();
            newItem.itemClassification = ((TextInputEditText) findViewById(R.id.item_title_class)).getText().toString();
            newItem.itemSize = ((TextInputEditText) findViewById(R.id.item_size)).getText().toString();
            Intent intent = new Intent();
            intent.putExtra("index", index);
            intent.putExtra("item", newItem);
            setResult(Activity.RESULT_OK,intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("item", item);
    }
}