
package Simulador;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author xenia
 */
public class Lista {
    Cliente cliente;
    private String nombre;
    private int total_productos;
    ArrayList<Item> lista = new ArrayList();
    
    public Lista(String nombre){
        this.nombre = nombre;
    }
    
    public void asignarCliente(Cliente C){
        cliente = C;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTotal_productos() {
        return total_productos;
    }
    
    public void agregarItem(Producto p){
        Item I = new Item(p,1);
//        I.asignarCarrito( this );
        lista.add(I);
        I.asignarProducto(p);
        total_productos += 1;
    }
    
    public void agregarAcarrito(){
        
        for(Item productos: lista){
            cliente.getCarro().agregarProducto(productos.getProducto(),1);
        }
    }
}
