package id.ac.unpas.pp2_c_233040090.modul10Tugas;

import id.ac.unpas.pp2_c_233040090.modul10Tugas.View.MhsView;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MhsView().setVisible(true);
        });
    }
}
