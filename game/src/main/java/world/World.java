package world;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * Copyright (C) 2015 Aeranythe Echosong
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
/**
 *
 * @author Aeranythe Echosong
 */
public class World {
    private int state = 0;
    private int mx;
    private int my;
    private Tile[][] tiles;
    private int width;
    private int height;
    private List<Thing> things;
    private List<Thing> bullets = new CopyOnWriteArrayList();
    private List<String> messages;
    public static final int TILE_TYPES = 2;
    public World(Tile[][] tiles,int mx,int my,List<String> messages) {
        this.tiles = tiles;
        this.width = tiles.length;
        this.height = tiles[0].length;
        //this.things = new ArrayList<>();
        this.things = new CopyOnWriteArrayList();
        this.mx = mx;
        this.my = my;
        this.messages = messages;
    }
    public void acceptLose(){
        state = -1;
        things.clear();
        bullets.clear();
        nodifyMessage("lose...");
    }
    public void acceptWin(){
        state = 1;
        things.clear();
        bullets.clear();
        nodifyMessage("win!");
    }
    public boolean isLose(){
        return state == -1;
    }
    public boolean isWin(){
        return state == 1;
    }
    public int getMx() {
        return mx;
    }
    public int getMy() {
        return my;
    }
    public List<Thing>bullets(){
        return bullets;
    }
    public Tile tile(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return Tile.BOUNDS;
        } else {
            return tiles[x][y];
        }
    }

    public char glyph(int x, int y) {
        return tiles[x][y].glyph();
    }

    public Color color(int x, int y) {
        return tiles[x][y].color();
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public void dig(int x, int y) {
        //if (tile(x, y).isDiggable()) {
            tiles[x][y] = Tile.FLOOR;
        //}
    }
    public void add(Thing thing){
        this.things.add(thing);
        if(thing instanceof Bullet){
            bullets.add(thing);
        }
    }
    public void addAtEmptyLocation(Thing creature) {
        int x;
        int y;

        do {
            x = (int) (Math.random() * this.width);
            y = (int) (Math.random() * this.height);
        } while (!tile(x, y).isGround() || this.creature(x, y) != null);

        creature.setX(x);
        creature.setY(y);
        this.things.add(creature);
    }
    
    public synchronized Thing thing(int x,int y){
        
        Thing result = null;
        for(Thing th:this.things){
            if (th.x() == x && th.y() == y) {
                result = th;
                break;
            }
        }
        
        return result;
    }
    public Creature creature(int x, int y) {
        Thing thing = thing(x, y);
        if(thing instanceof Creature){
            return (Creature)thing;
        }
        return null;
    }

    public List<Thing> getThings() {
        return this.things;
    }

    public void remove(Thing target) {
        this.things.remove(target);
        if(target instanceof Bullet){
            bullets.remove(target);
        }
    }

    public void update() {
        ArrayList<Thing> toUpdate = new ArrayList<>(this.things);
        for (Thing creature : toUpdate) {
            creature.update();
        }
    }
    public void setFloor(int x,int y){
        this.tiles[x][y] = Tile.FLOOR;
    }
    boolean outRange(int x,int y){
        return x <= 0 || x >= width || y<= 0|| y >= height;
    }
    public void nodifyMessage(String message){
        messages.add(message);
    }
}
