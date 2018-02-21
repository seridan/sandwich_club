package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility functions to handle JSON data.
 * Created by seridan on 19/02/2018.
 */

public final class JsonUtils {

    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";
    private static final String TAG = JsonUtils.class.toString();



    public static Sandwich parseSandwichJson (String json)  {

        JSONObject parsedSandwich = null;
        try {
            parsedSandwich = new JSONObject(json);
            return new Sandwich(getMainName(parsedSandwich),getAlsoKnowAs(parsedSandwich),
                    getPlaceOfOrigin(parsedSandwich), getDescription(parsedSandwich), getImage(parsedSandwich),
                    getIngredients(parsedSandwich));
        } catch (JSONException e) {
            Log.d(TAG, e.getMessage());
            return null;
        }

    }

    private static String getMainName(JSONObject sandwich) throws JSONException {
        JSONObject name = sandwich.optJSONObject(NAME);
        if (name != null){
            return name.optString(MAIN_NAME);
        }
        return null;
    }

    private static List<String> getAlsoKnowAs(JSONObject sandwich) throws JSONException {
        JSONObject name = sandwich.optJSONObject(NAME);
        List<String> stringList = new ArrayList<>();
        if (name != null) {
            JSONArray jsonArray = name.optJSONArray(ALSO_KNOWN_AS);
                for (int i = 0; i < jsonArray.length(); i++) {
                    stringList.add((String) jsonArray.opt(i));
                }

        }
        return stringList;
    }

    private static String getPlaceOfOrigin(JSONObject sandwich) throws JSONException {
        return sandwich.optString(PLACE_OF_ORIGIN);
    }

    private static String getDescription(JSONObject sandwich) throws JSONException {
        return sandwich.optString(DESCRIPTION);
    }

    private static String getImage(JSONObject sandwich) throws JSONException {
        return sandwich.optString(IMAGE);
    }

    private static List<String> getIngredients (JSONObject sandwich) throws JSONException {
        List<String> stringList = new ArrayList<>();
        JSONArray jsonArray = sandwich.optJSONArray(INGREDIENTS);
            for (int i = 0; i < jsonArray.length(); i++){
                stringList.add((String)jsonArray.opt(i));
            }

        return stringList;
    }
}
