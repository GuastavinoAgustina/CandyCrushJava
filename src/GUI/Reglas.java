package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Estrategias.*;

public class Reglas extends JFrame {

	protected JLabel texto_superior;
	protected JPanel panel_principal;
    protected Estrategia mi_estrategia;
    
	public Reglas(Estrategia e) {
		inicializar(e);
	}
	
	protected void inicializar(Estrategia e) {
		setTitle("Reglas");
		setSize(new Dimension(300, 190));
		setResizable(false);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(getContentPane());
		panel_principal = new JPanel();
		panel_principal.setLayout(null);
		getContentPane().add(panel_principal, BorderLayout.CENTER);
		panel_principal.setFocusable(true);
        int espacio = 30;
		int pos_y = 10;
		JLabel titulo = new JLabel("Reglas:");
		titulo.setBounds(25, pos_y, 165, 33);
		panel_principal.add(titulo);
		pos_y = pos_y + espacio+10;
        JLabel match = new JLabel("Match maximo: "+e.maxMatch());
		match.setBounds(25, pos_y, 229, 14);
		panel_principal.add(match);
		pos_y = pos_y + espacio;
		
		JLabel envueltos = new JLabel("Creacion de envueltos: "+e.crearEnvueltos());
		envueltos.setBounds(25, pos_y, 229, 14);
		panel_principal.add(envueltos);
		pos_y = pos_y + espacio;
		
		JLabel rayados = new JLabel("Creacion de rayados: "+e.crearRayados());
		rayados.setBounds(25, pos_y, 229, 14);
		panel_principal.add(rayados);
		pos_y = pos_y + espacio;
        
	}
}

