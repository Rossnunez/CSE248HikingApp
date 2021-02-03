package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.HikingHistory;

public class ControllerImageView implements Initializable {
	public HikingHistory hikingHistory;

	public Text imageTrail;
	public List<ImageView> list;

	public File filePath;
	public ImageView imageField;
	public Image defaultImage;
	public BufferedImage bufferedImage;

	public ImageView one;
	public ImageView two;
	public ImageView three;
	public ImageView four;
	public ImageView five;
	public ImageView six;
	public ImageView seven;
	public ImageView eight;
	public ImageView nine;
	//

	//
	public void removeImage1(MouseEvent event) {
		if(one.getImage() != null) {
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Remove Image");
			window.setMinWidth(250);
			Label label = new Label();
			label.setText("Would you like to remove this image?");
			label.setAlignment(Pos.TOP_CENTER);
			
			//two buttons!!! :D
			Button yesBtn = new Button("Yes");
			Button noBtn = new Button("No");
			
			yesBtn.setOnAction(e ->{
				one.setImage(null);
				hikingHistory.getImages().remove(0);
				System.out.println("Image removed!");
				window.close();
				refreshPage();
				
			});
			
			noBtn.setOnAction(e ->{
				System.out.println("Image not removed!");
				window.close();
			});
			
			VBox layout = new VBox(10);
			layout.getChildren().addAll(label,yesBtn,noBtn);
			layout.setAlignment(Pos.CENTER);
			Scene scene = new Scene(layout);
			window.setScene(scene);
			window.showAndWait();
		}	
	}
	
	public void removeImage2(MouseEvent event) {
		if(two.getImage() != null) {
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Remove Image");
			window.setMinWidth(250);
			Label label = new Label();
			label.setText("Would you like to remove this image?");
			label.setAlignment(Pos.TOP_CENTER);
			
			//two buttons!!! :D
			Button yesBtn = new Button("Yes");
			Button noBtn = new Button("No");
			
			yesBtn.setOnAction(e ->{
				two.setImage(null);
				hikingHistory.getImages().remove(1);
				System.out.println("Image removed!");
				window.close();
				refreshPage();
				
			});
			
			noBtn.setOnAction(e ->{
				System.out.println("Image not removed!");
				window.close();
			});
			
			VBox layout = new VBox(10);
			layout.getChildren().addAll(label,yesBtn,noBtn);
			layout.setAlignment(Pos.CENTER);
			Scene scene = new Scene(layout);
			window.setScene(scene);
			window.showAndWait();
		}	
	}
	
	public void removeImage3(MouseEvent event) {
		if(three.getImage() != null) {
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Remove Image");
			window.setMinWidth(250);
			Label label = new Label();
			label.setText("Would you like to remove this image?");
			label.setAlignment(Pos.TOP_CENTER);
			
			//two buttons!!! :D
			Button yesBtn = new Button("Yes");
			Button noBtn = new Button("No");
			
			yesBtn.setOnAction(e ->{
				three.setImage(null);
				hikingHistory.getImages().remove(2);
				System.out.println("Image removed!");
				window.close();
				refreshPage();
				
			});
			
			noBtn.setOnAction(e ->{
				System.out.println("Image not removed!");
				window.close();
			});
			
			VBox layout = new VBox(10);
			layout.getChildren().addAll(label,yesBtn,noBtn);
			layout.setAlignment(Pos.CENTER);
			Scene scene = new Scene(layout);
			window.setScene(scene);
			window.showAndWait();
		}	
	}
	
	public void removeImage4(MouseEvent event) {
		if(four.getImage() != null) {
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Remove Image");
			window.setMinWidth(250);
			Label label = new Label();
			label.setText("Would you like to remove this image?");
			label.setAlignment(Pos.TOP_CENTER);
			
			//two buttons!!! :D
			Button yesBtn = new Button("Yes");
			Button noBtn = new Button("No");
			
			yesBtn.setOnAction(e ->{
				four.setImage(null);
				hikingHistory.getImages().remove(3);
				System.out.println("Image removed!");
				window.close();
				refreshPage();
				
			});
			
			noBtn.setOnAction(e ->{
				System.out.println("Image not removed!");
				window.close();
			});
			
			VBox layout = new VBox(10);
			layout.getChildren().addAll(label,yesBtn,noBtn);
			layout.setAlignment(Pos.CENTER);
			Scene scene = new Scene(layout);
			window.setScene(scene);
			window.showAndWait();
		}	
	}
	
	public void removeImage5(MouseEvent event) {
		if(five.getImage() != null) {
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Remove Image");
			window.setMinWidth(250);
			Label label = new Label();
			label.setText("Would you like to remove this image?");
			label.setAlignment(Pos.TOP_CENTER);
			
			//two buttons!!! :D
			Button yesBtn = new Button("Yes");
			Button noBtn = new Button("No");
			
			yesBtn.setOnAction(e ->{
				five.setImage(null);
				hikingHistory.getImages().remove(4);
				System.out.println("Image removed!");
				window.close();
				refreshPage();
				
			});
			
			noBtn.setOnAction(e ->{
				System.out.println("Image not removed!");
				window.close();
			});
			
			VBox layout = new VBox(10);
			layout.getChildren().addAll(label,yesBtn,noBtn);
			layout.setAlignment(Pos.CENTER);
			Scene scene = new Scene(layout);
			window.setScene(scene);
			window.showAndWait();
		}	
	}
	
