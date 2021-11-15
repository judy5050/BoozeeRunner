//package ga.boozeeRunner.src.code.model;
//
//
//import ga.boozeeRunner.src.room.model.RoomInfo;
//import lombok.AccessLevel;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@NoArgsConstructor(access = AccessLevel.PUBLIC) // Unit Test 를 위해 PUBLIC
//@EqualsAndHashCode(callSuper = false)
//@Data // from lombok
//@Entity // 필수, Class 를 Database Table화 해주는 것이다
//@Table(name = "CodeInfo") // Table 이름을 명시해주지 않으면 class 이름을 Table 이름으로 대체한다.
//public class CodeInfo {
//
//    @Id // PK를 의미하는 어노테이션
//    @Column(name = "codeIdx", nullable = false, updatable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToOne(mappedBy = "codeInfo")
//    private RoomInfo roomInfo;
//
//
//    @Column(name = "code")
//    private String code;
//
//}
