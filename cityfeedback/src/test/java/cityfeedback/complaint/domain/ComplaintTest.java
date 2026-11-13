
package cityfeedback.complaint.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ComplaintTest {

    // ab hier Happy Path

    @Test
    void erstelltBeschwerdeMitGueltigenEingaben() {
        Complaint complaint = Complaint.create(
                "c-1",
                "Laterne defekt",
                "Die Laterne ist seit Tagen aus.",
                "Infrastruktur"
        );

        assertNotNull(complaint);
        assertEquals("c-1", complaint.getCitizenId());
        assertEquals("Laterne defekt", complaint.getTitle());
        assertEquals("Die Laterne ist seit Tagen aus.",
                complaint.getDescription());
        assertEquals("Infrastruktur", complaint.getCategory());
    }

    @Test
    void neueBeschwerdeHatStatusOpen() {
        Complaint complaint = Complaint.create(
                "c-1",
                "Laterne defekt",
                "Die Laterne ist seit Tagen aus.",
                "Infrastruktur"
        );

        assertEquals(ComplaintStatus.OPEN, complaint.getStatus());
    }

    @Test
    void neueBeschwerdeSetztErstellungsdatumAutomatisch() {
        LocalDateTime before = LocalDateTime.now();

        Complaint complaint = Complaint.create(
                "c-1",
                "Laterne defekt",
                "Die Laterne ist seit Tagen aus.",
                "Infrastruktur"
        );

        LocalDateTime after = LocalDateTime.now();

        assertNotNull(complaint.getCreatedAt());
        assertFalse(complaint.getCreatedAt().isBefore(before));
        assertFalse(complaint.getCreatedAt().isAfter(after));
    }

    //ab hier Negativtests/Grenzwerttests 

    @Test
    void titelMitGenauDreiZeichenIstGueltig() {
        assertDoesNotThrow(() ->
                Complaint.create(
                        "c-1",
                        "abc",
                        "Eine gültige Beschreibung",
                        "Infrastruktur"
                )
        );
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " ", "\t\n", "ab"})
    void ungueltigerTitelWirdAbgelehnt(String title) {
        assertThrows(IllegalArgumentException.class, () ->
                Complaint.create(
                        "c-1",
                        title,
                        "Eine gültige Beschreibung",
                        "Infrastruktur"
                )
        );
    }

    @Test
    void beschreibungMitGenauZehnZeichenIstGueltig() {
        assertDoesNotThrow(() ->
                Complaint.create(
                        "c-1",
                        "Titel",
                        "1234567890",
                        "Infrastruktur"
                )
        );
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " ", "\t\n", "123456789"})
    void ungueltigeBeschreibungWirdAbgelehnt(String description) {
        assertThrows(IllegalArgumentException.class, () ->
                Complaint.create(
                        "c-1",
                        "Titel",
                        description,
                        "Infrastruktur"
                )
        );
    }

    @Test
    void kategorieMitEinemZeichenIstGueltig() {
        assertDoesNotThrow(() ->
                Complaint.create(
                        "c-1",
                        "Titel",
                        "Eine gültige Beschreibung",
                        "A"
                )
        );
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " ", "\t"})
    void ungueltigeKategorieWirdAbgelehnt(String category) {
        assertThrows(IllegalArgumentException.class, () ->
                Complaint.create(
                        "c-1",
                        "Titel",
                        "Eine gültige Beschreibung",
                        category
                )
        );
    }
}
