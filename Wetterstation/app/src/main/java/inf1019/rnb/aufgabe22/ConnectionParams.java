package inf1019.rnb.aufgabe22;

import java.net.URL;

/**
 * Created by rnb on 06.05.2015.
 */
public class ConnectionParams {
    private URL url = null;
    private String requestMethod = "GET";
    public ConnectionParams(URL url, String requestMethod) {
        this.url = url;
        this.requestMethod = requestMethod;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }
}
