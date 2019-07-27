package com.example.veganres;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TraderJoesProductItems extends AppCompatActivity
{
    private ListView mListView;
    private ArrayList<GroceryItems> mGroceryItems = new ArrayList<GroceryItems>();

    private ArrayAdapter<GroceryItems> mGroceryItemsArrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trader_joes_product_item_list_layout);

        mListView = (ListView) findViewById(R.id.product_list_view);

        mGroceryItems.add(new GroceryItems("Chocolate Brownie and Vanilla Créme Sandwich Cookies"));
        mGroceryItems.add(new GroceryItems("Lemon Créme Sandwich Cookies"));
        mGroceryItems.add(new GroceryItems("Vegan Banana Bread with Walnuts"));
        mGroceryItems.add(new GroceryItems("Almond Beverage Original"));
        mGroceryItems.add(new GroceryItems("Almond Beverage Original Unsweetened"));
        mGroceryItems.add(new GroceryItems("Almond Beverage Vanilla Unsweetened"));
        mGroceryItems.add(new GroceryItems("Coconut Beverage Unsweetened"));
        mGroceryItems.add(new GroceryItems("Coconut Beverage Vanilla"));
        mGroceryItems.add(new GroceryItems("Coconut Creamer"));
        mGroceryItems.add(new GroceryItems("Organic Soymilk Original"));
        mGroceryItems.add(new GroceryItems("Organic Soymilk Original Unsweetened"));
        mGroceryItems.add(new GroceryItems("Organic Soymilk Vanilla"));




        mGroceryItemsArrayAdapter = new GroceryItemsArrayAdapter(this,mGroceryItems);

        mListView.setAdapter(mGroceryItemsArrayAdapter);

        setTitle("Vegan\uD83C\uDF31Product\uD83C\uDF3EList");
    }
}
