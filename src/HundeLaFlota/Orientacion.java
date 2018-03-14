package HundeLaFlota;

public enum Orientacion {
    ARRIBA('▲'), ABAJO('▼'), IZQUIERDA('◄'), DERECHA('►');

    char direccion;

    Orientacion(char direccion) {
        this.direccion = direccion;
    }

    public char getDireccion() {
        return direccion;
    }
}
