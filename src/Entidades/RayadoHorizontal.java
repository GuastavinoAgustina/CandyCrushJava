package Entidades;

import Logica.Tablero;

public class RayadoHorizontal extends Caramelo{
	
	public RayadoHorizontal(Tablero tablero, int f, int c, int col, String path_img) {
		super(tablero, f, c, col, path_img + "potenciadores/rayadoHorizontal/");
	}
	
	public void detonar() {
		entidad_grafica.notificar_detonacion();
		mi_tablero.eliminar_caramelo(this);
		mi_tablero.eliminar_fila(this);
		chequear_adyacentes();
	}
	public String get_puntaje() {
		return "scoreRayadoHorizontal";
	}
}
