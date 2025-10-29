package id.ac.unpas.pp2_c_233040090.modul05;


import javax.swing.*;

public class Latihan1 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frameSaya = new JFrame("INI BINGKAI");
                frameSaya.setSize(400, 300);
                frameSaya.setDefaultCloseOperation(frameSaya.EXIT_ON_CLOSE);
                frameSaya.setVisible(true);

                JLabel labelSaya = new JLabel("gws SOK KEREN");
            }
        });
    }
}
