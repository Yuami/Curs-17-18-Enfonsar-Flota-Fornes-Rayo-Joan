package HundeLaFlota;

public class Jugador {

    private Barco[] barcos;

    Jugador(Barco[] barcos){
        this.barcos = barcos;
    }

    public Barco[] getBarcos() {
        return barcos;
    }

    public void setBarcos(Barco[] barcos) {
        this.barcos = barcos;
    }
}
