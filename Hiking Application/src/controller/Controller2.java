package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import app.Main;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Trail;
import model.User;

public class Controller2 implements Initializable{
	@FXML
	public FileChooser fileChooser;
	public File filePath;
	
	public TreeMap<String, User> userMap;
	public HashSet<Trail> trailSet;
	public User user;
	public String username;
	
	//edit tab
	public Text usernameText;
	public TextField passwordField;
	public TextField firstnameField;
	public TextField lastnameField;
	public TextField phonenumberField;
	
	public String image;
	public ImageView imageField;
	public Image defaultImage;
	public BufferedImage bufferedImage;
	
	//edit tab
	public void changeImage(MouseEvent event) {
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
		userMap.get(username).setImage(filePath.getPath());
	}
	
	public void logOutPane(ActionEvent event) throws IOException {
		Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/view1.fxml"));
		Scene secondScene = new Scene(secondRoot);
		Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
		window.setScene(secondScene);
		window.show();		
	}
	
	public void editPassword(ActionEvent event) {
		TextInputDialog tid = new TextInputDialog("Enter your Password:");
		tid.setHeaderText("Edit Password");
		tid.showAndWait();
		String password = tid.getEditor().getText();
		user.setPassword(password);
		passwordField.setText(password);
	}
	
	
	public void editFirstname(ActionEvent event) {
		TextInputDialog tid = new TextInputDialog("Enter your First Name:");
		tid.setHeaderText("Edit First Name");
		tid.showAndWait();
		String firstname = tid.getEditor().getText();
		user.setFirstName(firstname);
		firstnameField.setText(firstname);
	}
	
	public void editLastname(ActionEvent event) {
		TextInputDialog tid = new TextInputDialog("Enter your Last Name:");
		tid.setHeaderText("Edit Last Name");
		tid.showAndWait();
		String lastname = tid.getEditor().getText();
		user.setLastName(lastname);
		lastnameField.setText(lastname);
	}
	
	public void editPhonenumber(ActionEvent event) {
		TextInputDialog tid = new TextInputDialog("Enter your Phonenumber");
		tid.setHeaderText("Edit Phonenumber");
		tid.showAndWait();
		String phonenumber = tid.getEditor().getText();
		user.setPhoneNumber(phonenumber);
		phonenumberField.setText(phonenumber);
	}
	
	//initialize variables
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
		
		File file = new File(Controller1.getUser().getImage());
		setFilePath(file);
		
		try {
			bufferedImage = ImageIO.read(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image image = SwingFXUtils.toFXImage(bufferedImage, null);
		imageField.setImage(image);
		
		username = Controller1.getUsername();
		user = Controller1.getUser();
		
		//update tab
		usernameText.setText(username);
		passwordField.setText(user.getPassword());
		firstnameField.setText(user.getFirstName());
		lastnameField.setText(user.getLastName());
		phonenumberField.setText(user.getPhoneNumber());
		
		
		
		
	}

}
