package controller;

import bo.BoFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBO;
import bo.custom.VehicleBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.VehicleDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.CustomerTM;
import view.tm.VehicleTM;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class VehicalFormController {
    public AnchorPane root;
    public JFXTextField txtVnum;
    public JFXTextField txtModel;
    public JFXTextField txtYear;
    public JFXTextField txtMileages;
    public TableColumn colModel;
    public TableColumn colYear;
    public TableColumn colVnum;
    public TableColumn colOperate;
    public JFXButton btnAddVehicle;
    public TableView Vehicletble;
    public TableColumn colMileage;
    public JFXButton btnUpdate;


    VehicleBO bo = BoFactory.getInstance()
            .getBo(BoFactory.BOType.VEHICLE);


    public void saveOnAction(ActionEvent actionEvent) throws Exception {
        boolean isSaved = bo.saveVehicle(new VehicleDTO(txtVnum.getText(),
                txtModel.getText(),txtMileages.getText(),txtYear.getText())
        );
        if (isSaved) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Success!");
            alert.show();
            loadAllVehicle ();
            clearTextField();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Saved Fail!");
            alert.show();
        }
    }

    public void initialize() throws Exception {

       // loadVnum();



        colVnum.setCellValueFactory(new PropertyValueFactory("Vnum"));
        colModel.setCellValueFactory(new PropertyValueFactory("Model"));
        colMileage.setCellValueFactory(new PropertyValueFactory("Milage"));
        colYear.setCellValueFactory(new PropertyValueFactory("YEAR"));
        colOperate.setCellValueFactory(new PropertyValueFactory("btn"));

        loadAllVehicle();



        Vehicletble.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData((VehicleTM) newValue);
                });

    }

    private void setData(VehicleTM tm) {
        try {
            txtVnum.setText(tm.getVnum());
           txtModel.setText(tm.getModel());
            txtMileages.setText(tm.getMilage());
            txtYear.setText(tm.getYEAR());
        } catch (NullPointerException ex) {

        }
    }

    private void loadAllVehicle() throws Exception {
        ObservableList<VehicleTM> tmList = FXCollections.observableArrayList();
        List<VehicleDTO> allVehicle = bo.getAllVehicle();
        for (VehicleDTO dto : allVehicle) {
            JFXButton btn = new JFXButton("Delete");
            VehicleTM tm = new VehicleTM(dto.getVnum(), dto.getModel(), dto.getMilage(), dto.getYEAR(), btn);
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
                        if (bo.deleteVehicle(tm.getVnum())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                           loadAllVehicle();
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
        Vehicletble.setItems(tmList);

    }
    public void updateOnAction(ActionEvent actionEvent) throws Exception {

        boolean isUpdated = bo.UpdateVehicle(
                new VehicleDTO(txtVnum.getText(),
                        txtModel.getText(),
                        txtMileages.getText(),
                        txtYear.getText()
                ));
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully").show();
            loadAllVehicle();
            clearTextField();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION, "Updated Fail !").show();
        }

    }


    public void getVehicle(ActionEvent actionEvent) throws Exception {
        VehicleDTO vehicle = bo.getVehicle(txtVnum.getText());
        if(null!=vehicle){
            txtModel.setText(vehicle.getModel());
            txtYear.setText(vehicle.getYEAR());
            txtMileages.setText(vehicle.getMilage());

        }else{
            System.out.println("no");

        }
    }

    public void clearTextField(){
        txtModel.clear ();
        txtVnum.clear ();
        txtMileages.clear ();
        txtYear.clear ();
    }
}
