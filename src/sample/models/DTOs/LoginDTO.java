package sample.models.DTOs;

public class LoginDTO {
    private String usernanme;
    private String password;

    public LoginDTO(String usernanme, String password) {
        this.usernanme = usernanme;
        this.password = password;
    }

    public LoginDTO(){};

    public String getUsernanme() {
        return usernanme;
    }

    public void setUsernanme(String usernanme) {
        this.usernanme = usernanme;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
