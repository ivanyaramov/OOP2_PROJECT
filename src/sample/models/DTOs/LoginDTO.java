package sample.models.DTOs;

public class LoginDTO {
    private String useranme;
    private String password;

    public LoginDTO(String useranme, String password) {
        this.useranme = useranme;
        this.password = password;
    }

    public String getUseranme() {
        return useranme;
    }

    public void setUseranme(String useranme) {
        this.useranme = useranme;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
