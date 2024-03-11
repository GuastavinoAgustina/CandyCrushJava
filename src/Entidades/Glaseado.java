package Entidades;

import Logica.Tablero;

/**
 * Modela el comportamiento de los Glaseados.
 * @author FJoaquin (federico.joaquin@cs.uns.edu.ar)
 *
 */
public class Glaseado extends Entidad{

	public Glaseado(Tablero tablero,int f, int c, int col, String path_img) {
		super(tablero,f, c, col,  path_img + "glaseados/");
	}

	public boolean es_posible_intercambiar(Entidad e) {
		return false;
	}

	public boolean puede_recibir(Caramelo c) {
		return false;
	}

	public boolean puede_recibir(Glaseado g) {
		return false;
	}

	public boolean puede_recibir(Vacio v){
		return false;
	}

	public boolean puede_recibir(Gelatina g){
		return false;
	}

	public boolean puede_recibir(Bomba b){
        return false;
    }

	public boolean machea(Entidad e) {
		return e.match_con(this);
	}

	public boolean match_con(Caramelo c) {
		return false;
	}

	public boolean match_con(Glaseado g) {
		return false;
	}

	public boolean match_con(Vacio v){
		return false;
	}
	
	public boolean match_con(Gelatina g){
		return false;
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
		mi_tablero.detono_glaseado();
		mi_tablero.eliminar_glaseado(this);
	}
	
	public void eliminar_adyacente(){
		this.detonar();
	}
}
