package app;
	
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javafx.application.Application;
import javafx.stage.Stage;
import model.HikingHistory;
import model.Trail;
import model.User;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private static TreeMap<String, User> userMap;
	private static HashSet<Trail> trailSet;
	
	public void start(Stage primaryStage) {
		
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
