package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import Animadores.CentralAnimaciones;
import Logica.EntidadLogica;
import Logica.Juego;
import Logica.ColorEntidades;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Modela el comportamiento de la Ventana de la aplicación.
 * Ofrece servicios para comunicar los diferentes elementos que conforman la gráfica de la aplicación con la lógica de la misma.
 */
public class Ventana extends JFrame implements VentanaAnimable, VentanaNotificable{
	
	protected Juego mi_juego;
	protected CentralAnimaciones mi_animador;
	protected int filas;
	protected int columnas;
	
	protected int animaciones_pendientes;
	protected boolean bloquear_intercambios;
	
	protected JLabel texto_superior;
	protected JLayeredPane panel_principal;
	protected JLabel movimientos;
	protected JLabel glaseados;
	protected JLabel gelatinas;
	protected JLabel caramelos;
	protected JLabel imagen_objetivo;
	protected JLabel puntaje;
	private int size_label = 60;
	private Timer timer; 
	private TopPlayerGrafico top_player_grafico; 
	private Reglas reglas_del_juego;
	private JLayeredPane layeredPane;
	//public static Properties configuration;

	/**
	 * Inicializa la ventana asociada al juego en progreso, considerando
	 * @param j El juego que controlará la lógica de la aplicación, y con quien comunicará los movimientos del jugador.
	 * @param f La cantidad de filas del tablero.
	 * @param c La cantidad de columnas del tablero.
	 */
	public Ventana(Juego j, int f, int c) {
		mi_juego = j;
		mi_animador = new CentralAnimaciones(this);
		
		filas = f;
		columnas = c;
		
		animaciones_pendientes = 0;
		bloquear_intercambios = false;
		
		inicializar();
	}
	
	/**
	 * Crea una nueva celda, que quedará asociada a la entidad lógica parametrizada, a partir de la ubicación de esta.
	 * Agrega y deja visible la celda creada, por sobre la pantalla.
	 * @param e Entidad lógica con la que quedará asociada la celda.
	 * @return La entidad gráfica creada.
	 */
	public EntidadGrafica agregar_entidad(EntidadLogica e) {
		Celda celda = new Celda(this, e, size_label);
		//layeredPane.add(celda, JLayeredPane.DEFAULT_LAYER);
		panel_principal.add(celda);
		return celda;
	}
	
	@Override
	public void notificarse_animacion_en_progreso() {
		synchronized(this){
			animaciones_pendientes ++;
			bloquear_intercambios = true;
		}
	}
	
	@Override
	public void notificarse_animacion_finalizada() {
		synchronized(this){
			animaciones_pendientes --;
			bloquear_intercambios = animaciones_pendientes > 0;
		}
	}
	
	@Override
	public void animar_movimiento(Celda c) {
		mi_animador.animar_cambio_posicion(c);
	}
	
	
	public void animar_detonacion(Celda c) {
		panel_principal.remove(c);
	}
	
	public void mostrar_timer(CountdownTimer tiempo){
		
		layeredPane.add(tiempo.get_etiquetaTiempo(), JLayeredPane.DRAG_LAYER);
		layeredPane.moveToFront(tiempo.get_etiquetaTiempo());
		panel_principal.add(tiempo.get_etiquetaTiempo());
		panel_principal.repaint();
	}

	public void diminuir_movimientos() {
		movimientos.setText("Movimientos diponibles: " + mi_juego.get_nivel().getCant_movimientos());
		if(mi_juego.get_nivel().getCant_movimientos() == 0){
            int respuesta = JOptionPane.showConfirmDialog(null, "¡No te quedan movimientos! ¿Quieres reintentar el nivel?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
				timer.stop();
            	mi_juego.decrementar_vidas();
            	mi_juego.cambiar_nivel(mi_juego.get_nivel_actual());
            } else {
            	System.exit(0); 
            }
		}
	}

	public void disminuir_glaseados(){
		glaseados.setText(mi_juego.get_nivel().getCant_destruir_glaseados()+" /");
	}

	public void disminuir_gelatina(){
		gelatinas.setText(mi_juego.get_nivel().getCant_destruir_gelatina()+" /");	
	}

	public void disminuir_caramelos(){
		caramelos.setText(mi_juego.get_nivel().getCant_destruir_caramelos()+" /");
	}
	public void incrementar_puntaje(){
		puntaje.setText("Puntaje: " + mi_juego.get_jugador().getScore());
	}
	protected void cambiar_imagen(String i, int pos_x,int pos_y) {
		ImageIcon imgIcon = new ImageIcon(this.getClass().getResource(i));
		Image imgEscalada = imgIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		ImageIcon iconoEscalado = new ImageIcon(imgEscalada);
		JLabel imagen_objetivo = new JLabel(iconoEscalado);	
		imagen_objetivo.setBounds(pos_x+25, pos_y-25, size_label, size_label);
		panel_principal.add(imagen_objetivo);
	}
	
