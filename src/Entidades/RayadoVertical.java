package Entidades;

import Logica.Tablero;

public class RayadoVertical  extends Caramelo{
	
	public RayadoVertical(Tablero tablero, int f, int c, int col, String path_img) {
		super(tablero, f, c, col, path_img + "potenciadores/rayadoVertical/");
	}
	
	public void detonar() {
		entidad_grafica.notificar_detonacion();
		mi_tablero.eliminar_caramelo(this);
		mi_tablero.eliminar_columna(this);
		chequear_adyacentes();
	}
	
	public String get_puntaje() {
		return "scoreRayadoVertical";
	}
}
