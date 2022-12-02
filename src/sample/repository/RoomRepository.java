package sample.repository;

public interface RoomRepository extends Repository {
    boolean roomNumberExists(int number);
}
