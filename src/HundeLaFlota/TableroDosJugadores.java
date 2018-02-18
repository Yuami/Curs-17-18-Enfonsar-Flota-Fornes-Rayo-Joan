package HundeLaFlota;

public class TableroDosJugadores {

    private Jugador jugadorPropio;
    private Tablero tableroPropio;
    private Jugador jugadorEnemigo;
    private Tablero tableroEnemigo;

    private Tablero tableroEnemigoShow;

    public TableroDosJugadores(Jugador jugadorPropio,Tablero tableroPropio, Jugador jugadorEnemigo, Tablero tableroEnemigo){
        this.jugadorPropio = jugadorPropio;
        this.tableroPropio = tableroPropio;

        this.jugadorEnemigo = jugadorEnemigo;
        this.tableroEnemigo = tableroEnemigo;

        Casilla[][] tablero = new Casilla[Tablero.getFilas()][Tablero.getColumnas()];
        tableroEnemigoShow = new Tablero(tablero);
    }

    public String tableroEnemigoToString(){
        int fil = Tablero.getFilas();
        int col = Tablero.getColumnas();

        String tableroToString = "";
        Casilla[][] tablero = tableroEnemigo.getTablero();





        return "";
    }

    @Override
    public String toString() {
        return "Tablero propio \n" +
                tableroPropio +
                "----\n" +
                "Tablero enemigo" +
                tableroEnemigoToString();
    }
}
