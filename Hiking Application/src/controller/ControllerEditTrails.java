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
import javafx.scene.input.KeyEvent;
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

	// create trails
	public TextField trailNameField;
	public TextField trailAddressField;
	public TextField lengthField;
	public TextField elevationField;

	public ComboBox<String> difficultyBox;
	public ComboBox<String> typeBox;

	// edit trails tab //
	public CheckComboBox<Level> difficultyCheckBox;
	public CheckComboBox<HikeType> typeCheckBox;

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

	// edit trails tab
	public void removeTrail(ActionEvent event) {
		if (table.getSelectionModel().getSelectedItem() != null) {
			Trail trail = table.getSelectionModel().getSelectedItem();
			trailSet.remove(trail);
			table.getItems().remove(table.getSelectionModel().getSelectedItem());
			selectedTrail.setText("Trail Removed");
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

			ObservableList<Level> difficultyList = difficultyCheckBox.getCheckModel().getCheckedItems();
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

			ObservableList<HikeType> typeList = typeCheckBox.getCheckModel().getCheckedItems();
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
			List<Trail> firstResult = trailSet.stream().filter(trail -> trail.getTrailName().toLowerCase().contains(search))
					.collect(Collectors.toList());
			List<Trail> difficultyResult = null;
			List<Trail> typeResult = null;
			List<Trail> rangeResult;

			ObservableList<Level> difficultyList = difficultyCheckBox.getCheckModel().getCheckedItems();
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

			ObservableList<HikeType> typeList = typeCheckBox.getCheckModel().getCheckedItems();
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

	public void selectedTrail(MouseEvent event) throws IOException {

		if (event.getClickCount() == 2) {
			try {
				selectedTrail.setText(table.getSelectionModel().getSelectedItem().getTrailName());
			} catch (NullPointerException e) {
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
			} catch (NullPointerException e) {
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

	// create trail tab
	public void createTrail(ActionEvent event) {
		if (difficultyBox.getSelectionModel().getSelectedItem() != null
				&& typeBox.getSelectionModel().getSelectedItem() != null) {
			String trailName = trailNameField.getText();
			String trailAddress = trailAddressField.getText();
			try {
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

			} catch (NumberFormatException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Length and Elevation need to be a Integer or Double");
				alert.setTitle("INTEGER ERROR");
				alert.setHeaderText("Only use numbers");
				alert.showAndWait();
			}

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Select difficulty and type");
			alert.setHeaderText("Difficulty or Hike Type not selected");
			alert.setTitle("TRAIL ERROR");
			alert.showAndWait();
		}

	}

	// initializer
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		trailSet = Main.getTrailSet();

		// create trails
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

		// edit trails
		final ObservableList<Level> difficultyCheckStrings = FXCollections.observableArrayList();
		difficultyCheckStrings.add(Level.HARD);
		difficultyCheckStrings.add(Level.MODERATE);
		difficultyCheckStrings.add(Level.EASY);
		difficultyCheckBox.getItems().addAll(difficultyCheckStrings);

		final ObservableList<HikeType> typeCheckStrings = FXCollections.observableArrayList();
		typeCheckStrings.add(HikeType.LOOP);
		typeCheckStrings.add(HikeType.OUT_AND_BACK);
		typeCheckStrings.add(HikeType.POINT_TO_POINT);
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
