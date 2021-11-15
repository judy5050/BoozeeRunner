package ga.boozeeRunner.src.userRoom;

import ga.boozeeRunner.src.userRoom.model.GetUserRoomListRes;
import ga.boozeeRunner.src.userRoom.model.UserRoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoomInfoRepository extends JpaRepository<UserRoomInfo,Long> {



    @Query("select new ga.boozeeRunner.src.userRoom.model.GetUserRoomListRes(r.roomInfo.memberCount,r.roomInfo.roomName,r.roomInfo.roomType,r.roomInfo.meetDay,r.roomInfo.meetTime,r.roomInfo.isPay,r.roomInfo.pay,r.roomInfo.isAlone,r.roomInfo.id) from UserRoomInfo r  where r.userInfo.id =:userIdx and r.roomInfo.isAlone =:isAlone and r.roomInfo.isDeleted =:isDeleted")
    List<GetUserRoomListRes> findByUserId(@Param("userIdx") Long userId, @Param("isAlone") String isAlone, @Param("isDeleted") String isDeleted);

    @Query("select r from UserRoomInfo  r where r.roomInfo.id =:roomId and r.userInfo.id =:userId")
    List<UserRoomInfo> findByUserIdAndRoomId(@Param("userId") Long userId,@Param("roomId") Long roomId);

    @Query("select r from UserRoomInfo r where r.roomInfo.id =:roomIdx")
    List<UserRoomInfo> findUserListByRoomId(@Param("roomIdx") Long roomIdx);
}
