package game.view;

import game.Game;
import game.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InGameMenuController {
    private MainApp mainApp;
    private Game currGame;
    
    public InGameMenuController() {
    	
    }

    @FXML
    private void initialize() {
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public void setCurrGame(Game game) {
    	this.currGame = game;
    }
    
    @FXML
    private void handleResume() {
    	mainApp.showGameInterface(currGame); // TODO: Make sure game resumes from latest state!
    }
    
    // TODO
    @FXML
    private void handleSave() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Work in Progress");
        alert.setHeaderText("Work in Progress");
        alert.setContentText("This section is currently under construction.\nGame was not saved.");

        alert.showAndWait();
    }
    
    // TODO
    @FXML
    private void handleSaveExit() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Work in Progress");
        alert.setHeaderText("Work in Progress");
        alert.setContentText("This section is currently under construction.\nGame was not saved.");

        alert.showAndWait();
        
        mainApp.showMainMenu();
    }
}