package id.ac.unpas.pp2_c_233040090.modul09;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class AplikasiFileIO extends JFrame {
    // Komponen UI
    private JTextArea textArea;
    private JButton btnOpenText, btnSaveText;
    private JButton btnSaveBinary, btnLoadBinary;
    private JFileChooser fileChooser;

    public AplikasiFileIO() {
        super("Tutorial File IO & Exception Handling");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inisialisasi Komponen
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        fileChooser = new JFileChooser();

        // Panel Tombol
        JPanel buttonPanel = new JPanel();
        btnOpenText = new JButton("Buka Text");
        btnSaveText = new JButton("Simpan Text");
        btnSaveBinary = new JButton("Simpan Config (Binary)");
        btnLoadBinary = new JButton("Muat Config (Binary)");

        buttonPanel.add(btnOpenText);
        buttonPanel.add(btnSaveText);
        buttonPanel.add(btnSaveBinary);
        buttonPanel.add(btnLoadBinary);

        // Layout
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- Event Handling ---

        // 1. MEMBACA FILE TEKS (Text Stream)
        btnOpenText.addActionListener(e -> bukaFileTeks());

        // 2. MENULIS FILE TEKS (Text Stream)
        btnSaveText.addActionListener(e -> simpanFileTeks());

        // 3. MENULIS FILE BINARY (Byte Stream)
        btnSaveBinary.addActionListener(e -> simpanConfigBinary());

        // 4. MEMBACA FILE BINARY (Byte Stream)
        btnLoadBinary.addActionListener(e -> muatConfigBinary());

        // Auto-load last notes jika ada
        bacaLastNotes();
    }

    // Contoh: Membaca File Teks dengan Try-Catch-Finally Konvensional
    private void bukaFileTeks() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedReader reader = null; // Deklarasi di luar try agar bisa diakses di finally

            try {
                // Membuka stream
                reader = new BufferedReader(new FileReader(file));
                textArea.setText(""); // Kosongkan area

                String line;
                // Baca baris demi baris
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }

                JOptionPane.showMessageDialog(this, "File berhasil dimuat!");

            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "File tidak ditemukan: " + ex.getMessage()
                );

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Gagal membaca file: " + ex.getMessage()
                );

            } finally {
                // Blok finally: selalu dijalankan untuk menutup resource
                try {
                    if (reader != null) {
                        reader.close(); // PENTING: Menutup stream
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // Contoh: Menulis File Teks menggunakan Try-With-Resources (Lebih Modern)
    private void simpanFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Try-with-resources otomatis menutup stream tanpa blok finally manual
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File berhasil disimpan!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Gagal menyimpan file: " + ex.getMessage()
                );
            }
        }
    }

    // Contoh: Menulis Binary (Menyimpan ukuran font saat ini ke file .bin)
    private void simpanConfigBinary() {
        try (DataOutputStream dos =
                     new DataOutputStream(new FileOutputStream("config.bin"))) {

            // Kita simpan ukuran font saat ini (Integer)
            int fontSize = textArea.getFont().getSize();
            dos.writeInt(fontSize);

            JOptionPane.showMessageDialog(
                    this,
                    "Ukuran font (" + fontSize + ") disimpan ke config.bin"
            );

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Gagal menyimpan binary: " + ex.getMessage()
            );
        }
    }

    // Contoh: Membaca Binary (Mengambil ukuran font dari file .bin)
    private void muatConfigBinary() {
        try (DataInputStream dis =
                     new DataInputStream(new FileInputStream("config.bin"))) {

            // Membaca data Integer mentah
            int fontSize = dis.readInt();

            // Terapkan ke aplikasi
            textArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
            JOptionPane.showMessageDialog(
                    this,
                    "Font diubah menjadi ukuran: " + fontSize
            );

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "File config.bin belum dibuat!"
            );

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Gagal membaca binary: " + ex.getMessage()
            );
        }
    }

    // Menyimpan Objek ke File (Latihan 3)
    public class SaveUserConfig {
        public static void main(String[] args) {
            UserConfig user = new UserConfig();
            user.setUsername("Fikri");
            user.setFontsize(14);

            try {
                FileOutputStream fileOut = new FileOutputStream("userconfig.dat");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);

                out.writeObject(user); // simpan objek
                out.close();
                fileOut.close();

                System.out.println("Objek berhasil disimpan.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Membaca Objek dari File (ObjectInputStream + Casting)
    public class LoadUserConfig {
        public static void main(String[] args) {
            try {
                FileInputStream fileIn = new FileInputStream("userconfig.dat");
                ObjectInputStream in = new ObjectInputStream(fileIn);

                UserConfig user = (UserConfig) in.readObject(); // casting
                in.close();
                fileIn.close();

                System.out.println("Username: " + user.getUsername());
                System.out.println("Font Size: " + user.getFontsize());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    // Membaca otomatis file last_notes.txt jika ada
    private void bacaLastNotes() {
        try (BufferedReader reader = new BufferedReader(new FileReader("last_notes.txt"))) {
            textArea.setText("");
            String line;

            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal membaca last_notes.txt: " + ex.getMessage());
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplikasiFileIO().setVisible(true);
        });
    }
}
