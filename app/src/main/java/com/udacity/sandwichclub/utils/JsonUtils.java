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


    /**
     * The method parsing a String object with json format and returns
     * an object of the sandwich class using the methods below.
     * @param json String object with json format.
     * @return an object of the sandwich class.
     */
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

    /**
     * Method to parse and return the main name string of the sandwich object.
     * @param sandwichJson
     * @return String that contains sandwich main name's.
     * @throws JSONException
     */
    private static String getMainName(JSONObject sandwichJson) throws JSONException {
        JSONObject nameJson = sandwichJson.optJSONObject(NAME);
        if (nameJson != null){
            return nameJson.optString(MAIN_NAME);
        }
        return null;
    }

    /**
     * Method to parse and return the Also KnownAs List String's of the sandwich object.
     * @param sandwichJson
     * @return List Strings that contains sandwich AlsoKnowAs's.
     * @throws JSONException
     */
    private static List<String> getAlsoKnowAs(JSONObject sandwichJson) throws JSONException {
        JSONObject nameJson = sandwichJson.optJSONObject(NAME);
        List<String> stringList = new ArrayList<>();
        if (nameJson != null) {
            JSONArray jsonArray = nameJson.optJSONArray(ALSO_KNOWN_AS);
                for (int i = 0; i < jsonArray.length(); i++) {
                    stringList.add((String) jsonArray.opt(i));
                }

        }
        return stringList;
    }

    /**
     * Method to parse and return the place of origin String of the sandwich object.
     * @param sandwichJson
     * @return String of place of origin
     * @throws JSONException
     */
    private static String getPlaceOfOrigin(JSONObject sandwichJson) throws JSONException {
        return sandwichJson.optString(PLACE_OF_ORIGIN);
    }

    /**
     * Method to parse and return the description String of the sandwich object.
     * @param sandwichJson
     * @return String of description
     * @throws JSONException
     */
    private static String getDescription(JSONObject sandwichJson) throws JSONException {
        return sandwichJson.optString(DESCRIPTION);
    }

    /**
     * Method to parse and return the image String of the sandwich object.
     * @param sandwichJson
     * @return String of image
     * @throws JSONException
     */
    private static String getImage(JSONObject sandwichJson) throws JSONException {
        return sandwichJson.optString(IMAGE);
    }

    /**
     * Method to parse and return the ingredients List String of the sandwich object.
     * @param sandwichJson
     * @return  List String of ingredients
     * @throws JSONException
     */
    private static List<String> getIngredients (JSONObject sandwichJson) throws JSONException {
        List<String> stringList = new ArrayList<>();
        JSONArray jsonArray = sandwichJson.optJSONArray(INGREDIENTS);
            for (int i = 0; i < jsonArray.length(); i++){
                stringList.add((String)jsonArray.opt(i));
            }

        return stringList;
    }
}
