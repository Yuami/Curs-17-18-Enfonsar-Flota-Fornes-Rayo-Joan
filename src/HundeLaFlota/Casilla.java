package HundeLaFlota;

public class Casilla {
    private boolean tocada;
    private int x;
    private int y;

    public Casilla(int x, int y){
        this.x = x;
        this.y = y;
        tocada = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isTocada() {
        return tocada;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setTocada(boolean tocada) {
        this.tocada = tocada;
    }
}
