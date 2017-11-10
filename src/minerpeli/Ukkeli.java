/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minerpeli;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author taavi
 */
public class Ukkeli {

    private int x, y;
    private Map<MyMap.NodeType, Integer> inventory;
    private int raha;
    private int inventorySize;
    private int inInventory;

    public Ukkeli(int x, int y) {
        this.x = x;
        this.y = y;
        inventorySize = 10;

        this.inventory = new HashMap<MyMap.NodeType, Integer>();
        int a = 0;
        for (MyMap.NodeType type : MyMap.NodeType.values()) {
            this.inventory.put(type, a);

        }
        this.inventory.put(MyMap.NodeType.LADDER, 10);

    }

    public void buy(MyMap.NodeType type) {
        if (type == MyMap.NodeType.LADDER && this.raha >= 5) {
            this.raha = this.raha - 5;
            this.setInInventory(MyMap.NodeType.LADDER, 5);
        } else if ((type == MyMap.NodeType.PILLAR && this.raha >= 2)) {
            this.raha -= 2;
            this.inventory.put(MyMap.NodeType.PILLAR, 1);
        }

    }

    public void addToRaha(int x) {
        this.raha += x;
    }

    public void sell() {
        this.raha = this.raha + inventory.get(MyMap.NodeType.COAL) * 1;
        this.raha = this.raha + inventory.get(MyMap.NodeType.GOLD) * 5;
        this.raha = this.raha + inventory.get(MyMap.NodeType.IRON) * 2;
        this.raha = this.raha + inventory.get(MyMap.NodeType.RUBIN) * 10;
        this.raha = this.raha + inventory.get(MyMap.NodeType.DIAMOND) * 100;
        this.inInventory = 0;

        for (MyMap.NodeType type : MyMap.NodeType.values()) {
            if ((type != MyMap.NodeType.LADDER)) {

                this.inventory.put(type, 0);
            }
        }

    }

    public void setInInventory(MyMap.NodeType type, int x) {
        if (this.inInventory < this.inventorySize) {
            this.inInventory++;
            this.inventory.put(type, this.inventory.get(type) + x);
        }

    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }
    public int getTila(){
        return (this.inventorySize-this.inInventory);
    }
    public void addToTila(int x){
        this.inventorySize += x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Map<MyMap.NodeType, Integer> getInventory() {
        return inventory;
    }

    public int getRaha() {
        return raha;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.x;
        hash = 59 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ukkeli other = (Ukkeli) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

}
