package gui;

import javax.swing.JFrame;

public class Okvir extends JFrame{
	Okvir(){
		this.setBounds(10,10,700,600);
		this.setTitle("Brick Breaker");
		this.setResizable(false);
		IgricaPanel panel = new IgricaPanel();
		this.add(panel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
	}
	
	public void prikaziOkvir() {
		setVisible(true);
	}
}
