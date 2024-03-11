package Logica;

import Entidades.*;
import Fabricas.FabricaEntidades;
import Estrategias.*;

/**
 * Modela el tablero de la aplicación. Mantiene control sobre las entidades, y provee acceso a ellas.
 */
public class Tablero {
	
	protected Juego mi_juego;
	protected Entidad [][] entidades;
	protected int filas;
	protected int columnas;
	protected int pos_f_jugador;
	protected int pos_c_jugador;
	protected FabricaEntidades fabrica;
	protected Estrategia estrategia;
	
	public Tablero(Juego j, FabricaEntidades f) {
		mi_juego = j;
		filas = 0;
		columnas = 0;
		fabrica = f;
	}
	
	public int get_filas() {
		return filas;
	}
	
	public int get_columnas() {
		return columnas;
	}
	
	public Entidad get_entidad(int f, int c) {
		return entidades[f][c];
	}
	public void set_estrategia(Estrategia e){
		estrategia = e;
	}
	public Estrategia get_estrategia() {
		return estrategia;
	}
	public void resetar_tablero(int f, int c) {
		filas = f;
		columnas = c;
		pos_f_jugador = 0;
		pos_c_jugador = 0;
		entidades = new Entidad[f][c];
	}
	
	public void descativar_bombas(){
		for(int i=0;i<filas;i++){
			for(int j=0;j<columnas;j++){
				entidades[i][j].get_contenido().desactivar_timer();;
			}
		}
	}

	public void fijar_jugador(int f, int c) {
		if (entidades[f][c].get_contenido().enfocar()) {
			entidades[pos_f_jugador][pos_c_jugador].get_contenido().desenfocar();
			pos_f_jugador = f;
			pos_c_jugador = c;
		}
	}
	
	public void mover_jugador(int d) {
		switch(d) {
			case Juego.ABAJO:{
				mover_jugador_auxiliar(pos_f_jugador + 1, pos_c_jugador);
				break;
			}
			case Juego.ARRIBA:{
				mover_jugador_auxiliar(pos_f_jugador - 1, pos_c_jugador);
				break;
			}
			case Juego.IZQUIERDA:{
				mover_jugador_auxiliar(pos_f_jugador, pos_c_jugador - 1);
				break;
			}
			case Juego.DERECHA:{
				mover_jugador_auxiliar(pos_f_jugador, pos_c_jugador + 1);
				break;
			}
		}
	}

	private void mover_jugador_auxiliar(int nf, int nc) {
		if ( (0 <= nf) && (nf < filas) && (0 <= nc) && (nc < columnas)) {
			if (entidades[nf][nc].enfocar()) {
				entidades[pos_f_jugador][pos_c_jugador].desenfocar();
				pos_f_jugador = nf;
				pos_c_jugador = nc;
			}
		}
	}

	public void intercambiar(int d) {
		switch(d) {
			case Juego.ABAJO:{
				intercambiar_auxiliar(pos_f_jugador + 1, pos_c_jugador);
				break;
			}
			case Juego.ARRIBA:{
				intercambiar_auxiliar(pos_f_jugador - 1, pos_c_jugador);
				break;
			}
			case Juego.IZQUIERDA:{
				intercambiar_auxiliar(pos_f_jugador, pos_c_jugador - 1);
				break;
			}
			case Juego.DERECHA:{
				intercambiar_auxiliar(pos_f_jugador, pos_c_jugador + 1);
				break;
			}
		}
	}

	private void intercambiar_auxiliar(int nf, int nc) {
		int af = pos_f_jugador;
		int ac = pos_c_jugador;
		
		if ( en_rango(nf, nc) ) {	
			if (entidades[af][ac].es_posible_intercambiar( entidades[nf][nc] )) {
				// Anima el posible intercambio de entidades
				aplicar_intercambio(af, ac, nf, nc);
				boolean hubo_match_three = matchThree(entidades[nf][nc],entidades[af][ac]);
				boolean hubo_match_rayado_doble = false;
				if (!hubo_match_three){
					hubo_match_rayado_doble = match_rayado_doble(entidades[nf][nc],entidades[af][ac]);
				}

				// Si el intercambio provoca un match de 3 o mas entidades, chequea las combinaciones y detona lo necesario
				// De lo contrario, retrotae el intercambio anterior que no fue válido
				if(!hubo_match_three && !hubo_match_rayado_doble) {
					aplicar_intercambio(nf, nc, af, ac);
					pos_f_jugador = af;
					pos_c_jugador = ac;
				}	
				else {
					mi_juego.decrementa_movimientos();
				}
			}
		}
	}

