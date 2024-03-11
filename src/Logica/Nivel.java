package Logica;

/**
 * Modela la clase nivel. Se espera que el mismo permita observar los objetivos esperados para el nivel.
 */
public class Nivel {
	
	protected int fila_inicial_jugador;
	protected int columna_inicial_jugador;
	protected int tiempo;
	protected int cant_movimientos;
	protected int cant_destruir_glaseados;
	protected int cant_destruir_gelatina;
	protected int cant_destruir_caramelos;
	protected int color_caramelos_para_destruir;


	public Nivel(int f, int c) {
		fila_inicial_jugador = f;
		columna_inicial_jugador = c;
	}
	
	public int get_fila_inicial_jugador() {
		return fila_inicial_jugador;
	}
	
	public int get_columna_inicial_jugador() {
		return columna_inicial_jugador;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public int getCant_movimientos() {
		return cant_movimientos;
	}

	public void setCant_movimientos(int cant_movimientos) {
		this.cant_movimientos = cant_movimientos;
	}

	public int getCant_destruir_glaseados() {
		return cant_destruir_glaseados;
	}

	public void setCant_destruir_glaseados(int cant_destruir_glaseados) {
		this.cant_destruir_glaseados = cant_destruir_glaseados;
	}

	public int getCant_destruir_gelatina() {
		return cant_destruir_gelatina;
	}

	public void setCant_destruir_gelatina(int cant_destruir_gelatina_o_envuelto) {
		this.cant_destruir_gelatina = cant_destruir_gelatina_o_envuelto;
	}

	public int getCant_destruir_caramelos() {
		return cant_destruir_caramelos;
	}

	public void setCant_destruir_caramelos(int cant_destruir_caramelos) {
		this.cant_destruir_caramelos = cant_destruir_caramelos;
	}

	public int getColor_caramelos_para_destruir() {
		return color_caramelos_para_destruir;
	}

	public void setColor_caramelos_para_destruir(int color_caramelos_para_destruir) {
		this.color_caramelos_para_destruir = color_caramelos_para_destruir;
	}
	
	
}
