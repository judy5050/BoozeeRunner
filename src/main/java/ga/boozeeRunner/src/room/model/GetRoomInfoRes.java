package ga.boozeeRunner.src.room.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetRoomInfoRes {

    private String startDate;
    private String endDate;
    private Integer meetNumber;
    private String meetTime;
    private String roomType;
    private String roomName;
    private Integer perMinute;
    private Integer pay;
    private String isPay;
    private Double longitude;
    private Double latitude;

}
