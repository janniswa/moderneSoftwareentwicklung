
package cityfeedback.complaint.api;

import com.sun.net.httpserver.HttpServer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class BeschwerdeServerTest {

    private HttpServer server;
    private HttpClient client;
    private String basisUrl;

    @BeforeEach
    void vorbereiten() throws Exception {
        server = BeschwerdeServer.starteServer(0);

        basisUrl = "http://localhost:"
                + server.getAddress().getPort();

        client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();
    }

    @AfterEach
    void serverBeenden() {
        if (server != null) {
            server.stop(0);
        }
    }

    private HttpResponse<String> abrufen(String pfad)
            throws Exception {

        HttpRequest anfrage = HttpRequest.newBuilder()
                .uri(URI.create(basisUrl + pfad))
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();

        return client.send(anfrage,
                HttpResponse.BodyHandlers.ofString());
    }

    private HttpResponse<String> absenden(String daten)
            throws Exception {

        HttpRequest anfrage = HttpRequest.newBuilder()
                .uri(URI.create(basisUrl + "/api/beschwerden"))
                .timeout(Duration.ofSeconds(5))
                .header("Content-Type",
                        "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(daten))
                .build();

        return client.send(anfrage,
                HttpResponse.BodyHandlers.ofString());
    }

    @Test
    void leereBeschwerdelisteWirdZurueckgegeben()
            throws Exception {

        var antwort = abrufen("/api/beschwerden");

        assertEquals(200, antwort.statusCode());
        assertEquals("[]", antwort.body());
    }

    @Test
    void beschwerdeWirdErstelltUndAbgerufen()
            throws Exception {

        var antwort = absenden(
                "titel=Defekte+Laterne"
                + "&beschreibung=Die+Laterne+funktioniert+nicht."
                + "&kategorie=Beleuchtung"
        );

        assertEquals(201, antwort.statusCode());
        assertTrue(antwort.body().contains("Defekte Laterne"));
        assertTrue(antwort.body().contains("OPEN"));

        var liste = abrufen("/api/beschwerden");

        assertEquals(200, liste.statusCode());
        assertTrue(liste.body().contains("Defekte Laterne"));
        assertTrue(liste.body().contains("Beleuchtung"));
    }

    @Test
    void ungueltigeBeschwerdeWirdAbgelehnt()
            throws Exception {

        var antwort = absenden(
                "titel="
                + "&beschreibung=Die+Laterne+funktioniert+nicht."
                + "&kategorie=Beleuchtung"
        );

        assertEquals(400, antwort.statusCode());
        assertTrue(antwort.body().contains("fehler"));
        assertEquals("[]",
                abrufen("/api/beschwerden").body());
    }

    @Test
    void falscherInhaltstypWirdAbgelehnt()
            throws Exception {

        HttpRequest anfrage = HttpRequest.newBuilder()
                .uri(URI.create(basisUrl + "/api/beschwerden"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{}"))
                .timeout(Duration.ofSeconds(5))
                .build();

        var antwort = client.send(anfrage,
                HttpResponse.BodyHandlers.ofString());

        assertEquals(415, antwort.statusCode());
    }

    @Test
    void fehlenderInhaltstypWirdAbgelehnt()
            throws Exception {

        HttpRequest anfrage = HttpRequest.newBuilder()
                .uri(URI.create(basisUrl + "/api/beschwerden"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .timeout(Duration.ofSeconds(5))
                .build();

        var antwort = client.send(anfrage,
                HttpResponse.BodyHandlers.ofString());

        assertEquals(415, antwort.statusCode());
    }

    @Test
    void nichtErlaubteHttpMethodeWirdAbgelehnt()
            throws Exception {

        HttpRequest anfrage = HttpRequest.newBuilder()
                .uri(URI.create(basisUrl + "/api/beschwerden"))
                .DELETE()
                .timeout(Duration.ofSeconds(5))
                .build();

        var antwort = client.send(anfrage,
                HttpResponse.BodyHandlers.ofString());

        assertEquals(405, antwort.statusCode());
    }

    @Test
    void zuGrossesFormularWirdAbgelehnt()
            throws Exception {

        var antwort = absenden(
                "beschreibung=" + "a".repeat(9000)
        );

        assertEquals(400, antwort.statusCode());
        assertTrue(antwort.body().contains("Formular ist zu groß"));
    }

    @Test
    void htmlOberflaecheWirdAusgeliefert()
            throws Exception {

        var antwort = abrufen("/");

        assertEquals(200, antwort.statusCode());
        assertTrue(antwort.body().contains("CityFeedback"));
        assertTrue(antwort.body().contains("beschwerde-formular"));

        assertEquals(200, abrufen("/index.html").statusCode());
    }

    @Test
    void unbekannteSeiteWirdNichtGefunden()
            throws Exception {

        var antwort = abrufen("/unbekannte-seite");

        assertEquals(404, antwort.statusCode());
    }

    @Test
    void sonderzeichenWerdenKorrektVerarbeitet()
            throws Exception {

        String beschreibung =
                "Defekte Laterne mit \"Glas\" und \\ Schaden.";

        String kodiert = URLEncoder.encode(
                beschreibung, StandardCharsets.UTF_8);

        var antwort = absenden(
                "titel=Defekte+Laterne"
                + "&beschreibung=" + kodiert
                + "&kategorie=Beleuchtung"
        );

        assertEquals(201, antwort.statusCode());
        assertTrue(antwort.body().contains("\\\"Glas\\\""));
        assertTrue(antwort.body().contains("\\\\"));
    }
}
