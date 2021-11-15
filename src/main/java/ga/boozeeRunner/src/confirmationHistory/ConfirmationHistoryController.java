package ga.boozeeRunner.src.confirmationHistory;


import ga.boozeeRunner.config.BaseException;
import ga.boozeeRunner.config.BaseResponse;
import ga.boozeeRunner.config.BaseResponseStatus;
import ga.boozeeRunner.src.room.RoomInfoService;
import ga.boozeeRunner.src.user.UserInfoService;
import ga.boozeeRunner.src.user.models.UserInfo;
import ga.boozeeRunner.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConfirmationHistoryController {

    private final JwtService jwtService;
    private final UserInfoService userInfoService;
    private  final RoomInfoService roomInfoService;
    /**
     * 인증하기
     */
    @PostMapping("/rooms/{roomIdx}/confirmation")
    public BaseResponse createConfirmation(@PathVariable Long roomIdx){

        Long userId;
        try {
            userId = jwtService.getUserId();
            UserInfo userInfo = userInfoService.getUserInfo(userId);
            roomInfoService.getRoomInfoResByRoomIdx(roomIdx);


        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }


        return new BaseResponse(BaseResponseStatus.SUCCESS);
    }


}