	public void fin() {
		resetGame();
		cierreDeJuego();
		JOptionPane.showConfirmDialog(null, "Felicidades, has completado el juego!", "Fin", JOptionPane.CLOSED_OPTION);
		System.exit(0); 
	}

	public void cambio_de_nivel(){
		timer.stop();
		int respuesta = JOptionPane.showConfirmDialog(null, "Felicidades, has completado el nivel "+mi_juego.get_nivel_actual()+", ¿Desea continuar?", "Confirmación", JOptionPane.YES_NO_OPTION);
		
		if (respuesta == JOptionPane.YES_OPTION) {
			mi_juego.actualizar_puntaje_anterior();
			mi_juego.cambiar_nivel(mi_juego.get_nivel_actual()+1);
		}else {
			System.exit(0); 
		}
	}

	protected void inicializar() {
		ImageIcon icono = new ImageIcon("src\\ImagenesCaramelos\\caramelos\\5.png");
        setIconImage(icono.getImage());
		setTitle("TdP 2023 Candy Crush");
		setSize(new Dimension(650, 450));
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(getContentPane());
		getContentPane().setLayout(new BorderLayout());
		
		panel_principal = new JLayeredPane();
		panel_principal.setSize(size_label * filas, size_label * columnas);
		panel_principal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {	
				switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT: 	{ mi_juego.mover_jugador(Juego.IZQUIERDA); break; }
					case KeyEvent.VK_RIGHT: { mi_juego.mover_jugador(Juego.DERECHA); break; }
					case KeyEvent.VK_UP: 	{ mi_juego.mover_jugador(Juego.ARRIBA);break; }
					case KeyEvent.VK_DOWN: 	{ mi_juego.mover_jugador(Juego.ABAJO); break; }
					case KeyEvent.VK_W:		{ if (!bloquear_intercambios) mi_juego.intercambiar(Juego.ARRIBA); break; }
					case KeyEvent.VK_S:		{ if (!bloquear_intercambios) mi_juego.intercambiar(Juego.ABAJO); break; }
					case KeyEvent.VK_A:		{ if (!bloquear_intercambios) mi_juego.intercambiar(Juego.IZQUIERDA); break; }
					case KeyEvent.VK_D:		{ if (!bloquear_intercambios) mi_juego.intercambiar(Juego.DERECHA); break; } 
				}
			}
		});
		
		panel_principal.setLayout(null);

		layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);

		getContentPane().add(panel_principal, BorderLayout.CENTER);
		
		panel_principal.setFocusable(true);
		
		JLabel nombre_jug = new JLabel("¡Bienvenido "+ mi_juego.get_jugador().getPlayer() + "!");
		nombre_jug.setBounds(400, 20, 144, 14);
		panel_principal.add(nombre_jug);

		JLabel vidas_jugador = new JLabel("Vidas:  "+ mi_juego.get_vidas() + " / 5");
		vidas_jugador.setBounds(400, 50, 144, 14);
		panel_principal.add(vidas_jugador);
		
		JLabel numero_nivel = new JLabel("Nivel: " + mi_juego.get_nivel_actual());
		numero_nivel.setBounds(400, 80, 107, 14);
		panel_principal.add(numero_nivel);
		
		JLabel tiempo = new JLabel("Tiempo restante:");
		tiempo.setBounds(400, 110, 121, 14);
		panel_principal.add(tiempo);
		
		movimientos = new JLabel("Movimientos diponibles: " + mi_juego.get_nivel().getCant_movimientos());
		movimientos.setBounds(400, 140, 160, 14);
		panel_principal.add(movimientos);
		
		JLabel contadorLabel = new JLabel("0" + mi_juego.get_nivel().getTiempo() + ":00");
		contadorLabel.setBounds(505, 110, 46, 14);
		panel_principal.add(contadorLabel);
		
		int pos_y = 170;//Responsive
		int pos_x= 400;
		int caramelos_restantes = mi_juego.get_nivel().getCant_destruir_caramelos();
		int gelatina_restantes = mi_juego.get_nivel().getCant_destruir_gelatina();
		int glaseados_restantes = mi_juego.get_nivel().getCant_destruir_glaseados();

		if(glaseados_restantes>0){
			glaseados = new JLabel(glaseados_restantes + " /");
			glaseados.setFont(new Font("Tahoma", Font.BOLD, 18));
			glaseados.setBounds(400, pos_y, 200, 14);
			cambiar_imagen(ColorEntidades.obtener_path_glaseado(mi_juego.get_tematica()), pos_x, pos_y);
			panel_principal.add(glaseados);
			pos_y = pos_y+30;
		}
		
		if(gelatina_restantes>0){
			gelatinas = new JLabel(gelatina_restantes + " /");
			gelatinas.setFont(new Font("Tahoma", Font.BOLD, 18));
			gelatinas.setBounds(400, pos_y, 200, 14);
			cambiar_imagen(ColorEntidades.obtener_path_gelatina( mi_juego.get_tematica()), pos_x, pos_y);
			panel_principal.add(gelatinas);
			pos_y = pos_y+30;
		}
		
		if(caramelos_restantes>0){
			caramelos = new JLabel(caramelos_restantes + " /");
			caramelos.setFont(new Font("Tahoma", Font.BOLD, 18));
			caramelos.setBounds(pos_x, pos_y, 200, 14);
			cambiar_imagen(ColorEntidades.obtener_path_color(mi_juego.get_nivel().getColor_caramelos_para_destruir(), mi_juego.get_tematica()), pos_x, pos_y);
			panel_principal.add(caramelos);
			pos_y = pos_y+30;
		}

		puntaje = new JLabel("Puntaje: " + mi_juego.get_jugador().getScore());
		puntaje.setBounds(pos_x, pos_y, 200, 14);
		panel_principal.add(puntaje);
		pos_y = pos_y+30;
