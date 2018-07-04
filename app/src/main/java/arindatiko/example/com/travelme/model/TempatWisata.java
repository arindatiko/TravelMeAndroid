package arindatiko.example.com.travelme.model;

/**
 * Created by arindatiko on 09/04/2018.
 */

public class TempatWisata {
    String jenis, nama, alamat;
    int img;

    public TempatWisata(String nama, String alamat, int img) {
        this.nama = nama;
        this.alamat = alamat;
        this.img = img;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
