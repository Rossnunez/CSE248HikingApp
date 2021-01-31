package app;
	
import java.io.IOException;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.HikingHistory;
import model.Trail;
import model.User;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private static TreeMap<String, User> userMap;
	private static HashSet<Trail> trailSet;
	
	public void start(Stage primaryStage) throws IOException {
		userMap = new TreeMap<String, User>();
		trailSet = new HashSet<Trail>(50000, (float)0.5);
		
		Parent root = FXMLLoader.load(getClass().getResource("/view/view1.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static TreeMap<String,User> getUserMap(){
		return userMap;
	}
	
	public static HashSet<Trail> getTrailSet(){
		return trailSet;
	}
}
