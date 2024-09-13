package alexgr.oauth2.controller;

import alexgr.oauth2.model.UserEntity;
import alexgr.oauth2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<?> user(@AuthenticationPrincipal OAuth2User principal) {
        Map<String, Object> userInfo = new HashMap<>();
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "User not authenticated"));
        }
        userInfo.put("name", principal.getAttribute("login"));
        return ResponseEntity.ok(userInfo);
    }

    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createEmployee(@RequestBody UserEntity user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/manager")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<UserEntity>> findAll() {
        return ResponseEntity.ok(userService.userEntityList());
    }
}
