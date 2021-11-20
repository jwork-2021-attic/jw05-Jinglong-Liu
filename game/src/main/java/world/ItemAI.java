package world;

import org.sonatype.aether.impl.UpdateCheck;

public class ItemAI extends CreatureAI {
    
    public ItemAI(Thing item) {
        super(item);
    }

    public void onEnter(int x, int y, Tile tile) {
        if(thing.world.outRange(x,y)){
            thing.world.remove(thing);
        }
        else if(tile.isDiggable()){
            thing.dig(x, y);
        }
        else if (tile.isGround()) {
            thing.setX(x);
            thing.setY(y);
        }
        thing.update();
    }
}