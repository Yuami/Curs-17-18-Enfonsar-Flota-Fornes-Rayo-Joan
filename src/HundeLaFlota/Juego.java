package HundeLaFlota;

import java.util.Scanner;

public class Juego {

    private static Jugador[] jugadors;

    public static void iniciarJuego(int numJugadors, int filas, int columnas, int cCuirassats, int cDestructors, int cFragates, int cSubmarins) {
        int[] numTipoBarcos = {cCuirassats, cDestructors, cFragates, cSubmarins};
        iniciarJuego(numJugadors, filas, columnas, numTipoBarcos);
    }

    public static void iniciarJuego(int numJugadors, int filas, int columnas, int[] numTipoBarcos) {
        if (numTipoBarcos.length != 4) {
            System.out.println("Faltan o sobran parametros en el numero de tipos de Barcos");
            return;
        }

        Juego.jugadors = new Jugador[numJugadors];

        for (int i = 0; i < Juego.jugadors.length; i++) {
            boolean noGustar = true;

            while (noGustar) {
                Tablero tablero = inicializarTablero(filas, columnas);
                Barco[] barcos = inicializarBarcosJugador(i + 1, numTipoBarcos, tablero);
                Juego.jugadors[i] = new Jugador(barcos, i + 1, tablero);
                noGustar = noGustar(tablero);
            }

        }
    }

    public static boolean noGustar(Tablero tablero) {
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println(tablero);
            System.out.println("Te gusta este tablero?");
            System.out.println("1) Sí");
            System.out.println("2) No");
            System.out.print("> ");
            int n = scan.nextInt();
            if (n == 1) {
                return false;
            }
            if (n == 2) {
                return true;
            }
        }
    }

    public static Tablero inicializarTablero(int filas, int columnas) {
        Casilla[][] tableroDeCasillas = new Casilla[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tableroDeCasillas[i][j] = new Casilla(i, j);
            }
        }

        return new Tablero(tableroDeCasillas);
    }


    public static Barco[] inicializarBarcosJugador(int jugador, int[] numTipoBarcos, Tablero tablero) {

        TiposBarco[] tipos = {TiposBarco.CUIRASSATS, TiposBarco.DESTRUCTOR, TiposBarco.FRAGATES, TiposBarco.SUBMARINS};

        int totalBarcos = numTipoBarcos[0] + numTipoBarcos[1] + numTipoBarcos[2] + numTipoBarcos[3];
        Barco[] barcos = new Barco[totalBarcos];

        int cont = 0;

        for (int i = 0; i < numTipoBarcos.length; i++) {
            for (int j = 0; j < numTipoBarcos[i]; j++) {
                Casilla inicial = pedirPosicionInicial(jugador, tipos[i], tablero);
                barcos[cont++] = pedirOrientacion(inicial, tipos[i], tablero);
            }
        }
        return barcos;
    }

    public static Casilla pedirPosicionInicial(int jugador, TiposBarco tiposBarco, Tablero tablero) {
        Scanner scan = new Scanner(System.in);
        System.out.println("JUGADOR " + jugador);
        System.out.println(tablero);

        System.out.println("Donde quieres poner el/la " + tiposBarco.getNombre());
        System.out.print("Elige la fila: ");
        int f = scan.nextInt();
        System.out.print("Elige la columna: ");
        int c = scan.nextInt();

        return tablero.getTablero()[f][c];
    }

    public static Barco pedirOrientacion(Casilla posicionInicial, TiposBarco tiposBarco, Tablero tablero) {
        Scanner scan = new Scanner(System.in);

        if (tiposBarco.getLongitud() == TiposBarco.SUBMARINS.getLongitud()) {
            return crearBarco(posicionInicial, tiposBarco, null, tablero);
        }

        int[][] direcciones = {
                {1, 0}, {-1, 0},
                {0, 1}, {0, -1}
        };

        Orientacion[] orientaciones = {
                Orientacion.ABAJO, Orientacion.ARRIBA,
                Orientacion.DERECHA, Orientacion.IZQUIERDA
        };

        while (true) {

            for (int i = 0; i < orientaciones.length; i++) {
                System.out.println(i + ") " + orientaciones[i].getDireccion());
            }
            System.out.println("Que orientacion quieres poner el barco?");
            int i = scan.nextInt();

            int[] direccion = direcciones[i];

            int dF = direccion[0];
            int dC = direccion[1];

            int compF = posicionInicial.getX() + (tiposBarco.getLongitud() - 1) * dF;
            int compC = posicionInicial.getY() + (tiposBarco.getLongitud() - 1) * dC;

            if (Tablero.limite.tablero(compF, compC)) {
                System.out.println("No se puede colocar en esa orientacion!");
                continue;
            }

            return crearBarco(posicionInicial, tiposBarco, direcciones[i], tablero);
        }
    }

    public static Barco crearBarco(Casilla cInicial, TiposBarco tipoBarco, int[] orientacion, Tablero tablero) {
        Casilla[] barco = new Casilla[tipoBarco.getLongitud()];

        if (tipoBarco.getLongitud() == TiposBarco.SUBMARINS.getLongitud()) {
            barco[0] = cInicial;
            cInicial.setFicha(Ficha.Barco);
            return new Barco(tipoBarco, barco);
        }

        for (int i = 0; i < tipoBarco.getLongitud(); i++) {
            int f = cInicial.getX() + (tipoBarco.getLongitud() - i - 1) * orientacion[0];
            int c = cInicial.getY() + (tipoBarco.getLongitud() - i - 1) * orientacion[1];

            barco[i] = tablero.getTablero()[f][c];
            tablero.getTablero()[f][c].setFicha(Ficha.Barco);
        }

        return new Barco(tipoBarco, barco);
    }

}
