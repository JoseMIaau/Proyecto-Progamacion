package modelo;
import persistencia.GestorArchivo;
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

    public boolean crearProducto(Producto producto){
        if( buscarProducto(producto.getId())!=null){
            System.out.println("Error: El producto ya existe.");
            return false;           
        }
        productos.add(producto);
        System.out.println("Producto agregado correctamente.");
        return true;
    }

    public Producto buscarProducto(int id){
        for(Producto p:productos){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    public void leerProductos(){
        if(productos.size() == 0){
            System.out.println("No hay productos en el inventario.");
        } else {
            for(Producto p:productos){
                System.out.println(p);
            }
        }
    }

    public boolean actualizarProducto(int id, double nuevoPrecio, int nuevoStock){
        Producto producto= buscarProducto(id);

        if(producto!=null){
            producto.setPrecio(nuevoPrecio);
            producto.setStock(nuevoStock);
            System.out.println("Producto actualizado");
            return true;
        }

        System.out.println("Producto no encontrado");
        return false;
    }

    public boolean eliminarProducto(int id){
        Producto producto=buscarProducto(id);
        if(producto!=null){
            productos.remove(producto);
            System.out.println("Producto eliminado");
            return true;
        }
        System.out.println("Producto no encontrado");
        return false;
    }
}