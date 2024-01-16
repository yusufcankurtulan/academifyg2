package com.example.academify.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/api/user-profiles")
public class UserProfileController {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @GetMapping
    public List<UserProfile> getAllUserProfiles() {
        return this.userProfileRepository.findAll();
    }

    @PostMapping
    public UserProfile createUserProfile(@RequestBody UserProfile userProfile) {
        return this.userProfileRepository.save(userProfile);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String resetCode, @RequestParam String newPassword) {
        Optional<UserProfile> user = userProfileRepository.findByEmail(email);
        if (user.isPresent() && user.get().getResetCode().equals(resetCode)) {
            user.get().setSifre(newPassword);
            user.get().setResetCode(null);
            userProfileRepository.save(user.get());
            return ResponseEntity.ok("Şifre başarıyla güncellendi.");
        } else {
            return ResponseEntity.badRequest().body("Geçersiz sıfırlama kodu veya kullanıcı.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody UserProfile user) {
        Map<String, Object> response = new HashMap<>();

        if (userProfileRepository.findByEmail(user.getEmail()).isPresent()) {
            response.put("success", false);
            response.put("message", "There is already an account with this email");
        } else {
            userProfileRepository.save(user);
            response.put("success", true);
            response.put("message", "Registration successful");
        }

        return ResponseEntity.ok(response);
    }

    // Yeni eklenen giriş yapma metodu
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody UserProfile loginUser) {
        Map<String, Object> response = new HashMap<>();
        Optional<UserProfile> user = userProfileRepository.findByEmail(loginUser.getEmail());

        if (user.isPresent() && user.get().getSifre().equals(loginUser.getSifre())) {
            response.put("success", true);
            response.put("message", "Giriş başarılı.");
            response.put("ad", user.get().getAd()); // Add name
            response.put("soyad", user.get().getSoyad()); // Add surname
            // Burada ek işlemler yapılabilir, örneğin kullanıcıya özel bir token döndürmek.
        } else {
            response.put("success", false);
            response.put("message", "E-posta veya şifre hatalı.");
        }

        return ResponseEntity.ok(response);
    }
}
