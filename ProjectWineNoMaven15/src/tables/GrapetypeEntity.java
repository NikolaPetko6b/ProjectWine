package tables;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "grapetype", schema = "projectwinedb", catalog = "")
public class GrapetypeEntity {
    private String type;
    private int grapetypeId;

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Id
    @Column(name = "grapetype_id")
    public int getGrapetypeId() {
        return grapetypeId;
    }

    public void setGrapetypeId(int grapetypeId) {
        this.grapetypeId = grapetypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrapetypeEntity that = (GrapetypeEntity) o;
        return grapetypeId == that.grapetypeId &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, grapetypeId);
    }
}
