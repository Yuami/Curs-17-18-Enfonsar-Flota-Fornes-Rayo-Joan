package HundeLaFlota;

public class Barco {

    private Casilla[] casillas;
    private TiposBarco tipo;
    private int longitudBarco;
    private boolean hundido;

    Barco(TiposBarco tipo, Casilla[] posicion) {

        if (tipo.getLongitud() != posicion.length) {
            System.out.println("Ha habido un error en la creacion del barco faltan casillas para asignar este barco!");
            return;
        }

        this.tipo = tipo;
        this.casillas = posicion;
        longitudBarco = this.casillas.length;
        hundido = false;
    }

    public TiposBarco getTipo() {
        return tipo;
    }

    public int getLongitudBarco() {
        return longitudBarco;
    }

    public Casilla[] getCasillas() {
        return casillas;
    }

    public boolean isHundido() {
        return hundido;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }

    public void setPosicion(Casilla[] barco) {
        this.casillas = barco;
    }

    private void setBarcoHundido() {
        for (Casilla cas : casillas) {
            cas.setFicha(Ficha.Hundido);
        }
    }

    public boolean compHundido() {
        int cont = 0;
        for (Casilla posicion : casillas) {
            if (posicion.isTocada()) {
                cont++;
                posicion.setFicha(Ficha.BarcoTocado);
            }
        }
        hundido = cont == longitudBarco;
        if (hundido) {
            setBarcoHundido();
        }
        return hundido;
    }
}
