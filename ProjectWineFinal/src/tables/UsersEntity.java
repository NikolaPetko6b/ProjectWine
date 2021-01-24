package tables;

import Dao.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "projectwinedb", catalog = "")
public class UsersEntity {
    private String username;
    private String password;
    private int privilegeId;
    private int userId;
    private Integer warehouseId;




    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "privilege_id")
    public int getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(int privilegeId) {
        this.privilegeId = privilegeId;
    }

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "warehouse_id")
    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return privilegeId == that.privilegeId &&
                userId == that.userId &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(warehouseId, that.warehouseId);
    }

    public boolean Exist()
    {
        try
        {
            Session session = HibernateUtil.getSessionFactory().openSession();
            UsersEntity result = (UsersEntity) session.createQuery("from UsersEntity where username = :Username").setParameter("Username",username).getSingleResult();
            return true;
        }catch (Exception e)
        {
            return false;
        }

    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, privilegeId, userId, warehouseId);
    }
}
