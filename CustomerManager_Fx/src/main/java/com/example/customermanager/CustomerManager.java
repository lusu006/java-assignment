

package com.example.customermanager;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerManager extends Application {

    private ObservableList<Customer> customers =
            FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {

        Label nameLabel = new Label("Customer Name:");
        TextField nameField = new TextField();

        Label provinceLabel = new Label("Province:");

        ComboBox<String> provinceBox = new ComboBox<>();

        provinceBox.getItems().addAll(
                "Central",
                "Copperbelt",
                "Eastern",
                "Luapula",
                "Lusaka",
                "Muchinga",
                "Northern",
                "North-Western",
                "Southern",
                "Western"
        );

        Button addButton = new Button("Add Customer");
        Button deleteButton = new Button("Delete Customer");

        TableView<Customer> table = new TableView<>();

        TableColumn<Customer, String> nameColumn =
                new TableColumn<>("Name");

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        TableColumn<Customer, String> provinceColumn =
                new TableColumn<>("Province");

        provinceColumn.setCellValueFactory(
                new PropertyValueFactory<>("province")
        );

        table.getColumns().addAll(
                nameColumn,
                provinceColumn
        );

        table.setItems(customers);

        addButton.setOnAction(event -> {

            String name = nameField.getText().trim();
            String province = provinceBox.getValue();

            if (name.isEmpty() || province == null) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Invalid Input",
                        "Please enter a name and select a province."
                );

                return;
            }

            Customer customer =
                    new Customer(name, province);

            customers.add(customer);

            nameField.clear();
            provinceBox.setValue(null);
        });

        deleteButton.setOnAction(event -> {

            Customer selectedCustomer =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selectedCustomer == null) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "No Customer Selected",
                        "Please select a customer to delete."
                );

                return;
            }

            Alert confirmation =
                    new Alert(Alert.AlertType.CONFIRMATION);

            confirmation.setTitle("Confirm Deletion");
            confirmation.setHeaderText("Delete Customer");

            confirmation.setContentText(
                    "Are you sure you want to delete "
                            + selectedCustomer.getName() + "?"
            );

            confirmation.showAndWait().ifPresent(response -> {

                if (response == ButtonType.OK) {
                    customers.remove(selectedCustomer);
                }
            });
        });

        HBox nameBox = new HBox(10);

        nameBox.getChildren().addAll(
                nameLabel,
                nameField
        );

        HBox provinceLayout = new HBox(10);

        provinceLayout.getChildren().addAll(
                provinceLabel,
                provinceBox
        );

        HBox buttonBox = new HBox(10);

        buttonBox.getChildren().addAll(
                addButton,
                deleteButton
        );

        VBox root = new VBox(15);

        root.setPadding(new Insets(20));

        root.getChildren().addAll(
                nameBox,
                provinceLayout,
                buttonBox,
                table
        );

        Scene scene =
                new Scene(root, 600, 450);

        stage.setTitle("Customer Manager");
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(
            Alert.AlertType type,
            String title,
            String message) {

        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}