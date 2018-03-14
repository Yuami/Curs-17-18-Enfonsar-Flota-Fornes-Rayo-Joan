package HundeLaFlota;

public class Jugador {

    private Tablero tablero;
    private Barco[] barcos;
    private int id;

    public Jugador(Barco[] barcos, int id, Tablero tablero){
        this.tablero = tablero;
        this.barcos = barcos;
        this.id = id;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Barco[] getBarcos() {
        return barcos;
    }

    public void setBarcos(Barco[] barcos) {
        this.barcos = barcos;
    }

    public boolean compTodosHundidos(){
        return Barco.compTodosHundidos(barcos);
    }

}
