package controller;

import bo.BoFactory;
import bo.custom.PaymentBO;
import bo.custom.ServiceCardBO;
import bo.custom.UsesBO;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import view.tm.CustomerTM;
import view.tm.PaymentTM;
import view.tm.ServiceCardTM;


import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PayementFormController {
    public AnchorPane root;
    public JFXTextField txtSID;
    public JFXTextField txtVnum;
    public JFXTextField txtUseCode;
    public JFXTextField txtCID;
    public JFXButton btnAdd;
    public JFXButton btnRemove;
    public JFXTextField txtPID;
    public Label lblTime;
    public Label lblDate;
    public JFXTextField txtTotal;
    public JFXComboBox cmbServiceCard;
    public TableView tblPayment;
    public TableColumn colScardId;
    public TableColumn colCid;
    public TableColumn colVnum;
    public TableColumn colUseCode;
    public TableColumn colTotal;

    PaymentBO bo = BoFactory.getInstance ( )
            .getBo ( BoFactory.BOType.PAYMENT );
    ServiceCardBO bo2 = BoFactory.getInstance ( )
            .getBo ( BoFactory.BOType.SERVICECARD );
    UsesBO bo3 = BoFactory.getInstance ( )
            .getBo ( BoFactory.BOType.USES );

    public void initialize ( ) throws Exception {

        generateDateTime ( );
        getServiceCard();

        loadAllPayment();

        colScardId.setCellValueFactory(new PropertyValueFactory ( "ScardID"));
        colCid.setCellValueFactory(new PropertyValueFactory("CID"));
        colVnum.setCellValueFactory(new PropertyValueFactory("Vnum"));
        colUseCode.setCellValueFactory(new PropertyValueFactory("UseCode"));
        colTotal.setCellValueFactory(new PropertyValueFactory("Total"));

        tblPayment.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData( (PaymentTM) newValue );
                });
    }


    private void setData ( PaymentTM tm){
        try {
            txtSID.setText(tm.getScardID ());
            txtCID.setText(tm.getCID ());
            txtVnum.setText(tm.getVnum ());
            txtUseCode.setText(tm.getUseCode ());
            txtTotal.setText(tm.getTotal ());
        } catch (NullPointerException ex) {

        }
    }

    private void loadAllPayment () throws Exception {

        ObservableList<PaymentTM> tmList = FXCollections.observableArrayList();
        List<PaymentDTO> allPayment = bo.getAllpayment ();
        for (PaymentDTO dto : allPayment) {
            PaymentTM tm = new PaymentTM (dto.getPID (), dto.getScardID (), dto.getCID (), dto.getVnum (), dto.getUseCode (),dto.getTotal ());
            tmList.add(tm);
        }
        tblPayment.setItems(tmList);

    }

    public void generateDateTime ( ) {
        lblDate.setText ( LocalDate.now ( ).toString ( ) );
        Timeline timeline = new Timeline ( new KeyFrame ( Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "hh:mm:ss a" );
            lblTime.setText ( LocalDateTime.now ( ).format ( formatter ) );
        } ), new KeyFrame ( Duration.seconds ( 1 ) ) );
        timeline.setCycleCount ( Animation.INDEFINITE );
        timeline.play ( );
    }

    public void SaveDetaOnAction ( ActionEvent actionEvent ) throws Exception {
        boolean isSaved = bo.savePayment ( new PaymentDTO (
                txtPID.getText ( ),
                txtSID.getText ( ),
                txtCID.getText ( ),
                txtVnum.getText ( ),
                txtUseCode.getText ( ),
                txtTotal.getText ( )
        ) );
        if (isSaved) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Payment Success");
            alert.show();
            loadAllPayment ();
            clearTextField();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Payment Fail");
            alert.show();
        }
    }

    public void getUses ( ActionEvent actionEvent ) throws Exception {
        UsesDTO uses = bo3.getUses ( txtUseCode.getText ( ) );
        if ( null != uses ) {
            txtTotal.setText ( uses.getTotal ( ) );
        }
        else {
            System.out.println ( "no" );
        }
    }


    public void getServiceCard() throws Exception {
        cmbServiceCard.getItems ( ).clear ( );
        ArrayList<ServiceCardDTO> serviceCardDTOArrayList = bo2.getAllServiceCard ( );
        for (ServiceCardDTO id : serviceCardDTOArrayList) {
            cmbServiceCard.getItems ( ).add ( id.getScardID ( ) );
        }
    }

    public void loadUseCode() throws Exception {
        UsesDTO usesDTO = bo3.getUses (txtUseCode.getText());
        System.out.println (usesDTO );
        if ( null != usesDTO ) {
            txtTotal.setText(usesDTO.getTotal ());
        } else {
            System.out.println("no");
        }
    }

    public void cmbServiceCardOnAction ( ActionEvent actionEvent ) throws Exception {
        List<ServiceCardDTO> allServiceCards = bo2.getAllServiceCard ();
        for (ServiceCardDTO allServiceCard : allServiceCards){
            if(cmbServiceCard.getValue().equals(allServiceCard.getScardID ())){
                txtSID.setText(allServiceCard.getScardID ());
                txtCID.setText ( allServiceCard.getCID () );
                txtVnum.setText ( allServiceCard.getVnum () );
                txtUseCode.setText ( allServiceCard.getUsesCode () );

            }
        }
        loadUseCode();
    }

    public void clearTextField(){
        txtPID.clear ();
        txtSID.clear ();
        txtCID.clear ();
        txtTotal.clear ();
        txtVnum.clear ();
        txtUseCode.clear ();
        txtPID.requestFocus ();
    }
}