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
package world;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import asciiPanel.AsciiPanel;
import screen.PlayScreen;
import screen.Screen;

/**
 *
 * @author Aeranythe Echosong
 */
public class CreatureFactory {

    private World world;

    public CreatureFactory(World world) {
        this.world = world;
    }

    public Creature newPlayer(List<String> messages){
        Creature player = new Creature(this.world, (char)2, AsciiPanel.brightWhite, 1, 20, 5, 9);
        world.addAtEmptyLocation(player);
        new PlayerAI(player, messages);
        return player;
    }

    public Creature newFungus() {
        Creature fungus = new Creature(this.world, (char)3, AsciiPanel.green, 10, 0, 0, 0);
        world.addAtEmptyLocation(fungus);
        new FungusAI(fungus, this);
        return fungus;
    }
    public Bullet newBullet(Creature order){
        Bullet bullet = new Bullet(world, order);
        new ItemAI(bullet);
        bullet.step();
        if(bullet.hp() > 0){
            world.add(bullet);
        }
        return bullet;
    }

    public Wall newWall(int x,int y){
        return new Wall(this.world,x,y);
    }
    public Enemy newEnemy(){
        //Enemy(World world, char glyph, Color color, int maxHP, int attack, int defense)
        Enemy enemy = new Enemy(world,(char)2,AsciiPanel.green,2,1,0,this);
        world.addAtEmptyLocation(enemy);
        new EnemyAI(enemy);
        return enemy;
    }
}
