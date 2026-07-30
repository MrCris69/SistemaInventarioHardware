package basedatosjava.vista;
import basedatosjava.dao.ServicioDAO;
import basedatosjava.entidad.Servicio;
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

public class VentanaServicio extends JFrame {
    private final JTextField txtDescripcion;
    private final JTextField txtCliente;
    private final JTextField txtPrecio;
    private final JButton btnGuardar;
    private final JButton btnListar;
    private final JTable tablaServicios;
    private final DefaultTableModel modelo;
    private final ServicioDAO servicioDAO;

    public VentanaServicio() {
        servicioDAO = new ServicioDAO();
        
        txtDescripcion = new JTextField(20);
        txtCliente = new JTextField(20);
        txtPrecio = new JTextField(20);
        btnGuardar = new JButton("Guardar");
        btnListar = new JButton("Listar");
        
        modelo = new DefaultTableModel(
                new Object[]{"ID", "Descripción", "Cliente", "Precio"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tablaServicios = new JTable(modelo);
        
        configurarVentana();
        organizarComponentes();
        registrarEventos();
        listarServicios();
    }

    private void configurarVentana() {
        setTitle("Registro de Servicios y Mantenimiento");
        setSize(760, 460);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void organizarComponentes() {
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 8, 8));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(12, 12, 8, 12));
        
        panelFormulario.add(new JLabel("Descripción del Trabajo:"));
        panelFormulario.add(txtDescripcion);
        panelFormulario.add(new JLabel("Nombre del Cliente:"));
        panelFormulario.add(txtCliente);
        panelFormulario.add(new JLabel("Precio Cobrado:"));
        panelFormulario.add(txtPrecio);
        
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnGuardar);
        panelBotones.add(btnListar);
        
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);
        
        JScrollPane desplazamiento = new JScrollPane(tablaServicios);
        
        setLayout(new BorderLayout());
        add(panelSuperior, BorderLayout.NORTH);
        add(desplazamiento, BorderLayout.CENTER);
    }

    private void registrarEventos() {
        btnGuardar.addActionListener(e -> guardarServicio());
        btnListar.addActionListener(e -> listarServicios());
    }

    private void guardarServicio() {
        String descripcion = txtDescripcion.getText().trim();
        String cliente = txtCliente.getText().trim();
        String precioTexto = txtPrecio.getText().trim();
        
        if (descripcion.isEmpty() || cliente.isEmpty() || precioTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            double precio = Double.parseDouble(precioTexto);
            
            Servicio servicio = new Servicio(descripcion, cliente, precio);
            boolean registrado = servicioDAO.registrar(servicio);
            
            if (registrado) {
                JOptionPane.showMessageDialog(this, "Servicio registrado exitosamente.");
                txtDescripcion.setText("");
                txtCliente.setText("");
                txtPrecio.setText("");
                txtDescripcion.requestFocus();
                listarServicios();
            } else {
                JOptionPane.showMessageDialog(this, "No fue posible registrar el servicio.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un valor numérico.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarServicios() {
        modelo.setRowCount(0);
        List<Servicio> servicios = servicioDAO.listar();
        
        for (Servicio servicio : servicios) {
            modelo.addRow(new Object[]{
                servicio.getId(),
                servicio.getDescripcion(),
                servicio.getCliente(),
                servicio.getPrecio()
            });
        }
    }
}
