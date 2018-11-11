package com.example.chefgiraffe.activities.table;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.chefgiraffe.R;
import com.example.chefgiraffe.domains.Order;
import com.example.chefgiraffe.domains.OrderStatus;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderAdapter extends ArrayAdapter<Order> {
    private Context context;
    private List<Order> orders;
    private LayoutInflater layoutInflater;

    public OrderAdapter(@NonNull Context context, @NonNull List<Order> orders) {
        super(context, R.layout.item_order, orders);
        this.context = context;
        this.orders = orders;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_order, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.vwItems.setText(orders.get(position).commaSeparatedItems());

        OrderStatus orderStatus = orders.get(position).getOrderStatus();
        viewHolder.vwStatus.setText("Status: " + orderStatus.name());
        if (orderStatus == OrderStatus.FULFILLED) {
            viewHolder.vwStatus.setTextColor(context.getResources().getColor(android.R.color.holo_green_dark));
        }
        viewHolder.vwPrice.setText("Total: $" + orders.get(position).totalPrice());

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.vwItems)
        TextView vwItems;
        @BindView(R.id.vwStatus)
        TextView vwStatus;
        @BindView(R.id.vwPrice)
        TextView vwPrice;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
