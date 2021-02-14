package controller;

import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sun.prism.paint.Paint.Type;

import app.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.AccountType;
import model.Status;
import model.User;

public class ControllerEditUsers implements Initializable {
	public TreeMap<String, User> userMap;
	public User selectedUser;

	public FileChooser fileChooser;
	public File filePath;

	public ComboBox<String> accountTypeBox;
	public ComboBox<String> statusBox;

	public String image;
	public ImageView imageView;
	public Image defaultImage;
	public BufferedImage bufferedImage;

	public Text selectedUserText;
	public TextField usernameField;
	public TextField passwordField;
	public TextField firstNameField;
	public TextField lastNameField;
	public TextField phonenumberField;

	public ObservableList<User> userList;
	public TableView<User> userTable = new TableView<User>();
	public TableColumn<User, String> usernameCol;
	public TableColumn<User, String> uncompletedCol;
	public TableColumn<User, String> historyCol;
	public TableColumn<User, String> typeCol;
	public TableColumn<User, String> statusCol;

	public void selectAccountType(ActionEvent event) {
		if (selectedUser != null) {
			String type = accountTypeBox.getSelectionModel().getSelectedItem().toString();
			if (type.contentEquals("ADMIN")) {
				selectedUser.setAccountType(AccountType.ADMIN);
				userTable.refresh();
			} else {
				selectedUser.setAccountType(AccountType.USER);
				userTable.refresh();
			}
		}
	}

	public void selectStatus(ActionEvent event) {
		if (selectedUser != null) {
			String status = statusBox.getSelectionModel().getSelectedItem().toString();
			if (status.contentEquals("ENABLE")) {
				selectedUser.setStatus(Status.ENABLED);
				userTable.refresh();
			} else {
				selectedUser.setStatus(Status.DISABLED);
				userTable.refresh();
			}
		}
	}

	public void goBackPane(ActionEvent event) throws IOException {
		Parent secondRoot = FXMLLoader.load(getClass().getResource("/view/view3.fxml"));
		Scene secondScene = new Scene(secondRoot);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(secondScene);
		window.show();
	}

	public void selectUser(MouseEvent event) throws IOException {
		if (event.getClickCount() == 2) {

			JFrame frame = new JFrame("FrameDemo");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			if (selectedUser != null) {
				int n = JOptionPane.showConfirmDialog(frame, "Do you want to remove this User?", "Remove User",
						JOptionPane.YES_NO_OPTION);

				if (n == JOptionPane.YES_OPTION) {
					userMap.remove(selectedUser.getUsername());
					userTable.getItems().remove(userTable.getSelectionModel().getSelectedItem());

				} else if (n == JOptionPane.NO_OPTION) {
					System.out.println("no");
				}
			}

		} else {
			imageView.setImage(null);
			selectedUser = userTable.getSelectionModel().getSelectedItem();
			try {
				selectedUserText.setText(selectedUser.getUsername());

				passwordField.setText(selectedUser.getPassword());
				firstNameField.setText(selectedUser.getFirstName());
				lastNameField.setText(selectedUser.getLastName());
				phonenumberField.setText(selectedUser.getPhoneNumber());

				File file = new File(selectedUser.getImage());
				setFilePath(file);

				try {
					image = filePath.getPath();
					bufferedImage = ImageIO.read(filePath);
					Image image = SwingFXUtils.toFXImage(bufferedImage, null);
					imageView.setImage(image);
				} catch (IOException e) {
					file = new File(
							"C:\\Users\\nross\\Desktop\\CSE248Portfolio\\CSE248HikingApp\\Hiking Application\\RawData\\user.png");
					setFilePath(file);
					image = filePath.getPath();
					bufferedImage = ImageIO.read(filePath);
					Image image = SwingFXUtils.toFXImage(bufferedImage, null);
					imageView.setImage(image);
				}
			} catch (NullPointerException e) {
				//
			}
		}
	}

