package arep.arquitectura;

/**
 * Representa una respuesta
 *
 * @author Diego Cardenas
 */
public class Response {
    private int statusCode;
    private String body;

    public Response(int statusCode, String body){
        this.statusCode = statusCode;
        this.body = body;
    }

    public String getBody(){
        return this.body;
    }

    public int getStatusCode(){
        return this.statusCode;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
