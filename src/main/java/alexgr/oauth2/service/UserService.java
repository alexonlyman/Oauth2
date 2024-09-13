package alexgr.oauth2.service;

import alexgr.oauth2.model.UserEntity;
import alexgr.oauth2.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public UserEntity createUser(UserEntity user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());
        return userRepo.save(userEntity);
    }

    public List<UserEntity> userEntityList() {
        return userRepo.findAll();
    }
}
