package sample.models.viemModels;

public class PersonForChoosingViewModel {
    private String fullName;
    private String username;

    public PersonForChoosingViewModel() {
    }

    public PersonForChoosingViewModel(String fullName, String username) {
        this.fullName = fullName;
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
