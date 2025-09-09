package arep.arquitectura;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;


/**
 * Representa una respuesta
 *
 * @author Diego Cardenas
 */
public class Request {
    private static URL url;

    public static Map<String, String> extracRequestParams(String request) throws MalformedURLException {
        try{
            url = new URL(request);
            String metods = URLConnection.guessContentTypeFromName(request);


        } catch (MalformedURLException e){
            e.printStackTrace();
        }
    }
}
