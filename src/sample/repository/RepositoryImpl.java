package sample.repository;

import sample.DBService.DatabaseService;

public abstract class RepositoryImpl implements Repository{
    DatabaseService databaseService = new DatabaseService();
    public void save(Object object){
        databaseService.saveObject(object);
    }
}
