/* Created by LeloKarma
* Jdbc connection included
*Javafx
*/

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class RegistrationForm  extends Application{
	
	
	String dburl = "jdbc:mysql://localhost:3306/registration_db";
	String dbusername = "root";
	String dbpassword = "lelolelo";

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Registration Form");
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20, 20, 20, 20));
		grid.setVgap(10);
		grid.setHgap(10);
		
		Label usernameLabel = new Label("Username:");
		GridPane.setConstraints(usernameLabel,  0, 0);
		TextField usernameInput = new TextField();
		GridPane.setConstraints(usernameInput, 1, 0);
		
		Label emailLabel = new Label("Email:");
		GridPane.setConstraints(emailLabel,  0, 1);
		TextField emailInput = new TextField();
		GridPane.setConstraints(emailInput, 1, 1);
		
		Label passwordLabel = new Label("Password:");
		GridPane.setConstraints(passwordLabel,  0, 2);
		PasswordField passwordInput = new PasswordField();
		GridPane.setConstraints(passwordInput, 1, 2);
		
		Button registerButton = new Button("Register");
		GridPane.setConstraints(registerButton, 1, 3);
		
		//Register button event handler
		registerButton.setOnAction(event -> {
			String username = usernameInput.getText();
			String email = emailInput.getText();
			String password = passwordInput.getText();
			
			if(isValidEmail(email)) {
			if(validateInput(username, email, password, email)) {
				if (registerUser(username, password, email)) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					
					alert.setTitle("Registration Successful!");
					alert.setHeaderText(null);
					alert.setContentText("Registration completed successfully!");
					alert.showAndWait();
					
				}else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Registration Not Successful!");
					alert.setHeaderText(null);
					alert.setContentText("Failed to register. Please Try Again");
					alert.showAndWait();
				 }
				}else {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("Invalid Input");
					alert.setHeaderText("Please Fill all the Fields.");
					alert.showAndWait();
				}
			}
			
		}
		
	);
		
		
		grid.getChildren().addAll(usernameLabel, emailLabel, passwordLabel, usernameInput, passwordInput, emailInput, registerButton);
		
		
		Scene scene = new Scene(grid, 300, 200);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	private boolean validateInput(String username, String password, String email, String fullname) {
	    return !username.isEmpty() && !password.isEmpty() && !email.isEmpty() && !fullname.isEmpty();
	}
	
	private boolean isValidEmail(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9+_.-]+$";
		Pattern pattern = Pattern.compile(emailRegex);
		return pattern.matcher(email).matches();
	}
	
	private boolean registerUser(String username, String password, String email) {
		try {
			Connection conn = DriverManager.getConnection(dburl, dbusername, dbpassword);
			  String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
			  
			  PreparedStatement prepareStatement = conn.prepareStatement(sql);
			  prepareStatement.setString(1,  username);
			  prepareStatement.setString(2, email);
			  prepareStatement.setString(3, password);
			  
			  int rowsAffected = prepareStatement.executeUpdate();
			  return rowsAffected > 0;
			  
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
