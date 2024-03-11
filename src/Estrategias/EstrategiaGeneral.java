package Estrategias;

import java.util.LinkedList;

import Entidades.*;
import Logica.Tablero;

public abstract class EstrategiaGeneral implements Estrategia{ 
    protected Tablero mi_tablero;
    int max;
    public abstract boolean matchThree(Entidad e1,Entidad e2, Tablero t);

	protected abstract boolean elementosMatchThree(Entidad e,LinkedList<Entidad> matchVertical,LinkedList<Entidad> matchHorizontal);

	protected void caminoIzquierda(Entidad entidadActual, LinkedList<Entidad> matchHorizontal) {
        if(mi_tablero.en_rango(entidadActual.get_fila(),(entidadActual.get_columna()-1)) && entidadActual.machea(izquierda(entidadActual)) && matchHorizontal.size() < max) {
           entidadActual = izquierda(entidadActual);
           matchHorizontal.add(entidadActual);
           caminoIzquierda(entidadActual, matchHorizontal);
        }
    }
  
    protected void caminoDerecha(Entidad entidadActual, LinkedList<Entidad> matchHorizontal) {
        if(mi_tablero.en_rango(entidadActual.get_fila(),(entidadActual.get_columna()+1)) && entidadActual.machea(derecha(entidadActual))&& matchHorizontal.size() < max) {
           entidadActual = derecha(entidadActual);
           matchHorizontal.add(entidadActual);
           caminoDerecha(entidadActual, matchHorizontal);
        }
  
    }
  
    protected void caminoArriba(Entidad entidadActual, LinkedList<Entidad> matchVertical) {
        if(mi_tablero.en_rango((entidadActual.get_fila()-1),entidadActual.get_columna()) && entidadActual.machea(arriba(entidadActual))&& matchVertical.size() < max) {
           entidadActual = arriba(entidadActual);
           matchVertical.add(entidadActual);
           caminoArriba(entidadActual, matchVertical);
  
        }
    }
  
    protected void caminoAbajo(Entidad entidadActual, LinkedList<Entidad> matchVertical) {
        if(mi_tablero.en_rango((entidadActual.get_fila()+1),entidadActual.get_columna()) && entidadActual.machea(abajo(entidadActual))&& matchVertical.size() < max) {
           entidadActual = abajo(entidadActual);
           matchVertical.add(entidadActual);
           caminoAbajo(entidadActual, matchVertical);
        }
    }

	protected Entidad izquierda(Entidad e){
		return mi_tablero.get_entidad(e.get_fila(),e.get_columna()-1);
	}

	protected Entidad derecha(Entidad e){
		return mi_tablero.get_entidad(e.get_fila(),e.get_columna()+1);
	}

	protected Entidad arriba(Entidad e){
		return mi_tablero.get_entidad(e.get_fila()-1,e.get_columna());
	}

	protected Entidad abajo(Entidad e){
		return mi_tablero.get_entidad(e.get_fila()+1,e.get_columna());
	}

	public boolean matchThreeGeneral() {
		boolean hubo_match = false;
		for(int i=0;i<mi_tablero.get_filas();i++) {
			for(int j=0; j<mi_tablero.get_columnas(); j++) {
				hubo_match = hubo_match || elmentosMatchThreeGeneral(mi_tablero.get_entidad(i, j));
			}
		}
		return hubo_match;
	}

	protected boolean elmentosMatchThreeGeneral(Entidad e1){
		LinkedList<Entidad> matchVertical1= new LinkedList<Entidad>();
		LinkedList<Entidad> matchHorizontal1= new LinkedList<Entidad>();
		boolean match1 = elementosMatchThree(e1,matchVertical1,matchHorizontal1);
		if(matchHorizontal1.size()>=3) {
			for(Entidad entidad :  matchHorizontal1) {
				entidad.detonar();
			}			
		}
		if(matchVertical1.size()>=3) {
			for(Entidad entidad :  matchVertical1) {
				entidad.detonar();
			}
		}
		if (matchHorizontal1.size()>=3 && matchVertical1.size()>=3 && matchHorizontal1.getFirst().get_color() ==matchVertical1.getFirst().get_color() ){
			mi_tablero.crear_envuelto(e1);
		}
		if (matchHorizontal1.size()>=4 && matchVertical1.size()<3){
			mi_tablero.crear_rayado_horizontal(e1);
		}
		if (matchVertical1.size()>=4 && matchHorizontal1.size()<3){
			mi_tablero.crear_rayado_vertical(e1);
		}
		
		return match1;
	}
}
