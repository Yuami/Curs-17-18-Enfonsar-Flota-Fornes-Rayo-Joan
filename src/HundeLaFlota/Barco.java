package HundeLaFlota;

public class Barco {

    private Casilla[] posicion;
    private TiposBarco tipo;
    private int longitudBarco;
    private boolean hundido;
    private int id;

    public Barco(TiposBarco tipo, Casilla[] posicion, int id) {

        if (tipo.getLongitud() != posicion.length) {
            System.out.println("Ha habido un error en la creacion del barco faltan posicion para asignar este barco!");
            return;
        }

        for (int i = 0; i< posicion.length; i++){
            posicion[i].setFicha(Ficha.Barco);
            posicion[i].setContieneBarco(true);
        }
        this.id = id;
        this.tipo = tipo;
        this.posicion = posicion;
        longitudBarco = this.posicion.length;
        hundido = false;
    }

    public Barco(TiposBarco tipo, Casilla posicion, int id){
        posicion.setFicha(Ficha.Barco);
        posicion.setContieneBarco(true);

        Casilla[] cas = new Casilla[1];
        cas[0] = posicion;
        this.id = id;
        this.tipo = tipo;
        this.posicion = cas;
        longitudBarco = this.posicion.length;
        hundido = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        for (Casilla posicio : posicion) {
            if (posicio.isTocado()) {
                cont++;
                posicio.setFicha(Ficha.Tocado);
            }
        }
        if (cont == longitudBarco){
            hundido = true;
            setBarcoHundido();
        }

        return hundido;
    }
}
