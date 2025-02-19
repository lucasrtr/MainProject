package cardGame;

import javax.swing.*;
import java.awt.*;

public class GameUIv2 {
    private JFrame frame;
    private JTextArea gameTextArea;
    private JTextArea statusTextArea;
    private JTextArea logTextArea;
    private JButton nextRoundButton;

    public GameUIv2() {
        frame = new JFrame("HAZARD'S QUEST");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 650);
        frame.setLayout(new BorderLayout());
        
        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);
        
        // Área de status do jogador
        statusTextArea = new JTextArea(6, 30);
        statusTextArea.setEditable(false);
        statusTextArea.setBackground(Color.BLACK);
        statusTextArea.setForeground(Color.YELLOW);
        statusTextArea.setFont(new Font("Courier New", Font.PLAIN, 15));
        JScrollPane statusScroll = new JScrollPane(statusTextArea);
        statusScroll.setBorder(BorderFactory.createTitledBorder("- Player Status -"));
        
        // Área principal do jogo
        gameTextArea = new JTextArea(20, 50);
        gameTextArea.setEditable(false);
        gameTextArea.setBackground(Color.BLACK);
        gameTextArea.setForeground(Color.WHITE);
        gameTextArea.setFont(new Font("Courier New", Font.PLAIN, 16));
        JScrollPane gameScroll = new JScrollPane(gameTextArea);
        gameScroll.setBorder(BorderFactory.createTitledBorder("- Game -"));
        
        // Área de log
        logTextArea = new JTextArea(8, 50);
        logTextArea.setEditable(false);
        logTextArea.setBackground(Color.BLACK);
        logTextArea.setForeground(Color.WHITE);
        logTextArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        JScrollPane logScroll = new JScrollPane(logTextArea);
        logScroll.setBorder(BorderFactory.createTitledBorder("- Log -"));
        
        // Botão de próxima rodada
        nextRoundButton = new JButton("Next Round");
        nextRoundButton.setBackground(Color.DARK_GRAY);
        nextRoundButton.setForeground(Color.YELLOW);
        nextRoundButton.setFont(new Font("Courier New", Font.BOLD, 14));
        
        // Layout
        mainPanel.add(statusScroll, BorderLayout.WEST);
        mainPanel.add(gameScroll, BorderLayout.CENTER);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(logScroll, BorderLayout.SOUTH);
        frame.add(nextRoundButton, BorderLayout.NORTH);
        
        // Iniciando tela
        frame.setVisible(true);
        
        // Exemplo de inicialização
        statusTextArea.setText("Health: 15\n" +
                "\nWeapon: \n" +
                "\nShield: \n" +
                "\nAbility: \n" +
                "\nDivinity: \n" +
                "\nDisease: \n" +
                "\nCurse: \n" + 
                "\nBackpack:\n Slot 1: Empty\n \n Slot 2: Empty");
        
        gameTextArea.setText("Welcome to Hazard's Quest!\nPress 'Next Round' to start.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameUIv2::new);
    }
}
