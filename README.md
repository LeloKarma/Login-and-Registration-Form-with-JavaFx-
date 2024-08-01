# Login-and-Registration-Form-with-JavaFx

# Registration Form Application

This is a simple JavaFX application that implements a registration form. It allows users to enter their username, email, and password for registration. The application validates the input and securely stores the user's information in a MySQL database.

## Prerequisites

- Java Development Kit (JDK) installed
- MySQL database server installed and running
- 
- MySQL Connector/J library added to the project's classpath

## Getting Started

1. Clone or download the repository to your local machine.

2. Import the project into your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).

3. Set up the MySQL database:
   - Create a new database named `registration_db`.
   - Create a table named `users` with the following columns:
     - `username` (VARCHAR)
     - `email` (VARCHAR)
     - `password` (VARCHAR)
   - Update the `dburl`, `dbusername`, and `dbpassword` variables in the `RegistrationForm.java` file to match your MySQL database configuration.

4. Build and run the `RegistrationForm.java` class to launch the application.

5. The registration form will appear, allowing you to enter your username, email, and password.

6. Click the "Register" button to submit the form. If the input is valid and the registration is successful, an information dialog will be displayed. Otherwise, an error or warning dialog will appear.

## Additional Notes

- The application uses JavaFX for the user interface and JDBC for connecting to the MySQL database.

- Input validation is performed to ensure that all fields are filled out and that the email is in a valid format.

- The `registerUser` method securely stores user information in the `users` table using prepared statements to prevent SQL injection.

- The application assumes a basic development setup and does not include advanced features such as password hashing or encryption. For production use, additional security measures should be implemented.

- The code provides comments to explain the functionality of each section and method.

## License

This project is licensed under the [MIT License](LICENSE).
