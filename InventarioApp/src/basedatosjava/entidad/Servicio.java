package basedatosjava.entidad;

public class Servicio {
    private int id;
    private String descripcion;
    private String cliente;
    private double precio;

    public Servicio() {
    }

    public Servicio(String descripcion, String cliente, double precio) {
        this.descripcion = descripcion;
        this.cliente = cliente;
        this.precio = precio;
    }

    public Servicio(int id, String descripcion, String cliente, double precio) {
        this.id = id;
        this.descripcion = descripcion;
        this.cliente = cliente;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
