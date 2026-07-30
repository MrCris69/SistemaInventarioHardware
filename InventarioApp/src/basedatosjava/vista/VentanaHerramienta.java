package basedatosjava.vista;

import basedatosjava.dao.HerramientaDAO;
import basedatosjava.entidad.Herramienta;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class VentanaHerramienta extends JFrame {
    private final JTextField txtNombre, txtTipo, txtCantidad;
    private final JButton btnGuardar, btnListar;
    private final JTable tablaHerramientas;
    private final DefaultTableModel modelo;
    private final HerramientaDAO herramientaDAO = new HerramientaDAO();

    public VentanaHerramienta() {
        txtNombre = new JTextField(20);
        txtTipo = new JTextField(20);
        txtCantidad = new JTextField(20);
        btnGuardar = new JButton("Guardar");
        btnListar = new JButton("Listar");
        
        modelo = new DefaultTableModel(new Object[]{"ID", "Nombre", "Tipo", "Cantidad"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) { return false; }
        };
        tablaHerramientas = new JTable(modelo);
        
        setTitle("Registro de Herramientas");
        setSize(760, 460);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 8, 8));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(12, 12, 8, 12));
        panelFormulario.add(new JLabel("Nombre de la Herramienta:")); panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Tipo/Categoría:")); panelFormulario.add(txtTipo);
        panelFormulario.add(new JLabel("Cantidad en Taller:")); panelFormulario.add(txtCantidad);
        
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnGuardar); panelBotones.add(btnListar);
        
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);
        
        setLayout(new BorderLayout());
        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(tablaHerramientas), BorderLayout.CENTER);
        
        btnGuardar.addActionListener(e -> guardarHerramienta());
        btnListar.addActionListener(e -> listarHerramientas());
        listarHerramientas();
    }

    private void guardarHerramienta() {
        String nombre = txtNombre.getText().trim();
        String tipo = txtTipo.getText().trim();
        String cantidadTexto = txtCantidad.getText().trim();
        
        if (nombre.isEmpty() || tipo.isEmpty() || cantidadTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            int cantidad = Integer.parseInt(cantidadTexto);
            if (herramientaDAO.registrar(new Herramienta(nombre, tipo, cantidad))) {
                JOptionPane.showMessageDialog(this, "Herramienta registrada exitosamente.");
                txtNombre.setText(""); txtTipo.setText(""); txtCantidad.setText("");
                listarHerramientas();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarHerramientas() {
        modelo.setRowCount(0);
        for (Herramienta herramienta : herramientaDAO.listar()) {
            modelo.addRow(new Object[]{herramienta.getId(), herramienta.getNombre(), herramienta.getTipo(), herramienta.getCantidad()});
        }
    }
}