package tables;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "privilegetype", schema = "projectwinedb", catalog = "")
public class PrivilegetypeEntity {
    private String privilege;
    private int privilegeId;

    @Basic
    @Column(name = "privilege")
    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    @Id
    @Column(name = "privilege_id")
    public int getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(int privilegeId) {
        this.privilegeId = privilegeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrivilegetypeEntity that = (PrivilegetypeEntity) o;
        return privilegeId == that.privilegeId &&
                Objects.equals(privilege, that.privilege);
    }

    @Override
    public int hashCode() {
        return Objects.hash(privilege, privilegeId);
    }
}
