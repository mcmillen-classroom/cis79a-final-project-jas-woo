package com.example.veganres;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class TJoesProductsFragment extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder tJoesList = new AlertDialog.Builder(getActivity());
        tJoesList.setTitle(R.string.vegan_products_trader_joes)
                 .setItems(R.array.list_trader_joes, new DialogInterface.OnClickListener()
                 {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i)
                     {

                     }
                 })
                 .setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // Create the AlertDialog object and return it
        return tJoesList.create();
    }
    /*@Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        return new AlertDialog.Builder(getActivity())
        //AlertDialog.Builder tJoesProducts = new AlertDialog.Builder(getActivity());
                .setTitle(R.string.vegan_products_trader_joes)
                //.setMessage(R.array.list_trader_joes)
                .setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface tJoesDialog, int t)
                    {

                    }
                })
                .create();
    }*/
}
