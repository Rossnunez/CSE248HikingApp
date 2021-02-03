package app;
	
import java.io.IOException;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.AccountType;
import model.HikeType;
import model.HikingHistory;
import model.Level;
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
		
		Trail trail = new Trail("green", "123 bb street", 10, 30, Level.EASY, HikeType.LOOP);
		Trail trail2 = new Trail("green1", "123 bb street", 20, 40, Level.EASY, HikeType.OUT_AND_BACK);
		Trail trail3 = new Trail("green2", "123 bb street", 30, 50, Level.MODERATE, HikeType.POINT_TO_POINT);
		Trail trail4 = new Trail("green3", "123 bb street", 40, 60, Level.EASY, HikeType.LOOP);
		Trail trail5 = new Trail("green4", "123 bb street", 10, 70, Level.HARD, HikeType.OUT_AND_BACK);
		
		trailSet.add(trail);
		trailSet.add(trail2);
		trailSet.add(trail3);
		trailSet.add(trail4);
		trailSet.add(trail5);
		
		User admin = new User("user", "", "","","","",AccountType.ADMIN,null,null);
		userMap.put("user", admin);
		
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
