package ga.boozeeRunner.src.alarm.model;


import ga.boozeeRunner.config.BaseEntity;
import ga.boozeeRunner.src.room.model.RoomInfo;
import ga.boozeeRunner.src.user.models.UserInfo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC) // Unit Test 를 위해 PUBLIC
@EqualsAndHashCode(callSuper = false)
@Data // from lombok
@Entity
@Table(name = "AlarmInfo")
public class AlarmInfo extends BaseEntity {

    @Id
    @Column(name = "alarmIdx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //알람 들어온 인덱스
    @Column(name = "userIdx")
    private Long userIdx;

    //알람 메시지
    @Column(name = "msg")
    private String msg;

    //메시지 확인 유무
    @Column(name = "isCheck")
    private String isCheck="N";

    //가입 요청인가?
    @Column(name = "isJoin")
    private String isJoin;

    //수락유무
    @Column(name = "isAllow")
    private String isAllow="D";

    //혼자목표에 대한 알람인지 (필터링용)
    @Column(name = "isAlone")
    private String isAlone;

    @Column(name = "senderIdx")
    private Long senderIdx;

    @Column(name = "roomIdx")
    private Long roomIdx;


    /**
     * 알랆 생성(신청)
     * @param userInfo
     * @param roomInfo
     */
    public AlarmInfo(UserInfo userInfo, RoomInfo roomInfo,String isJoin,String isAlone) {
        this.userIdx=roomInfo.getUserInfo().getId();
        this.msg=userInfo.getNickName()+"가 "+roomInfo.getRoomName()+"에 가입요청을 보냈습니다.";
        this.isJoin=isJoin;
//        this.isAllow="D";
        this.isAlone=isAlone;
        this.senderIdx=userInfo.getId();
        this.roomIdx=roomInfo.getId();

    }
}
