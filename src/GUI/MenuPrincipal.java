package GUI;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Properties;
import Logica.Juego;


public class MenuPrincipal extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private int tematica_fabrica;
    public static Properties configuration;
    protected TopPlayers top ;

    public static void main(String[] args) {
        loadConfiguration();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuPrincipal frame = new MenuPrincipal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MenuPrincipal() {
        ImageIcon icono = new ImageIcon("src\\ImagenesCaramelos\\caramelos\\5.png");
        setIconImage(icono.getImage());
        setTitle("TdP 2023 Candy Crush");
		setSize(new Dimension(650, 450));
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(contentPane);;
		getContentPane().setLayout(new BorderLayout());
		
		JPanel panel_principal = new JPanel();
		panel_principal.setSize(new Dimension(650, 450));
		
		panel_principal.setLayout(null);
		
		getContentPane().add(panel_principal, BorderLayout.CENTER);
		
		panel_principal.setFocusable(true);
		
		JLabel titulo = new JLabel(" TdP 2023 Candy Crush");
        titulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		titulo.setBounds(225, 100, 400, 14);
		panel_principal.add(titulo);

        JLabel selecione = new JLabel("Seleccione una temática:");
        selecione.setFont(new Font("Tahoma", Font.PLAIN, 15));
		selecione.setBounds(240,  150, 500, 20);
		panel_principal.add(selecione);

        JLabel nombre = new JLabel("Nombre:");
        nombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nombre.setBounds(240,  240, 500, 20);
		panel_principal.add(nombre);
        textField = new JTextField();
		textField.setBounds(300, 240, 114, 19);
		panel_principal.add(textField);
		textField.setColumns(10);

        String[] options = {"Caramelos", "Gatos"};
        
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboBox.setBounds(240, 180, 160, 20);
        comboBox.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                String nombre = (String)comboBox.getSelectedItem();
                if(nombre == "Caramelos"){
                    tematica_fabrica = 0;
                }else{
                    tematica_fabrica = 1;
                }
            }
        });
        panel_principal.add(comboBox);

        top = new TopPlayers();
		try {
			FileInputStream fileInputStream = new FileInputStream(configuration.getProperty("file"));
		    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            top = (TopPlayers) objectInputStream.readObject();
            objectInputStream.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
        JButton start_button = new JButton("Start Game");
        start_button.setFont(new Font("Tahoma", Font.BOLD, 14));
        start_button.setBounds(240, 310, 160, 40);
        
        start_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Player jugador = new Player(textField.getText(),0);
                new Juego(tematica_fabrica, jugador, top);
			}
		});
        panel_principal.add(start_button);
        textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {	
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    start_button.doClick();
                }
            }
        });
        panel_principal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {	
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    start_button.doClick();
                }
            }
        });

    } 
    
    
    private static void loadConfiguration() {
		try (
			InputStream input = new FileInputStream("./configuration.properties")) {
            MenuPrincipal.configuration = new Properties();
            MenuPrincipal.configuration.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
}