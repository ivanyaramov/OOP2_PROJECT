package sample.repository;

import sample.DBService.DatabaseService;

public class RepositoryImpl implements Repository{
    private DatabaseService databaseService = new DatabaseService();
    @Override
    public void save(Object object) {
        databaseService.saveObject(object);
    }
}
