package Tugas;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Tugas1 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Contoh BorderLayout");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.setLayout(new BorderLayout());

                JLabel label = new JLabel("Label ada di Atas (NORTH)");
                JButton button = new JButton("Tombol Ada di Bawah (SOUTH)");
                JButton button2 = new JButton("WEST");
                JButton button3 = new JButton("EAST");
                JButton button4 = new JButton("CENTER");

                button.addActionListener(e -> {
                    label.setText("Tombol di SOUTH diklik");
                });

                button2.addActionListener(e -> {
                    label.setText("Tombol WEST di KLIK!");
                });

                button3.addActionListener(e -> {
                    label.setText("Tombol EAST di KLIK!");
                });

                button4.addActionListener(e -> {
                    label.setText("Tombol CENTER di KLIK!");
                });

                frame.add(label, BorderLayout.NORTH);
                frame.add(button, BorderLayout.SOUTH);
                frame.add(button2, BorderLayout.WEST);
                frame.add(button3, BorderLayout.EAST);
                frame.add(button4, BorderLayout.CENTER);

                frame.setVisible(true);
            }
        });
    }
}
