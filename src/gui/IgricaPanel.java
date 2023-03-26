package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import logika.Logika;
import logika.MapaLogika;

/**
 * 
 * @author Kerim Smajlovic
 * @see Glavna klasa - panel igrice
 *
 */
public class IgricaPanel extends JPanel implements KeyListener,ActionListener
{

	private MapaLogika mapa;	
	private Timer vrijeme;
	private int delay = 8;

	
	private Logika logika = new Logika();

	/**
	 * Konstruktor bez parametara koji dodaje key listenere i loada mapu.
	 */
	public IgricaPanel() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		mapa = new MapaLogika(3, 7); // br. redova i kolona.
		vrijeme = new Timer(delay, this);
		vrijeme.start();
		
	}
	
	/**
	 * funkcija paint zaduzena za crtanje pozadine, mape - blokova, lopte, rezultata i drugih 
	 * potrebnih podataka u prozor.
	 */
	public void paint(Graphics g) {
		
		// pozadina
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		mapa.draw((Graphics2D)g);
		
		// borderi - ivice,okvir
		g.setColor(Color.yellow);
		g.fillRect(0,0,3,592);
		g.fillRect(0,0,692,3);
		g.fillRect(0,0,692,3);
		g.fillRect(691,0,3,592);
		
		// scores - rezultati
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD,25));
		g.drawString(""+ logika.score, 590,30);
		
		// paddle - igrac
		g.setColor(Color.green);
		g.fillRect(logika.igracX, 550, 100, 8);
		
		// lopta
		g.setColor(Color.blue);
		g.fillOval(logika.pozicijaLopteX, logika.pozicijaLopteY, 20, 20);
		
		
			
		
		if(logika.ukupniBlokovi <= 0) {
			logika.play = false;
			logika.smjerLopteX = 0;
			logika.smjerLopteY = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Pobijedili ste: ",260,300);
			
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Pritisnite Enter za Restart", 230, 350);
		}
		
		if(logika.pozicijaLopteY > 570) {
			logika.play = false;
			logika.smjerLopteX = 0;
			logika.smjerLopteY = 0;
			g.setColor(Color.red);
			g.setFont(new Font("dialog",Font.BOLD,25));
			g.drawString("Game Over, Scores: "+logika.score,190,300);
			
			g.setFont(new Font("dialog",Font.BOLD,25));
			g.drawString("Pritisnite Enter za Restart", 230, 350);
		}
		
		g.dispose();
		
	}
	/**
	 * funkcija actionPerformed koja ce se pozvati kada se zapocne igra (prethodno pritisnutom strelicom
	 * za desno ili lijevo.
	 * zaduzena je za icrtavanje lopte na ekranu pri pomjeranju, detekcija pogodjenih cigli(blokova) i 
	 * njihovih brisanja, te dodavanja podataka na rezultat pri uspjesnom pogotku.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//vrijeme.start();
		if(logika.play) {
			if(new Rectangle(logika.pozicijaLopteX, logika.pozicijaLopteY, 20,20).intersects(new Rectangle(logika.igracX,550,100,8))) {
				logika.smjerLopteY = - logika.smjerLopteY;
			}
			
			A: for(int i=0;i<mapa.mapa.length;i++) {
				for(int j=0;j<mapa.mapa[0].length;j++) {
					if(mapa.mapa[i][j] > 0) {
						int blokX = j* mapa.sirinaBloka+80;
						int blokY = i* mapa.visinaBloka+50;
						int visinaBloka = mapa.visinaBloka;
						int sirinaBloka = mapa.sirinaBloka;
						
						Rectangle rect = new Rectangle(blokX,blokY,sirinaBloka,visinaBloka);
						Rectangle loptaRect = new Rectangle(logika.pozicijaLopteX,logika.pozicijaLopteY,20,20);
						Rectangle blokRect = rect;
						
						if(loptaRect.intersects(blokRect)) {
							mapa.setVrijednostBloka(0, i, j);
							logika.ukupniBlokovi--;
							logika.score += 5;
							
							if(logika.pozicijaLopteX+19 <= blokRect.x || logika.pozicijaLopteX+1 >= blokRect.x + blokRect.width) {
								logika.smjerLopteX = -logika.smjerLopteX;
							}else {
								logika.smjerLopteY = -logika.smjerLopteY;
							}
							
							break A;
						}
					}
				}
			}
			
			/**
			 * poziva se logika pomjeranja pozicije i smjera lopte sa svojim x i y koordinatama
			 */
			logika.update();
		}
		/**
		 * nakon svake akcije update-amo izgled
		 */
		repaint(); 
		
	}


	
	/**
	 * Funkcija se poziva kada pritisnemo neki key, ako je to desna, lijeva strelica ili ENTER
	 * pozvat ce se funkcija iz logike koja ce u slucaju strelica zapoceti igricu novu i/ili pomjeriti
	 * paddle - igraca lijevo ili desno. U slucaju klika na ENTER nakon zavrsene igre zapocinje se nova igra sto je definisano u logici.
	 * 
	 * 
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			logika.provjeriDesno();
		}	
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			logika.provjeriLijevo();	
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!logika.play) {
				logika.play = true;
				logika.pozicijaLopteX = 120;
				logika.pozicijaLopteY = 350;
				logika.smjerLopteX = -1;
				logika.smjerLopteY = -2;
				logika.igracX = 310;
				logika.score = 0;
				logika.ukupniBlokovi = 21;
				mapa = new MapaLogika(3,7);
				
				repaint();
			}
		}
		}
	//}
	
	

	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	
	
	
	
	

	
	
}
