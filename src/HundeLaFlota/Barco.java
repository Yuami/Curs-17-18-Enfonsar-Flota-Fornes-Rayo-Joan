package HundeLaFlota;

public class Barco {

    private Casilla[] posicion;
    private TiposBarco tipo;
    private int longitudBarco;
    private boolean hundido;

    public Barco(TiposBarco tipo, Casilla[] posicion) {

        if (tipo.getLongitud() != posicion.length) {
            System.out.println("Ha habido un error en la creacion del barco faltan posicion para asignar este barco!");
            return;
        }

        this.tipo = tipo;
        this.posicion = posicion;
        longitudBarco = this.posicion.length;
        hundido = false;
    }

    public TiposBarco getTipo() {
        return tipo;
    }

    public int getLongitudBarco() {
        return longitudBarco;
    }

    public Casilla[] getPosicion() {
        return posicion;
    }

    public boolean isHundido() {
        return hundido;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }

    public void setPosicion(Casilla[] barco) {
        this.posicion = barco;
    }

    private void setBarcoHundido() {
        for (Casilla cas : posicion) {
            cas.setFicha(Ficha.Hundido);
        }
    }

    public boolean compHundido() {
        int cont = 0;
        for (Casilla posicion : posicion) {
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

    public static boolean compTodosHundidos(Barco[] barcos) {
        int cont = 0;
        for (Barco barco : barcos) {
            if (barco.compHundido())
                cont++;
        }
        return cont == barcos.length - 1;
    }
}
