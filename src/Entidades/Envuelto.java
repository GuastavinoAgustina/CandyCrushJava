package Entidades;

import Logica.Tablero;

public class Envuelto extends Caramelo {
	
	public Envuelto(Tablero tablero, int f, int c, int col, String path_img) {
		super(tablero, f, c, col, path_img + "potenciadores/envuelto/");
	}

	public void detonar() {
		entidad_grafica.notificar_detonacion();
		mi_tablero.eliminar_caramelo(this);
		mi_tablero.eliminar_cuadrado(this);
	}
	public String get_puntaje() {
		return "scoreEnvuelto";
	}
}
