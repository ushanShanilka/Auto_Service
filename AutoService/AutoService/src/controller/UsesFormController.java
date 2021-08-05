package controller;

import bo.BoFactory;
import bo.custom.ItemBO;
import bo.custom.UsesBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.ItemDTO;
import dto.UsesDTO;
import dto.VehicleDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.UsesTM;


import javax.swing.*;
import java.util.List;
import java.util.Optional;

public class UsesFormController {
    public TableColumn colUseCode;
    public TableColumn colCode;
    public TableColumn colQty;
    public TableColumn colOperate;
    public JFXTextField txtUseCode;
    public JFXTextField txtCode;
    public JFXTextField txtQty;
    public JFXButton btnAdd;
    public TableView tblUses;
    public TableColumn colDiscription;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public JFXButton btnUpdate;
    public JFXTextField txtDiscription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtTotal;
    public JFXButton btnPlace;

    UsesBO bo = BoFactory.getInstance()
            .getBo(BoFactory.BOType.USES);
    ItemBO bo1 = BoFactory.getInstance().
            getBo(BoFactory.BOType.ITEM);

    public void initialize() throws Exception {

        colUseCode.setCellValueFactory(new PropertyValueFactory("UseCode"));
        colCode.setCellValueFactory(new PropertyValueFactory("Code"));
        colDiscription.setCellValueFactory(new PropertyValueFactory("Discription"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("UnitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory("Qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory("Total"));
        colOperate.setCellValueFactory(new PropertyValueFactory("btn"));



        loadAllUses();



        tblUses.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData((UsesTM) newValue);
                });

    }

    public void SaveOnAction(ActionEvent actionEvent) throws Exception {
        try {
            boolean isSaved = bo.saveUses(new UsesDTO(txtUseCode.getText(),txtCode.getText(),
                                                      txtDiscription.getText(),txtUnitPrice.getText(),
                                                      txtQty.getText (),txtTotal.getText())
            );
            if ( isSaved ){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Uses is Saved");
                alert.show();
                loadAllUses();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Uses Not Saved");
                alert.show();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void setData(UsesTM tm) {
        try {
            txtUseCode.setText(tm.getUseCode());
            txtCode.setText(tm.getCode());
            txtDiscription.setText(tm.getDiscription());
            txtUnitPrice.setText(tm.getUnitPrice());
            txtQty.setText(tm.getQty());
            txtTotal.setText(tm.getTotal());
        } catch (NullPointerException ex) {

        }
    }

    private void loadAllUses() throws Exception {
        ObservableList<UsesTM> tmList = FXCollections.observableArrayList();
        List<UsesDTO> allUses = bo.getAllUses();
        for (UsesDTO dto : allUses) {
            JFXButton btn = new JFXButton("Delete");
            UsesTM tm = new UsesTM(dto.getUseCode(), dto.getCode(),dto.getDiscription(),dto.getUnitPrice(), dto.getQty(),dto.getTotal(),btn);
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
                        if (bo.deleteUses(tm.getUseCode())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllUses();
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
        tblUses.setItems(tmList);

    }

    public void UpdateOnAction(ActionEvent actionEvent) throws Exception {
        boolean isUpdated = bo.UpdateUses(
                new UsesDTO(txtUseCode.getText(),
                        txtCode.getText(),
                        txtDiscription.getText(),
                        txtUnitPrice.getText(),
                        txtQty.getText(),
                        txtTotal.getText()
                ));
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully").show();
            loadAllUses();
            clearTextField();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION, "Updated Fail !").show();
        }
    }

    public void getItem(ActionEvent actionEvent) throws Exception {
        ItemDTO item = bo1.getItem(txtCode.getText());
        if(null!=item){
            txtDiscription.setText(item.getDescription());
            txtUnitPrice.setText(item.getUnitPrice());
        }else{
            System.out.println("no");

        }

    }

    public double getItemTotal(ActionEvent actionEvent) {
            double total;
            int qty = Integer.parseInt(txtQty.getText());
            double UnitPrice = Double.parseDouble(txtUnitPrice.getText());
            total = (UnitPrice*qty);
            txtTotal.setText(String.valueOf(total));
            return total;
        }

    public void btnPlace(ActionEvent actionEvent) throws Exception {
        boolean isSaved = bo.saveUses(new UsesDTO(txtUseCode.getText(),
                txtCode.getText(),
                txtDiscription.getText(),
                txtUnitPrice.getText(),
                txtQty.getText(),
                txtTotal.getText()
        ));
        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully").show();
            loadAllUses();
            clearTextField();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Fail !").show();
        }
    }

    public void clearTextField(){
        txtUseCode.clear ();
        txtUnitPrice.clear ();
        txtCode.clear ();
        txtDiscription.clear ();
        txtQty.clear ();
        txtTotal.clear ();
        txtUseCode.requestFocus ();
    }
}

