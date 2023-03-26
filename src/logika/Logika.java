package logika;

/**
 * 
 * 
 * @author Kerim Smajlovic
 * @see Glavna Klasa Logika zadauzena za logiku igrice
 */
public class Logika {
	public int igracX ;
	public int pozicijaLopteX;
	public int pozicijaLopteY ;
	public int smjerLopteX ;
	public int smjerLopteY;
	public int ukupniBlokovi = 21;
	public boolean play = false;
	public int score = 0;
	private MapaLogika mapa;
	

	/**
	 * Konstruktor bez parametara koji postavlja vrijednosti igraca, koordinate i smjer lopte
	 */
	public Logika() {
		igracX = 310;
		 pozicijaLopteX = 120;
		 pozicijaLopteY = 350;
		 smjerLopteX = -1;
		 smjerLopteY = -2;
		
	}
	
/**
 * Funkcija koja provjerava da li je igrac pomjeren desno.
 */
	public void provjeriDesno() {
		if(igracX >= 600) {
			igracX = 600;
		}else {
			/**
			 * Ukoliko je igrac pomjeren desno, pozivamo funkciju koja ga pomjera udesno.
			 */
			pomjeriDesno();
		}
	}
	/**
	 * Funkcija koja provjerava da li je igrac pomjeren lijevo.
	 */
	public void provjeriLijevo() {
		if(igracX < 10) {
			igracX = 10;
		}else {
			/**
			 * Ukoliko je igrac pomjeren lijevo, pozivamo funkciju koja ga pomjera ulijevo.
			 */
			pomjeriLijevo();
		}
	}
	/**
	 * Funkcija za pomjeranje igraca udesno
	 */
	public void pomjeriDesno() {
		play = true;
		igracX += 20;
	}
	/**
	 * Funkcija za pomjeranje igraca ulijevo
	 */
	public void pomjeriLijevo() {
		play = true;
		igracX -= 20;
	}
	/**
	 * Boolean funkcija koja provjerava kada je pritisnut ENTER je li igra zavrsena, da bi se pokrenula nova.
	 * @return vraca true ili false u zavisnosti od toga da li je play true ili false
	 */
	public boolean provjeriEnter() {
		if(play == false) {
			play = true;
			pozicijaLopteX = 120;
			pozicijaLopteY = 350;
			smjerLopteX = -1;
			smjerLopteY = -2;
			igracX = 310;
			score = 0;
			ukupniBlokovi = 21;
			MapaLogika mapa = new MapaLogika(3,7);
			return true;
			
			
		}
		return false;
	}
	/**
	 * Funkcija ce update-ovati pozicije lopte i provjerit da li je lopta dosla do coska panela i time je sprijeciti da
	 * izadje iz panela i ona ce se samo odbiti od ivicu.
	 */
	public void update() {
		pozicijaLopteX += smjerLopteX;
		pozicijaLopteY += smjerLopteY;
		
		if(pozicijaLopteX < 0) {
			smjerLopteX = -smjerLopteX;
		}
		if(pozicijaLopteY < 0) {
			smjerLopteY = -smjerLopteY;
		}
		if(pozicijaLopteX > 670) {
			smjerLopteX = -smjerLopteX;
		}
	}
	
}
