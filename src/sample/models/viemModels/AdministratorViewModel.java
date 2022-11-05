package sample.models.viemModels;

import javafx.beans.property.SimpleStringProperty;

public class AdministratorViewModel {
    private SimpleStringProperty username;
    private SimpleStringProperty role;

    public AdministratorViewModel(SimpleStringProperty username, SimpleStringProperty role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getRole() {
        return role.get();
    }

    public SimpleStringProperty roleProperty() {
        return role;
    }

    public void setRole(String role) {
        this.role.set(role);
    }
}
