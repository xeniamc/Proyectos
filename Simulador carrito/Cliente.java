package Simulador;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author xenia
 */
public class Cliente {
    
    private final String nombre;
    private Carrito carro;
    ArrayList<Lista> listas = new ArrayList();
            
    
    public Cliente(String nombre){
        this.nombre=nombre;
    }
    
    public void asignarCarro(Carrito C){
         carro=C; 
         C.asignarCliente(this);
    }
    
    public void asignarListas(Lista L){
         listas.add(L);
    }
    
    public String getNombre(){
        return nombre;
    }

    public Carrito getCarro() {
        return carro;
    }

    public ArrayList<Lista> getListas() {
        return listas;
    }
    
      
    public void verListas(){
        int index_lista, op_boton = 0;
        String[] botones ={"Borrar lista","Agregar al carrito", "Regresar"};
        String[] listas_botones = new String[(listas.size()+1)]; //Agrego el boton "regresar"
        for (int i=0; i < listas.size(); i++){ //Obtiene el nombre de los items y los pone en un arreglo
            listas_botones[i]=listas.get(i).getNombre();//productos es un arreglo para usarse como botones.
        }
        listas_botones[listas.size()] = "Regresar";
        
        do{
            index_lista = JOptionPane.showOptionDialog(null,"Elige una lista para modificarlo", "Listas de compra", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, listas_botones,listas_botones[0]);
        }while(index_lista == -1);
        
        if(index_lista != listas.size()){
            //Si selecciona una lista se muestran sus productos.
            for(int i =0; i < listas.get(index_lista).lista.size(); i++){
                if(i == (listas.get(index_lista).lista.size()-1)){
                    op_boton = JOptionPane.showOptionDialog(null, "\nPRODUCTO: "+listas.get(index_lista).lista.get(i).getProducto().getNombre(),"Opciones de Lista",JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, null,botones,botones[0]);
                }else{
                    JOptionPane.showMessageDialog(null, "\nPRODUCTO: "+listas.get(index_lista).lista.get(i).getProducto().getNombre(), "Productos de la lista", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            //SWITCH
            switch(op_boton){
                case 0:
                    listas.remove(index_lista);
                    break;
                case 1:
                    listas.get(index_lista).agregarAcarrito();
                    break;
                case 2:
                    break;
                default:
                    break;
            }
        }
    }
}