	public void removeImage6(MouseEvent event) {
		if(six.getImage() != null) {
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Remove Image");
			window.setMinWidth(250);
			Label label = new Label();
			label.setText("Would you like to remove this image?");
			label.setAlignment(Pos.TOP_CENTER);
			
			//two buttons!!! :D
			Button yesBtn = new Button("Yes");
			Button noBtn = new Button("No");
			
			yesBtn.setOnAction(e ->{
				six.setImage(null);
				hikingHistory.getImages().remove(5);
				System.out.println("Image removed!");
				window.close();
				refreshPage();
				
			});
			
			noBtn.setOnAction(e ->{
				System.out.println("Image not removed!");
				window.close();
			});
			
			VBox layout = new VBox(10);
			layout.getChildren().addAll(label,yesBtn,noBtn);
			layout.setAlignment(Pos.CENTER);
			Scene scene = new Scene(layout);
			window.setScene(scene);
			window.showAndWait();
		}	
	}
	
	public void removeImage7(MouseEvent event) {
		if(seven.getImage() != null) {
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Remove Image");
			window.setMinWidth(250);
			Label label = new Label();
			label.setText("Would you like to remove this image?");
			label.setAlignment(Pos.TOP_CENTER);
			
			//two buttons!!! :D
			Button yesBtn = new Button("Yes");
			Button noBtn = new Button("No");
			
			yesBtn.setOnAction(e ->{
				seven.setImage(null);
				hikingHistory.getImages().remove(6);
				System.out.println("Image removed!");
				window.close();
				refreshPage();
				
			});
			
			noBtn.setOnAction(e ->{
				System.out.println("Image not removed!");
				window.close();
			});
			
			VBox layout = new VBox(10);
			layout.getChildren().addAll(label,yesBtn,noBtn);
			layout.setAlignment(Pos.CENTER);
			Scene scene = new Scene(layout);
			window.setScene(scene);
			window.showAndWait();
		}	
	}
	
	public void removeImage8(MouseEvent event) {
		if(eight.getImage() != null) {
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Remove Image");
			window.setMinWidth(250);
			Label label = new Label();
			label.setText("Would you like to remove this image?");
			label.setAlignment(Pos.TOP_CENTER);
			
			//two buttons!!! :D
			Button yesBtn = new Button("Yes");
			Button noBtn = new Button("No");
			
			yesBtn.setOnAction(e ->{
				eight.setImage(null);
				hikingHistory.getImages().remove(7);
				System.out.println("Image removed!");
				window.close();
				refreshPage();
				
			});
			
			noBtn.setOnAction(e ->{
				System.out.println("Image not removed!");
				window.close();
			});
			
			VBox layout = new VBox(10);
			layout.getChildren().addAll(label,yesBtn,noBtn);
			layout.setAlignment(Pos.CENTER);
			Scene scene = new Scene(layout);
			window.setScene(scene);
			window.showAndWait();
		}	
	}
	
	public void removeImage9(MouseEvent event) {
		if(nine.getImage() != null) {
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Remove Image");
			window.setMinWidth(250);
			Label label = new Label();
			label.setText("Would you like to remove this image?");
			label.setAlignment(Pos.TOP_CENTER);
			
			//two buttons!!! :D
			Button yesBtn = new Button("Yes");
			Button noBtn = new Button("No");
			
			yesBtn.setOnAction(e ->{
				nine.setImage(null);
				hikingHistory.getImages().remove(8);
				System.out.println("Image removed!");
				window.close();
				refreshPage();
				
			});
			
			noBtn.setOnAction(e ->{
				System.out.println("Image not removed!");
				window.close();
			});
			
			VBox layout = new VBox(10);
			layout.getChildren().addAll(label,yesBtn,noBtn);
			layout.setAlignment(Pos.CENTER);
			Scene scene = new Scene(layout);
			window.setScene(scene);
			window.showAndWait();
		}	
	}
	
	public void goBackPane(ActionEvent event) throws IOException {
		Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/view2.fxml"));
		Scene secondScene = new Scene(secondRoot);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(secondScene);
		window.show();
	}
	
	public void refreshPage() {
		int i = 0;
		one.setImage(null);
		two.setImage(null);
		three.setImage(null);
		four.setImage(null);
		five.setImage(null);
		six.setImage(null);
		seven.setImage(null);
		eight.setImage(null);
		nine.setImage(null);
		for (String string : hikingHistory.getImages()) {
				File file = new File(string);
				setFilePath(file);

				try {
					bufferedImage = ImageIO.read(filePath);
				} catch (IOException e) {
					System.out.println("ERROR");
				}
				Image image = SwingFXUtils.toFXImage(bufferedImage, null);
				list.get(i).setImage(image);
				i++;
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
		hikingHistory = Controller2.getHikingHistory();
		imageTrail.setText(hikingHistory.getTrailName());

		list = new LinkedList<ImageView>();
		list.add(one);
		list.add(two);
		list.add(three);
		list.add(four);
		list.add(five);
		list.add(six);
		list.add(seven);
		list.add(eight);
		list.add(nine);
		
		int i = 0;
		for (String string : hikingHistory.getImages()) {
				File file = new File(string);
				setFilePath(file);

				try {
					bufferedImage = ImageIO.read(filePath);
				} catch (IOException e) {
					System.out.println("ERROR");
				}
				Image image = SwingFXUtils.toFXImage(bufferedImage, null);
				list.get(i).setImage(image);
				i++;
		}
	}
}
