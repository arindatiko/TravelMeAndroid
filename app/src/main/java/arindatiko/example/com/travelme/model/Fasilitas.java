package arindatiko.example.com.travelme.model;

/**
 * Created by arindatiko on 18/03/2018.
 */

public class Fasilitas {
    private String nama;
    private int img;

    public Fasilitas(String nama, int img) {
        this.nama = nama;
        this.img = img;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
