package world;
import java.awt.Color;
public class Thing {
    protected World world;

    private int x;

    public void setX(int x) {
        this.x = x;
    }

    public int x() {
        return x;
    }

    private int y;

    public void setY(int y) {
        this.y = y;
    }

    public int y() {
        return y;
    }

    protected char glyph;

    public char glyph() {
        return this.glyph;
    }

    private Color color;

    public Color color() {
        return this.color;
    }

    private CreatureAI ai;

    public void setAI(CreatureAI ai) {
        this.ai = ai;
    }

    private int maxHP;

    public int maxHP() {
        return this.maxHP;
    }

    private int hp;

    public int hp() {
        return this.hp;
    }

    public void modifyHP(int amount) {
        this.hp += amount;

        if (this.hp < 1) {
            world.remove(this);
        }
    }

    private int attackValue;

    public int attackValue() {
        return this.attackValue;
    }

    private int defenseValue;

    public int defenseValue() {
        return this.defenseValue;
    }

    private int visionRadius;

    public int visionRadius() {
        return this.visionRadius;
    }

    private int socre = 100;

    private int score() {
        return this.socre;
    }

    public boolean canSee(int wx, int wy) {
        // return ai.canSee(wx, wy);
        return true;
    }

    public Tile tile(int wx, int wy) {
        return world.tile(wx, wy);
    }

    public void dig(int wx, int wy) {
       world.dig(wx, wy);
    }

    public void moveBy(int mx, int my) {
        Thing other = world.thing(x + mx, y + my);

        if (other == null) {
            ai.onEnter(x + mx, y + my, world.tile(x + mx, y + my));
        } else {
            attack(other);
        }
        //notifyAll();
    }

    public void attack(Thing other) {
        int damage = Math.max(0, this.attackValue() - other.defenseValue());
        damage = (int) (Math.random() * damage) + 1;

        other.modifyHP(-damage);

        this.notify("You attack the '%s' for %d damage.", other.glyph, damage);
        other.notify("The '%s' attacks you for %d damage.", glyph, damage);
    }

    public void update() {
        //this.ai.onUpdate();
    }

    public boolean canEnter(int x, int y) {
        return world.tile(x, y).isReachable();
    }

    public void notify(String message, Object... params) {
        ai.onNotify(String.format(message, params));
    }

    public Thing(World world, char glyph, Color color, int maxHP, int attack, int defense, int visionRadius) {
        this.world = world;
        this.glyph = glyph;
        this.color = color;
        this.maxHP = maxHP;
        this.hp = maxHP;
        this.attackValue = attack;
        this.defenseValue = defense;
        this.visionRadius = visionRadius;
    }
    private Direction direction = Direction.UP;
    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public void turn(Direction direction){
        this.direction = direction;
    }
    public void step(){
        switch (direction){
            case LEFT:
                moveBy(-1, 0);
                break;
            case RIGHT:
                moveBy(1, 0);
                break;
            case UP:
                moveBy(0, -1);
                break;
            case DOWN:
                moveBy(0, 1);
                break;
        }
    }
    public void die(){
        this.modifyHP(-this.hp);
    }
}