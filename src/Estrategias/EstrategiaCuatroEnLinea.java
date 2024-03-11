package Estrategias;

import java.util.LinkedList;

import Entidades.*;
import Logica.Tablero;

public class EstrategiaCuatroEnLinea extends EstrategiaGeneral {
    
   public boolean matchThree(Entidad e1,Entidad e2, Tablero t){
      max = 4;
      mi_tablero = t;
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
      if (matchHorizontal1.size()>=3 && matchVertical1.size()>=3 && matchHorizontal1.getFirst().get_color() ==matchVertical1.getFirst().get_color() ){
         mi_tablero.crear_envuelto(e1);
      }
      if (matchHorizontal1.size()>=4 && matchVertical1.size()<3){
         mi_tablero.crear_rayado_horizontal(e1);
      }
      if (matchVertical1.size()>=4 && matchHorizontal1.size()<3){
         mi_tablero.crear_rayado_vertical(e1);
      }
      if (matchHorizontal2.size()>=3 && matchVertical2.size()>=3 && matchHorizontal2.getFirst().get_color() ==matchVertical2.getFirst().get_color() ){
         mi_tablero.crear_envuelto(e2);
      }
      if (matchHorizontal2.size()>=4 && matchVertical2.size()<3){
         mi_tablero.crear_rayado_horizontal(e2);
      }
      if (matchVertical2.size()>=4 && matchHorizontal2.size()<3){
         mi_tablero.crear_rayado_vertical(e2);
      }
      return match1 || match2;
   }	

   protected boolean elementosMatchThree(Entidad e,LinkedList<Entidad> matchVertical,LinkedList<Entidad> matchHorizontal) {
      boolean toReturn = false;
      Entidad entidadActual = e;
      
      matchHorizontal.add(entidadActual);
      matchVertical.add(entidadActual);
      caminoDerecha(entidadActual,matchHorizontal); 
      if(matchHorizontal.size() < 4){
         caminoIzquierda(entidadActual,matchHorizontal);
      }
      caminoArriba(entidadActual,matchVertical);
      if(matchVertical.size() < 4){
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
	   return 4;
   }
   public String crearEnvueltos() {
	   return "ON";
   }
   public String crearRayados() {
	   return "ON";
   }

}