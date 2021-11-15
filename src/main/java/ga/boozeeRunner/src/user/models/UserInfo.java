package ga.boozeeRunner.src.user.models;

import ga.boozeeRunner.config.BaseEntity;
import ga.boozeeRunner.src.room.model.RoomInfo;
import ga.boozeeRunner.src.userRoom.model.UserRoomInfo;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC) // Unit Test 를 위해 PUBLIC
@EqualsAndHashCode(callSuper = false)
@Data // from lombok
@Entity // 필수, Class 를 Database Table화 해주는 것이다
@Table(name = "UserInfo") // Table 이름을 명시해주지 않으면 class 이름을 Table 이름으로 대체한다.
public class UserInfo extends BaseEntity {
    /**
     * 유저 ID
     */
    @Id // PK를 의미하는 어노테이션
    @Column(name = "userIdx", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 이메일
     */
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    /**
     * 비밀번호
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * 닉네임
     */
    @Column(name = "nickName", nullable = false, length = 7)
    private String nickName;

    /**
     * 전화번호
     */
    @Column(name = "phoneNumber", length = 30)
    private String phoneNumber;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userRoomIdx")
//    private UserRoomInfo userRoomInfo;

    @OneToMany(mappedBy = "userInfo")
    private List<UserRoomInfo> userRoomInfoList=new ArrayList<>();

    @OneToMany(mappedBy = "userInfo",fetch = FetchType.LAZY)
    private List<RoomInfo> roomInfo=new ArrayList<>();

    @Column(name = "profile")
    private String profile;

    @Column(name = "msg")
    private String msg;

    public UserInfo(String email, String password, String nickname, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.nickName = nickname;
        this.phoneNumber = phoneNumber;
    }
}
