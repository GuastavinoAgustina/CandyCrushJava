package Entidades;

import Logica.Tablero;

public class Vacio extends Entidad{
    public Vacio(Tablero tablero, int f, int c) {
		super(tablero,f, c, 1, "/ImagenesCaramelos/vacio/");
    }

    public boolean es_posible_intercambiar(Entidad e) {
		return e.puede_recibir(this);
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
	
	public boolean match_con(Glaseado g) {
		return false;
	}
	
	public boolean match_con(Caramelo c) {
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
	}
	
	public void caida(){
		while(fila-1>=0){
			mi_tablero.aplicar_intercambio(fila,columna,fila-1, columna);
		}
		mi_tablero.generar_caramelo(mi_tablero.get_entidad(0,columna));
		
	}
}
