package alexgr.oauth2.repo;

import alexgr.oauth2.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    UserEntity findUserEntityByUsername(String username);
}