	public void aplicar_intercambio(int entidad1f, int entidad1c, int entidad2f, int entidad2c) {
		Entidad entidad1 = entidades[entidad1f][entidad1c];
		Entidad entidad2 = entidades[entidad2f][entidad2c];

		entidad1.intercambiar_posicion(entidad2f, entidad2c);
		entidad2.intercambiar_posicion(entidad1f, entidad1c);

		Entidad contenidoENe1 = entidad1.get_contenido();
		Entidad contenidoENe2 = entidad2.get_contenido();
		entidad1.set_contenido(contenidoENe2);
		entidad2.set_contenido(contenidoENe1);
		
		pos_f_jugador = entidad1f;
		pos_c_jugador = entidad1c;
	}
	
	public boolean en_rango(int nf, int nc){
		return ((0 <= nf) && (nf < filas) && (0 <= nc) && (nc < columnas));
	}

	public int verificarColor(){
		return mi_juego.mi_nivel.getColor_caramelos_para_destruir();
	}

	public void agregar_entidad(Entidad e) {
		entidades[e.get_fila()][e.get_columna()] = e;
	}

	public void generar_entidad_grafica(Entidad cont){
		mi_juego.crear_entidad_grafica(cont);
	}

	public Entidad generar_caramelo(Entidad e){
		Entidad carameloNuevo = generar_entidad_aleatoria(0, e.get_columna());
		entidades[e.get_fila()][e.get_columna()].detonar();
		entidades[e.get_fila()][e.get_columna()].set_contenido(carameloNuevo);
		mi_juego.crear_entidad_grafica(carameloNuevo);
		return carameloNuevo;
	}

	private Entidad generar_entidad_aleatoria(int f, int c){
		int valorEntero = (int) (Math.floor(Math.random()*(6-1+1)+1));
		Entidad nueva = fabrica.crear_caramelo(this, f, c, valorEntero);
		return nueva;
	}

	public void eliminar_caramelo(Caramelo c){
		if(c.get_color() == verificarColor()){
			detono_caramelo();
		}
		Entidad vacio = new Vacio(this,c.get_fila(),c.get_columna());
		entidades[c.get_fila()][c.get_columna()].set_contenido(vacio);
		mi_juego.crear_entidad_grafica(vacio);
		
		//Preguntar si actualizar_puntaje esta bien posicionado

		mi_juego.actualizar_puntaje(c.get_puntaje()); 
	}
	
	public void eliminar_gelatina(Gelatina g){
		detono_gelatina();
		Entidad vacio = new Vacio(this,g.get_fila(),g.get_columna());
		entidades[g.get_fila()][g.get_columna()] = vacio;
		mi_juego.crear_entidad_grafica(vacio);
		mi_juego.actualizar_puntaje("scoreGelatina"); 
	}

	public void eliminar_glaseado(Glaseado g){
		Entidad vacio = new Vacio(this,g.get_fila(),g.get_columna());
		entidades[g.get_fila()][g.get_columna()].set_contenido(vacio);
		mi_juego.crear_entidad_grafica(vacio);
		mi_juego.actualizar_puntaje("scoreGlaseado");
	}

	public void eliminar_bomba(Bomba b){
		Entidad vacio = new Vacio(this,b.get_fila(),b.get_columna());
		entidades[b.get_fila()][b.get_columna()].set_contenido(vacio);
		mi_juego.crear_entidad_grafica(vacio);
		mi_juego.actualizar_puntaje("scoreBomba");
	}

	public void detono_glaseado() {
		mi_juego.actualizar_glaseados();
	}
	private void detono_gelatina(){
		mi_juego.actualizar_gelatina();
	}
	private void detono_caramelo(){
		mi_juego.actualizar_caramelos();
	}

