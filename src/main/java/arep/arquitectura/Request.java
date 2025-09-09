package arep.arquitectura;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


/**
 * Representa una respuesta
 *
 * @author Diego Cardenas
 */
public class Request {
    private static URL url;

    private String content;

    public Request(String urle){
        try {
            url = new URL(urle);
            String path = URLConnection.guessContentTypeFromName(urle);

            content = path.split("\n")[0];

        } catch (MalformedURLException e){
            e.printStackTrace();
        }
    }

    /**
     * Extract the query params from the url
     * @param request url to extract query params
     * @return Hap of the query params with key and value
     * @throws MalformedURLException expertion from URL
     */
    public Map<String, String> extracRequestParams(String request) throws MalformedURLException {
        try{
            url = new URL(request);
            String path = URLConnection.guessContentTypeFromName(request);

            String core = path.split("\n")[0];

            String params = path.split("/?")[1];
            Map<String, String> queryParams = new HashMap<>();
            // Key and Value
            String[] pa = params.split("&");

            for(String str: pa){
                String key = str.split("=")[0];
                String value = str.split("=")[1];
                queryParams.put(key, value);
            }
            this.setContent(core);
            return queryParams;
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    /**
     * Get url
     * @return url
     */
    public static URL getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static void setUrl(URL url) {
        Request.url = url;
    }
}
