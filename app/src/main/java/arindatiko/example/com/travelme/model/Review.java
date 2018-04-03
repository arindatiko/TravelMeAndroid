package arindatiko.example.com.travelme.model;

/**
 * Created by arindatiko on 03/04/2018.
 */

public class Review {
    String nama, review, tgl;
    int foto;

    public Review(String nama, String review, String tgl, int foto) {
        this.nama = nama;
        this.review = review;
        this.tgl = tgl;
        this.foto = foto;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }
}
