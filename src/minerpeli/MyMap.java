/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minerpeli;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author taavi
 */
public class MyMap {

    private final int width;

    private final int height;
    private int voittoPisteet;
    private int PlayerX;
    private int PlayerY;        
    private  NodeType[][] map;

    public MyMap(int x, int y,int pX,int pY) {
        width = x;
        height = y;
        PlayerX = pX;
        PlayerY = pY;
        
        map = new NodeType[x][y];
        
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                map[i][j] = NodeType.AIR;
            }
        }
    }

    public void setNode(int x, int y, NodeType type) {
        map[x][y] = type;
    }

    public NodeType getNode(int x, int y) {
        return map[x][y];
    }

    public void setPlayerX(int PlayerX) {
        this.PlayerX = PlayerX;
    }

    public void setPlayerY(int PlayerY) {
        this.PlayerY = PlayerY;
    }
    
    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

 
    
    public int getPlayerX() {
        return PlayerX;
    }

    public int getPlayerY() {
        return PlayerY;
    }
    
    public boolean isWithinMap(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public void writeToStream(OutputStream s) throws IOException {
        s.write(width);
        s.write(height);
        s.write(PlayerX);
        s.write(PlayerY);
        
        byte[] bytes = new byte[width * height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                bytes[i * height + j] = map[i][j].getCode();
            }
        }

        s.write(bytes);
    }

    public static MyMap readFromStream(InputStream s) throws IOException {
        int width = s.read();
        int height = s.read();
        int PlayerX = s.read();
        int PlayerY = s.read();
        

        byte[] bytes = new byte[width * height];
        s.read(bytes);

        MyMap map = new MyMap(width, height,PlayerX,PlayerY);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                NodeType type = NodeType.nodeTypeFromByte(bytes[i * height + j]);
                if (type == null) {
                    throw new RuntimeException("illegal map data, node type not found: " + bytes[j * height + i]);
                }
                map.map[i][j] = type;
            }
        }

        return map;
    }
    
    public enum NodeCategory {
        AIR,
        EARTH,
        ORE,
        ITEM,
        KAUPPA,
        ;
        
        
    }

    public enum NodeType {
        AIR(0, NodeCategory.AIR),
        STONE(1, NodeCategory.EARTH),
        GRASS(2,NodeCategory.EARTH),
        HARDSTONE(3,NodeCategory.EARTH),
        COAL(4,NodeCategory.ORE),
        IRON(5,NodeCategory.ORE),
        GOLD(6,NodeCategory.ORE),
        DIAMOND(7,NodeCategory.ORE),
        RUBIN(8,NodeCategory.ORE),
        LADDER(9,NodeCategory.ITEM),
        PILLAR(10,NodeCategory.ITEM),
        KAUPPA1(11,NodeCategory.KAUPPA),
        KAUPPA2(12,NodeCategory.KAUPPA)
        ;

        public static NodeType nodeTypeFromByte(byte i) {
            for (NodeType t : values()) {
                if (t.getCode() == i) {
                    return t;
                }
            }
            return null;
        }

        private byte code;
        private NodeCategory category;

        private NodeType(int code, NodeCategory cat) {
            this.code = (byte) code;
            this.category = cat;
        }

        public byte getCode() {
            return code;
        }

        public NodeCategory getCategory() {
            return category;
        }
        
        

    }

}
