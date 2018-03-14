package HundeLaFlota;

public enum TiposBarco {
    CUIRASSATS("CUIRASSATS",4), DESTRUCTOR("DESTRUCTOR", 3), FRAGATES("FRAGATES",2), SUBMARINS("SUBMARINS",1);

    private int longitud;
    private String nombre;

    TiposBarco(String nombre, int longitud){
        this.longitud = longitud;
        this.nombre = nombre;
    }

    public int getLongitud() {
        return longitud;
    }

    public String getNombre() {
        return nombre;
    }
}
