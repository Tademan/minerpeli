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

        for (int i = 0; i < 3; i++) {
            File file = new File("kartat/kartta" + String.format("%03d", i) + ".dat");
            MyMap map = new MyMap(40, 60, 5, 5);
            
            for (int x = 0; x < map.getWidth(); x++) {
                for (int y = 0; y < map.getHeight(); y++) {
                    
                    if (y < 3){
                        map.setNode(x, y, NodeType.AIR);
                    }else {
                        map.setNode(x, y, NodeType.STONE);
                    }
                    if (y == 3) {
                        map.setNode(x, y, NodeType.STONE);
                    }
                    if (r.nextFloat() < 1/y) {
                        map.setNode(x, y, NodeType.HARDSTONE);
                    }
                    if (r.nextFloat() < 0.1) {
                        map.setNode(x, y, NodeType.COAL);
                    }
                    if (r.nextFloat() < 0.1) {
                        map.setNode(x, y, NodeType.IRON);
                    } 
                    if (r.nextFloat() < 0.1) {
                        map.setNode(x, y, NodeType.GOLD);
                    }
                    if (r.nextFloat() < 0.1) {
                        map.setNode(x, y, NodeType.AIR);
                    } 
                }
            }
            
            map.setNode(r.nextInt(map.getWidth()-1), map.getHeight()-3-r.nextInt(map.getHeight()/10), NodeType.DIAMOND);
            
            for (int k = 0; k < map.getWidth(); k++){
                map.setNode(k, map.getHeight()-1, NodeType.HARDSTONE);
                map.setNode(k, map.getHeight()-2, NodeType.HARDSTONE);
            }
            
            try (FileOutputStream o = new FileOutputStream(file)) {
                map.writeToStream(o);

            }
        }

    }

}
