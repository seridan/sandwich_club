package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seridan on 19/02/2018.
 */

public final class JsonUtils {

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

    private static String getMainName(JSONObject sandwich){
        return sandwich.optString(MAIN_NAME);
    }

    private static List<String> getAlsoKnowAs(JSONObject sandwich){
        List<String> stringList = new ArrayList<>();
        JSONArray jsonArray = sandwich.optJSONArray(ALSO_KNOWN_AS);
        if (jsonArray != null){
            for (int i = 0; i < jsonArray.length(); i++){
                stringList.add((String) jsonArray.opt(i));
            }
        }
        return stringList;
    }

    private static String getPlaceOfOrigin(JSONObject sandwich){
        return sandwich.optString(PLACE_OF_ORIGIN);
    }

    private static String getDescription(JSONObject sandwich){
        return sandwich.optString(DESCRIPTION);
    }

    private static String getImage(JSONObject sandwich){
        return sandwich.optString(IMAGE);
    }

    private static List<String> getIngredients (JSONObject sandwich){
        List<String> stringList = new ArrayList<>();
        JSONArray jsonArray = sandwich.optJSONArray(INGREDIENTS);
        if (jsonArray != null){
            for (int i = 0; i < jsonArray.length(); i++){
                stringList.add((String)jsonArray.opt(i));
            }
        }
        return stringList;
    }
}
