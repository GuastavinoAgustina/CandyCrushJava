package GUI;

import java.io.Serializable;

public class Player implements Comparable<Player>, Serializable{
	private String player;
	private Integer score;
	
	public Player( String name, Integer puntos ) {
		this.player = name;
		this.score = puntos;
	}
	
	public String getPlayer () {
		return this.player;
	}
	
	public Integer getScore() {
		return this.score;
	}
	
	@Override
	public int compareTo(Player arg0) {
		return this.score.compareTo(arg0.getScore());
	}

	public void setScore(int i) {
		score = i;
	}
	public void sumar_puntaje(int puntaje){
		score = score + puntaje;
	}
}
