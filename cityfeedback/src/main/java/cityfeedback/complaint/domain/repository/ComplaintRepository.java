package cityfeedback.complaint.domain.repository;

import cityfeedback.complaint.domain.Complaint;
import java.util.List;
import java.util.Optional;

public interface ComplaintRepository {

    void save(Complaint complaint);

    Optional<Complaint> findById(String id);

    List<Complaint> findAll();
}
