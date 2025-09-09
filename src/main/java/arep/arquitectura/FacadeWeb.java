package arep.arquitectura;
import jdk.internal.net.http.HttpRequestImpl;

import java.net.*;
import java.io.*;



/**
 * Represent de Facade Web Server with page html and with a params
 *
 * @author Diego Cardenas
 */
public class FacadeWeb {

    private static boolean running = true;


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(36000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        Socket clientSocket = null;

        while (running) {
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Recibí: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }


/*
            URL obj = new URL(inputLine);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", HttpRequestImpl.USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader inm = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLinee;
                StringBuffer response = new StringBuffer();

                while ((inputLinee = inm.readLine()) != null) {
                    response.append(inputLinee);
                }
                inm.close();

            }
            String cont = con.toString().split("/?")[1];*/
            outputLine = indexPage();

            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    private static String getBadResponse(){
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Bad request</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Se Ha producido un Error</h1>\n"
                + "</body>\n"
                + "</html>\n";
    }

    private static String indexPage(){
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\n" +
                "<head>\n" +
                "    <title>Form Facade</title>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<h1>Form para cambiar el valor almacenado de una llave dando su valor</h1>\n" +
                "<form action=\"/setkv\">\n" +
                "    <label for=\"key\">Key:</label><br>\n" +
                "    <input type=\"text\" id=\"key\" name=\"key\" value=\"key\"><br><br>\n" +
                "    <label for=\"value\">Value:</label><br>\n" +
                "    <input type=\"text\" id=\"value\" name=\"value\" value=\"value\"><br><br>\n" +
                "    <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                "</form>\n" +
                "<div id=\"getrespmsg\"></div>\n" +
                "\n" +
                "<script>\n" +
                "    function loadGetMsg() {\n" +
                "        let keyVar = document.getElementById(\"key\").value;\n" +
                "\tlet valueVar = document.getElementById(\"value\").value;\n" +
                "        const xhttp = new XMLHttpRequest();\n" +
                "        xhttp.onload = function () {\n" +
                "            document.getElementById(\"getrespmsg\").innerHTML =\n" +
                "                this.responseText;\n" +
                "        }\n" +
                "        xhttp.open(\"GET\", \"/setkv?key=\" + keyVar + \"&\" + \"value=\" +valueVar);\n" +
                "        xhttp.send();\n" +
                "    }\n" +
                "</script>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }
}
