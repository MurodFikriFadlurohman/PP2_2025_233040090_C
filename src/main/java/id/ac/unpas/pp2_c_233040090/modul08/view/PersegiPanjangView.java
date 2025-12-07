package id.ac.unpas.pp2_c_233040090.modul08.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PersegiPanjangView extends JFrame {

    // Komponen UI sebagai atribut
    private JTextField txtPanjang = new JTextField(10);
    private JTextField txtLebar = new JTextField(10);
    private JLabel lblHasil = new JLabel("-");
    // Fitur Baru: Label untuk Keliling
    private JLabel lblHasilKeliling = new JLabel("-");
    private JButton btnHitung = new JButton("Hitung Luas");
    // Fitur Baru: Tombol Reset
    private JButton btnReset = new JButton("Reset");

    public PersegiPanjangView() {
        // Inisialisasi UI
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 250);
        this.setLayout(new GridLayout(6, 2, 10, 10)); // Grid 4 baris
        this.setTitle("MVC Kalkulator");

        this.add(new JLabel("Panjang:"));
        this.add(txtPanjang);
        this.add(new JLabel("Lebar:"));
        this.add(txtLebar);
        this.add(new JLabel("Hasil Luas:"));
        this.add(lblHasil);
        // Fitur Baru: Baris untuk Keliling
        this.add(new JLabel("Hasil Keliling:"));
        this.add(lblHasilKeliling);

        //this.add(new JLabel("")); // Spacer kosong
        this.add(btnHitung);
        this.add(btnReset);
    }

    // Mengambil nilai panjang dari Textfield
    public double getPanjang() {
        return Double.parseDouble(txtPanjang.getText());
    }

    // Mengambil nilai lebar dari Textfield
    public double getLebar() {
        return Double.parseDouble(txtLebar.getText());
    }

    // Menampilkan hasil ke Label
    public void setHasil(double hasil) {
        lblHasil.setText(String.valueOf(hasil));
    }

    // Menampilkan pesan error (jika input bukan angka)
    public void tampilkanPesanError(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }

    // Mendaftarkan Listener untuk tombol (Controller yang akan memberikan aksinya)
    public void addHitungListener(ActionListener listener) {
        btnHitung.addActionListener(listener);
    }

    // Fitur Baru: Mendaftarkan Listener untuk tombol Reset
    public void addResetListener(ActionListener listener) {
        btnReset.addActionListener(listener);
    }

    // Fitur Baru: Setter untuk Keliling
    public void setHasilKeliling(double hasil) {
        lblHasilKeliling.setText(String.valueOf(hasil));
    }

    // Fitur Baru: Metode untuk membersihkan dan mereset View
    public void resetView() {
        txtPanjang.setText("");
        txtLebar.setText("");
        lblHasil.setText("-");
        lblHasilKeliling.setText("-");
    }
}