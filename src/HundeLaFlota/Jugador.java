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

    public boolean compTodosTocados(){
        for (Barco barco: barcos){
            if (!barco.isHundido()) return false;
        }

        return true;
    }


    public static void main(String[] args) {
        Barco[] barcoPrueba = new Barco[2];
        Casilla c1 = null;
        Casilla c2 = null;
        for (int f = 0; f < 2; f++){
            for (int c = 0; c < 2; c++){
                c1 = new Casilla(f,c);
                c2 = new Casilla(f+1,c+1);
            }
            Casilla[] casillas = {c1,c2};
            barcoPrueba[f] = new Barco(TiposBarco.DOS, casillas);
        }
        Jugador jugador = new Jugador(barcoPrueba);

        for (Barco barco : jugador.getBarcos()){
          Casilla[] casillas = barco.getCasillas();
          for (Casilla casilla : casillas){
              casilla.setTocada();
          }
          barco.compHundido();
        }

        System.out.println(jugador.compTodosTocados());
    }

}
