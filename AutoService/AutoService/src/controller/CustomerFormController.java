package controller;

import bo.BoFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import view.tm.CustomerTM;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;


public class CustomerFormController {
    public AnchorPane root1;
    public JFXButton btnAddCustomer;
    public JFXTextField txtAddress;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public TableColumn colCustomerName;
    public TableColumn colAddress;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn colOperate;
    public JFXTextField txtContac;
    public TableColumn colContact;
    public TableColumn colCustomerID;
    public AnchorPane CustomerForm;
    public JFXButton btnUpdat;


    CustomerBO bo = BoFactory.getInstance()
            .getBo(BoFactory.BOType.CUSTOMER);


    public void setUI(String location) throws IOException {
        this.root1.getChildren().clear();
        this.root1.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/" + location)));
    }


    public void saveOnAction(ActionEvent actionEvent) throws Exception {
        if (Pattern.compile("^(C00)[1-9]{1}$").matcher(txtId.getText()).matches()) {
            if (Pattern.compile("^[A-z]{1,}\\s|[A-z]{1,}$").matcher(txtName.getText()).matches()) {
                if (Pattern.compile("^[A-z]{1,}\\s|[A-z]{1,}$").matcher(txtAddress.getText()).matches()) {
                    if (Pattern.compile("^[0-9]{9,10}$").matcher(txtContac.getText()).matches()) {
                        try {
                            boolean isSaved = bo.saveCustomer(new CustomerDTO(txtId.getText(),
                                    txtName.getText(),
                                    txtAddress.getText(),
                                    txtContac.getText()
                            ));
                            if (isSaved) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Customer is Saved");
                                alert.show();
                                loadAllCustomers();
                                clearTextField();
                            }else{
                                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Customer Not Saved");
                                alert.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                    txtContac.setFocusColor(Paint.valueOf("red"));
                    txtContac.requestFocus();
                }
            }else{
                    txtAddress.setFocusColor(Paint.valueOf("red"));
                    txtAddress.requestFocus();

            }
        }else{
            txtName.setFocusColor(Paint.valueOf("red"));
            txtName.requestFocus();
        }

    }else{
        txtId.setFocusColor(Paint.valueOf("red"));
        txtId.requestFocus();
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Pattern Not Matched");
        alert.show();
    }

}
    public void initialize () throws Exception {

            loadCID();

            colCustomerID.setCellValueFactory(new PropertyValueFactory("CID"));
            colCustomerName.setCellValueFactory(new PropertyValueFactory("name"));
            colAddress.setCellValueFactory(new PropertyValueFactory("address"));
            colContact.setCellValueFactory(new PropertyValueFactory("contact"));
            colOperate.setCellValueFactory(new PropertyValueFactory("btn"));

            loadAllCustomers();


            tblCustomer.getSelectionModel().selectedItemProperty().
                    addListener((observable, oldValue, newValue) -> {
                        setData(newValue);
                    });

        }

        private void loadCID () throws Exception {
            String CID = bo.getCID();
            txtId.setText(CID);
        }

        private void setData (CustomerTM tm){
            try {
                txtId.setText(tm.getCID());
                txtName.setText(tm.getName());
                txtAddress.setText(tm.getAddress());
                txtContac.setText(tm.getContact());
            } catch (NullPointerException ex) {

            }
        }

        private void loadAllCustomers () throws Exception {

            ObservableList<CustomerTM> tmList = FXCollections.observableArrayList();
            List<CustomerDTO> allCustomers = bo.getAllCustomers();
            for (CustomerDTO dto : allCustomers) {
                JFXButton btn = new JFXButton("Delete");
                CustomerTM tm = new CustomerTM(dto.getCID(), dto.getName(), dto.getAddress(), dto.getContact(), btn);
                tmList.add(tm);
                btn.setOnAction((e) -> {
                    try {

                        ButtonType ok = new ButtonType("OK",
                                ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("NO",
                                ButtonBar.ButtonData.CANCEL_CLOSE);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                                "Are You Sure ?", ok, no);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.orElse(no) == ok) {
                            if (bo.deleteCustomer(tm.getCID())) {
                                new Alert(Alert.AlertType.CONFIRMATION,
                                        "Deleted", ButtonType.OK).show();
                                loadAllCustomers();
                                return;
                            }
                            new Alert(Alert.AlertType.WARNING,
                                    "Try Again", ButtonType.OK).show();
                            JOptionPane jOptionPane = new JOptionPane(alert);
                        } else {

                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
            }
            tblCustomer.setItems(tmList);

        }

        public void updateOnAction (ActionEvent actionEvent) throws Exception {

            boolean isUpdated = bo.UpdateCustomer(
                    new CustomerDTO(txtId.getText(),
                            txtName.getText(),
                            txtAddress.getText(),
                            txtContac.getText()
                    ));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully").show();
                clearTextField();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated Fail !").show();
            }

        }


        public void getdetaOnAction (ActionEvent actionEvent) throws Exception {
            CustomerDTO customer = bo.getCustomer(txtId.getText());
            if (null != customer) {
                txtName.setText(customer.getName());
                txtAddress.setText(customer.getAddress());
                txtContac.setText(customer.getContact());

            } else {
                System.out.println("no");
            }
        }


    public void clearTextField(){
        txtId.clear ();
        txtName.clear ();
        txtAddress.clear ();
        txtContac.clear ();
        txtId.requestFocus ();
    }
    }

