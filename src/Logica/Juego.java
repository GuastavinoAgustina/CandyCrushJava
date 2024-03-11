package Logica;

import Entidades.Entidad;
import Estrategias.Estrategia;
import Fabricas.FabricaCaramelos;
import Fabricas.FabricaEntidades;
import Fabricas.FabricaGatos;
import GUI.EntidadGrafica;
import GUI.MenuPrincipal;
import GUI.Player;
import GUI.Ventana;
import GUI.TopPlayers;


/**
 * Modela el comportamiento del Juego.
 * Ofrece servicios para comunicar los diferentes elementos que conforman la lógica de la aplicación con la gráfica de la misma.
 */
public class Juego {
	
	public static final int ARRIBA = 15000;
	public static final int ABAJO = 15001;
	public static final int IZQUIERDA = 15002;
	public static final int DERECHA = 15003;
	
	protected Tablero mi_tablero;
	protected Ventana mi_ventana;
	protected Nivel mi_nivel;
	protected int nivel_actual;
	protected int vidas;
	protected FabricaEntidades fabrica;
	protected int tematica;
	protected Player mi_player;
	protected TopPlayers mi_top;
	protected int anterior_puntaje = 0;
	
	public Juego(int tematica, Player jugador, TopPlayers top) {
		this.tematica = tematica;
		if(tematica == 0){
			fabrica = new FabricaCaramelos();
		}else{
			fabrica = new FabricaGatos();
		}
		mi_tablero = new Tablero(this, fabrica);
		nivel_actual = 1;
		vidas = 5;
		mi_player = jugador;
		mi_top = top;
		mi_nivel = GeneradorNivel.cargar_nivel_y_tablero(1, mi_tablero, fabrica);
		mi_ventana = new Ventana(this, mi_tablero.get_filas(), mi_tablero.get_columnas());
		asociar_entidades_logicas_graficas();
		mi_tablero.fijar_jugador(mi_nivel.get_fila_inicial_jugador(), mi_nivel.get_columna_inicial_jugador());
		
	}
	
	public void mover_jugador(int d) {
		mi_tablero.mover_jugador(d);
	}
	
	public void cambiar_nivel (int nivel) {
		mi_nivel = GeneradorNivel.cargar_nivel_y_tablero(nivel, mi_tablero,fabrica);
		nivel_actual = nivel;
		mi_ventana.setVisible(false);
		//mejorable
		mi_ventana = new Ventana(this, mi_tablero.get_filas(), mi_tablero.get_columnas());
		asociar_entidades_logicas_graficas();
		mi_tablero.fijar_jugador(mi_nivel.get_fila_inicial_jugador(), mi_nivel.get_columna_inicial_jugador());
	}

	public Nivel get_nivel() {
		return mi_nivel;
	}
	
	public int get_nivel_actual() {
		return nivel_actual;
	}

	public int get_vidas() {
		return vidas;
	}
	public int get_tematica(){
		return tematica;
	}
	public void decrementar_vidas() {
		vidas--;
	}
	public Player get_jugador(){
		return mi_player;
	}
	public TopPlayers get_top(){
		return mi_top;
	}
	public void intercambiar(int d) {
		mi_tablero.intercambiar(d);
	}

	public void crear_entidad_grafica(Entidad nueva){
		EntidadGrafica eg = mi_ventana.agregar_entidad(nueva);
		nueva.set_entidad_grafica(eg);
	}
	
	private void asociar_entidades_logicas_graficas() {
		Entidad e;
		EntidadGrafica eg;
		
		for (int f=0; f<mi_tablero.get_filas(); f++) {
			for (int c=0; c<mi_tablero.get_columnas(); c++) {
				e = mi_tablero.get_entidad(f, c);
				eg = mi_ventana.agregar_entidad(e);
				e.set_entidad_grafica(eg);
			}
		}
		mi_ventana.setVisible(true);
	}
	public void decrementa_movimientos() {
		mi_nivel.setCant_movimientos(mi_nivel.getCant_movimientos() - 1);
		mi_ventana.diminuir_movimientos();
	}
	public void actualizar_glaseados(){
		if(mi_nivel.getCant_destruir_glaseados()>0){
			mi_nivel.setCant_destruir_glaseados(mi_nivel.getCant_destruir_glaseados()-1);
			mi_ventana.disminuir_glaseados();
		}
	}

	public void actualizar_gelatina(){
		if(mi_nivel.getCant_destruir_gelatina()>0){
			mi_nivel.setCant_destruir_gelatina(mi_nivel.getCant_destruir_gelatina()-1);
			mi_ventana.disminuir_gelatina();
		}
	}
	
	public void actualizar_caramelos(){
		if(mi_nivel.getCant_destruir_caramelos()>0){
			mi_nivel.setCant_destruir_caramelos(mi_nivel.getCant_destruir_caramelos()-1);
			mi_ventana.disminuir_caramelos();
		}
	}

	public void actualizar_puntaje(String tipo){
		int puntaje_entidad = Integer.parseInt(MenuPrincipal.configuration.getProperty(tipo));
		mi_player.sumar_puntaje(puntaje_entidad);
		mi_ventana.incrementar_puntaje();
	}

	public void actualizar_puntaje_anterior() {
		anterior_puntaje = mi_player.getScore();
	}
	public void reiniciar_puntaje(){
		mi_player.setScore(anterior_puntaje);
	}

	public void verificar_fin() {
		int caramelos_restantes = mi_nivel.getCant_destruir_caramelos();
		int gelatina_restantes = mi_nivel.getCant_destruir_gelatina();
		int glaseados_restantes = mi_nivel.getCant_destruir_glaseados();
		if(caramelos_restantes<=0 && gelatina_restantes <=0 && glaseados_restantes <=0){
			if(get_nivel_actual() == 5){
				mi_ventana.fin();
			}else{
				mi_ventana.cambio_de_nivel();
			}
		}
	}
	public void descativar_bombas(){
		mi_tablero.descativar_bombas();
	}
	public Estrategia get_strategy() {
		return mi_tablero.get_estrategia();
	}
}
