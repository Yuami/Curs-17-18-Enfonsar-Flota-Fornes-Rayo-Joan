package HundeLaFlota;

public enum Ficha {
    Agua('X'), Base('~'), Barco(''), Tocado('▽'), Hundido('☠');


    private char ficha;

    Ficha(char ficha){
        this.ficha = ficha;
    }

    public char getChar() {
        return ficha;
    }
}