	public void crear_envuelto(Entidad e){
		Entidad envuelto = fabrica.crear_envuelto(this,e.get_fila(),e.get_columna(),e.get_color());
		entidades[e.get_fila()][e.get_columna()].detonar();
		entidades[e.get_fila()][e.get_columna()].set_contenido(envuelto);
		mi_juego.crear_entidad_grafica(envuelto);
	}

	public void crear_rayado_horizontal(Entidad e){
		Entidad rayadoH = fabrica.crear_rayado_horizontal(this,e.get_fila(),e.get_columna(),e.get_color());
		entidades[e.get_fila()][e.get_columna()].detonar();
		entidades[e.get_fila()][e.get_columna()].set_contenido(rayadoH);
		mi_juego.crear_entidad_grafica(rayadoH);
	}

	public void crear_rayado_vertical(Entidad e){
		Entidad rayadoV = fabrica.crear_rayado_vertical(this,e.get_fila(),e.get_columna(),e.get_color());
		entidades[e.get_fila()][e.get_columna()].detonar();
		entidades[e.get_fila()][e.get_columna()].set_contenido(rayadoV);
		mi_juego.crear_entidad_grafica(rayadoV);
	}

	public void caidaGeneral() {
		for(int i=0;i<filas;i++){
			for(int j=0;j<columnas;j++){
				entidades[i][j].get_contenido().caida();
			}
		}
	}
	private boolean match_rayado_doble(Entidad e1, Entidad e2){
		int fila_enfocada = e1.get_fila();
		int columna_enfocada = e1.get_columna();
		boolean hubo_match = e1.machea_doble_con(e2);
		if(hubo_match){
			e1.detonar();
			e2.detonar();
		}
		if(hubo_match) {
			caidaGeneral();
			matchThreeGeneral();
			fijar_jugador(fila_enfocada,columna_enfocada);
			mi_juego.verificar_fin();
		}
		return hubo_match;
	}

	private boolean matchThree(Entidad e1,Entidad e2){
		boolean huboMatch = estrategia.matchThree(e1,e2,this);
		int fila_enfocada = e1.get_fila();
		int columna_enfocada = e1.get_columna();
		if(huboMatch) {
			caidaGeneral();
			matchThreeGeneral();
			fijar_jugador(fila_enfocada,columna_enfocada);
			mi_juego.verificar_fin();
		}
		return huboMatch;
	}	

	public void eliminar_fila(Entidad e) {
		int f = e.get_fila();
		for(int c=0; c<columnas; c++) {
			if(c != e.get_columna()){
				entidades[f][c].detonar();
			}
		}
	}

	public void eliminar_columna(Entidad e) {
		int c = e.get_columna();
		for(int f = 0; f<filas; f++) {
			if(f != e.get_fila()){
				entidades[f][c].detonar();
			}
		}
	}
	public void eliminar_fila_y_columna(Entidad e){
		eliminar_columna(e);
		eliminar_fila(e);
	}
	public void eliminar_cuadrado(Entidad e){
		if(en_rango(e.get_fila(), e.get_columna()-1))
			entidades[e.get_fila()][e.get_columna()-1].detonar();
		if(en_rango(e.get_fila(), e.get_columna()+1))
			entidades[e.get_fila()][e.get_columna()+1].detonar();
		if(en_rango(e.get_fila()-1, e.get_columna()))
			entidades[e.get_fila()-1][e.get_columna()].detonar();
		if(en_rango(e.get_fila()+1, e.get_columna()))
			entidades[e.get_fila()+1][e.get_columna()].detonar();

		if(en_rango(e.get_fila()-1, e.get_columna()-1))
			entidades[e.get_fila()-1][e.get_columna()-1].detonar();
		if(en_rango(e.get_fila()-1, e.get_columna()+1))
			entidades[e.get_fila()-1][e.get_columna()+1].detonar();
		if(en_rango(e.get_fila()+1, e.get_columna()+1))
			entidades[e.get_fila()+1][e.get_columna()+1].detonar();
		if(en_rango(e.get_fila()+1, e.get_columna()-1))
			entidades[e.get_fila()+1][e.get_columna()-1].detonar();
	}

	private void matchThreeGeneral() {
		boolean huboMatch = estrategia.matchThreeGeneral();
		if(huboMatch) {
			caidaGeneral();
			matchThreeGeneral();
			mi_juego.verificar_fin();

		}
	}
}