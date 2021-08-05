package controller;

import bo.BoFactory;
import bo.custom.ServiceTypeBO;
import bo.custom.VehicleBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import dto.ServiceTypeDTO;
import dto.VehicleDTO;
import entity.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.ServiceTypeTM;
import view.tm.VehicleTM;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public class ServiceTypeFormController {
    public JFXTextField txtTypeId;
    public JFXTextField txtdetails;
    public JFXTextField txtName;
    public TableView tblServiceType;
    public TableColumn colTypeID;
    public TableColumn colName;
    public TableColumn colDetails;
    public TableColumn colOprerate;
    public JFXButton btnAdd;
    public TableColumn colOperate;

    ServiceTypeBO bo = BoFactory.getInstance()
            .getBo(BoFactory.BOType.SERVICETYPE);

    public void saveOnAction(ActionEvent actionEvent) throws Exception {
        boolean isSaved = bo.saveServiceType(new ServiceTypeDTO(txtTypeId.getText(),
                txtName.getText(), txtdetails.getText())
        );
        if (isSaved) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Success!");
            alert.show();
            loadAllServiceType ();
            clearTextField();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Saved Fail!");
            alert.show();
        }


    }

    public void initialize() throws Exception {

        colTypeID.setCellValueFactory(new PropertyValueFactory("TypeId"));
        colName.setCellValueFactory(new PropertyValueFactory("Name"));
        colDetails.setCellValueFactory(new PropertyValueFactory("Details"));
        colOperate.setCellValueFactory(new PropertyValueFactory("btn"));

        loadAllServiceType();


        tblServiceType.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData((ServiceTypeTM) newValue);
                });

    }


    private void setData(ServiceTypeTM tm) {
        try {
            txtTypeId.setText(tm.getTypeId());
            txtName.setText(tm.getName());
            txtdetails.setText(tm.getDetails());
        } catch (NullPointerException ex) {

        }
    }

    private void loadAllServiceType() throws Exception {
        ObservableList<ServiceTypeTM> tmList = FXCollections.observableArrayList();
        List<ServiceTypeDTO> allServiceType = bo.getAllServiceType();
        for (ServiceTypeDTO dto : allServiceType) {
            JFXButton btn = new JFXButton("Delete");
            ServiceTypeTM tm = new ServiceTypeTM(dto.getTypeId(), dto.getName(), dto.getDetails(), btn);
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
                        if (bo.deleteServiceType(tm.getTypeId())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllServiceType();
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
        tblServiceType.setItems(tmList);
    }

    public void getDetaOnAction(ActionEvent actionEvent) throws Exception {
        ServiceTypeDTO  serviceType = bo.getServiceType(txtTypeId.getText());
        if(null!=serviceType){
            txtName.setText(serviceType.getName());
            txtdetails.setText(serviceType.getDetails());


        }else{
            System.out.println("no");

        }
    }

    public void UpdateOnAction(ActionEvent actionEvent) throws Exception {
        boolean isUpdated = bo.UpdateServiceType(
                new ServiceTypeDTO(txtTypeId.getText(),
                        txtName.getText(),
                        txtdetails.getText()
                ));
        if (isUpdated) {
            loadAllServiceType();
            new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION, "Updated Fail !").show();
        }
        System.out.println(isUpdated);
    }

    public void clearTextField(){
        txtTypeId.clear ();
        txtdetails.clear ();
        txtName.clear ();
        txtTypeId.requestFocus ();
    }
}

