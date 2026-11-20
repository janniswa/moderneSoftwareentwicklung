
package cityfeedback.complaint.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Complaint {

    private final String id;
    private final String citizenId;
    private final String title;
    private final String description;
    private final String category;
    private ComplaintStatus status;
    private final LocalDateTime createdAt;

    private final List<ComplaintCreated> domainEvents;

    private Complaint(String id, String citizenId, String title,
                      String description, String category) {

        this.id = id;
        this.citizenId = citizenId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.status = ComplaintStatus.OPEN;
        this.createdAt = LocalDateTime.now();

        this.domainEvents = List.of(
                new ComplaintCreated(this.id, this.createdAt)
        );
    }

    public static Complaint create(String citizenId, String title,
                                   String description, String category) {

        validateText(citizenId, "Bürger-ID", 1);
        validateText(title, "Titel", 3);
        validateText(description, "Beschreibung", 10);
        validateText(category, "Kategorie", 1);

        return new Complaint(
                UUID.randomUUID().toString(),
                citizenId.strip(),
                title.strip(),
                description.strip(),
                category.strip()
        );
    }

    private static void validateText(String value, String fieldName,
                                     int minimumLength) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    fieldName + " darf nicht leer sein."
            );
        }

        if (value.strip().length() < minimumLength) {
            throw new IllegalArgumentException(
                    fieldName + " muss mindestens "
                    + minimumLength + " Zeichen enthalten."
            );
        }
    }

    public void startProcessing() {
        if (status != ComplaintStatus.OPEN) {
            throw new IllegalStateException(
                    "Nur offene Beschwerden können in Bearbeitung genommen werden."
            );
        }

        status = ComplaintStatus.IN_PROGRESS;
    }

    public void resolve() {
        if (status == ComplaintStatus.RESOLVED) {
            throw new IllegalStateException(
                    "Die Beschwerde ist bereits gelöst."
            );
        }

        status = ComplaintStatus.RESOLVED;
    }

    public String getId() {
        return id;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<ComplaintCreated> getDomainEvents() {
        return domainEvents;
    }
}