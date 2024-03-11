package GUI;

public interface EntidadGrafica {
	/**
	 * Notifica a la entidad gráfica de que, la entidad lógica, modificó su estado.
	 * Desencadena la actualización de la entidad gráfica en la ventana de la aplicación, considerando el nuevo estado.
	 */
	public void notificarse_cambio_estado();
	
	/**
	 * Notifica a la entidad gráfica de que, la entidad lógica, modificó su posición.
	 * Desencadena la actualización de la entidad gráfica en la ventana de la aplicación, considerando su nueva posición, y animando este
	 * movimiento.
	 */
	public void notificarse_cambio_posicion();
	/**
	 * Notifica a la entidad gráfica de que, la entidad lógica, se detonó
	 * Desencadena la actualización de la entidad gráfica en la ventana de la aplicación, considerando que la entidad ya no existe y animando este
	 * movimiento.
	 */
	public void notificar_detonacion();
	public void asociar_timer(CountdownTimer tiempo);
	public void actualizar_timer(CountdownTimer tiempo);
	public void detener_timer(CountdownTimer tiempo);
}
