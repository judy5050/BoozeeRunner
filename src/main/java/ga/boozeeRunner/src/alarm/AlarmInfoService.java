package ga.boozeeRunner.src.alarm;


import ga.boozeeRunner.config.BaseException;
import ga.boozeeRunner.config.BaseResponseStatus;
import ga.boozeeRunner.src.alarm.model.AlarmInfo;
import ga.boozeeRunner.src.alarm.model.PostRoomJoinReq;
import ga.boozeeRunner.src.room.model.RoomInfo;
import ga.boozeeRunner.src.user.models.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlarmInfoService {

    private final AlarmInfoRepository alarmInfoRepository;

    /**
     * 방장에게 방 신청보내기
     * @param userInfo
     * @param roomInfo
     */

    @Transactional
    public AlarmInfo createdRoomJoinAlarm(UserInfo userInfo, RoomInfo roomInfo) throws BaseException {
        existRoomJoinAlarm(userInfo,roomInfo);
        AlarmInfo alarmInfo = alarmInfoRepository.save(new AlarmInfo(userInfo, roomInfo,"Y","N"));
        return alarmInfo;
    }

    /**
     * 이미 신청을 보낸 코드인지 확인하기
     */
    public void existRoomJoinAlarm(UserInfo userInfo,RoomInfo roomInfo) throws BaseException {
        AlarmInfo alarmInfo = alarmInfoRepository.findBySenderIdxAndRoomIdx(userInfo.getId(), roomInfo.getId());
        if(alarmInfo!=null){
            throw new BaseException(BaseResponseStatus.ALREADY_ALARM_ROOM_CODE);
        }

    }

    /**
     * 방 신청 허가 or 거절
     * @param alarmIdx
     */
    @Transactional
    public AlarmInfo permitRoom(Long alarmIdx, PostRoomJoinReq postRoomJoinReq) throws BaseException {
        AlarmInfo alarmInfo = alarmInfoRepository.findById(alarmIdx).orElse(null);
        //해당 알람이 존재하지 않을때
        if(alarmInfo==null||alarmInfo.getIsDeleted().equals("Y")||alarmInfo.getIsJoin().equals("N")){
            throw new BaseException(BaseResponseStatus.NOT_FOUND_ALARM);
        }
        //해당 요청을 수락하거나 거절했을 때
        if(alarmInfo.getIsAllow().equals("N")||alarmInfo.getIsAllow().equals("Y")){
            throw new BaseException(BaseResponseStatus.ALREADY_JOIN_OR_REJECT);
        }
        alarmInfo.setIsAllow(postRoomJoinReq.getIsAllow());
        alarmInfo.setIsCheck("Y");
        return alarmInfo;
//        alarmInfoRepository.save(alarmInfo);
    }
}
