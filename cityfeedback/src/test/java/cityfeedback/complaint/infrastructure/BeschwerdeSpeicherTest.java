package cityfeedback.complaint.infrastructure;

import cityfeedback.complaint.domain.Complaint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeschwerdeSpeicherTest {

    private Complaint erstelleTestBeschwerde() {
        return Complaint.create(
                "buerger-1",
                "Defekte Straßenlaterne",
                "Die Straßenlaterne funktioniert nicht.",
                "Beleuchtung"
        );
    }

    @Test
    void beschwerdeWirdGespeichert() {
        BeschwerdeSpeicher speicher = new BeschwerdeSpeicher();
        Complaint beschwerde = erstelleTestBeschwerde();

        speicher.save(beschwerde);

        assertTrue(speicher.findById(beschwerde.getId()).isPresent());
    }

    @Test
    void gespeicherteBeschwerdeWirdGefunden() {
        BeschwerdeSpeicher speicher = new BeschwerdeSpeicher();
        Complaint beschwerde = erstelleTestBeschwerde();

        speicher.save(beschwerde);

        Complaint gefunden = speicher.findById(beschwerde.getId())
                .orElseThrow();

        assertEquals(beschwerde.getTitle(), gefunden.getTitle());
    }

    @Test
    void alleBeschwerdenWerdenAngezeigt() {
        BeschwerdeSpeicher speicher = new BeschwerdeSpeicher();

        speicher.save(erstelleTestBeschwerde());
        speicher.save(erstelleTestBeschwerde());

        assertEquals(2, speicher.findAll().size());
    }

    @Test
    void nichtVorhandeneBeschwerdeWirdNichtGefunden() {
        BeschwerdeSpeicher speicher = new BeschwerdeSpeicher();

        assertTrue(speicher.findById("ungueltige-id").isEmpty());
    }

    @Test
    void nullBeschwerdeWirdAbgelehnt() {
        BeschwerdeSpeicher speicher = new BeschwerdeSpeicher();

        assertThrows(
                IllegalArgumentException.class,
                () -> speicher.save(null)
        );
    }

    @Test
    void sucheMitNullIdLiefertKeinErgebnis() {
        BeschwerdeSpeicher speicher = new BeschwerdeSpeicher();

        assertTrue(speicher.findById(null).isEmpty());
    }
}
