package HundeLaFlota;

import java.util.Scanner;

public class Juego {

    private static Jugador[] jugadors;
    private static int turno;
    private static Tablero tablero;
    private static Tablero tableroEnemigo;

    public static void iniciarJuego(int numJugadors, int filas, int columnas, int cCuirassats, int cDestructors, int cFragates, int cSubmarins) {
        int[] numTipoBarcos = {cCuirassats, cDestructors, cFragates, cSubmarins};
        iniciarJuego(numJugadors, filas, columnas, numTipoBarcos);
    }

    public static void iniciarJuego(int numJugadors, int filas, int columnas, int[] numTipoBarcos) {
        if (numTipoBarcos.length != 4) {
            System.out.println("Faltan o sobran parametros en el numero de tipos de Barcos");
            return;
        }
        turno = 0;
        Juego.jugadors = new Jugador[numJugadors];

        for (int i = 0; i < Juego.jugadors.length; i++) {
            inicializarJugador(filas, columnas, i, numTipoBarcos);
        }

        tablero = jugadors[turno].getTablero();
        tableroEnemigo = jugadors[getTurnoEnemigo()].getTablero();

        empezar();
    }

    public static void imprimirTableros(boolean seguirPartida) {
        System.out.print("Ficha" + Ficha.Base + ": " + Ficha.Base.getChar() + " ");
        System.out.println("Ficha" + Ficha.Barco + ": " + Ficha.Barco.getChar() + " ");

        System.out.println("Tipos de tocados: ");
        System.out.print("Ficha" + Ficha.Tocado + ": " + Ficha.Tocado.getChar() + " ");
        System.out.println("Ficha" + Ficha.Hundido + ": " + Ficha.Hundido.getChar() + " ");

        System.out.println("JUGADOR " + jugadors[turno].getId());
        System.out.println("TU TABLERO");
        System.out.println(tablero);

        System.out.println("JUGADOR " + jugadors[getTurnoEnemigo()].getId());
        System.out.println("TABLERO ENEMIGO");

        if (!seguirPartida) {
            System.out.println(tableroEnemigo);
            System.out.println("HA GANADO EL JUGADOR " + jugadors[turno].getId());
        } else {
            System.out.println(tableroEnemigo.tableroEscondido());
        }

    }

    public static void imprimirNotificacion(Casilla cas) {
        System.out.println("----");
        System.out.println(cas.getFicha());
        System.out.println("----");
    }

    public static void empezar() {
        boolean seguirPartida = true;
        while (seguirPartida) {
            imprimirTableros(seguirPartida);
            Casilla cas = pedirCasilla(tableroEnemigo);

            cas.setTocado();

            if (cas.isContieneBarco()) {
                tocarImposibles(cas);

                if (compBarcoHundido(cas)) {
                    tocarImposiblesHundido(cas);

                    if (jugadors[getTurnoEnemigo()].compTodosHundidos()) seguirPartida = false;
                }
            } else cambiarTurno();

            imprimirNotificacion(cas);
        }
        imprimirTableros(seguirPartida);
    }

    public static void tocarImposibles(Casilla cas) {
        int[][] imposibles = {
                {-1, -1}, {-1, +1},
                {+1, -1}, {+1, +1}
        };

        tocarImposibleFin(cas, imposibles);
    }

    public static void tocarImposiblesHundido(Casilla cas) {
        Barco[] barcos = jugadors[getTurnoEnemigo()].getBarcos();


        int[][] imposibles = {
                {-1, 0}, {0, -1},
                {+1, 0}, {0, +1}
        };

        for (Barco barco : barcos) {
            if (barco.getId() == cas.getBarcoID()) {
                Casilla posicion;
                for (int i = 0; i < 2; i++) {
                    if (i == 0)
                        posicion = barco.getPosicion()[0];
                    else
                        posicion = barco.getPosicion()[barco.getPosicion().length - 1];

                        tocarImposibleFin(posicion, imposibles);
                }

                break;
            }
        }
    }

