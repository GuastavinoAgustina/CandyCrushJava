package Entidades;

import Logica.ColorEntidades;
import Logica.Tablero;

/**
 * Modela el comportamiento de los Caramelos.
 */
public class Caramelo extends Entidad {
	//constructor usado por las clases potenciadores
	public Caramelo(Tablero tablero, int f, int c, int col, String path_img) {
		super(tablero, f, c, col, path_img + "caramelos/");
	}
	public boolean es_posible_intercambiar(Entidad e) {
		return e.puede_recibir(this);
	}
	public boolean puede_recibir(Caramelo c) {
		return true;
	}
	public boolean puede_recibir(Glaseado g) {
		return false;
	}
	public boolean puede_recibir(Vacio v){
		return false;
	}
	public boolean puede_recibir(Gelatina g){
		return true;
	}
	public boolean puede_recibir(Bomba b){
        return false;
    }
	public boolean machea(Entidad e) {
		return e.match_con(this);
	}
	public boolean match_con(Caramelo c) {
		boolean toReturn = false;
		if(color == c.get_color()) {
			toReturn = true;
		}
		return toReturn;
	}
	public boolean match_con(Glaseado g) {
		return false;
	}
	public boolean match_con(Vacio v){
		return false;
	}
	public boolean match_con(Gelatina g){
		boolean toReturn = false;
		if(color == g.get_color()) {
			toReturn = true;
		}
		return toReturn;
	}
	public boolean match_con(Bomba b){
		return false;
	}
	public boolean machea_doble_con(Entidad e){
		return e.match_doble_con(this);
	}
	public boolean match_doble_con(Caramelo c){
		return false;
	}
	public boolean match_doble_con(Glaseado g){
		return false;
	}
	public boolean match_doble_con(Vacio v){
		return false;
	}
	public boolean match_doble_con(Gelatina g){
		return false;
	}
	public boolean match_doble_con(Bomba b){
		return false;
	}
	public void detonar() {
		entidad_grafica.notificar_detonacion();
		mi_tablero.eliminar_caramelo(this);
		chequear_adyacentes();
	}
	public String get_puntaje() {
		return "score"+ColorEntidades.obtenerNombreColor(get_color());
	}
}
