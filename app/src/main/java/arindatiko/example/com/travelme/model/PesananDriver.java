package arindatiko.example.com.travelme.model;

/**
 * Created by arindatiko on 02/04/2018.
 */

public class PesananDriver {
    private String nama, id, nomor, tempat, harga, via;
    private int foto;

    public PesananDriver(String nama, String id, String nomor, String tempat, String harga, String via, int foto) {
        this.nama = nama;
        this.id = id;
        this.nomor = nomor;
        this.tempat = tempat;
        this.harga = harga;
        this.via = via;
        this.foto = foto;
    }

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

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
