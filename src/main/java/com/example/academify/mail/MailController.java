package com.example.academify.mail;

import com.example.academify.mail.MailService;
import com.example.academify.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    private UserService userService;

    @PostMapping("/send-verification-code")
    public ResponseEntity<String> sendVerificationCode(@RequestParam String userEmail) {
        String verificationCode = generateVerificationCode(); // Doğrulama kodu oluştur
        mailService.sendVerificationCode(userEmail, verificationCode); // E-posta gönder
        return ResponseEntity.ok("Doğrulama kodu e-posta adresinize gönderildi.");
    }

    // Doğrulama kodu oluşturma metodu (Rastgele 4 haneli kod oluşturabilirsiniz)
    private String generateVerificationCode() {
        Random random = new Random();
        int code = 1000 + random.nextInt(9000); // 1000 ile 9999 arasında rastgele bir kod oluşturur
        return String.valueOf(code);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String resetCode, @RequestParam String newPassword) {
        // Kullanıcının e-posta adresi ve sıfırlama kodu ile sıfırlama isteği gönder
        boolean isResetSuccessful = userService.resetUserPassword(email, resetCode, newPassword);

        if (isResetSuccessful) {
            return ResponseEntity.ok("Şifre başarıyla güncellendi.");
        } else {
            return ResponseEntity.badRequest().body("Geçersiz sıfırlama kodu veya kullanıcı.");
        }
    }
}