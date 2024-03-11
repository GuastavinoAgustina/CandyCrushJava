package Logica;
import java.util.HashMap;
import java.util.Map;
/**
 * Define los colores estandar a utilizar en los Caramelos
 */
public class ColorEntidades {
	public static final int VERDE = 1;
	public static final int ROJO = 2;
	public static final int AMARILLO = 3;
	public static final int NARANJA = 4;
	public static final int AZUL = 5;
	public static final int VIOLETA = 6;
	public static final int Gelatina = 7;
	public static final int Glaseado = 8;
	private static final Map<Integer, String> nombresColores = new HashMap<>();
    protected String [] imagenes_representativas;
    
    static {
        nombresColores.put(VERDE, "Verde");
        nombresColores.put(ROJO, "Rojo");
        nombresColores.put(AMARILLO, "Amarillo");
        nombresColores.put(NARANJA, "Naranja");
        nombresColores.put(AZUL, "Azul");
        nombresColores.put(VIOLETA, "Purpura");
        nombresColores.put(Gelatina, "Gelatina");
        nombresColores.put(Glaseado, "Glaseado");
    }

    public static String obtenerNombreColor(int codigoColor) {
        return nombresColores.get(codigoColor);
    }

    public static String obtener_path_color(int codigoColor, int tematica){
        if(tematica==0){
             return "/ImagenesCaramelos/caramelos/"+codigoColor+".png";
        }
        else{
             return "/ImagenesGatos/caramelos/"+codigoColor+".png";
        }
    }
    public static String obtener_path_gelatina(int tematica){
        if(tematica==0){
             return "/ImagenesCaramelos/gelatina/1.png";
        }
        else{
             return "/ImagenesGatos/gelatina/1.png";
        }
    }
    public static String obtener_path_glaseado(int tematica){
        if(tematica==0){
             return "/ImagenesCaramelos/glaseados/8.png";
        }
        else{
             return "/ImagenesGatos/glaseados/8.png";
        }
    }
}
