package sample.models.DTOs;

import sample.models.people.Gender;
import sample.models.people.Role;

public class RegisterDTO {
    private Role role;
    private String fullName;
    private Gender gender;
    private String username;
    private String password;
    private String passwordRepeat;
    private String telephone;

    public RegisterDTO(Role role, String fullName, Gender gender, String username, String password, String passwordRepeat, String telephone) {
        this.role = role;
        this.fullName = fullName;
        this.gender = gender;
        this.username = username;
        this.password = password;
        this.passwordRepeat = passwordRepeat;
        this.telephone = telephone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
}
