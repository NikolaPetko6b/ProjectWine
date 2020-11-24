package tables;

import javax.persistence.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "wine", schema = "projectwinedb", catalog = "")
public class WineEntity {
    private Double winequantity;
    private int winetypeId;
    private int wineId;
    private Integer warehouseId;
    private Date madedate;

    @Basic
    @Column(name = "MadeDate")
    private Date getMadedate(){return madedate;}
    private void setMadedate(Date d){madedate = d;}

    @Basic
    @Column(name = "winequantity")
    public Double getWinequantity() {
        return winequantity;
    }

    public void setWinequantity(Double winequantity) {
        this.winequantity = winequantity;
    }

    @Basic
    @Column(name = "winetype_id")
    public int getWinetypeId() {
        return winetypeId;
    }

    public void setWinetypeId(int winetypeId) {
        this.winetypeId = winetypeId;
    }

    @Id
    @Column(name = "wine_id")
    public int getWineId() {
        return wineId;
    }

    public void setWineId(int wineId) {
        this.wineId = wineId;
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
        WineEntity that = (WineEntity) o;
        return winetypeId == that.winetypeId &&
                wineId == that.wineId &&
                Objects.equals(winequantity, that.winequantity) &&
                Objects.equals(warehouseId, that.warehouseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winequantity, winetypeId, wineId, warehouseId);
    }
}
