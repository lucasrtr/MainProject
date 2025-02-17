package cardGame;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Card Game!");
        System.out.println("Press Enter to begin the first round.");
        scanner.nextLine();
        
        Game game = new Game();
        
        for (int round = 1; round <= 13; round++) {
            System.out.println("Round " + round);
            System.out.println("Press Enter to draw Utility cards.");
            scanner.nextLine();
            
            game.drawUtilityCards(game.calculateUtilityCards());
            game.showUtilityCards(); // Exibir cartas sorteadas para o jogador
            
            System.out.println("Press Enter to reveal Hazard cards.");
            scanner.nextLine();
            
            game.drawFromHazardPile(game.calculateHazardCards());
            game.showHazardCards(); // Exibir as cartas de perigo
            
            System.out.println("Press Enter to start combat.");
            scanner.nextLine();
            game.startCombat();
        }
        
        System.out.println("The Player survived the 13 rounds! Now it will face the final boss.");












			scanner.close();
		}

	}

