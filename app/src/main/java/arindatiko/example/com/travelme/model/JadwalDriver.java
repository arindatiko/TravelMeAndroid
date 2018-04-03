package arindatiko.example.com.travelme.model;

/**
 * Created by arindatiko on 02/04/2018.
 */

public class JadwalDriver {
    private String nama, via, paket, bayar, tgl, waktu;
    private int gambar;

    public JadwalDriver(String nama, String via, String paket, String bayar, String tgl, String waktu, int gambar) {
        this.nama = nama;
        this.via = via;
        this.paket = paket;
        this.bayar = bayar;
        this.tgl = tgl;
        this.waktu = waktu;
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public String getBayar() {
        return bayar;
    }

    public void setBayar(String bayar) {
        this.bayar = bayar;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }
}
