package arindatiko.example.com.travelme.model;

/**
 * Created by arindatiko on 17/03/2018.
 */

public class Minuman {
    private String nama, harga, hargaBaru;
    private int foto;

    public Minuman(String nama, String harga, int foto) {
        this.nama = nama;
        this.harga = harga;
        this.foto = foto;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getHargaBaru() {
        return hargaBaru;
    }

    public void setHargaBaru(String hargaBaru) {
        this.hargaBaru= hargaBaru;
    }
}
