package modelo;
import persistencia.GestorArchivo;
import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private static Inventario instancia;
    private List<Producto> productos;
    private GestorArchivo gestorArchivo;

    private Inventario() {
        this.gestorArchivo = new GestorArchivo("inventario.csv");
        this.productos = gestorArchivo.cargarCatalogo();
    }

    public static Inventario getInstancia() {
        if (instancia == null) {
            instancia = new Inventario();
        }
        return instancia;
    }

    //Metodos CRUD
}
