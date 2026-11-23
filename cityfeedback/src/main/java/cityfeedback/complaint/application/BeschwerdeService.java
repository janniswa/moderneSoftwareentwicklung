package cityfeedback.complaint.application;

import cityfeedback.complaint.domain.Complaint;
import cityfeedback.complaint.domain.repository.ComplaintRepository;

import java.util.List;
import java.util.Optional;
import java.util.Objects;

public class BeschwerdeService {

    private final ComplaintRepository repository;

    public BeschwerdeService(ComplaintRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    // Neue Beschwerde erstellen und speichern
    public Complaint beschwerdeErstellen(
            String buergerId,
            String titel,
            String beschreibung,
            String kategorie) {

        Complaint beschwerde = Complaint.create(
                buergerId,
                titel,
                beschreibung,
                kategorie
        );

        repository.save(beschwerde);
        return beschwerde;
    }

    // Alle gespeicherten Beschwerden abrufen
    public List<Complaint> alleBeschwerden() {
        return repository.findAll();
    }

    // Eine bestimmte Beschwerde suchen
    public Optional<Complaint> beschwerdeFinden(String id) {
        return repository.findById(id);
    }
}
