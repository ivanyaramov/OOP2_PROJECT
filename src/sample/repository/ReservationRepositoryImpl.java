package sample.repository;

import sample.DBService.DatabaseService;

public class ReservationRepositoryImpl extends RepositoryImpl  implements ReservationRepository{
    private DatabaseService databaseService = new DatabaseService();

}
