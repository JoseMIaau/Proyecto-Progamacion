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

    //  Crear producto 
    public boolean crearProducto(Producto producto){
        if(producto== null){
            return false;           
        }
        getInstancia().productos.add(producto);
        getInstancia().gestorArchivo.guardarCatalogo(getInstancia().productos);
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
    
    //  Leer productos
    public void leerProductos(){
        if(productos.size() == 0){
            System.out.println("No hay productos en el inventario.");
        } else {
            for(Producto p:productos){
                System.out.println(p);
            }
        }
    }

    //Actualizar producto
    public boolean actualizarProducto(int id, double nuevoPrecio, int nuevoStock){
        Producto producto= buscarProducto(id);

        if(producto!=null){
            producto.setPrecio(nuevoPrecio);
            producto.setStock(nuevoStock);
            System.out.println("Producto actualizado");
            return true;
        }

        getInstancia().gestorArchivo.guardarCatalogo(getInstancia().productos);

        System.out.println("Producto no encontrado");
        return false;
    }

    //Eliminar producto
    public boolean eliminarProducto(int id){
        Producto producto=buscarProducto(id);
        if(producto!=null){
            productos.remove(producto);
            System.out.println("Producto eliminado");
            return true;
        }
        getInstancia().gestorArchivo.guardarCatalogo(getInstancia().productos);
        System.out.println("Producto no encontrado");
        return false;
    }
}