/** 
		JLabel match = new JLabel("Match: 3");
		match.setBounds(5, 390, 200, 14);
		panel_principal.add(match);

		JLabel crear_potenciadores = new JLabel("Crear potenciadores: on");
		crear_potenciadores.setBounds(70, 390, 200, 14);
		panel_principal.add(crear_potenciadores);
 */

		JButton mejores_puntajes = new JButton("Mejores puntajes");
		mejores_puntajes.setBounds(400, 280, 140, 23);
		mejores_puntajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				top_player_grafico = new TopPlayerGrafico(mi_juego.get_top());
				top_player_grafico.setVisible(true);
				panel_principal.requestFocusInWindow();
			}
		});
		panel_principal.add(mejores_puntajes);

		JButton restar_game = new JButton("Reiniciar Nivel");
		restar_game.setBounds(400, 325, 140, 23);
		restar_game.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mi_juego.descativar_bombas();
				mi_juego.reiniciar_puntaje();
				timer.stop();
				mi_juego.cambiar_nivel(mi_juego.get_nivel_actual());
			}
		});

		panel_principal.add(restar_game);
		
		JButton reglas = new JButton("Mostrar reglas");
		reglas.setBounds(400, 370, 140, 23);
		reglas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reglas_del_juego = new Reglas(mi_juego.get_strategy());
				reglas_del_juego.setVisible(true);
				panel_principal.requestFocusInWindow();
			}
		});
		panel_principal.add(reglas);

		timer = new Timer(1000, new ActionListener() {
            int minutos = mi_juego.get_nivel().getTiempo();
            int segundos = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                segundos--;
                if (segundos < 0) {
                    segundos = 59;
                    minutos--;
                }

                String tiempoRestante = String.format("%02d:%02d", minutos, segundos);
                contadorLabel.setText(tiempoRestante);

                if (minutos <= 0 && segundos <= 0) {
                    ((Timer) e.getSource()).stop();
                    int respuesta = JOptionPane.showConfirmDialog(null, "¡Tiempo agotado! ¿Quieres reintentar el nivel?", "Confirmación", JOptionPane.YES_NO_OPTION);
                    
                    if (respuesta == JOptionPane.YES_OPTION) {
                    	mi_juego.decrementar_vidas();
                    	mi_juego.cambiar_nivel(mi_juego.get_nivel_actual());
                    } else {
                    	System.exit(0); 
                    }
                    
                }
            }
        });
		
        timer.start(); // Iniciar el temporizador cuando se ejecuta la aplicación
		

	}

	private void resetGame(){
		if( mi_juego.get_jugador().getScore() != 0 ) {
			Player p1 = new Player(mi_juego.get_jugador().getPlayer(), mi_juego.get_jugador().getScore());
			mi_juego.get_top().addPlayer(p1);
		    mi_juego.get_jugador().setScore(0);
			mi_juego.get_top().printPlayers();
		}
	}
	private void cierreDeJuego() {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(MenuPrincipal.configuration.getProperty("file"));
		    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		    objectOutputStream.writeObject(mi_juego.get_top());
		    objectOutputStream.flush();
		    objectOutputStream.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
