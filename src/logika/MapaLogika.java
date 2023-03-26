package logika;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * 
 * @author Kerim Smajlovic
 * @see Klasa MapaLogika koja kreira cigle - blokove.
 *
 */
public class MapaLogika {
	public int mapa[][];
	public int sirinaBloka;
	public int visinaBloka;
	
	/**
	 * Konstruktor sa dva parametra
	 * @param row - broj redova blokova
	 * @param col - broj kolona blokova
	 */
	public MapaLogika(int row, int col) {
		mapa = new int[row][col];
		for(int i=0;i<mapa.length;i++) {
			for(int j=0;j<mapa[0].length;j++) {
				mapa[i][j] = 1;
			}
		}
		sirinaBloka = 540/col;
		visinaBloka = 150/row;
	}
	/**
	 * Funkcija koja crta blokove
	 * 
	 */
	public void draw(Graphics2D g) {
		for(int i=0;i<mapa.length;i++) {
			for(int j=0;j<mapa[0].length;j++) {
				if(mapa[i][j] > 0) {
					g.setColor(Color.red);
					g.fillRect(j*sirinaBloka+80, i*visinaBloka+50, sirinaBloka, visinaBloka);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*sirinaBloka+80, i*visinaBloka+50, sirinaBloka, visinaBloka);
				}
			}
	
		}
	}
	
	/**
	 * Funkcija koja postavlja vrijednost bloka na 0 jer je on udaren loptom.
	 */
	public void setVrijednostBloka(int vrijednost, int red, int kolona) {
		mapa[red][kolona] = vrijednost;
	}
	
	
	
}
	

