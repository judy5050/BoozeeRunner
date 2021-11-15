package ga.boozeeRunner.src.user;

import ga.boozeeRunner.utils.JwtService;
import ga.boozeeRunner.config.secret.Secret;
import ga.boozeeRunner.utils.AES128;
import ga.boozeeRunner.config.BaseException;
import ga.boozeeRunner.config.BaseResponseStatus;
import ga.boozeeRunner.src.user.models.*;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.regex.Pattern;


@Service
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final UserInfoProvider userInfoProvider;
    private final JwtService jwtService;

    @Autowired
    public UserInfoService(UserInfoRepository userInfoRepository, UserInfoProvider userInfoProvider, JwtService jwtService) {
        this.userInfoRepository = userInfoRepository;
        this.userInfoProvider = userInfoProvider;
        this.jwtService = jwtService;
    }

    /**
     * 회원가입
     * @param postUserReq
     * @return PostUserRes
     * @throws BaseException
     */
    public PostUserRes createUserInfo(@RequestBody PostUserReq postUserReq) throws BaseException {
        UserInfo existsUserInfo = null;
        // 1-1. 이미 존재하는 회원이 있는지 조회
        existsUserInfo = userInfoProvider.retrieveUserInfoByEmail(postUserReq.getEmail());

        // 1-3. 이미 존재하는 회원이 있다면 return DUPLICATED_USER
        if (existsUserInfo != null) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_USER);
        }

        // 2. 유저 정보 생성
        String email = postUserReq.getEmail();
        String nickname = postUserReq.getNickName();
        String phoneNumber = postUserReq.getPhoneNumber();
        String password;
//        existUserNickName();
        try {
            password = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(postUserReq.getPassword());
        } catch (Exception ignored) {
            throw new BaseException(BaseResponseStatus.FAILED_TO_POST_USER);
        }

        UserInfo userInfo = new UserInfo(email, password, nickname, phoneNumber);

        // 3. 유저 정보 저장
        try {
            userInfo = userInfoRepository.save(userInfo);
        } catch (Exception exception) {
            throw new BaseException(BaseResponseStatus.FAILED_TO_POST_USER);
        }

        // 4. JWT 생성
        String jwt = jwtService.createJwt(userInfo.getId());

        // 5. UserInfoLoginRes로 변환하여 return
        Long id = userInfo.getId();
        return new PostUserRes(id, jwt);
    }

//    /**
//     * 이메일 형식 확인
//     */
//    public void checkEmailStr(String email) throws BaseException {
//        String pattern1 = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
//        if(!Pattern.matches(pattern1,email)){
//            throw new BaseException(BaseResponseStatus.INVALID_EMAIL);
//        }
//    }
    /**
     * 유저 닉네임 중복 여부 확인하기
     */

    public void existUserNickName(String userNickName) throws BaseException {
        List<UserInfo> userInfoList = userInfoRepository.findByNickName(userNickName);
        if(userInfoList.size()>0){
            throw new BaseException(BaseResponseStatus.DUPLICATED_USER);
        }
    }

    /**
     * 회원 정보 수정 (POST uri 가 겹쳤을때의 예시 용도)
     * @param patchUserReq
     * @return PatchUserRes
     * @throws BaseException
     */
    public PatchUserRes updateUserInfo(@NonNull Integer userId, PatchUserReq patchUserReq) throws BaseException {
        try {
            String email = patchUserReq.getEmail().concat("_edited");
            String nickname = patchUserReq.getNickname().concat("_edited");
            String phoneNumber = patchUserReq.getPhoneNumber().concat("_edited");
            return new PatchUserRes(email, nickname, phoneNumber);
        } catch (Exception ignored) {
            throw new BaseException(BaseResponseStatus.FAILED_TO_PATCH_USER);
        }
    }

    /**
     * 회원 탈퇴
     * @param userId
     * @throws BaseException
     */
    public void deleteUserInfo(Long userId) throws BaseException {
        // 1. 존재하는 UserInfo가 있는지 확인 후 저장
        UserInfo userInfo = userInfoProvider.retrieveUserInfoByUserId(userId);

        // 2-1. 해당 UserInfo를 완전히 삭제
//        try {
//            userInfoRepository.delete(userInfo);
//        } catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR_USER_INFO);
//        }

        // 2-2. 해당 UserInfo의 status를 INACTIVE로 설정
        userInfo.setIsDeleted("Y");
        try {
            userInfoRepository.save(userInfo);
        } catch (Exception ignored) {
            throw new BaseException(BaseResponseStatus.FAILED_TO_DELETE_USER);
        }
    }



    public UserInfo getUserInfo(Long userIdx) throws BaseException {


        UserInfo userInfo = userInfoRepository.findById(userIdx).orElse(null);
        if(userInfo==null){
            throw new BaseException(BaseResponseStatus.NOT_FOUND_USER);
        }
        return userInfo;
    }
}