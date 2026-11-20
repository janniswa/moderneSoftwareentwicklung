
package cityfeedback.complaint.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

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

        assertAll(
            () -> assertEquals("c-1", complaint.getCitizenId()),
            () -> assertEquals("Laterne defekt", complaint.getTitle()),
            () -> assertEquals(
                    "Die Laterne ist seit Tagen aus.",
                    complaint.getDescription()
            ),
            () -> assertEquals("Infrastruktur", complaint.getCategory()),
            () -> assertEquals(ComplaintStatus.OPEN, complaint.getStatus()),
            () -> assertNotNull(complaint.getCreatedAt()),
            () -> assertNotNull(complaint.getId())
        );
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


    @Test
    void erfolgreicheErstellungErzeugtComplaintCreatedEvent() {
        Complaint complaint = Complaint.create(
            "c-1",
            "Laterne defekt",
            "Die Laterne ist seit Tagen aus.",
            "Infrastruktur"
        );

        assertEquals(1, complaint.getDomainEvents().size());

        ComplaintCreated event = complaint.getDomainEvents().get(0);

        assertEquals(complaint.getId(), event.complaintId());
        assertEquals(complaint.getCreatedAt(), event.occurredAt());
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

    //weitere Testfälle

    @Test
    void erstelltKeineBeschwerdeMitUngueltigerCitizenId() {
        assertThrows(IllegalArgumentException.class, () ->
            Complaint.create(
                    null,
                    "Laterne defekt",
                    "Die Laterne ist seit Tagen aus.",
                    "Infrastruktur"
                )
        );

        assertThrows(IllegalArgumentException.class, () ->
            Complaint.create(
                    " ",
                    "Laterne defekt",
                    "Die Laterne ist seit Tagen aus.",
                    "Infrastruktur"
            )
        );
    }

    @Test
    void titelMitLeerzeichenWirdVorDerValidierungBereinigt() {
        assertThrows(IllegalArgumentException.class, () ->
            Complaint.create(
                    "c-1",
                    "  ab  ",
                    "Die Laterne ist seit Tagen aus.",
                    "Infrastruktur"
                )
        );
    }

    @Test
    void beschreibungMitLeerzeichenWirdVorDerValidierungBereinigt() {
        assertThrows(IllegalArgumentException.class, () ->
            Complaint.create(
                    "c-1",
                    "Laterne defekt",
                    "  abc  ",
                    "Infrastruktur"
                )
        );
    }

    @Test
    void offeneBeschwerdeKannInBearbeitungGenommenWerden() {
        Complaint complaint = Complaint.create(
            "c-1",
            "Laterne defekt",
            "Die Laterne ist seit Tagen aus.",
            "Infrastruktur"
        );

        complaint.startProcessing();

        assertEquals(ComplaintStatus.IN_PROGRESS, complaint.getStatus());
    }

    @Test
    void offeneBeschwerdeKannDirektGeloestWerden() {
        Complaint complaint = Complaint.create(
            "c-1",
            "Laterne defekt",
            "Die Laterne ist seit Tagen aus.",
            "Infrastruktur"
        );

        complaint.resolve();

        assertEquals(ComplaintStatus.RESOLVED, complaint.getStatus());
    }

    @Test
    void bearbeiteteBeschwerdeKannGeloestWerden() {
        Complaint complaint = Complaint.create(
            "c-1",
            "Laterne defekt",
            "Die Laterne ist seit Tagen aus.",
            "Infrastruktur"
        );


        complaint.startProcessing();
        complaint.resolve();

        assertEquals(ComplaintStatus.RESOLVED, complaint.getStatus());
    }

    @Test
    void geloesteBeschwerdeKannNichtErneutGeaendertWerden() {
        Complaint complaint = Complaint.create(
            "c-1",
            "Laterne defekt",
            "Die Laterne ist seit Tagen aus.",
            "Infrastruktur"
        );

        complaint.resolve();

        assertThrows(IllegalStateException.class, complaint::startProcessing);

        assertThrows(IllegalStateException.class, complaint::resolve);
    }


    @Test
    void neuErstellteBeschwerdeHatEineGueltigeId() {
        Complaint complaint = Complaint.create(
            "c-1",
            "Laterne defekt",
            "Die Laterne ist seit Tagen aus.",
            "Infrastruktur"
        );


        assertNotNull(complaint.getId());
        assertFalse(complaint.getId().isBlank());
    }

    @Test
    void zweiBeschwerdenErhaltenUnterschiedlicheIds() {
        Complaint complaint1 = Complaint.create(
            "c-1",
            "Laterne defekt",
            "Die Laterne ist seit Tagen aus.",
            "Infrastruktur"
        );

        Complaint complaint2 = Complaint.create(
            "c-2",
            "Strasse beschaedigt",
            "Die Strasse hat mehrere tiefe Schlagloecher.",
            "Infrastruktur"
        );

        assertNotEquals(complaint1.getId(), complaint2.getId());
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
