import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    //Atribut Gambar;
    Image BackgroundImage;
    Image BirdImage;
    Image LowerPipeImage;
    Image UpperPipeImage;
    Image GameOverImage;
    Image GetReadyImage;
    Image[] numberImages;

    //Kita bikin Atribut player
    int playerStartPosX = frameWidth / 8;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    //Kita bikin Player
    Player player;

    //Kita bikin Atribut pipa
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    ArrayList<Pipe> pipes;

    //Logika game
    Timer gameloop;
    Timer pipesCooldown;
    int gravity = 1;
    boolean isGameOver = false;
    boolean isGameStarted = false;

    //Score
    int score = 0;
    int digitWidth = 24; // Lebar gambar angka (sesuaikan dengan ukuran gambar Anda)
    int digitHeight = 36; // Tinggi gambar angka (sesuaikan dengan ukuran gambar Anda)

    //kita bikin konstruktor dulu
    public FlappyBird(){
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setFocusable(true);
        addKeyListener(this);
        setLayout(null);

        //Buat Load Gambarnya
        BackgroundImage = new ImageIcon(getClass().getResource("assets/japannese.png")).getImage();
        BirdImage = new ImageIcon(getClass().getResource("assets/Yaemiko.png")).getImage();
        LowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        UpperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();
        GameOverImage = new ImageIcon(getClass().getResource("assets/gameover.png")).getImage();
        GetReadyImage = new ImageIcon(getClass().getResource("assets/getready.png")).getImage();

        // Load gambar angka
        numberImages = new Image[10];
        for (int i = 0; i < 10; i++) {
            numberImages[i] = new ImageIcon(getClass().getResource("assets/" + i + ".png")).getImage();
        }

        initGame();
    }

    public void initGame() {
        // Hentikan timer yang sedang berjalan jika ada
        if (gameloop != null) {
            gameloop.stop();
        }
        if (pipesCooldown != null) {
            pipesCooldown.stop();
        }

        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, BirdImage);
        pipes = new ArrayList<Pipe>();
        score = 0;
        isGameOver = false;
        isGameStarted = false;

        // Game loop tetap berjalan untuk animasi bird di layar "Get Ready"
        gameloop = new Timer(1000/60, this);
        gameloop.start();
    }

    public void startGame() {
        if (!isGameStarted && !isGameOver) {
            isGameStarted = true;

            // Hentikan timer pipa lama jika ada
            if (pipesCooldown != null) {
                pipesCooldown.stop();
            }

            pipesCooldown = new Timer(1500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    placePipes();
                }
            });
            pipesCooldown.start();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(BackgroundImage, 0, 0, frameWidth, frameHeight, null);
        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for(int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }

        // Gambar skor menggunakan gambar angka jika game sudah dimulai
        if (isGameStarted) {
            drawScore(g);
        }

        // Tampilkan layar Get Ready jika game belum dimulai
        if (!isGameStarted && !isGameOver) {
            // Semi-transparent overlay
            g.setColor(new Color(0, 0, 0, 70));
            g.fillRect(0, 0, frameWidth, frameHeight);

            // Draw Get Ready image
            int getReadyWidth = GetReadyImage.getWidth(null);
            int getReadyHeight = GetReadyImage.getHeight(null);
            g.drawImage(GetReadyImage,
                    (frameWidth - getReadyWidth) / 2,
                    frameHeight / 3,
                    getReadyWidth,
                    getReadyHeight,
                    null);

            // Instruksi untuk memulai permainan
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
//            String startText = "Press SPACE to Start";
            FontMetrics fm = g.getFontMetrics();
//            int textWidth = fm.stringWidth(startText);
//            g.drawString(startText, (frameWidth - textWidth) / 2, frameHeight / 2 + 50);
        }

        // Tampilkan layar Game Over jika game berakhir
        if (isGameOver) {
            // Semi-transparent overlay
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, frameWidth, frameHeight);

            // Gambar gameover.png
            int gameOverWidth = GameOverImage.getWidth(null);
            int gameOverHeight = GameOverImage.getHeight(null);
            g.drawImage(GameOverImage,
                    (frameWidth - gameOverWidth) / 2,
                    frameHeight / 3,
                    gameOverWidth,
                    gameOverHeight,
                    null);

            // Gambar instruksi restart
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            String restartText = "Press 'R' to Restart";
            FontMetrics fm = g.getFontMetrics();
            int restartWidth = fm.stringWidth(restartText);
            g.drawString(restartText, (frameWidth - restartWidth) / 2, frameHeight / 2 + 50);

            // Gambar skor
            drawScore(g);
        }
    }

    private void drawScore(Graphics g) {
        // Convert score to string
        String scoreStr = Integer.toString(score);
        int totalWidth = scoreStr.length() * digitWidth;

        // Calculate starting X position to center the score
        int startX = (frameWidth - totalWidth) / 2;

        // Draw each digit
        for (int i = 0; i < scoreStr.length(); i++) {
            int digit = Character.getNumericValue(scoreStr.charAt(i));
            g.drawImage(numberImages[digit],
                    startX + (i * digitWidth),
                    30, // Y position from top
                    digitWidth,
                    digitHeight,
                    null);
        }
    }

    public void move() {
        // Pada layar "Get Ready", buat animasi bird mengambang
        if (!isGameStarted && !isGameOver) {
            // Animasi bird mengambang naik turun di posisi awal
            int time = (int)(System.currentTimeMillis() / 100) % 20;
            int oscillation = 0;
            if (time < 10) {
                oscillation = time;
            } else {
                oscillation = 20 - time;
            }
            player.setPosY(playerStartPosY - 5 + oscillation);
            return;
        }

        if (isGameOver) return;

        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());

            // Check if player has passed the pipe
            if (!pipe.getPassed() && player.getPosX() > pipe.getPosX() + pipe.getWidth()) {
                pipe.setPassed(true);
                // Only count score for upper pipes to avoid counting twice
                if (pipe.getPosY() <= 0) {
                    score++;
                }
            }
        }
    }

    public void placePipes() {
        if (isGameOver || !isGameStarted) return;

        int randomPosY = (int) (pipeStartPosY - pipeHeight/4 - Math.random() * (pipeHeight/2));
        int openingSpace = frameHeight/4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, UpperPipeImage);
        upperPipe.setVelocityX(-4);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, LowerPipeImage);
        lowerPipe.setVelocityX(-4);
        pipes.add(lowerPipe);
    }

    public void checkCollision() {
        if (!isGameStarted || isGameOver) return;

        // Check if player hits the ground
        if (player.getPosY() + player.getHeight() >= frameHeight) {
            gameOver();
            return;
        }

        // Check if player hits any pipe
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);

            if (player.getPosX() < pipe.getPosX() + pipe.getWidth() &&
                    player.getPosX() + player.getWidth() > pipe.getPosX() &&
                    player.getPosY() < pipe.getPosY() + pipe.getHeight() &&
                    player.getPosY() + player.getHeight() > pipe.getPosY()) {
                gameOver();
                return;
            }
        }
    }

    private void gameOver() {
        isGameOver = true;
        if (pipesCooldown != null) {
            pipesCooldown.stop();
        }
    }

    public void cleanPipes() {
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            if (pipe.getPosX() + pipe.getWidth() < 0) {
                pipes.remove(i);
                i--;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        checkCollision();
        cleanPipes();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!isGameStarted && !isGameOver) {
                // Mulai game saat spasi ditekan di layar "Get Ready"
                startGame();
            } else if (isGameStarted && !isGameOver) {
                // Bird terbang saat spasi ditekan selama game
                player.setVelocityY(-10);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_R && isGameOver) {
            // Restart game
            initGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}