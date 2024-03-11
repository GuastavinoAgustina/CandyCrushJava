package Animadores;

import GUI.Celda;

/**
 * Define las operaciones esperables por sobre un elemento Animador.
 */
public interface Animador {
	/**
	 * Obtiene la celda que se animará.
	 * @return Retorna la celda obtenida.
	 */
	public Celda get_celda_asociada();
	
	/**
	 * Inicia el comportamiento asociado con la animación.
	 */
	public void comenzar_animacion();
}
