package Logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Estrategias.*;
import Fabricas.FabricaEntidades;

/**
 * Simula el comportamiento real de un Generador de Nivel, cableando la generación de entidades de forma manual.
 * Se espera que la clase permita parsear el contenido de un archivo de texto, desde donde se generará efectivamente el nivel.
 */
public class GeneradorNivel {

	public static Nivel cargar_nivel_y_tablero(int nivel, Tablero t, FabricaEntidades fabrica) {
		Nivel nivelNuevo = new Nivel(2,2);
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		int cantFilas = 0;
		int cantColumnas = 0;
		int cantTiempo = 0;
		int cant_movimientos = 0;
		int cant_destruir_glaseados = 0;
		int cant_destruir_gelatina= 0;
		int color_caramelos_para_destruir = 0;
		int cant_destruir_caramelos = 0;
		
		try {
			archivo = new File (GeneradorNivel.class.getResource("/niveles/"+nivel+".txt").getFile());
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
			
			String linea = br.readLine();
			cantFilas = Character.getNumericValue(linea.charAt(0));
			linea = br.readLine();
			cantColumnas = Character.getNumericValue(linea.charAt(0));
			t.resetar_tablero(cantFilas, cantColumnas); 
			//leer tiempo
			linea = br.readLine();
			cantTiempo = Character.getNumericValue(linea.charAt(0));
			nivelNuevo.setTiempo(cantTiempo);
			//leer movimientos 
			linea = br.readLine();
			cant_movimientos = Integer.parseInt(linea);
			nivelNuevo.setCant_movimientos(cant_movimientos);
			//leer cant_destruir_glaseados
			linea = br.readLine();
			cant_destruir_glaseados = Integer.parseInt(linea);
			nivelNuevo.setCant_destruir_glaseados(cant_destruir_glaseados);
			//leer cant_destruir_gelatina
			linea = br.readLine();
			cant_destruir_gelatina = Integer.parseInt(linea);
			nivelNuevo.setCant_destruir_gelatina(cant_destruir_gelatina);
			//leer color_caramelos_para_destruir
			linea = br.readLine();
			color_caramelos_para_destruir = Character.getNumericValue(linea.charAt(0));
			nivelNuevo.setColor_caramelos_para_destruir(color_caramelos_para_destruir);
			//leer cant_destruir_caramelos
			linea = br.readLine();
			cant_destruir_caramelos = Integer.parseInt(linea);
			nivelNuevo.setCant_destruir_caramelos(cant_destruir_caramelos);

			//leer estrategia a utilizar(todavia no lee pero anda bien)
			linea = br.readLine();
			t.set_estrategia(new EstrategiaTodas());
			String tipoEstrategia = linea;
			switch(tipoEstrategia) {
				case "EstrategiaTodas":{t.set_estrategia(new EstrategiaTodas());break;}
				case "EstrategiaCuatroEnLinea":{t.set_estrategia(new EstrategiaCuatroEnLinea());break;}
				case "EstrategiaSinTSinL":{t.set_estrategia(new EstrategiaSinTSinL());break;}
				case "EstrategiaTresEnLinea":{t.set_estrategia(new EstrategiaTresEnLinea());break;}	
			}

			for(int i=0; i<cantFilas;i++) {
				linea = br.readLine();
				int pos = 0;
				for(int j=0; j<cantColumnas;j++){
					char tipo = linea.charAt(pos);
					pos++;
					int color =Character.getNumericValue(linea.charAt(pos));
					switch(tipo) {
						case 'C':{t.agregar_entidad(fabrica.crear_caramelo(t,i,j,color));break;}
						case 'E':{t.agregar_entidad(fabrica.crear_envuelto(t,i,j,color));break;}
						case 'V':{t.agregar_entidad(fabrica.crear_rayado_vertical(t,i,j,color));break;}
						case 'H':{t.agregar_entidad(fabrica.crear_rayado_horizontal(t,i,j,color));break;}
						case 'G':{t.agregar_entidad(fabrica.crear_gelatina(fabrica.crear_caramelo(t,i,j,color),t,i,j));break;}
						case 'D':{t.agregar_entidad(fabrica.crear_glaseado(t,i,j,color));break;}
						case 'R':{t.agregar_entidad(fabrica.crear_rayado_doble(t,i,j,color));break;}
						case 'B':{t.agregar_entidad(fabrica.crear_bomba(t,i,j,color));break;}
					}
					pos++; //estoy en el espacio
					pos++; //pasa al primero de la proxima columna
				}
	        }
			if( null != fr )
				fr.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		return nivelNuevo;
	}	
}