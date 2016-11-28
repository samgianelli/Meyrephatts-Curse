package game.view;

import game.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MainMenuController {
    private MainApp mainApp;
    private Stage stage;

    public MainMenuController() {
    }

    @FXML
    private void initialize() {

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public void setStage(Stage mainStage) {
    	this.stage = mainStage;
    }
    
    @FXML
    private void handleNewGame() {
    	mainApp.showGameInterface(/*new Game()*/);
    }
    
    @FXML
    private void handleLoadGame() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Work in Progress");
        alert.setHeaderText("Work in Progress");
        alert.setContentText("This section is currently under construction.");

        alert.showAndWait();
    }
    
    @FXML
    private void handleOptions() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Work in Progress");
        alert.setHeaderText("Work in Progress");
        alert.setContentText("This section is currently under construction.");

        alert.showAndWait();
    }
    
    @FXML
    private void handleExit() {
    	stage.close();
    }
}
