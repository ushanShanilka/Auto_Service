package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;



import java.io.IOException;

public class DashBoardFormConrtroller {

    public AnchorPane root1;
    public JFXButton btnLogOut;
    public AnchorPane root;
    public JFXButton btnAddCustomer;
    public JFXButton btnServiceCard;
    public JFXButton btnHome;


    public void initialize() throws IOException {
        setUI("HomeForm.fxml");
    }

    public void opeanAddCustomer(ActionEvent actionEvent) throws IOException {
        setUI("CustomerForm.fxml");

    }

    public void opeanVehical(ActionEvent actionEvent) throws IOException {
       setUI("VehicalForm.fxml");

    }

    public void opeanPayment(ActionEvent actionEvent) throws IOException {
        setUI("PaymentForm.fxml");
    }

    public void opeanItemTable(ActionEvent actionEvent) throws IOException {
        setUI("ItemTableForm.fxml");
    }

    public void btnLogOut(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml")));
    }

    public void setUI(String location) throws IOException {
        this.root1.getChildren().clear();
        this.root1.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/"+location)));
    }

    public void opeanAddHome(ActionEvent actionEvent) throws IOException {
        setUI("HomeForm.fxml");
    }

    public void opeanServiceCard(ActionEvent actionEvent) throws IOException {
        setUI("ServiceCardForm.fxml");
    }

    public void opeanUses(ActionEvent actionEvent) throws IOException {
        setUI("UsesForm.fxml");
    }

    public void opeanServicetype(ActionEvent actionEvent) throws IOException {
        setUI("ServiceTypeForm.fxml");
    }
}
