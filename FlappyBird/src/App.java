import javax.swing.*;

public class App {
    public static void main(String[] args){
        // Create and show the start menu first
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StartMenu startMenu = new StartMenu();
                startMenu.setVisible(true);
            }
        });
    }
}
