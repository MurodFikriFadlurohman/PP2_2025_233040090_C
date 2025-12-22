package id.ac.unpas.pp2_c_233040090.modul10Tugas.Model;

public class MhsModel {
    private String nama;
    private String nim;
    private String jurusan;

    public MhsModel(String nama, String nim, String jurusan) {
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public String getJurusan() {
        return jurusan;
    }
}