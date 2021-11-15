package ga.boozeeRunner.src.room.model;

import ga.boozeeRunner.src.userRoom.model.UserRoomInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class GetRoomUserInfoRes {

    private String nickName;
    private String msg;
    private String profile;
    private String phoneNumber;

    public GetRoomUserInfoRes(UserRoomInfo u){
        this.nickName=u.getUserInfo().getNickName();
        this.msg=u.getUserInfo().getMsg();
        this.profile=u.getUserInfo().getProfile();
        this.phoneNumber=u.getUserInfo().getPhoneNumber();
    }


}
