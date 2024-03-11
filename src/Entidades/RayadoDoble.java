package Entidades;

import Logica.Tablero;

public class RayadoDoble extends Caramelo {

	public RayadoDoble(Tablero tablero, int f, int c, int col, String path_img) {
		super(tablero, f, c, col, path_img + "potenciadores/rayadoDoble/");
	}

	public boolean machea_doble_con(Entidad e){
		return machea(e);
	}
	
	public boolean match_doble_con(Caramelo c){
		return c.get_color() == color;
	}
		
  	public void detonar(){
    	entidad_grafica.notificar_detonacion();
    	
		mi_tablero.eliminar_fila_y_columna(this);
		mi_tablero.eliminar_caramelo(this);
		chequear_adyacentes();
  	}

  	public String get_puntaje() {
	  	return "scoreRayadoDoble";
	}
 
}
