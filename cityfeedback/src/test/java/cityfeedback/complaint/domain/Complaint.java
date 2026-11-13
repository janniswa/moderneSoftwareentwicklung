package cityfeedback.complaint.domain;

import java.time.LocalDateTime;

public class Complaint {

    private final String id;
    private final String citizenId;
    private final String title;
    private final String description;
    private final String category;
    private ComplaintStatus status;
    private final LocalDateTime createdAt;

    private Complaint(String id, String citizenId, String title,
                      String description, String category) {

        this.id = id;
        this.citizenId = citizenId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.status = ComplaintStatus.OPEN;
        this.createdAt = LocalDateTime.now();
    }

    public static Complaint create(String citizenId, String title,
                                   String description, String category) {

        validateText(title, "Titel", 3);
        validateText(description, "Beschreibung", 10);
        validateText(category, "Kategorie", 1);

        return new Complaint(
                java.util.UUID.randomUUID().toString(),
                citizenId,
                title,
                description,
                category
        );
    }

    private static void validateText(String value, String fieldName,
                                     int minimumLength) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    fieldName + " darf nicht leer sein."
            );
        }

        if (value.length() < minimumLength) {
            throw new IllegalArgumentException(
                    fieldName + " muss mindestens "
                    + minimumLength + " Zeichen enthalten."
            );
        }
    }

    public void changeStatus(ComplaintStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException(
                    "Der Status darf nicht null sein."
            );
        }

        this.status = newStatus;
    }

    public void resolve() {
        this.status = ComplaintStatus.RESOLVED;
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
}
