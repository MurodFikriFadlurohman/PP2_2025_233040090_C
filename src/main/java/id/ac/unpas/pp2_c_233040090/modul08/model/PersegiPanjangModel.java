package id.ac.unpas.pp2_c_233040090.modul08.model;

public class PersegiPanjangModel {
    private double panjang;
    private double lebar;
    private double luas;
    // Fitur baru : menambahkan keliling
    private double keliling;

    // Fitur Baru: Metode untuk mereset data internal Model
    public void resetModel() {
        this.panjang = 0;
        this.lebar = 0;
        this.luas = 0;
        this.keliling = 0;
    }

    // Menghitung luas (Logika Bisnis)
    public void hitungLuas() {
        this.luas = this.panjang * this.lebar;
    }

    // Menghitung Keliling
    public void hitungKeliling() {
        this.keliling = 2 * (this.panjang + this.lebar);
    }

    // Getters dan Setters
    public void setPanjang(double panjang) {
        this.panjang = panjang;
    }

    public void setLebar(double lebar) {
        this.lebar = lebar;
    }

    public double getLuas() {
        return luas;
    }

    // Fitur Baru: Getter untuk Keliling
    public double getKeliling() {
        return keliling;
    }
}