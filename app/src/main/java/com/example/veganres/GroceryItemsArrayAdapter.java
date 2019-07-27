package com.example.veganres;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class GroceryItemsArrayAdapter extends ArrayAdapter<GroceryItems>
{
    public GroceryItemsArrayAdapter(Context context, int resource, GroceryItems[] objects)
    {
        super(context, resource, objects);
    }
    public GroceryItemsArrayAdapter(Context context, List<GroceryItems> objects)
    {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        GroceryItems product = getItem(position);

        if (convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_trader_joes_product_items,parent,false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.grocery_item);

        title.setText(product.getItemName());

        return convertView;
    }
}
