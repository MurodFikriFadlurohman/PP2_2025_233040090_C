package id.ac.unpas.pp2_c_233040090.modul10Tugas.View;

import id.ac.unpas.pp2_c_233040090.modul10Tugas.Controller.MhsController;
import id.ac.unpas.pp2_c_233040090.modul10Tugas.Model.MhsModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MhsView extends JFrame {
    // Komponen GUI
    JTextField txtNama, txtNIM, txtJurusan, txtCari;
    JButton btnSimpan, btnEdit, btnHapus, btnClear, btnCari;
    JTable tableMahasiswa;
    DefaultTableModel model;

    public MhsView() {
        // Setup Frame
        super("Aplikasi CRUD Mahasiswa JDBC | emPiCi ver");
        MhsController controller = new MhsController();
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 1. Panel Form (Input Data)
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("Nama: "));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("NIM: "));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);

        panelForm.add(new JLabel("Jurusan: "));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);

        // Panel Tombol
        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");

        panelTombol.add(btnSimpan);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);

        // Latihan 3: Tampilkan Seacrh bar ke Frame
        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtCari = new JTextField(15);
        btnCari = new JButton("Cari");
        panelCari.add(txtCari);
        panelCari.add(btnCari);

        // Gabungkan Panel Form dan Tombol di bagian Atas (NORTH)
        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.NORTH);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);
        panelAtas.add(panelCari, BorderLayout.CENTER);
        add(panelAtas, BorderLayout.NORTH);

        // 2. Tabel Data (Menampilkan Data)
        model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nama");
        model.addColumn("NIM");
        model.addColumn("Jurusan");

        tableMahasiswa = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableMahasiswa);
        add(scrollPane, BorderLayout.CENTER);

        btnSimpan.addActionListener(e -> {
            if (controller.cekNIM(txtNIM.getText())) {
                JOptionPane.showMessageDialog(this,"NIM sudah ada");
                return;
            }
            controller.tambah(new MhsModel(
                    txtNama.getText(),
                    txtNIM.getText(),
                    txtJurusan.getText()
            ));
            controller.loadData(model);
        });

        btnEdit.addActionListener(e -> {
            controller.update(new MhsModel(
                    txtNama.getText(),
                    txtNIM.getText(),
                    txtJurusan.getText()
            ));
            controller.loadData(model);
        });

        btnHapus.addActionListener(e -> {
            controller.delete(txtNIM.getText());
            controller.loadData(model);
        });

        controller.loadData(model);
    }
}