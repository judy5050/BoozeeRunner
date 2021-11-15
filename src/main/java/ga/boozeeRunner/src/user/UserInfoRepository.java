package ga.boozeeRunner.src.user;

import ga.boozeeRunner.src.user.models.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // => JPA => Hibernate => ORM => Database 객체지향으로 접근하게 해주는 도구이다
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {
//    List<UserInfo> findByStatus(String status);
    List<UserInfo> findByEmailAndIsDeleted(String email, String status);

    List<UserInfo> findByNickName(String userNickName);

//    List<UserInfo> findByStatusAndNickNameIsContaining(String status, String word);
}