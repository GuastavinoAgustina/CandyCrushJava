package Entidades;
import GUI.EntidadGrafica;
import Logica.Tablero;
public class Gelatina extends Entidad {
    protected Entidad contenido;

	public Gelatina(Caramelo cont,Tablero tablero,int f, int c, String path_img){
        super(tablero,f,c,1,  path_img + "gelatina/");
        contenido = cont;
    }
    public boolean es_posible_intercambiar(Entidad e) {
		return e.puede_recibir(this);
	}
	public void set_entidad_grafica(EntidadGrafica e) {
		entidad_grafica = e;
		mi_tablero.generar_entidad_grafica(contenido);
	}
	public boolean enfocar() {
		return contenido.enfocar();
	}
	@Override
	public void desenfocar() {
		contenido.desenfocar();
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
	public Entidad get_contenido(){
		return contenido;
	}
	public void set_contenido(Entidad e){
		contenido = e;
	}
	public void intercambiar_posicion(int nf, int nc) {
		contenido.intercambiar_posicion(nf, nc);
	}
	 public boolean machea(Entidad e) {
		return e.match_con(this);
	}
	public boolean match_con(Caramelo c) {
		boolean toReturn = false;
		if(contenido.get_color() == c.get_color()) {
			toReturn = true;
		}
		return toReturn;
	}
    public boolean match_con(Glaseado g) {
		return false;
	}
	public boolean match_con(Gelatina g){
		boolean toReturn = false;
		if(contenido.get_color() == g.get_color()){
			toReturn = true;
		}
		return toReturn;
	}
	public boolean match_con(Vacio v){
		return false;
	}
	public boolean match_con(Bomba b){
		return false;
	}
	public int get_color(){
		return contenido.get_color();
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
    public void detonar(){
		entidad_grafica.notificar_detonacion();
		mi_tablero.eliminar_gelatina(this);
		contenido.detonar();
    }
	public void caida(){
	}
}
