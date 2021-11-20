package ga.boozeeRunner.src.alarm;

import ga.boozeeRunner.src.alarm.model.AlarmInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmInfoRepository extends JpaRepository<AlarmInfo,Long> {

    AlarmInfo findBySenderIdxAndRoomIdx(Long senderIdx, Long roomIdx);
}
