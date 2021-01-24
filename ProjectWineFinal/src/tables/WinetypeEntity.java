package tables;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "winetype", schema = "projectwinedb", catalog = "")
public class WinetypeEntity {
    private String winename;
    private int winetypeId;
    private Double whiteGrapeNeeded;
    private Double redGrapeNeeded;
    private Integer userId;

    @Basic
    @Column(name = "winename")
    public String getWinename() {
        return winename;
    }

    public void setWinename(String winename) {
        this.winename = winename;
    }

    @Id
    @Column(name = "winetype_id")
    public int getWinetypeId() {
        return winetypeId;
    }

    public void setWinetypeId(int winetypeId) {
        this.winetypeId = winetypeId;
    }

    @Basic
    @Column(name = "WhiteGrapeNeeded")
    public Double getWhiteGrapeNeeded() {
        return whiteGrapeNeeded;
    }

    public void setWhiteGrapeNeeded(Double whiteGrapeNeeded) {
        this.whiteGrapeNeeded = whiteGrapeNeeded;
    }

    @Basic
    @Column(name = "RedGrapeNeeded")
    public Double getRedGrapeNeeded() {
        return redGrapeNeeded;
    }

    public void setRedGrapeNeeded(Double redGrapeNeeded) {
        this.redGrapeNeeded = redGrapeNeeded;
    }

    @Basic
    @Column(name = "User_Id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinetypeEntity that = (WinetypeEntity) o;
        return winetypeId == that.winetypeId &&
                Objects.equals(winename, that.winename) &&
                Objects.equals(whiteGrapeNeeded, that.whiteGrapeNeeded) &&
                Objects.equals(redGrapeNeeded, that.redGrapeNeeded) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winename, winetypeId, whiteGrapeNeeded, redGrapeNeeded, userId);
    }
}
