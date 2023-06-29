package VisualPanel;

import com.codecool.marsexploration.mapexplorer.buildings.CommandCenter;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.MarsRover;

import javax.swing.*;
import javax.swing.text.html.parser.Entity;
import java.awt.*;

public class VisualPanel extends JPanel implements Runnable {
    final int originalTileSize = 8;//dla wyświetlania 16x16
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 32;
    final int maxScreenRow = 23;
    final int screenWidth = tileSize * maxScreenCol;//
    final int screenHeight = tileSize * maxScreenRow;//

    private boolean showCommandCenter = false;
    private boolean commandCenterBuilt = false;

    Sound soundEffect = new Sound();

    Sound music = new Sound();
    public final int maxWorldCol = 32;
    public final int maxWorldRow = 23;
    public int FPS = 60;
    int x = 0;
    int y = 0;
    int speedX = 1;
    int speedY = 1;
    MarsRover MR;
    Thread simulationThread;//keeps program runing until you stop
    TileMenager tileM = new TileMenager(this);
    boolean soundPlayed = false;
    CommandCenter cc;
    Rectangle rectangle = new Rectangle(6, 6, tileSize, tileSize);


    public VisualPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void setMarsRover(MarsRover marsRover) {
        this.MR = marsRover;
    }

    public void StartSimulationThread() {
        simulationThread = new Thread(this);
        simulationThread.start();
    }

    public void update() {
        x = MR.getCurrentPosition().X() * tileSize;
        y = MR.getCurrentPosition().Y() * tileSize;

        // Sprawdzenie granic ekranu
        if (rectangle.x + rectangle.width >= screenWidth || rectangle.x <= 0) {
            speedX *= -1; // Odwrócenie kierunku w osi X, jeśli prostokąt dotyka lewej lub prawej krawędzi ekranu
        }
        if (rectangle.y + rectangle.height >= screenHeight || rectangle.y <= 0) {
            speedY *= -1; // Odwrócenie kierunku w osi Y, jeśli prostokąt dotyka górnej lub dolnej krawędzi ekranu
        }
        if (MR.getCurrentPosition().X() == 10 && MR.getCurrentPosition().Y() == 10) {
            showCommandCenter = true;
            buildCommandCenter();
            if (!soundPlayed) {
                playSE(1);
                soundPlayed = true; // Ustaw flagę, że dźwięk został odtworzony
            }
        }

        rectangle.setLocation(x, y);
    }

    public void buildCommandCenter() {
        commandCenterBuilt = true;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        g2.setColor(Color.white);
        Image scaledImage = tileM.tile[5].image.getScaledInstance(rectangle.width, rectangle.height, Image.SCALE_SMOOTH);
        g2.drawImage(scaledImage, rectangle.x, rectangle.y, null);
        if (showCommandCenter) {
            Rectangle commandCenter = new Rectangle(10 * tileSize, 10 * tileSize, tileSize, tileSize);
            Image scaled = tileM.tile[6].image.getScaledInstance(commandCenter.width, commandCenter.height, Image.SCALE_SMOOTH);
            g2.drawImage(scaled, commandCenter.x, commandCenter.y, null);
        }

    }


    public void playMusic(int i) {
        music.setFile(i);
        music.setVolume(0.1f);
        music.play();
        music.loop();

    }


    public void playSE(int i) {
        soundEffect.setFile(i);
        music.setVolume(0.1f);
        soundEffect.play();
    }

    @Override
    public void run() {
        playMusic(0);
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (simulationThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
