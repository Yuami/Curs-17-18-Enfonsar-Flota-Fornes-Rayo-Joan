package HundeLaFlota;

public class Casilla {
    private boolean tocada;
    private int x;
    private int y;
    private Ficha ficha;
    private boolean utilizable;

    public Casilla(int x, int y) {
        this.x = x;
        this.y = y;
        tocada = false;
        ficha = Ficha.Basica;
        utilizable = true;
    }

    public boolean isUtilizable() {
        return utilizable;
    }

    public void setUtilizable(boolean utilizable) {
        this.utilizable = utilizable;
    }

    public void setTocada(boolean tocada) {
        this.tocada = tocada;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Ficha getFicha() {
        return ficha;
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

    public void setTocada() {
        if (this.tocada != true) {
            this.tocada = true;
            ficha = Ficha.Tocado;
        }
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    @Override
    public String toString() {
        return "" + getFicha().getChar();
    }
}
