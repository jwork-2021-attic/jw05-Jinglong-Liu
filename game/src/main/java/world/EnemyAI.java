package world;

import java.util.Random;

public class EnemyAI extends CreatureAI{
    private CreatureFactory factory;
    public EnemyAI(Thing creature) {
        super(creature);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onEnter(int x, int y, Tile tile) {
        // TODO Auto-generated method stub
        if (tile.isGround()) {
            thing.setX(x);
            thing.setY(y);
        }
        else if (tile.isDiggable()) {
            thing.dig(x, y);
        }
    }
}