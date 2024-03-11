package Estrategias;

import java.util.LinkedList;

import Entidades.Entidad;
import Logica.Tablero;

public class EstrategiaTresEnLinea extends EstrategiaGeneral {


   public boolean matchThree(Entidad e1,Entidad e2, Tablero t){
      mi_tablero = t;
      max = 3;
      LinkedList<Entidad> matchVertical1= new LinkedList<Entidad>();
      LinkedList<Entidad> matchHorizontal1= new LinkedList<Entidad>();
      LinkedList<Entidad> matchVertical2= new LinkedList<Entidad>();
      LinkedList<Entidad> matchHorizontal2= new LinkedList<Entidad>();
      boolean match1 = elementosMatchThree(e1,matchVertical1,matchHorizontal1);
      boolean match2 = elementosMatchThree(e2,matchVertical2,matchHorizontal2);
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
      if(matchHorizontal2.size()>=3) {
         for(Entidad entidad :  matchHorizontal2) {
            entidad.detonar();
         }			
      }
      if(matchVertical2.size()>=3) {
         for(Entidad entidad :  matchVertical2) {
            entidad.detonar();
         }
      }
      
      return match1 || match2;
   }	

   protected boolean elementosMatchThree(Entidad e,LinkedList<Entidad> matchVertical,LinkedList<Entidad> matchHorizontal) {
      boolean toReturn = false;
      Entidad entidadActual = e;
      
      matchHorizontal.add(entidadActual);
      matchVertical.add(entidadActual);
      caminoDerecha(entidadActual,matchHorizontal); 
      if(matchHorizontal.size() < 3){
         caminoIzquierda(entidadActual,matchHorizontal);
      }
      caminoArriba(entidadActual,matchVertical);
      if(matchVertical.size() < 3){
         caminoAbajo(entidadActual,matchVertical);
      }
      if(matchHorizontal.size()>=3) {		
         toReturn = true;
      }
      if(matchVertical.size()>=3) {
         toReturn = true;
      }
      return toReturn;
   }	

   public int maxMatch() {
	   return 3;
   }
   public String crearEnvueltos() {
	   return "OFF";
   }
   public String crearRayados() {
	   return "OFF";
   }
   
}