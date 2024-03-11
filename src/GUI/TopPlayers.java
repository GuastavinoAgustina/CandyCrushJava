package GUI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopPlayers implements Serializable {
	private List<Player> ranking;
	
	public TopPlayers() {
		this.ranking = new ArrayList<Player>();
	}
	
	public void addPlayer( Player p ) {
		this.ranking.add(p);
	}
	public List<Player> posiciones(){
		return ranking;
	}
	public void printPlayers() {
		Collections.sort(this.ranking, Collections.reverseOrder());
		System.out.println();
		int i = 0;
		for(Player p : this.ranking) {
			System.out.println(p.getPlayer() + " " + p.getScore());
			if( i== 5 ) break;
			i++;
		}
	}
}
