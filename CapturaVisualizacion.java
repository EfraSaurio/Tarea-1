package Tarea1;

import javax.swing.*;
import java.awt.*;

public class CapturaVisualizacion extends JFrame {

    private final JLabel nombreLabel;
    private final JLabel edadLabel;
    private final JLabel pesoLabel;
    private final JLabel alturaLabel;
    private final JLabel indiceMcLabel;

    public CapturaVisualizacion(String nombre, String edad, String peso, String altura, String indiceMc){
        super("Visualización de las capturas");
        setLayout(new FlowLayout());

        nombreLabel = new JLabel("Nombre:" + nombre);
        edadLabel = new JLabel("Edad:" + edad);
        pesoLabel = new JLabel("Peso:" + peso);
        alturaLabel = new JLabel("Altura:" + altura);
        indiceMcLabel = new JLabel("Índice de Masa Corporal:" + indiceMc);

        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(nombreLabel);
        add(new JLabel(""));
        add(edadLabel);
        add(new JLabel(""));
        add(pesoLabel);
        add(new JLabel(""));
        add(alturaLabel);
        add(new JLabel(""));
        add(indiceMcLabel);

        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
