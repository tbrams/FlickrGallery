package dk.brams.android.flickrgallery;

import android.content.Context;
import android.preference.PreferenceManager;

public class QueryPreferences {
    private static final String PREFS_SEARCH_QUERY = "searchQuery";
    public static final String PREFS_LAST_RESULT_ID = "lastResultId";


    public static String getStoredQuery(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(PREFS_SEARCH_QUERY, null);
    }

    public static void setStoredQuery(Context context, String query) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(PREFS_SEARCH_QUERY, query).apply();
    }

    public static String getLastResultId(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(PREFS_LAST_RESULT_ID, null);
    }
    public static void setLastResultId(Context context, String lastResultId) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(PREFS_LAST_RESULT_ID, lastResultId).apply();
    }

}
