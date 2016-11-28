package game.view;

import game.Game;
import game.GameConstants;
import game.MainApp;
import game.entities.characters.Player;
import game.entities.objects.Item;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

public class GameInterfaceController {
    // Reference to the main application.
    private MainApp mainApp;
    private Game gameSession;
    
 // Player panel
    @FXML
    private Label playerName;
    @FXML
    private Label playerLevel;
    @FXML
    private Label playerExperience;
    @FXML
    private ProgressBar playerXpBar;
    @FXML
    private Label playerHealth;
    @FXML
    private ProgressBar playerHpBar;
    @FXML
    private Label playerMana;
    @FXML
    private ProgressBar playerMpBar;
    @FXML
    private Label playerAttack;
    @FXML
    private Label playerDefense;
    @FXML
    private ImageView playerAvatar;
    
    // Inventory panel
    @FXML
    private TilePane inventory;
    @FXML
    private TextArea itemInfo;
    // Internal
    private String[] itemInfos;
    
    // Encounter panel
    @FXML
    private Label monsterName1;
    @FXML
    private Label monsterLevel1;
    @FXML
    private Label monsterHealth1;
    @FXML
    private ProgressBar monsterHpBar1;
    @FXML
    private ImageView monsterAvatar1;
    @FXML
    private Label monsterName2;
    @FXML
    private Label monsterLevel2;
    @FXML
    private Label monsterHealth2;
    @FXML
    private ProgressBar monsterHpBar2;
    @FXML
    private ImageView monsterAvatar2;
    
    // Activity panel
    @FXML
    private TextArea consoleOut;
    @FXML
    private TextField consoleIn;
    
    // Map panel
    @FXML
    private ImageView map;

    public GameInterfaceController() {
    	itemInfos = new String[GameConstants.MaxInventory];
    	gameSession = new Game();
    }

    @FXML
    private void initialize() {
    	initConsole();
    	initInventory();
    	
    	updateGUI();
    }
    
