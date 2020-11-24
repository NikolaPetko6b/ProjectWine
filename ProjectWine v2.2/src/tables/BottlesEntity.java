package tables;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "bottles", schema = "projectwinedb", catalog = "")
public class BottlesEntity {
    private Integer bottlequantity;
    private int bottletypeId;
    private int bottlesId;
    private java.util.Date dateoforderB;
    private Integer warehouseId;

    @Basic
    @Column(name = "bottlequantity")
    public Integer getBottlequantity() {
        return bottlequantity;
    }

    public void setBottlequantity(Integer bottlequantity) {
        this.bottlequantity = bottlequantity;
    }

    @Basic
    @Column(name = "bottletype_id")
    public int getBottletypeId() {
        return bottletypeId;
    }

    public void setBottletypeId(int bottletypeId) {
        this.bottletypeId = bottletypeId;
    }

    @Id
    @Column(name = "bottles_id")
    public int getBottlesId() {
        return bottlesId;
    }

    public void setBottlesId(int bottlesId) {
        this.bottlesId = bottlesId;
    }

    @Basic
    @Column(name = "dateoforder_b")
    public java.util.Date getDateoforderB() {
        return dateoforderB;
    }
    public void setDateoforderB(Date dateoforderB) {
        this.dateoforderB = dateoforderB;
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
        BottlesEntity that = (BottlesEntity) o;
        return bottletypeId == that.bottletypeId &&
                bottlesId == that.bottlesId &&
                Objects.equals(bottlequantity, that.bottlequantity) &&
                Objects.equals(dateoforderB, that.dateoforderB) &&
                Objects.equals(warehouseId, that.warehouseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bottlequantity, bottletypeId, bottlesId, dateoforderB, warehouseId);
    }
}
