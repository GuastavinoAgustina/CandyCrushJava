package Entidades;

/**
 * Define los mensaje posibles de solicitar por sobre las entidades de la aplicación, 
 */
public interface Intercambiable {
	/**
	 * Indica si es posible que la entidad receptora pueda ser intercambiada de posición con la entidad parametrizada.
	 * Chequea las condiciones necesarias para que el intercambio sea válido.
	 * @param e Entidad con la que se analiza el intercambio.
	 * @return True si el intercambio es posible, false en caso contrario.
	 */
	public boolean es_posible_intercambiar(Entidad e);
	/**
	 * Indica si es posible que la entidad receptora pueda ser intercambiada de posición con el Caramelo parametrizado.
	 * Chequea las condiciones necesarias para el intercambio.
	 * @param c Caramelo con el que se analiza el intercambio.
	 * @return True si el intercambio es posible, false en caso contrario.
	 */
	public boolean puede_recibir(Caramelo c);
	/**
	 * Indica si es posible que la entidad receptora pueda ser intercambiada de posición con el Glaseado parametrizado.
	 * Chequea las condiciones necesarias para el intercambio.
	 * @param g Glaseado con el que se analiza el intercambio.
	 * @return True si el intercambio es posible, false en caso contrario.
	 */
	public boolean puede_recibir(Glaseado g);
	/**
	 * Indica si es posible que la entidad receptora pueda ser intercambiada de posición con el Vacio parametrizado.
	 * Chequea las condiciones necesarias para el intercambio.
	 * @param v Vacio con el que se analiza el intercambio.
	 * @return True si el intercambio es posible, false en caso contrario.
	 */
	public boolean puede_recibir(Vacio v);
	/**
	 * Indica si es posible que la entidad receptora pueda ser intercambiada de posición con la Gelatina parametrizada.
	 * Chequea las condiciones necesarias para el intercambio.
	 * @param g Gelatina con el que se analiza el intercambio.
	 * @return True si el intercambio es posible, false en caso contrario.
	 */
	public boolean puede_recibir(Gelatina g);
	/**
	 * Indica si es posible que la entidad receptora pueda ser intercambiada de posición con la Bomba parametrizada.
	 * Chequea las condiciones necesarias para el intercambio.
	 * @param b Bomba con el que se analiza el intercambio.
	 * @return True si el intercambio es posible, false en caso contrario.
	 */
	public boolean puede_recibir(Bomba b);
	/**
	 * Intercambia de posición a la entidad receptora, la nueva posición será la parametrizada.
	 * @param nf Número de fila a la que se moverá la entidad receptora.
	 * @param nc Número de columna a la que se moverá la entidad receptora.
	 */
	public void intercambiar_posicion(int nf, int nc);


}
