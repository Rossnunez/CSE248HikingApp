package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.Level;
import model.Trail;

public class ControllerSelectedTrail implements Initializable {
	public Trail trail;

	public Text trailNameText;

	public TextField trailNameField;
	public TextField trailAddressField;
	public TextField lengthField;
	public TextField elevationField;
	public TextField difficultyField;
	public TextField typeField;

	public void editName(ActionEvent event) {
		TextInputDialog tid = new TextInputDialog("Enter a new Trail Name");
		tid.setHeaderText("Edit Trail Name");
		tid.showAndWait();
		String trailName = tid.getEditor().getText();
		trail.setTrailName(trailName);
		trailNameField.setText(trailName);
		trailNameText.setText(trailName);
	}

	public void editAddress(ActionEvent event) {
		TextInputDialog tid = new TextInputDialog("Enter a new Trail Address");
		tid.setHeaderText("Edit Trail Address");
		tid.showAndWait();
		String trailAddress = tid.getEditor().getText();
		trail.setTrailAddress(trailAddress);
		trailAddressField.setText(trailAddress);
	}

	public void editLength(ActionEvent event) {
		TextInputDialog tid = new TextInputDialog("Enter a new Length in Miles");
		tid.setHeaderText("Edit Length");
		tid.showAndWait();
		String lengthS = tid.getEditor().getText();
		double length = Double.valueOf(lengthS);
		trail.setLength(length);
		lengthField.setText(lengthS);

	}

	public void editElevation(ActionEvent event) {
		TextInputDialog tid = new TextInputDialog("Enter a new Elevation in Feet");
		tid.setHeaderText("Edit Elevation");
		tid.showAndWait();
		String elevationS = tid.getEditor().getText();
		double elevation = Double.valueOf(elevationS);
		trail.setElevation(elevation);
		elevationField.setText(elevationS);
	}

	public void editDifficulty(ActionEvent event) {
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(primaryStage);
		
		TilePane tilePane = new TilePane();
		
		Label label = new Label("Select Trail Difficulty");
		
		ToggleGroup toggleGroup = new ToggleGroup();
		
		RadioButton rHard = new RadioButton("HARD");
		RadioButton rModerate = new RadioButton("MODERATE");
		RadioButton rEasy = new RadioButton("EASY");
		
		
		rHard.setToggleGroup(toggleGroup);
		rModerate.setToggleGroup(toggleGroup);
		rEasy.setToggleGroup(toggleGroup);
		label.setAlignment(Pos.TOP_CENTER);
		tilePane.setAlignment(Pos.CENTER);
		tilePane.getChildren().addAll(label, rHard,rModerate,rEasy);
		Scene sc = new Scene(tilePane, 200,200);
		
		toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n)
			{
				RadioButton radioButton = (RadioButton) toggleGroup.getSelectedToggle();
				
				if(radioButton != null) {
					String s = radioButton.getText();
					trail.setDifficulty(Level.valueOf(s));
					difficultyField.setText(trail.getDifficulty().name());
					dialog.close();
				}
			}
		});
		
		dialog.setScene(sc);
		dialog.show();
		
		/*		
		VBox dialogVbox = new VBox(20);
		dialogVbox.getChildren().add(new Text("This is a Dialog"));
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		dialog.setScene(dialogScene);
		dialog.show();
		*/

	}

	public void editType(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		trail = ControllerEditTrails.getTrail();
		trailNameField.setText(trail.getTrailName());
		trailAddressField.setText(trail.getTrailAddress());
		lengthField.setText(String.valueOf(trail.getLength()));
		elevationField.setText(String.valueOf(trail.getElevation()));
		typeField.setText(trail.getType().name());
		difficultyField.setText(trail.getDifficulty().name());
		trailNameText.setText(trail.getTrailName());
	}

}
