package ga.boozeeRunner.src.userRoom;


import ga.boozeeRunner.config.BaseException;
import ga.boozeeRunner.config.BaseResponseStatus;
import ga.boozeeRunner.src.room.RoomInfoRepository;
import ga.boozeeRunner.src.room.RoomInfoService;
import ga.boozeeRunner.src.room.model.GetRoomInfoRes;
import ga.boozeeRunner.src.room.model.GetRoomUserInfoRes;
import ga.boozeeRunner.src.room.model.RoomInfo;
import ga.boozeeRunner.src.user.models.UserInfo;
import ga.boozeeRunner.src.userRoom.model.GetUserRoomListRes;
import ga.boozeeRunner.src.userRoom.model.UserRoomInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRoomInfoService {

    private final UserRoomInfoRepository userRoomInfoRepository;
    private final RoomInfoRepository roomInfoRepository;
    /**
     * 유저가 들어간 방 조회
     * @param userId
     * @param isAlone
     */
    public List<GetUserRoomListRes> readUserRoomList(Long userId, String isAlone) {
        return userRoomInfoRepository.findByUserId(userId, isAlone, "N");

    }

    /**
     * 유저 방 접속
     * @param userInfo
     * @param roomInfo
     */
    public void createdUserRoomInfo(UserInfo userInfo, RoomInfo roomInfo) throws BaseException {
        List<UserRoomInfo> userRoomInfoList = userRoomInfoRepository.findByUserIdAndRoomId(userInfo.getId(), roomInfo.getId());
        if(userRoomInfoList.size()>0){
            throw new BaseException(BaseResponseStatus.ALREADY_USE_ROOM_CODE);
        }
        roomInfo.setMemberCount(roomInfo.getMemberCount()+1);
        userRoomInfoRepository.save(new UserRoomInfo(userInfo,roomInfo));
    }

    /**
     * 룸별 회원 정보 조회 API
     *
     * @param
     * @param roomIdx
     */
    public List<GetRoomUserInfoRes> getRoomUserList(Long roomIdx) {

        List<UserRoomInfo> userListByRoom = userRoomInfoRepository.findUserListByRoomId(roomIdx);
        List<GetRoomUserInfoRes> content=userListByRoom.stream().map((UserRoomInfo u) ->new GetRoomUserInfoRes(u)).collect(Collectors.toList());
        return content;
    }
}
