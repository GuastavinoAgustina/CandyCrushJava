package Fabricas;

import Entidades.*;
import Logica.Tablero;

public class FabricaGatos implements FabricaEntidades{

    protected String path = "/ImagenesGatos/";

    public Caramelo crear_caramelo(Tablero tablero, int f, int c, int col){
        return new Caramelo(tablero, f, c, col, path);
    }
    public RayadoHorizontal crear_rayado_horizontal(Tablero tablero, int f, int c, int col){
        return new RayadoHorizontal(tablero, f, c, col, path);
    }
    public RayadoVertical crear_rayado_vertical(Tablero tablero, int f, int c, int col){
        return new RayadoVertical(tablero, f, c, col, path);
    }
    public RayadoDoble crear_rayado_doble(Tablero tablero, int f, int c, int col){
         return new RayadoDoble(tablero, f, c, col, path);
    }
    public Envuelto crear_envuelto(Tablero tablero, int f, int c, int col){
        return new Envuelto(tablero, f, c, col, path);
    }
    public Glaseado crear_glaseado(Tablero tablero, int f, int c, int col){
        return new Glaseado(tablero, f, c, col, path);
    }
    public Bomba crear_bomba(Tablero tablero, int f, int c, int col){
        return new Bomba(tablero, f, c, col, path);
    }
    public Gelatina crear_gelatina(Caramelo cont, Tablero tablero, int f, int c){
        return new Gelatina(cont, tablero, f, c, path);
    }
    public Vacio crear_vacio(Tablero tablero, int f, int c, int col){
        return new Vacio(tablero, f, c);
    }
}
