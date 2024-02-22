package Simulador;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author xenia
 */
public class Carrito{
    private int total_productos;
    private Cliente cliente;
    ArrayList<Item> carro = new ArrayList();
    String[] opciones_mod ={"Quitar","Cambiar cantidad", "Regresar"};
    
    public void asignarCliente(Cliente C){
        cliente = C;
    }
    public void agregarProducto(Producto p, int cantidad){
        Item I = new Item(p,cantidad);
        I.asignarCarrito( this );
        carro.add(I);
        I.asignarProducto(p);
        total_productos += cantidad;
    }

    public int getTotal_productos() {
        return total_productos;
    }

    public void setTotal_productos(int total_productos) {
        this.total_productos = total_productos;
    }
    
    public boolean carritoVacio(){
        return total_productos == 0;
    }
    
    public int verCarrito(){
        
        int index_item, opcModificar;
            String[] productos = new String[(carro.size()+1)]; //Agrego el boton "regresar"
            for (int i=0; i < carro.size(); i++){ //Obtiene el nombre de los items y los pone en un arreglo
                productos[i]=carro.get(i).getProducto().getNombre();//productos es un arreglo para usarse como botones.
            }
            productos[carro.size()] = "Regresar";
            
            do{
            index_item = JOptionPane.showOptionDialog(null,"Elige un producto para modificarlo", "Productos", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, productos,productos[0]);
            }while(index_item == -1);
            
            if(index_item != carro.size()){ //Si selecciona un producto se muestra el siguiente submenu.
                opcModificar = JOptionPane.showOptionDialog(null, "\nPRODUCTO SELECCIONADO: "+carro.get(index_item).getProducto().getNombre()+"\n\nPRECIO: $"+carro.get(index_item).getProducto().getPrecio()+"\n\nCANTIDAD: "+carro.get(index_item).getCant_solicitada()+
                        "\n\nDESCRIPCION: "+carro.get(index_item).getProducto().getDescripcion()+"\n\nElige una opcion para el producto ","Modificar producto",JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null,opciones_mod,opciones_mod[0]);

                switch(opcModificar){
                    case 0:
                        //Actualizar inventario
                        //carro.get(index_producto).actualizarInventario(carro.get(index_producto).getCant_solicitada(),0);
                        //Actualizar carro
                        total_productos -= carro.get(index_item).getCant_solicitada();
                        //QUITAR PRODUCTO
                        carro.remove(index_item);
                        JOptionPane.showMessageDialog(null, "Se quito el producto con exito", "Quitar producto", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 1:
                        //CAMBIAR CANTIDAD
                        String cantidad_nueva;
                        do{
                            cantidad_nueva = JOptionPane.showInputDialog("CANTIDAD ACTUAL:"+carro.get(index_item).getCant_solicitada()+"\n\nCual es la cantidad nueva de productos:\n");
                        }while(cantidad_nueva == null || cantidad_nueva.equals(""));
                        int cantidad = Integer.parseInt(cantidad_nueva);
                        //Verificar disponibilidad de la nueva cantidad
                        if(carro.get(index_item).getProducto().verificarDisponibilidad(cantidad)== true){
                            //Actualizar inventario
                            //carro.get(index_producto).actualizarInventario(cantidad,1);
                            //Actualizar carro
                            total_productos = (total_productos - carro.get(index_item).getCant_solicitada()) + cantidad;
                            //Guardar cantidad solicitada nueva
                            carro.get(index_item).setCant_solicitada(cantidad);
                            JOptionPane.showMessageDialog(null, "Su cambio quedo guardado\n\nPRODUCTO: "+carro.get(index_item).getProducto().getNombre()+"\n\nCANTIDAD: "+carro.get(index_item).getCant_solicitada(), "Cambiar cantidad", JOptionPane.PLAIN_MESSAGE);

                        }else{
                            JOptionPane.showMessageDialog(null, "No hay suficientes productos en existencia.");
                        }
                        break;
                    case 2:
                        break;
                }
                return 0; 
            }else{
                return index_item;
            }
    }
    
    public void vaciarCarrito(){
        carro.clear();
        JOptionPane.showMessageDialog(null, "Su Carrito se ha vaciado con éxito", "Carrito vacio",JOptionPane.INFORMATION_MESSAGE);
        total_productos=0;
    }
    
    public void actualizarInventario(Producto p, int cantidad){
        int cantidad_nueva = p.getCant_disponibles()-cantidad;
        p.setCant_disponibles(cantidad_nueva);
    }
    
    public void hacerPedido(){
        double subtotal = 0,total = 0;
        for(Item items: carro){
            subtotal = (int) ((items.getCant_solicitada())*(items.getProducto().getPrecio()));
            JOptionPane.showMessageDialog(null, "Producto: "+items.getProducto().getNombre()+"\n\nCantidad: "+items.getCant_solicitada()+"\n\nPrecio unitario: $"+items.getProducto().getPrecio()+"\n\nSubtotal: $"+subtotal, "Productos en el carrito", JOptionPane.INFORMATION_MESSAGE);
            total = total + subtotal;
        }
        
        JOptionPane.showMessageDialog(null, "Cantidad total de productos: "+total_productos+"\n\nTotal: $"+total, "Pedido", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, "Su pedido se ha completado con exito", "Pedido realizado", JOptionPane.INFORMATION_MESSAGE);
        
    }
    
    
        
}
  
