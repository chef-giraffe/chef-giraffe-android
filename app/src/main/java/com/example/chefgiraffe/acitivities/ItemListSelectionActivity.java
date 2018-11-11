package com.example.chefgiraffe.acitivities;

import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chefgiraffe.BaseActivity;
import com.example.chefgiraffe.ItemListSelectionViewModel;
import com.example.chefgiraffe.MainActivity;
import com.example.chefgiraffe.MainViewModel;
import com.example.chefgiraffe.R;
import com.example.chefgiraffe.TableActivity;
import com.example.chefgiraffe.domains.DataRequest;
import com.example.chefgiraffe.domains.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.chefgiraffe.acitivities.OrderActivity.PICK_ITEM_REQUEST;

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
        viewModel.getItems().observe(this, value -> {
            if (value.isSuccessful()) {
                items = value.getData();
                List<String> names = this.getNames(value.getData());
                ArrayAdapter<String> itemsAdapter =
                        new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
                listView.setAdapter(itemsAdapter);
            } else {
                Toast.makeText(ItemListSelectionActivity.this, value.getMessage(), Toast.LENGTH_SHORT).show();
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
        UUID itemId = items.get(position).getId();
        Intent intent = new Intent();
        intent.putExtra("itemId", itemId);
        setResult(PICK_ITEM_REQUEST, intent);
    }
}
