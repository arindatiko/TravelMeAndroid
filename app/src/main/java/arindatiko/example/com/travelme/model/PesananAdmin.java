package arindatiko.example.com.travelme.model;

/**
 * Created by arindatiko on 18/03/2018.
 */

public class PesananAdmin {
    private String nama, tgl_order, waktu_order;
    private int foto;

    public PesananAdmin(String nama, String tgl_order, String waktu_order, int foto) {
        this.nama = nama;
        this.tgl_order = tgl_order;
        this.waktu_order = waktu_order;
        this.foto = foto;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTgl_order() {
        return tgl_order;
    }

    public void setTgl_order(String tgl_order) {
        this.tgl_order = tgl_order;
    }

    public String getWaktu_order() {
        return waktu_order;
    }

    public void setWaktu_order(String waktu_order) {
        this.waktu_order = waktu_order;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
