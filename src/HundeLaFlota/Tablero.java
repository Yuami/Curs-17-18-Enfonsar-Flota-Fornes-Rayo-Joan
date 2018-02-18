package HundeLaFlota;

public class Tablero {
    private Casilla[][] tablero;
    private static int filas;
    private static int columnas;

    public Tablero(Casilla[][] tablero){
        this.tablero = tablero;

        filas = tablero.length;
        columnas = tablero[0].length;

        for (int f = 0; f < filas; f++){
            for (int c = 0; c < columnas; c++){

            }
        }
    }

    public Casilla[][] getTablero() {
        return tablero;
    }

    public void setTablero(Casilla[][] tablero) {
        this.tablero = tablero;
    }

    public static int getFilas() {
        return filas;
    }

    public static int getColumnas() {
        return columnas;
    }

    public static void setFilas(int filas) {
        Tablero.filas = filas;
    }

    public static void setColumnas(int columnas) {
        Tablero.columnas = columnas;
    }

    @Override
    public String toString() {
        String tableroToString = "";

        for (int f = 0; f < filas; f++){
            for (int c = 0; c < columnas; c++){
                tableroToString += tablero[f][c] + " ";
            }
            tableroToString += "\n";
        }
        return tableroToString;
    }
}
