package ga.boozeeRunner.src.room;


import ga.boozeeRunner.config.BaseException;
import ga.boozeeRunner.config.BaseResponse;
import ga.boozeeRunner.config.BaseResponseStatus;
import ga.boozeeRunner.src.room.model.*;
import ga.boozeeRunner.src.user.UserInfoRepository;
import ga.boozeeRunner.src.user.models.UserInfo;
import ga.boozeeRunner.src.userRoom.UserRoomInfoRepository;
import ga.boozeeRunner.src.userRoom.model.UserRoomInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class RoomInfoService {

    private final RoomInfoRepository roomInfoRepository;
    private final UserInfoRepository userInfoRepository;
    private final UserRoomInfoRepository userRoomInfoRepository;

    /**
     * 방 만들기
     * @param roomUserIdx
     * @param postRoomReq
     */
    public PostRoomRes createRoom(Long roomUserIdx, PostRoomReq postRoomReq) throws BaseException {

        UserInfo userInfo = userInfoRepository.findById(roomUserIdx).orElse(null);
        if(userInfo==null||userInfo.getIsDeleted().equals("Y")){
            throw new BaseException(BaseResponseStatus.NOT_FOUND_USER);
        }
        System.out.println("created code");
        String code = createCode();
        System.out.println("code = " + code);
        RoomInfo roomInfo = new RoomInfo(userInfo, postRoomReq.getRoomName(), postRoomReq.getRoomType(), postRoomReq.getMeetNumber(), postRoomReq.getIsMorning(), postRoomReq.getMeetTime(), postRoomReq.getStartDate(), postRoomReq.getEndDate(),
                postRoomReq.getBackGround(),code,postRoomReq.getIsAlone(),postRoomReq.getIsOpen(),postRoomReq.getPlaceName(),postRoomReq.getIsPay(),postRoomReq.getPay(),postRoomReq.getIsPhoto(),postRoomReq.getIsWakeUpService(),postRoomReq.getLatitude(),postRoomReq.getLongitude());
        RoomInfo r = roomInfoRepository.save(roomInfo);
        UserRoomInfo userRoomInfo = new UserRoomInfo(userInfo, r);
        userRoomInfoRepository.save(userRoomInfo);
        System.out.println("code = " + code);
        return new PostRoomRes(r.getId(),r.getCode());


    }


    public String createCode(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        boolean check=false;
        String generatedString;
        do {
             generatedString = random.ints(leftLimit,rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            check = existCode(generatedString);
        }while (check==true);




        return generatedString;
    }

    public boolean existCode(String code){
        System.out.println("error");
        List<RoomInfo> roomInfoList = roomInfoRepository.findByCode(code, "N");
        if(roomInfoList.size()==0){
            return false;
        }else{
            return true;
        }

    }


    public GetRoomCodeRes readRoomCode(Long roomIdx) throws BaseException {
        RoomInfo roomInfo = roomInfoRepository.findById(roomIdx).orElse(null);
        if(roomInfo.getIsDeleted().equals("Y")||roomInfo==null){
            throw new BaseException(BaseResponseStatus.NOT_FOUND_ROOM);
        }
        return new GetRoomCodeRes(roomInfo.getCode());

    }

    public RoomInfo getRoomInfo(String code) throws BaseException {
        List<RoomInfo> roomInfoList = roomInfoRepository.findByCode(code, "N");
        if(roomInfoList.size()==0){
            throw new BaseException(BaseResponseStatus.NOT_FOUND_ROOM);
        }
        RoomInfo roomInfo = roomInfoList.get(0);
        return roomInfo;
    }

    /**
     *룸 정보 조회 단건
     * @param roomIdx
     */
    public GetRoomInfoRes getRoomInfoResByRoomIdx(Long roomIdx) throws BaseException {

        RoomInfo roomInfo;
        try {
             roomInfo = getRoomInfo(roomIdx);
        }catch (BaseException e){
            throw new BaseException(e.getStatus());
        }

        return new GetRoomInfoRes(roomInfo.getStartDate(), roomInfo.getEndDate(), roomInfo.getMeetNumber(), roomInfo.getMeetTime(), roomInfo.getRoomType(), roomInfo.getRoomName(), roomInfo.getPerMinute(), roomInfo.getPay(), roomInfo.getIsPay(), roomInfo.getLongitude(), roomInfo.getLatitude());

    }

    /**
     * 방 정보 조회
     * @param roomIdx
     * @return
     * @throws BaseException
     */
    public RoomInfo getRoomInfo(Long roomIdx) throws BaseException {
        RoomInfo roomInfo = roomInfoRepository.findById(roomIdx).orElse(null);
        if(roomInfo==null||roomInfo.getIsDeleted().equals("Y")){
            throw  new BaseException(BaseResponseStatus.NOT_FOUND_ROOM);
        }
        return roomInfo;
    }


    /**
     * 룸 정보 삭제 API
     * @param
     */
    public void deleteRoomInfo(Long roomIdx) {
        RoomInfo roomInfo = roomInfoRepository.findById(roomIdx).orElse(null);
        roomInfo.setIsDeleted("Y");
        //왜 save 해야하는거지? 알아보기
        roomInfoRepository.save(roomInfo);


    }
}
