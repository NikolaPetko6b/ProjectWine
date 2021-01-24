package Scenes;

import Dao.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import tables.*;

import java.awt.*;
import java.io.IOException;

public class Main extends Application {
    static Logger logger = Logger.getLogger(Scenes.Main.class.getName());

    @Override
    public void start(Stage primaryStage) throws Exception{


        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("ProjectWine");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("wineicon.png")));
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();

        WinNotifications winNotifications = new WinNotifications();
        winNotifications.WelcomeNotification();
        CurrentStockDao currentStockDao = new CurrentStockDao();




        BottleTypeDao bottleTypeDao = new BottleTypeDao();
        BottletypeEntity bottletypeEntity = new BottletypeEntity();
        if(bottleTypeDao.getBottleType().size()<1)
        {
            bottletypeEntity.setBottletypeId(1);
            bottletypeEntity.setSizeB("187");
            bottleTypeDao.AddBottleType(bottletypeEntity);

            bottletypeEntity.setBottletypeId(2);
            bottletypeEntity.setSizeB("200");
            bottleTypeDao.AddBottleType(bottletypeEntity);

            bottletypeEntity.setBottletypeId(3);
            bottletypeEntity.setSizeB("375");
            bottleTypeDao.AddBottleType(bottletypeEntity);

            bottletypeEntity.setBottletypeId(4);
            bottletypeEntity.setSizeB("750");
            bottleTypeDao.AddBottleType(bottletypeEntity);
        }

        GrapeTypeDao grapeTypeDao = new GrapeTypeDao();
        GrapetypeEntity grapetypeEntity = new GrapetypeEntity();
        if(grapeTypeDao.getGrapeType().size() < 1)
        {
            grapetypeEntity.setGrapetypeId(1);
            grapetypeEntity.setType("White");
            grapeTypeDao.AddGrapeType(grapetypeEntity);
            grapetypeEntity.setGrapetypeId(2);
            grapetypeEntity.setType("Red");
            grapeTypeDao.AddGrapeType(grapetypeEntity);
        }

        PrivilegeTypeDao privilegeTypeDao = new PrivilegeTypeDao();
        PrivilegetypeEntity privilegetypeEntity = new PrivilegetypeEntity();
        if(privilegeTypeDao.getPrivilegeType().size() < 1)
        {
            privilegetypeEntity.setPrivilegeId(1);
            privilegetypeEntity.setPrivilege("admin");
            privilegeTypeDao.AddPrivilege(privilegetypeEntity);
            privilegetypeEntity.setPrivilegeId(2);
            privilegetypeEntity.setPrivilege("host");
            privilegeTypeDao.AddPrivilege(privilegetypeEntity);
            privilegetypeEntity.setPrivilegeId(3);
            privilegetypeEntity.setPrivilege("winemaker");
            privilegeTypeDao.AddPrivilege(privilegetypeEntity);
        }


        CurrentStockEntity currentStockEntity = new CurrentStockEntity();
        if(currentStockDao.getRecords().size() < 1)
        {
            currentStockEntity.setCurDate(null);
            currentStockEntity.setWarehouseId(1);
            currentStockEntity.setCurrentstockid(1);
            currentStockEntity.setRedGrape(0.0);
            currentStockEntity.setWhiteGrape(0.0);
            currentStockEntity.setBottles187(0);
            currentStockEntity.setBottles375(0);
            currentStockEntity.setBottles200(0);
            currentStockEntity.setBottles750(0);
            currentStockDao.AddRecord(currentStockEntity);
        }

        WarehouseEntity warehouseEntity = new WarehouseEntity();
        WarehouseDao warehouseDao = new WarehouseDao();
        if(warehouseDao.getWarehouses().size() < 1)
        {
            warehouseEntity.setWarehouseId(1);
            warehouseEntity.setWarehouseLocation("Bulgaria");
            warehouseEntity.setWarehouseN("Sklad1");
            warehouseDao.AddWarehouse(warehouseEntity);
        }

        UsersDao usersDao = new UsersDao();
        UsersEntity usersEntity  = new UsersEntity();
        if(usersDao.getUsers().size() < 1)
        {
            usersEntity.setUsername("admin");
            usersEntity.setPassword(AES.encrypt("admin","Secret"));
            usersEntity.setPrivilegeId(1);
            usersEntity.setUserId(1);
            usersEntity.setWarehouseId(1);
            usersDao.AddUser(usersEntity);
        }



    }





    public static void main(String[] args) throws IOException, AWTException {

        launch(args);

    }
}



