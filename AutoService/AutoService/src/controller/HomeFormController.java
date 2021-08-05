package controller;

import bo.BoFactory;
import bo.custom.HomeBO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomeFormController {
    public AnchorPane root1;
    public Label lblDate;
    public Label lblTime;
    public Label lblTotVehicle;
    public Label lblTotCustomer;
    HomeBO bo;

    public void initialize() throws Exception {

        bo =BoFactory.getInstance().getBo(BoFactory.BOType.DEFAULT);

        generateDateTime();
        getTotVehicle();
        getTotCustomer();


    }
    public void generateDateTime() {
        lblDate.setText(LocalDate.now().toString());

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    public void getTotVehicle() throws Exception {
        lblTotVehicle.setText(String.valueOf(bo.getTotalVehicle()));
    }
    public void getTotCustomer()throws Exception{
        lblTotCustomer.setText(String.valueOf(bo.getTotCustomer()));
    }

}
