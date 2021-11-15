package ga.boozeeRunner.src.userRoom.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class GetUserRoomListRes {
    private  Long memberCount;
    private String roomType;
    private String roomName;
    private String meetDay;
    private String meetTime;
    private String isPay;
    private Integer pay;
    private String isAlone;
    private Long roomIdx;

    public GetUserRoomListRes(Long memberCount,String roomName,String roomType,String meetDay,String meetTime,String isPay,Integer pay,String isAlone,Long roomIdx){
        this.memberCount=memberCount;
        this.roomName=roomName;
        this.roomType=roomType;
        this.meetDay=meetDay;
        this.meetTime=meetTime;
        this.isPay=isPay;
        this.pay=pay;
        this.isAlone=isAlone;
        this.roomIdx=roomIdx;
    }

}
