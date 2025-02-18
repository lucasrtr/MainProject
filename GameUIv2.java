package cardGame;

import javax.swing.*;
import java.awt.*;

public class GameUIv2 {
    private JFrame frame;
    private JTextArea mainArea, playerStatus, actionArea, logArea;
    private JButton nextRoundButton;
    private Game game;

    public GameUIv2() {
        game = new Game();
        
        frame = new JFrame("HAZARD'S QUEST");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        
        // Área Principal (Eventos do Jogo)
        mainArea = new JTextArea();
        mainArea.setEditable(false);
        mainArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        mainArea.setBorder(BorderFactory.createTitledBorder("Game"));
        frame.add(new JScrollPane(mainArea), BorderLayout.CENTER);
        
        // Área do Jogador (Status)
        playerStatus = new JTextArea();
        playerStatus.setEditable(false);
        playerStatus.setFont(new Font("Monospaced", Font.PLAIN, 14));
        playerStatus.setBorder(BorderFactory.createTitledBorder("Player Status"));
        frame.add(new JScrollPane(playerStatus), BorderLayout.WEST);
        
        // Área de Ações
        actionArea = new JTextArea();
        actionArea.setEditable(false);
        actionArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        actionArea.setBorder(BorderFactory.createTitledBorder("Actions"));
        frame.add(new JScrollPane(actionArea), BorderLayout.EAST);
        
        // Área de Logs
        logArea = new JTextArea(5, 50);
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        logArea.setBorder(BorderFactory.createTitledBorder("Log"));
        frame.add(new JScrollPane(logArea), BorderLayout.SOUTH);
        
        // Botão de Próxima Rodada
        nextRoundButton = new JButton("Next Round");
        nextRoundButton.addActionListener(e -> nextRound());
        frame.add(nextRoundButton, BorderLayout.NORTH);
        
        frame.setVisible(true);
        displayWelcomeMessage();
    }

    private void displayWelcomeMessage() {
        mainArea.setText("Welcome to Hazard's Quest!\nPress 'Next Round' to start.\n");
        updatePlayerStatus();
    }

    private void nextRound() {
        mainArea.append("\nNew round begins!\n");
        logArea.append("[LOG] Round started.\n");
        // Lógica do jogo aqui
        updatePlayerStatus();
    }

    private void updatePlayerStatus() {
        playerStatus.setText("Health: " + game.getPlayerLife() + "\n");
        playerStatus.append("Weapon: " + game.getEquippedWeapon() + "\n");
        playerStatus.append("Shield: " + game.getEquippedShield() + "\n");
        playerStatus.append("Ability: " + game.getActiveAbility() + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameUI::new);
    }
}
