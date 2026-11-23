package cityfeedback.complaint.infrastructure;

import cityfeedback.complaint.domain.Complaint;
import cityfeedback.complaint.domain.repository.ComplaintRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class BeschwerdeSpeicher implements ComplaintRepository {

    private final Map<String, Complaint> beschwerden =
            new ConcurrentHashMap<>();

    @Override
    public void save(Complaint beschwerde) {
        if (beschwerde == null) {
            throw new IllegalArgumentException(
                    "Beschwerde darf nicht null sein."
            );
        }

        beschwerden.put(beschwerde.getId(), beschwerde);
    }

    @Override
    public Optional<Complaint> findById(String id) {
        if (id == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(beschwerden.get(id));
    }

    @Override
    public List<Complaint> findAll() {
        return new ArrayList<>(beschwerden.values());
    }
}
