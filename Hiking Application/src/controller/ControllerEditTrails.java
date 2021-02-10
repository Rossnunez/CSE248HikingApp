package controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.controlsfx.control.CheckComboBox;

import app.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.HikeType;
import model.Level;
import model.Trail;

public class ControllerEditTrails implements Initializable {
	public HashSet<Trail> trailSet;
	public static Trail trailToEdit;

	public TextField trailNameField;
	public TextField trailAddressField;
	public TextField lengthField;
	public TextField elevationField;

	public ComboBox<String> difficultyBox;
	public ComboBox<String> typeBox;

	// start trails tab //
	public CheckComboBox<String> difficultyCheckBox;
	public CheckComboBox<String> typeCheckBox;

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
	
	//edit trails tab
	public void removeTrail(ActionEvent event) {
		Trail trail = table.getSelectionModel().getSelectedItem();
		trailSet.remove(trail);
		table.getItems().remove(table.getSelectionModel().getSelectedItem());
		selectedTrail.setText("Trail Removed");
	}
	
	public void searchForTrails(ActionEvent event) {
		trail.clear();
		String search = searchField.getText();

		List<Trail> firstResult = trailSet.stream().filter(trail -> trail.getTrailName().startsWith(search))
				.collect(Collectors.toList());
		List<Trail> difficultyResult = null;
		List<Trail> typeResult = null;
		List<Trail> rangeResult;

		ObservableList<String> difficultyList = difficultyCheckBox.getCheckModel().getCheckedItems();
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

		ObservableList<String> typeList = typeCheckBox.getCheckModel().getCheckedItems();
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

	public void selectedTrail(MouseEvent event) throws IOException {
		
		if(event.getClickCount() == 2) {
			try {
				selectedTrail.setText(table.getSelectionModel().getSelectedItem().getTrailName());
			} catch(NullPointerException e) {
				//
			}
			trailToEdit = table.getSelectionModel().getSelectedItem();
			Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/viewSelectedTrail.fxml"));
			Scene secondScene = new Scene(secondRoot);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(secondScene);
			window.show();
		} else {
			try {
				selectedTrail.setText(table.getSelectionModel().getSelectedItem().getTrailName());
			} catch(NullPointerException e) {
				//
			}
		}
	}
	
	public static Trail getTrail() {
		return trailToEdit;
	}
	
	public void goBackPane(ActionEvent event) throws IOException {
		Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/view3.fxml"));
		Scene secondScene = new Scene(secondRoot);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(secondScene);
		window.show();
	}
	
	//create trail tab
	public void createTrail(ActionEvent event) {
		if (difficultyBox.getSelectionModel().getSelectedItem() != null
				&& typeBox.getSelectionModel().getSelectedItem() != null) {
			String trailName = trailNameField.getText();
			String trailAddress = trailAddressField.getText();
			double length = Double.valueOf(lengthField.getText());
			double elevation = Double.valueOf(elevationField.getText());
			Level level = null;
			HikeType hikeType = null;

			String difficulty = difficultyBox.getSelectionModel().getSelectedItem();
			String type = typeBox.getSelectionModel().getSelectedItem();

			if (difficulty.contentEquals("HARD")) {
				level = Level.HARD;
			} else if (difficulty.contentEquals("MODERATE")) {
				level = Level.MODERATE;
			} else if (difficulty.contentEquals("EASY")) {
				level = Level.EASY;
			}

			if (type.contentEquals("LOOP")) {
				hikeType = HikeType.LOOP;
			} else if (type.contentEquals("OUT AND BACK")) {
				hikeType = HikeType.OUT_AND_BACK;
			} else if (type.contentEquals("POINT TO POINT")) {
				hikeType = HikeType.POINT_TO_POINT;
			}

			Trail trail = new Trail(trailName, trailAddress, length, elevation, level, hikeType);
			trailSet.add(trail);
			
			trailNameField.clear();
			trailAddressField.clear();
			lengthField.clear();
			elevationField.clear();
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Hiking Trail is Created!");
			alert.setHeaderText("Trail added to Database");
			alert.setTitle("TRAIL CONFIRMATION");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Select difficulty and type");
			alert.setHeaderText("Difficulty or Hike Type not selected");
			alert.setTitle("TRAIL ERROR");
			alert.showAndWait();
		}

	}

	//initializer
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		trailSet = Main.getTrailSet();
		
		//create trails
		final ObservableList<String> typeStrings = FXCollections.observableArrayList();
		typeStrings.add("LOOP");
		typeStrings.add("OUT AND BACK");
		typeStrings.add("POINT TO POINT");
		typeBox.getItems().addAll(typeStrings);

		final ObservableList<String> difficultyStrings = FXCollections.observableArrayList();
		difficultyStrings.add("HARD");
		difficultyStrings.add("MODERATE");
		difficultyStrings.add("EASY");
		difficultyBox.getItems().addAll(difficultyStrings);
		
		//start trails
		final ObservableList<String> difficultyCheckStrings = FXCollections.observableArrayList();
		difficultyCheckStrings.add("HARD");
		difficultyCheckStrings.add("MODERATE");
		difficultyCheckStrings.add("EASY");
		difficultyCheckBox.getItems().addAll(difficultyCheckStrings);

		final ObservableList<String> typeCheckStrings = FXCollections.observableArrayList();
		typeCheckStrings.add("LOOP");
		typeCheckStrings.add("OUT AND BACK");
		typeCheckStrings.add("POINT TO POINT");
		typeCheckBox.getItems().addAll(typeCheckStrings);
		
		trail = FXCollections.observableArrayList();
		
		
		trailAddress.setCellValueFactory(new PropertyValueFactory<Trail, String>("trailAddress"));
		trailName.setCellValueFactory(new PropertyValueFactory<Trail, String>("trailName"));
		difficulty.setCellValueFactory(new PropertyValueFactory<Trail, String>("difficulty"));
		elevation.setCellValueFactory(new PropertyValueFactory<Trail, String>("elevation"));
		type.setCellValueFactory(new PropertyValueFactory<Trail, String>("type"));
		length.setCellValueFactory(new PropertyValueFactory<Trail, String>("length"));
	}

}
