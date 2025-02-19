package cardGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GameUIv2 {
	private JFrame frame;
	private JTextArea gameTextArea;
	private JTextArea statusTextArea;
	private JTextArea logTextArea;
	private JTextField commandField; // Nova área de entrada de comando
	private JButton nextRoundButton;
	private Game game; //Instância da classe Game


	public GameUIv2() {
		frame = new JFrame("HAZARD'S QUEST");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 650);
		frame.setLayout(new BorderLayout());

		// Inicializando o jogo
		game = new Game(); // Cria a instância do jogo

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

		// Área principal do jogo (onde as cartas vão aparecer)
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

		// Área de entrada de comando
		commandField = new JTextField();
		commandField.setBackground(Color.BLACK);
		commandField.setForeground(Color.WHITE);
		commandField.setFont(new Font("Courier New", Font.PLAIN, 14));
		commandField.setBorder(BorderFactory.createTitledBorder("- Command -"));

		// Action listener para o campo de comando
		commandField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String command = commandField.getText();
				handleCommand(command);  // Processa o comando do usuário
				commandField.setText("");  // Limpa o campo de comando após execução
			}
		});

		// Botão de próxima rodada
		nextRoundButton = new JButton("Next Round");
		nextRoundButton.setBackground(Color.RED);
		nextRoundButton.setForeground(Color.BLACK);
		nextRoundButton.setFont(new Font("Courier New", Font.BOLD, 14));

		// Action listener para o botão de próxima rodada
		nextRoundButton.addActionListener(e -> {
			// Sorteia 3 cartas de utilidade quando o botão é clicado 
			int numberOfCards = 3; // Sempre sorteia 3 cartas na primeira rodada !!!
			java.util.List<String> drawnCards = game.drawUtilityCards(numberOfCards);
			updateGameArea(drawnCards); // Atualiza a área do jogo com as cartas sorteadas
			updateLogArea("Drawn 3 utility cards: " + String.join(", ", drawnCards)); // Atualiza o histórico
		});

		// Layout
		mainPanel.add(statusScroll, BorderLayout.WEST);
		mainPanel.add(gameScroll, BorderLayout.CENTER);
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.add(logScroll, BorderLayout.SOUTH);
		frame.add(commandField, BorderLayout.NORTH);
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

	// Atualiza a área de jogo com os títulos das cartas sorteadas
	private void updateGameArea(java.util.List<String> drawnCards) {
		StringBuilder gameText = new StringBuilder();
		for (String card : drawnCards) {
			gameText.append("Drawn Cards: ").append(card).append("\n");
		}
		gameTextArea.setText(gameText.toString()); // Atualiza a area de jogo
	}

	// Atualiza o histórico (log) com a ação realizada
	private void updateLogArea(String logMessage) {
		String currentLog = logTextArea.getText();
		String updatedLog = currentLog + "\n" + logMessage;
		logTextArea.setText(updatedLog);  // Atualiza o histórico de eventos
	}

	// Processa o comando inserido pelo usuário
	private void handleCommand(String command) {
		// Aqui você pode adicionar diferentes ações conforme o comando
		if (command.equalsIgnoreCase("help")) {
			updateLogArea("Available commands: 'help', 'next', 'status', etc.");
		} else if (command.equalsIgnoreCase("status")) {
			updateLogArea("Player status: Health: 15, Weapon: Sword, Shield: Wooden");
		} else {
			updateLogArea("Unknown command: " + command);
		}
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(GameUIv2::new);
	}
}