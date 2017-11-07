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
        if (pekka.getX() == 10 && pekka.getY() == 1){
            pekka.buy(MyMap.NodeType.LADDER);
        }
    }

    private void movePlayer(int x, int y) {
        if (map.isWithinMap(this.pekka.getX() + x, this.pekka.getY() + y)) {
            
            
            MyMap.NodeType type = map.getNode(this.pekka.getX() + x, this.pekka.getY() + y);
            if ((
                    !map.isWithinMap(this.pekka.getX(), this.pekka.getY() + 1)
                    || !map.isWithinMap(this.pekka.getX(), this.pekka.getY() + 2))
                    || (y == -1
                    && map.getNode(this.pekka.getX(), this.pekka.getY() + 1) == MyMap.NodeType.AIR
                    && !(map.getNode(this.pekka.getX(), this.pekka.getY() + 2) == MyMap.NodeType.AIR))) {
                
                if ((pekka.getInventory().get(MyMap.NodeType.LADDER) > 0)&&(this.pekka.getY() > 1)) {
                    map.setNode(this.pekka.getX(), this.pekka.getY() + 1, MyMap.NodeType.LADDER);
                    pekka.setInInventory(MyMap.NodeType.LADDER, -1);
                }else{    
                    this.pekka.move(0, 1);
                }
            } else if (map.isWithinMap(this.pekka.getX(), this.pekka.getY() + 1) && map.getNode(this.pekka.getX(), this.pekka.getY() + 1) == MyMap.NodeType.AIR) {
                this.pekka.move(0, 1);
            } else if (type == MyMap.NodeType.AIR || type == MyMap.NodeType.LADDER) {
                this.pekka.move(x, y);

            } else if (type == MyMap.NodeType.HARDSTONE || type == MyMap.NodeType.LADDER) {

            } else {
                map.setNode(pekka.getX() + x, pekka.getY() + y, MyMap.NodeType.AIR);
                pekka.setInInventory(type,1);
            }
        }

    }

    public static void main(String[] args) {
        Minerpeli game = new Minerpeli();
        try (FileInputStream i = new FileInputStream(MAP_FILE)) {
            game.map = MyMap.readFromStream(i);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        game.pekka = new Ukkeli(2, 2);
        game.ikkuna = new Ikkuna(game.map, game.pekka);

        game.init();

    }

}
