package controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Trail;
import model.User;

public class Controller3 implements Initializable {
	public TreeMap<String,User> userMap;
	public HashSet<Trail> trailSet;
	public User user;
	public String username;
	public Text adminUserText;
	
	public void editUsersPane(ActionEvent event) throws IOException {
		Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/viewEditUsers.fxml"));
		Scene secondScene = new Scene(secondRoot);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(secondScene);
		window.show();
	}
	
	public void editTrailsPane(ActionEvent event) throws IOException {
		Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/viewEditTrails.fxml"));
		Scene secondScene = new Scene(secondRoot);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(secondScene);
		window.show();
	}
	
	public void userViewPane(ActionEvent event) throws IOException {
		Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/view2.fxml"));
		Scene secondScene = new Scene(secondRoot);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(secondScene);
		window.show();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		username = Controller1.getUsername();
		user = Controller1.getUser();
		adminUserText.setText(username);
	}
}
