package ga.boozeeRunner.src.room.model;


import ga.boozeeRunner.config.BaseEntity;
import ga.boozeeRunner.src.user.models.UserInfo;
import ga.boozeeRunner.src.userRoom.model.UserRoomInfo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC) // Unit Test 를 위해 PUBLIC
@EqualsAndHashCode(callSuper = false)
@Data // from lombok
@Entity // 필수, Class 를 Database Table화 해주는 것이다
@Table(name = "RoomInfo") // Table 이름을 명시해주지 않으면 class 이름을 Table 이름으로 대체한다.
public class RoomInfo extends BaseEntity {

    @Id // PK를 의미하는 어노테이션
    @Column(name = "roomIdx", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "roomName",nullable = false)
    private String roomName;

    @Column(name = "roomType",nullable = false)
    private String roomType;

    @Column(name = "code",nullable = false)
    private String code;

    @Column(name = "meetNumber",nullable = false)
    private int meetNumber;

    @Column(name = "isMorning",nullable = false)
    private String isMorning;

    @Column(name = "meetTime")
    private String meetTime;

    @Column(name = "startDate",nullable = false)
    private String startDate;

    @Column(name = "endDate")
    private String endDate;

    @Column(name = "pay")
    private int pay;

    @Column(name = "isPay")
    private String isPay;

    @Column(name = "isWakeUpService")
    private String isWakeUpService;

    @Column(name = "isOpen")
    private String isOpen;

    @Column(name = "backGround")
    private String backGround;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "placeName")
    private String placeName;

    @Column(name = "isAlone")
    private String isAlone;

    @OneToMany(mappedBy = "roomInfo")
    private List<UserRoomInfo> userRoomInfoList=new ArrayList<>();

    @Column(name = "isPhoto")
    private String isPhoto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    private UserInfo userInfo;

    @Column(name = "memberCount")
    private Long memberCount=0L;

    @Column(name="meetDay")
    private String meetDay;

    @Column(name = "perMinute")
    private int perMinute;


    public RoomInfo(UserInfo userInfo, String roomName, String roomType, Integer meetNumber, String isMorning, String meetTime, String startDate,
                    String endDate,String backGround,String code,String isAlone,String isOpen,
                    String placeName,String isPay,int pay,String  isPhoto,String isWakeUpService,Double latitude,Double longitude) {
        this.userInfo=userInfo;
        this.roomName=roomName;
        this.roomType=roomType;
        this.meetNumber=meetNumber;
        this.isMorning=isMorning;
        this.meetTime=meetTime;
        this.startDate=startDate;
        this.endDate=endDate;
        this.backGround=backGround;
        this.code=code;
        this.isAlone=isAlone;
        this.isOpen=isOpen;
        this.isPay=isPay;
        this.placeName=placeName;
        this.pay=pay;
        this.isPhoto=isPhoto;
        this.isWakeUpService=isWakeUpService;
        this.latitude=latitude;
        this.longitude=longitude;

    }
}
