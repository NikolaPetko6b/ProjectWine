package tables;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "warehouse", schema = "projectwinedb", catalog = "")
public class WarehouseEntity {
    private String warehouseN;
    private String warehouseLocation;
    private int warehouseId;

    @Basic
    @Column(name = "warehouse_n")
    public String getWarehouseN() {
        return warehouseN;
    }

    public void setWarehouseN(String warehouseN) {
        this.warehouseN = warehouseN;
    }

    @Basic
    @Column(name = "warehouse_location")
    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    @Id
    @Column(name = "warehouse_id")
    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseEntity that = (WarehouseEntity) o;
        return warehouseId == that.warehouseId &&
                Objects.equals(warehouseN, that.warehouseN) &&
                Objects.equals(warehouseLocation, that.warehouseLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warehouseN, warehouseLocation, warehouseId);
    }
}
