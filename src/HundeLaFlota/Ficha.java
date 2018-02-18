package HundeLaFlota;

public enum Ficha {
    Tocado('X'), Basica('◉'), Barco(''), BarcoTocado('▽'), Hundido('☠');


    private char fichas;

    Ficha(char ficha){
        this.fichas = fichas;
    }

    public char getFichas() {
        return fichas;
    }
}
