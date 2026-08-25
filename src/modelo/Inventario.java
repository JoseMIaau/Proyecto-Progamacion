package modelo;

import java.util.ArrayList;
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
        if(producto == null || buscarProducto(producto.getId()) != null){
            return false;
        }

        // verifica que el precio y el stock no sean negativos (como va a valer -100 pesos jajalolxd :V)
        if(producto.getPrecio() < 0 || producto.getStock() < 0){
            return false;
        }

        productos.add(producto); // agrega el producto
        gestorArchivo.guardarCatalogo(productos); // guarda el producto 
        return true;
    }
    
    // Busca el producto
    public Producto buscarProducto(int id){
        for(Producto p:productos){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }
    
    //  Leer productos
    public List<Producto> leerProductos(){
        return new ArrayList<>(productos);
    }

    //Actualizar producto
    public boolean actualizarProducto(int id, double nuevoPrecio, int nuevoStock){

        // valida que el nuevo precio y stock no sean negativos dnvo xdxdloljaja
        if(nuevoPrecio < 0 || nuevoStock < 0){
            return false;
        }

        Producto producto= buscarProducto(id);

        if(producto!=null){
            producto.setPrecio(nuevoPrecio); // cambia el precio 
            producto.setStock(nuevoStock); // cambia el stock
            gestorArchivo.guardarCatalogo(productos); // lo guarda 
            return true;
        }
        return false;
    }

    //Eliminar producto
    public boolean eliminarProducto(int id){

        Producto producto=buscarProducto(id);

        if(producto!=null){
            productos.remove(producto);
            gestorArchivo.guardarCatalogo(productos);
            return true;
        }
        return false;
    }
}
