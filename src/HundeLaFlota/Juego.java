package HundeLaFlota;

public class Juego {

    private static Jugador[] jugadors;

    public static void pruebaJuego() {

    }

    private void inicializarBarcos() {
        Barco[] barcos = new Barco[10];


//        for (int i = 0; i < 1; i++)
//            barcos[i] = new Barco(TiposBarco.CUATRO);
//        for (int i = 1; i < 3; i++)
//            barcos[i] = new Barco(TiposBarco.TRES);
//        for (int i = 3; i < 7; i++)
//            barcos[i] = new Barco(TiposBarco.DOS);
//        for (int i = 7; i < 11; i++)
//            barcos[i] = new Barco(TiposBarco.UNO);

        for (int i = 0; i < 2; i++)
            jugadors[i] = new Jugador(barcos);
    }
}
