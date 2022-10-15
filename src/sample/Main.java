package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        String jdbcUrl = "jdbc:postgresql://localhost:5432/OOP4";
        String user = "postgres";
        String pass = "admin";
        try {
            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
            System.out.println("success");
        }catch (Exception e){
            System.out.println("maika ti");
        }

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
