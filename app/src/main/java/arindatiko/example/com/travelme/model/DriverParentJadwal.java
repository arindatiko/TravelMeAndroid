package arindatiko.example.com.travelme.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by arindatiko on 03/04/2018.
 */

public class DriverParentJadwal implements Serializable {
    private String nama, id, paket;
    private ArrayList<DriverChildJadwal> childData;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public ArrayList<DriverChildJadwal> getChildData() {
        return childData;
    }

    public void setChildData(ArrayList<DriverChildJadwal> childData) {
        this.childData = childData;
    }
}
