package ga.boozeeRunner.src.room;


import ga.boozeeRunner.config.BaseException;
import ga.boozeeRunner.config.BaseResponse;
import ga.boozeeRunner.config.BaseResponseStatus;
import ga.boozeeRunner.src.room.model.*;
import ga.boozeeRunner.src.user.UserInfoService;
import ga.boozeeRunner.src.user.models.UserInfo;
import ga.boozeeRunner.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final JwtService jwtService;
    private final RoomInfoService roomInfoService;
    private final UserInfoService userInfoService;

    /**
     * 스터디 등록
     * @param postRoomReq
     * @return
     */

    @PostMapping("/room")
    public BaseResponse<PostRoomRes>postRoom(@RequestBody PostRoomReq postRoomReq)  {

        Long userId;
        try {
            //방을 새로 만드는 사람
             userId = jwtService.getUserId();


        } catch (BaseException  exception) {
            return new BaseResponse<>(exception.getStatus());
        }
        if((postRoomReq.getRoomName().length()<0)||(postRoomReq.getRoomName().length()>10)||postRoomReq.getRoomName()==null){
            return new BaseResponse<>(BaseResponseStatus.INVALID_ROOM_NAME);
        }
        if(postRoomReq.getRoomType().length()<0||postRoomReq.getRoomType()==null){
            return new BaseResponse<>(BaseResponseStatus.INVALID_ROOM_NAME);
        }
        if(postRoomReq.getMeetNumber()==null){
            return new BaseResponse<>(BaseResponseStatus.INVALID_ROOM_NAME);
        }
        //오전 오후 유무
        if(postRoomReq.getIsMorning()==null||postRoomReq.getIsMorning().length()<0){
            return new BaseResponse<>(BaseResponseStatus.INVALID_ROOM_NAME);
        }
        //시작 시간
        if(postRoomReq.getStartDate()==null||postRoomReq.getStartDate().length()<0){
            return new BaseResponse<>(BaseResponseStatus.INVALID_ROOM_NAME);
        }
        //시작 시간
        if(postRoomReq.getBackGround()==null||postRoomReq.getBackGround().length()<=0){
            return new BaseResponse<>(BaseResponseStatus.INVALID_ROOM_NAME);
        }
        //여러번 만날때 종료기간 입력 x시
        if(postRoomReq.getMeetNumber()==2&&(postRoomReq.getEndDate()==null||postRoomReq.getEndDate().length()<=0)){

            return new BaseResponse<>(BaseResponseStatus.INVALID_ROOM_NAME);
        }
        //만나는 요일
        if(postRoomReq.getMeetDay()==null||postRoomReq.getMeetDay().length()<=0){

            return new BaseResponse<>(BaseResponseStatus.INVALID_ROOM_NAME);
        }



        PostRoomRes postRoomRes;
        try {
            System.out.println("createRoom");
            postRoomRes=roomInfoService.createRoom(userId,postRoomReq);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }




        return new BaseResponse<>(BaseResponseStatus.SUCCESS,postRoomRes);


    }
    /**
     * 방별 코드 조회
     */

    @GetMapping("/room/{roomIdx}/code")
    public BaseResponse<GetRoomCodeRes> getRoomCode(@PathVariable Long roomIdx){
        Long userId;
        GetRoomCodeRes getRoomCodeRes;
        try {
            //방을 새로 만드는 사람
            userId = jwtService.getUserId();
            UserInfo userInfo = userInfoService.getUserInfo(userId);
            getRoomCodeRes = roomInfoService.readRoomCode(roomIdx);


        } catch (BaseException  exception) {
            return new BaseResponse<>(exception.getStatus());
        }


        return new BaseResponse<>(BaseResponseStatus.SUCCESS,getRoomCodeRes);

    }

    /**
     * 방 정보 단건 조회
     */
    @GetMapping("/users/rooms/{roomIdx}")
    public BaseResponse<GetRoomInfoRes> getRoomInfo(@PathVariable Long roomIdx) throws BaseException {

        GetRoomInfoRes getRoomInfoRes;
        Long userId;
        try {
                userId = jwtService.getUserId();
             getRoomInfoRes = roomInfoService.getRoomInfoResByRoomIdx(roomIdx);
        }catch (BaseException e){
           return new BaseResponse<>(e.getStatus());
        }

        return new BaseResponse<>(BaseResponseStatus.SUCCESS,getRoomInfoRes);

    }

    /**
     * 스터디 삭제
     */
    @DeleteMapping("/rooms/{roomIdx}")
    public BaseResponse deleteRoomInfo(@PathVariable Long roomIdx) {

        Long userId;
        try {
            userId = jwtService.getUserId();
            RoomInfo roomInfo = roomInfoService.getRoomInfo(roomIdx);
            roomInfoService.deleteRoomInfo(roomIdx);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);

    }





}
