
package cityfeedback.complaint.api;

import cityfeedback.complaint.application.BeschwerdeService;
import cityfeedback.complaint.domain.Complaint;
import cityfeedback.complaint.infrastructure.BeschwerdeSpeicher;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class BeschwerdeServer {

    public static void main(String[] args) throws IOException {

        BeschwerdeService service =
                new BeschwerdeService(new BeschwerdeSpeicher());

        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080), 0
        );

        server.createContext("/api/beschwerden", exchange -> {
            try {

                // Alle Beschwerden abrufen
                if ("GET".equals(exchange.getRequestMethod())) {

                    String antwort = service.alleBeschwerden().stream()
                            .map(BeschwerdeServer::alsJson)
                            .collect(Collectors.joining(",", "[", "]"));

                    sendeAntwort(exchange, 200, antwort);

                // Neue Beschwerde erstellen
                } else if ("POST".equals(exchange.getRequestMethod())) {

                    String typ = exchange.getRequestHeaders()
                            .getFirst("Content-Type");

                    if (typ == null || !typ.toLowerCase(Locale.ROOT)
                            .startsWith("application/x-www-form-urlencoded")) {

                        sendeAntwort(exchange, 415,
                                "{\"fehler\":\"Formulardaten erwartet\"}");
                        return;
                    }

                    Map<String, String> felder = leseFormular(exchange);

                    Complaint beschwerde = service.beschwerdeErstellen(
                            "demo-buerger",
                            felder.get("titel"),
                            felder.get("beschreibung"),
                            felder.get("kategorie")
                    );

                    sendeAntwort(exchange, 201, alsJson(beschwerde));

                } else {
                    exchange.getResponseHeaders().set("Allow", "GET, POST");

                    sendeAntwort(exchange, 405,
                            "{\"fehler\":\"Methode nicht erlaubt\"}");
                }

            } catch (IllegalArgumentException fehler) {

                sendeAntwort(exchange, 400,
                        "{\"fehler\":" + jsonText(fehler.getMessage()) + "}");
            }
        });

        
        // HTML-Benutzeroberfläche bereitstellen
        server.createContext("/", exchange -> {

            String pfad = exchange.getRequestURI().getPath();

            if (!"/".equals(pfad)
                    && !"/index.html".equals(pfad)) {

                exchange.sendResponseHeaders(404, -1);
                exchange.close();
                return;
            }

            if (!"GET".equals(exchange.getRequestMethod())) {

                exchange.getResponseHeaders().set("Allow", "GET");
                exchange.sendResponseHeaders(405, -1);
                exchange.close();
                return;
            }

            try (var datei = BeschwerdeServer.class
                    .getResourceAsStream("/web/index.html")) {

                if (datei == null) {
                    exchange.sendResponseHeaders(404, -1);
                    exchange.close();
                    return;
                }

                byte[] daten = datei.readAllBytes();

                exchange.getResponseHeaders().set(
                        "Content-Type",
                        "text/html; charset=UTF-8"
                );

                exchange.sendResponseHeaders(200, daten.length);

                try (var ausgabe = exchange.getResponseBody()) {
                    ausgabe.write(daten);
                }
            }
        });

        
        server.start();

        System.out.println("CityFeedback läuft auf Port 8080");
    }

    // Formulardaten auslesen
    private static Map<String, String> leseFormular(
            HttpExchange exchange) throws IOException {

        byte[] daten = exchange.getRequestBody().readNBytes(8193);

        if (daten.length > 8192) {
            throw new IllegalArgumentException("Formular ist zu groß.");
        }

        String text = new String(daten, StandardCharsets.UTF_8);
        Map<String, String> felder = new HashMap<>();

        for (String eintrag : text.split("&")) {

            String[] teil = eintrag.split("=", 2);

            String schluessel = URLDecoder.decode(
                    teil[0], StandardCharsets.UTF_8);

            String wert = URLDecoder.decode(
                    teil.length > 1 ? teil[1] : "",
                    StandardCharsets.UTF_8);

            felder.put(schluessel, wert);
        }

        return felder;
    }

    // Beschwerde in JSON umwandeln
    private static String alsJson(Complaint beschwerde) {

        return "{\"id\":" + jsonText(beschwerde.getId())
                + ",\"titel\":" + jsonText(beschwerde.getTitle())
                + ",\"beschreibung\":" + jsonText(beschwerde.getDescription())
                + ",\"kategorie\":" + jsonText(beschwerde.getCategory())
                + ",\"status\":" + jsonText(beschwerde.getStatus().name())
                + "}";
    }

    // Sonderzeichen für JSON absichern
    private static String jsonText(String text) {

        if (text == null) return "null";

        StringBuilder ergebnis = new StringBuilder("\"");

        for (char zeichen : text.toCharArray()) {

            switch (zeichen) {
                case '"' -> ergebnis.append("\\\"");
                case '\\' -> ergebnis.append("\\\\");
                case '\n' -> ergebnis.append("\\n");
                case '\r' -> ergebnis.append("\\r");
                case '\t' -> ergebnis.append("\\t");

                default -> {
                    if (zeichen < 32) {
                        ergebnis.append(
                                String.format("\\u%04x", (int) zeichen));
                    } else {
                        ergebnis.append(zeichen);
                    }
                }
            }
        }

        return ergebnis.append('"').toString();
    }

    // HTTP-Antwort senden
    private static void sendeAntwort(
            HttpExchange exchange,
            int status,
            String inhalt) throws IOException {

        byte[] daten = inhalt.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set(
                "Content-Type", "application/json; charset=UTF-8"
        );

        exchange.sendResponseHeaders(status, daten.length);

        try (OutputStream ausgabe = exchange.getResponseBody()) {
            ausgabe.write(daten);
        }
    }
}
