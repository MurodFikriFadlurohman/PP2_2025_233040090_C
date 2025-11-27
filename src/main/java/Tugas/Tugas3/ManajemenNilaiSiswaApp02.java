package Tugas.Tugas3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManajemenNilaiSiswaApp02 extends JFrame {
    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMatkul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;

    private JPanel createInputpanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Komponen Nama
        panel.add(new JLabel("Nama Siswa: "));
        txtNama = new JTextField();
        panel.add(txtNama);

        // Komponen Mata Pelajaran (ComboBox)
        panel.add(new JLabel("Mata Pelajaran: "));
        String[] matkul = {"Matematika Dasar", "Bahasa Indonesia",
                "Algoritma dan Pemrograman I", "Praktikum Pemrograman II"};
        cmbMatkul = new JComboBox<>(matkul);
        panel.add(cmbMatkul);

        // Komponen Nilai
        panel.add(new JLabel("Nilai (0-100: "));
        txtNilai = new JTextField();
        panel.add(txtNilai);

        // Tombol Simpan
        JButton btnSimpan = new JButton("Simpan Data");
        panel.add(new JLabel("")); // Placeholder kosong agar tombol di kanan
        panel.add(btnSimpan);

        // Event Handling Tombol Simpan
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesSimpan();
            }
        });
        return panel;
    }

    // Method untuk membuat desain Tab Tabel
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Setup Model Tabel (Kolom)
        String[] kolom = {"Nama Siswa", "Mata Pelajaran", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);

        // Membungkus tabel dengan ScrollPane (agar bisa di scroll jika data banyak)
        JScrollPane scrollPane = new JScrollPane(tableData);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Tugas 2: Panel tombol hapus
        JPanel panelBawah = new JPanel(new  FlowLayout(FlowLayout.RIGHT)){};
        JButton btnHapus = new JButton("Hapus Data");
        panelBawah.add(btnHapus);
        panel.add(panelBawah, BorderLayout.SOUTH);

        // Event tombol hapus
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indeks = tableData.getSelectedRow();
                if (indeks > -1) {
                    // Tampilkan dialog konfirmasi
                    int konfirmasi = JOptionPane.showConfirmDialog(null, "Apakah Anda Yakin?", "Hapus Data", JOptionPane.YES_NO_OPTION);

                    // Hanya hapus JIKA user menekan YES
                    if (konfirmasi == JOptionPane.YES_OPTION) {
                        tableModel.removeRow(indeks);
                    }
                    // Jika user pilih NO atau Close, tidak terjadi apa-apa
                } else {
                    JOptionPane.showMessageDialog(null, "Silakan pilih baris yang ingin dihapus.");
                }
            }
        });
        return panel;
    }

    // Logika Validasi dan Penyimpanan Data
    private void prosesSimpan() {
        // 1. Ambil data dari input
        String nama = txtNama.getText();
        String matkul = (String) cmbMatkul.getSelectedItem();
        String strNilai = txtNilai.getText();

        // 2. VALIDASI INPUT

        // Validasi 1: Cek apakah nama kosong
        if (nama.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama Siswa tidak boleh kosong",
                    "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return; // Hentikan Proses
        }

        // Validasi 2: Cek apakah nilai berupa angka dan dalam range valid
        int nilai;
        try {
            nilai = Integer.parseInt(strNilai);
            if (nilai <  0 || nilai > 100) {
                JOptionPane.showMessageDialog(this, "Nilai harus antara 0 - 100!",
                        "Error Validasi", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!",
                    "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Logika Bisnis (Menentukan Grade)
        String grade;
        Integer nilaiObjek = Integer.valueOf(nilai); // Ubah int primitif menjadi Integer objek

        grade = switch (nilaiObjek) {
            // Grade A: Rentang 80 sampai 100
            case Integer i when (i >= 80) -> "A";

            // Grade AB: Rentang 70 sampai 79
            case Integer i when (i >= 70) -> "AB";

            // Grade B: Rentang 60 sampai 69
            case Integer i when (i >= 60) -> "B";

            // Grade BC: Rentang 50 sampai 59
            case Integer i when (i >= 50) -> "BC";

            // Grade C: Rentang 40 sampai 49
            case Integer i when (i >= 40) -> "C";

            // Grade D: Rentang 30 sampai 39
            case Integer i when (i >= 30) -> "D";

            // Grade E: Untuk nilai di bawah 30
            default -> "E";
        };

        // 4. Masukkan ke tabel (Update Model)
        Object[] dataBaris = {nama, matkul, nilai, grade};
        tableModel.addRow(dataBaris);

        // 5. Reset Form dan Pindah Tab
        txtNama.setText("");
        txtNilai.setText("");
        cmbMatkul.setSelectedIndex(0);

        JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan!");
        tabbedPane.setSelectedIndex(1); // Otomatis pindah ke tab tabel
    }

    public ManajemenNilaiSiswaApp02(){
        // 1. Konfigurasi Frame Utama
        setTitle("Aplikasi Manajemen Nilai Siswa");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Posisi di tengah layar

        // 2. Inisialisasi Tabbed Pane
        tabbedPane = new JTabbedPane();

        // 3. Membuat Panel untuk Tab 1 (Form Input)
        JPanel panelInput = createInputpanel();
        tabbedPane.addTab("Input Data", panelInput);

        // 4. Membuat Panel untuk Tab 2 (Tabel Data)
        JPanel panelTable = createTablePanel();
        tabbedPane.addTab("Daftar Nilai", panelTable);

        // Menambahkan TabbedPane ke Frame
        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ManajemenNilaiSiswaApp02().setVisible(true);
        });
    }
}


