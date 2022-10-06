package br.com.passmanager.Controller;

import br.com.passmanager.Entity.Password;
import br.com.passmanager.Service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/password")
public class PasswordController {

    @Autowired
    PasswordService passwordService;

    @GetMapping
    public ResponseEntity<List<Password>> getAllRequests(){
        return ResponseEntity.status(HttpStatus.OK).body(passwordService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Password password) {
        try {
            this.passwordService.insert(password);
            return ResponseEntity.ok().body("Senha cadastrada com sucesso.");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{idPassword}")
    public ResponseEntity<?> update(@PathVariable Long idPassword, @RequestBody Password password) {
        try {
            this.passwordService.update(idPassword, password);
            return ResponseEntity.ok().body("Senha atualizada com sucesso.");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/excluir/{idPassword}")
    public ResponseEntity<?> excluir(@PathVariable Long idPassword, @RequestBody Password password) {
        try {
            this.passwordService.excluir(idPassword, password);
            return ResponseEntity.ok().body("Senha excluida com sucesso.");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
