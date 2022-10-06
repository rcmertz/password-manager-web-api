package br.com.passmanager.Service;

import br.com.passmanager.Entity.Password;
import br.com.passmanager.Repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PasswordService {

    @Autowired
    PasswordRepository passwordRepository;

    public List<Password> findAll(){
        return passwordRepository.findAll();
    }

    @Transactional
    public void insert(Password password) {

        this.passwordRepository.save(password);
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
    public void excluir(Long id, Password password) {
        if (id == password.getId()) {
            this.passwordRepository.exlcuir(password.getId());
        } else {
            throw new RuntimeException();
        }
    }

}