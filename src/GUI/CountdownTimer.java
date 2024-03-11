package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class CountdownTimer {
    
    private int tiempoRestante;
    private Timer timer;
    private JLabel etiquetaTiempo;

    public CountdownTimer(int tiempoInicial) {
        this.tiempoRestante = tiempoInicial;
        etiquetaTiempo = new JLabel(Integer.toString(tiempoRestante));
        etiquetaTiempo.setHorizontalAlignment(JLabel.CENTER);
    }
    
    public void iniciarContador(){
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tiempoRestante > 0) {
                    tiempoRestante--;
                    etiquetaTiempo.setText(Integer.toString(tiempoRestante));
                } else {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "¡Tiempo finalizado! Usted ha perdido");
                    System.exit(0); 
                }
            }
        });
        timer.start();
    }
    public JLabel get_etiquetaTiempo(){
        etiquetaTiempo.setForeground(new Color(255, 255, 255));
        etiquetaTiempo.setFont(new Font("Tahoma", Font.BOLD, 17));
        return etiquetaTiempo;
    }
    public void set_bounds(int x, int y, int w, int h ){
        etiquetaTiempo.setBounds(x, y, w, h);
    }
    public void detener_timer(){
        etiquetaTiempo.setText(" ");
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }
    public void reiniciar_timer(){
        detener_timer();
    }
}
