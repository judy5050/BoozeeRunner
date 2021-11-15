package ga.boozeeRunner.src.room.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetRoomInfoListRes {

    private String roomName;
    private String roomType;
    private String meetDay;
    private String meetTime;
    private Integer pay;

}
