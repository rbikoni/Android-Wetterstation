package inf1019.rnb.aufgabe22;

import com.google.gson.Gson;

public class Weather {
    private long ts;
    private double baro;
    private double hum = 0;

    private Trend trend = new Trend();
    private Temp temp = new Temp();
    private Wind wind = new Wind();
    private Rain rain = new Rain();
    private Forecast forecast = new Forecast();
    private Sun sun = new Sun();


    public Weather() {
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public double getBaro() {
        return baro;
    }

    public void setBaro(double baro) {
        this.baro = baro;
    }

    public double getHum() {
        return hum;
    }

    public void setHum(double hum) {
        this.hum = hum;
    }

    public Sun getSun() {
        return sun;
    }

    public void setSun(Sun sun) {
        this.sun = sun;
    }

    public Trend getTrend() {
        return trend;
    }

    public void setTrend(Trend trend) {
        this.trend = trend;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    @Override
    public String toString() {
        return String.format("Temperatur %s °C\n" +
                "Luftdruck %.2f hPa\n" +
                "Luftfeuchtigkeit %s %c \n" +
                "Windgeschwindigkeit %s km/h \n" +
                "Windrichtung %s\n" +
                "UV Index %s\n" +
                "Sonnenstrahlung %s Watt/qm\n" +
                "Regen (heute)%s mm\n"+
                "Vorhersage %s", getTemp().c, getBaro(),getHum(), '%', getWind().speed_kmh, getWind().dir_text, getSun().uv, getSun().rad, getRain().day, getForecast().text );
    }

    public String[] toArray() {
        return toString().split("\n");
    }
}