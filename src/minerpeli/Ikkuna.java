/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minerpeli;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

/**
 *
 * @author Taavi
 */
public class Ikkuna extends Canvas {

    int kerroin = 32;
    int näkymä = 1;
    int näkymäulkon = 10;
    MyMap map;
    Ukkeli pekka;
    int mode;
    Map<MyMap.NodeType, Color> värit;
    Color pekkaVäri;

    public Ikkuna(MyMap map, Ukkeli pekka, int mode) {
        this.map = map;
        this.pekka = pekka;
        this.mode = mode;
        Color perus = new Color(214, 194, 045);
        värit = new HashMap<MyMap.NodeType, Color>();
        for (MyMap.NodeType type : MyMap.NodeType.values()) {
            this.värit.put(type, perus);
        }
        
        värit.put(MyMap.NodeType.AIR, Color.white);
        värit.put(MyMap.NodeType.STONE, Color.lightGray);
        värit.put(MyMap.NodeType.GRASS, Color.lightGray);
        värit.put(MyMap.NodeType.COAL, Color.darkGray);
        värit.put(MyMap.NodeType.IRON, new Color(100, 100, 100));
        värit.put(MyMap.NodeType.GOLD, Color.yellow);
        värit.put(MyMap.NodeType.LADDER, Color.orange);
        värit.put(MyMap.NodeType.RUBIN, Color.red);
        värit.put(MyMap.NodeType.HARDSTONE, Color.black);
        värit.put(MyMap.NodeType.KAUPPA1, Color.pink);
        värit.put(MyMap.NodeType.KAUPPA2, Color.cyan);

        pekkaVäri = new Color(200, 0, 0);

        if (mode == 0) {
            JFrame frame = new JFrame("Miner peli");
            frame.setPreferredSize(new Dimension(map.getWidth() * (kerroin), (map.getHeight()) * (kerroin)));
            frame.setMaximumSize(new Dimension(map.getWidth() * (kerroin + 10), map.getHeight() * (kerroin + 10)));
            frame.setMinimumSize(new Dimension(map.getWidth() * (kerroin), map.getHeight() * (kerroin)));
            frame.add(this);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(true);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } else {
            JFrame frame = new JFrame("Miner peli");
            frame.setPreferredSize(new Dimension(21 * (kerroin), 21 * (kerroin)));
            frame.setMaximumSize(new Dimension(21 * (kerroin + 10), 21 * (kerroin + 10)));
            frame.setMinimumSize(new Dimension(21 * (kerroin), 21 * (kerroin)));
            frame.add(this);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(true);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }

    public void addToNäkymä(int x) {
        this.näkymä += x;
    }

    public void maalaa(Graphics g, int x, int y, int nx, int ny) { //ny ja nx on se mistä se blokki haetaan
        if (map.isWithinMap(nx, ny) && null != map.getNode(nx, ny)) {
            g.setColor(this.värit.get(map.getNode(nx, ny)));
            
            switch (map.getNode(nx, ny)) {
                case AIR:                   
                    g.fillRect(x * kerroin, y * kerroin, kerroin, kerroin);
                    break;
                case STONE:
                    g.fillRect(x * kerroin, y * kerroin, kerroin, kerroin);
                    break;
                case GRASS:
                    g.fillRect(x * kerroin, y * kerroin, kerroin, kerroin);
                    g.setColor(Color.green);
                    g.fillRect(x * kerroin, y * kerroin, kerroin, kerroin / 2);
                    break;
                case HARDSTONE:
                    g.fillRect(x * kerroin, y * kerroin, kerroin, kerroin);
                    break;
                case COAL:
                    g.fillRect(x * kerroin, y * kerroin, kerroin, kerroin);

                    break;
                case GOLD:
                    g.fillRect(x * kerroin, y * kerroin, kerroin, kerroin);
                    break;
                case IRON:
                    g.fillRect(x * kerroin, y * kerroin, kerroin, kerroin);
                    break;
                case RUBIN:
                    g.fillRect(x * kerroin, y * kerroin, kerroin, kerroin);
                    break;
                case DIAMOND:
                    g.fillRect(x * kerroin, y * kerroin, kerroin, kerroin);
                    break;
                case LADDER:
                    g.fillRect(x * kerroin, y * kerroin, kerroin / 3, kerroin);
                    g.fillRect(x * kerroin + kerroin / 3 * 2 + 2, y * kerroin, kerroin / 3, kerroin);
                    g.fillRect(x * kerroin, y * kerroin + kerroin / 4, kerroin, kerroin / 3);
                    break;
                case KAUPPA1:
                    g.fillRect(x * kerroin, y * kerroin, kerroin, kerroin);
                    break;
                case KAUPPA2:
                    g.fillRect(x * kerroin, y * kerroin, kerroin, kerroin);
                    break;
                default:
                    break;
            }
        }
    }
    
    @Override
    public void paint(Graphics g) {
        if (this.mode == 0) {
            paint1(g);
        } else {
            paint3(g);
        }
    }

    public void ylimääräset(Graphics g) {
        g.drawString("Rahat: " + Integer.toString(this.pekka.getRaha()), 10, 20);
        g.drawString("Tikkaat: " + Integer.toString(this.pekka.getInventory().get(MyMap.NodeType.LADDER)), 10, 30);
        g.drawString("Hiili: " + Integer.toString(this.pekka.getInventory().get(MyMap.NodeType.COAL)), 10, 40);
        g.drawString("Rauta: " + Integer.toString(this.pekka.getInventory().get(MyMap.NodeType.IRON)), 10, 50);
        g.drawString("Kulta: " + Integer.toString(this.pekka.getInventory().get(MyMap.NodeType.GOLD)), 10, 60);
        g.drawString("Timantti: " + Integer.toString(this.pekka.getInventory().get(MyMap.NodeType.DIAMOND)), 10, 70);
        g.drawString("Rubiini: " + Integer.toString(this.pekka.getInventory().get(MyMap.NodeType.RUBIN)), 10, 80);
        g.drawString("Y: " + Integer.toString(this.pekka.getY()), 100, 20);
        g.drawString("X: " + Integer.toString(this.pekka.getX()), 100, 30);
        g.drawString("Tilaa: " + Integer.toString(this.pekka.getTila()), 100, 40);
        if (pekka.getTila()<=0){
            g.drawString("EI TILAA", 100, 100);
        }
    }

    public void paint1(Graphics g) {
        g.setColor(Color.black);
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                maalaa(g, i, j, i, j);

            }
            g.setColor(Color.red);
            g.fillRect(this.pekka.getX() * kerroin, this.pekka.getY() * kerroin, kerroin, kerroin);
            ylimääräset(g);
        }

    } //koko map

    public void paint2(Graphics g) {
        g.setColor(Color.black);
        for (int i = -näkymä; i < näkymä + 1; i++) {
            for (int j = -näkymä; j < näkymä + 1; j++) {

                int in = i + this.pekka.getX();
                int jn = j + this.pekka.getY();
                maalaa(g, in, jn, in, jn);
            }

        }
        g.setColor(Color.red);
        g.fillRect(this.pekka.getX() * kerroin, this.pekka.getY() * kerroin, kerroin, kerroin);
        ylimääräset(g);

    } //vain pelaajan ympäriltä

    public void paint3(Graphics g) {
        if (this.pekka.getY() >= 3) {
            for (int i = -näkymä; i < näkymä + 1; i++) {
                for (int j = -näkymä; j < näkymä + 1; j++) {
                    if(Math.hypot(i, j)<= näkymä || (näkymä == 1)){

                    int in = i + this.pekka.getX();
                    int jn = j + this.pekka.getY();

                    maalaa(g, i + 10, j + 10, in, jn);}

                }
            }

        } else {
            for (int i = -näkymäulkon; i < näkymäulkon + 1; i++) {
                for (int j = -näkymäulkon; j < näkymä + 2; j++) {

                    int in = i + this.pekka.getX();
                    int jn = j + this.pekka.getY();

                    maalaa(g, i + 10, j + 10, in, jn);

                }
            }
        }

        g.setColor(pekkaVäri);
        g.fillRect(10 * kerroin, 10 * kerroin, kerroin, kerroin);

        ylimääräset(g);

    } //vain pelaajan ympäriltä

}
