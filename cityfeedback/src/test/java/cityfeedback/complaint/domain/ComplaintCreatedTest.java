package cityfeedback.complaint.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ComplaintCreatedTest {

    @Test
    void shouldRejectNullComplaintId() {
        assertThrows(IllegalArgumentException.class,
                () -> new ComplaintCreated(null, LocalDateTime.now()));
    }

    @Test
    void shouldRejectBlankComplaintId() {
        assertThrows(IllegalArgumentException.class,
                () -> new ComplaintCreated("   ", LocalDateTime.now()));
    }

    @Test
    void shouldRejectNullOccurredAt() {
        assertThrows(IllegalArgumentException.class,
                () -> new ComplaintCreated("complaint-123", null));
    }
}