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
	public CheckComboBox<String> difficultyBox;
	public CheckComboBox<String> typeBox;

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
	public CheckComboBox<String> difficultyBoxUncompleted;
	public CheckComboBox<String> typeBoxUncompleted;

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
	public CheckComboBox<String> difficultyBoxHistory;
	public CheckComboBox<String> typeBoxHistory;

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
		String search = searchFieldHistory.getText();

		List<HikingHistory> firstResult = user.getHikingHistorySet().stream()
				.filter(hikingUncompleted -> hikingUncompleted.getTrail().getTrailName().startsWith(search))
				.collect(Collectors.toList());
		List<HikingHistory> difficultyResult = null;
		List<HikingHistory> typeResult = null;
		List<HikingHistory> rangeResult;

		ObservableList<String> difficultyList = difficultyBoxUncompleted.getCheckModel().getCheckedItems();
		if (difficultyList.size() == 2) {

			if (difficultyList.get(0).toString().contentEquals("HARD")
					&& difficultyList.get(1).toString().contentEquals("MODERATE")) {

				difficultyResult = firstResult.stream()
						.filter(hikingHistory -> hikingHistory.getTrail().getDifficulty().equals(Level.HARD)
								|| hikingHistory.getTrail().getDifficulty().equals(Level.MODERATE))
						.collect(Collectors.toList());

			} else if (difficultyList.get(0).toString().contentEquals("HARD")
					&& difficultyList.get(1).toString().contentEquals("EASY")) {

				difficultyResult = firstResult.stream()
						.filter(hikingHistory -> hikingHistory.getTrail().getDifficulty().equals(Level.HARD)
								|| hikingHistory.getTrail().getDifficulty().equals(Level.EASY))
						.collect(Collectors.toList());

			} else if (difficultyList.get(0).toString().contentEquals("MODERATE")
					&& difficultyList.get(1).toString().contentEquals("EASY")) {

				difficultyResult = firstResult.stream()
						.filter(hikingHistory -> hikingHistory.getTrail().getDifficulty().equals(Level.MODERATE)
								|| hikingHistory.getTrail().getDifficulty().equals(Level.EASY))
						.collect(Collectors.toList());

			}

		} else if (difficultyList.size() == 1) {

			if (difficultyList.get(0).toString().contentEquals("HARD")) {

				difficultyResult = firstResult.stream()
						.filter(hikingHistory -> hikingHistory.getTrail().getDifficulty().equals(Level.HARD))
						.collect(Collectors.toList());

			} else if (difficultyList.get(0).toString().contentEquals("MODERATE")) {

				difficultyResult = firstResult.stream()
						.filter(hikingHistory -> hikingHistory.getTrail().getDifficulty().equals(Level.MODERATE))
						.collect(Collectors.toList());

			} else if (difficultyList.get(0).toString().contentEquals("EASY")) {

				difficultyResult = firstResult.stream()
						.filter(hikingHistory -> hikingHistory.getTrail().getDifficulty().equals(Level.EASY))
						.collect(Collectors.toList());
			}
		} else {
			difficultyResult = firstResult;
		}

		ObservableList<String> typeList = typeBoxHistory.getCheckModel().getCheckedItems();
		if (typeList.size() == 2) {

			if (typeList.get(0).toString().contentEquals("LOOP")
					&& typeList.get(1).toString().contentEquals("OUT AND BACK")) {

				typeResult = difficultyResult.stream()
						.filter(hikingHistory -> hikingHistory.getTrail().getType().equals(HikeType.LOOP)
								|| hikingHistory.getTrail().getType().equals(HikeType.OUT_AND_BACK))
						.collect(Collectors.toList());

			} else if (typeList.get(0).toString().contentEquals("LOOP")
					&& typeList.get(1).toString().contentEquals("POINT TO POINT")) {

				typeResult = difficultyResult.stream()
						.filter(hikingHistory -> hikingHistory.getTrail().getType().equals(HikeType.LOOP)
								|| hikingHistory.getTrail().getType().equals(HikeType.POINT_TO_POINT))
						.collect(Collectors.toList());

			} else if (typeList.get(0).toString().contentEquals("OUT AND BACK")
					&& typeList.get(1).toString().contentEquals("POINT TO POINT")) {

				typeResult = difficultyResult.stream()
						.filter(hikingHistory -> hikingHistory.getTrail().getType().equals(HikeType.OUT_AND_BACK)
								|| hikingHistory.getTrail().getType().equals(HikeType.POINT_TO_POINT))
						.collect(Collectors.toList());

			}

		} else if (typeList.size() == 1) {

			if (typeList.get(0).toString().contentEquals("LOOP")) {

				typeResult = difficultyResult.stream()
						.filter(hikingHistory -> hikingHistory.getTrail().getType().equals(HikeType.LOOP))
						.collect(Collectors.toList());

			} else if (typeList.get(0).toString().contentEquals("POINT TO POINT")) {

				typeResult = difficultyResult.stream()
						.filter(hikingHistory -> hikingHistory.getTrail().getType().equals(HikeType.POINT_TO_POINT))
						.collect(Collectors.toList());

			} else if (typeList.get(0).toString().contentEquals("OUT AND BACK")) {

				typeResult = difficultyResult.stream()
						.filter(hikingHistory -> hikingHistory.getTrail().getType().equals(HikeType.OUT_AND_BACK))
						.collect(Collectors.toList());
			}
		} else {
			typeResult = difficultyResult;
		}

		int length = (int) lengthSliderHistory.getValue();
		int evelation = (int) evelationSliderHistory.getValue();

		rangeResult = typeResult.stream()
				.filter(hikingUncompleted -> hikingUncompleted.getTrail().getElevation() <= evelation
						&& hikingUncompleted.getTrail().getLength() <= length)
				.collect(Collectors.toList());

		trailHistory.addAll(rangeResult);
		tableHistory.setItems(trailHistory);

	}

	public void selectedTrailHistory(MouseEvent event) throws IOException {
		if (event.getClickCount() == 2) {
			hikingHistoryForPictures = tableHistory.getSelectionModel().getSelectedItem();
			Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/viewImages.fxml"));
			Scene secondScene = new Scene(secondRoot);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
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
		user.getHikingUncompletedSet()
				.removeIf(e -> e.getTrail().getTrailName().compareTo(hikingUncompleted.getTrail().getTrailName()) == 0);

		tableUncompleted.getItems().remove(tableUncompleted.getSelectionModel().getSelectedItem());
	}

	public void searchForTrailsUncompleted(ActionEvent event) {
		trailUncompleted.clear();
		String search = searchFieldUncompleted.getText();

		List<HikingUncompleted> firstResult = user.getHikingUncompletedSet().stream()
				.filter(hikingUncompleted -> hikingUncompleted.getTrail().getTrailName().startsWith(search))
				.collect(Collectors.toList());
		List<HikingUncompleted> difficultyResult = null;
		List<HikingUncompleted> typeResult = null;
		List<HikingUncompleted> rangeResult;

		ObservableList<String> difficultyList = difficultyBoxUncompleted.getCheckModel().getCheckedItems();
		if (difficultyList.size() == 2) {

			if (difficultyList.get(0).toString().contentEquals("HARD")
					&& difficultyList.get(1).toString().contentEquals("MODERATE")) {

				difficultyResult = firstResult.stream()
						.filter(hikingUncompleted -> hikingUncompleted.getTrail().getDifficulty().equals(Level.HARD)
								|| hikingUncompleted.getTrail().getDifficulty().equals(Level.MODERATE))
						.collect(Collectors.toList());

			} else if (difficultyList.get(0).toString().contentEquals("HARD")
					&& difficultyList.get(1).toString().contentEquals("EASY")) {

				difficultyResult = firstResult.stream()
						.filter(hikingUncompleted -> hikingUncompleted.getTrail().getDifficulty().equals(Level.HARD)
								|| hikingUncompleted.getTrail().getDifficulty().equals(Level.EASY))
						.collect(Collectors.toList());

			} else if (difficultyList.get(0).toString().contentEquals("MODERATE")
					&& difficultyList.get(1).toString().contentEquals("EASY")) {

				difficultyResult = firstResult.stream()
						.filter(hikingUncompleted -> hikingUncompleted.getTrail().getDifficulty().equals(Level.MODERATE)
								|| hikingUncompleted.getTrail().getDifficulty().equals(Level.EASY))
						.collect(Collectors.toList());

			}

		} else if (difficultyList.size() == 1) {

			if (difficultyList.get(0).toString().contentEquals("HARD")) {

				difficultyResult = firstResult.stream()
						.filter(hikingUncompleted -> hikingUncompleted.getTrail().getDifficulty().equals(Level.HARD))
						.collect(Collectors.toList());

			} else if (difficultyList.get(0).toString().contentEquals("MODERATE")) {

				difficultyResult = firstResult.stream().filter(
						hikingUncompleted -> hikingUncompleted.getTrail().getDifficulty().equals(Level.MODERATE))
						.collect(Collectors.toList());

			} else if (difficultyList.get(0).toString().contentEquals("EASY")) {

				difficultyResult = firstResult.stream()
						.filter(hikingUncompleted -> hikingUncompleted.getTrail().getDifficulty().equals(Level.EASY))
						.collect(Collectors.toList());
			}
		} else {
			difficultyResult = firstResult;
		}

		ObservableList<String> typeList = typeBoxUncompleted.getCheckModel().getCheckedItems();
		if (typeList.size() == 2) {

			if (typeList.get(0).toString().contentEquals("LOOP")
					&& typeList.get(1).toString().contentEquals("OUT AND BACK")) {

				typeResult = difficultyResult.stream()
						.filter(hikingUncompleted -> hikingUncompleted.getTrail().getType().equals(HikeType.LOOP)
								|| hikingUncompleted.getTrail().getType().equals(HikeType.OUT_AND_BACK))
						.collect(Collectors.toList());

			} else if (typeList.get(0).toString().contentEquals("LOOP")
					&& typeList.get(1).toString().contentEquals("POINT TO POINT")) {

				typeResult = difficultyResult.stream()
						.filter(hikingUncompleted -> hikingUncompleted.getTrail().getType().equals(HikeType.LOOP)
								|| hikingUncompleted.getTrail().getType().equals(HikeType.POINT_TO_POINT))
						.collect(Collectors.toList());

			} else if (typeList.get(0).toString().contentEquals("OUT AND BACK")
					&& typeList.get(1).toString().contentEquals("POINT TO POINT")) {

				typeResult = difficultyResult.stream().filter(
						hikingUncompleted -> hikingUncompleted.getTrail().getType().equals(HikeType.OUT_AND_BACK)
								|| hikingUncompleted.getTrail().getType().equals(HikeType.POINT_TO_POINT))
						.collect(Collectors.toList());

			}

		} else if (typeList.size() == 1) {

			if (typeList.get(0).toString().contentEquals("LOOP")) {

				typeResult = difficultyResult.stream()
						.filter(hikingUncompleted -> hikingUncompleted.getTrail().getType().equals(HikeType.LOOP))
						.collect(Collectors.toList());

			} else if (typeList.get(0).toString().contentEquals("POINT TO POINT")) {

				typeResult = difficultyResult.stream().filter(
						hikingUncompleted -> hikingUncompleted.getTrail().getType().equals(HikeType.POINT_TO_POINT))
						.collect(Collectors.toList());

			} else if (typeList.get(0).toString().contentEquals("OUT AND BACK")) {

				typeResult = difficultyResult.stream().filter(
						hikingUncompleted -> hikingUncompleted.getTrail().getType().equals(HikeType.OUT_AND_BACK))
						.collect(Collectors.toList());
			}
		} else {
			typeResult = difficultyResult;
		}

		int length = (int) lengthSliderUncompleted.getValue();
		int evelation = (int) evelationSliderUncompleted.getValue();

		rangeResult = typeResult.stream()
				.filter(hikingUncompleted -> hikingUncompleted.getTrail().getElevation() <= evelation
						&& hikingUncompleted.getTrail().getLength() <= length)
				.collect(Collectors.toList());

		trailUncompleted.addAll(rangeResult);
		tableUncompleted.setItems(trailUncompleted);

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

		HikingUncompleted hikingUncompleted = new HikingUncompleted(current, trail);
		user.getHikingUncompletedSet().add(hikingUncompleted);
		table.getItems().remove(table.getSelectionModel().getSelectedItem());
		// idea for later//
		// remove trail from trail set entirely so it doesnt show up on the table at all
		// ;)//
		// DONT FORGET ABOUT THIS//

	}

	public void searchForTrails(ActionEvent event) {
		trail.clear();
		String search = searchField.getText();

		List<Trail> firstResult = trailSet.stream().filter(trail -> trail.getTrailName().startsWith(search))
				.collect(Collectors.toList());
		List<Trail> difficultyResult = null;
		List<Trail> typeResult = null;
		List<Trail> rangeResult;

		ObservableList<String> difficultyList = difficultyBox.getCheckModel().getCheckedItems();
		if (difficultyList.size() == 2) {

			if (difficultyList.get(0).toString().contentEquals("HARD")
					&& difficultyList.get(1).toString().contentEquals("MODERATE")) {

				difficultyResult = firstResult.stream().filter(trail -> trail.getDifficulty().equals(Level.HARD)
						|| trail.getDifficulty().equals(Level.MODERATE)).collect(Collectors.toList());

			} else if (difficultyList.get(0).toString().contentEquals("HARD")
					&& difficultyList.get(1).toString().contentEquals("EASY")) {

				difficultyResult = firstResult.stream().filter(
						trail -> trail.getDifficulty().equals(Level.HARD) || trail.getDifficulty().equals(Level.EASY))
						.collect(Collectors.toList());

			} else if (difficultyList.get(0).toString().contentEquals("MODERATE")
					&& difficultyList.get(1).toString().contentEquals("EASY")) {

				difficultyResult = firstResult.stream().filter(trail -> trail.getDifficulty().equals(Level.MODERATE)
						|| trail.getDifficulty().equals(Level.EASY)).collect(Collectors.toList());

			}

		} else if (difficultyList.size() == 1) {

			if (difficultyList.get(0).toString().contentEquals("HARD")) {

				difficultyResult = firstResult.stream().filter(trail -> trail.getDifficulty().equals(Level.HARD))
						.collect(Collectors.toList());

			} else if (difficultyList.get(0).toString().contentEquals("MODERATE")) {

				difficultyResult = firstResult.stream().filter(trail -> trail.getDifficulty().equals(Level.MODERATE))
						.collect(Collectors.toList());

			} else if (difficultyList.get(0).toString().contentEquals("EASY")) {

				difficultyResult = firstResult.stream().filter(trail -> trail.getDifficulty().equals(Level.EASY))
						.collect(Collectors.toList());
			}
		} else {
			difficultyResult = firstResult;
		}

		ObservableList<String> typeList = typeBox.getCheckModel().getCheckedItems();
		if (typeList.size() == 2) {

			if (typeList.get(0).toString().contentEquals("LOOP")
					&& typeList.get(1).toString().contentEquals("OUT AND BACK")) {

				typeResult = difficultyResult.stream().filter(
						trail -> trail.getType().equals(HikeType.LOOP) || trail.getType().equals(HikeType.OUT_AND_BACK))
						.collect(Collectors.toList());

			} else if (typeList.get(0).toString().contentEquals("LOOP")
					&& typeList.get(1).toString().contentEquals("POINT TO POINT")) {

				typeResult = difficultyResult.stream().filter(trail -> trail.getType().equals(HikeType.LOOP)
						|| trail.getType().equals(HikeType.POINT_TO_POINT)).collect(Collectors.toList());

			} else if (typeList.get(0).toString().contentEquals("OUT AND BACK")
					&& typeList.get(1).toString().contentEquals("POINT TO POINT")) {

				typeResult = difficultyResult.stream().filter(trail -> trail.getType().equals(HikeType.OUT_AND_BACK)
						|| trail.getType().equals(HikeType.POINT_TO_POINT)).collect(Collectors.toList());

			}

		} else if (typeList.size() == 1) {

			if (typeList.get(0).toString().contentEquals("LOOP")) {

				typeResult = difficultyResult.stream().filter(trail -> trail.getType().equals(HikeType.LOOP))
						.collect(Collectors.toList());

			} else if (typeList.get(0).toString().contentEquals("POINT TO POINT")) {

				typeResult = difficultyResult.stream().filter(trail -> trail.getType().equals(HikeType.POINT_TO_POINT))
						.collect(Collectors.toList());

			} else if (typeList.get(0).toString().contentEquals("OUT AND BACK")) {

				typeResult = difficultyResult.stream().filter(trail -> trail.getType().equals(HikeType.OUT_AND_BACK))
						.collect(Collectors.toList());
			}
		} else {
			typeResult = difficultyResult;
		}

		int length = (int) lengthSlider.getValue();
		int evelation = (int) evelationSlider.getValue();

		rangeResult = typeResult.stream()
				.filter(trail -> trail.getElevation() <= evelation && trail.getLength() <= length)
				.collect(Collectors.toList());

		trail.addAll(rangeResult);
		table.setItems(trail);
	}

	// edit tab
	public void changeImage(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		fileChooser = new FileChooser();

		this.setFilePath(fileChooser.showOpenDialog(stage));

		try {
			// image = filePath.getPath();
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

		trail = FXCollections.observableArrayList();
		trailAddress.setCellValueFactory(new PropertyValueFactory<Trail, String>("trailAddress"));
		trailName.setCellValueFactory(new PropertyValueFactory<Trail, String>("trailName"));
		difficulty.setCellValueFactory(new PropertyValueFactory<Trail, String>("difficulty"));
		elevation.setCellValueFactory(new PropertyValueFactory<Trail, String>("elevation"));
		type.setCellValueFactory(new PropertyValueFactory<Trail, String>("type"));
		length.setCellValueFactory(new PropertyValueFactory<Trail, String>("length"));

		// hiking uncompleted tab//
		final ObservableList<String> difficultyStringsUncompleted = FXCollections.observableArrayList();
		difficultyStringsUncompleted.add("HARD");
		difficultyStringsUncompleted.add("MODERATE");
		difficultyStringsUncompleted.add("EASY");
		difficultyBoxUncompleted.getItems().addAll(difficultyStringsUncompleted);

		final ObservableList<String> typeStringsUncompleted = FXCollections.observableArrayList();
		typeStringsUncompleted.add("LOOP");
		typeStringsUncompleted.add("OUT AND BACK");
		typeStringsUncompleted.add("POINT TO POINT");
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
		final ObservableList<String> difficultyStringsHistory = FXCollections.observableArrayList();
		difficultyStringsHistory.add("HARD");
		difficultyStringsHistory.add("MODERATE");
		difficultyStringsHistory.add("EASY");
		difficultyBoxHistory.getItems().addAll(difficultyStringsHistory);

		final ObservableList<String> typeStringsHistory = FXCollections.observableArrayList();
		typeStringsHistory.add("LOOP");
		typeStringsHistory.add("OUT AND BACK");
		typeStringsHistory.add("POINT TO POINT");
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
