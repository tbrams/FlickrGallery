package dk.brams.android.flickrgallery;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import dk.brams.android.flickrgallery.model.Flickr;
import dk.brams.android.flickrgallery.model.Photo;


public class FlickrFetchr {
    private static final String TAG = "FlickrFetchr";


    public byte[] getUrlBytes(String urlSpec) throws IOException {

        // create a new URL object from the string and prepare the HTTP connection interface
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            // prepare outbut buffer and establish HTTP connection
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream input = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + " with " + urlSpec);
            }

            // copy buffer contents to output continously until inputstream is empty
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = input.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public List<GalleryItem> fetchItems() {
        List<GalleryItem> items = new ArrayList<>();
        try {
            String url = Uri.parse("https://api.flickr.com/services/rest/")
                    .buildUpon()
                    .appendQueryParameter("method", "flickr.photos.getRecent")
                    .appendQueryParameter("api_key", My.API_KEY)
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", "1")
                    .appendQueryParameter("extras", "url_s")
                    .build().toString();
            Log.d(TAG, "Flickre URL is: " + url);
            String jsonString = getUrlString(url);

            Log.d(TAG, "received JSON: " + jsonString);
            parseItemsGSON(items, jsonString);

        } catch (IOException ioe) {
            Log.e(TAG, "failed to fetch json items" + ioe);
        }

        return items;
    }


    private void parseItemsGSON(List<GalleryItem> items, String jsonString)  {

        Gson gson = new GsonBuilder().create();
        Flickr f = gson.fromJson(jsonString, Flickr.class);
        for (Photo p:f.photos.photo) {
            GalleryItem item = new GalleryItem();
            item.setId(p.id);
            item.setCaption(p.title);
            item.setUrl(p.url_s);
            items.add(item);
        }

    }

}

