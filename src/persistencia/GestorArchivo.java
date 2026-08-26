package persistencia;
import modelo.Producto;
import modelo.Categorias;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorArchivo {
    private String rutaArchivo;
    private static final String separador = ",";

    public GestorArchivo(String rutaArchivo){
        this.rutaArchivo = rutaArchivo;
    }

    public void guardarCatalogo(List<Producto> productos){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))){
            for (Producto p : productos){
                String linea = p.getId()+separador+
                                p.getNombre()+separador+
                                p.getPrecio()+separador+
                                p.getStock()+separador+
                                p.getCategoria().name();;
                bw.write(linea);
                bw.newLine();            
            }


        } catch (IOException e){
            System.err.println("Error al guardar en el archivo: " + e.getMessage());
        }
    }

    public List<Producto> cargarCatalogo(){
        List<Producto> productos = new ArrayList<>();
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            return productos; 
        }

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))){
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(separador);
                if (p.length == 5) {
                    int id = Integer.parseInt(p[0].trim());
                    String nombre = p[1].trim();
                    double precio = Double.parseDouble(p[2].trim());
                    int stock = Integer.parseInt(p[3].trim());
                    Categorias categoria = Categorias.valueOf(p[4].trim().toUpperCase());

                    productos.add(new Producto(id, nombre, precio, stock, categoria));
                }
            }
        }   catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
        }

        return productos;
    }

}
