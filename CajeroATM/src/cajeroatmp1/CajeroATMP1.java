
package cajeroatmp1;

public class CajeroATMP1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String n;
        int op;
        
        Cliente c1 = new Cliente();
        Interfaz gui = new Interfaz();
        
        gui.saveData();
        gui.showData(); 
        
        do{
            op = gui.showMenu();
            
            switch(op){
                case 1:
                    if(c1.validarSaldo()){
                        c1.cobrarComision();
                        gui.avisoComision();
                        System.out.println("Saldo actual: "+c1.getSaldo());
                        gui.verSaldo();
                    }
                    else{
                        System.out.println("Saldo actual: "+c1.getSaldo());
                        gui.verSaldo();
                    }
                    break;
                case 2:
                    float cantidad = gui.depositarCantidad();
                    c1.depositar(cantidad);
                    break;
                case 3:
                    float retiro = gui.retirarCantidad();
                    while(!c1.validarRetiro(retiro)){
                        gui.errorRetiro();
                        retiro = gui.retirarCantidad();
                    }
                    c1.retirar(retiro);
                    if(c1.validarSaldo()){
                        gui.avisoComision();
                        c1.cobrarComision();
                    }
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }while(op!=4);
    }    
}
