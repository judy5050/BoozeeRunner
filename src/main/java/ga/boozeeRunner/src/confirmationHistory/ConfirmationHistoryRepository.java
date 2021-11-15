package ga.boozeeRunner.src.confirmationHistory;

import ga.boozeeRunner.src.confirmationHistory.model.ConfirmationHistoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationHistoryRepository extends JpaRepository<ConfirmationHistoryInfo,Long> {
}