	public void changeImage(MouseEvent event) {
		if (selectedUser != null) {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			fileChooser = new FileChooser();

			this.setFilePath(fileChooser.showOpenDialog(stage));

			try {
				// image = filePath.getPath();
				BufferedImage bufferedImage = ImageIO.read(filePath);
				Image image = SwingFXUtils.toFXImage(bufferedImage, null);
				imageView.setImage(image);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			selectedUser.setImage(filePath.getPath());
		}
	}

	public File getFilePath() {
		return filePath;
	}

	public void setFilePath(File filePath) {
		this.filePath = filePath;
	}

	public void keyPressed(KeyEvent event) {
		userList.clear();

		String search = usernameField.getText();

		if (!search.contentEquals("")) {
			Map<String, User> result = userMap.entrySet().stream().filter(user -> user.getKey().contains(search))
					.collect(Collectors.toMap(user -> user.getKey(), user -> user.getValue()));

			for (java.util.Map.Entry<String, User> entry : result.entrySet()) {
				userList.add(entry.getValue());
			}

			userTable.setItems(userList);
		}
	}

	public void searchField(ActionEvent event) {
		userList.clear();

		String search = usernameField.getText();

		if (!search.contentEquals("")) {
			Map<String, User> result = userMap.entrySet().stream().filter(user -> user.getKey().contains(search))
					.collect(Collectors.toMap(user -> user.getKey(), user -> user.getValue()));

			for (java.util.Map.Entry<String, User> entry : result.entrySet()) {
				userList.add(entry.getValue());
			}

			userTable.setItems(userList);
		}

	}

	public void editPassword(ActionEvent event) {
		if (selectedUser != null) {
			TextInputDialog tid = new TextInputDialog("Enter your Password:");
			tid.setHeaderText("Edit Password");
			tid.showAndWait();
			String password = tid.getEditor().getText();
			selectedUser.setPassword(password);
			passwordField.setText(password);
		}
	}

	public void editFirstName(ActionEvent event) {
		if (selectedUser != null) {
			TextInputDialog tid = new TextInputDialog("Enter your First Name:");
			tid.setHeaderText("Edit First Name");
			tid.showAndWait();
			String firstname = tid.getEditor().getText();
			selectedUser.setFirstName(firstname);
			firstNameField.setText(firstname);
		}
	}

	public void editLastName(ActionEvent event) {
		if (selectedUser != null) {
			TextInputDialog tid = new TextInputDialog("Enter your Last Name:");
			tid.setHeaderText("Edit Last Name");
			tid.showAndWait();
			String lastname = tid.getEditor().getText();
			selectedUser.setLastName(lastname);
			lastNameField.setText(lastname);
		}
	}

	public void editPhonenumber(ActionEvent event) {
		if (selectedUser != null) {
			TextInputDialog tid = new TextInputDialog("Enter your Phonenumber");
			tid.setHeaderText("Edit Phonenumber");
			tid.showAndWait();
			String phonenumber = tid.getEditor().getText();
			selectedUser.setPhoneNumber(phonenumber);
			phonenumberField.setText(phonenumber);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		userMap = Main.getUserMap();
		image = "\\RawData\\user.png";
		userList = FXCollections.observableArrayList();
		usernameCol.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		uncompletedCol.setCellValueFactory(new PropertyValueFactory<User, String>("uncompletedHikeSize"));
		historyCol.setCellValueFactory(new PropertyValueFactory<User, String>("hikingHistorySize"));
		typeCol.setCellValueFactory(new PropertyValueFactory<User, String>("accountType"));
		statusCol.setCellValueFactory(new PropertyValueFactory<User, String>("status"));

		selectedUser = null;

		final ObservableList<String> accountTypeStrings = FXCollections.observableArrayList();
		accountTypeStrings.add("ADMIN");
		accountTypeStrings.add("USER");
		accountTypeBox.getItems().addAll(accountTypeStrings);

		final ObservableList<String> statusStrings = FXCollections.observableArrayList();
		statusStrings.add("ENABLE");
		statusStrings.add("DISABLE");
		statusBox.getItems().addAll(statusStrings);

	}

}
