package ga.boozeeRunner.src.alarm;


import ga.boozeeRunner.config.BaseException;
import ga.boozeeRunner.config.BaseResponse;
import ga.boozeeRunner.config.BaseResponseStatus;
import ga.boozeeRunner.src.alarm.model.AlarmInfo;
import ga.boozeeRunner.src.alarm.model.PostRoomJoinReq;
import ga.boozeeRunner.src.alarm.model.PostRoomJoinRes;
import ga.boozeeRunner.src.room.model.PostRoomReq;
import ga.boozeeRunner.src.userRoom.UserRoomInfoService;
import ga.boozeeRunner.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AlarmInfoController {

    private final UserRoomInfoService userRoomInfoService;
    private final AlarmInfoService alarmInfoService;
    private final JwtService jwtService;
    /**
     * 방 신청 수락 or 거절
     * @param alarmIdx
     */
    @PostMapping("/alarms/{alarmIdx}")
    public BaseResponse<PostRoomJoinRes> permitRoom(@PathVariable Long alarmIdx, @RequestBody PostRoomJoinReq postRoomJoinReq){

        AlarmInfo alarmInfo;
        try {
            jwtService.getUserId();
            alarmInfo=alarmInfoService.permitRoom(alarmIdx,postRoomJoinReq);
        }catch (BaseException e){
            return new BaseResponse(e.getStatus());
        }

        return new BaseResponse(BaseResponseStatus.SUCCESS,new PostRoomJoinRes(alarmInfo.getIsCheck()));

    }


}
