package ga.boozeeRunner.src.user.models;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class PostUserNickNameReq {
    private String nickName;
}