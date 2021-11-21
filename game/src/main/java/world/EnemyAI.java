package world;

import java.util.List;
import java.util.Random;

public class EnemyAI extends CreatureAI{
    private CreatureFactory factory;
    private List<String> messages;
    public EnemyAI(Thing creature,List<String> messages) {
        super(creature);
        this.messages = messages;
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
    @Override
    public void onNotify(String message) {
        // TODO Auto-generated method stub
        messages.add(message);
    }
}