import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JFrame {
    private JPanel mainPanel;
    private JButton startButton;
    private JButton aboutButton;
    private JLabel creditLabel;

    public StartMenu() {
        setTitle("Flappy Bird");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300); // Ukuran lebih besar untuk menampung tombol tambahan
        setLocationRelativeTo(null);
        setResizable(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(115, 200, 255));

        // Panel untuk title dan credit di bagian atas
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setOpaque(false);

        // Buat gambar flappybird.png sebagai title
        ImageIcon flappyBirdIcon = new ImageIcon(getClass().getResource("assets/flappybird.png"));
        JLabel flappyBirdImage = new JLabel(flappyBirdIcon);
        flappyBirdImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create credit label
        creditLabel = new JLabel("Made by : Meoongkill (2300330)");
        creditLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        creditLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditLabel.setForeground(Color.BLACK);

        // Tambahkan padding/jarak
        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(flappyBirdImage);
        topPanel.add(Box.createVerticalStrut(5));
        topPanel.add(creditLabel);
        topPanel.add(Box.createVerticalStrut(10));

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);

        // Start Game button
        startButton = new JButton("Mulai Gamenya!");
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setMaximumSize(new Dimension(200, 50));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setBackground(new Color(76, 175, 80));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        // About Us button
        aboutButton = new JButton("About Us");
        aboutButton.setFont(new Font("Arial", Font.BOLD, 16));
        aboutButton.setMaximumSize(new Dimension(200, 50));
        aboutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        aboutButton.setBackground(new Color(33, 150, 243));
        aboutButton.setForeground(Color.WHITE);
        aboutButton.setFocusPainted(false);

        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAboutDialog();
            }
        });

        // Add buttons to panel with spacing
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(startButton);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(aboutButton);
        buttonPanel.add(Box.createVerticalStrut(20));

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void startGame() {
        // Close this frame
        dispose();

        // Create and show the game frame
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        FlappyBird flappyBird = new FlappyBird();
        frame.add(flappyBird);
        frame.pack();
        frame.setVisible(true);
    }

    private void showAboutDialog() {
        JDialog aboutDialog = new JDialog(this, "About Us", true);
        aboutDialog.setSize(300, 200);
        aboutDialog.setLocationRelativeTo(this);

        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BorderLayout(1, 1));
        dialogPanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

        JLabel titleLabel = new JLabel("Flappy Bird KW 7");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JTextArea infoArea = new JTextArea(
                "Game ini adalah replika sederhana dari Flappy Bird.\n\n" +
                        "Dibuat oleh: Muhammad Fathan Putra (2300330)\n\n" +
                        "Untuk keperluan Tugas Praktikum 6 DPBO.");
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setBackground(dialogPanel.getBackground());

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> aboutDialog.dispose());

        dialogPanel.add(titleLabel, BorderLayout.NORTH);
        dialogPanel.add(infoArea, BorderLayout.CENTER);
        dialogPanel.add(closeButton, BorderLayout.SOUTH);

        aboutDialog.add(dialogPanel);
        aboutDialog.setResizable(false);
        aboutDialog.setVisible(true);
    }
}