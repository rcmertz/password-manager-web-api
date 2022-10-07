package br.com.passmanager.Service;

import br.com.passmanager.Entity.Password;
import br.com.passmanager.Repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PasswordService {

    @Autowired
    PasswordRepository passwordRepository;

    public List<Password> findAll(){
        return passwordRepository.findAll();
    }

    @Transactional
    public Password insert(Password password) {
        return this.passwordRepository.save(password);
    }

    @Transactional
    public void update(Long id, Password password) {
        if (id == password.getId()){
            this.passwordRepository.save(password);
        } else {
            throw new RuntimeException();
        }
    }
    @Transactional
    public void delete(Long id) {
        Optional<Password> passw = passwordRepository.findById(id);
        if (passw.isPresent()){
            this.passwordRepository.delete(passw.get());
        } else {
            throw new RuntimeException("Senha nao encrotada");
        }
    }


}