package lk.ijse.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.tm.ItemTM;

import java.io.IOException;
import java.sql.*;

public class ItemFormController {
    public TableColumn col_Operate;
    public TableColumn col_UPrice;
    public TableColumn col_description;
    public TableColumn col_code;
    public TextField txtSearch;
    public TableView table;
    public Button btnSave;
    public TextField txtQTYOnHand;
    public TextField txtDescription;
    public TextField txtUPrice;
    public TextField txtCode;
    public AnchorPane window;
    public TableColumn colQtyOnHand;


    public void initialize() {
        col_code.setCellValueFactory(
                new PropertyValueFactory<>("code"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_UPrice.setCellValueFactory(new PropertyValueFactory<>("uprice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        col_Operate.setCellValueFactory(new PropertyValueFactory<>("button"));
        loadAllItems();
    }

    private void loadAllItems() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ThogaKade",
                    "root",
                    "ijse");
            PreparedStatement stm =
                    con.prepareStatement(
                            "SELECT * FROM Item");
            ResultSet resultSet = stm.executeQuery();
            ObservableList<ItemTM> obList = FXCollections.observableArrayList();
            while (resultSet.next()) {

                Button deleteButton = new Button("Delete");

                ItemTM itemTm = new ItemTM(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4),
                        deleteButton
                );
                obList.add(itemTm);

                deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("deleted");
                    }
                });

            }
            table.setItems(obList);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void btnNewOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ThogaKade",
                    "root",
                    "ijse");
            PreparedStatement stm =
                    con.prepareStatement(
                            "INSERT INTO Item VALUES(?,?,?,?)");
            stm.setObject(1,
                    txtCode.getText());
            stm.setObject(2, txtDescription.getText());
            stm.setObject(3, Double.parseDouble(txtUPrice.getText()));
            stm.setObject(4, Double.parseDouble(txtQTYOnHand.getText()));
            boolean isSaved = stm.executeUpdate() > 0;
            if (isSaved)
                new Alert(Alert.AlertType.CONFIRMATION,
                        "Item has been Saved..",
                        ButtonType.OK).show();
            else
                new Alert(Alert.AlertType.CONFIRMATION,
                        "Try Again.. ",
                        ButtonType.OK).show();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void btnBackToHome(MouseEvent mouseEvent) throws IOException {
        Parent load = FXMLLoader.load(
                this.getClass().getResource("../view/MainForm.fxml"));
        Stage window = (Stage) this.window.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
