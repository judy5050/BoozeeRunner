package ga.boozeeRunner.src.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserRes {
    private  Long userId;
    private final String email;
    private final String nickname;
    private final String phoneNumber;
}