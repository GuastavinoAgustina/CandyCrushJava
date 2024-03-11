package Entidades;

import GUI.CountdownTimer;
import GUI.EntidadGrafica;
import Logica.Tablero;

public class Bomba extends Entidad{

	protected CountdownTimer tiempo;

    public Bomba(Tablero tablero,int f, int c, int col, String path_img) {
		super(tablero,f, c, col,  path_img + "bomba/");
		tiempo = new CountdownTimer(50);
		tiempo.iniciarContador();
	}
	public void set_entidad_grafica(EntidadGrafica e) {
		entidad_grafica = e;
		entidad_grafica.asociar_timer(tiempo);
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
    public boolean puede_recibir(Bomba b){
        return false;
    }
	public boolean puede_recibir(Gelatina g){
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
		entidad_grafica.detener_timer(tiempo);
		mi_tablero.detono_glaseado();
		mi_tablero.eliminar_bomba(this);
	}
	public void eliminar_adyacente(){
		this.detonar();
	}
	public void caida(){
		entidad_grafica.actualizar_timer(tiempo);
	}
	public void desactivar_timer(){
		tiempo.reiniciar_timer();
	}
}
