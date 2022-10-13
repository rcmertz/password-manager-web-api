package br.com.passmanager.Controller;

import br.com.passmanager.Entity.Password;
import br.com.passmanager.Service.PasswordService;
import br.com.passmanager.dto.PasswordResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
@RequestMapping("/api/password")
public class PasswordController {

    @Autowired
    PasswordService passwordService;

    public Password unencrypt(Password password){
        byte[] decoded = Base64.getDecoder().decode(password.getPassword());
        String decodedString = new String(decoded);
        password.setPassword(decodedString);
        return password;
    }
    @RequestMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(passwordService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Password>> getAllRequests(){
        return ResponseEntity.status(HttpStatus.OK).body(passwordService.findAll().stream().map(this::unencrypt).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Password password) {
        try {
            String original = password.getPassword();
            String encoded = Base64.getEncoder().encodeToString(original.getBytes());
            password.setPassword(encoded);
            Password pass = this.passwordService.insert(password);
            return ResponseEntity.ok().body(PasswordResponse.buildSuccess(pass));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(PasswordResponse.buildFailed(e.getMessage()));
        }
    }

    @PutMapping("/{idPassword}")
    public ResponseEntity<?> update(@PathVariable Long idPassword, @RequestBody Password password) {
        try {
            this.passwordService.update(idPassword, password);
            return ResponseEntity.ok().body("Senha atualizada com sucesso.");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(PasswordResponse.buildFailed(e.getMessage()));
        }
    }

    @DeleteMapping("/{idPassword}")
    public ResponseEntity<?> delete(@PathVariable Long idPassword) {
        try {
            this.passwordService.delete(idPassword);
            return ResponseEntity.ok().body("Senha excluida com sucesso.");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(PasswordResponse.buildFailed(e.getMessage()));
        }
    }
}
