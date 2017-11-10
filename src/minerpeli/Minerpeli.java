/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minerpeli;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author Taavi
 */
public class Minerpeli {

    MyMap map;
    Ikkuna ikkuna;
    Ukkeli pekka;
    static int kartanNumero = 0;
    private static File MAP_FILE = new File("kartat/kartta" + String.format("%03d", kartanNumero) + ".dat");

    private void init() {

        ikkuna.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                switch (ke.getKeyCode()) {
                    case KeyEvent.VK_DOWN:
                        movePlayer(0, 1);
                        break;
                    case KeyEvent.VK_UP:
                        movePlayer(0, -1);
                        break;
                    case KeyEvent.VK_RIGHT:
                        movePlayer(1, 0);
                        break;
                    case KeyEvent.VK_LEFT:
                        movePlayer(-1, 0);
                        break;

                }
                tick();

            }

        });
        System.out.println("moi");

    }

    private void tick() {
        ikkuna.repaint();
        update();
    }

    private void update() {
        System.out.println(pekka.getInventory());
        System.out.println(pekka.getRaha());
        if (pekka.getY() < 3) {
            pekka.sell();

        }
        if (map.getNode(pekka.getX(), pekka.getY()) == MyMap.NodeType.KAUPPA1) {
            pekka.buy(MyMap.NodeType.LADDER);
        }
        if (map.getNode(pekka.getX(), pekka.getY()) == MyMap.NodeType.KAUPPA2) {
            if (pekka.getRaha() >= 50) {
                pekka.addToRaha(-50);
                ikkuna.addToNäkymä(1);
            }
        }
         if (map.getNode(pekka.getX(), pekka.getY()) == MyMap.NodeType.KAUPPA3) {
            if (pekka.getRaha() >= 20) {
                pekka.addToRaha(-20);
                pekka.addToTila(1);
            }
        }

    }

    private void movePlayer(int x, int y) {
        if (map.isWithinMap(this.pekka.getX() + x, this.pekka.getY() + y)) {

            MyMap.NodeType type = map.getNode(this.pekka.getX() + x, this.pekka.getY() + y);
            int px = this.pekka.getX();
            int py = this.pekka.getY();

            if (!map.isWithinMap(px, py + 1) //Ladderin pistäminen
                    || !map.isWithinMap(px, py + 2)
                    || (y == -1
                    && map.getNode(px, py + 1) == MyMap.NodeType.AIR
                    && map.getNode(px, py + 2) != MyMap.NodeType.AIR)) {

                if (pekka.getInventory().get(MyMap.NodeType.LADDER) > 0 && py > 1) {
                    map.setNode(px, py + 1, MyMap.NodeType.LADDER);
                    pekka.setInInventory(MyMap.NodeType.LADDER, -1);
                } else {
                    this.pekka.move(0, 1);

                }
            } else if (map.isWithinMap(px, py + 1) && map.getNode(px, py + 1) == MyMap.NodeType.AIR) {//tippuminen
                this.pekka.move(0, 1);
                if (map.isWithinMap(px, py + 2) && map.getNode(px, py + 2) == MyMap.NodeType.AIR) {

                    this.movePlayer(0, 1);

                }
            } else if (type == MyMap.NodeType.AIR || type == MyMap.NodeType.LADDER || type.getCategory() == MyMap.NodeCategory.KAUPPA) {
                this.pekka.move(x, y);

            } else if (type == MyMap.NodeType.HARDSTONE || type == MyMap.NodeType.LADDER) {

            } else {
                map.setNode(px + x, py + y, MyMap.NodeType.AIR);
                if (type.getCategory() == MyMap.NodeCategory.ORE){
                    
                    pekka.setInInventory(type, 1);}
            }
        }

    }

    public static void main(String[] args) throws IOException {
        Minerpeli game = new Minerpeli();
        MapCreator creator = new MapCreator();
        creator.teekartta();
        try (FileInputStream i = new FileInputStream(MAP_FILE)) {
            game.map = MyMap.readFromStream(i);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        game.pekka = new Ukkeli(game.map.getWidth()/2, 2);
        game.ikkuna = new Ikkuna(game.map, game.pekka, 1);

        game.init();

    }

}
