package sample.models.people;

import javax.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, columnDefinition = "VARCHAR(30)")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false, columnDefinition = "VARCHAR(30)")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private double rating;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String telephone;

    public Person(Role role) {
        this.role = role;
    }

    public Person() {

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

    public Long getId() {
        return id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
