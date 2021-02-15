package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.controlsfx.control.CheckComboBox;

import app.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.HikeType;
import model.HikingHistory;
import model.HikingUncompleted;
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
	public static HikingHistory hikingHistoryForPictures;

	// edit tab //
	public Text usernameText;
	public TextField passwordField;
	public TextField firstnameField;
	public TextField lastnameField;
	public TextField phonenumberField;

	public String image;
	public ImageView imageField;
	public Image defaultImage;
	public BufferedImage bufferedImage;

	// start trails tab //
	public CheckComboBox<Level> difficultyBox;
	public CheckComboBox<HikeType> typeBox;

	public Slider lengthSlider;
	public Slider evelationSlider;

	public Text selectedTrail;
	public ObservableList<Trail> trail;
	public TableView<Trail> table = new TableView<Trail>();
	public TableColumn<Trail, String> trailName;
	public TableColumn<Trail, String> trailAddress;
	public TableColumn<Trail, String> length;
	public TableColumn<Trail, String> elevation;
	public TableColumn<Trail, String> difficulty;
	public TableColumn<Trail, String> type;
	public TextField searchField;

	// hikes in progress tab//
	public CheckComboBox<Level> difficultyBoxUncompleted;
	public CheckComboBox<HikeType> typeBoxUncompleted;

	public Slider lengthSliderUncompleted;
	public Slider evelationSliderUncompleted;

	public Text selectedTrailUncompleted;
	public ObservableList<HikingUncompleted> trailUncompleted;
	public TableView<HikingUncompleted> tableUncompleted = new TableView<HikingUncompleted>();
	public TableColumn<HikingUncompleted, String> dateStartedUncompleted;
	public TableColumn<HikingUncompleted, String> trailNameUncompleted;
	public TableColumn<HikingUncompleted, String> trailAddressUncompleted;
	public TableColumn<HikingUncompleted, String> lengthUncompleted;
	public TableColumn<HikingUncompleted, String> elevationUncompleted;
	public TableColumn<HikingUncompleted, String> difficultyUncompleted;
	public TableColumn<HikingUncompleted, String> typeUncompleted;
	public TextField searchFieldUncompleted;

	// hiking history tab//
	public CheckComboBox<Level> difficultyBoxHistory;
	public CheckComboBox<HikeType> typeBoxHistory;

	public Slider lengthSliderHistory;
	public Slider evelationSliderHistory;

	public Text selectedTrailHistory;
	public ObservableList<HikingHistory> trailHistory;
	public TableView<HikingHistory> tableHistory = new TableView<HikingHistory>();
	public TableColumn<HikingHistory, String> dateStartedHistory;
	public TableColumn<HikingHistory, String> trailNameHistory;
	public TableColumn<HikingHistory, String> lengthHistory;
	public TableColumn<HikingHistory, String> elevationHistory;
	public TableColumn<HikingHistory, String> difficultyHistory;
	public TableColumn<HikingHistory, String> typeHistory;
	public TableColumn<HikingHistory, String> trailDurationHistory;
	public TableColumn<HikingHistory, String> trailPaceHistory;
	public TableColumn<HikingHistory, String> dateCompletedHistory;
	public TextField searchFieldHistory;

	// hiking history tab//
	public void showHistoryHikes(Event event) {
		trailHistory.clear();
		trailHistory.addAll(user.getHikingHistorySet());
		tableHistory.setItems(trailHistory);

	}

	public static HikingHistory getHikingHistory() {
		return hikingHistoryForPictures;
	}

	public void searchForTrailsHistory(ActionEvent event) {
		trailHistory.clear();
		String search = searchFieldHistory.getText().toLowerCase();

		List<HikingHistory> firstResult = user.getHikingHistorySet().stream()
				.filter(hikingUncompleted -> hikingUncompleted.getTrail().getTrailName().toLowerCase().contains(search))
				.collect(Collectors.toList());
		List<HikingHistory> difficultyResult = null;
		List<HikingHistory> typeResult = null;
		List<HikingHistory> rangeResult;
		///////////////////////////////
		ObservableList<Level> difficultyList = difficultyBoxHistory.getCheckModel().getCheckedItems();
		if (difficultyList.size() == 2) {
			difficultyResult = firstResult.stream()
					.filter(hikingHistory -> hikingHistory.getTrail().getDifficulty().equals(difficultyList.get(0))
							|| hikingHistory.getTrail().getDifficulty().equals(difficultyList.get(1)))
					.collect(Collectors.toList());
		} else if (difficultyList.size() == 1) {
			difficultyResult = firstResult.stream()
					.filter(hikingHistory -> hikingHistory.getTrail().getDifficulty().equals(difficultyList.get(0)))
					.collect(Collectors.toList());
		} else {
			difficultyResult = firstResult;
		}

		ObservableList<HikeType> typeList = typeBoxHistory.getCheckModel().getCheckedItems();
		if (typeList.size() == 2) {
			typeResult = difficultyResult.stream()
					.filter(hikingHistory -> hikingHistory.getTrail().getType().equals(typeList.get(0))
							|| hikingHistory.getTrail().getType().equals(typeList.get(1)))
					.collect(Collectors.toList());
		} else if (typeList.size() == 1) {
			typeResult = difficultyResult.stream()
					.filter(hikingHistory -> hikingHistory.getTrail().getType().equals(typeList.get(0)))
					.collect(Collectors.toList());
		} else {
			typeResult = difficultyResult;
		}

		int length = (int) lengthSliderHistory.getValue();
		int evelation = (int) evelationSliderHistory.getValue();
		if (length == 100 && evelation == 100) {
			trailHistory.addAll(typeResult);
			tableHistory.setItems(trailHistory);
		} else if (length == 100 && evelation != 100) {

			rangeResult = typeResult.stream()
					.filter(hikingUncompleted -> hikingUncompleted.getTrail().getElevation() <= evelation)
					.collect(Collectors.toList());

			trailHistory.addAll(rangeResult);
			tableHistory.setItems(trailHistory);

		} else if (evelation == 100 && length != 100) {

			rangeResult = typeResult.stream()
					.filter(hikingUncompleted -> hikingUncompleted.getTrail().getLength() <= length)
					.collect(Collectors.toList());

			trailHistory.addAll(rangeResult);
			tableHistory.setItems(trailHistory);

		} else {
			rangeResult = typeResult.stream()
					.filter(hikingUncompleted -> hikingUncompleted.getTrail().getElevation() <= evelation
							&& hikingUncompleted.getTrail().getLength() <= length)
					.collect(Collectors.toList());

			trailHistory.addAll(rangeResult);
			tableHistory.setItems(trailHistory);
		}
	}

	public void selectedTrailHistory(MouseEvent event) throws IOException {
		if (event.getClickCount() == 2) {
			hikingHistoryForPictures = tableHistory.getSelectionModel().getSelectedItem();
			Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/viewImages.fxml"));
			Scene secondScene = new Scene(secondRoot);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(secondScene);
			window.show();

		} else {
			try {
				selectedTrailHistory
						.setText(tableHistory.getSelectionModel().getSelectedItem().getTrail().getTrailName());
			} catch (NullPointerException e) {
				//
			}
		}

	}

	public void addPicturesHistory(ActionEvent event) {
		HikingHistory hikingHistory = tableHistory.getSelectionModel().getSelectedItem();
		if (hikingHistory != null) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Multiple Images to you Hike");
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

			List<File> picturesList = fileChooser.showOpenMultipleDialog(stage);

			if (picturesList != null) {
				for (File file : picturesList) {
					hikingHistory.getImages().add(file.toString());
				}
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText("Process Complete");
				alert.setContentText("Your images have been uploaded!");
				alert.setTitle("Images Added to Hiking Trail");
				alert.showAndWait();
			}
		}

	}

	// hikes in progress tab//
	public void showUncompletedHikes(Event event) {
		trailUncompleted.clear();
		trailUncompleted.addAll(user.getHikingUncompletedSet());
		tableUncompleted.setItems(trailUncompleted);

	}

	public void selectedTrailUncompleted(MouseEvent event) {
		try {
			selectedTrailUncompleted
					.setText(tableUncompleted.getSelectionModel().getSelectedItem().getTrail().getTrailName());
		} catch (NullPointerException e) {
			//
		}
	}

	public void completeTrail(ActionEvent event) throws ParseException {

		HikingUncompleted hikingUncompleted = tableUncompleted.getSelectionModel().getSelectedItem();
		if (hikingUncompleted != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY hh:mm:ss");
			Date currentDoneTime = new Date(System.currentTimeMillis());
			String completedTime = sdf.format(currentDoneTime);

			Date completedDate = sdf.parse(completedTime);
			Date current = sdf.parse(hikingUncompleted.getDate());

			long difference = completedDate.getTime() - current.getTime();
			long duration = (difference / 1000) / 60;
			double pace = duration / hikingUncompleted.getTrail().getLength();

			// Date date = new Date(difference);
			// String duration = sdf.format(date);
			LinkedList<String> images = new LinkedList<String>();
			HikingHistory hikingHistory = new HikingHistory(hikingUncompleted.getTrail(), hikingUncompleted.getDate(),
					completedTime, String.valueOf(duration), images, String.valueOf(pace));

			user.getHikingHistorySet().add(hikingHistory);
			user.getHikingUncompletedSet().removeIf(
					e -> e.getTrail().getTrailName().compareTo(hikingUncompleted.getTrail().getTrailName()) == 0);

			tableUncompleted.getItems().remove(tableUncompleted.getSelectionModel().getSelectedItem());
			selectedTrailUncompleted.setText("Trail Completed!");
		}
	}

	public void searchForTrailsUncompleted(ActionEvent event) {
		trailUncompleted.clear();
		String search = searchFieldUncompleted.getText().toLowerCase();

		List<HikingUncompleted> firstResult = user.getHikingUncompletedSet().stream()
				.filter(hikingUncompleted -> hikingUncompleted.getTrail().getTrailName().toLowerCase().contains(search))
				.collect(Collectors.toList());
		List<HikingUncompleted> difficultyResult = null;
		List<HikingUncompleted> typeResult = null;
		List<HikingUncompleted> rangeResult;

		ObservableList<Level> difficultyList = difficultyBoxUncompleted.getCheckModel().getCheckedItems();
		if (difficultyList.size() == 2) {

			difficultyResult = firstResult.stream().filter(
					hikingUncompleted -> hikingUncompleted.getTrail().getDifficulty().equals(difficultyList.get(0))
							|| hikingUncompleted.getTrail().getDifficulty().equals(difficultyList.get(1)))
					.collect(Collectors.toList());

		} else if (difficultyList.size() == 1) {

			difficultyResult = firstResult.stream().filter(
					hikingUncompleted -> hikingUncompleted.getTrail().getDifficulty().equals(difficultyList.get(0)))
					.collect(Collectors.toList());

		} else {
			difficultyResult = firstResult;
		}

		ObservableList<HikeType> typeList = typeBoxUncompleted.getCheckModel().getCheckedItems();
		if (typeList.size() == 2) {
			typeResult = difficultyResult.stream()
					.filter(hikingUncompleted -> hikingUncompleted.getTrail().getType().equals(typeList.get(0))
							|| hikingUncompleted.getTrail().getType().equals(typeList.get(1)))
					.collect(Collectors.toList());
		} else if (typeList.size() == 1) {
			typeResult = difficultyResult.stream()
					.filter(hikingUncompleted -> hikingUncompleted.getTrail().getType().equals(typeList.get(0)))
					.collect(Collectors.toList());
		} else {
			typeResult = difficultyResult;
		}

		int length = (int) lengthSliderUncompleted.getValue();
		int evelation = (int) evelationSliderUncompleted.getValue();

		if (length == 100 && evelation == 100) {

			trailUncompleted.addAll(typeResult);
			tableUncompleted.setItems(trailUncompleted);

		} else if (length == 100 && evelation != 100) {

			rangeResult = typeResult.stream()
					.filter(hikingUncompleted -> hikingUncompleted.getTrail().getElevation() <= evelation)
					.collect(Collectors.toList());

			trailUncompleted.addAll(rangeResult);
			tableUncompleted.setItems(trailUncompleted);

		} else if (evelation == 100 && length != 100) {

			rangeResult = typeResult.stream()
					.filter(hikingUncompleted -> hikingUncompleted.getTrail().getLength() <= length)
					.collect(Collectors.toList());

			trailUncompleted.addAll(rangeResult);
			tableUncompleted.setItems(trailUncompleted);

		} else {
			rangeResult = typeResult.stream()
					.filter(hikingUncompleted -> hikingUncompleted.getTrail().getElevation() <= evelation
							&& hikingUncompleted.getTrail().getLength() <= length)
					.collect(Collectors.toList());

			trailUncompleted.addAll(rangeResult);
			tableUncompleted.setItems(trailUncompleted);
		}

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
		if (trail != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY hh:mm:ss");
			Date currentTime = new Date(System.currentTimeMillis());
			String current = sdf.format(currentTime);

			HikingUncompleted hikingUncompleted = new HikingUncompleted(current, trail);
			user.getHikingUncompletedSet().add(hikingUncompleted);
			table.getItems().remove(table.getSelectionModel().getSelectedItem());
			selectedTrail.setText("Trail Started!");
		}

	}

	public void keyPressed(KeyEvent event) {
		trail.clear();
		String search = searchField.getText().toLowerCase();

		if (!search.contentEquals("")) {
			List<Trail> firstResult = trailSet.stream()
					.filter(trail -> trail.getTrailName().toLowerCase().contains(search)).collect(Collectors.toList());
			List<Trail> difficultyResult = null;
			List<Trail> typeResult = null;
			List<Trail> rangeResult;

			ObservableList<Level> difficultyList = difficultyBox.getCheckModel().getCheckedItems();
			if (difficultyList.size() == 2) {
				difficultyResult = firstResult.stream()
						.filter(trail -> trail.getDifficulty().equals(difficultyList.get(0))
								|| trail.getDifficulty().equals(difficultyList.get(1)))
						.collect(Collectors.toList());
			} else if (difficultyList.size() == 1) {
				difficultyResult = firstResult.stream()
						.filter(trail -> trail.getDifficulty().equals(difficultyList.get(0)))
						.collect(Collectors.toList());
			} else {
				difficultyResult = firstResult;
			}

			ObservableList<HikeType> typeList = typeBox.getCheckModel().getCheckedItems();
			if (typeList.size() == 2) {
				typeResult = difficultyResult.stream().filter(
						trail -> trail.getType().equals(typeList.get(0)) || trail.getType().equals(typeList.get(1)))
						.collect(Collectors.toList());
			} else if (typeList.size() == 1) {
				typeResult = difficultyResult.stream().filter(trail -> trail.getType().equals(typeList.get(0)))
						.collect(Collectors.toList());
			} else {
				typeResult = difficultyResult;
			}

			int length = (int) lengthSlider.getValue();
			int evelation = (int) evelationSlider.getValue();
			if (length == 100 && evelation == 100) {
				trail.addAll(typeResult);
				table.setItems(trail);
			} else if (length == 100 && evelation != 100) {
				rangeResult = typeResult.stream().filter(trail -> trail.getElevation() <= evelation)
						.collect(Collectors.toList());
				trail.addAll(rangeResult);
				table.setItems(trail);
			} else if (evelation == 100 && length != 100) {
				rangeResult = typeResult.stream().filter(trail -> trail.getLength() <= length)
						.collect(Collectors.toList());
				trail.addAll(rangeResult);
				table.setItems(trail);
			} else {
				rangeResult = typeResult.stream()
						.filter(trail -> trail.getElevation() <= evelation && trail.getLength() <= length)
						.collect(Collectors.toList());
				trail.addAll(rangeResult);
				table.setItems(trail);
			}
		}
	}

	public void searchForTrails(ActionEvent event) {
		trail.clear();
		String search = searchField.getText().toLowerCase();

		if (!search.contentEquals("")) {
			List<Trail> firstResult = trailSet.stream()
					.filter(trail -> trail.getTrailName().toLowerCase().contains(search)).collect(Collectors.toList());
			List<Trail> difficultyResult = null;
			List<Trail> typeResult = null;
			List<Trail> rangeResult;

			ObservableList<Level> difficultyList = difficultyBox.getCheckModel().getCheckedItems();
			if (difficultyList.size() == 2) {

				difficultyResult = firstResult.stream()
						.filter(trail -> trail.getDifficulty().equals(difficultyList.get(0))
								|| trail.getDifficulty().equals(difficultyList.get(1)))
						.collect(Collectors.toList());

			} else if (difficultyList.size() == 1) {

				difficultyResult = firstResult.stream()
						.filter(trail -> trail.getDifficulty().equals(difficultyList.get(0)))
						.collect(Collectors.toList());

			} else {
				difficultyResult = firstResult;
			}

			ObservableList<HikeType> typeList = typeBox.getCheckModel().getCheckedItems();
			if (typeList.size() == 2) {

				typeResult = difficultyResult.stream().filter(
						trail -> trail.getType().equals(typeList.get(0)) || trail.getType().equals(typeList.get(1)))
						.collect(Collectors.toList());

			} else if (typeList.size() == 1) {

				typeResult = difficultyResult.stream().filter(trail -> trail.getType().equals(typeList.get(0)))
						.collect(Collectors.toList());

			} else {
				typeResult = difficultyResult;
			}

			int length = (int) lengthSlider.getValue();
			int evelation = (int) evelationSlider.getValue();

			if (length == 100 && evelation == 100) {

				trail.addAll(typeResult);
				table.setItems(trail);

			} else if (length == 100 && evelation != 100) {

				rangeResult = typeResult.stream().filter(trail -> trail.getElevation() <= evelation)
						.collect(Collectors.toList());
				trail.addAll(rangeResult);
				table.setItems(trail);

			} else if (evelation == 100 && length != 100) {

				rangeResult = typeResult.stream().filter(trail -> trail.getLength() <= length)
						.collect(Collectors.toList());
				trail.addAll(rangeResult);
				table.setItems(trail);

			} else {

				rangeResult = typeResult.stream()
						.filter(trail -> trail.getElevation() <= evelation && trail.getLength() <= length)
						.collect(Collectors.toList());
				trail.addAll(rangeResult);
				table.setItems(trail);

			}
		}
	}

	// edit tab
	public void changeImage(MouseEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		fileChooser = new FileChooser();

		this.setFilePath(fileChooser.showOpenDialog(stage));

		try {
			// image = filePath.getPath();
			BufferedImage bufferedImage = ImageIO.read(filePath);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			imageField.setImage(image);
		} catch (IllegalArgumentException e) {
			//
		} catch (NullPointerException e) {
			
		}
		try {
			userMap.get(username).setImage(filePath.getPath());
		} catch (NullPointerException e) {
			//
		}
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
		boolean hasDigit = false;

		if (password.length() >= 8) {
			for (int i = 0; i < password.length(); i++) {
				if (password.charAt(i) >= '0' && password.charAt(i) <= '9') {
					hasDigit = true;
					break;
				}
			}
		} else {
			hasDigit = false;
		}

		if (hasDigit) {
			user.setPassword(password);
			passwordField.setText(password);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Your password needs to be 8 char long and contain atleast one number");
			alert.setTitle("PASSWORD ERROR");
			alert.setHeaderText("Your password is weak");
			alert.showAndWait();
		}
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
		boolean isFormatted = false;

		if (phonenumber.length() == 12) {
			for (int i = 0; i < phonenumber.length(); i++) {
				if (!(i == 3 || i == 7)) {
					if (!(phonenumber.charAt(i) >= '0' && phonenumber.charAt(i) <= '9')) {
						isFormatted = false;
						break;
					} else {
						isFormatted = true;
					}
				} else {
					if (phonenumber.charAt(i) == '-') {
						isFormatted = true;
					}
				}
			}

			if (isFormatted && phonenumber.charAt(3) == '-' && phonenumber.charAt(7) == '-') {
				isFormatted = true;

			} else {
				isFormatted = false;
			}
		}

		if (isFormatted) {
			user.setPhoneNumber(phonenumber);
			phonenumberField.setText(phonenumber);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Format: ###-###-####");
			alert.setTitle("PHONENUMBER ERROR");
			alert.setHeaderText("Phonenumber needs to formatted correctly");
			alert.showAndWait();
		}

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

		// update user tab
		try {
			bufferedImage = ImageIO.read(filePath);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			imageField.setImage(image);
		} catch (IOException e) {
			defaultImage = imageField.getImage();
			// image = "\\RawData\\user.png";
		}

		username = Controller1.getUsername();
		user = Controller1.getUser();
		usernameText.setText(username);
		passwordField.setText(user.getPassword());
		firstnameField.setText(user.getFirstName());
		lastnameField.setText(user.getLastName());
		phonenumberField.setText(user.getPhoneNumber());

		// start trails tab
		final ObservableList<Level> difficultyStrings = FXCollections.observableArrayList();
		difficultyStrings.add(Level.HARD);
		difficultyStrings.add(Level.MODERATE);
		difficultyStrings.add(Level.EASY);
		difficultyBox.getItems().addAll(difficultyStrings);

		final ObservableList<HikeType> typeStrings = FXCollections.observableArrayList();
		typeStrings.add(HikeType.LOOP);
		typeStrings.add(HikeType.OUT_AND_BACK);
		typeStrings.add(HikeType.POINT_TO_POINT);
		typeBox.getItems().addAll(typeStrings);

		trail = FXCollections.observableArrayList();
		trailAddress.setCellValueFactory(new PropertyValueFactory<Trail, String>("trailAddress"));
		trailName.setCellValueFactory(new PropertyValueFactory<Trail, String>("trailName"));
		difficulty.setCellValueFactory(new PropertyValueFactory<Trail, String>("difficulty"));
		elevation.setCellValueFactory(new PropertyValueFactory<Trail, String>("elevation"));
		type.setCellValueFactory(new PropertyValueFactory<Trail, String>("type"));
		length.setCellValueFactory(new PropertyValueFactory<Trail, String>("length"));

		// hiking uncompleted tab//
		final ObservableList<Level> difficultyStringsUncompleted = FXCollections.observableArrayList();
		difficultyStringsUncompleted.add(Level.HARD);
		difficultyStringsUncompleted.add(Level.MODERATE);
		difficultyStringsUncompleted.add(Level.EASY);
		difficultyBoxUncompleted.getItems().addAll(difficultyStringsUncompleted);

		final ObservableList<HikeType> typeStringsUncompleted = FXCollections.observableArrayList();
		typeStringsUncompleted.add(HikeType.LOOP);
		typeStringsUncompleted.add(HikeType.OUT_AND_BACK);
		typeStringsUncompleted.add(HikeType.POINT_TO_POINT);
		typeBoxUncompleted.getItems().addAll(typeStringsUncompleted);

		trailUncompleted = FXCollections.observableArrayList();
		dateStartedUncompleted.setCellValueFactory(new PropertyValueFactory<HikingUncompleted, String>("date"));
		trailAddressUncompleted
				.setCellValueFactory(new PropertyValueFactory<HikingUncompleted, String>("trailAddress"));
		trailNameUncompleted.setCellValueFactory(new PropertyValueFactory<HikingUncompleted, String>("trailName"));
		difficultyUncompleted.setCellValueFactory(new PropertyValueFactory<HikingUncompleted, String>("difficulty"));
		elevationUncompleted.setCellValueFactory(new PropertyValueFactory<HikingUncompleted, String>("elevation"));
		typeUncompleted.setCellValueFactory(new PropertyValueFactory<HikingUncompleted, String>("type"));
		lengthUncompleted.setCellValueFactory(new PropertyValueFactory<HikingUncompleted, String>("length"));

		// hiking history tab
		final ObservableList<Level> difficultyStringsHistory = FXCollections.observableArrayList();
		difficultyStringsHistory.add(Level.HARD);
		difficultyStringsHistory.add(Level.MODERATE);
		difficultyStringsHistory.add(Level.EASY);
		difficultyBoxHistory.getItems().addAll(difficultyStringsHistory);

		final ObservableList<HikeType> typeStringsHistory = FXCollections.observableArrayList();
		typeStringsHistory.add(HikeType.LOOP);
		typeStringsHistory.add(HikeType.OUT_AND_BACK);
		typeStringsHistory.add(HikeType.POINT_TO_POINT);
		typeBoxHistory.getItems().addAll(typeStringsHistory);

		trailHistory = FXCollections.observableArrayList();
		dateStartedHistory.setCellValueFactory(new PropertyValueFactory<HikingHistory, String>("date"));
		trailDurationHistory.setCellValueFactory(new PropertyValueFactory<HikingHistory, String>("duration"));
		trailNameHistory.setCellValueFactory(new PropertyValueFactory<HikingHistory, String>("trailName"));
		difficultyHistory.setCellValueFactory(new PropertyValueFactory<HikingHistory, String>("difficulty"));
		elevationHistory.setCellValueFactory(new PropertyValueFactory<HikingHistory, String>("elevation"));
		typeHistory.setCellValueFactory(new PropertyValueFactory<HikingHistory, String>("type"));
		lengthHistory.setCellValueFactory(new PropertyValueFactory<HikingHistory, String>("length"));
		trailPaceHistory.setCellValueFactory(new PropertyValueFactory<HikingHistory, String>("pace"));
		dateCompletedHistory.setCellValueFactory(new PropertyValueFactory<HikingHistory, String>("dateDone"));

	}

}
