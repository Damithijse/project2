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
import lk.ijse.pos.bo.custom.BOFactory;
import lk.ijse.pos.bo.custom.BOType;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dto.CustomerDto;
import lk.ijse.pos.tm.CustomerTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CustomerFormController {


    public AnchorPane window;
    public TextField txtcusId;
    public TextField txtCusAddress;
    public TextField txtCusName;
    public TextField txtCusSalary;
    public TextField txtSearchHere;
    public TableView<CustomerTM> cusTable;
    public TableColumn Col_Name;
    public TableColumn col_Id;
    public TableColumn Col_Salary;
    public TableColumn Col_Address;
    public TableColumn Col_Operate;
    public Button btnSave;
    CustomerBO customerBO = BOFactory.getInstance().getBO(BOType.CUSTOMER);
    private String id;

    public void initialize() {
        col_Id.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        Col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Col_Address.setCellValueFactory(new PropertyValueFactory<>("address"));
        Col_Salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        Col_Operate.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadAllCustomers();


        cusTable.getSelectionModel().selectedItemProperty().
                addListener(
                        (observable, oldValue, newValue) -> {
                            //code here
                            if (newValue != null) {
                                loadSpecificData(newValue);

                            }
                        });
    }

    private void loadSpecificData(CustomerTM newValue) {
        btnSave.setText("Update");
        txtCusAddress.setText(newValue.getAddress());
        txtcusId.setText(newValue.getId());
        txtCusName.setText(newValue.getName());
        txtCusSalary.setText(String.valueOf(newValue.getSalary()));
    }

    private void loadAllCustomers() {

        try {
            List<CustomerDto> allCustomers = customerBO.getAllCustomer();
            ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
            for (CustomerDto all : allCustomers) {
                Button deleteButton = new Button("Delete");
                CustomerTM customerTM = new CustomerTM(
                        all.getId(),
                        all.getName(),
                        all.getAddress(),
                        all.getSalary(),
                        deleteButton
                );
                obList.add(customerTM);
                deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {


                        try {
                            Alert a1 = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure babe?", ButtonType.OK, ButtonType.NO);

                            Optional<ButtonType> buttontype = a1.showAndWait();
                            if (buttontype.get() == ButtonType.YES) {
                                boolean b = customerBO.deleteCustomer(all.getId());

                                if (b) {
                                    new Alert(Alert.AlertType.CONFIRMATION, "Deleted", ButtonType.OK).show();
                                }

                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                });

            }
            cusTable.setItems(obList);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
        btnSave.setText("Save");
        clear();
    }

    private void clear() {
        txtCusSalary.clear();
        txtCusName.clear();
        txtcusId.clear();
        txtCusAddress.clear();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (btnSave.getText().equalsIgnoreCase("Save")) {
            try {
                boolean isSaved = customerBO.saveCustomer(new CustomerDto(
                        txtcusId.getText(),
                        txtCusName.getText(),
                        txtCusAddress.getText(),
                        Double.parseDouble(txtCusSalary.getText())));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION,
                            "Customer has been Saved..",
                            ButtonType.OK).show();
                    loadAllCustomers();
                } else
                    new Alert(Alert.AlertType.CONFIRMATION,
                            "Try Again.. ",
                            ButtonType.OK).show();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {


            try {
                boolean isSaved = customerBO.updateCustomer(new CustomerDto(
                        txtcusId.getText(),
                        txtCusName.getText(),
                        txtCusAddress.getText(),
                        Double.parseDouble(txtCusSalary.getText())
                ));

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION,
                            "Customer has been Updated..",
                            ButtonType.OK).show();
                    loadAllCustomers();
                } else
                    new Alert(Alert.AlertType.CONFIRMATION,
                            "Try Again.. ",
                            ButtonType.OK).show();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void btnBackToHome(MouseEvent mouseEvent) throws IOException {
        Parent load = FXMLLoader.load(
                this.getClass().getResource("../view/MainForm.fxml"));
        Stage window = (Stage) this.window.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
