package ga.boozeeRunner.src.userRoom.model;

import ga.boozeeRunner.src.room.model.GetRoomUserInfoRes;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class GetRoomUserInfoListRes {
    List<GetRoomUserInfoRes> userList;
}
