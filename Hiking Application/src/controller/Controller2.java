package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.controlsfx.control.CheckComboBox;

import app.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.HikeType;
import model.HikingHistory;
import model.Level;
import model.Trail;
import model.User;

public class Controller2 implements Initializable {
	@FXML
	public FileChooser fileChooser;
	public File filePath;

	public TreeMap<String, User> userMap;
	public HashSet<Trail> trailSet;
	public User user;
	public String username;

	// edit tab
	public Text usernameText;
	public TextField passwordField;
	public TextField firstnameField;
	public TextField lastnameField;
	public TextField phonenumberField;

	public String image;
	public ImageView imageField;
	public Image defaultImage;
	public BufferedImage bufferedImage;
	

	// start trails tab
	public CheckComboBox<String> difficultyBox;
	public CheckComboBox<String> typeBox;
	
	static HikeType answerType;
	static Level answerLevel;
	
	public Text selectedTrail;
	public ComboBox<String> sortByBox;
	public ObservableList<String> searchList = FXCollections.observableArrayList("NAME", "LENGTH", "EVALATION", "LEVEL",
			"TYPE");
	public ObservableList<Trail> trail;
	public TableView<Trail> table = new TableView<Trail>();
	public TableColumn<Trail, String> trailName;
	public TableColumn<Trail, String> trailAddress;
	public TableColumn<Trail, String> length;
	public TableColumn<Trail, String> evelation;
	public TableColumn<Trail, String> level;
	public TableColumn<Trail, String> type;
	public TextField searchField;

	
	//hiking history tab//
	public void searchForTrailsHistory(ActionEvent event) {
		
	}
	
	public void completeTrail(ActionEvent event) {
		
	}
	
	public void selectedTrailHistory(MouseEvent event) {
		
	}
	// start trail tab//
	public void selectedTrail(MouseEvent event) {
		try {
			selectedTrail.setText(table.getSelectionModel().getSelectedItem().getTrailName());
		} catch (NullPointerException e) {
			//
		}
	}
	
	public void startTrail(ActionEvent event) {
		Trail trail = table.getSelectionModel().getSelectedItem();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY hh:mm:ss");
		Date currentTime = new Date(System.currentTimeMillis());
		String current = sdf.format(currentTime);
		TreeSet<String> images = new TreeSet<String>();
		HikingHistory hikingHistory = new HikingHistory(trail.getTrailAddress(), current, "In Progress", trail.getMiles(), "In Progress", images, "In Progress");
		user.getHikingHistorySet().add(hikingHistory);
		table.getItems().remove(table.getSelectionModel().getSelectedItem());
		
		
	}
	
	public static void displayLevel() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Types of Levels");
		window.setMinWidth(300);
		window.setMinHeight(250);
		Label label = new Label();
		label.setText("Select a Trail Level");
		label.setAlignment(Pos.TOP_CENTER);
		
		Button easyButton = new Button("Hard Difficulty");
		Button modButton = new Button("Moderate Difficulty");
		Button hardButton = new Button("Easy Difficulty");
		
		easyButton.setOnAction(e ->{
			answerLevel = Level.EASY;
			window.close();
		});
		
		modButton.setOnAction(e ->{
			answerLevel = Level.MODERATE;
			window.close();
		});
		
