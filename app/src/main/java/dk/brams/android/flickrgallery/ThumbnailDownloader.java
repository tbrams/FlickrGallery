package dk.brams.android.flickrgallery;

import android.os.HandlerThread;
import android.util.Log;

public class ThumbnailDownloader<T> extends HandlerThread{
    private static final String TAG = "ThumbnailDownloader";

    public ThumbnailDownloader() {
        super(TAG);
    }

    public void queueThumbnail(T target, String url) {
        Log.d(TAG, "queueThumbnail() called with: " + "target = [" + target + "], url = [" + url + "]");
    }
}
