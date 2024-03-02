package Tarea1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Captura extends JFrame {

    private final JTextField nombre;
    private final JTextField edad;
    private final JTextField peso;
    private final JTextField altura;
    private final JTextField indiceMc;
    private int contadorCapturas;
    private final JButton borrar;
    private final JButton nuevo;
    private final JButton guardar;
    private final JButton eliminarInformacion;
    private final JButton siguiente;
    private final JButton anterior;

    private int indiceActual = -1;
    private ArrayList<String> capturas = new ArrayList<>();

    public Captura(){
        super("Captura de información personal");
        setLayout(new FlowLayout());

        cargarCapturas();

        add(new JLabel("Nombre:"));
        nombre = new JTextField(20);
        add(nombre);

        add(new JLabel("Edad:"));
        edad = new JTextField(20);
        add(edad);

        add(new JLabel("Peso:"));
        peso = new JTextField(20);
        add(peso);

        add(new JLabel("Altura:"));
        altura = new JTextField(20);
        add(altura);

        add(new JLabel("IndiceMc:"));
        indiceMc = new JTextField(20);
        add(indiceMc);

        borrar = new JButton("Borrar Captura");
        add(borrar);

        nuevo = new JButton("Nuevo");
        add(nuevo);

        guardar = new JButton("Guardar");
        add(guardar);

        eliminarInformacion = new JButton("Eliminar Información");
        add(eliminarInformacion);

        siguiente = new JButton(">");
        add(siguiente);

        anterior = new JButton("<");
        add(anterior);

        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarInformacion();
            }
        });

        borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarInformacion();
            }
        });

        nuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevoRegistro();
            }
        });

        eliminarInformacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarTodo();
            }
        });

        anterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCapturaAnterior();
            }
        });

        siguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCapturaSiguiente();
            }
        });

    }

    private void borrarInformacion(){
        nombre.setText("");
        edad.setText("");
        peso.setText("");
        altura.setText("");
        indiceMc.setText("");
    }


    private void guardarInformacion(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("informacion.txt", true))) {
            writer.write("Captura " + contadorCapturas);
            writer.newLine();
            writer.write("Nombre: " + nombre.getText());
            writer.newLine();
            writer.write("Edad: " + edad.getText());
            writer.newLine();
            writer.write("Peso: " + peso.getText());
            writer.newLine();
            writer.write("Altura: " + altura.getText());
            writer.newLine();
            writer.write("Índice de masa corporal: " + indiceMc.getText());
            writer.newLine();
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Información guardada exitosamente" + contadorCapturas);
            contadorCapturas++;
        }catch (IOException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar la información", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void nuevoRegistro(){
        borrarInformacion();
        JOptionPane.showMessageDialog(this, "Inicia una nueva captura de información");
    }

    private void eliminarTodo(){
        try {
            File archivo = new File("informacion.txt");
            if (archivo.exists()){
                archivo.delete();
                JOptionPane.showMessageDialog(this, "Toda la información ha sido eliminada del archivo");
            } else {
                JOptionPane.showMessageDialog(this, "No hay información para eliminar en el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar la información del archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarCapturaAnterior(){
        if (indiceActual > 0 && indiceActual <= capturas.size()){
            indiceActual--;
            mostrarCapturaVentana();
        } else {
            JOptionPane.showMessageDialog(this, "No hay capturas anteriores disponibles", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarCapturaSiguiente(){
        if (indiceActual >= 0 && indiceActual < capturas.size() - 1){
            indiceActual++;
            mostrarCapturaVentana();
        } else {
            JOptionPane.showMessageDialog(this, "No hay capturas siguientes disponibles", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarCapturaVentana(){
        String captura = capturas.get(indiceActual);
        String[] lineas = captura.split("\n");

        nombre.setText(obtenerValorCampo(lineas, "Nombre:"));
        edad.setText(obtenerValorCampo(lineas, "Edad:"));
        peso.setText(obtenerValorCampo(lineas, "Peso:"));
        altura.setText(obtenerValorCampo(lineas, "Altura:"));
        indiceMc.setText(obtenerValorCampo(lineas, "Índice de masa corporal:"));

        new CapturaVisualizacion( nombre.getText(), edad.getText(), peso.getText(), altura.getText(), indiceMc.getText());
    }

    private String obtenerValorCampo(String[] lineas, String etiquetaCampo){
        for (String linea : lineas){
            if (linea.startsWith(etiquetaCampo)){
                return linea.substring(etiquetaCampo.length()).trim();
            }
        }
        return "";
    }

    private void cargarCapturas(){
        try {
            File archivo = new File("informacion.txt");
            if (!archivo.exists()){
                JOptionPane.showMessageDialog(this, "No hay información guardada aún", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String linea;
            StringBuilder capturaActual = new StringBuilder();

            while ((linea = reader.readLine()) != null){
                if (linea.startsWith("Captura")){
                    if (capturaActual.length() > 0){
                        capturas.add(capturaActual.toString());
                        capturaActual = new StringBuilder();
                    }
                }
                capturaActual.append(linea).append("\n");
            }
            if (capturaActual.length() > 0){
                capturas.add(capturaActual.toString());
            }
            reader.close();
        } catch (IOException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar las capturas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}