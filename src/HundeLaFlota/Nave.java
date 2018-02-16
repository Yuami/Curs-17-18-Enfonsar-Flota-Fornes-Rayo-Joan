package HundeLaFlota;

public class Nave {

    private int longitudNave;
    private Casilla[] nave;

    public Nave(Casilla[] nave){
        this.nave = nave;
        longitudNave = nave.length;
    }

    public boolean hundido(){
        int cont = 0;
        for (Casilla posicion: nave){
            if (posicion.isTocada()){
                cont++;
                continue;
            }
            break;
        }
        return cont == longitudNave;
    }
}
