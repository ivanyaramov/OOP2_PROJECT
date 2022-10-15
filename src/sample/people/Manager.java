package sample.people;

import java.util.List;

public class Manager extends Person{
    private List<Receptionist> receptionists;
    public Manager() {
        super(Role.MANAGER);
    }
}
