package HundeLaFlota;

public class Casilla {
    private int x;
    private int y;
    private Ficha ficha;
    private boolean utilizable;

    private boolean tocado;
    private boolean contieneBarco;
    private int barcoID;

    public Casilla(int x, int y) {
        this.x = x;
        this.y = y;
        ficha = Ficha.Base;

        contieneBarco = false;
        tocado = false;
        utilizable = true;
    }

    public boolean isContieneBarco() {
        return contieneBarco;
    }

    public void setContieneBarco(boolean contieneBarco) {
        this.contieneBarco = contieneBarco;
    }

    public int getBarcoID() {
        return barcoID;
    }

    public void setBarcoID(int barcoID) {
        this.barcoID = barcoID;
    }

    public boolean isUtilizable() {
        return utilizable;
    }

    public void setUtilizable(boolean utilizable) {
        this.utilizable = utilizable;
    }

    public void setTocado() {
        if (tocado != true) {
            tocado = true;
            if (contieneBarco) {
                ficha = Ficha.Tocado;
            } else {
                ficha = Ficha.Agua;
            }
        }
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

    public boolean isTocado() {
        return tocado;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    @Override
    public String toString() {
        return "" + ficha.getChar();
    }
}
