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
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.HikingHistory;

public class ControllerImageView implements Initializable {
	public HikingHistory hikingHistory;

	public Text imageTrail;

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
	public void goBackPane(ActionEvent event) {

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

		List<ImageView> list = new LinkedList<ImageView>();
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
