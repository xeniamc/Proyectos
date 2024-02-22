package Simulador;

import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author xenia
 */
public class main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Arreglo de Productos//
        ArrayList<Producto> Catalogo = new ArrayList();
        Catalogo.add(new Producto("Estereo",5359.99,"Color negro"));
        Catalogo.add(new Producto("Audifonos ",300.50,"Color azul"));
        Catalogo.add(new Producto("Reloj",650.66,"Color rojo"));
        Catalogo.add(new Producto("Rasuradora",876.20,"Color morado"));
        Catalogo.add(new Producto("Camisa",526.59,"Color blanco"));
        Catalogo.add(new Producto("Bicicleta",2359.99,"Color negro"));
        Catalogo.add(new Producto("Guitarra",3000.50,"Color azul"));
        Catalogo.add(new Producto("Vestido",650.66,"Color rojo"));
        Catalogo.add(new Producto("Abrigo",876.20,"Color morado"));
        Catalogo.add(new Producto("Mochila",526.59,"Color blanco"));
        
        //Arreglos de Clientes//
        ArrayList<Cliente>Clientes = new ArrayList();
        Cliente c1 = new Cliente("Ana");
        Cliente c2 = new Cliente("Erick");
        Cliente c3 = new Cliente("Edher");
        Clientes.add(c1);
        Clientes.get(0).asignarCarro(new Carrito());
        Clientes.add(c2);
        Clientes.get(1).asignarCarro(new Carrito());
        Clientes.add(c3);
        Clientes.get(2).asignarCarro(new Carrito());
        Lista l1 = new Lista("Super");
        l1.agregarItem(Catalogo.get(2));
        l1.agregarItem(Catalogo.get(5));
        l1.asignarCliente(Clientes.get(0));
        
        Lista l2 = new Lista("Baño");
        l2.agregarItem(Catalogo.get(6));
        l2.agregarItem(Catalogo.get(4));
        l2.asignarCliente(Clientes.get(0));
        
        Lista l3 = new Lista("Cocina");
        l3.agregarItem(Catalogo.get(7));
        l3.agregarItem(Catalogo.get(9));
        l3.asignarCliente(Clientes.get(1));
        
        Lista l4 = new Lista("Despensa");
        l4.agregarItem(Catalogo.get(0));
        l4.agregarItem(Catalogo.get(1));
        l4.asignarCliente(Clientes.get(1));
        
        Lista l5 = new Lista("Utiles escolares");
        l5.agregarItem(Catalogo.get(3));
        l5.agregarItem(Catalogo.get(8));
        l5.asignarCliente(Clientes.get(2));
        
        Lista l6 = new Lista("Limpieza");
        l6.agregarItem(Catalogo.get(6));
        l6.agregarItem(Catalogo.get(1));
        l6.asignarCliente(Clientes.get(2));
        
        Clientes.get(0).asignarListas(l1);
        Clientes.get(0).asignarListas(l2);
        Clientes.get(1).asignarListas(l3);
        Clientes.get(1).asignarListas(l4);
        Clientes.get(2).asignarListas(l5);
        Clientes.get(2).asignarListas(l6);
        
        
        /*//PRUEBA DE PRODUCTO
        c1.getCarro().agregarProducto(new Producto("LapTop",6532.68,"Color azul"), 1);
        c1.getCarro().agregarProducto(new Producto("Celular",6556,"Color blanco"), 1);
        c1.getCarro().agregarProducto(new Producto("X-Box",5060.60,"Color blanco"), 1);
        
        //PRUEBA DE PRODUCTO 2//
        c2.getCarro().agregarProducto(new Producto ("Televisión",20000,"40"),1);
        c2.getCarro().agregarProducto(new Producto ("Blue-Ray",4000,"40"),1);
        
        //PRUEBA DE PRODUCTO 3//
        c3.getCarro().agregarProducto(new Producto ("Motoneta",14000,"40"),1);
        c3.getCarro().agregarProducto(new Producto ("Videojuego",8000,"40"),1);
        c3.getCarro().agregarProducto(new Producto ("Asistente",4000,"40"),1);
        */
        
        int opcCliente;
        String pregunta;
        int opcSubMenu;
        String pregunta2;
        String entrada;
        
        //MENU PRINCIPAL
        JOptionPane.showMessageDialog(null, "BIENVENIDO A 'ESCOM-SHOP' ");
        int op;
        do{
            do{
                entrada = JOptionPane.showInputDialog("\tMENU PRINCIPAL \n\n1."+c1.getNombre()+"    -   ("+c1.getCarro().getTotal_productos()+")\n\n2."+c2.getNombre()+"    -   ("+c2.getCarro().getTotal_productos()+")\n\n3."+c3.getNombre()+"     -   ("+c3.getCarro().getTotal_productos()+")\n\n4. Salir.\n\nSelecciona una opcion:");
            }while(entrada==null || entrada.equals(""));
            op=Integer.parseInt(entrada);

            switch(op){
                //PARA EL CLIENTE 1//
                case 1:
                    JOptionPane.showMessageDialog(null, c1.getNombre());
                    do{
                        do{
                            pregunta2= JOptionPane.showInputDialog(null, "Carrito de Compras:\n\n1. Ver carrito de compras. \n\n2. Agregar al carrito. \n\n3. Vaciar carrito.\n\n4.Hacer pedido.\n\n5.Listas de compra\n\n6.Log Out\n\n");
                        }while(pregunta2 == null || pregunta2.equals(""));
                        opcSubMenu= Integer.parseInt(pregunta2);
                        //SUB-MENU DEL CLIENTE 1//
                        switch(opcSubMenu){
                            case 1:
                                //Ver, quitar, cambiar cantidad,regresar
                                int index_regresar; // carro.size() nunca será -2
                                do{
                                    if(c1.getCarro().carritoVacio()){
                                        JOptionPane.showMessageDialog(null, "\nNo tiene productos en su carrito.","Aviso", JOptionPane.WARNING_MESSAGE);
                                        index_regresar = c1.getCarro().carro.size();
                                    }
                                    else{
                                        index_regresar = c1.getCarro().verCarrito();
                                    }
                                }while(index_regresar != c1.getCarro().carro.size());
                                break;
                            case 2:
                                //Mostrar catalogo, agregar producto, especificar cantidad, regresar
                                int index_producto, _cantidad;
                                String cantidad;

                                String[] catalogo = new String[Catalogo.size()];

                                for (int i=0; i < Catalogo.size(); i++){
                                    catalogo[i]=Catalogo.get(i).getNombre();
                                }
                                do{
                                    index_producto = JOptionPane.showOptionDialog(null,"Selecciona un producto para ver mas informacion", "CATALOGO", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, catalogo,catalogo[0]);

                                cantidad = JOptionPane.showInputDialog("CANTIDAD:      ");
                                _cantidad = Integer.parseInt(cantidad);
                                c1.getCarro().agregarProducto(Catalogo.get(index_producto), _cantidad);
                                }while(index_producto == -1);
                                break;
                            case 3://Vaciar Carrito//
                                c1.getCarro().vaciarCarrito();              
                                break;
                                                                                            
                            case 4:
                                //Descripcion Precio unitario  Cantidad  Subtotal, Total =, Ticket, Actualizar el stock de los productos.
                                if(c1.getCarro().carritoVacio()){
                                    JOptionPane.showMessageDialog(null, "\nNo tiene productos en su carrito.","Aviso", JOptionPane.WARNING_MESSAGE);
                                }
                                else{
                                    c1.getCarro().hacerPedido();
                                    c1.getCarro().vaciarCarrito();
                                }
                                break;
                            case 5:
                                c1.verListas();
                                break;
                            case 6:
                                JOptionPane.showMessageDialog(null, "Se ha cerrado su sesion.","Cerrar Sesion",JOptionPane.INFORMATION_MESSAGE);
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Incorrecto. Intente de nuevo.");
                                break;
                        }
                    }while (opcSubMenu != 6);
                    break;
                     

                //PARA EL CLIENTE 2//              
                case 2:
                    JOptionPane.showMessageDialog(null, c2.getNombre());
                    do{
                        do{
                            pregunta2= JOptionPane.showInputDialog(null, "Carrito de Compras:\n\n1. Ver carrito de compras. \n\n2. Agregar al carrito. \n\n3. Vaciar carrito.\n\n4.Hacer pedido.\n\n5.Listas de compra\n\n6.Log Out\n\n");
                        }while(pregunta2 == null || pregunta2.equals(""));
                        opcSubMenu= Integer.parseInt(pregunta2);
                        //SUB-MENU DEL CLIENTE 2//
                        switch(opcSubMenu){
                            case 1:
                                //Ver, quitar, cambiar cantidad,regresar
                                int index_regresar; // carro.size() nunca será -2
                                do{
                                    if(c2.getCarro().carritoVacio()){
                                        JOptionPane.showMessageDialog(null, "\nNo tiene productos en su carrito.","Aviso", JOptionPane.WARNING_MESSAGE);
                                        index_regresar = c2.getCarro().carro.size();
                                    }
                                    else{
                                        index_regresar = c2.getCarro().verCarrito();
                                    }
                                }while(index_regresar != c2.getCarro().carro.size());
                                break;
                            case 2:
                                //Mostrar catalogo, agregar producto, especificar cantidad, regresar
                                int index_producto, _cantidad;
                                String cantidad;

                                String[] catalogo = new String[Catalogo.size()];

                                for (int i=0; i < Catalogo.size(); i++){
                                    catalogo[i]=Catalogo.get(i).getNombre();
                                }
                                do{
                                    index_producto = JOptionPane.showOptionDialog(null,"Selecciona un producto para ver mas informacion", "CATALOGO", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, catalogo,catalogo[0]);

                                cantidad = JOptionPane.showInputDialog("CANTIDAD:      ");
                                _cantidad = Integer.parseInt(cantidad);
                                c2.getCarro().agregarProducto(Catalogo.get(index_producto), _cantidad);
                                }while(index_producto == -1);
                                break;
                            case 3:
                                //Vaciar Carro
                                c2.getCarro().vaciarCarrito();
                                break;
                            case 4:
                                //Descripcion Precio unitario  Cantidad  Subtotal, Total =, Ticket, Actualizar el stock de los productos.  
                                if(c2.getCarro().carritoVacio()){
                                    JOptionPane.showMessageDialog(null, "\nNo tiene productos en su carrito.","Aviso", JOptionPane.WARNING_MESSAGE);
                                }else{
                                    c2.getCarro().hacerPedido();
                                    c2.getCarro().vaciarCarrito();
                                }
                                break;
                            case 5:
                                c2.verListas();
                                break;
                            case 6:
                                JOptionPane.showMessageDialog(null, "Se ha cerrado su sesion.","Cerrar Sesion",JOptionPane.INFORMATION_MESSAGE);
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Incorrecto. Intente de nuevo.");
                                break;
                        }
                    }while (opcSubMenu != 6);
                    break;
                    
                //PARA EL CLIENTE 3//
                case 3:
                    JOptionPane.showMessageDialog(null, c3.getNombre());
                    do{
                        do{
                            pregunta2= JOptionPane.showInputDialog(null, "Carrito de Compras:\n\n1. Ver carrito de compras. \n\n2. Agregar al carrito. \n\n3. Vaciar carrito.\n\n4.Hacer pedido.\n\n5.Listas de compra\n\n6.Log Out\n\n");
                        }while(pregunta2 == null || pregunta2.equals(""));
                        opcSubMenu= Integer.parseInt(pregunta2);
                        //SUB-MENU DEL CLIENTE 2//
                        switch(opcSubMenu){
                            case 1:
                                //Ver, quitar, cambiar cantidad,regresar
                                int index_regresar; // carro.size() nunca será -2
                                do{
                                    if(c3.getCarro().carritoVacio()){
                                        JOptionPane.showMessageDialog(null, "\nNo tiene productos en su carrito.","Aviso", JOptionPane.WARNING_MESSAGE);
                                        index_regresar = c3.getCarro().carro.size();
                                    }
                                    else{
                                        index_regresar = c3.getCarro().verCarrito();
                                    }
                                }while(index_regresar != c3.getCarro().carro.size());
                                break;
                            case 2:
                                //Mostrar catalogo, agregar producto, especificar cantidad, regresar
                                int index_producto, _cantidad;
                                String cantidad;

                                String[] catalogo = new String[Catalogo.size()];

                                for (int i=0; i < Catalogo.size(); i++){
                                    catalogo[i]=Catalogo.get(i).getNombre();
                                }
                                do{
                                    index_producto = JOptionPane.showOptionDialog(null,"Selecciona un producto para ver mas informacion", "CATALOGO", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, catalogo,catalogo[0]);

                                cantidad = JOptionPane.showInputDialog("CANTIDAD:      ");
                                _cantidad = Integer.parseInt(cantidad);
                                c3.getCarro().agregarProducto(Catalogo.get(index_producto), _cantidad);
                                }while(index_producto == -1);
                                break;
                            case 3:
                                //Vaciar Carro
                                c3.getCarro().vaciarCarrito();
                                break;
                            case 4:
                                //Descripcion Precio unitario  Cantidad  Subtotal, Total =, Ticket, Actualizar el stock de los productos.
                                if(c3.getCarro().carritoVacio()){
                                    JOptionPane.showMessageDialog(null, "\nNo tiene productos en su carrito.","Aviso", JOptionPane.WARNING_MESSAGE);
                                }else{
                                    c3.getCarro().hacerPedido();
                                    c3.getCarro().vaciarCarrito();
                                }
                                break;
                            case 5:
                                c3.verListas();
                                break;
                            case 6:
                                JOptionPane.showMessageDialog(null, "Se ha cerrado su sesion.","Cerrar Sesion",JOptionPane.INFORMATION_MESSAGE);
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Incorrecto. Intente de nuevo.");
                                break;
                        }
                    }while(opcSubMenu != 6);
                    break;
                //SALIDA DEL MENU PRINCIPAL//
                case 4:
                    JOptionPane.showMessageDialog(null, "Vuelva pronto, Guap@");
                    System.exit(0);
                    break;                
                default:
                    JOptionPane.showMessageDialog(null, "Incorrecto. Intente de nuevo.");
                    break;

                    }
        }while(op != 5);
        
        
        /*for (Producto prod : Catalogo) {
            JOptionPane.showMessageDialog(null, "PRODUCTOS "+prod.getNombre(), "CATALOGO", JOptionPane.PLAIN_MESSAGE);
        }*/
        
    }
    
}
