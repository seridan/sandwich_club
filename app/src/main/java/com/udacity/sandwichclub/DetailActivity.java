package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        /*Get the position form selected sandwich in mainActivity.*/
        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        /*Create an array with all sandwich details wich are in strings.xml in JSON format
        * Then get the JSON string according to the position selected*/
        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        /*Now parsing the JSON string using the parseSandwichJson method from JsonUtils class
        * into a Sandwich object*/
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    /**
     * This method check if there is value to set in the TextxView. If not will set the error message.
     *
     * @param s        string which represent the text that is set.
     * @param textView corresponding with the textView to set the data.
     */
    private void checkAndSetTex(String s, TextView textView) {
        if (TextUtils.isEmpty(s)) {
            textView.setText(R.string.sandwich_error_message);
        } else {
            textView.setText(s);
        }
    }

    /**
     * This method populate the Details textViews.
     *
     * @param sandwich Sandwich object.
     */
    private void populateUI(Sandwich sandwich) {
        TextView alsoKnowASTv = findViewById(R.id.also_known_tv);
        TextView originTv = findViewById(R.id.origin_tv);
        TextView descriptionTv = findViewById(R.id.description_tv);
        TextView ingredientsTv = findViewById(R.id.ingredients_tv);

        checkAndSetTex(TextUtils.join("\n", sandwich.getAlsoKnownAs()), alsoKnowASTv);
        checkAndSetTex(sandwich.getPlaceOfOrigin(), originTv);
        checkAndSetTex(sandwich.getDescription(), descriptionTv);
        checkAndSetTex(TextUtils.join("\n", sandwich.getIngredients()), ingredientsTv);

    }

}
