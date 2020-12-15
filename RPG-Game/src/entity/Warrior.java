
package entity;

import graphics.Sprite;
import util.Vector2f;


public class Warrior extends Player{
    
    /* Atributos - Entity
    protected Animation ani;
    protected HashMap<String, Sprite> hpAnim;
    protected Sprite sprite;
    protected Vector2f pos;
    protected float initPosX = pos.x;
    
    protected boolean attack;
    protected boolean run;
    
    protected int size;
    protected float speed;
    
    protected int currentAnimation;
    */
    
    
    public Warrior(Sprite sprite, Vector2f origin, int size) {
        super(sprite, origin, size);
        speed = 6;
        hSprite.put("idle", new Sprite("resources/sprites/warrior/Idle.png"));
        hSprite.put("run", new Sprite("resources/sprites/warrior/Run.png"));
        hSprite.put("attack", new Sprite("resources/sprites/warrior/Attack1.png"));
        hSprite.put("hit", new Sprite("resources/sprites/warrior/Hit.png"));
    }
    
    @Override
    public void update() {
        super.update();
    }
    
}
