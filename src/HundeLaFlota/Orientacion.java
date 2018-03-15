package HundeLaFlota;

public enum Orientacion {
    ARRIBA('▲',new int[]{-1,0}),
    ABAJO('▼',new int[]{1,0}),
    IZQUIERDA('◄',new int[]{0,-1}),
    DERECHA('►',new int[]{0,1});

    char direccion;
    int[] orientacion;

    Orientacion(char direccion, int[] orientacion) {
        this.direccion = direccion;
        this.orientacion = orientacion;
    }

    public char getDireccion() {
        return direccion;
    }

    public int[] getOrientacion(){
        return orientacion;
    }
}
