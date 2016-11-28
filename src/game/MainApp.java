package game;

import java.io.IOException;

import game.view.GameInterfaceController;
import game.view.InGameMenuController;
import game.view.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    //private ObservableList<Person> personData = FXCollections.observableArrayList();

    public MainApp() {
    }


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Meyrephaett\'s Curse");

        initRootLayout();

        showMainMenu();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the main menu inside the root layout.
     */
    public void showMainMenu() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MainMenu.fxml"));
            AnchorPane mainMenu = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(mainMenu);

            // Give the controller access to the main app.
            MainMenuController controller = loader.getController();
            controller.setStage(primaryStage);
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Begins game. TODO: Implement loading from game or new game
     * 
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public void showGameInterface(/*Game game*/) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/GameInterface.fxml"));
            AnchorPane gameInterface = (AnchorPane) loader.load();

            // Create the dialog Stage.
            rootLayout.setCenter(gameInterface);
            
            // Give the controller access
            GameInterfaceController controller = loader.getController();
            //controller.setGame(game);
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Shows the main menu inside the root layout.
     */
    public void showInGameMenu() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/InGameMenu.fxml"));
            AnchorPane inGameMenu = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(inGameMenu);

            // Give the controller access to the main app.
            InGameMenuController controller = loader.getController();
//            controller.setStage(primaryStage);
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    // Temp, make Eclipse shut up
    public class Game {
    }
}