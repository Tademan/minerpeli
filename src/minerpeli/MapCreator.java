/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minerpeli;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import minerpeli.MyMap.NodeType;

/**
 *
 * @author taavi
 */
public class MapCreator {

    /**
     * @param args the command line arguments
     */
    private static double tod(int y, int start, int whenhalf, double endtod) {

        return (Math.atan((y - start) / (whenhalf - start) / (Math.PI / 2)) * endtod);
    }

    public void teekartta() throws IOException {
        Random r = new Random();
        float pi = (float) Math.PI;

        for (int i = 0; i < 3; i++) {
            File file = new File("kartat/kartta" + String.format("%03d", i) + ".dat");
            MyMap map = new MyMap(1000, 1000, 5, 5);

            for (int x = 0; x < map.getWidth(); x++) {
                for (int y = 0; y < map.getHeight(); y++) {

                    map.setNode(x, y, NodeType.STONE);

                    if (r.nextFloat() < tod(y, 5, 30, 0.1)) {
                        map.setNode(x, y, NodeType.HARDSTONE);
                    }
                    if (r.nextFloat() < tod(y, 0, 5, 0.2)) {
                        map.setNode(x, y, NodeType.COAL);
                    }
                    if (r.nextFloat() < tod(y, 0, 10, 0.1)) {
                        map.setNode(x, y, NodeType.IRON);
                    }
                    if (r.nextFloat() < tod(y, 10, 20, 0.025)) {
                        map.setNode(x, y, NodeType.GOLD);
                    }
                    if (r.nextFloat() < 0.1) {
                        int g = 10;
                        for (int j = 0; j < g; j++) {
                            map.setNode(x, (y + j), NodeType.AIR);
                        }

                    }
                    if (r.nextFloat() < tod(y, 20, 60, 0.01)) {
                        map.setNode(x, y, NodeType.RUBIN);
                    }

                    if (y == 3) {
                        map.setNode(x, y, NodeType.GRASS);
                    }
                    if (y < 3) {
                        map.setNode(x, y, NodeType.AIR);

                    }
                }
            }

            map.setNode(r.nextInt(map.getWidth() - 1), map.getHeight() - 3 - r.nextInt(map.getHeight() / 10), NodeType.DIAMOND);
            
            map.setNode(map.getWidth()/2, 1, NodeType.KAUPPA1);
            map.setNode(map.getWidth()/2+1, 1, NodeType.KAUPPA2);
            map.setNode(map.getWidth()/2+2, 1, NodeType.KAUPPA3);

            for (int k = 0; k < map.getWidth(); k++) {
                map.setNode(k, map.getHeight() - 1, NodeType.HARDSTONE);
                map.setNode(k, map.getHeight() - 2, NodeType.HARDSTONE);
            }

            try (FileOutputStream o = new FileOutputStream(file)) {
                map.writeToStream(o);

            }

        }

    }
}
