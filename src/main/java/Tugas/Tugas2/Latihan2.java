package Tugas.Tugas2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Latihan2 {
    public static void main(String[] args) {
        // Membuat Frame
        JFrame frame = new JFrame("Konverter Suhu dari Celcius ke Fahrenheit");
        frame.setSize(350,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 3, 5, 5));

        // Komponen
        JLabel labelC = new JLabel("Celcius : ");
        JTextField textC = new JTextField(10);
        JButton buttonK = new JButton("Konversi");
        JLabel labelF = new JLabel("Fahrenheit : ");
        JLabel hasil = new JLabel("");

        // Menambahkan Komponen ke Frame
        frame.add(labelC);
        frame.add(textC);
        frame.add(labelF);
        frame.add(hasil);
        frame.add(buttonK);

        // EventListener untuk tombol "Konversi"

        buttonK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Ambil teks dari JTextField dan ubah ke double
                    double c = Double.parseDouble(textC.getText());

                    // Hitung konversi ke Fahrenheit
                    double f = (c * 9 / 5) + 32;

                    // Tampilkan hasil di JLabel
                    hasil.setText(String.format("%.2f °F", f));
                } catch (NumberFormatException ex) {
                    // Jika input bukan angka
                    JOptionPane.showMessageDialog(frame, "Masukkan angka yang valid !", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Tampilkan frame
        frame.setVisible(true);
    }
}
