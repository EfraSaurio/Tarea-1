package Tarea1;

import javax.swing.*;
import java.awt.*;

public class TestCaptura {

    public static void main(String[] args) {

        Captura prueba = new Captura();

        prueba.setVisible(true);
        prueba.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        prueba.setSize(280, 400);
        prueba.getContentPane().setBackground(Color.GRAY);
        prueba.setResizable(false);
        prueba.setLocationRelativeTo(null );
    }

}
