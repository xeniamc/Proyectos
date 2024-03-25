
package cajeroatmp1;

public class Cliente {
    private int id;
    private String nombre;
    private String direccion;
    private float saldo;
    //Constructor
    public Cliente(){
        this.saldo=1500;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public float getSaldo() {
        return saldo;
    }
    
    public void setSaldo(float saldo){
        this.saldo=saldo;
    }
    
    public void depositar(float cantidad){
        this.saldo = this.saldo + cantidad;
    }
    
    public boolean validarRetiro(float cretiro){
        return (cretiro <= saldo) && (cretiro > 0);
    }
    
    public void retirar(float retiro){
        this.saldo = this.saldo - retiro;
    }
    
    public boolean validarSaldo(){
        return this.saldo<1000;
    }
    
    public void cobrarComision(){
        this.saldo = (float) (this.saldo - 30.5);
    }
    

}
