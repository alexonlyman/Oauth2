package alexgr.oauth2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String password;
    private String email;
    private String username;
    @OneToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}
