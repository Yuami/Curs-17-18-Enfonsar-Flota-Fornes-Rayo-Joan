package HundeLaFlota;

public enum TiposBarco {
    CUATRO(4), TRES(3), DOS(2), UNO(1);

    private int longitud;

    TiposBarco(int longitud){
        this.longitud = longitud;
    }

    public int getLongitud() {
        return longitud;
    }
}
