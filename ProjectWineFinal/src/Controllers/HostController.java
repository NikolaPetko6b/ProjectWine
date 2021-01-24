package Controllers;

import Dao.*;
import Scenes.Main;
import com.mysql.cj.protocol.x.NoticeFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import Scenes.WinNotifications;
import javafx.util.Duration;
import org.apache.log4j.PropertyConfigurator;
import org.controlsfx.control.Notifications;
import org.hibernate.Session;
import tables.BottlesEntity;
import tables.CurrentStockEntity;
import tables.GrapeEntity;
import org.apache.log4j.Logger;
import tables.NotificationsEntity;

import javax.management.Notification;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class HostController implements Initializable {
    @FXML
    public TextField bottle1Field;

    @FXML
    public TextField bottle2Field;

    @FXML
    public TextField bottle3Field;

    @FXML
    public TextField bottle4Field;

    @FXML
    public TextField WhiteGrapeField;
    @FXML
    public TextField RedGrapeField;

    @FXML
    public CheckBox bottle1Box;
    @FXML
    public CheckBox bottle2Box;
    @FXML
    public CheckBox bottle3Box;
    @FXML
    public CheckBox bottle4Box;
    @FXML
    public CheckBox WhiteGrapeBox;
    @FXML
    public CheckBox RedGrapeBox;

    @FXML
    public TextField bottle1Quantity;
    @FXML
    public TextField bottle2Quantity;
    @FXML
    public TextField bottle3Quantity;
    @FXML
    public TextField bottle4Quantity;
    @FXML
    public TextField WhiteGrapeQuantity;
    @FXML
    public TextField RedGrapeQuantity;
    @FXML
    public Circle Nicon;
    @FXML
    public Label Ncounter;

    static Logger logger = Logger.getLogger(Main.class.getName());



    @FXML
    public Label criticalWhiteGr;
    private static final org.apache.log4j.Logger log = Logger.getLogger(HostController.class);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            CurrentStockDao currentStockDao = new CurrentStockDao();
            WhiteGrapeField.setText(String.valueOf(currentStockDao.curWhiteGrape()));
            RedGrapeField.setText(String.valueOf(currentStockDao.curRedGrape()));
            bottle1Field.setText(String.valueOf(currentStockDao.cur187Bottles()));
            bottle2Field.setText(String.valueOf(currentStockDao.cur200Bottles()));
            bottle3Field.setText(String.valueOf(currentStockDao.cur375Bottles()));
            bottle4Field.setText(String.valueOf(currentStockDao.cur750Bottles()));



            WinNotifications winNotifications = new WinNotifications();
            if(currentStockDao.curWhiteGrape()<20){

                winNotifications.showNotification("White Grape");
            }
            if(currentStockDao.curRedGrape()<20){

                winNotifications.showNotification("Red Grape");
            }
            if(currentStockDao.cur187Bottles()<20){

                winNotifications.showNotification("Bottle 187ml");
            }
            if(currentStockDao.cur200Bottles()<20){

                winNotifications.showNotification("Bottle 200ml");
            }
            if(currentStockDao.cur375Bottles()<20){

                winNotifications.showNotification("Bottle 375ml");
            }
            if(currentStockDao.cur750Bottles()<20){
                winNotifications.showNotification("Bottle 750ml");
            }
        }
        catch (Exception e)
        {

        }


        NotificationsDao notificationsDao = new NotificationsDao();
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> list = session.createQuery("from NotificationsEntity n where status like 'NEW'").getResultList();
        session.close();

        if(list.size() > 0)
        {
            Ncounter.setText(String.valueOf(list.size()));
            Nicon.setVisible(true);
        }
        else
        {
            Nicon.setVisible(false);
        }



    }

    @FXML
    public void BackOnAction(javafx.event.ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/Scenes/sample.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }



    public void SeeOnAction(javafx.event.ActionEvent event) throws IOException {

        Parent Orders = FXMLLoader.load(getClass().getResource("/Scenes/OrderHistory.fxml"));
        Scene OrdersScene = new Scene(Orders);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(OrdersScene);
        window.show();


    }



    public void NotificationsOnAction(javafx.event.ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/Scenes/HostNotifications.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();

        Stage current;
        current=(Stage) Nicon.getScene().getWindow();
        current.hide();
        Parent HostView = FXMLLoader.load(getClass().getResource("/Scenes/Host.fxml"));
        Scene Host = new Scene(HostView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(Host);
        window.show();

    }

    public void MakeRequestOnAction() throws IOException
    {

        try
        {
            java.util.Date date=new java.util.Date();
            CurrentStockDao currentStockDao = new CurrentStockDao();
            CurrentStockEntity currentStockEntity = new CurrentStockEntity();
            currentStockEntity.setCurDate(date);
            currentStockEntity.setWarehouseId(1);
            CurrentStockEntity previous = currentStockDao.getRecordByID(currentStockDao.getLatestRecordID());
            currentStockEntity.setCurrentstockid(currentStockDao.getLatestRecordID()+1);

            if(WhiteGrapeBox.isSelected())
            {
                GrapeDao grapeDao = new GrapeDao();
                GrapeEntity grapeEntity = new GrapeEntity();
                grapeEntity.setGrapeId(grapeDao.getMaxID()+1);
                grapeEntity.setGrapetypeId(1);
                grapeEntity.setGrapequantity(Double.parseDouble(WhiteGrapeQuantity.getText()));
                grapeEntity.setDateoforder(date);
                grapeEntity.setWarehouseId(1);
                grapeDao.AddGrape(grapeEntity);
                currentStockEntity.setWhiteGrape((Double.parseDouble(WhiteGrapeQuantity.getText()))+previous.getWhiteGrape());
                PropertyConfigurator.configure("src/Scenes/log4j.properties");
                logger.info("Host ordered White grape");
            }
            else
            {
                currentStockEntity.setWhiteGrape(0.0+previous.getWhiteGrape());
            }

            if(RedGrapeBox.isSelected())
            {
                GrapeDao grapeDao = new GrapeDao();
                GrapeEntity grapeEntity = new GrapeEntity();
                grapeEntity.setGrapeId(grapeDao.getMaxID()+1);
                grapeEntity.setGrapetypeId(2);
                grapeEntity.setGrapequantity(Double.parseDouble(RedGrapeQuantity.getText()));
                grapeEntity.setDateoforder(date);
                grapeEntity.setWarehouseId(1);
                grapeDao.AddGrape(grapeEntity);
                currentStockEntity.setRedGrape((Double.parseDouble(RedGrapeQuantity.getText()))+previous.getRedGrape());
                PropertyConfigurator.configure("src/Scenes/log4j.properties");
                logger.info("Host ordered Red grape");
            }
            else
            {
                currentStockEntity.setRedGrape(0.0+ previous.getRedGrape());
            }

            if(bottle1Box.isSelected())
            {
                BottlesDao bottlesDao = new BottlesDao();
                BottlesEntity bottlesEntity = new BottlesEntity();
                bottlesEntity.setBottlequantity(Integer.parseInt(bottle1Quantity.getText()));
                bottlesEntity.setBottlesId(bottlesDao.getBottlesMaxID()+1);
                bottlesEntity.setBottletypeId(1);
                bottlesEntity.setDateoforderB(date);
                bottlesEntity.setWarehouseId(1);
                bottlesDao.AddBottle(bottlesEntity);
                currentStockEntity.setBottles187((Integer.parseInt(bottle1Quantity.getText())+previous.getBottles187()));
                PropertyConfigurator.configure("src/Scenes/log4j.properties");
                logger.info("Host ordered 180ml bottles");
            }
            else
            {
                currentStockEntity.setBottles187(0+previous.getBottles187());
            }

            if(bottle2Box.isSelected())
            {
                BottlesDao bottlesDao = new BottlesDao();
                BottlesEntity bottlesEntity = new BottlesEntity();
                bottlesEntity.setBottlequantity(Integer.parseInt(bottle2Quantity.getText()));
                bottlesEntity.setBottlesId(bottlesDao.getBottlesMaxID()+1);
                bottlesEntity.setBottletypeId(2);
                bottlesEntity.setDateoforderB(date);
                bottlesEntity.setWarehouseId(1);
                bottlesDao.AddBottle(bottlesEntity);
                currentStockEntity.setBottles200((Integer.parseInt(bottle2Quantity.getText())+previous.getBottles200()));
                PropertyConfigurator.configure("src/Scenes/log4j.properties");
                logger.info("Host ordered 200ml bottles");


            }
            else
            {
                currentStockEntity.setBottles200(0+ previous.getBottles200());
            }

            if(bottle3Box.isSelected())
            {
                BottlesDao bottlesDao = new BottlesDao();
                BottlesEntity bottlesEntity = new BottlesEntity();
                bottlesEntity.setBottlequantity(Integer.parseInt(bottle3Quantity.getText()));
                bottlesEntity.setBottlesId(bottlesDao.getBottlesMaxID()+1);
                bottlesEntity.setBottletypeId(3);
                bottlesEntity.setDateoforderB(date);
                bottlesEntity.setWarehouseId(1);
                bottlesDao.AddBottle(bottlesEntity);
                currentStockEntity.setBottles375((Integer.parseInt(bottle3Quantity.getText())+previous.getBottles375()));
                PropertyConfigurator.configure("src/Scenes/log4j.properties");
                logger.info("Host ordered 375ml bottles");
            }
            else
            {
                currentStockEntity.setBottles375(0+previous.getBottles375());
            }

            if(bottle4Box.isSelected())
            {
                BottlesDao bottlesDao = new BottlesDao();
                BottlesEntity bottlesEntity = new BottlesEntity();
                bottlesEntity.setBottlequantity(Integer.parseInt(bottle4Quantity.getText()));
                bottlesEntity.setBottlesId(bottlesDao.getBottlesMaxID()+1);
                bottlesEntity.setBottletypeId(4);
                bottlesEntity.setDateoforderB(date);
                bottlesEntity.setWarehouseId(1);
                bottlesDao.AddBottle(bottlesEntity);
                currentStockEntity.setBottles750((Integer.parseInt(bottle4Quantity.getText())+previous.getBottles750()));
                PropertyConfigurator.configure("src/Scenes/log4j.properties");
                logger.info("Host ordered 750ml bottles");
            }
            else
            {
                currentStockEntity.setBottles750(0+ previous.getBottles750());
            }


            currentStockDao.AddRecord(currentStockEntity);
            WhiteGrapeField.setText(String.valueOf(currentStockDao.curWhiteGrape()));
            RedGrapeField.setText(String.valueOf(currentStockDao.curRedGrape()));
            bottle1Field.setText(String.valueOf(currentStockDao.cur187Bottles()));
            bottle2Field.setText(String.valueOf(currentStockDao.cur200Bottles()));
            bottle3Field.setText(String.valueOf(currentStockDao.cur375Bottles()));
            bottle4Field.setText(String.valueOf(currentStockDao.cur750Bottles()));



            if(currentStockDao.curWhiteGrape()<20){
                WinNotifications.showNotification("White Grape");
                Nicon.setVisible(true);
            }
            if(currentStockDao.curRedGrape()<20){
                WinNotifications.showNotification("Red Grape");
                Nicon.setVisible(true);
            }
            if(currentStockDao.cur187Bottles()<20){
                WinNotifications.showNotification("Bottle 187ml");
                Nicon.setVisible(true);
            }
            if(currentStockDao.cur200Bottles()<20){
                WinNotifications.showNotification("Bottle 200ml");
                Nicon.setVisible(true);
            }
            if(currentStockDao.cur375Bottles()<20){
                WinNotifications.showNotification("Bottle 375ml");
                Nicon.setVisible(true);
            }
            if(currentStockDao.cur750Bottles()<20){
                WinNotifications.showNotification("Bottle 750ml");
                Nicon.setVisible(true);
            }



        }
        catch(Exception e)
        {
            e.printStackTrace();
        }





    }




}
