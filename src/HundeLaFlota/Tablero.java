package HundeLaFlota;

public class Tablero {
    private Casilla[][] tablero;
    private static int filas;
    private static int columnas;

    public Tablero(Casilla[][] tablero) {
        this.tablero = tablero;
        filas = tablero.length;
        columnas = tablero[0].length;
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

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                tableroToString += "\t" + tablero[f][c] + " ";
            }
            tableroToString += "\t" + f + " \n";
        }

        for (int c = 0; c < columnas; c++) {
            tableroToString += "\t" + c + " ";
        }

        tableroToString += "\n";

        return tableroToString;
    }

    public interface Limites {
        public boolean tablero(int compF, int compC);
    }

    public static Limites limite = (int compF, int compC) -> (compF < 0 || compC < 0 || compF > filas - 1 || compC > columnas - 1);
}
