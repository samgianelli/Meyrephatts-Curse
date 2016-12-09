package game;

import java.util.Iterator;

import game.entities.characters.Enemy;
import game.entities.characters.Player;
import game.entities.objects.Item;
import game.org.mapInfo.Room;
import javafx.animation.PauseTransition;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class GameGUI extends AnchorPane{

	
	private Player player;
	
	private Label playerName = new Label();
	private Label playerLevel = new Label();
	private Label playerAttack = new Label();
	private Label playerDefense = new Label();
	private Label playerXP = new Label(); //label saying the actual player's experience 
	private Label playerHealth = new Label();
	private Label playerMana = new Label();
	private ProgressBar expBar = new ProgressBar();
	private ProgressBar hpBar = new ProgressBar(); //player's health bar
	private ProgressBar manaBar = new ProgressBar();
	private ImageView mapImage = new ImageView(); //holds the image of the map and player's current position
	private Enemy monster1 = null;
	private Enemy monster2 = null;
	private AnchorPane monsterHouse = new AnchorPane(); //hold monsters
	private AnchorPane inventoryContainer = new AnchorPane();//hold inventory
	private TextArea consoleOut = new TextArea();
	

	
	public GameGUI(Player player, boolean hasSaveFile)
	{
		super(); //call anchorpane constructor
		this.player = player;		
		Room currentLocation = player.getLocation(); //<<<Check this for debugging

		
		SplitPane menuDivider = new SplitPane(); // used to vertically divides the stat and inventory menu
		AnchorPane upperScreen = new AnchorPane(); // will contain the top half of the screen
		SplitPane dividerMenu = new SplitPane(); // divider for monsters and stat menus
		AnchorPane statContainer = new AnchorPane();
		VBox statBox = new VBox(); // container for laying out the box for the player's health, mana, etc. 
		
		//variables for the player's stat menu (health, hp, exp, etc)
		Label statusLabel = new Label();
		GridPane statInterface = new GridPane();
		ColumnConstraints cc1 = new ColumnConstraints(); // column constraint options for the GridPane
		ColumnConstraints cc2 = new ColumnConstraints();
		RowConstraints rc1 = new RowConstraints(); // row constraint options for the GridPane
		RowConstraints rc2 = new RowConstraints();
		RowConstraints rc3 = new RowConstraints();
		RowConstraints rc4 = new RowConstraints();
		RowConstraints rc5 = new RowConstraints();
		RowConstraints rc6 = new RowConstraints();
		RowConstraints rc7 = new RowConstraints();
		Label nameLabel = new Label(); //label saying NAME: 
		Label levelLabel = new Label(); //LEVEL: 
		Label xpLabel = new Label(); //label saying XP: 
		Insets barConfig = new Insets(5.0, 5.0, 5.0, 0.0); //set insets for the progress bars to make their dimensions better
		Label hpLabel = new Label();		
		Label manaLabel = new Label();
		Label attackLabel = new Label();
		Label defenseLabel = new Label();
		ImageView playerImage = new ImageView();		
		

		if(hasSaveFile)
		{
			Room.populateMap();
		}
		
		///////////////////////////////////////////////end upper screen//////////////////////////////////////////////////////
		//vars for the lower screen config
		AnchorPane lowerScreenContainer = new AnchorPane();
		SplitPane lowerScreen = new SplitPane(); //holds everything in the lowerScreen interface. child of the lowerScreenContainer
		
	

		//console
		AnchorPane consolePane = new AnchorPane(); //holds the console
		HBox consoleContainer = new HBox(); //holds the console & related info
		Label consoleInputLabel = new Label(); //what the console show as its cursor
		TextField consoleIn = new TextField(); //where the user types
		VBox consoleBox = new VBox();
		Label consoleTitle = new Label();
		
		//map
		Label mapLabel = new Label(); //name of map
		AnchorPane mapContainer = new AnchorPane(); //contains everything for map interface
		VBox mapBox = new VBox(); //lock inside of the anchorpane
		TilePane mapTiler = new TilePane(); //in case we ever need multiple objects in the map interface
		//variables for the menu bar (holds the battleMenu)
		ButtonBar inGameButtons = new ButtonBar(); //holds the battleMenu
		Button battleMenu = new Button(); //let the user go to the in game menu
		///////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		this.setPrefHeight(768.0);
		this.setPrefWidth(1024.0);
		this.getStyleClass().add("background");

		menuDivider.setDividerPositions(0.6); // ~halfway up the screen
		menuDivider.setOrientation(Orientation.VERTICAL); // bar should change vertically
		menuDivider.setPrefWidth(160.0);

		upperScreen.setMinHeight(0.0);
		upperScreen.setMinWidth(0.0);
		upperScreen.setPrefHeight(100.0);
		upperScreen.setPrefWidth(160.0);

		dividerMenu.setDividerPositions(0.30); // mostly on the left side of the screen (stats window vs monsters)
		dividerMenu.setPrefHeight(160.0);
		dividerMenu.setPrefWidth(200.0);

		statContainer.setMinHeight(0.0);
		statContainer.setMinWidth(0.0);
		statContainer.setPrefHeight(160.0);
		statContainer.setPrefWidth(100.0);
		statContainer.getStyleClass().add("panel");

		statBox.setAlignment(Pos.TOP_CENTER);
		statBox.setPrefHeight(347.0);
		statBox.setPrefWidth(200.0);

		statusLabel.setMaxWidth(1000000); //making this a huge number lets us force left
		statusLabel.setText("Status");
		statBox.getChildren().add(statusLabel);

		statInterface.setMinHeight(Double.NEGATIVE_INFINITY);
		statInterface.setMinWidth(Double.NEGATIVE_INFINITY);
		statInterface.setPrefWidth(250.0);

		//configure the stats interface
		cc1.setHalignment(HPos.LEFT);
		cc1.setHgrow(Priority.ALWAYS);
		cc1.setMaxWidth(100000); 
		cc1.setMinWidth(Double.NEGATIVE_INFINITY);
		cc1.setPrefWidth(61.0);
		statInterface.getColumnConstraints().add(cc1);

		cc2.setHalignment(HPos.LEFT);
		cc2.setHgrow(Priority.SOMETIMES);
		cc2.setMaxWidth(100000);
		cc2.setPrefWidth(200.0);
		statInterface.getColumnConstraints().add(cc2);

		rc1.setMinHeight(10.0);
		rc1.setPrefHeight(30.0);
		rc1.setVgrow(Priority.SOMETIMES);
		statInterface.getRowConstraints().add(rc1);

		rc2.setMinHeight(10.0);
		rc2.setPrefHeight(30.0);
		rc2.setVgrow(Priority.SOMETIMES);
		statInterface.getRowConstraints().add(rc2);

		rc3.setMinHeight(10.0);
		rc3.setPrefHeight(30.0);
		rc3.setVgrow(Priority.SOMETIMES);
		statInterface.getRowConstraints().add(rc3);

		rc4.setMinHeight(10.0);
		rc4.setPrefHeight(30.0);
		rc4.setVgrow(Priority.SOMETIMES);
		statInterface.getRowConstraints().add(rc4);

		rc5.setMinHeight(10.0);
		rc5.setPrefHeight(30.0);
		rc5.setVgrow(Priority.SOMETIMES);
		statInterface.getRowConstraints().add(rc5);

		rc6.setMinHeight(10.0);
		rc6.setPrefHeight(30.0);
		rc6.setVgrow(Priority.SOMETIMES);
		statInterface.getRowConstraints().add(rc6);

		rc7.setMinHeight(10.0);
		rc7.setPrefHeight(30.0);
		rc7.setVgrow(Priority.SOMETIMES);
		statInterface.getRowConstraints().add(rc7);

		//configure everything for the gridPane! IE playerLevel, playerName, etc.
  		nameLabel.setContentDisplay(ContentDisplay.RIGHT);
		nameLabel.getStyleClass().add("label-small"); //make it look the way we have configured on the CSS sheet
		nameLabel.setText("Name: ");

		playerName.setId("playerName");
		playerName.getStyleClass().add("label-small");
		playerName.setTextAlignment(TextAlignment.RIGHT);
		GridPane.setColumnIndex(playerName, 1); //set location

		levelLabel.setContentDisplay(ContentDisplay.RIGHT);
		levelLabel.getStyleClass().add("label-small");
		levelLabel.setText("Level: ");
		GridPane.setRowIndex(levelLabel, 1); //set location

		playerLevel.setId("playerLevel");
		playerLevel.getStyleClass().add("label-small");
		playerLevel.setTextAlignment(TextAlignment.RIGHT);
		GridPane.setColumnIndex(playerLevel, 1); //set locations in the grid Pand
		GridPane.setRowIndex(playerLevel, 1);

		xpLabel.setContentDisplay(ContentDisplay.RIGHT);
		xpLabel.setMaxWidth(100000);
		xpLabel.getStyleClass().add("label-small");
		xpLabel.setText("XP:");		
		GridPane.setRowIndex(xpLabel, 2); //move down another row

		playerXP.setId("playerExperience");
		playerXP.setContentDisplay(ContentDisplay.CENTER);
		playerXP.setMaxWidth(100000);
		playerXP.setPrefHeight(30.0);
		playerXP.setPrefWidth(200.0);
		playerXP.getStyleClass().add("label-small");

		//used to show the player's progress in leveling their player
		expBar.setId("playerXpBar");
		expBar.setMaxWidth(100000);
		expBar.setPrefHeight(32.0);
		expBar.setPrefWidth(200.0);
		expBar.getStyleClass().add("bar-exp"); //use CSS to make the exp bar pretty and different than the other bars.
		expBar.setPadding(barConfig); //use the insets to make the bar's dimensions better
		playerXP.setGraphic(expBar); //add the words onto the bars!
		
		GridPane.setColumnIndex(playerXP, 1);
		GridPane.setRowIndex(playerXP, 2);

		hpLabel.setContentDisplay(ContentDisplay.RIGHT);
		hpLabel.setMaxWidth(100000);
		hpLabel.getStyleClass().add("label-small");
		hpLabel.setText("HP:");
		GridPane.setRowIndex(hpLabel, 3);
		playerHealth.setId("playerHealth");
		playerHealth.setContentDisplay(ContentDisplay.CENTER);
		playerHealth.setMaxWidth(100000);
		playerHealth.setPrefHeight(30.0);
		playerHealth.setPrefWidth(200.0);

		playerHealth.getStyleClass().add("label-small");

		
		hpBar.setId("playerHpBar");
		hpBar.setMaxWidth(100000);
		hpBar.setPrefHeight(32.0);
		hpBar.setPrefWidth(200.0);
		hpBar.getStyleClass().add("bar-health"); //make the health bar look like a health bar with CSS
		hpBar.setPadding(barConfig);
		playerHealth.setGraphic(hpBar); //add the words over the bar!
		GridPane.setColumnIndex(playerHealth, 1);
		GridPane.setRowIndex(playerHealth, 3);


		//configure the mana bar menu in the exact same way as the health and ex bars 
		manaLabel.setContentDisplay(ContentDisplay.RIGHT);
		manaLabel.setMaxWidth(100000);
		manaLabel.getStyleClass().add("label-small");
		manaLabel.setText("MP:");
		GridPane.setRowIndex(manaLabel, 4);

		playerMana.setId("playerMana");
		playerMana.setContentDisplay(ContentDisplay.CENTER);
		playerMana.setMaxWidth(100000);
		playerMana.setPrefHeight(30.0);
		playerMana.setPrefWidth(200.0);

		playerMana.getStyleClass().add("label-small");

		playerMana.setTextOverrun(OverrunStyle.CLIP);
		manaBar.setId("playerMpBar");
		manaBar.setMaxWidth(100000);
		manaBar.setPrefHeight(32.0);
		manaBar.setPrefWidth(200.0);
		manaBar.getStyleClass().add("bar-mana"); //make the mana bar blue and pretty
		manaBar.setPadding(barConfig);
		playerMana.setGraphic(manaBar); //add onto man bar
		GridPane.setColumnIndex(playerMana, 1);
		GridPane.setRowIndex(playerMana, 4);

		attackLabel.setContentDisplay(ContentDisplay.RIGHT);
		attackLabel.setMaxWidth(100000);
		attackLabel.getStyleClass().add("label-small");
		attackLabel.setText("Attack:");
		GridPane.setRowIndex(attackLabel, 5);

		defenseLabel.setContentDisplay(ContentDisplay.RIGHT);
		defenseLabel.setMaxWidth(100000);
		defenseLabel.getStyleClass().add("label-small");
		defenseLabel.setText("Defense:");
		GridPane.setRowIndex(defenseLabel, 6);

		//add all of the children into the statInterface gridPane

		statInterface.getChildren().add(nameLabel);		
		statInterface.getChildren().add(playerName);		
		statInterface.getChildren().add(levelLabel);		
		statInterface.getChildren().add(playerLevel);		
		statInterface.getChildren().add(xpLabel);
		statInterface.getChildren().add(playerXP);
		statInterface.getChildren().add(hpLabel);
		statInterface.getChildren().add(playerHealth);
		statInterface.getChildren().add(manaLabel);		
		statInterface.getChildren().add(playerMana);
		statInterface.getChildren().add(attackLabel);		
		statInterface.getChildren().add(defenseLabel);
		statInterface.getChildren().add(playerAttack);
		statInterface.getChildren().add(playerDefense);

		playerAttack.setId("playerAttack");
		playerAttack.getStyleClass().add("label-small");
		GridPane.setColumnIndex(playerAttack, 1);
		GridPane.setRowIndex(playerAttack, 5);

		playerDefense.setId("playerDefense");
		playerDefense.getStyleClass().add("label-small");
		GridPane.setColumnIndex(playerDefense, 1);
		GridPane.setRowIndex(playerDefense, 6);

		statBox.getChildren().add(statInterface); //let the vertical box contain the stat interface!
		VBox.setVgrow(statInterface, Priority.NEVER);
		VBox.setMargin(statInterface, new Insets(0.0, 0.0, 10.0, 5.0));	//set the bottom and right insets


		//configure the player's image! <NOTE: ALL IMAGES SET AT BOTTOM SO THEY CAN BE CHANGED EASILY>
		playerImage.setId("playerAvatar");
		playerImage.setFitHeight(150.0);
		playerImage.setFitWidth(215.0);
		playerImage.setPickOnBounds(true);
		playerImage.setPreserveRatio(true);
		statBox.getChildren().add(playerImage); //add the player's image to the vBox
		
		VBox.setVgrow(playerImage, Priority.ALWAYS);
		statBox.setPadding(new Insets(0.0, 5.0, 0.0, 0.0)); //padding for the stat's menu, etc


		statContainer.getChildren().add(statBox); //add the statBox to the anchorPane!
		AnchorPane.setBottomAnchor(statBox, 0.0); //make it resize to fit the anchorpane as needed
		AnchorPane.setLeftAnchor(statBox, 0.0);
		AnchorPane.setRightAnchor(statBox, 0.0);
		AnchorPane.setTopAnchor(statBox, 0.0);
		
		dividerMenu.getItems().add(statContainer); //add all of the stat stuff created to the dividerMenu

		////////////////////////////////////////////////////////////////////////////////////////////////
		//		End of stat screen configs, begin configuring the monster display					  //
		////////////////////////////////////////////////////////////////////////////////////////////////
	
		monsterHouse.setMinHeight(0.0);
		monsterHouse.setMinWidth(0.0);
		monsterHouse.setPrefHeight(160.0);
		monsterHouse.setPrefWidth(100.0);
		monsterHouse.getStyleClass().add("background"); //use the background from the menu for the battles also

		displayMonsters(currentLocation);
		
		//configure the window, and set the background		
		
		
		//now put it all together for the top of the screen
		dividerMenu.getItems().add(monsterHouse);
		upperScreen.getChildren().add(dividerMenu); // update the anchorpane for the top of the screen (stats and monsters)
		AnchorPane.setBottomAnchor(dividerMenu, 0.0);
		AnchorPane.setLeftAnchor(dividerMenu, 0.0);
		AnchorPane.setRightAnchor(dividerMenu, 0.0);
		AnchorPane.setTopAnchor(dividerMenu, 0.0);

		menuDivider.getItems().add(upperScreen); // add the anchorPane that we just populated to the menuDivider interface

		////////////////////////////////////////////////////////////////////////////////////////////////
		//		End of monster configs, begin configuring the inventory display						  //
		////////////////////////////////////////////////////////////////////////////////////////////////
		
		//configure the lower screen's anchorpane
		lowerScreenContainer.setMinHeight(0.0);
		lowerScreenContainer.setMinWidth(0.0);
		lowerScreenContainer.setPrefHeight(100.0);
		lowerScreenContainer.setPrefWidth(160.0);

		//configure the splitpane to hold everything in the lower interface
		lowerScreen.setDividerPositions(0.25, 0.75); //set divisions between the map, console, and inventory
		lowerScreen.setLayoutX(-150.0); 
		lowerScreen.setLayoutY(-20.0);  
		lowerScreen.setPrefHeight(250.0);
		lowerScreen.setPrefWidth(800.0);

		//configure the anchorpane holding the inventory container
		inventoryContainer.setMinHeight(0.0);
		inventoryContainer.setMinWidth(0.0);
		inventoryContainer.getStyleClass().add("panel");

		updateInventory();

		lowerScreen.getItems().add(inventoryContainer);

		////////////////////////////////////////////////////////////////////////////////////////////////
		//		End of inventory configs, begin configuring the console display						  //
		////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		//configure the console's anchorpane
		consolePane.setMinHeight(0.0);
		consolePane.setMinWidth(0.0);
		consolePane.setPrefHeight(160.0);
		consolePane.setPrefWidth(100.0);
		consolePane.getStyleClass().add("panel");

		//configure the box holding console info 
		consoleBox.setAlignment(Pos.TOP_CENTER);
		consoleBox.setPrefHeight(200.0);
		consoleBox.setPrefWidth(100.0);

		//configure the activity label above the console
		consoleTitle.setMaxWidth(100000);
		consoleTitle.setText("Activity");
		consoleBox.getChildren().add(consoleTitle);
		VBox.setMargin(consoleTitle, new Insets(0.0, 5.0, 0.0, 0.0));

		//configure the output for the console
		consoleOut.setId("consoleOut");
		consoleOut.setEditable(false);
		consoleOut.setPrefHeight(200.0);
		consoleOut.setPrefWidth(200.0);
		consoleOut.getStyleClass().add("console");
		consoleOut.setWrapText(true);
		consoleOut.setPadding(new Insets(0.0, 10.0, 0.0, 0.0));
		consoleBox.getChildren().add(consoleOut); //add the consoleOut to the consoleBox
		VBox.setVgrow(consoleOut, Priority.ALWAYS);
		VBox.setMargin(consoleOut, new Insets(0.0, 10.0, 0.0, 10.0));

		//configure the hbox holding the console
		consoleContainer.setMaxHeight(30.0);
		consoleContainer.setMinHeight(30.0);
		consoleContainer.setPrefHeight(30.0);
		consoleContainer.setPrefWidth(200.0);

		//configure the label (currently >>> because alex is feeling funny and likes Python. Yay Python)
		consoleInputLabel.setMaxHeight(30.0);
		consoleInputLabel.setMaxWidth(30.0);
		consoleInputLabel.setMinHeight(30.0);
		consoleInputLabel.setMinWidth(30.0);
		consoleInputLabel.setPrefHeight(30.0);
		consoleInputLabel.setPrefWidth(30.0);
		consoleInputLabel.getStyleClass().add("console");
		consoleInputLabel.setText(">>"); //>>> doesn't work lol
		consoleContainer.getChildren().add(consoleInputLabel);

		//configure the console input
		consoleIn.setId("consoleIn");
		consoleIn.setMaxHeight(30.0);
		consoleIn.setMaxWidth(100000);
		consoleIn.setMinHeight(30.0);
		consoleIn.setPrefHeight(30.0);
		consoleIn.getStyleClass().add("console");
		consoleIn.setPadding(new Insets(0.0, 0.0, 0.0, -5.0));
		consoleContainer.getChildren().add(consoleIn); //add the consoleIn to the container 
		HBox.setHgrow(consoleIn, Priority.ALWAYS);

		//format the consoleBox
		consoleBox.getChildren().add(consoleContainer);
		VBox.setMargin(consoleContainer, new Insets(0.0, 10.0, 10.0, 10.0));

		//format the anchorpane and add it to the lower screen
		consolePane.getChildren().add(consoleBox);
		AnchorPane.setBottomAnchor(consoleBox, 0.0);
		AnchorPane.setLeftAnchor(consoleBox, 0.0);
		AnchorPane.setRightAnchor(consoleBox, 0.0);
		AnchorPane.setTopAnchor(consoleBox, 0.0);
		lowerScreen.getItems().add(consolePane);

		////////////////////////////////////////////////////////////////////////////////////////////////
		//		End of console configs, begin configuring the map display		     				  //
		////////////////////////////////////////////////////////////////////////////////////////////////
		
		//begin configuring the map interface
		mapContainer.setMinHeight(0.0);
		mapContainer.setMinWidth(0.0);
		mapContainer.setPrefHeight(160.0);
		mapContainer.setPrefWidth(100.0);
		mapContainer.getStyleClass().add("panel");

		//begin configuring the vbox that holds the map
		mapBox.setAlignment(Pos.TOP_CENTER);
		mapBox.setPrefHeight(200.0);
		mapBox.setPrefWidth(100.0);

		//begin configuring the map label
		mapLabel.setMaxWidth(100000);
		mapLabel.setText("Map");
		mapBox.getChildren().add(mapLabel);
		VBox.setMargin(mapLabel, new Insets(0.0, 0.0, 0.0, 5.0));

		//configure the map tiler
		mapTiler.setAlignment(Pos.CENTER);
		mapTiler.setPrefHeight(200.0);
		mapTiler.setPrefWidth(200.0);

		//set the map image settings
		mapImage.setId("map");
		mapImage.setFitWidth(150.0);
		mapImage.setPickOnBounds(true);
		mapImage.setPreserveRatio(true);

		///update the nested containers and the anchorpane, then add the anchorpane to the lower screen
		mapTiler.getChildren().add(mapImage);
		mapBox.getChildren().add(mapTiler);

		mapContainer.getChildren().add(mapBox);
		AnchorPane.setBottomAnchor(mapBox, 0.0);
		AnchorPane.setLeftAnchor(mapBox, 0.0);
		AnchorPane.setRightAnchor(mapBox, 0.0);
		AnchorPane.setTopAnchor(mapBox, 0.0);

		lowerScreen.getItems().add(mapContainer);
		
		//configure the lower screen and add it to the menuDivider
		lowerScreenContainer.getChildren().add(lowerScreen);
		AnchorPane.setBottomAnchor(lowerScreen, 0.0);
		AnchorPane.setLeftAnchor(lowerScreen, 0.0);
		AnchorPane.setRightAnchor(lowerScreen, 0.0);
		AnchorPane.setTopAnchor(lowerScreen, 0.0);
		menuDivider.getItems().add(lowerScreenContainer); 
		
		//now add the menuDivider to the in game interface!
		this.getChildren().add(menuDivider);
		AnchorPane.setBottomAnchor(menuDivider, 0.0);
		AnchorPane.setLeftAnchor(menuDivider, 0.0);
		AnchorPane.setRightAnchor(menuDivider, 0.0);
		AnchorPane.setTopAnchor(menuDivider, 0.0);

		//configure the menu bar
		inGameButtons.setLayoutX(586.0);
		inGameButtons.setLayoutY(14.0);
		inGameButtons.setPrefHeight(40.0);
		inGameButtons.setPrefWidth(200.0);

		//configure the in game menu button
		battleMenu.setMnemonicParsing(false);
		battleMenu.setPrefHeight(36.0);
		battleMenu.setPrefWidth(85.0);
		battleMenu.getStyleClass().add("button-lite");
		battleMenu.setText("Menu");
		inGameButtons.getButtons().add(battleMenu); //add to the menu bar
		inGameButtons.setPadding(new Insets(-10.0, 0.0, 0.0, 0.0));

		//add the inGameButtons to the overarching AnchorPane
		this.getChildren().add(inGameButtons);
		AnchorPane.setRightAnchor(inGameButtons, 5.0);
		AnchorPane.setTopAnchor(inGameButtons, 0.0);

		/////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		populateLabels(player);
		playerImage.setImage(new Image("res/player/avatar.png"));

		
		//set images here!

		battleMenu.setOnAction(e -> MainApp.showInGameMenu()); 
		consoleOut.appendText("For a list of options, please type help." + System.lineSeparator());
		
    	consoleIn.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
    		switch (keyEvent.getCode()) {
            case ENTER:
                String text = consoleIn.getText();
                handleConsoleInput(text);
                consoleIn.clear(); //clear what the user is typing!
                break;
            default:
                break;
    		}    		
    	});	
    	consoleOut.textProperty().addListener( (observable, oldValue, newValue) -> 
		consoleOut.setScrollTop(Double.MAX_VALUE) ); 		
	}
	
	
	
	public void displayMonsters(Room currentLocation)
	{	
		currentLocation = player.getLocation();
		Iterator<Enemy> iter =player.getLocation().getEnemies().iterator();

		
		//should not happen, but just for safety...
		if(currentLocation == null)
		{
			return;
		}
				
		//remove any dead enemies from the display
		while (iter.hasNext()) {
		    Enemy x = iter.next();

		    if (x.getHealth() <= 0)
		    {
		    	if(x.getName().equals("Dragon"))
		    	{
		    		MainApp.showVictory();
		    	}
		    	//player.gainExperience((int) Math.floor((float)x.getExperienceLevel() / player.getCurrentStats().getLevel()));
		    	player.gainExperience(x.getExperienceLevel());
		    	player.checkLeveled();		    	
		        iter.remove();
		    }
		}
		monsterHouse.getChildren().clear();
		
		int numMonsters = currentLocation.getEnemies().size();
		Insets barConfig = new Insets(5.0, 5.0, 5.0, 0.0); //set insets for the progress bars to make their dimensions better
		
		if(numMonsters == 0)
		{
			return;
		}
		{ //scoping for variables
			monster1 = currentLocation.getEnemies().get(0);
			HBox monsterBox = new HBox(); //holds the monsters
			HBox monster1Stats = new HBox();
			VBox monsterContainer1 = new VBox(); //holds information for the first monster
			ImageView firstMonster = new ImageView(); //holds the monster image settings and picture
			Label monsterName1 = new Label();
			Label monster1LevelLabel = new Label();
			HBox monster1StatBox = new HBox();
			Label monster1HPLabel = new Label();
			Label monster1HealthLabel = new Label();
			ProgressBar monster1HealthBar = new ProgressBar();	
		
			//set up exactly the same as monster 1
			ImageView secondMonster = new ImageView();
			Label monster2HealthLabel = new Label(); //"Level"
			Label monster2HPLabel = new Label(); //"HP"
			Label monster2HealthValue = new Label(); //current health (current/max)
			Label monsterName2 = new Label(); //"name"
			HBox monster2Stats = new HBox();
			VBox monsterContainer2 = new VBox();
			HBox monster2StatBox = new HBox();
			ProgressBar monster2HealthBar = new ProgressBar();			
			if(numMonsters == 1 || numMonsters == 2)
			{
				//configure the HXox holding the monster
				monsterBox.setAlignment(Pos.CENTER);
				monsterBox.setPrefHeight(100.0);
				monsterBox.setPrefWidth(200.0);
				monsterBox.setSpacing(50.0);

				//configure VBox to hold monster info
				monsterContainer1.setAlignment(Pos.CENTER);
				monsterContainer1.setPrefHeight(200.0);
				monsterContainer1.setPrefWidth(100.0);

				//configure the monster's image settings
				firstMonster.setId("monsterAvatar1");
				firstMonster.setFitHeight(150.0);
				firstMonster.setFitWidth(200.0);
				firstMonster.setPickOnBounds(true);
				firstMonster.setPreserveRatio(true);
				monsterContainer1.getChildren().add(firstMonster); //add the monster picture to the VBox

				//configure the HBox for the monster stats
				monster1Stats.setAlignment(Pos.CENTER);
				monster1Stats.setFillHeight(false);
				monster1Stats.setMaxHeight(30.0);
				monster1Stats.setPrefHeight(30.0);
				monster1Stats.setPrefWidth(200.0);

				//create labels for monster1's name and level and add them to the screen
				monsterName1.setId("monsterName1");
				monsterName1.setContentDisplay(ContentDisplay.RIGHT);
				monsterName1.setMaxWidth(100000);
				monsterName1.getStyleClass().add("label-small");
				monsterName1.setText("Name");
				monster1Stats.getChildren().add(monsterName1);
				HBox.setHgrow(monsterName1, Priority.ALWAYS);

				monster1LevelLabel.setId("monsterLevel1");
				monster1LevelLabel.getStyleClass().add("label-small");
				monster1LevelLabel.setText("Level");
				monster1LevelLabel.setTextAlignment(TextAlignment.RIGHT);
				monster1Stats.getChildren().add(monster1LevelLabel);
				monsterContainer1.getChildren().add(monster1Stats);

				//configure the box holding the monster's stats
				monster1StatBox.setFillHeight(false);
				monster1StatBox.setPrefHeight(100.0);
				monster1StatBox.setPrefWidth(200.0);

				//configure the health bar for the first monster
				monster1HPLabel.setContentDisplay(ContentDisplay.RIGHT);
				monster1HPLabel.setMaxWidth(35.0);
				monster1HPLabel.setPrefHeight(30.0);
				monster1HPLabel.setPrefWidth(35.0);
				monster1HPLabel.getStyleClass().add("label-small");
				monster1HPLabel.setText("HP:");
				monster1HPLabel.setTextOverrun(OverrunStyle.CLIP);
				monster1StatBox.getChildren().add(monster1HPLabel);
				HBox.setHgrow(monster1HPLabel, Priority.NEVER);


				monster1HealthLabel.setId("monsterHealth1");
				monster1HealthLabel.setContentDisplay(ContentDisplay.CENTER);
				monster1HealthLabel.getStyleClass().add("label-small");
				monster1HealthLabel.setText(GameGUI.formatFraction(monster1.getHealth(), monster1.getBaseStats().getMaxHealth()));

				//configure the first monster's health bar
				monster1HealthBar.setId("monsterHpBar1");
				monster1HealthBar.setPrefHeight(32.0);
				monster1HealthBar.setPrefWidth(164.0);
				monster1HealthBar.getStyleClass().add("bar-health");
				monster1HealthBar.setPadding(barConfig);
				monster1HealthLabel.setGraphic(monster1HealthBar); //add text over the health bar

				//add to respective containers
				monster1StatBox.getChildren().add(monster1HealthLabel);
				monsterContainer1.getChildren().add(monster1StatBox);

				monster1HealthBar.setProgress((double)monster1.getHealth() / monster1.getCurrentStats().getMaxHealth()); //FIX
				monsterBox.getChildren().add(monsterContainer1);
				firstMonster.setImage(new Image(monster1.getImage())); //replace with actual image				

			}
			if(numMonsters == 2)
			{
				monster2 = currentLocation.getEnemies().get(1);
				//configure VBox to hold monster info
				monsterContainer2.setAlignment(Pos.CENTER);
				monsterContainer2.setPrefHeight(200.0);
				monsterContainer2.setPrefWidth(100.0);

				//configure the second monster image
				secondMonster.setId("monsterAvatar2");
				secondMonster.setFitHeight(150.0);
				secondMonster.setFitWidth(200.0);
				secondMonster.setPickOnBounds(true);
				secondMonster.setPreserveRatio(true);
				monsterContainer2.getChildren().add(secondMonster);

				//configure the HBox with Monster 2's stats
				monster2Stats.setAlignment(Pos.CENTER);
				monster2Stats.setFillHeight(false);
				monster2Stats.setMaxHeight(30.0);
				monster2Stats.setPrefHeight(30.0);
				monster2Stats.setPrefWidth(200.0);

				//configure the monster name and level labels and add them to the stats box
				monsterName2.setId("monsterName2");
				monsterName2.setContentDisplay(ContentDisplay.RIGHT);
				monsterName2.setMaxWidth(100000);
				monsterName2.getStyleClass().add("label-small");
				monsterName2.setText("Name");		
				monster2Stats.getChildren().add(monsterName2);
				HBox.setHgrow(monsterName2, Priority.ALWAYS);
				monster2HealthLabel.setId("monsterLevel2");
				monster2HealthLabel.getStyleClass().add("label-small");
				monster2HealthLabel.setText("Level");
				monster2HealthLabel.setTextAlignment(TextAlignment.RIGHT);
				monster2Stats.getChildren().add(monster2HealthLabel);
				monsterContainer2.getChildren().add(monster2Stats); //add the stats to the monster container

				//configure the box holding the second monster's stats
				monster2StatBox.setFillHeight(false);
				monster2StatBox.setPrefHeight(100.0);
				monster2StatBox.setPrefWidth(200.0);
				
				//configure the second monster's hp label
				monster2HPLabel.setContentDisplay(ContentDisplay.RIGHT);
				monster2HPLabel.setMaxWidth(35.0);
				monster2HPLabel.setPrefHeight(30.0);
				monster2HPLabel.setPrefWidth(35.0);
				monster2HPLabel.getStyleClass().add("label-small");
				monster2HPLabel.setText("HP:");
				monster2HPLabel.setTextOverrun(OverrunStyle.CLIP);
				
				monster2StatBox.getChildren().add(monster2HPLabel); //add the hp label to the monster's stat box
				HBox.setHgrow(monster2HPLabel, Priority.NEVER);

				//configure the second monster's health
				monster2HealthValue.setId("monsterHealth2");
				monster2HealthValue.setContentDisplay(ContentDisplay.CENTER);
				monster2HealthValue.getStyleClass().add("label-small");
				monster2HealthValue.setText(GameGUI.formatFraction(monster2.getHealth(), monster2.getBaseStats().getMaxHealth()));

				//configure the health bar for the second monster and add it to the statbox
				monster2HealthBar.setId("monsterHpBar2");
				monster2HealthBar.setPrefHeight(32.0);
				monster2HealthBar.setPrefWidth(164.0);
				monster2HealthBar.getStyleClass().add("bar-health");
				monster2HealthBar.setPadding(barConfig);
				monster2HealthValue.setGraphic(monster2HealthBar); //add text over the health bar
				monster2StatBox.getChildren().add(monster2HealthValue);
				monsterContainer2.getChildren().add(monster2StatBox);


				//monster health
				monster2HealthBar.setProgress((double)monster2.getHealth() / monster2.getCurrentStats().getMaxHealth()); //red mark for the bar (percentage)
				monsterBox.getChildren().add(monsterContainer2); //makes monsters visible. Can add one only if wanted
				secondMonster.setImage(new Image(monster2.getImage()));
				
			}
			monsterHouse.getChildren().add(monsterBox);
			AnchorPane.setBottomAnchor(monsterBox, 0.0);
			AnchorPane.setLeftAnchor(monsterBox, 0.0);
			AnchorPane.setRightAnchor(monsterBox, 0.0);
			AnchorPane.setTopAnchor(monsterBox, 30.0);
						
		}
	}
	
    static public String formatFraction(Integer num, Integer den) {
    	return num.toString() + "/" + den.toString();
    }    
	
	
	//fill in the bars and labels for the player
	public void populateLabels(Player player)
	{
		playerName.setText(player.getName()); //actual player's name
		playerLevel.setText(Integer.toString(player.getBaseStats().getLevel()));  //player's level 
		playerAttack.setText(Integer.toString(player.getCurrentStats().getAttack()));
		playerDefense.setText(Integer.toString(player.getCurrentStats().getDefense()));

		//set player bar values here
		playerXP.setText(GameGUI.formatFraction(player.getExperience(),player.playerPlusOne(player.getBaseStats().getLevel()))); //player's current experience, and the exp needed for the next level.
		playerHealth.setText(GameGUI.formatFraction(player.getHealth(), player.getCurrentStats().getMaxHealth())); //to be added over the hp progress bar
		playerMana.setText("ADD MANA");  //<<<<<<<<<<<<<<<<<<Player mana goes here>>>>>>>>>>>>>>>>>>>>

		//set player bar values
		manaBar.setProgress(1); // <<<<<<<<<<<<<<<<<<<<<<<<<<<FIX ME>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.
		hpBar.setProgress((double)player.getHealth() / player.getCurrentStats().getMaxHealth()); 
		expBar.setProgress((double)player.getExperience() / player.playerPlusOne(player.getBaseStats().getLevel()));//<<<<<<<<<<<<<<<<<<FIX ME LATER>>>>>>>>>>>>>>>>>>>>>>>>
		
	
		mapImage.setImage(new Image(this.player.getLocation().getImage())); //show the new map
		displayMonsters(this.player.getLocation()); //show the monsters
		updateInventory();
	}
	


    public void itemHover(int slotNumber, TextArea itemInfo)
    {
    	if(player.getInventory().size() <  slotNumber)
    	{
    		itemInfo.setText("");
    	}
    	else
    	{
    		itemInfo.setText(player.getInventory().get(slotNumber - 1).getEffect().getDescription());
    	}
    }
    
    
    public void updateInventory()
    {
    	Room currentRoom = this.player.getLocation();
 
    	if(currentRoom.getContainer() != null)
    	{
        	for(Item x : currentRoom.getContainer().getContents())
        	{
        		if(!this.player.getInventory().contains(x) && x != null)
        		{
        			this.player.addItem(x);
        		}
        	}
    	}
    	
    	//variables for inventory, etc
		TilePane inventory = new TilePane(); //actual inventory display 
		VBox inventoryBox = new VBox(); //holds the inventory label, inventory, and text area

		//configure the vbox holding things for the inventory
		inventoryContainer.getChildren().clear(); //clear everything in the inventory for display
		inventoryBox.setAlignment(Pos.TOP_CENTER);
		inventoryBox.setPrefHeight(200.0);
		inventoryBox.setPrefWidth(100.0);
		
		
		ImageView slot1 = new ImageView();
		ImageView slot2 = new ImageView();
		ImageView slot3 = new ImageView();
		ImageView slot4 = new ImageView();
		ImageView slot5 = new ImageView();
		ImageView slot6 = new ImageView();
		ImageView slot7 = new ImageView();
		ImageView slot8 = new ImageView();
		ImageView slot9 = new ImageView();
		TextArea itemInfo = new TextArea(); //shows item information when hovering over it

    	
		//configure the inventory's label
		Label inventoryLabel = new Label();
		inventoryLabel.setMaxWidth(100000);
		inventoryLabel.setPrefWidth(135.0);
		inventoryLabel.setText("Inventory");
		inventoryBox.getChildren().add(inventoryLabel);
		VBox.setMargin(inventoryLabel, new Insets(0.0, 5.0, 0.0, 0.0));

		//begin inventory configuring 
		inventory.setId("inventory");
		inventory.setAlignment(Pos.CENTER);
		inventory.setHgap(5.0);
		inventory.setPrefHeight(200.0);
		inventory.setPrefWidth(200.0);
		inventory.setVgap(5.0);
		inventory.setPadding(new Insets(20.0, 20.0, 20.0, 20.0));

		//configure individual slots for the inventory (images set below)
		slot1.setFitHeight(50.0);
		slot1.setFitWidth(50.0);
		slot1.setPickOnBounds(true);
		inventory.getChildren().add(slot1);

		slot2.setFitHeight(50.0);
		slot2.setFitWidth(50.0);
		slot2.setPickOnBounds(true);
		inventory.getChildren().add(slot2);

		slot3.setFitHeight(50.0);
		slot3.setFitWidth(50.0);
		slot3.setPickOnBounds(true);
		inventory.getChildren().add(slot3);

		slot4.setFitHeight(50.0);
		slot4.setFitWidth(50.0);
		slot4.setPickOnBounds(true);
		inventory.getChildren().add(slot4);

		slot5.setFitHeight(50.0);
		slot5.setFitWidth(50.0);
		slot5.setPickOnBounds(true);
		inventory.getChildren().add(slot5);

		slot6.setFitHeight(50.0);
		slot6.setFitWidth(50.0);
		slot6.setPickOnBounds(true);
		inventory.getChildren().add(slot6);

		slot7.setFitHeight(50.0);
		slot7.setFitWidth(50.0);
		slot7.setPickOnBounds(true);
		inventory.getChildren().add(slot7);

		slot8.setFitHeight(50.0);
		slot8.setFitWidth(50.0);
		slot8.setPickOnBounds(true);
		inventory.getChildren().add(slot8);

		slot9.setFitHeight(50.0);
		slot9.setFitWidth(50.0);
		slot9.setPickOnBounds(true);
		inventory.getChildren().add(slot9);
		
		//add the inventory tiling to the box holding the inventory components
		inventoryBox.getChildren().add(inventory);

		//start configuring the area where we hover over the images to see descriptions
		itemInfo.setId("itemInfo");
		itemInfo.setEditable(false);
		itemInfo.setMaxHeight(100000);
		itemInfo.setMaxWidth(100000);
		itemInfo.setPrefHeight(200.0);
		itemInfo.setPrefWidth(200.0);
		itemInfo.setWrapText(true);
		inventoryBox.getChildren().add(itemInfo);
		VBox.setVgrow(itemInfo, Priority.SOMETIMES);

		inventoryContainer.getChildren().add(inventoryBox);
		AnchorPane.setBottomAnchor(inventoryBox, 0.0);
		AnchorPane.setLeftAnchor(inventoryBox, 0.0);
		AnchorPane.setRightAnchor(inventoryBox, 0.0);
		AnchorPane.setTopAnchor(inventoryBox, 0.0);

		for(int j = 1; j <= this.player.getInventory().size(); j++) //make me into an arraylist later lol
		{
			switch (j) {
	       	case 1:
	    		slot1.setImage(new Image(this.player.getInventory().get(j - 1).getImage())); //r1c1
	       		break;
	       	case 2:
	    		slot2.setImage(new Image(this.player.getInventory().get(j - 1).getImage())); //r1c2	       		
	       		break;
	       	case 3:
	    		slot3.setImage(new Image(this.player.getInventory().get(j - 1).getImage())); //r1c3	       		
	       		break;
	       	case 4:
	    		slot4.setImage(new Image(this.player.getInventory().get(j - 1).getImage())); //r2c1	       		
	       		break;	       
	       	case 5:
	    		slot5.setImage(new Image(this.player.getInventory().get(j - 1).getImage())); //r2c2	       		
	       		break;
	       	case 6:
	    		slot6.setImage(new Image(this.player.getInventory().get(j - 1).getImage())); //r2c3	       		
	       		break;
	       	case 7:
	    		slot7.setImage(new Image(this.player.getInventory().get(j - 1).getImage())); //r3c1	       		
	       		break;
	       	case 8:
	    		slot8.setImage(new Image(this.player.getInventory().get(j - 1).getImage())); //r3c2	       		
	       		break;	  
	       	case 9:
	    		slot9.setImage(new Image(this.player.getInventory().get(j - 1).getImage())); //r3c3	       		
	       		break;
	       	default:
	       		break;
			}
		}
		//events for displaying effects
    	slot1.setOnMouseEntered(e -> itemHover(1, itemInfo));
    	slot2.setOnMouseEntered(e -> itemHover(2, itemInfo));
    	slot3.setOnMouseEntered(e -> itemHover(3, itemInfo));
    	slot4.setOnMouseEntered(e -> itemHover(4, itemInfo));
    	slot5.setOnMouseEntered(e -> itemHover(5, itemInfo));
    	slot6.setOnMouseEntered(e -> itemHover(6, itemInfo));
    	slot7.setOnMouseEntered(e -> itemHover(7, itemInfo));
    	slot8.setOnMouseEntered(e -> itemHover(8, itemInfo));
    	slot9.setOnMouseEntered(e -> itemHover(9, itemInfo));
    	
    	//clickable effect (use items or equip)
    	slot1.setOnMouseClicked(e -> useItem(this.player.getInventory().get(0)));
    	slot2.setOnMouseClicked(e -> useItem(this.player.getInventory().get(1)));
    	slot3.setOnMouseClicked(e -> useItem(this.player.getInventory().get(2)));
    	slot4.setOnMouseClicked(e -> useItem(this.player.getInventory().get(3)));
    	slot5.setOnMouseClicked(e -> useItem(this.player.getInventory().get(4)));
    	slot6.setOnMouseClicked(e -> useItem(this.player.getInventory().get(5)));
    	slot7.setOnMouseClicked(e -> useItem(this.player.getInventory().get(6)));
    	slot8.setOnMouseClicked(e -> useItem(this.player.getInventory().get(7)));
    	slot9.setOnMouseClicked(e -> useItem(this.player.getInventory().get(8)));

    	
		
    }
    
    //remove the item from the player's inventory, and update their stats!
    public void useItem(Item item)
    {
    	player.useItem(item); //update the player's stats!
    	player.getInventory().remove(item);
    	this.populateLabels(player);
    	this.updateInventory();
    }
    
    public boolean handleConsoleInput(String input) {
       	input = input.toLowerCase();//case insensitive commands

    	switch (input) {
       	case "help":
       		consoleOut.appendText(System.lineSeparator() + "You have access to the following options." + System.lineSeparator());
       		consoleOut.appendText("Thunderbolt - Risky, but hits harder." + System.lineSeparator());
       		consoleOut.appendText("Fireball - You'll look funny trying to cast the spell, but try your best!" + System.lineSeparator());
       		consoleOut.appendText("Flail - IF you can hit them, you will surely win." + System.lineSeparator());
       		consoleOut.appendText("Flee - Try your best to run away to the room you came from." + System.lineSeparator());
       		consoleOut.appendText("<NOTE: You will be able to move <insert options here> after you clear the room>");
       		break;
    	case "thunderbolt":
    		consoleOut.appendText(System.lineSeparator() + input + System.lineSeparator());
    		consoleOut.appendText("You used thunderbolt!" + System.lineSeparator());
    		//updateGUI(); // This is where turn handling would happen
    		break;
    	case "":
    		consoleOut.appendText("Alright, well, you should probably do something..." + System.lineSeparator());
    		//updateGUI();
    		break;
    	case "fireball":
    		consoleOut.appendText(System.lineSeparator() + input + System.lineSeparator());    		
    		consoleOut.appendText("You used fireball!" + System.lineSeparator());
    		break;
    	case "flail":
    		consoleOut.appendText(System.lineSeparator() + input + System.lineSeparator());    		
    		consoleOut.appendText("You drop your enemy like a ton of bricks. They won't get back up." + System.lineSeparator());
    	case "north":
    		if(this.player.getLocation().getDoor("n") != null)
    		{
        		this.player.setLocation(this.player.getLocation().getDoor("n").enterRoom(this.player.getLocation()));
    		}    	
    		else
    		{
        		consoleOut.appendText("You can't move that way!" + System.lineSeparator());
    		}
    		break;
    	case "south":
    		if(this.player.getLocation().getDoor("s") != null)
    		{
        		this.player.setLocation(this.player.getLocation().getDoor("s").enterRoom(this.player.getLocation()));
    		}    	
    		else
    		{
        		consoleOut.appendText("You can't move that way!" + System.lineSeparator());
    		}    		
    		break; 
    	case "east":
    		if(this.player.getLocation().getDoor("e") != null)
    		{
        		this.player.setLocation(this.player.getLocation().getDoor("e").enterRoom(this.player.getLocation()));
    		}    	
    		else
    		{
        		consoleOut.appendText("You can't move that way!" + System.lineSeparator());
    		}
    		break;  
    	case "west":
    		if(this.player.getLocation().getDoor("w") != null)
    		{
        		this.player.setLocation(this.player.getLocation().getDoor("w").enterRoom(this.player.getLocation()));
    		}
    		break;    		
    	default:
    		consoleOut.appendText(System.lineSeparator() + "Invalid Option. Try something else." + System.lineSeparator());
    	}
       	if(input.equals("south") || input.equals("north") || input.equals("east") || input.equals("west"))
       	{
       		populateLabels(this.player); //update the directions because the player is moving.
       	}
       	if(GameGUI.isFighting(input))
       	{
           	this.triggerBattleSequence(input);
       	}

    	return true;
    }

    //single turn of attacking
    public void triggerBattleSequence(String input)
    {
       	if(GameGUI.isFighting(input))
       	{
       		if(player.getHealth() > 0)
       		{
           		player.attack();
           		for(Enemy x : player.getLocation().getEnemies())
           		{
           			//player just killed an enemy! give the player theiry drops!
           			if(x.getHealth() <= 0 && x.getDrops() != null)
           			{
           				for(Item item : x.getDrops().getContents()) 
           				{
               				player.getInventory().add(item);           					
           				}
           				this.updateInventory(); //update display module
           			}
           		}
           		this.displayMonsters(player.getLocation());
       		}
       		
       		PauseTransition pause1 = new PauseTransition(Duration.seconds(.5));
       		pause1.setOnFinished(e -> this.displayMonsters(player.getLocation()));
       		pause1.play();
       		
       		if(player.getLocation().getEnemies().size() > 0 && player.getHealth() > 0)
       		{
       			player.receiveDamage();
       		}
       		
       		PauseTransition pause2 = new PauseTransition(Duration.seconds(.5));
       		pause2.setOnFinished(e -> populateLabels(this.player));
       		pause2.play();
       	}
    }
    
    //if the attack is fighting, user should attack the enemies in the room! Otherwise, do nothing.
    public static boolean isFighting(String string)
    {
    	return(string.equals("thunderbolt") || string.equals("fireball") || string.equals("flail"));
    }

}
