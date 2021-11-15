package ga.boozeeRunner.src.confirmationHistory.model;


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
@Table(name = "ConfirmationHistory")
public class ConfirmationHistoryInfo extends BaseEntity {

    @Id
    @Column(name = "confirmationHistoryIdx", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomIdx")
    private RoomInfo roomInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    private UserInfo userInfo;

    @Column(name = "isPhoto")
    private String isPhoto;

    @Column(name = "isStatus")
    private String isStatus;

    @Column(name = "time")
    private String time;




}
