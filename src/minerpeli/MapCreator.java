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
    public static void main(String[] args) throws IOException {
        Random r = new Random();

        for (int i = 0; i < 1; i++) {
            File file = new File("kartat/kartta" + String.format("%03d", i) + ".dat");
            MyMap map = new MyMap(30, 30, 5, 5);
            for (int j = 0; j < map.getHeight(); j++) {
                for (int k = 0; k < map.getWidth(); k++) {
                    if (k < 3){
                        map.setNode(j, k, NodeType.AIR);
                    }else if (k == 3) {
                        map.setNode(j, k, NodeType.STONE);
                    } else if (r.nextFloat() < 0.1) {
                        map.setNode(j, k, NodeType.HARDSTONE);
                    } else if (r.nextFloat() < 0.1) {
                        map.setNode(j, k, NodeType.COAL);
                    } else if (r.nextFloat() < 0.1) {
                        map.setNode(j, k, NodeType.IRON);
                    } else if (r.nextFloat() < 0.1) {
                        map.setNode(j, k, NodeType.GOLD);
                    } else if (r.nextFloat() < 0.1) {
                        map.setNode(j, k, NodeType.AIR);
                    } else {
                        map.setNode(j, k, NodeType.STONE);
                    }
                }
            }
            map.setNode(r.nextInt(map.getWidth()-1), map.getWidth()-1-r.nextInt(5), NodeType.DIAMOND);

            try (FileOutputStream o = new FileOutputStream(file)) {
                map.writeToStream(o);

            }
        }

    }

}
