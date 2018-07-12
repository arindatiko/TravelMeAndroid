package arindatiko.example.com.travelme.model;

import java.util.List;

/**
 * Created by arindatiko on 02/04/2018.
 */

public class PesananDriver {
    private int id_customer;
    private int id_driver;
    private int id_pesanan;
    private Double posisi_lat;
    private Double posisi_lng;
    private int status;
    private Double total_budget;
    private String waktu_pesan;
    //private String waktu_acc;

    private Customer user;
    private List<Tujuan> tujuan;

    public PesananDriver() {
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public int getId_driver() {
        return id_driver;
    }

    public void setId_driver(int id_driver) {
        this.id_driver = id_driver;
    }

    public int getId_pesanan() {
        return id_pesanan;
    }

    public void setId_pesanan(int id_pesanan) {
        this.id_pesanan = id_pesanan;
    }

    public Double getPosisi_lat() {
        return posisi_lat;
    }

    public void setPosisi_lat(Double posisi_lat) {
        this.posisi_lat = posisi_lat;
    }

    public Double getPosisi_lng() {
        return posisi_lng;
    }

    public void setPosisi_lng(Double posisi_lng) {
        this.posisi_lng = posisi_lng;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Double getTotal_budget() {
        return total_budget;
    }

    public void setTotal_budget(Double total_budget) {
        this.total_budget = total_budget;
    }

    public String getWaktu_pesan() {
        return waktu_pesan;
    }

    public void setWaktu_pesan(String waktu_pesan) {
        this.waktu_pesan = waktu_pesan;
    }

    public void setTujuan(List<Tujuan> tujuan) {
        this.tujuan = tujuan;
    }

    public List<Tujuan> getTujuan() {
        return tujuan;
    }

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }
}
