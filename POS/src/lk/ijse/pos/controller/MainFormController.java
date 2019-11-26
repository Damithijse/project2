package lk.ijse.pos.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {
    public AnchorPane window;

    public void btnOpenItemOnAction(MouseEvent mouseEvent) throws IOException {
        openWindow("ItemForm");
    }

    public void btnOpenCustomer(MouseEvent mouseEvent) throws IOException {
        openWindow("CustomerForm");
    }

    private void openWindow(String location) throws IOException {
        Parent load = FXMLLoader.load(
                this.getClass().getResource("../view/" + location + ".fxml"));
        Stage window = (Stage) this.window.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
