package VisualPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileMenager {
    VisualPanel vp;
    Tile[] tile;
    int mapTileNum[][];

    public TileMenager(VisualPanel vp) {
        this.vp = vp;
        tile = new Tile[10];
        mapTileNum = new int[vp.maxWorldCol][vp.maxWorldRow];
        getTileImage();
        loadMap();


    }




    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/TILES/mountain.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/TILES/minerals.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/TILES/pits.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/TILES/space.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/TILES/water.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/TILES/drone.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/TILES/base.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/TILES/exploration-0.1.map");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < vp.maxWorldRow) {
                String numbers[] = line.split(" ");

                for (int col = 0; col < vp.maxWorldCol; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                }

                row++;
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while (col < vp.maxScreenCol && row < vp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, vp.tileSize, vp.tileSize, null);
            col++;
            x += vp.tileSize;
            if (col == vp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += vp.tileSize;
            }
        }
    }

}
