package sample.currentLogin;

import sample.models.people.Person;

public class CurrentLoggedUser {
    private static Person loggedUser;

    public static boolean hasLoggedInUser(){
        return loggedUser != null;
    }

    public static void logout(){
        loggedUser = null;
    }

    public static void login(Person person){
        if(!hasLoggedInUser()) {
            loggedUser = person;
        }
    }
}
