package sample.hotels;

import sample.people.Manager;

import java.util.List;

public class Hotel {
    private Manager manager;
    private List<Room> rooms;

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