    private static void tocarImposibleFin(Casilla cas, int[][] imposibles){
        for (int[] imposible : imposibles) {
            int f = cas.getX() + imposible[0];
            int c = cas.getY() + imposible[1];

            if (!Tablero.limite.tablero(f, c))
                tableroEnemigo.getTablero()[f][c].setTocado();
        }
    }

    public static boolean compBarcoHundido(Casilla cas) {
        Barco[] barcos = jugadors[getTurnoEnemigo()].getBarcos();
        for (Barco barco : barcos) {
            if (barco.getId() == cas.getBarcoID()) {
                return barco.compHundido();
            }
        }
        return false;
    }

    public static Casilla pedirCasilla(Tablero tablero) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Turno para el/la JUGADOR/A " + (turno + 1));
            System.out.print("Elige fila: ");
            int f = scan.nextInt();
            System.out.print("Elige columna: ");
            int c = scan.nextInt();

            if (Tablero.limite.tablero(f, c) || compTocada(tablero.getTablero()[f][c])) {
                imprimirTableros(true);
                System.out.println("No puedes usar esa casilla");
                continue;
            }

            return tablero.getTablero()[f][c];
        }
    }

    public static boolean compTocada(Casilla c) {
        return c.isTocado();
    }

    public static void cambiarTurno() {
        turno = ++turno % jugadors.length;
        tablero = jugadors[turno].getTablero();
        tableroEnemigo = jugadors[getTurnoEnemigo()].getTablero();

    }

    public static int getTurnoEnemigo() {
        return (turno + 1) % jugadors.length;
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

    public static void inicializarJugador(int f, int c, int jugador, int[] numTipoBarcos) {
        boolean noGustar = true;

        while (noGustar) {
            Tablero tablero = inicializarTablero(f, c);
            Barco[] barcos = inicializarBarcosJugador(jugador, numTipoBarcos, tablero);
            Juego.jugadors[jugador] = new Jugador(barcos, jugador + 1, tablero);
            noGustar = noGustar(tablero);
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

    public static Barco[] inicializarBarcosJugador(int jugador, int[] numTipoBarcos, Tablero tablero) {

        TiposBarco[] tipos = {TiposBarco.CUIRASSATS, TiposBarco.DESTRUCTOR, TiposBarco.FRAGATES, TiposBarco.SUBMARINS};

        int totalBarcos = numTipoBarcos[0] + numTipoBarcos[1] + numTipoBarcos[2] + numTipoBarcos[3];
        Barco[] barcos = new Barco[totalBarcos];
        Casilla inicial;
        Barco barc;

        int cont = 0;

        for (int i = 0; i < numTipoBarcos.length; i++) {

            for (int j = 0; j < numTipoBarcos[i]; j++) {

                if (tipos[i] == TiposBarco.SUBMARINS) {
                    Casilla[] cas = new Casilla[1];
                    cas[0] = pedirPosicionInicial(jugador, tipos[i], tablero);
                    cas[0].setBarcoID(cont);
                    barc = new Barco(tipos[i], cas, cont);
                } else {
                    inicial = pedirPosicionInicial(jugador, tipos[i], tablero);
                    barc = pedirOrientacion(inicial, tipos[i], tablero, cont);
                }

                barcos[cont++] = barc;
            }
        }
        return barcos;
    }

    public static Casilla pedirPosicionInicial(int jugador, TiposBarco tiposBarco, Tablero tablero) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("JUGADOR/A " + (jugador + 1));
            System.out.println(tablero);

            System.out.println("Donde quieres poner el/la " + tiposBarco.getNombre());
            System.out.print("Elige la fila: ");
            int f = scan.nextInt();
            System.out.print("Elige la columna: ");
            int c = scan.nextInt();

            if (!compPosicionInicial(tablero.getTablero()[f][c])) {
                System.out.println("No puede utilizar esa casilla!");
                continue;
            }

            return tablero.getTablero()[f][c];
        }
    }

    public static Barco pedirOrientacion(Casilla posicionInicial, TiposBarco tiposBarco, Tablero tablero, int id) {
        Scanner scan = new Scanner(System.in);

        Orientacion[] orientaciones = {
                Orientacion.ABAJO, Orientacion.ARRIBA,
                Orientacion.DERECHA, Orientacion.IZQUIERDA
        };

        while (true) {

            for (int i = 0; i < orientaciones.length; i++) {
                System.out.println(i + ") " + orientaciones[i].getDireccion());
            }
            System.out.println("Que orientacion quieres poner el barco?");
            int ori = scan.nextInt();

            if (!compDisponible(ori, posicionInicial, tiposBarco, tablero)) {
                System.out.println(tablero);
                System.out.println("No se puede utilizar esa orientacion");
                System.out.println("Recuerda no salir del tablero y los barcos no pueden tocarse!");
                continue;
            }

            return crearBarco(posicionInicial, tiposBarco, ori, tablero, id);
        }
    }

    public static boolean compDisponible(int ori, Casilla posicionInicial, TiposBarco tiposBarco, Tablero tablero) {
        int[][] orientaciones = {
                {1, 0}, {-1, 0},
                {0, 1}, {0, -1}, {0, 0}
        };

        int[] orientacion = orientaciones[ori];

        int dF = orientacion[0];
        int dC = orientacion[1];

        for (int i = 0; i < tiposBarco.getLongitud(); i++) {
            int F = posicionInicial.getX() + i * dF;
            int C = posicionInicial.getY() + i * dC;

            if (Tablero.limite.tablero(F, C) || !tablero.getTablero()[F][C].isUtilizable())
                return false;
        }
        return true;
    }

    public static boolean compPosicionInicial(Casilla c) {
        return c.isUtilizable();
    }

    public static Barco crearBarco(Casilla cInicial, TiposBarco tipoBarco, int ori, Tablero tablero, int id) {
        Casilla[] tempBarco = new Casilla[tipoBarco.getLongitud()];

        int[][] orientaciones = {
                {1, 0}, {-1, 0},
                {0, 1}, {0, -1}, {0, 0}
        };

        int[] orientacion = orientaciones[ori];

        if (tipoBarco.getLongitud() == TiposBarco.SUBMARINS.getLongitud()) {
            tempBarco[0] = cInicial;
            cInicial.setFicha(Ficha.Barco);
            return new Barco(tipoBarco, tempBarco, id);
        }

        for (int i = 0; i < tipoBarco.getLongitud(); i++) {
            int f = cInicial.getX() + i * orientacion[0];
            int c = cInicial.getY() + i * orientacion[1];

            tempBarco[i] = tablero.getTablero()[f][c];
            tablero.getTablero()[f][c].setBarcoID(id);
        }

        Barco barco = new Barco(tipoBarco, tempBarco, id);

        setNoUtilizable(barco, tablero);

        return barco;
    }

    public static void setNoUtilizable(Barco barco, Tablero tablero) {
        Casilla[][] tableroCasillas = tablero.getTablero();
        Casilla[] posicion = barco.getPosicion();

        int[][] direcciones = {
                {-1, -1}, {-1, +0}, {-1, +1},
                {+0, -1}, {+0, +1},
                {+1, -1}, {+1, +0}, {+1, +1}
        };

        for (Casilla cas : posicion) {
            cas.setUtilizable(false);

            for (int[] direccion : direcciones) {
                int dirF = direccion[0];
                int dirC = direccion[1];

                int f = cas.getX() + dirF;
                int c = cas.getY() + dirC;

                if (Tablero.limite.tablero(f, c)) {
                    continue;
                }

                tableroCasillas[f][c].setUtilizable(false);
            }

        }
    }

}
