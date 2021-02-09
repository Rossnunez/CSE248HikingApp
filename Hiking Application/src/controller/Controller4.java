package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.imageio.ImageIO;

import app.Main;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.AccountType;
import model.HikingHistory;
import model.HikingUncompleted;
import model.Status;
import model.Trail;
import model.User;

public class Controller4 implements Initializable {
	public FileChooser fileChooser;
	public File filePath;

	public TreeMap<String, User> userMap;
	public HashSet<Trail> trailSet;

	public TextField usernameField;
	public PasswordField passwordField;
	public TextField firstnameField;
	public TextField lastnameField;
	public TextField phonenumberField;

	public String image;
	public ImageView imageField;
	public Image defaultImage;

	public void goBackPane(ActionEvent event) throws IOException {
		Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/view1.fxml"));
		Scene secondScene = new Scene(secondRoot);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(secondScene);
		window.show();
	}

	public void changeImage(MouseEvent event) throws IOException {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		fileChooser = new FileChooser();
		
		this.setFilePath(fileChooser.showOpenDialog(stage));
		
		try {
			image = filePath.getPath();
			BufferedImage bufferedImage = ImageIO.read(filePath);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			imageField.setImage(image);
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void signUp(ActionEvent event) {
		String username = usernameField.getText();
		if (!userMap.containsKey(username)) {
			String firstname = firstnameField.getText();
			String lastname = lastnameField.getText();
			String password = passwordField.getText();
			String phonenumber = phonenumberField.getText();
			TreeSet<HikingHistory> hikingHistorySet = new TreeSet<HikingHistory>();
			TreeSet<HikingUncompleted> hikingUncompletedSet = new TreeSet<HikingUncompleted>();

			User user = new User(username, password, firstname, lastname, phonenumber, image, AccountType.USER,
					hikingHistorySet,hikingUncompletedSet, Status.ENABLED);
			userMap.put(username, user);

			usernameField.clear();
			passwordField.clear();
			firstnameField.clear();
			lastnameField.clear();
			phonenumberField.clear();
			imageField.setImage(defaultImage);
			image = "C:\\Users\\nross\\Desktop\\CSE248Portfolio\\CSE248HikingApp\\Hiking Application\\RawData\\user.png";
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("ACCOUNT COMPLETE");
			alert.setHeaderText("Thank's for signing up!");
			alert.setContentText("Account has been created");
			alert.showAndWait();
			
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ACCOUNT ERROR");
			alert.setHeaderText("Username already exist");
			alert.setContentText("Enter a unique username");
			alert.showAndWait();
		}
	}

	public File getFilePath() {
		return filePath;
	}

	public void setFilePath(File filePath) {
		this.filePath = filePath;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		userMap = Main.getUserMap();
		trailSet = Main.getTrailSet();
		defaultImage = imageField.getImage();
		image = "\\RawData\\user.png";
	}

}
