package cardGame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class GameUI {
	private JFrame frame;
	private JPanel panel;
	private JButton startButton;


	public GameUI() {
		frame = new JFrame("Card Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
		frame.setLayout(new BorderLayout());

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel titleLabel = new JLabel("Welcome to the Card Game!");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

		startButton = new JButton("Start Game");
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Game Started!");
			}
		});

		 // Adicionando os componentes ao painel
        panel.add(titleLabel);
        panel.add(startButton);

        // Adicionando o painel ao frame
        frame.add(panel, BorderLayout.CENTER);

        // Tornando a janela visível
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameUI());
    }
}