package sample.models.viemModels;

import sample.models.people.Role;

public class PersonWithRoleViewModel {
    private String username;
    private Role role;

    public PersonWithRoleViewModel() {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
