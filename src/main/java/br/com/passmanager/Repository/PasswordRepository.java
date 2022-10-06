package br.com.passmanager.Repository;

import br.com.passmanager.Entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Integer> {

    @Modifying
    @Query("UPDATE Password password " +
            "SET password.ativo = false " +
            "WHERE password.id = :password")
    public void exlcuir(@Param("password") Long idPassword);


}
