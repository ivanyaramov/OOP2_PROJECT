package sample.people;

public abstract class Person {
    private String id;
    private Role role;
    private String name;

    public Person(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

}
