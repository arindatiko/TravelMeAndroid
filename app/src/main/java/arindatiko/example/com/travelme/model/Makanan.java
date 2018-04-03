package arindatiko.example.com.travelme.model;

/**
 * Created by arindatiko on 17/03/2018.
 */

public class Makanan {
    private String nama, hargaBaru, harga;
    private int foto;

    public Makanan(){

    }

    public Makanan(String nama, String harga, int foto){
        this.nama = nama;
        this.harga = harga;
        this.foto = foto;
    }

    public String getNama() {
        return nama;
    }

    public String getHarga() {
        return harga;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHargaBaru() {
        return hargaBaru;
    }

    public void setHargaBaru(String hargaBaru) {
        this.hargaBaru= hargaBaru;
    }
}
