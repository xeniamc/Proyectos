package Simulador;

import javax.swing.JOptionPane;

/**
 *
 * @author xenia
 */
public class Producto {
    private int cant_disponibles;
    private String nombre;
    private double precio;
    private String descripcion;
    
    public Producto(String nombre, double precio, String descripcion){
        cant_disponibles = 10;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public int getCant_disponibles() {
        return cant_disponibles;
    }
    
    public void setCant_disponibles(int cant_disponibles) {
        this.cant_disponibles = cant_disponibles;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public void actualizarInventario(int cantidad, int quitar_sumar){ // quitar = 0, sumar = 1
        if(quitar_sumar == 0){
            cant_disponibles += cantidad;
        }else{
            cant_disponibles = 3 - cantidad;
        }
    }
    
    public boolean verificarDisponibilidad(int can_solicitados){
        return ( can_solicitados <= cant_disponibles  && can_solicitados > 0);
    }
    
    /*public void agregarProducto(Producto p, int cantidad){
        carro.add(p);
        total_productos += cantidad;
    }
    
    public void quitarProducto(int iter_producto){
        carro.remove(iter_producto);
    }
    
    public void cambiarCantidad(int iter_producto, int cantidad){
        carro.get(iter_producto).setCant_disponibles(cantidad);
    }*/

    
}
