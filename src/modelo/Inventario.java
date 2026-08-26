package modelo;

import java.util.ArrayList;
import persistencia.GestorArchivo;
import java.util.List;

public class Inventario {
    private static Inventario instancia;
    private List<Producto> productos;
    private List<ItemCarrito> carrito;
    private GestorArchivo gestorArchivo;

    private Inventario() {
        this.gestorArchivo = new GestorArchivo("inventario.csv");
        this.productos = gestorArchivo.cargarCatalogo();
        this.carrito = new ArrayList<>();
        inicializarDatosDemoSiVacio();
    }

    public static Inventario getInstancia() {
        if (instancia == null) {
            instancia = new Inventario();
        }
        return instancia;
    }

    private void inicializarDatosDemoSiVacio() {
        if (productos.isEmpty()) {
            productos.add(new Producto(1, "Plátano", 1490.0, 50, Categorias.FRUTAS_Y_VERDURAS));
            productos.add(new Producto(2, "Manzana", 1190.0, 40, Categorias.FRUTAS_Y_VERDURAS));
            productos.add(new Producto(3, "Leche Entera", 990.0, 30, Categorias.LACTEOS));
            productos.add(new Producto(4, "Marraqueta", 1890.0, 25, Categorias.PANADERIA));
            productos.add(new Producto(5, "Pechuga Pollo", 4990.0, 15, Categorias.CARNICERIA));
            productos.add(new Producto(6, "Bebida Cola 2L", 2100.0, 35, Categorias.BEBIDAS));
            productos.add(new Producto(7, "Detergente 1L", 3490.0, 20, Categorias.LIMPIEZA));
            gestorArchivo.guardarCatalogo(productos);
        }
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
    
    public List<Producto> filtrarPorCategoria(Categorias cat) {
        if (cat == null) return leerProductos();
        List<Producto> filtrados = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getCategoria() == cat) filtrados.add(p);
        }
        return filtrados;
    }

    //---Carrito---
    public List<ItemCarrito> getCarrito() {
        return carrito;
    }

    public void agregarAlCarrito(Producto p, double cantidad) {
        //Verificacion de si ya existe el prodcuto en el carrito
        for (ItemCarrito item : carrito) {//Busca si el producto esta en el carrito
            if (item.getProducto().getId() == p.getId()) {//si lo encuentra le suma la nueva cantidad en lugar de agregarlo por separado al carrito
                item.setCantidad(item.getCantidad() + cantidad);
                return;//termina la ejecucion
            }
        }
        carrito.add(new ItemCarrito(p, cantidad));//agrega por defecto el producto al carrito
    }

    public void eliminarDelCarrito(int idProducto) {
        carrito.removeIf(item -> item.getProducto().getId() == idProducto);
    }

    public void vaciarCarrito() {
        carrito.clear();
    }

    public double calcularTotalCarrito() {
        double total = 0.0;
        for (ItemCarrito item : carrito) {
            total += item.getSubtotal();
        }
        return total;
    }
}
