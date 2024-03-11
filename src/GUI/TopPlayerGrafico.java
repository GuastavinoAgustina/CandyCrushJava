package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopPlayerGrafico extends JFrame {

	protected JLabel texto_superior;
	protected JPanel panel_principal;
	protected  TopPlayers mi_top_player;
    
	public TopPlayerGrafico(TopPlayers top) {
		inicializar(top);
	}
	
	protected void inicializar(TopPlayers top) {
		mi_top_player = top;
		setTitle("Puntuaciones");
		setSize(new Dimension(300, 300));
		setResizable(false);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(getContentPane());
		List<Player> lista_de_posiciones = mi_top_player.posiciones();
		Iterator<Player> iterador_de_posiciones = lista_de_posiciones.iterator();
		Player pos_jugador;
		panel_principal = new JPanel();
		panel_principal.setLayout(null);
		getContentPane().add(panel_principal, BorderLayout.CENTER);
		panel_principal.setFocusable(true);
        int espacio = 30;
		int pos_y = 30;
		JLabel titulo = new JLabel("Mejores Puntuaciones:");
		titulo.setBounds(25, pos_y, 165, 33);
		panel_principal.add(titulo);
        pos_y = 50;
		pos_y = pos_y + espacio;
		pos_jugador = proxima_posicion(iterador_de_posiciones);
		JLabel primero = new JLabel("1°");
		if(pos_jugador != null){
			primero = new JLabel("1° "+pos_jugador.getPlayer()+" "+pos_jugador.getScore());
		}
		primero.setBounds(25, pos_y, 229, 14);
		panel_principal.add(primero);
		pos_y = pos_y + espacio;
		pos_jugador = proxima_posicion(iterador_de_posiciones);
		JLabel segundo = new JLabel("2°");
		if(pos_jugador != null){
			segundo = new JLabel("2° "+pos_jugador.getPlayer()+" "+pos_jugador.getScore());
		}		
		segundo.setBounds(25, pos_y, 229, 14);
		panel_principal.add(segundo);
		pos_y = pos_y + espacio;
		pos_jugador = proxima_posicion(iterador_de_posiciones);
		JLabel tercero = new JLabel("3°");
		if(pos_jugador != null){
			tercero = new JLabel("3° "+pos_jugador.getPlayer()+" "+pos_jugador.getScore());
		}		
		tercero.setBounds(25, pos_y, 229, 14);
		panel_principal.add(tercero);
		pos_y = pos_y + espacio;
		pos_jugador = proxima_posicion(iterador_de_posiciones);
		JLabel cuarto = new JLabel("4°");
		if(pos_jugador != null){
			cuarto = new JLabel("4° "+pos_jugador.getPlayer()+" "+pos_jugador.getScore());
		}		
		cuarto.setBounds(25, pos_y, 229, 14);
		panel_principal.add(cuarto);
		pos_y = pos_y + espacio;
		pos_jugador = proxima_posicion(iterador_de_posiciones);
		JLabel quinto = new JLabel("5°");
		if(pos_jugador != null){
			quinto = new JLabel("5° "+pos_jugador.getPlayer()+" "+pos_jugador.getScore());
		}		
		quinto.setBounds(25, pos_y, 229, 14);
		panel_principal.add(quinto);
	}

	private Player proxima_posicion(Iterator<Player> iterador_de_posiciones) {
		Player toReturn = null;
		if(iterador_de_posiciones.hasNext()){
			toReturn = iterador_de_posiciones.next();
		}
		return toReturn;
	}
}


