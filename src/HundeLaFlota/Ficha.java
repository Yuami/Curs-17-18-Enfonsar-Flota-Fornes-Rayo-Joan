package HundeLaFlota;

public enum Ficha {
    Tocado('X'), Basica('~'), Barco(''), BarcoTocado('▽'), Hundido('☠');


    private char ficha;

    Ficha(char ficha){
        this.ficha = ficha;
    }

    public char getChar() {
        return ficha;
    }
}
