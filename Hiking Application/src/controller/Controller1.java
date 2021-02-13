package controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.TreeMap;

import app.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.AccountType;
import model.Status;
import model.StorageBag;
import model.Trail;
import model.User;

public class Controller1 implements Initializable {
	public TreeMap<String, User> userMap;
	public HashSet<Trail> trailSet;
	public StorageBag storageBag;

	public TextField usernameField;
	public PasswordField passwordField;

	private static String username;
	private static User userInfo;

	public void importData(ActionEvent event) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("RawData/SavedData.dat");
		ObjectInputStream ois = null;
		try {
			 ois = new ObjectInputStream(fis);
		} catch(EOFException e) {
			Alert exportAlert = new Alert(AlertType.WARNING);
			exportAlert.setTitle("WARNING Alert");
			exportAlert.setHeaderText("Restore Incomplete");
			exportAlert.setContentText("You must export before you can import!");
			exportAlert.showAndWait();
			fis.close();
			return;
		}
		
		storageBag = (StorageBag) ois.readObject();
		
		Main.setUserMap(storageBag.getUserMap());
		Main.setTrailSet(storageBag.getTrailSet());
		
		userMap = storageBag.getUserMap();
		trailSet = storageBag.getTrailSet();
		ois.close();
		fis.close();
		Alert exportAlert = new Alert(AlertType.CONFIRMATION);
		exportAlert.setTitle("Confirmation Alert");
		exportAlert.setHeaderText("Restore Completed");
		exportAlert.setContentText("Your data has been imported!");
		exportAlert.showAndWait();

	}

	public void exportData(ActionEvent event) throws IOException {
		storageBag = new StorageBag(userMap, trailSet);
		
		FileOutputStream fos = new FileOutputStream("RawData/SavedData.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(storageBag);
		oos.close();
		fos.close();
		Alert exportAlert = new Alert(AlertType.CONFIRMATION);
		exportAlert.setTitle("Confirmation Alert");
		exportAlert.setHeaderText("Save Completed");
		exportAlert.setContentText("Your data has been exported!");
		exportAlert.showAndWait();
	}

	public void exit(ActionEvent event) {
		System.exit(0);
	}

	public void signInPane(ActionEvent event) throws IOException {
		String user = usernameField.getText();
		String pass = passwordField.getText();

		if (userMap.containsKey(user) && userMap.get(user).getPassword().contentEquals(pass)) {

			if (userMap.get(user).getStatus().equals(Status.ENABLED)) {
				if (userMap.get(user).getAccountType() == AccountType.USER) {
					username = user;
					userInfo = userMap.get(user);

					Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/view2.fxml"));
					Scene secondScene = new Scene(secondRoot);
					Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
					window.setScene(secondScene);
					window.show();
				} else {
					username = user;
					userInfo = userMap.get(user);
					Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/view3.fxml"));
					Scene secondScene = new Scene(secondRoot);
					Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
					window.setScene(secondScene);
					window.show();
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR WARNING");
				alert.setContentText("Your account has been disabled.");
				alert.setHeaderText("Disabled Account");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR WARNING");
			alert.setContentText("Username or Password is incorrect.");
			alert.setHeaderText("Login Not Found");
			alert.showAndWait();

		}
	}

	public void createAccountPane(MouseEvent event) throws IOException {
		Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/view4.fxml"));
		Scene secondScene = new Scene(secondRoot);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(secondScene);
		window.show();
	}

	public static String getUsername() {
		return username;
	}

	public static User getUser() {
		return userInfo;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		userMap = Main.getUserMap();
		trailSet = Main.getTrailSet();

	}

}
