package ga.boozeeRunner.src.room;

import ga.boozeeRunner.src.room.model.RoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomInfoRepository extends JpaRepository<RoomInfo,Long> {


    @Query("select r from RoomInfo  r where r.code =:code and r.isDeleted =:isDeleted")
    List<RoomInfo> findByCode(@Param("code") String code, @Param("isDeleted") String isDeleted);



}