	// Initialize embedded console
    private void initConsole() {
    	consoleOut.setEditable(false);
    	
    	// Console input listener
    	consoleIn.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
    		switch (keyEvent.getCode()) {
            case ENTER:
                String text = consoleIn.getText();
                //consoleOut.appendText(text + System.lineSeparator());
                handleConsoleInput(text);
                consoleIn.clear();
                break;
            default:
                break;
    		}
    	});
    	
    	// Console output autoscroll
    	consoleOut.textProperty().addListener( (observable, oldValue, newValue) -> 
            						consoleOut.setScrollTop(Double.MAX_VALUE) );
    }
    
    // Initialize inventory and listeners
    private void initInventory() {
    	// Test
    	for (int i = 0; i != GameConstants.MaxInventory; ++i) {
    		final int index = i;
    		// Add event listeners to provide mouseover tooltips
    		inventory.getChildren().get(i).addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
    		     @Override
    		     public void handle(MouseEvent event) {
    		         itemInfo.setText(itemInfos[index]);
    		         event.consume();
    		     }
    		});
    	}
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
//    TODO
    public void setGame(Game game) {
    	this.gameSession = game;
    	
    	updateGUI();
    }
    
    // TODO
    public void updateGUI() {
    	updatePlayerStats(gameSession.getPlayer());
    	updateInventory(gameSession.getPlayer());
    	updateInstance(/*game.getInstance()*/);
    	updateConsole();
    	updateMap(/* TODO */);
    }
    
    private void updatePlayerStats(Player player) {
        if (player != null) {
            playerName.setText(player.getName());
            playerLevel.setText(player.getCurrentStats().getLevel().toString());
            playerExperience.setText(formatFraction(player.getExperience(), 100));
            playerXpBar.setProgress(player.getExperience() / 100.0); //TODO
            playerHealth.setText(formatFraction(player.getHealth(), player.getCurrentStats().getMaxHealth()));
            playerHpBar.setProgress(((double) player.getHealth()) / player.getBaseStats().getMaxHealth());
            playerMana.setText("N/A"); //TODO
            playerMpBar.setDisable(true); //TODO
            playerAttack.setText(player.getCurrentStats().getAttack().toString());
            playerDefense.setText(player.getCurrentStats().getDefense().toString());
            playerAvatar.setImage(new Image("res/player/avatar.png")); //TODO
        } else {
        	// Player is null
            playerName.setText("null");
            playerLevel.setText("null");
            playerExperience.setText("null");
            playerXpBar.setDisable(true);
            playerHealth.setText("null");
            playerHpBar.setDisable(true);
            playerMana.setText("null");
            playerMpBar.setDisable(true);
            playerAttack.setText("null");
            playerDefense.setText("null");
            playerAvatar.setImage(null); //TODO
        }
    }
    
    // TODO
    private void updateInventory(Player player) {
    	int i = 0;
    	if (player != null) {
	    	for (; i < player.getInventory().size(); ++i) {
	         	Item item = player.getInventory().get(i);
	         	
	         	itemInfos[i] = "Item: " + item.getName() + System.lineSeparator();
	         	// Update item tooltip
	         	if (item.getEffect() == null) {
	         		itemInfos[i] += "This item is thoroughly regular. (No special effect)";
	         	}
	         	else {
	         		itemInfos[i] += item.getEffect().getDescription();
	         	}
	         	
	         	// Update item icon in inventory
	         	ImageView itemImage = (ImageView) inventory.getChildren().get(i);
	         	//itemImage.setImage(new Image(item.getIconURL())); // TODO
	         	itemImage.setImage(new Image("res/items/seal.jpg")); // Testing Only
	    	}
    	}
    	// Clear empty inventory slots
    	for (; i < GameConstants.MaxInventory; ++i) {
    		itemInfos[i] = "";
    		
    		ImageView itemImage = (ImageView) inventory.getChildren().get(i);
         	itemImage.setImage(null);
    	}
    }
    
    // TODO
    private void updateInstance(/* Instance instance */) {
		// Testing example monster
		monsterName1.setText("Testing A");
		monsterLevel1.setText("Lvl 999");
		monsterHealth1.setText(formatFraction(999, 2153));
		monsterHpBar1.setProgress(999.0 / 2153);
		monsterAvatar1.setImage(new Image("res/monsters/dur.png"));
		
		monsterName2.setText("Testing B");
		monsterLevel2.setText("Lvl 2");
		monsterHealth2.setText("N/A");
		monsterHpBar2.setProgress(0.0);
		monsterAvatar2.setImage(new Image("res/monsters/dur.png"));
    }
    
    // TODO
    private void updateConsole() {
        consoleOut.setText(gameSession.consoleOutput);
        consoleOut.appendText(""); // Force update
    }
    
    // TODO
    private void updateMap(/* TODO */) {
    	map.setRotate(map.getRotate() + 5);
    }
    
    @FXML
    private void handleMenu() {
    	mainApp.showInGameMenu(gameSession);
    }
    
    private boolean handleConsoleInput(String input) {
    	switch (input) {
    	case "thunderbolt":
    		printlnToConsole("You shout \"THUNDERBOLT!\" like a true LARPer. Good on you.");
    		updateGUI(); // This is where turn handling would happen
    		break;
    	case "":
    		printlnToConsole("");
    		updateGUI();
    		break;
    	default:
    		printlnToConsole("Unhandled input: " + input);
    		printlnToConsole("Due to the nature of testing, this action has cost you a turn.");
    		updateGUI();
    	}
    	
    	return true;
    }
    
    private void printlnToConsole(String string) {
    	gameSession.consoleOutput += string + System.lineSeparator();
    	//consoleOut.appendText(string + System.lineSeparator());
    }
    
    // Utility functions
    static private String formatFraction(Integer num, Integer den) {
    	return num.toString() + "/" + den.toString();
    }
}
