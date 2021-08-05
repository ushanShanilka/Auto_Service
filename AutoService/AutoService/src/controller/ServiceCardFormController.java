package controller;

import bo.BoFactory;
import bo.custom.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.*;
import entity.ServiceCard;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import view.tm.ServiceCardTM;
import view.tm.UsesTM;
import view.tm.VehicleTM;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ServiceCardFormController {
    public JFXTextField txtSID;
    public JFXTextField txtDate;
    public JFXTextField txtTime;
    public JFXComboBox cmdCID;
    public JFXComboBox cmdVnum;
    public JFXTextField txtCID;
    public JFXTextField txtVnum;
    public JFXButton btnAdd;
    public TableView tblServicecard;
    public TableColumn colCID;
    public TableColumn colVnum;
    public TableColumn colOperate;
    public JFXComboBox cmdCode;
    public JFXTextField txtCode;
    public JFXComboBox cmdTypeId;
    public JFXTextField txtTypeId;
    public TableColumn colScardID;
    public TableColumn coldate;
    public TableColumn coltime;
    public TableColumn colCode;
    public TableColumn colTypeID;
    public TableColumn colUsesCode;
    public JFXComboBox cmdUseCode;
    public JFXTextField txtUseCode;


    ServiceCardBO bo = BoFactory.getInstance()
            .getBo(BoFactory.BOType.SERVICECARD);
    UsesBO bo1 = BoFactory.getInstance()
            .getBo(BoFactory.BOType.USES);
    ServiceTypeBO bo2 = BoFactory.getInstance()
            .getBo(BoFactory.BOType.SERVICETYPE);
    VehicleBO bo3 = BoFactory.getInstance()
            .getBo(BoFactory.BOType.VEHICLE);


    private ServiceCardBO serviceCardBO = BoFactory.getInstance().getBo(BoFactory.BOType.SERVICECARD);
    private CustomerBO customerBO = BoFactory.getInstance().getBo(BoFactory.BOType.CUSTOMER);
    private VehicleBO vehicleBO = BoFactory.getInstance().getBo(BoFactory.BOType.VEHICLE);
    private ItemBO itemBO = BoFactory.getInstance().getBo(BoFactory.BOType.ITEM);
    private UsesBO usesBO = BoFactory.getInstance().getBo(BoFactory.BOType.USES);
    private ServiceTypeBO serviceTypeBO= BoFactory.getInstance().getBo(BoFactory.BOType.SERVICETYPE);



    public void initialize() throws Exception {
        LocalDate today = LocalDate.now();
        txtDate.setText(today.toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        txtTime.setText(LocalDateTime.now().format(formatter));
        

        getLastServiceCardId();
        addCustomerValues();
        addVehicleValues();
        getUsesCode();
        getTypeId();

        colScardID.setCellValueFactory(new PropertyValueFactory("ScardID"));
        coldate.setCellValueFactory(new PropertyValueFactory("date"));
        colCID.setCellValueFactory(new PropertyValueFactory("CID"));
        colVnum.setCellValueFactory(new PropertyValueFactory("Vnum"));
        colUsesCode.setCellValueFactory(new PropertyValueFactory("UsesCode"));
        colTypeID.setCellValueFactory(new PropertyValueFactory("TypeID"));
        colOperate.setCellValueFactory(new PropertyValueFactory("btn"));

        loadAllServiceCard();


        tblServicecard.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData((ServiceCardTM) newValue);
                });

    }

    private void setData(ServiceCardTM tm) {
        try {
            txtSID.setText(tm.getScardID());
            txtDate.setText ( tm.getDate ());
            txtCID.setText(tm.getCID());
            txtVnum.setText(tm.getVnum());
            txtUseCode.setText(tm.getUsesCode());
            txtTypeId.setText(tm.getTypeID());
        } catch (NullPointerException ex) {

        }
    }

    private void loadAllServiceCard() throws Exception {
        ObservableList<ServiceCardTM> tmList = FXCollections.observableArrayList();
        List<ServiceCardDTO> allServiceCard = bo.getAllServiceCard();
        for (ServiceCardDTO dto : allServiceCard) {
            JFXButton btn = new JFXButton("Delete");
            ServiceCardTM tm = new ServiceCardTM(dto.getScardID(),dto.getDate (),dto.getCID(),dto.getVnum(),dto.getUsesCode(),dto.getTypeID(),btn);
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
                        if (bo.deleteServiceCrad(tm.getScardID())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllServiceCard();
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
        tblServicecard.setItems(tmList);
        System.out.println (tmList );

    }
        private void getLastServiceCardId () {
            try {
                txtSID.setText(serviceCardBO.getNewServiceCardId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public void cmIdOnAction (ActionEvent actionEvent){
            try {
                CustomerDTO customerDTO = customerBO.getCustomer(cmdCID.getSelectionModel().getSelectedItem().toString());

                if (customerDTO != null) {
                    txtCID.setText(customerDTO.getCID());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void addCustomerValues () throws Exception {
            cmdCID.getItems().clear();
            ArrayList<CustomerDTO> customerDTOArrayList = customerBO.getAllCustomers();
            for (CustomerDTO id : customerDTOArrayList) {
                cmdCID.getItems().add(id.getCID());
            }

        }

        public void addVehicleValues () throws Exception {
            cmdVnum.getItems().clear();
            ArrayList<VehicleDTO> vehicleDTOArrayList = vehicleBO.getAllVehicle();
            for (VehicleDTO vnum : vehicleDTOArrayList) {
                cmdVnum.getItems().add(vnum.getVnum());
            }
        }

        public void getTypeId(){
            try{
            ArrayList<ServiceTypeDTO>arrayList=bo2.getAllServiceType();
            for(ServiceTypeDTO t:arrayList){
                cmdTypeId.getItems().addAll(t.getTypeId());
            }

            }catch (Exception e){
            e.printStackTrace();
             }
        }
        public void getUsesCode(){
            try{
                ArrayList<UsesDTO>arrayList=bo1.getAllUses();
                for(UsesDTO t:arrayList){
                    cmdUseCode.getItems().addAll(t.getUseCode());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public void SaveOnAction (ActionEvent actionEvent) throws Exception {
            boolean isSaved = bo.saveServiceCard(new ServiceCardDTO(
                    txtSID.getText(),
                    txtDate.getText (),
                    txtCID.getText(),
                    txtVnum.getText(),
                    txtUseCode.getText(),
                    txtTypeId.getText()
            ));
            if (isSaved) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Success!");
                alert.show();
                loadAllServiceCard ();
                clearTextField();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Saved Fail!");
                alert.show();
            }
        }

    public void cmVnumOnAction(ActionEvent actionEvent) throws Exception {
        List<VehicleDTO> allVehicles = bo3.getAllVehicle();
        for (VehicleDTO allvehicle : allVehicles){
            if(cmdVnum.getValue().equals(allvehicle.getVnum())){
                txtVnum.setText(allvehicle.getVnum());
                return;
            }

        }
    }


    public void getTypeID(ActionEvent actionEvent) throws Exception {
        List<ServiceTypeDTO> allTypeIds = bo2.getAllServiceType();
        for (ServiceTypeDTO allTypeId : allTypeIds){
            if(cmdTypeId.getValue().equals(allTypeId.getTypeId())){
                txtTypeId.setText(allTypeId.getTypeId());
                return;
            }

        }
    }

    public void getUsesCodeOnAction(ActionEvent actionEvent) throws Exception {
        List<UsesDTO> allUsesCodes = bo1.getAllUses();
        for (UsesDTO allUsesCode : allUsesCodes){
            if(cmdUseCode.getValue().equals(allUsesCode.getUseCode())){
                txtUseCode.setText(allUsesCode.getUseCode());
                return;
            }
        }
    }

    public void clearTextField(){
        txtSID.clear ();
        txtSID.clear ();
        txtCID.clear ();
        txtVnum.clear ();
        txtUseCode.clear ();
        txtTypeId.clear ();
        txtSID.requestFocus ();
    }

}



