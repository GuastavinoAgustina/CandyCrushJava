package Entidades;

/**
 * Define los mensaje posibles de solicitar por sobre las entidades de la aplicación, 
 */
public interface Matcheable {
	
	/**
	 * Indica si la entidad receptora pueda permite generar un match con la entidad parametrizada.
	 * @param e Entidad con la que se analiza el match.
	 * @return True, en caso de match; false, en caso contrario.
	 */
	public boolean machea(Entidad e);
	
	/**
	 * Indica si la entidad receptora pueda permite generar un match con un caramelo parametrizado.
	 * @param c Caramelo con el que se analiza el match.
	 * @return True, en caso de match; false, en caso contrario.
	 */
	public boolean match_con(Caramelo c);
	/**
	 * Indica si la entidad receptora pueda permite generar un match con un glaseado parametrizado.
	 * @param g Glaseado con el que se analiza el match.
	 * @return True, en caso de match; false, en caso contrario.
	 */
	public boolean match_con(Glaseado g);
	/**
	 * Indica si la entidad receptora pueda permite generar un match con un vacio parametrizado.
	 * @param v Vacio con el que se analiza el match.
	 * @return True, en caso de match; false, en caso contrario.
	 */
	public boolean match_con(Vacio v);
	/**
	 * Indica si la entidad receptora pueda permite generar un match con una gelatina parametrizada.
	 * @param g Gelatina con el que se analiza el match.
	 * @return True, en caso de match; false, en caso contrario.
	 */
	public boolean match_con(Gelatina g);
	/**
	 * Indica si la entidad receptora pueda permite generar un match con una bomba parametrizada.
	 * @param b Bomba con el que se analiza el match.
	 * @return True, en caso de match; false, en caso contrario.
	 */
	public boolean match_con(Bomba b);
	/**
	 * Indica si la entidad receptora pueda permite generar un matchDoble con la entidad parametrizada.
	 * @param e Entidad con la que se analiza el matchDoble.
	 * @return True, en caso de matchDoble; false, en caso contrario.
	 */
	public boolean machea_doble_con(Entidad e);
	/**
	 * Indica si la entidad receptora pueda permite generar un matchDoble con un caramelo parametrizado.
	 * @param c Caramelo con el que se analiza el match.
	 * @return True, en caso de matchDoble; false, en caso contrario.
	 */
	public boolean match_doble_con(Caramelo c);
	/**
	 * Indica si la entidad receptora pueda permite generar un matchDoble con un glaseado parametrizado.
	 * @param G Glaseado con el que se analiza el match.
	 * @return True, en caso de matchDoble; false, en caso contrario.
	 */
	public boolean match_doble_con(Glaseado g);
	/**
	 * Indica si la entidad receptora pueda permite generar un matchDoble con un vacio parametrizado.
	 * @param v Vacio con el que se analiza el match.
	 * @return True, en caso de matchDoble; false, en caso contrario.
	 */
	public boolean match_doble_con(Vacio v);
	/**
	 * Indica si la entidad receptora pueda permite generar un matchDoble con una gelatina parametrizada.
	 * @param g Gelatina con el que se analiza el match.
	 * @return True, en caso de matchDoble; false, en caso contrario.
	 */
	public boolean match_doble_con(Gelatina g);
	/**
	 * Indica si la entidad receptora pueda permite generar un matchDoble con una bomba parametrizada.
	 * @param b Bomba con el que se analiza el match.
	 * @return True, en caso de matchDoble; false, en caso contrario.
	 */
	public boolean match_doble_con(Bomba b);

	
}
