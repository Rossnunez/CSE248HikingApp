package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.HikeType;
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
	//
	
	public void goBackPane(ActionEvent event) throws IOException {
		Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/viewEditTrails.fxml"));
		Scene secondScene = new Scene(secondRoot);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(secondScene);
		window.show();
	}

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
	    label.setFont(new Font("Microsoft JhengHei", 18));
	    label.setTextFill(Color.web("#58872e"));
	    label.setAlignment(Pos.TOP_CENTER);
	    label.setPadding(new Insets(0,0,0,0));

	    BackgroundFill bg = new BackgroundFill(Color.web("#DCEDC8"), null, null);
	    Background backGround = new Background(bg);
	    tilePane.setBackground(backGround);
		
		ToggleGroup toggleGroup = new ToggleGroup();
		
		RadioButton rHard = new RadioButton("HARD");
		RadioButton rModerate = new RadioButton("MODERATE");
		RadioButton rEasy = new RadioButton("EASY");
		
		rHard.setFont(new Font(14));
		rModerate.setFont(new Font(14));
		rEasy.setFont(new Font(14));
		
		rHard.setPadding(new Insets(5,5,5,5));
		rModerate.setPadding(new Insets(5,5,5,5));
		rEasy.setPadding(new Insets(5,5,5,5));
		
		rHard.setToggleGroup(toggleGroup);
		rModerate.setToggleGroup(toggleGroup);
		rEasy.setToggleGroup(toggleGroup);
		
		label.setAlignment(Pos.TOP_CENTER);
		tilePane.setAlignment(Pos.CENTER);
		tilePane.getChildren().addAll(label, rHard,rModerate,rEasy);
		Scene sc = new Scene(tilePane, 250,200);
		
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
		
		dialog.getIcons().add(new Image("file:RawData/hiking.png"));
		dialog.setTitle("Level");
		dialog.setScene(sc);
		dialog.show();
		
	}

	public void editType(ActionEvent event) {
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		final Stage dialog = new Stage();
		
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(window);
		
		TilePane tilePane = new TilePane();
		
		Label label = new Label("Select Trail Type");
	    label.setFont(new Font("Microsoft JhengHei", 18));
	    label.setTextFill(Color.web("#58872e"));
	    label.setAlignment(Pos.TOP_CENTER);
	    label.setPadding(new Insets(0,0,0,0));

	    BackgroundFill bg = new BackgroundFill(Color.web("#DCEDC8"), null, null);
	    Background backGround = new Background(bg);
	    tilePane.setBackground(backGround);
		
		ToggleGroup toggleGroup = new ToggleGroup();
		
		RadioButton rPoint = new RadioButton("POINT_TO_POINT");
		RadioButton rLoop = new RadioButton("LOOP");
		RadioButton rOut = new RadioButton("OUT_AND_BACK");
		
		rPoint.setFont(new Font(14));
		rLoop.setFont(new Font(14));
		rOut.setFont(new Font(14));
		
		rPoint.setPadding(new Insets(5,5,5,5));
		rLoop.setPadding(new Insets(5,5,5,5));
		rOut.setPadding(new Insets(5,5,5,5));
		
		rPoint.setToggleGroup(toggleGroup);
		rLoop.setToggleGroup(toggleGroup);
		rOut.setToggleGroup(toggleGroup);
		
		tilePane.setAlignment(Pos.CENTER);
		label.setAlignment(Pos.TOP_CENTER);
		tilePane.getChildren().addAll(label, rPoint, rLoop, rOut);
		Scene sc = new Scene(tilePane, 250,200);
		
		toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n)
			{
				RadioButton radioButton = (RadioButton) toggleGroup.getSelectedToggle();
				if(radioButton != null) {
					String s = radioButton.getText();
					trail.setType(HikeType.valueOf(s));
					typeField.setText(trail.getType().name());
					dialog.close();
				}
			}
		});
		
		dialog.setTitle("Trail Type");
		dialog.setScene(sc);
		dialog.show();
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
