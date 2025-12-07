package id.ac.unpas.pp2_c_233040090.modul08.controller;

import id.ac.unpas.pp2_c_233040090.modul08.model.PersegiPanjangModel;
import id.ac.unpas.pp2_c_233040090.modul08.view.PersegiPanjangView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersegiPanjangController {
    // Model dan View sebagai atribut kelas
    private PersegiPanjangModel model;
    private PersegiPanjangView view;

    public PersegiPanjangController(PersegiPanjangModel model, PersegiPanjangView view) {
        this.model = model;
        this.view = view;

        // Menghubungkan tombol di View dengan logic di Controller
        this.view.addHitungListener(new HitungListener());

        // Fitur Baru: Menghubungkan listener Reset
        this.view.addResetListener(new ResetListener());
    }

    // Inner class untuk menangani event klik tombol
    class HitungListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // 1. Ambil data dari View
               double p = view.getPanjang();
               double l = view.getLebar();

                // 2. Kirim data ke Model
                model.setPanjang(p);
                model.setLebar(l);

                // 3. Jalankan logika bisnis di Model
                model.hitungLuas();
                model.hitungKeliling();

                // 4. Ambil hasil dari Model dan tampilkan kembali di View
                double hasil = model.getLuas();
                view.setHasil(hasil);

                // Update Keliling
                double hasilKeliling = model.getKeliling();
                view.setHasilKeliling(hasilKeliling);

            } catch (NumberFormatException ex) {
                // Handle jika user memasukkan huruf atau input kosong
                view.tampilkanPesanError("Masukkan angka yang valid!");
            }
        }
    }

    // Fitur Baru: Inner class untuk menangani event klik tombol Reset
    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 1. Reset data internal di Model
            model.resetModel();

            // 2. Reset tampilan di View
            view.resetView();
        }
    }
}
