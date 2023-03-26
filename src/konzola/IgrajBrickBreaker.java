package konzola;

import java.util.Scanner;

public class IgrajBrickBreaker {
	private static int pozicijaPaddle;
	private static Scanner scanner;
	private static String potez;
	
	private static int ballX = 5;
	private static int ballY = 5;
	private static int ballXDirection = 1;
	private static int ballYDirection = -1;
    private static boolean[][] cigle = new boolean[3][7]; 
    private static boolean play;
    private static boolean sveCigle;
	
	public static void main(String[] args) {
		pozicijaPaddle = 5;
		play = true;
		sveCigle = true;
		
		prikaziStanje("");
		while(play || sveCigle) {
		scanner = new Scanner(System.in);
		potez = scanner.next();
		prikaziStanje(potez);}
	
	}
	
	private static void prikaziStanje(String potez) {
	    // update ball position based on direction
	    ballX += ballXDirection;
	    ballY += ballYDirection;

	    // check if ball hits walls or paddle
	    if(ballX == 1 || ballX == 9) {
	        ballXDirection *= -1;
	    }
	    if(ballY == 1 || (ballY == 18 && ballX >= pozicijaPaddle && ballX <= pozicijaPaddle + 2)) {
	        ballYDirection *= -1;
	    }
	    
	    for(int i=0;i<cigle.length;i++) {
	        for(int j=0;j<cigle[0].length;j++) {
	            if(cigle[i][j] == false) {
	                sveCigle = false;
	                break;
	            }
	        }
	        if(sveCigle == false) {
	            break;
	        }
	    }

	    if(potez.equals("d")) {
	        pozicijaPaddle++;
	    } else if(potez.equals("a")) {
	        pozicijaPaddle--;
	    }

	    if(pozicijaPaddle == 10) {
	        pozicijaPaddle = 9;
	    }
	    if(pozicijaPaddle == 0) {
	        pozicijaPaddle = 1;
	    }
	    
	    for(int i=0;i<21;i++) {
	        for(int j=0;j<11;j++) {
	            if(i == 0 || i == 20) {
	                System.out.print(" - ");
	            } else if(j == 0 || j==10) {
	                System.out.print(" | ");
	            } else if(i==19 && j==pozicijaPaddle) {
	                System.out.print("===");
	            } else if(ballY == 20) {
	                play = false;
	                break;
	            } else if(i==ballY && j==ballX) {
	                System.out.print(" O ");
	                int k = j-2;
	                int l = i-1;
	                if(l < 3 && k >= 0 && k<=6) {
	                    cigle[l][k] = true;
	                }
	            } else if((i == 1 || i == 2 || i == 3) && j > 1 && j < 9) {
	                int k = j-2;
	                int l = i-1;
	                if(cigle[l][k] == false) {
	                    System.out.print(" # ");
	                } else if(cigle[l][k] == true) {
	                    System.out.print("   ");
	                }
	            } else {
	                System.out.print("   ");
	            }
	        }
	    
	        System.out.println();
	        if(play == false) {
	            break;
	        }
	    }
	    if(sveCigle == true) {
	        play = false;
	    }
	    if(play == true) {
	        System.out.println("Naredni potez (Lijevo [A] | Bez Pomjeranja[S] Desno [D]): ");
	    }
	}

		
}
