package cityfeedback.complaint.api;

import cityfeedback.complaint.application.BeschwerdeService;
import cityfeedback.complaint.infrastructure.BeschwerdeSpeicher;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class BeschwerdeServer {

    public static void main(String[] args) throws IOException {

        // Beschwerdespeicher und Service erstellen
        BeschwerdeSpeicher speicher = new BeschwerdeSpeicher();
        BeschwerdeService service = new BeschwerdeService(speicher);

        // HTTP-Server auf Port 8080 starten
        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080), 0
        );

        // Erster API-Endpunkt
        server.createContext("/api/beschwerden", exchange -> {

            if (!"GET".equals(exchange.getRequestMethod())) {
                exchange.getResponseHeaders().set("Allow", "GET");
                sendeAntwort(exchange, 405,
                        "{\"fehler\":\"Methode nicht erlaubt\"}");
                return;
            }

            int anzahl = service.alleBeschwerden().size();

            String antwort = "{\"anzahl\":" + anzahl + "}";

            sendeAntwort(exchange, 200, antwort);
        });

        server.start();

        System.out.println(
                "CityFeedback-Server gestartet auf Port 8080"
        );
    }

    private static void sendeAntwort(
            HttpExchange exchange,
            int statusCode,
            String inhalt) throws IOException {

        byte[] daten = inhalt.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set(
                "Content-Type", "application/json; charset=UTF-8"
        );

        exchange.sendResponseHeaders(statusCode, daten.length);

        try (OutputStream ausgabe = exchange.getResponseBody()) {
            ausgabe.write(daten);
        }
    }
}
