package com.example.veganres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button mFFQuizButton;
    private Button mVeganProductButton;
    private Button mCommunityButton;
    private Button mIngredientIdentifierButton;

    private TextView mVeganResTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFFQuizButton = (Button) findViewById(R.id.fun_facts_quiz_button);
        mVeganProductButton = (Button) findViewById(R.id.vegan_products_button);
        mCommunityButton = (Button)findViewById(R.id.community_button);
        mIngredientIdentifierButton = (Button) findViewById(R.id.vegan_identifier_button);

        mFFQuizButton.setOnClickListener(this);
        mVeganProductButton.setOnClickListener(this);
        mCommunityButton.setOnClickListener(this);
        mIngredientIdentifierButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.fun_facts_quiz_button)
        {
            Intent funFactsIntent = FunFactsActivity.newIntent(this);
            startActivity(funFactsIntent);

        }
        else if (view.getId() == R.id.vegan_products_button)
        {
            Intent veganProductsIntent = VeganProductListActivity.newIntent(this);
            startActivity(veganProductsIntent);
        }
        else if (view.getId() == R.id.community_button)
        {
            Intent communityIntent = CommunityActivity.newIntent(this);
            startActivity(communityIntent);

        }
        else if (view.getId() == R.id.vegan_identifier_button)
        {
            Intent openFoodFactsIntent = new Intent("openfoodfacts.github.scrachx.openfood.views.MainActivity");

            if (openFoodFactsIntent.resolveActivity(getPackageManager()) != null)
            {
                startActivity(openFoodFactsIntent);
            }
            else
            {
                Toast.makeText(this, "OpenFoodFacts not installed", Toast.LENGTH_LONG).show();
            }
        }
    }
}
