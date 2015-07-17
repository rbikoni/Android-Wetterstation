package inf1019.rnb.aufgabe22;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rnb on 07.05.2015.
 */
public class WeatherParser {
    JSONObject job;

    public WeatherParser() {
    }

    public void parse(String json, Weather weather) {
        if (weather == null || json == "") return;

        try {
            job = new JSONObject(json);
        } catch (JSONException e) {
            job = null;
        }

        if (job == null) return;

        try {
            weather.setTs(this.getTs());
            weather.setTemp(this.getTemp());
            weather.setWind(this.getWind());
            weather.setBaro(this.getBaro());
            weather.setHum(this.getHum());
            weather.setRain(this.getRain());
            weather.setSun(this.getSun());
            weather.setForecast(this.getForecast());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Temp getTemp() throws JSONException {
        JSONObject jsonTemp = job.getJSONObject("temp");
        JSONObject jsonTempOut = jsonTemp.getJSONObject("out");
        Temp temp = new Temp();

        temp.c = jsonTempOut.getDouble("c");
        temp.f = jsonTempOut.getDouble("f");

        return temp;
    }

    private double getBaro() throws JSONException {

        return job.getDouble("baro");
    }

    private double getHum() throws JSONException {
        JSONObject hum = job.getJSONObject("hum");
        return hum.getDouble("out");
    }

    private Sun getSun() throws JSONException {
        JSONObject jsonSun = job.getJSONObject("sun");

        Sun sun = new Sun();
        sun.uv = jsonSun.getDouble("uv");
        sun.rise = jsonSun.getString("rise");
        sun.rad = jsonSun.getInt("rad");
        sun.set = jsonSun.getString("set");

        return sun;
    }

    private Rain getRain() throws JSONException {
        JSONObject jsonRain = job.getJSONObject("rain");

        Rain rain = new Rain();
        rain.rate = jsonRain.getInt("rate");
        rain.day = jsonRain.getInt("day");
        rain.month = jsonRain.getDouble("month");
        rain.storm = jsonRain.getInt("storm");
        rain.year = jsonRain.getDouble("year");

        return rain;
    }

    private Forecast getForecast() throws JSONException {
        JSONObject jsonForecast = job.getJSONObject("forecast");

        Forecast forecast = new Forecast();
        forecast.text = jsonForecast.getString("text");
        forecast.val = jsonForecast.getInt("val");
        forecast.rule = jsonForecast.getInt("rule");

        return forecast;
    }
    private Wind getWind() throws JSONException {
        JSONObject jsonWind = job.getJSONObject("wind");
        JSONObject jsonSpeed = jsonWind.getJSONObject("speed");
        JSONObject jsonAvg = jsonWind.getJSONObject("avg");
        JSONObject jsonDir = jsonWind.getJSONObject("dir");
        Wind wind = new Wind();

        wind.speed_kmh = jsonSpeed.getDouble("kmh");
        wind.speed_mph = jsonSpeed.getDouble("mph");

        wind.speed_avg_kmh = jsonAvg.getDouble("kmh");
        wind.speed_avg_mph = jsonAvg.getDouble("mph");

        wind.dir_deg = jsonDir.getInt("deg");
        wind.dir_text = jsonDir.getString("text");

        return wind;
    }

    public long getTs() throws JSONException {
        return job.getLong("ts");
    }
}
