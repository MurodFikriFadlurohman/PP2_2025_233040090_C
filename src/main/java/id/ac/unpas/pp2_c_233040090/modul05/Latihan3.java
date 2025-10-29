package id.ac.unpas.pp2_c_233040090.modul05;

import javax.swing.*;
import java.awt.FlowLayout;

public class Latihan3 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Modul 3");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 300);
                frame.setLayout(new FlowLayout());

                JLabel label = new JLabel("Modul 3 gaiss");
                JButton button = new JButton("KLIK");
                button.addActionListener(e -> {
                    label.setText("Aku adalah wowo");
                });

                frame.add(label);
                frame.add(button);
                frame.setVisible(true);
            }
        });
    }
}
