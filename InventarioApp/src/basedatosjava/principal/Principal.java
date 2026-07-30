package basedatosjava.principal;

import basedatosjava.vista.VentanaComponente;
import javax.swing.SwingUtilities;

public class Principal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaComponente ventana = new VentanaComponente();
            ventana.setVisible(true);
        });
    }
}

