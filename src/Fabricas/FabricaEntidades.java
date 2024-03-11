package Fabricas;

import Entidades.*;
import Logica.Tablero;

public interface FabricaEntidades  {
    public Caramelo crear_caramelo(Tablero tablero, int f, int c, int col);
    public RayadoHorizontal crear_rayado_horizontal(Tablero tablero, int f, int c, int col );
    public RayadoVertical crear_rayado_vertical(Tablero tablero, int f, int c, int col);
    public RayadoDoble crear_rayado_doble(Tablero tablero, int f, int c, int col);
    public Envuelto crear_envuelto(Tablero tablero, int f, int c, int col);
    public Glaseado crear_glaseado(Tablero tablero, int f, int c, int col);
    public Bomba crear_bomba(Tablero tablero, int f, int c, int col );
    public Gelatina crear_gelatina(Caramelo cont, Tablero tablero, int f, int c);
    public Vacio crear_vacio(Tablero tablero, int f, int c, int col );
}