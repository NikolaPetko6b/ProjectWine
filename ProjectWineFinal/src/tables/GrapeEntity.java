package tables;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "grape", schema = "projectwinedb", catalog = "")
public class GrapeEntity {
    private Double grapequantity;
    private int grapetypeId;
    private int grapeId;
    private java.util.Date dateoforder;
    private Integer warehouseId;

    @Basic
    @Column(name = "grapequantity")
    public Double getGrapequantity() {
        return grapequantity;
    }
    public void setGrapequantity(Double grapequantity) {
        this.grapequantity = grapequantity;
    }

    @Basic
    @Column(name = "grapetype_id")
    public int getGrapetypeId() {
        return grapetypeId;
    }

    public void setGrapetypeId(int grapetypeId) {
        this.grapetypeId = grapetypeId;
    }

    @Id
    @Column(name = "grape_id")
    public int getGrapeId() {
        return grapeId;
    }

    public void setGrapeId(int grapeId) {
        this.grapeId = grapeId;
    }

    @Basic
    @Column(name = "dateoforder")
    public java.util.Date getDateoforder() {
        return dateoforder;
    }
    public void setDateoforder(Date dateoforder) {
        this.dateoforder = dateoforder;
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
        GrapeEntity that = (GrapeEntity) o;
        return grapetypeId == that.grapetypeId &&
                grapeId == that.grapeId &&
                Objects.equals(grapequantity, that.grapequantity) &&
                Objects.equals(dateoforder, that.dateoforder) &&
                Objects.equals(warehouseId, that.warehouseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grapequantity, grapetypeId, grapeId, dateoforder, warehouseId);
    }
}
