package ga.boozeeRunner.src.room.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostRoomReq {

    private String roomName;
    private String roomType;
    private Integer meetNumber;
    private String isMorning;
    private String meetTime;
    private String startDate;
    private String endDate;
    private String backGround;
    private String isAlone;
    private String isOpen;
    private String isPhoto;
    private int pay;
    private String isPay;
    private Double latitude;
    private Double longitude;
    private String placeName;
    private String isWakeUpService;

}
