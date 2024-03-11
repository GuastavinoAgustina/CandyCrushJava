package GUI;

/**
 * Define las operaciones esperables por una ventana que permite notificarse sobre animaciones en progreso o finalizadas.
 *
 */
public interface VentanaNotificable {
	/**
	 * Notifica que una nueva animación comenzó a ejecutarse.
	 */
	public void notificarse_animacion_en_progreso();
	
	/**
	 * Notifica que una animación en progreso, ha finalizado.
	 */
	public void notificarse_animacion_finalizada();
}
