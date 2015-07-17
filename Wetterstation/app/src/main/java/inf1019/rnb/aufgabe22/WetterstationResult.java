package inf1019.rnb.aufgabe22;

import android.graphics.Bitmap;

/**
 * Created by rnb on 10.05.2015.
 */
public class WetterstationResult {
    String JSON = "";
    Bitmap picture;

    public WetterstationResult(String JSON, Bitmap picture) {
        this.JSON = JSON;
        this.picture = picture;
    }

    public String getJSON() {
        return JSON;
    }

    public void setJSON(String JSON) {
        this.JSON = JSON;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }
}
