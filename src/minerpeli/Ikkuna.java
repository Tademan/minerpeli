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
import javax.swing.JFrame;

/**
 *
 * @author Taavi
 */
public class Ikkuna extends Canvas {

    int kerroin = 32;
    int näkymä = 2;
    MyMap map;
    Ukkeli pekka;

    public Ikkuna(MyMap map, Ukkeli pekka) {
        this.map = map;
        this.pekka = pekka;

        JFrame frame = new JFrame("Miner peli");
        frame.setPreferredSize(new Dimension(map.getWidth() * kerroin, map.getHeight() * kerroin + kerroin));
        frame.setMaximumSize(new Dimension(map.getWidth() * kerroin, map.getHeight() * kerroin + kerroin));
        frame.setMinimumSize(new Dimension(map.getWidth() * kerroin, map.getHeight() * kerroin + kerroin));
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void maalaa(Graphics g, int x, int y) {
        if (map.isWithinMap(x, y) && null != map.getNode(x, y)) {
            switch (map.getNode(x, y)) {
                case STONE:
                    g.setColor(Color.gray);
                    g.fillRect(x * kerroin, y * kerroin, kerroin, kerroin);
                    break;
                case HARDSTONE:
                    g.setColor(Color.black);
                    g.fillRect(x * kerroin, y * kerroin, kerroin, kerroin);
                    break;
                case COAL:
                    g.setColor(Color.darkGray);
                    g.fillRect(x * kerroin, y * kerroin, kerroin, kerroin);
                    break;
                case GOLD:
                    g.setColor(Color.yellow);
                    g.fillRect(x * kerroin, y * kerroin, kerroin, kerroin);
                    break;
                case IRON:
                    g.setColor(Color.lightGray);
                    g.fillRect(x * kerroin, y * kerroin, kerroin, kerroin);
                    break;
                case DIAMOND:
                    g.setColor(Color.blue);
                    g.fillRect(x * kerroin, y * kerroin, kerroin, kerroin);
                    break;
                case LADDER:
                    g.setColor(Color.orange);
                    g.fillRect(x * kerroin, y * kerroin, kerroin, kerroin);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        paint1(g);
    }

    public void paint1(Graphics g) {
        g.setColor(Color.black);
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                maalaa(g, i, j);

            }

        }
        g.setColor(Color.pink);
        g.fillRect(10 * kerroin, 1 * kerroin, kerroin, kerroin);
        g.setColor(Color.red);
        g.fillRect(this.pekka.getX() * kerroin, this.pekka.getY() * kerroin, kerroin, kerroin);
        g.drawString("Rahat: " + Integer.toString(this.pekka.getRaha()), 10, 20);
        g.drawString("Tikkaat: " + Integer.toString(this.pekka.getInventory().get(MyMap.NodeType.LADDER)), 10, 30);

    } //koko map

    public void paint2(Graphics g) {
        g.setColor(Color.black);
        for (int i = -näkymä; i < näkymä + 1; i++) {
            for (int j = -näkymä; j < näkymä + 1; j++) {

                int in = i + this.pekka.getX();
                int jn = j + this.pekka.getY();
                maalaa(g, in, jn);
            }

        }
        g.setColor(Color.pink);
        g.fillRect(10 * kerroin, 1 * kerroin, kerroin, kerroin);
        g.setColor(Color.red);
        g.fillRect(this.pekka.getX() * kerroin, this.pekka.getY() * kerroin, kerroin, kerroin);
        g.drawString("Rahat: " + Integer.toString(this.pekka.getRaha()), 10, 20);
        g.drawString("Tikkaat: " + Integer.toString(this.pekka.getInventory().get(MyMap.NodeType.LADDER)), 10, 30);

    } //vain pelaajan ympäriltä

    public void paint3(Graphics g) {
        for (int i = -näkymä; i < näkymä + 1; i++) {
            for (int j = -näkymä; j < näkymä + 1; j++) {

                int in = i + this.pekka.getX();
                int jn = j + this.pekka.getY();
                    
                    maalaa(g, in, jn);
                
            }

        }
        g.setColor(Color.pink);
        g.fillRect(10 * kerroin, 1 * kerroin, kerroin, kerroin);
        g.setColor(Color.red);
        g.fillRect(this.pekka.getX() * kerroin, this.pekka.getY() * kerroin, kerroin, kerroin);
        g.drawString("Rahat: " + Integer.toString(this.pekka.getRaha()), 10, 20);
        g.drawString("Tikkaat: " + Integer.toString(this.pekka.getInventory().get(MyMap.NodeType.LADDER)), 10, 30);

    } //vain pelaajan ympäriltä

}
