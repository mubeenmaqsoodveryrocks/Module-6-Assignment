package com.example.registration;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;


public class HelloApplication extends Application {
    private boolean isFirstNameValid = true;        //private boolean methods
    private boolean isLastNameValid = true;
    private boolean isEmailValid = true;
    private boolean isDobValid = true;
    private boolean isZipValid = true;

    @Override
    public void start(Stage primaryStage) {
        // TextFields For Methods
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField emailField = new TextField();
        TextField dobField = new TextField();
        TextField zipField = new TextField();

        // Labels For Methods
        Label firstNameHint = new Label();
        Label lastNameHint = new Label();
        Label emailHint = new Label();
        Label dobHint = new Label();
        Label zipHint = new Label();

        //We Added Button Called "Add".
        Button addButton = new Button("Add");
        boolean addValidation;
        if (addValidation = true) {

            addButton.setDisable(false);
        }
        else { addButton.setDisable(true);
        }
        // Validation handlers
        addValidation(firstNameField, firstNameHint, "First Name must be 2-25 characters.",
                "^[A-Za-z]{2,25}$", valid -> isFirstNameValid = valid, addButton);

        addValidation(lastNameField, lastNameHint, "Last Name must be 2-25 characters.",
                "^[A-Za-z]{2,25}$", valid -> isLastNameValid = valid, addButton);

        addValidation(emailField, emailHint, "Must accept only Farmingdale email (e.g., user@farmingdale.edu).",
                "^[\\w._%+-]+@farmingdale\\.edu$", valid -> isEmailValid = valid, addButton);

        addValidation(dobField, dobHint, "Date of Birth must be in MM/DD/YYYY format.",
                "^(0[1-9]|1[0-2])/([0-2][0-9]|3[01])/\\d{4}$", valid -> isDobValid = valid, addButton);

        addValidation(zipField, zipHint, "Zip Code must be 5-digit numbers.",
                "^\\d{5}$", valid -> isZipValid = valid, addButton);

        // Add button action
        addButton.setOnAction(e -> {
            showSuccessUI(primaryStage);
        });

        //Graphical User Interface Layout
        GridPane formGrid = new GridPane();
        formGrid.setVgap(10);
        formGrid.setHgap(10);
        formGrid.setPadding(new Insets(20));
        formGrid.setAlignment(Pos.CENTER);

        formGrid.add(new Label("First Name:"), 0, 0);
        formGrid.add(firstNameField, 1, 0);
        formGrid.add(firstNameHint, 2, 0);

        formGrid.add(new Label("Last Name:"), 0, 1);
        formGrid.add(lastNameField, 1, 1);
        formGrid.add(lastNameHint, 2, 1);

        formGrid.add(new Label("Email:"), 0, 2);
        formGrid.add(emailField, 1, 2);
        formGrid.add(emailHint, 2, 2);

        formGrid.add(new Label("Date of Birth:"), 0, 3);
        formGrid.add(dobField, 1, 3);
        formGrid.add(dobHint, 2, 3);

        formGrid.add(new Label("ZIP Code:"), 0, 4);
        formGrid.add(zipField, 1, 4);
        formGrid.add(zipHint, 2, 4);

        formGrid.add(addButton, 1, 5);

        Scene scene = new Scene(formGrid, 700, 400);
        primaryStage.setTitle("Registration Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addValidation(TextField field, Label hintLabel, String hint, String regex,
                               java.util.function.Consumer<Boolean> updateValidity,
                               Button addButton) {
        field.focusedProperty().addListener((obs, oldVal, newVal) -> {

            if (!newVal) {
                boolean isValid = field.getText().matches(regex);
                updateValidity.accept(isValid);
                hintLabel.setText(isValid ? "✓" : hint);
                hintLabel.setStyle(isValid ? "-fx-text-fill: green;" : "-fx-text-fill: red;");
                updateAddButton(addButton);
            }
        });

        field.addEventFilter(KeyEvent.KEY_TYPED, e -> {
            hintLabel.setText("");
        });
    }

    private void updateAddButton(Button addButton) {
        addButton.setDisable(!(isFirstNameValid && isLastNameValid && isEmailValid && isDobValid && isZipValid));
    }
    //Set the stage
    private void showSuccessUI(Stage stage) {
        Label successLabel = new Label("Registration Successful!");
        successLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: green;");
        VBox box = new VBox(successLabel);
        box.setAlignment(Pos.CENTER);
        Scene newScene = new Scene(box, 400, 200);
        stage.setScene(newScene);
    }

    /**
     *
     * @param args
     * @return main
     */
    public static void main(String[] args) {
        launch(args);
    }
}
