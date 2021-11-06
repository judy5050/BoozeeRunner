package ga.boozeeRunner.src.user.models;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC) // Unit Test 를 위해 PUBLIC
@Getter
public class PostUserReq {
    private String email;
    private String password;
    private String nickName;
    private String phoneNumber;
}
