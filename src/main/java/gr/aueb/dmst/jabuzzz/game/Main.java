package gr.aueb.dmst.jabuzzz.game;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class Main extends Application {
    
    public static Stage primaryStage;
    private static AnchorPane mainLayoutAnchorPane;
    private static BorderPane mainLayoutBorderPane;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Main.primaryStage = primaryStage;
        Main.primaryStage.setTitle("SKABUZZZ");
        Image ico = new Image("file:src/main/resources/photos/GameIcon.png");
        Main.primaryStage.getIcons().add(ico);
        showMainMenu();
        
    }
    
    /**
     * showInstructions loads the Scene object that contains the game's instructions.
     * @throws IOException
     */
    public void showInstructions() throws IOException {
        String resource = "/Instructions.fxml";
        this.show(mainLayoutAnchorPane, resource);
    }
    
    /**
     * showMainView loads the Scene object that contains the game's main act,
     * namely the questions and buzzer.
     * @throws IOException
     */
    public void showMainView() throws IOException {
        String resource = "/MainView.fxml";
        this.show(mainLayoutBorderPane, resource);
    }
    
    /**
     * showGameSetup loads the Scene object that contains the game's settings
     * that allows players to opt their game style.
     * @throws IOException
     */
    public void showGameSetUp() throws IOException {
        String resource = "/GameSetUp.fxml";
        this.show(mainLayoutAnchorPane, resource);
    }
    
    /**
     * showMainMenu loads the Scene object that contains the game's main menu.
     * @throws IOException
     */
    public void showMainMenu() throws IOException {
        String resource = "/MainMenu.fxml";
        this.show(mainLayoutAnchorPane, resource);
    }
    
    public void showEndOfGame() throws IOException {
        String resource = "/EndOfGame.fxml";
        this.show(mainLayoutAnchorPane, resource);
    }

    /*
     * show is a private method that needs two parameters: a Parent object and a String
     * Parent is an indirect superclass of both AnchorPane and BorderPane
     *                   --> AnchorPane
     * Parent --> Pane -{
     *                   --> BorderPane
     * Parent is used instead of Pane superclass, because it is able to include every
     * graphic Node (Node is a superclass of Parent).
     * 
     * resource contains the path that leads to the used FXML file.
     */
    private void show(Parent mainLayoutParent, String fxmlResource) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/main.fxml"));
        loader.setLocation(Main.class.getResource(fxmlResource));
        mainLayoutParent = loader.load();
        Scene scene = new Scene (mainLayoutParent);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
