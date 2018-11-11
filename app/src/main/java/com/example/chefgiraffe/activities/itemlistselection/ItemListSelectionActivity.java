package com.example.chefgiraffe.activities.itemlistselection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chefgiraffe.R;
import com.example.chefgiraffe.activities.BaseActivity;
import com.example.chefgiraffe.domains.DataRequest;
import com.example.chefgiraffe.domains.Item;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;

public class ItemListSelectionActivity extends BaseActivity implements
        AdapterView.OnItemClickListener {
    @BindView(R.id.listViewItems)
    ListView listView;
    List<Item> items;
//    List<String> items = Arrays.asList("Sandwich", "fries", "burger");

    private ItemListSelectionViewModel viewModel;

    @Override
    protected int contentView() {
        return R.layout.activity_item_list_selection;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ItemListSelectionViewModel.class);
        viewModel.loadItems();
        listView.setOnItemClickListener(this);

        viewModel.getItems().observe(this, new Observer<DataRequest<List<Item>>>() {
            @Override
            public void onChanged(DataRequest<List<Item>> value) {
                if (value.isSuccessful()) {
                    items = value.getData();
                    List<String> names = ItemListSelectionActivity.this.getNames(value.getData());
                    ArrayAdapter<String> itemsAdapter =
                            new ArrayAdapter<>(ItemListSelectionActivity.this, android.R.layout.simple_list_item_1, names);
                    listView.setAdapter(itemsAdapter);
                } else {
                    Toast.makeText(ItemListSelectionActivity.this, value.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private List<String> getNames(List<Item> items) {
        List<String> names = new ArrayList<>();
        for (Item item : items) {
            names.add(item.getName());
        }
        return names;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String itemId = items.get(position).getId();
        String itemName = items.get(position).getName();
        Intent intent = new Intent();
        intent.putExtra("itemId", itemId);
        intent.putExtra("itemName", itemName);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
