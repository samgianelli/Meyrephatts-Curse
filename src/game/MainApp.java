package game;

import java.io.File;
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Game game = null; //to be loaded for serialization

	
	private String musicFile = "./src/game/songOfRunes.mp3"; //change to whatever you want. Follow trigger function if you want multiple sound files
	private MediaPlayer mPlayer;
	private boolean musicOn;
	
    private Stage primaryStage;
    private static BorderPane rootLayout;
    //private ObservableList<Person> personData = FXCollections.observableArrayList();

    private static AnchorPane mainMenu;
    private static AnchorPane inGameMenu;
    private static AnchorPane optionsMenu;
    private static GameGUI inGameInterface;
    private static AnchorPane deathScreen;
    private static AnchorPane victory;
    
    

    public MainApp() {
    	game = null; //must be loaded or started over
    	triggerMusic(); //music needs to load and construct media player for options.    	
    	createMainMenu();
    	createDeathScreen();
    	createInGameMenu();
    	createOptionsMenu();
        createVictoryScreen();
    }

    
    public void triggerMusic()
    {
    	Media music = new Media(new File(this.musicFile).toURI().toString());
    	mPlayer = new MediaPlayer(music);
    	mPlayer.play();
    	musicOn = true;
    }

    public void createMainMenu()
    {   	    	
    	Button newGame = new Button();
    	Button loadGame = new Button();
    	Button options = new Button();
    	Button exit = new Button();
    	Font gameFont = Font.font("Algerian", 36.0);
    	DropShadow textEffect = new DropShadow(); //used for the title, etc.
    	textEffect.setOffsetX(5);
    	textEffect.setOffsetY(5);
    	VBox titleBox = new VBox(2); //contains the title and the second vbox
    	VBox vbox  = new VBox(4);
    	Color textColor = Color.YELLOW;
    	
    	Label labelTitle = new Label("Dungeon Quest");    	
    	
    	mainMenu = new AnchorPane();
    	mainMenu.setPrefWidth(1024.0);
    	mainMenu.setPrefHeight(768.0);
    	mainMenu.setId("main menu");
    	
    	vbox.getChildren().add(newGame);
    	vbox.getChildren().add(loadGame);
    	vbox.getChildren().add(options);
    	vbox.getChildren().add(exit);
    	
    	//populate the title box
    	titleBox.getChildren().add(labelTitle);
    	titleBox.getChildren().add(vbox);
    	mainMenu.getChildren().add(titleBox);		
    	
    	//set the anchor values
    	AnchorPane.setTopAnchor(titleBox, 0.0);
    	AnchorPane.setRightAnchor(titleBox, 0.0);
    	AnchorPane.setBottomAnchor(titleBox, 0.0);
    	AnchorPane.setLeftAnchor(titleBox, 0.0);
    	

    	labelTitle.getStyleClass().add("label-header");
    	
    	
    	titleBox.setAlignment(Pos.CENTER); //center the vboxes
    	vbox.setAlignment(Pos.CENTER);
    	
    	VBox.setMargin(labelTitle, new Insets(-100, 0, 0, 0));
    	VBox.setMargin(newGame, new Insets(20, 0, 0, 0));
    	VBox.setMargin(loadGame, new Insets(20, 0, 0, 0));
    	VBox.setMargin(options, new Insets(20, 0, 0, 0));
    	VBox.setMargin(exit, new Insets(20, 0, 0, 0));
    	VBox.setMargin(vbox, new Insets(50, 0, 0, 0));
    	
    	vbox.setPrefWidth(100.0); //vertical box dimensions
    	vbox.setPrefHeight(200.0);
    	
    	//configure settings for the buttons 
    	newGame.setMaxHeight(60);
    	loadGame.setMaxHeight(60);
    	options.setMaxHeight(60);
    	exit.setMaxHeight(60);
    	
    	newGame.setMinWidth(500);
    	loadGame.setMinWidth(500);
    	options.setMinWidth(500);
    	exit.setMinWidth(500);
    	
    	newGame.setMaxWidth(600);
    	loadGame.setMaxWidth(600);
    	options.setMaxWidth(600);
    	exit.setMaxWidth(600);
    	newGame.setMinHeight(60);
    	loadGame.setMinHeight(60);
    	options.setMinHeight(60);
    	exit.setMinHeight(60);
    	
    	VBox.setVgrow(newGame, Priority.NEVER); //don't want buttons to grow vertically
    	VBox.setVgrow(loadGame, Priority.NEVER);
    	VBox.setVgrow(options, Priority.NEVER);
    	VBox.setVgrow(exit, Priority.NEVER);
    	
    	newGame.setFont(gameFont); //use the same font as we did for the title
    	loadGame.setFont(gameFont);
    	options.setFont(gameFont);
    	exit.setFont(gameFont);
    	
    	newGame.setText("New Game");
    	loadGame.setText("Load Game");
    	options.setText("Options");
    	exit.setText("Exit");

    	newGame.setAlignment(Pos.CENTER);
    	loadGame.setAlignment(Pos.CENTER);
    	options.setAlignment(Pos.CENTER);
    	exit.setAlignment(Pos.CENTER);
   	
    	newGame.setMnemonicParsing(false);
    	loadGame.setMnemonicParsing(false);
    	options.setMnemonicParsing(false);
    	exit.setMnemonicParsing(false);
    	
    	newGame.setTextFill(textColor);
    	loadGame.setTextFill(textColor);
    	options.setTextFill(textColor);
    	exit.setTextFill(textColor);

    	//FIX : Newgame -> paused -> newGame
    	newGame.setOnAction(e -> this.showGameInterface(true)); //can change this later, but temporary while ingamemenucontroller is in FXML
    	loadGame.setOnAction(e -> tryExistingGame()); //to do. Should just do it here and kill the game menu controller class when finished
    	options.setOnAction(e -> this.showOptionsMenu());
    	exit.setOnAction(e -> closeGame()); //terminate	
    }
    

    
    public void createOptionsMenu()
    {
    	Button toggleSound = new Button();
    	Button back = new Button();
    	
    	Font gameFont = Font.font("Algerian", 36.0);
    	DropShadow textEffect = new DropShadow(); //used for the title, etc.
    	textEffect.setOffsetX(5);
    	textEffect.setOffsetY(5);
    	VBox titleBox = new VBox(2); //contains the title and the second vbox
    	VBox vbox  = new VBox(2);
    	Color textColor = Color.YELLOW;
    	
    	Label labelTitle = new Label("Dungeon Quest");    	
    	
    	optionsMenu = new AnchorPane();
    	optionsMenu.setPrefWidth(1024.0);
    	optionsMenu.setPrefHeight(768.0);
    	optionsMenu.setId("options menu");
    	
    	vbox.getChildren().add(toggleSound);
    	vbox.getChildren().add(back);
    	
    	//populate the title box
    	titleBox.getChildren().add(labelTitle);
    	titleBox.getChildren().add(vbox);
    	optionsMenu.getChildren().add(titleBox);		
    	
    	//set the anchor values
    	AnchorPane.setTopAnchor(titleBox, 0.0);
    	AnchorPane.setRightAnchor(titleBox, 0.0);
    	AnchorPane.setBottomAnchor(titleBox, 0.0);
    	AnchorPane.setLeftAnchor(titleBox, 0.0);
    	
    	labelTitle.getStyleClass().add("label-header");
    	
    	titleBox.setAlignment(Pos.CENTER); //center the vboxes
    	vbox.setAlignment(Pos.CENTER);
    	
    	VBox.setMargin(labelTitle, new Insets(-100, 0, 0, 0));
    	VBox.setMargin(back, new Insets(20, 0, 0, 0));
    	VBox.setMargin(toggleSound, new Insets(20, 0, 0, 0));

    	VBox.setMargin(vbox, new Insets(50, 0, 0, 0));
    	
    	vbox.setPrefWidth(100.0); //vertical box dimensions
    	vbox.setPrefHeight(200.0);
    	
    	//configure settings for the buttons 
    	toggleSound.setMaxHeight(60);
    	toggleSound.setMinWidth(500);
    	toggleSound.setMaxWidth(600);
    	toggleSound.setMinHeight(60);
    	back.setMaxHeight(60);
    	back.setMinWidth(500);
    	back.setMaxWidth(600);
    	back.setMinHeight(60);    	
    	
    	VBox.setVgrow(toggleSound, Priority.NEVER); //don't want buttons to grow vertically
    	VBox.setVgrow(back, Priority.NEVER); 

    	toggleSound.setFont(gameFont); //use the same font as we did for the title
    	back.setFont(gameFont);
    	
    	back.setText("Return");
    	toggleSound.setText("Toggle Music");
    	
    	back.setAlignment(Pos.CENTER);
    	toggleSound.setAlignment(Pos.CENTER);
   	
    	back.setMnemonicParsing(false);
    	toggleSound.setMnemonicParsing(false);
    	
    	back.setTextFill(textColor);
    	toggleSound.setTextFill(textColor);
    	
    	back.setOnAction(e -> this.showMainMenu()); //can change this later, but temporary while ingamemenucontroller is in FXML
    	toggleSound.setOnAction(e -> this.toggleMusic());
    	
    
    	
    }
    
    void toggleMusic()
    {
    	if(!this.musicOn)
    	{
    		mPlayer.play();
    	}
    	else
    	{
    		mPlayer.pause();
    	}
    	musicOn = (musicOn == true) ? false : true; //toggle the bit
    }
    

    //screen display options
    public static void showVictory()
    {
    	rootLayout.setCenter(victory);
    }

    public static void showDeathScreen()
    {
    	rootLayout.setCenter(deathScreen);
    }
    
    public void showOptionsMenu()
    {
    	rootLayout.setCenter(optionsMenu);
    }
    
    public void closeGame()
    {
    	mPlayer.stop();
    	this.primaryStage.close();
    }
    
    public void tryExistingGame()
    {
    	this.game = Game.loadData();
    	if(this.game == null) //no game exists, so nothing can be loaded! Throw an error.
    	{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Invalid Action Selected");
            alert.setHeaderText("No Save File Found");
            alert.setContentText("Please select New Game to start over.");
            alert.showAndWait();
    	}
    	else
    	{
    		this.showGameInterface(false);
    	}
    }
    
    public void createInGameMenu()
    {
    	Button resume = new Button();
    	Button save = new Button();
    	Button save_Exit = new Button();
    	Button quit = new Button();
    	
    	//Button exit = new Button();
    	Font gameFont = Font.font("Algerian", 36.0);
    	DropShadow textEffect = new DropShadow(); //used for the title, etc.
    	textEffect.setOffsetX(5);
    	textEffect.setOffsetY(5);
    	VBox titleBox = new VBox(3); //contains the title and the second vbox
    	VBox vbox  = new VBox(4);
    	Color textColor = Color.YELLOW;
    	
    	Label labelTitle = new Label("Dungeon Quest");    	
    	Label paused = new Label("Game is Paused");    	
    	
    	inGameMenu = new AnchorPane();
    	inGameMenu.setPrefWidth(1024.0);
    	inGameMenu.setPrefHeight(768.0);
    	inGameMenu.setId("in game menu");
    	
    	vbox.getChildren().add(resume);
    	vbox.getChildren().add(save);
    	vbox.getChildren().add(save_Exit);
    	vbox.getChildren().add(quit);
    	
    	//populate the title box
    	titleBox.getChildren().add(labelTitle);
    	titleBox.getChildren().add(paused);
    	titleBox.getChildren().add(vbox);
    	inGameMenu.getChildren().add(titleBox);		
    	
    	//set the anchor values
    	AnchorPane.setTopAnchor(titleBox, 0.0);
    	AnchorPane.setRightAnchor(titleBox, 0.0);
    	AnchorPane.setBottomAnchor(titleBox, 0.0);
    	AnchorPane.setLeftAnchor(titleBox, 0.0);
    	
    	labelTitle.getStyleClass().add("label-header");
    	paused.getStyleClass().add("label-header2");
    	
    	
    	titleBox.setAlignment(Pos.CENTER); //center the vboxes
    	vbox.setAlignment(Pos.CENTER);
    	
    	VBox.setMargin(labelTitle, new Insets(-100, 0, 0, 0));
    	VBox.setMargin(resume, new Insets(20, 0, 0, 0));
    	VBox.setMargin(save, new Insets(20, 0, 0, 0));
    	VBox.setMargin(save_Exit, new Insets(20, 0, 0, 0));
    	VBox.setMargin(quit, new Insets(20, 0, 0, 0));    	
    	VBox.setMargin(vbox, new Insets(50, 0, 0, 0));
    	
    	vbox.setPrefWidth(100.0); //vertical box dimensions
    	vbox.setPrefHeight(200.0);
    	
    	//configure settings for the buttons 
    	resume.setMaxHeight(60);
    	save.setMaxHeight(60);
    	save_Exit.setMaxHeight(60);
    	quit.setMaxHeight(60);

    	
    	resume.setMinWidth(500);
    	save.setMinWidth(500);
    	save_Exit.setMinWidth(500);
    	quit.setMinWidth(500);
    	
    	resume.setMaxWidth(600);
    	save_Exit.setMaxWidth(600);
    	save.setMaxWidth(600);
    	quit.setMaxWidth(600);

    	
    	resume.setMinHeight(60);
    	save_Exit.setMinHeight(60);
    	save.setMinHeight(60);
    	quit.setMinHeight(60);

    	
    	VBox.setVgrow(resume, Priority.NEVER); //don't want buttons to grow vertically
    	VBox.setVgrow(save_Exit, Priority.NEVER);
    	VBox.setVgrow(save, Priority.NEVER);
    	VBox.setVgrow(quit, Priority.NEVER);

    	
    	resume.setFont(gameFont); //use the same font as we did for the title
    	save_Exit.setFont(gameFont);
    	save.setFont(gameFont);
    	quit.setFont(gameFont);

    	
    	resume.setText("Resume");
    	save.setText("Save");
    	save_Exit.setText("Save & Exit");
    	quit.setText("Return to Main Menu");
    	
    	resume.setAlignment(Pos.CENTER);
    	save.setAlignment(Pos.CENTER);
    	save_Exit.setAlignment(Pos.CENTER);
    	quit.setAlignment(Pos.CENTER);
    	
    	resume.setMnemonicParsing(false);
    	save.setMnemonicParsing(false);
    	save_Exit.setMnemonicParsing(false);
    	quit.setMnemonicParsing(false);    	
    	
    	resume.setTextFill(textColor);
    	save.setTextFill(textColor);
    	save_Exit.setTextFill(textColor);
    	quit.setTextFill(textColor);
    	
    	resume.setOnAction(e -> this.showGameInterface(false)); //change me to not be a new game 
    	save.setOnAction(e -> saveGame()); //to do. Should just do it here and kill the game menu controller class when finished
    	save_Exit.setOnAction(e -> saveAndExit()); //terminate	
    	quit.setOnAction(e -> this.showMainMenu()); //terminate	    	
    }

    
    public void createDeathScreen()
    {
    	
    	Button toMenu = new Button(); 
    	Button quit = new Button();
    	
    	Font gameFont = Font.font("Algerian", 36.0);

    	VBox titleBox = new VBox(2); //contains the title and the second vbox
    	VBox vbox  = new VBox(2);
    	Color textColor = Color.YELLOW;
    	
    	Label deathNote = new Label("Oh Dear, You Have Died!");    	
    	
    	deathScreen = new AnchorPane();
    	deathScreen.setPrefWidth(1024.0);
    	deathScreen.setPrefHeight(768.0);
    	deathScreen.setId("death screen");
    	
    	vbox.getChildren().add(toMenu);
    	vbox.getChildren().add(quit);
    	
    	//populate the title box
    	titleBox.getChildren().add(deathNote);
    	titleBox.getChildren().add(vbox);
    	deathScreen.getChildren().add(titleBox);	
    	
    	//set the anchor values
    	AnchorPane.setTopAnchor(titleBox, 0.0);
    	AnchorPane.setRightAnchor(titleBox, 0.0);
    	AnchorPane.setBottomAnchor(titleBox, 0.0);
    	AnchorPane.setLeftAnchor(titleBox, 0.0);
    	
    	deathNote.getStyleClass().add("label-header");    	
    	
    	titleBox.setAlignment(Pos.CENTER); //center the vboxes
    	vbox.setAlignment(Pos.CENTER);
    	
    	VBox.setMargin(deathNote, new Insets(-100, 0, 0, 0));
    	VBox.setMargin(toMenu, new Insets(20, 0, 0, 0));
    	VBox.setMargin(quit, new Insets(20, 0, 0, 0));    	
    	VBox.setMargin(vbox, new Insets(50, 0, 0, 0));
    	
    	vbox.setPrefWidth(100.0); //vertical box dimensions
    	vbox.setPrefHeight(200.0);
    	
    	//configure settings for the buttons 
    	toMenu.setMaxHeight(60);
    	quit.setMaxHeight(60);

    	
    	toMenu.setMinWidth(500);
    	quit.setMinWidth(500);
    	
    	toMenu.setMaxWidth(600);
    	quit.setMaxWidth(600);

    	
    	toMenu.setMinHeight(60);
    	quit.setMinHeight(60);

    	
    	VBox.setVgrow(toMenu, Priority.NEVER); //don't want buttons to grow vertically
    	VBox.setVgrow(quit, Priority.NEVER);

    	
    	toMenu.setFont(gameFont); //use the same font as we did for the title
    	quit.setFont(gameFont);

    	
    	toMenu.setText("Return to Main Menu");
    	quit.setText("Give Up");
    	
    	toMenu.setAlignment(Pos.CENTER);
    	quit.setAlignment(Pos.CENTER);
    	
    	toMenu.setMnemonicParsing(false);
    	quit.setMnemonicParsing(false);    	
    	
    	toMenu.setTextFill(textColor);
    	quit.setTextFill(textColor);
    	
    	quit.setOnAction(e -> closeGame()); //end session
    	toMenu.setOnAction(e -> this.showMainMenu()); //show the main menu	    	
    }
    
    public void createVictoryScreen()
    {    	
    	Button quit = new Button();
    	Font gameFont = Font.font("Algerian", 36.0);

    	VBox titleBox = new VBox(2); //contains the title and the second vbox
    	VBox vbox  = new VBox(1);
    	Color textColor = Color.YELLOW;
    	
    	Label victoryNote = new Label("Well Done!");    	
    	
    	victory = new AnchorPane();
    	victory.setPrefWidth(1024.0);
    	victory.setPrefHeight(768.0);
    	victory.setId("victory screen");
    	
    	vbox.getChildren().add(quit);
    	
    	//populate the title box
    	titleBox.getChildren().add(victoryNote);
    	titleBox.getChildren().add(vbox);
    	victory.getChildren().add(titleBox);	
    	
    	//set the anchor values
    	AnchorPane.setTopAnchor(titleBox, 0.0);
    	AnchorPane.setRightAnchor(titleBox, 0.0);
    	AnchorPane.setBottomAnchor(titleBox, 0.0);
    	AnchorPane.setLeftAnchor(titleBox, 0.0);
    	
    	victoryNote.getStyleClass().add("label-header");    	
    	victoryNote.setWrapText(true);//make this label wrap around
    	
    	titleBox.setAlignment(Pos.CENTER); //center the vboxes
    	vbox.setAlignment(Pos.CENTER);
    	victoryNote.setAlignment(Pos.CENTER);
    	
    	VBox.setMargin(victory, new Insets(-100, 0, 0, 0));
    	VBox.setMargin(quit, new Insets(20, 0, 0, 0));    	
    	VBox.setMargin(vbox, new Insets(50, 0, 0, 0));
    	
    	vbox.setPrefWidth(100.0); //vertical box dimensions
    	vbox.setPrefHeight(200.0);
    	
    	//configure settings for the buttons 
    	quit.setMaxHeight(60);
    	quit.setMinWidth(500);
    	quit.setMaxWidth(600);    	
    	quit.setMinHeight(60);
    	VBox.setVgrow(quit, Priority.NEVER);
    	quit.setFont(gameFont);
    	quit.setText("Exit");
    	quit.setAlignment(Pos.CENTER);
    	quit.setMnemonicParsing(false);    	
    	quit.setTextFill(textColor);    	
    	quit.setOnAction(e -> closeGame()); //end session
    }
    
    
    
    public void saveGame()
    {
    	Game.saveData(game);
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Save Complete");
        alert.setHeaderText("You have saved the game.");
        alert.showAndWait();
    }
    
    //used for lambda on save and exit
    public void saveAndExit()
    {
    	Game.saveData(game);
    	closeGame();

    }
    
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Dungeon Quest");
        initRootLayout();
        showMainMenu();
                
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {

    	rootLayout = new BorderPane();
        rootLayout.setPrefHeight(768.0);
       	rootLayout.setPrefWidth(1024.0);
        	
        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        scene.getStylesheets().add
        (MainApp.class.getResource("view/curse.css").toExternalForm());   //retrieve the CSS file      
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * Shows the main menu inside the root layout.
     */
    public void showMainMenu() {
    	rootLayout.setCenter(mainMenu);
    }
    
    /**
     * Begins game. TODO: Implement loading from game or new game
     * 
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public void showGameInterface(boolean clearFile) {

    	if(game == null || clearFile == true) //new game!
    	{    		
    		TextInputDialog nameTrack = new TextInputDialog("<Your name here>");
    		nameTrack.setHeaderText("Enter your Name");
    		nameTrack.setContentText("Please enter your name to continue.");
    		Optional<String> result = nameTrack.showAndWait();
    		if(result.isPresent())
    		{
    			game = new Game(result.get());
    			inGameInterface = new GameGUI(game.getPlayer(), false);
    	    	rootLayout.setCenter(inGameInterface);        		
    		}
    	}
    	else
    	{
			inGameInterface = new GameGUI(game.getPlayer(), true);
    		rootLayout.setCenter(inGameInterface);
    	}

    }
    
    /**
     * Shows the main menu inside the root layout.
     */
    public static void showInGameMenu() {
    	rootLayout.setCenter(inGameMenu);
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
}