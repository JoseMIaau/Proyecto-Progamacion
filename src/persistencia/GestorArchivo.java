package persistencia;
import modelo.Producto;

import java.io.BufferedWriter;
import java.io.File;
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
                                p.getCategoria();
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
        return productos;
    }

}