		hardButton.setOnAction(e ->{
			answerLevel = Level.HARD;
			window.close();
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, easyButton, modButton, hardButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
	
	public static void displayType() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Types of Trails");
		window.setMinWidth(300);
		window.setMinHeight(250);
		Label label = new Label();
		label.setText("Select a Trail Type");
		label.setAlignment(Pos.TOP_CENTER);
		
		Button loopButton = new Button("Loop Trail");
		Button outButton = new Button("Out and Back Trail");
		Button pointButton = new Button("Point Trail");
		
		loopButton.setOnAction(e ->{
			answerType = HikeType.LOOP;
			window.close();
		});
		
		outButton.setOnAction(e ->{
			answerType = HikeType.OUT_AND_BACK;
			window.close();
		});
		
		pointButton.setOnAction(e ->{
			answerType = HikeType.POINT_TO_POINT;
			window.close();
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, pointButton, outButton, loopButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
	
	public void searchForTrails(ActionEvent event) {
		trail.clear();
		
		String search = searchField.getText();
		String type = sortByBox.getValue();
		
		if(type.contentEquals("NAME")) {
			List<Trail> result = trailSet.stream()
					.filter(trail -> trail.getTrailName().startsWith(search)).collect(Collectors.toList());
			trail.addAll(result);
			table.setItems(trail);
		}else if(type.contentEquals("EVELATION")) {
			List<Trail> result = trailSet.stream()
					.filter(trail -> String.valueOf(trail.getElevation()).startsWith(search)).collect(Collectors.toList());
			trail.addAll(result);
			table.setItems(trail);
		} else if(type.contentEquals("LENGTH")) {
			List<Trail> result = trailSet.stream()
					.filter(trail -> String.valueOf(trail.getMiles()).startsWith(search)).collect(Collectors.toList());
			trail.addAll(result);
			table.setItems(trail);
		} else if(type.contentEquals("TYPE")) {
			displayType();
			List<Trail> result = trailSet.stream()
					.filter(trail -> trail.getType().equals(answerType)).collect(Collectors.toList());
			trail.addAll(result);
			table.setItems(trail);
		} else if(type.contentEquals("LEVEL")) {
			displayLevel();
			List<Trail> result = trailSet.stream()
					.filter(trail -> trail.getDifficulty().equals(answerLevel)).collect(Collectors.toList());
			trail.addAll(result);
			table.setItems(trail);
		}
	}

	// edit tab
	public void changeImage(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		fileChooser = new FileChooser();

		this.setFilePath(fileChooser.showOpenDialog(stage));

		try {
			//image = filePath.getPath();
			BufferedImage bufferedImage = ImageIO.read(filePath);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			imageField.setImage(image);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		userMap.get(username).setImage(filePath.getPath());
	}

	public void logOutPane(ActionEvent event) throws IOException {
		Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/view1.fxml"));
		Scene secondScene = new Scene(secondRoot);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
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

	// initialize variables
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
		
		//update user tab
		try {
			bufferedImage = ImageIO.read(filePath);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			imageField.setImage(image);
		} catch (IOException e) {
			defaultImage = imageField.getImage();
			//image = "\\RawData\\user.png";
		}
		
		username = Controller1.getUsername();
		user = Controller1.getUser();

		usernameText.setText(username);
		passwordField.setText(user.getPassword());
		firstnameField.setText(user.getFirstName());
		lastnameField.setText(user.getLastName());
		phonenumberField.setText(user.getPhoneNumber());
		
		final ObservableList<String> difficultyStrings = FXCollections.observableArrayList();
		difficultyStrings.add("HARD");
		difficultyStrings.add("MODERATE");
		difficultyStrings.add("EASY");
		difficultyBox.getItems().addAll(difficultyStrings);
		
		final ObservableList<String> typeStrings = FXCollections.observableArrayList();
		typeStrings.add("LOOP");
		typeStrings.add("OUT AND BACK");
		typeStrings.add("POINT TO POINT");
		typeBox.getItems().addAll(typeStrings);

		// start trails tab
		trail = FXCollections.observableArrayList();
		trailAddress.setCellValueFactory(new PropertyValueFactory<Trail, String>("trail address"));
		trailName.setCellValueFactory(new PropertyValueFactory<Trail, String>("trail name"));
		level.setCellValueFactory(new PropertyValueFactory<Trail, String>("level"));
		evelation.setCellValueFactory(new PropertyValueFactory<Trail, String>("evelation"));
		type.setCellValueFactory(new PropertyValueFactory<Trail, String>("type"));
		sortByBox.setItems(searchList);
		//trail = FXCollections.observableArrayList();

	}

}
