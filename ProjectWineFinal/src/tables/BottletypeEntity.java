package tables;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bottletype", schema = "projectwinedb", catalog = "")
public class BottletypeEntity {
    private String sizeB;
    private int bottletypeId;

    @Basic
    @Column(name = "Size_B")
    public String getSizeB() {
        return sizeB;
    }

    public void setSizeB(String sizeB) {
        this.sizeB = sizeB;
    }

    @Id
    @Column(name = "bottletype_id")
    public int getBottletypeId() {
        return bottletypeId;
    }

    public void setBottletypeId(int bottletypeId) {
        this.bottletypeId = bottletypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BottletypeEntity that = (BottletypeEntity) o;
        return bottletypeId == that.bottletypeId &&
                Objects.equals(sizeB, that.sizeB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sizeB, bottletypeId);
    }
}
