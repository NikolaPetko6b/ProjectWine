package tables;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "current_stock", schema = "projectwinedb", catalog = "")
public class CurrentStockEntity {
    private Integer bottles187;
    private Integer bottles200;
    private Integer bottles375;
    private Integer bottles750;
    private Double whiteGrape;
    private Double redGrape;
    private Integer warehouseId;
    private java.util.Date curDate;
    private int currentstockid;

    @Id
    @Column(name = "current_stock_id")
    public int getCurrentstockid(){return currentstockid;};

    public void setCurrentstockid(int currentstockid) {
        this.currentstockid = currentstockid;
    }

    @Basic
    @Column(name = "bottles187")
    public Integer getBottles187() {
        return bottles187;
    }

    public void setBottles187(Integer bottles187) {
        this.bottles187 = bottles187;
    }

    @Basic
    @Column(name = "bottles200")
    public Integer getBottles200() {
        return bottles200;
    }

    public void setBottles200(Integer bottles200) {
        this.bottles200 = bottles200;
    }

    @Basic
    @Column(name = "bottles375")
    public Integer getBottles375() {
        return bottles375;
    }

    public void setBottles375(Integer bottles375) {
        this.bottles375 = bottles375;
    }

    @Basic
    @Column(name = "bottles750")
    public Integer getBottles750() {
        return bottles750;
    }

    public void setBottles750(Integer bottles750) {
        this.bottles750 = bottles750;
    }

    @Basic
    @Column(name = "WhiteGrape")
    public Double getWhiteGrape() {
        return whiteGrape;
    }

    public void setWhiteGrape(Double whiteGrape) {
        this.whiteGrape = whiteGrape;
    }

    @Basic
    @Column(name = "RedGrape")
    public Double getRedGrape() {
        return redGrape;
    }

    public void setRedGrape(Double redGrape) {
        this.redGrape = redGrape;
    }

    @Basic
    @Column(name = "warehouse_id")
    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Basic
    @Column(name = "CurDate")
    public java.util.Date getCurDate() {
        return curDate;
    }
    public void setCurDate(Date curDate) {
        this.curDate = curDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentStockEntity that = (CurrentStockEntity) o;
        return Objects.equals(bottles187, that.bottles187) &&
                Objects.equals(bottles200, that.bottles200) &&
                Objects.equals(bottles375, that.bottles375) &&
                Objects.equals(bottles750, that.bottles750) &&
                Objects.equals(whiteGrape, that.whiteGrape) &&
                Objects.equals(redGrape, that.redGrape) &&
                Objects.equals(warehouseId, that.warehouseId) &&
                Objects.equals(curDate, that.curDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bottles187, bottles200, bottles375, bottles750, whiteGrape, redGrape, warehouseId, curDate);
    }
}
