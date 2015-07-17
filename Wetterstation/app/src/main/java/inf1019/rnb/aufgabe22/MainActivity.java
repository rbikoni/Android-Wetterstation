package inf1019.rnb.aufgabe22;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity
        extends Activity
        implements OnTaskCompleted {

    private Weather weather = new Weather();
    private ImageView imgView;
    private ListView lv;
    private TextView tv_label;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgView = (ImageView) findViewById(R.id.imageView);
        lv = (ListView) findViewById(R.id.listView);
        tv_label = (TextView)findViewById(R.id.textView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_request) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Wird nach abschließen der HttpTask von HttpTask.onPostExecute aufgerufen
    @Override
    public void onTaskCompleted(WetterstationResult result) {

        // Parsen, Wetterinformationen
        WeatherParser wp = new WeatherParser();
        wp.parse(result.getJSON(), this.weather);

        // Bild, imageView
        imgView.setImageBitmap(result.getPicture());

        // Wetterinformation in einer ListView darstellen
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, weather.toArray());
        lv.setAdapter(adapter);

        // Timestamp
        Date date = new Date(weather.getTs());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateString = sdf.format(date);
        tv_label.setText("Aktuelle Informationen " + dateString);

    }

    public void sendRequest(MenuItem item) {
        URL url = null;
        String requestMethod = "GET";
        ConnectionParams connectionParams = null;

        try {
            url = new URL("http://wetter2.mt-labor.it.hs-worms.de/api/data/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        connectionParams = new ConnectionParams(url, requestMethod);
        HttpTask task = new HttpTask(this);
        task.execute(connectionParams);
    }

}
