package sample.models.viemModels;

import javafx.beans.property.SimpleStringProperty;

public class CreateHotelViewModel {
    private SimpleStringProperty username;
    private SimpleStringProperty fullname;

    public CreateHotelViewModel(SimpleStringProperty username, SimpleStringProperty fullname) {
        this.username = username;
        this.fullname = fullname;
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

    public String getFullname() {
        return fullname.get();
    }

    public SimpleStringProperty fullnameProperty() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname.set(fullname);
    }
}
