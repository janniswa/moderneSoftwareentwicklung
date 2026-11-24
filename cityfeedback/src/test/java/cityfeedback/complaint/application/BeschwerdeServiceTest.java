package cityfeedback.complaint.application;

import cityfeedback.complaint.domain.Complaint;
import cityfeedback.complaint.infrastructure.BeschwerdeSpeicher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeschwerdeServiceTest {

    private BeschwerdeSpeicher speicher;
    private BeschwerdeService service;

    @BeforeEach
    void vorbereiten() {
        speicher = new BeschwerdeSpeicher();
        service = new BeschwerdeService(speicher);
    }

    @Test
    void beschwerdeWirdErstelltUndGespeichert() {
        Complaint beschwerde = service.beschwerdeErstellen(
                "buerger-1",
                "Defekte Straßenlaterne",
                "Die Straßenlaterne funktioniert nicht.",
                "Beleuchtung"
        );

        assertNotNull(beschwerde.getId());
        assertEquals("Defekte Straßenlaterne",
                beschwerde.getTitle());
        assertTrue(speicher.findById(beschwerde.getId()).isPresent());
    }

    @Test
    void alleBeschwerdenWerdenZurueckgegeben() {
        service.beschwerdeErstellen(
                "buerger-1",
                "Defekte Straßenlaterne",
                "Die Straßenlaterne funktioniert nicht.",
                "Beleuchtung"
        );

        service.beschwerdeErstellen(
                "buerger-2",
                "Schlagloch auf der Straße",
                "Auf der Hauptstraße befindet sich ein Schlagloch.",
                "Verkehr"
        );

        assertEquals(2, service.alleBeschwerden().size());
    }

    @Test
    void bestehendeBeschwerdeWirdGefunden() {
        Complaint beschwerde = service.beschwerdeErstellen(
                "buerger-1",
                "Defekte Straßenlaterne",
                "Die Straßenlaterne funktioniert nicht.",
                "Beleuchtung"
        );

        assertTrue(service.beschwerdeFinden(
                beschwerde.getId()).isPresent());
    }

    @Test
    void unbekannteBeschwerdeWirdNichtGefunden() {
        assertTrue(service.beschwerdeFinden(
                "unbekannte-id").isEmpty());
    }

    @Test
    void ungueltigerTitelWirdAbgelehnt() {
        assertThrows(IllegalArgumentException.class,
                () -> service.beschwerdeErstellen(
                        "buerger-1",
                        "",
                        "Die Straßenlaterne funktioniert nicht.",
                        "Beleuchtung"
                ));

        assertTrue(service.alleBeschwerden().isEmpty());
    }

    @Test
    void repositoryDarfNichtNullSein() {
        assertThrows(NullPointerException.class,
                () -> new BeschwerdeService(null));
    }
}
