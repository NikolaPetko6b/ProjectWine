package tables;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "notifications", schema = "projectwinedb", catalog = "")
public class NotificationsEntity {
    private int notificationId;
    private String message;
    public String status;
    private java.util.Date date;

    @Id
    @Column(name = "notification_id")
    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    @Basic
    @Column(name = "message")
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }



    @Basic
    @Column(name = "status")
    public String getStatus(){return status;}

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationsEntity that = (NotificationsEntity) o;
        return notificationId == that.notificationId &&
                Objects.equals(message, that.message) &&
                Objects.equals(date, that.date);
    }




    @Override
    public int hashCode() {
        return Objects.hash(notificationId, message, date);
    }
}
