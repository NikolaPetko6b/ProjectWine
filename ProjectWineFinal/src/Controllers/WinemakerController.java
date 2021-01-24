package Controllers;

import Dao.*;
import Scenes.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import Scenes.WinNotifications;
import tables.*;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class WinemakerController implements Initializable {
    static Logger logger = Logger.getLogger(Main.class.getName());

    @FXML
    public TextField RedGrapeYouNeed;

    @FXML
    public TextField WhiteGrapeYouNeed;


    @FXML
    public TextField LitersYouCanMake;


    @FXML
    public ListView winehistory;

    @FXML
    public DatePicker datefrom;

    @FXML
    public DatePicker dateto;


    @FXML
    public TextField WarehouseRedGrape;

    @FXML
    public TextField WarehouseWhiteGrape;

    @FXML
    public TextField Warehouse187Bottles;

    @FXML
    public TextField Warehouse200Bottles;

    @FXML
    public TextField  Warehouse375Bottles;

    @FXML
    public TextField  Warehouse750Bottles;

    @FXML
    public Label calculateLabel;
    @FXML
    public Label calculateLabel1;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {






        calculateLabel.setText("");
        calculateLabel1.setText("");
        CurrentStockDao currentStockDao = new CurrentStockDao();
        WineTypeDao wineTypeDao = new WineTypeDao();
        WinetypeEntity winetypeEntity = new WinetypeEntity();
        UsersEntity usersEntity = new UsersEntity();

        UsersDao usersDao = new UsersDao();

        try {
            WarehouseRedGrape.setText(String.valueOf(currentStockDao.curRedGrape()));
            WarehouseWhiteGrape.setText(String.valueOf(currentStockDao.curWhiteGrape()));
            Warehouse187Bottles.setText(String.valueOf(currentStockDao.cur187Bottles()));
            Warehouse200Bottles.setText(String.valueOf(currentStockDao.cur200Bottles()));
            Warehouse375Bottles.setText(String.valueOf(currentStockDao.cur375Bottles()));
            Warehouse750Bottles.setText(String.valueOf(currentStockDao.cur750Bottles()));


        }
        catch (Exception e)
        {

        }


        RedGrapeYouNeed.setText(String.valueOf(5.00));
        WhiteGrapeYouNeed.setText(String.valueOf(5.00));
        winetypeEntity = wineTypeDao.getWineTypeByUserID(usersDao.getLoggedid());
        WhiteGrapeYouNeed.setText(String.valueOf(winetypeEntity.getWhiteGrapeNeeded()));
        RedGrapeYouNeed.setText(String.valueOf(winetypeEntity.getRedGrapeNeeded()));
    }

    @FXML
    public void SwitchBottlesFill(javafx.event.ActionEvent event)throws IOException
    {
        try {
            CurrentStockDao currentStockDao = new CurrentStockDao();
            double liters = Double.parseDouble(LitersYouCanMake.getText());
            if(liters*Double.parseDouble(WhiteGrapeYouNeed.getText()) < currentStockDao.curWhiteGrape() &&
                    liters*Double.parseDouble(RedGrapeYouNeed.getText()) < currentStockDao.curRedGrape()) {
                calculateLabel.setText("");
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/Scenes/BottlesFill.fxml"));

                Parent root = (Parent) loader.load();
                BottlesFillController secController = (BottlesFillController) loader.getController();
                secController.liters.setText(LitersYouCanMake.getText());
                secController.tempLiters = Double.parseDouble(LitersYouCanMake.getText());
                secController.allLiters = Double.parseDouble(LitersYouCanMake.getText());
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
            else
            {
                calculateLabel1.setText("Not enough grape!!");
            }
        }
        catch (Exception e)
        {
            calculateLabel.setText("You have to calculate before making!!");
        }


        /*
        Parent BottleFill = FXMLLoader.load(getClass().getResource("BottlesFill.fxml"));
        Scene BottleFillS = new Scene(BottleFill);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(BottleFillS);
        window.show();
         */

    }


    @FXML
    public void BackOnAction(javafx.event.ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/Scenes/sample.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void  SeeHistoryOnAction(){

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            WineTypeDao wineTypeDao = new WineTypeDao();
            UsersEntity usersEntity = new UsersEntity();
            UsersDao usersDao = new UsersDao();
            WinetypeEntity winetypeEntity = new WinetypeEntity();

            winetypeEntity = wineTypeDao.getWineTypeByUserID(usersDao.getLoggedid());
            winehistory.getItems().clear();
            List<WineEntity> result1 = null;

            result1 = session.createQuery("from WineEntity  where madedate >= '" + datefrom.getValue() + "' and madedate <= '" + dateto.getValue() + "'").list();
            for (int i = 0; i < result1.size(); i++) {
                if(result1.get(i).getWinetypeId() == winetypeEntity.getWinetypeId()) {
                    winehistory.getItems().add("ID: " + result1.get(i).getWineId() + " Wine Name " + winetypeEntity.getWinename() +  " Size " + result1.get(i).getSize() +  " Quantity: " + result1.get(i).getWinequantity() + " SerialNum: " + result1.get(i).getSerialnum() +  " Date made: " + result1.get(i).getMadedate());
                }
            }
        }
        catch (Exception e)
        {

        }
    }







    public void CalculateOnAction()throws IOException
    {
        GrapeDao grapeDao = new GrapeDao();
        CurrentStockDao currentStockDao = new CurrentStockDao();
        double liters = 0.00;

        if(Double.parseDouble(WhiteGrapeYouNeed.getText()) < currentStockDao.curWhiteGrape() &&
                Double.parseDouble(RedGrapeYouNeed.getText()) < currentStockDao.curRedGrape())
        {

            double temp1 = currentStockDao.curWhiteGrape()/Double.parseDouble(WhiteGrapeYouNeed.getText());
            double temp2 = currentStockDao.curRedGrape()/Double.parseDouble(RedGrapeYouNeed.getText());
            if(temp1 < temp2) liters = temp1-10;
            else liters = temp2-10;




        }




        LitersYouCanMake.setText(String.valueOf(Math.round(liters)));
    }

    public void RefreshWarehouseStock() throws IOException, AWTException {
        CurrentStockDao currentStockDao = new CurrentStockDao();
        WarehouseRedGrape.setText(String.valueOf(currentStockDao.curRedGrape()));
        WarehouseWhiteGrape.setText(String.valueOf(currentStockDao.curWhiteGrape()));
        Warehouse187Bottles.setText(String.valueOf(currentStockDao.cur187Bottles()));
        Warehouse200Bottles.setText(String.valueOf(currentStockDao.cur200Bottles()));
        Warehouse375Bottles.setText(String.valueOf(currentStockDao.cur375Bottles()));
        Warehouse750Bottles.setText(String.valueOf(currentStockDao.cur750Bottles()));



        if(currentStockDao.curWhiteGrape()<20){
            WinNotifications.showNotification("White Grape");
        }
        if(currentStockDao.curRedGrape()<20){
            WinNotifications.showNotification("Red Grape");
        }
        if(currentStockDao.cur187Bottles()<20){
            WinNotifications.showNotification("Bottle 187ml");
        }
        if(currentStockDao.cur200Bottles()<20){
            WinNotifications.showNotification("Bottle 200ml");
        }
        if(currentStockDao.cur375Bottles()<20){
            WinNotifications.showNotification("Bottle 375ml");
        }
        if(currentStockDao.cur750Bottles()<20){
            WinNotifications.showNotification("Bottle 750ml");
        }

    }





    public void MakeOnAction() throws IOException, AWTException {
        CurrentStockEntity currentStockEntity = new CurrentStockEntity();
        CurrentStockDao currentStockDao = new CurrentStockDao();
        WinetypeEntity winetypeEntity = new WinetypeEntity();
        WineTypeDao wineTypeDao = new WineTypeDao();
        WineDao wineDao= new WineDao();
        WineEntity wineEntity = new WineEntity();
        GrapeDao grapeDao = new GrapeDao();
        UsersDao usersDao = new UsersDao();
        java.util.Date date=new java.util.Date();

        currentStockEntity.setWarehouseId(1);
        currentStockEntity.setCurDate(date);
        currentStockEntity.setCurrentstockid(currentStockDao.getLatestRecordID()+1);

        wineEntity.setWinetypeId(wineTypeDao.getWineTypeByUserID(usersDao.getLoggedid()).getWinetypeId());
        wineEntity.setWarehouseId(1);
        wineEntity.setMadedate(date);

        wineEntity.setSerialnum(String.valueOf(wineDao.getMaxID()+1));

        if(Double.parseDouble(WhiteGrapeYouNeed.getText()) < Double.parseDouble(WarehouseWhiteGrape.getText()) &&
                Double.parseDouble(RedGrapeYouNeed.getText()) < Double.parseDouble(WarehouseRedGrape.getText()))
        {
            if(Double.parseDouble(WhiteGrapeYouNeed.getText())!=0) {

                currentStockEntity.setWhiteGrape(currentStockDao.curWhiteGrape()-(Double.parseDouble(LitersYouCanMake.getText())
                        *Double.parseDouble(WhiteGrapeYouNeed.getText())));

            }
            else
            {
                currentStockEntity.setWhiteGrape(currentStockDao.curWhiteGrape());
            }

            if(Double.parseDouble(RedGrapeYouNeed.getText())!=0) {

                currentStockEntity.setRedGrape(currentStockDao.curRedGrape()-(Double.parseDouble(LitersYouCanMake.getText())
                        *Double.parseDouble(RedGrapeYouNeed.getText())));
            }
            else
            {
                currentStockEntity.setRedGrape(currentStockDao.curRedGrape());
            }

            Double liters = Double.parseDouble(LitersYouCanMake.getText());




            if((int)(liters/0.750) != 0) {
                currentStockEntity.setBottles750(currentStockDao.cur750Bottles()-(int) (liters / 0.750));
                wineEntity.setSize(750.0);
                wineEntity.setWinequantity((int)(liters/0.750));
                wineEntity.setWineId(wineDao.getMaxID()+1);
                wineDao.AddWine(wineEntity);

                liters = (liters % 0.75);

            }
            else
            {
                currentStockEntity.setBottles750(currentStockDao.cur750Bottles());
                liters = (liters % 0.750);
            }

            if((int)(liters/0.375) != 0) {
                currentStockEntity.setBottles375(currentStockDao.cur375Bottles()-(int) (liters / 0.375));

                wineEntity.setSize(375.0);
                wineEntity.setWinequantity((int)(liters/0.375));
                wineEntity.setWineId(wineDao.getMaxID()+1);
                wineDao.AddWine(wineEntity);

                liters = (liters % 0.375);
            }
            else
            {
                currentStockEntity.setBottles375(currentStockDao.cur375Bottles());
                liters = (liters % 0.375);
            }




            if((int)(liters/0.200) != 0) {
                currentStockEntity.setBottles200(currentStockDao.cur200Bottles()-(int) (liters / 0.200));

                wineEntity.setSize(200.0);
                wineEntity.setWinequantity((int)(liters/0.200));
                wineEntity.setWineId(wineDao.getMaxID()+1);
                wineDao.AddWine(wineEntity);

                liters = (liters % 0.200);
            }
            else
            {
                currentStockEntity.setBottles200(currentStockDao.cur200Bottles());
                liters = (liters % 0.200);
            }

            if((int)(liters/0.187) != 0) {
                currentStockEntity.setBottles187(currentStockDao.cur187Bottles()-(int) (liters / 0.187));

                wineEntity.setSize(187.0);
                wineEntity.setWinequantity((int)(liters/0.187));
                wineEntity.setWineId(wineDao.getMaxID()+1);
                wineDao.AddWine(wineEntity);

                liters = (liters % 0.187);
            }
            else
            {
                currentStockEntity.setBottles187(currentStockDao.cur187Bottles());
                liters = (liters % 0.187);
            }

            currentStockDao.AddRecord(currentStockEntity);

            RefreshWarehouseStock();


        }




    }




}
