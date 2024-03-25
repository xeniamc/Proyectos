
package cajeroatmp1;

import javax.swing.JOptionPane;

/**
 *
 * @author xenia
 */
public class Interfaz {
    Cliente c;

    public Interfaz() {
        this.c = new Cliente();
    }
    
    public void saveData(){
        c.setId(Integer.parseInt(JOptionPane.showInputDialog("ID:")));
        c.setNombre(JOptionPane.showInputDialog("Nombre:"));
        c.setDireccion(JOptionPane.showInputDialog("Direcccion:"));
    }
    
    public void showData(){
        JOptionPane.showMessageDialog(null,"\nID: "+c.getId()+"\nNombre: "+c.getNombre()+"\nDireccion: "+c.getDireccion(), "Datos", JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showMessageDialog(null, "Bienvenido "+c.getNombre(), "Cajero ATM", JOptionPane.PLAIN_MESSAGE);
    }
    
    public int showMenu(){
        String entrada;
        int op;
        do{
            entrada = JOptionPane.showInputDialog("\tMENU\n1. Ver saldo.\n2. Depositar una cantidad.\n3. Retirar una cantidad.\n4. Salir.\nSelecciona una opcion:");
        }while(entrada==null || entrada.equals(""));
        op=Integer.parseInt(entrada);
        return op;
    }
    
    public void verSaldo(){
        JOptionPane.showMessageDialog(null, "Su saldo es: "+c.getSaldo(), "Consulta de saldo", JOptionPane.PLAIN_MESSAGE);
    }
    
    public float depositarCantidad(){
        String can = JOptionPane.showInputDialog("Cantidad a depositar:");
        float cantidad = Float.parseFloat(can);
        return cantidad;
    }
    
    public float retirarCantidad(){
        String r = JOptionPane.showInputDialog("Monto del retiro:");
        float retiro = Float.parseFloat(r);
        return retiro;
    }
    
    public void errorRetiro(){
        JOptionPane.showMessageDialog(null, "No es posible retirar esa cantidad. \nIntentelo nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void avisoComision(){
        JOptionPane.showMessageDialog(null, "Se le ha cobrado una comision de $30.50", "Cobro de comision", JOptionPane.WARNING_MESSAGE);
    }
    
}
