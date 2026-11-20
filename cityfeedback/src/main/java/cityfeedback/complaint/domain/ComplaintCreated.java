
package cityfeedback.complaint.domain;

import java.time.LocalDateTime;

public record ComplaintCreated(
        String complaintId,
        LocalDateTime occurredAt
) {
    public ComplaintCreated {
        if (complaintId == null || complaintId.isBlank()) {
            throw new IllegalArgumentException(
                    "Die Beschwerde-ID darf nicht leer sein."
            );
        }

        if (occurredAt == null) {
            throw new IllegalArgumentException(
                    "Der Ereigniszeitpunkt darf nicht null sein."
            );
        }
    }
}
