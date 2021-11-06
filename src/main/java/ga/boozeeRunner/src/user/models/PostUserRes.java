package ga.boozeeRunner.src.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostUserRes {
    private Long userId;
    private final String jwt;
}
