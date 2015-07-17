package inf1019.rnb.aufgabe22;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by rnb on 06.05.2015.
 */
public class HttpTask extends AsyncTask<ConnectionParams, Integer, WetterstationResult>{
    private OnTaskCompleted listener;

    public HttpTask(OnTaskCompleted listener) {
        this.listener = listener;
    }

    @Override
    protected WetterstationResult doInBackground(ConnectionParams... connectionParams) {
        String JSON = getJSON(connectionParams);
        Bitmap picture = getWebcamPicture();

        return new WetterstationResult(JSON, picture);
    }

    private String getJSON(ConnectionParams... connectionParams) {
        HttpURLConnection urlConnection = null;
        String content = null;

        try {
            // Prepare Connection
            urlConnection = (HttpURLConnection) connectionParams[0]
                    .getUrl()
                    .openConnection();


            // Setup HTTP-REQUEST GET,POST, ...
            urlConnection.setRequestMethod(connectionParams[0].getRequestMethod());
           content = getData(urlConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            // Closing connection
            urlConnection.disconnect();
        }
        Log.v("HHHHH", content);

        return content;
    }
    private Bitmap getWebcamPicture() {
        HttpURLConnection urlConnection = null;
        URL url = null;
        Bitmap picture = null;

        try {
            url = new URL("http://wetter.fh-worms.de/img/webcam/preview.jpg");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream is = urlConnection.getInputStream();
            picture = BitmapFactory.decodeStream(is);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return picture;
    }

    @Override
    protected void onPostExecute(WetterstationResult result) {
        super.onPostExecute(result);

        listener.onTaskCompleted(result);
    }

    private String getData(InputStream in) {
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader buffer = new BufferedReader(reader);
        StringBuffer content = new StringBuffer();
        String line = null;

        do {
            try {
                line = buffer.readLine();
                if(line != null) content.append(line);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }while( line != null );

        return content.toString();
    }
}
