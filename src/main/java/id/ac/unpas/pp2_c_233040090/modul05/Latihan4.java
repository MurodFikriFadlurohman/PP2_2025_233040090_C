package id.ac.unpas.pp2_c_233040090.modul05;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Latihan4 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
          public void run() {
              JFrame frame = new JFrame("Contoh BorderLayout");
              frame.setSize(400, 300);
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

              frame.setLayout(new BorderLayout());

              JLabel label = new JLabel("Label ada di Atas (NORTH)");
              JButton button = new JButton("Tombol Ada di Bawah (SOUTH)");

              button.addActionListener(e -> {
                  label.setText("Tombol di SOUTH diklik");
              });

              frame.add(label, BorderLayout.NORTH);
              frame.add(button, BorderLayout.SOUTH);
              frame.add(new JButton("WEST"), BorderLayout.WEST);
              frame.add(new JButton("EAST"), BorderLayout.EAST);
              frame.add(new JButton("CENTER"), BorderLayout.CENTER);

              frame.setVisible(true);
            }
        });
    }
}
