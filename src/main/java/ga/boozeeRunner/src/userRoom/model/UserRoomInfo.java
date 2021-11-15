package ga.boozeeRunner.src.userRoom.model;


import ga.boozeeRunner.src.room.model.RoomInfo;
import ga.boozeeRunner.src.user.models.UserInfo;
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
@Table(name = "UserRoomInfo") // Table 이름을 명시해주지 않으면 class 이름을 Table 이름으로 대체한다.
public class UserRoomInfo {

    @Id // PK를 의미하는 어노테이션
    @Column(name = "userRoomIdx", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRoomIdx;

//    @OneToMany(mappedBy = "userRoomInfo")
//    private List<RoomInfo> roomInfo=new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomIdx")
    private RoomInfo roomInfo;

//    @OneToMany(mappedBy = "userRoomInfo")
//    private List<UserInfo> userInfo=new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    private UserInfo userInfo;

    public UserRoomInfo(UserInfo userInfo, RoomInfo roomInfo) {
        this.userInfo=userInfo;
        this.roomInfo=roomInfo;

    }
}
