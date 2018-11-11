package com.example.chefgiraffe.activities.itemlistselection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.chefgiraffe.R;
import com.example.chefgiraffe.domains.Item;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemAdapter extends ArrayAdapter<Item> {
    private Context context;
    private List<Item> items;
    private LayoutInflater layoutInflater;

    public ItemAdapter(@NonNull Context context, @NonNull List<Item> items) {
        super(context, R.layout.item_order, items);
        this.context = context;
        this.items = items;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ItemAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item, parent, false);
            viewHolder = new ItemAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else viewHolder = (ItemAdapter.ViewHolder) convertView.getTag();

        Item item = items.get(position);
        viewHolder.txtItemName.setText(item.getName());
        viewHolder.txtItemDescription.setText(item.getDescription());
        viewHolder.txtItemPrice.setText("$" + Double.toString(item.getPrice()));

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.txtItemName)
        TextView txtItemName;
        @BindView(R.id.txtItemDescription)
        TextView txtItemDescription;
        @BindView(R.id.txtItemPrice)
        TextView txtItemPrice;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}