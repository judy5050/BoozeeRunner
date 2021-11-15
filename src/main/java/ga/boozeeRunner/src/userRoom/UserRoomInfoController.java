package ga.boozeeRunner.src.userRoom;


import ga.boozeeRunner.config.BaseException;
import ga.boozeeRunner.config.BaseResponse;
import ga.boozeeRunner.config.BaseResponseStatus;
import ga.boozeeRunner.src.room.RoomInfoService;
import ga.boozeeRunner.src.room.model.*;
import ga.boozeeRunner.src.user.UserInfoService;
import ga.boozeeRunner.src.user.models.UserInfo;
import ga.boozeeRunner.src.userRoom.model.GetRoomUserInfoListRes;
import ga.boozeeRunner.src.userRoom.model.GetUserRoomListRes;
import ga.boozeeRunner.src.userRoom.model.PostRoomCodeReq;
import ga.boozeeRunner.src.userRoom.model.PostRoomCodeRes;
import ga.boozeeRunner.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserRoomInfoController {

    private final JwtService jwtService;
    private final UserRoomInfoService userRoomInfoService;
    private final UserInfoService userInfoService;
    private final RoomInfoService roomInfoService;

    /**
     * 방 조회
     */
    @GetMapping("/roomList")
    public BaseResponse<List<GetUserRoomListRes>> getRoomInfoList(@RequestParam String isAlone){
        Long userId;

        List<GetUserRoomListRes> getUserRoomListRes;
        try {
            //조회할 유저 인덱스

            userId = jwtService.getUserId();

        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
        System.out.println("getUserRoomListRes = ");
        getUserRoomListRes = userRoomInfoService.readUserRoomList(userId, isAlone);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS,getUserRoomListRes);
    }

    /**
     * 초대 코드 입력 후 방 접속
     */
    @PostMapping("/room/code")
    public BaseResponse<PostRoomCodeRes> getRoomInfoList(@RequestBody PostRoomCodeReq postRoomCodeReq){
        Long userId;

        List<GetUserRoomListRes> getUserRoomListRes;
        RoomInfo roomInfo;
        try {
            //조회할 유저 인덱스

            userId = jwtService.getUserId();
            UserInfo userInfo = userInfoService.getUserInfo(userId);
            roomInfo = roomInfoService.getRoomInfo(postRoomCodeReq.getCode());
            userRoomInfoService.createdUserRoomInfo(userInfo,roomInfo);

        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }


        return new BaseResponse<>(BaseResponseStatus.SUCCESS,new PostRoomCodeRes(roomInfo.getId()));

    }


    /**
     * 각 방 참여자 목록
     */
    @GetMapping("/rooms/{roomIdx}")
    public BaseResponse<GetRoomUserInfoListRes> getRoomUserList(@PathVariable Long roomIdx){

        Long userId;
        List<GetRoomUserInfoRes> roomUserList;

        try {
            userId = jwtService.getUserId();
            UserInfo userInfo = userInfoService.getUserInfo(userId);
            roomInfoService.getRoomInfoResByRoomIdx(roomIdx);
            roomUserList = userRoomInfoService.getRoomUserList(roomIdx);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }

        return new BaseResponse<>(BaseResponseStatus.SUCCESS,new GetRoomUserInfoListRes(roomUserList));


    }
}
