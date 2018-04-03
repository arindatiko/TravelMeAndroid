package arindatiko.example.com.travelme.model;

/**
 * Created by arindatiko on 27/03/2018.
 */

public class History {
    private String nama, tgl, status;
    private int profil;

    public History(String nama, String tgl, String status, int profil) {
        this.nama = nama;
        this.tgl = tgl;
        this.status = status;
        this.profil = profil;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProfil() {
        return profil;
    }

    public void setProfil(int profil) {
        this.profil = profil;
    }
}
