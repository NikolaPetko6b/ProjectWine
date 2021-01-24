package Scenes;

import Dao.NotificationsDao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tables.NotificationsEntity;

import java.awt.*;

public class WinNotifications {

    public void displayTray() throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("/Scenes/pnghut_wine-bottle-vector-art-water-bottles.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/Scenes/pnghut_wine-bottle-vector-art-water-bottles.png"));

        TrayIcon trayIcon = new TrayIcon(image, "ProjectWine");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("ProjectWine");
        tray.add(trayIcon);

        trayIcon.displayMessage("Welcome to our project :)", "ProjectWine", TrayIcon.MessageType.NONE);

    }


    public static void criticalQuantity(String stockName) throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().getImage("Scenes/wineicon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("wineicon.png"));
        TrayIcon trayIcon = new TrayIcon(image);
        //Set tooltip text for the tray icon
        tray.add(trayIcon);
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);




        trayIcon.displayMessage("Please Restock: "+stockName+"", "WineProject", TrayIcon.MessageType.WARNING);
    }



    public static void showNotification(String stockname)
    {
        java.util.Date date=new java.util.Date();
        NotificationsDao notificationsDao = new NotificationsDao();
        NotificationsEntity notificationsEntity = new NotificationsEntity();
        notificationsEntity.setDate(date);
        notificationsEntity.setMessage(stockname+" critical warning.Please restock!!");
        notificationsEntity.setNotificationId(notificationsDao.getMaxID()+1);
        notificationsEntity.setStatus("NEW");
        notificationsDao.AddNotification(notificationsEntity);
        Notifications notifications = Notifications.create()
                .title("Critcal Warning")
                .text(stockname+" critical warning.Please restock!!")
                .graphic(null)
                .hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {


                    }
                });
        notifications.showConfirm();

    }

    public static void WelcomeNotification()
    {
        java.util.Date date=new java.util.Date();
        NotificationsDao notificationsDao = new NotificationsDao();
        NotificationsEntity notificationsEntity = new NotificationsEntity();
        notificationsEntity.setDate(date);
        notificationsEntity.setMessage("Welcome to ProjectWine.");
        notificationsEntity.setNotificationId(notificationsDao.getMaxID()+1);
        notificationsEntity.setStatus("NEW");
        notificationsDao.AddNotification(notificationsEntity);


        Notifications notifications = Notifications.create()
                .title("ProjectWine")
                .text("Welcome to ProjectWine.")
                .graphic(null)
                .hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {


                    }
                });
        notifications.showConfirm();

    }



}
