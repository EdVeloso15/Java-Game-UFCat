package entity;

import graphics.Sprite;
import util.Vector2f;


public class EvilMage extends Enemy {

    /* Atributos Pai
    protected Animation ani;
    protected HashMap<String, Sprite> hpAnim;
    protected Sprite sprite;
    protected Vector2f pos;
    protected float initPosX;
    
    //Vida Máxima da Entidade
    protected float entityMHP;
    //Vida Atual da Entidade
    protected float hp;
    //Booleano para determinar se a entidade morreu ou não
    protected boolean isDead = false;
    //Booleano para determinar se deve haver o cálculo de dano ou não
    protected boolean calculateDamage = false;
    */
    
    
    public EvilMage(Sprite sprite, Vector2f origin, int size) {
        super(sprite, origin, size);
        speed = 6;
        hSprite.put("idle", new Sprite("resources/sprites/inimigos/evilmage/Idle.png"));
        hSprite.put("attack", new Sprite("resources/sprites/inimigos/evilmage/Attack.png"));
        hSprite.put("run", new Sprite("resources/sprites/inimigos/evilmage/Move.png"));
        hSprite.put("hit", new Sprite("resources/sprites/inimigos/evilmage/Hit.png"));
    }
    
    

    @Override
    public void animate() {
        super.animate();
    }
    
}
