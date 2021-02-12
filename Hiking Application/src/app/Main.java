package app;
	
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.AccountType;
import model.HikeType;
import model.HikingHistory;
import model.HikingUncompleted;
import model.Level;
import model.Status;
import model.StorageBag;
import model.Trail;
import model.User;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private static TreeMap<String, User> userMap;
	private static HashSet<Trail> trailSet;
	
	public void start(Stage primaryStage) throws IOException {
		userMap = new TreeMap<String, User>();
		trailSet = new HashSet<Trail>(50000, (float)0.5);
		
		FileInputStream fis = new FileInputStream("RawData/StartUpData.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		StorageBag storageBag = null;
		try {
			storageBag = (StorageBag) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Main.setUserMap(storageBag.getUserMap());
		Main.setTrailSet(storageBag.getTrailSet());
		
		userMap = storageBag.getUserMap();
		trailSet = storageBag.getTrailSet();
		ois.close();
		fis.close();
		
		Parent root = FXMLLoader.load(getClass().getResource("/view/view1.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("WalkinWithStyle.css").toExternalForm());
		primaryStage.setTitle("Hiking Application");
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
	
	public static void setUserMap(TreeMap<String,User> treeMap){
		userMap = treeMap;
	}
	
	public static void setTrailSet(HashSet<Trail> hashSet){
		trailSet = hashSet;
	}
}
