package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import sample.entertainment.Entertainment;
import sample.entertainment.EntertainmentType;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Scenes/login.fxml"));
//        primaryStage.setTitle("Login");
//        Label usernameLabel = new Label("Username");
//        usernameLabel.setTranslateX(0);
//        usernameLabel.setTranslateY(-200);
//        Label passwordLabel = new Label("Password");
//        passwordLabel.setTranslateX(0);
//        passwordLabel.setTranslateY(-120);
//        TextField usernameTextField = new TextField();
//        usernameTextField.setPromptText("Username");
//        usernameTextField.getText();
//        usernameTextField.setMaxWidth(200);
//        usernameTextField.setTranslateX(0);
//        usernameTextField.setTranslateY(-160);
//        TextField passwordTextField = new TextField();
//        passwordTextField.setPromptText("Password");
//        passwordTextField.getText();
//        passwordTextField.setMaxWidth(200);
//        passwordTextField.setTranslateX(0);
//        passwordTextField.setTranslateY(-90);
//        Button loginButton = new Button();
//        loginButton.setText("Login");
//        loginButton.setTranslateX(-60);
//        loginButton.setTranslateY(0);
//        Button registerButton = new Button();
//        registerButton.setText("Register");
//        registerButton.setTranslateX(30);
//        registerButton.setTranslateY(0);
//        StackPane r = new StackPane();
//        r.getChildren().add(usernameLabel);
//        r.getChildren().add(passwordLabel);
//        r.getChildren().add(usernameTextField);
//        r.getChildren().add(passwordTextField);
//        r.getChildren().add(loginButton);
//        r.getChildren().add(registerButton);
//        Scene sc = new Scene(r, 595, 600);
//        primaryStage.setScene(sc);
//        primaryStage.show();
//        primaryStage.setTitle("Reservation");
//
//        Label reservationTypeLabel = new Label("Choose your room type");
//        reservationTypeLabel.setTranslateX(0);
//        reservationTypeLabel.setTranslateY(-250);
//        ObservableList<String> options =
//                FXCollections.observableArrayList(
//                        "Dvoina",
//                        "Prezidentska",
//                        "Studio"
//                );
//        final ComboBox comboBox = new ComboBox(options);
//        comboBox.setTranslateX(0);
//        comboBox.setTranslateY(-215);
//        Label startDateLabel = new Label("Choose start date");
//        startDateLabel.setTranslateX(0);
//        startDateLabel.setTranslateY(-150);
//        DatePicker startDatePicker = new DatePicker();
//        startDatePicker.setValue(LocalDate.now());
//        startDatePicker.setTranslateX(0);
//        startDatePicker.setTranslateY(-120);
//        Label daysCountLabel = new Label("Choose nights");
//        daysCountLabel.setTranslateX(0);
//        daysCountLabel.setTranslateY(-75);
//        TextField daysCountTextField = new TextField();
//        daysCountTextField.setPromptText("Count");
//        daysCountTextField.getText();
//        daysCountTextField.setMaxWidth(80);
//        daysCountTextField.setTranslateX(0);
//        daysCountTextField.setTranslateY(-45);
//        Button reservationButton = new Button();
//        reservationButton.setText("Reserve");
//        reservationButton.setTranslateX(0);
//        reservationButton.setTranslateY(10);
//        StackPane r = new StackPane();
//        r.getChildren().add(reservationTypeLabel);
//        r.getChildren().add(comboBox);
//        r.getChildren().add(startDateLabel);
//        r.getChildren().add(startDatePicker);
//        r.getChildren().add(daysCountLabel);
//        r.getChildren().add(daysCountTextField);
//        r.getChildren().add(reservationButton);
//        Scene sc = new Scene(root, 595, 600);
//        primaryStage.setScene(sc);
//        primaryStage.show();

        Scene scene = new Scene(root, 590, 600);

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();


        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            SessionFactory factory = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
            Session session = factory.openSession();
            Transaction transaction = session.beginTransaction();

            Entertainment entertainment = new Entertainment();
            entertainment.setPrice(5);
            entertainment.setType(EntertainmentType.BEACH_FOOTBALL);

            session.save(entertainment);

            transaction.commit();

            session.close();
            factory.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
