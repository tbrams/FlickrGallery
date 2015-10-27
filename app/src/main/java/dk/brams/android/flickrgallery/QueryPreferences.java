package dk.brams.android.flickrgallery;

import android.content.Context;
import android.preference.PreferenceManager;

public class QueryPreferences {
    private static final String PREFS_SEARCH_QUERY = "searchQuery";


    public static String getStoredQuery(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(PREFS_SEARCH_QUERY, null);
    }

    public static void setStoredQuery(Context context, String query) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(PREFS_SEARCH_QUERY, query).apply();
    }

}
