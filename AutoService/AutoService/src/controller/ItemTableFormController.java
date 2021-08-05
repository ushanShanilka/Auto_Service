package controller;

import bo.BoFactory;
import bo.custom.ItemBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.ItemDTO;
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
import view.tm.ItemTM;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class ItemTableFormController {
    public AnchorPane root1;
    public JFXTextField txtcode;
    public JFXTextField txtdescription;
    public JFXTextField txtqty;
    public JFXTextField txtuniteprice;
    public TableView <ItemTM>itemtable;
    public JFXButton btnAdd;
    public TableColumn colItemDescription;
    public TableColumn colItemQty;
    public TableColumn colItemUnitPrice;
    public TableColumn colItemOperate;
    public TableColumn colItemCode;


    ItemBO bo = BoFactory.getInstance()
            .getBo(BoFactory.BOType.ITEM);


    public void setUI(String location) throws IOException {
        Stage stage = (Stage) root1.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/" + location))));
    }

    public void SaveOnActoin(ActionEvent actionEvent) throws Exception {
        boolean isSaved = bo.saveItem(new ItemDTO(txtcode.getText(),
                txtdescription.getText(),
                txtuniteprice.getText(),
                txtqty.getText()

                )
        );
        if (isSaved) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved Success!");
            alert.show();
            loadAllItems ();
            clearTextField();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Saved Fail!");
            alert.show();
        }
    }
    public void initialize() throws Exception {

        loadCode();

        colItemCode.setCellValueFactory(new PropertyValueFactory("code"));
        colItemDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory("Unitprice"));
        colItemQty.setCellValueFactory(new PropertyValueFactory("QtyOnHand"));
        colItemOperate.setCellValueFactory(new PropertyValueFactory("btn"));

        loadAllItems();

        itemtable.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData(newValue);
                });

    }

    private void loadCode() throws Exception {
        String Code = bo.getCode();
        txtcode.setText(Code);
    }

    private void setData(ItemTM tm) {
            txtcode.setText(tm.getCode());
            txtdescription.setText(tm.getDescription());
            txtuniteprice.setText(tm.getUnitprice());
            txtqty.setText(tm.getQtyOnHand());
    }

    private void loadAllItems() throws Exception {
        ObservableList<ItemTM> tmList = FXCollections.observableArrayList();
        List<ItemDTO> allItems = bo.getAllItem();
        for (ItemDTO dto : allItems) {
            JFXButton btn = new JFXButton("Delete");
            ItemTM tm = new ItemTM(dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand(),btn);
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
                        if (bo.deleteItem(tm.getCode())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllItems();
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
        itemtable.setItems(tmList);
    }

    public void getItem(ActionEvent actionEvent) throws Exception {
        ItemDTO item = bo.getItem(txtcode.getText());
        if(null!=item){
            txtdescription.setText(item.getDescription());
            txtuniteprice.setText(item.getUnitPrice());
            txtqty.setText(item.getQtyOnHand());
        }else{
            System.out.println("no");
        }
    }

    public void clearTextField(){
        txtcode.clear ();
        txtdescription.clear ();
        txtqty.clear ();
        txtuniteprice.clear ();
        txtcode.requestFocus ();
    }
}
