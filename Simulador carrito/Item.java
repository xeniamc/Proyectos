package Simulador;

/**
 *
 * @author xenia
 */
public class Item {
    
    private int cant_solicitada;
    private Producto _producto;
    private Carrito _carro;
    
    public Item(Producto p, int cantidad){
        _producto = p;
        cant_solicitada = cantidad;
    }
    
    public void asignarCarrito(Carrito c){
        _carro = c;
    }
    
    public void asignarProducto(Producto p){
        _producto = p;
    }

    public int getCant_solicitada() {
        return cant_solicitada;
    }

    public void setCant_solicitada(int cant_solicitada) {
        this.cant_solicitada = cant_solicitada;
    }

    public Producto getProducto() {
        return _producto;
    }

    public void setProducto(Producto _producto) {
        this._producto = _producto;
    }

    public Carrito getCarro() {
        return _carro;
    }

    public void setCarro(Carrito _carro) {
        this._carro = _carro;
    }
    
    
    
}
