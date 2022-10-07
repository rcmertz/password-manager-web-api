package br.com.passmanager.dto;


import br.com.passmanager.Entity.Password;
import lombok.Getter;

@Getter
public class PasswordResponse {

    private final Long idPassword;
    private final String message;
    private final Boolean ok;
    private PasswordResponse(Password password, boolean ok, String msg) {
        this.idPassword = password.getId();
        this.message = msg;
        this.ok = ok;
    }

    public static PasswordResponse buildSuccess(Password password){
        return new PasswordResponse(password, true, "Senha cadastrada com sucesso");
    }

    public static PasswordResponse buildFailed(String msg){
        return new PasswordResponse(null, false, "Nao foi possivel cadastrar a senha. " + msg);
    }
}
