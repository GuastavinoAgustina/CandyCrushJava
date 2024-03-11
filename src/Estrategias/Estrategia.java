package Estrategias;

import Entidades.*;
import Logica.Tablero;

public interface Estrategia {
    public boolean matchThree(Entidad e1, Entidad e2, Tablero t);

    public boolean matchThreeGeneral();
    public int maxMatch();
    public String crearEnvueltos();
    public String crearRayados();
} 
    

