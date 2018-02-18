package HundeLaFlota;

public class Main {

    public static void main(String[] args) {
        Casilla c1, c2, c3;
        c1 = new Casilla(0,0);
        c2 = new Casilla(0,1);
        c3 = new Casilla(0,2);

        Casilla[] naves = {c1,c2,c3};

        Barco barco = new Barco(TiposBarco.CUATRO,naves);
        for (int i = 0; i < naves.length + 1; i++)
        if (barco.compHundido()){
            System.out.println("hundido");
        } else {
            naves[i].setTocada(true);
            System.out.println("tocado");
        }
    }
}
