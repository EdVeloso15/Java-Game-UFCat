
package entity;

import graphics.Animation;
import graphics.Font;
import graphics.Sprite;
import java.awt.Graphics2D;
import java.util.HashMap;
import states.PlayState;
import states.State;
import util.KeyHandler;
import util.Vector2f;


public abstract class Entity implements State {
    
    //Animação atual
    protected Animation ani;
    //Lista de Sprites para animação
    protected HashMap<String, Sprite> hSprite;
    //Sprite atual
    protected Sprite sprite;
    //Posição da entidade
    protected Vector2f pos;
    //Armazena a posição x inicial da entidade
    protected float initPosX;
    
    //Vida Máxima da Entidade
    protected float entityMHP;
    //Vida Atual da Entidade
    protected float hp;
    //Booleano para determinar se a entidade morreu ou não
    protected boolean isDead = false;
    //Booleano para determinar se deve haver o cálculo de dano ou não
    protected boolean calculateDamage = false;
    //Dano base da Entidade
    protected int baseDamage;
    //Booleano para determinar se deve receber o dano. Também controla animação.
    protected boolean receiveDamage = false;
    
    //Nível da nossa Entidade - Serve para calculo do dano final e do sua Vida Máxima
    protected int entityLV;
    
    //Tamanho da Sprite
    protected int size;
    //Velocidade de animação no eixo x
    protected float speed;
    
    //Estado de Corrida
    protected boolean attacking = false;
    //Flags para a animação
    protected boolean flag1 = false;
    protected boolean flag2 = false;
    protected boolean flag3 = false;
    
    protected int movement = 0;
    
    public Entity(Sprite sprite, Vector2f origin, int size) {
        this.sprite = sprite;
        pos = origin;
        this.size = size;
        ani = new Animation();
        ani.setAnimation(sprite.getSpriteArray(0), 10);
        initPosX = pos.x;
        hSprite = new HashMap<>();
        calculateMHP();
        hp = entityMHP;
        //    hpAnim.put("idle", new Sprite(" ", 128, 128));      Deve ser colocado o arquivo nas classes filhas
        //    hpAnim.put("run", new Sprite(" ", 128, 128));       Deve ser colocado o arquivo nas classes filhas
        //    hpAnim.put("attack", new Sprite(" ", 128, 128));    Deve ser colocado o arquivo nas classes filhas
        //    hAnim.put("hit", new Sprite(" ", 128, 128));        Deve ser colocado o arquivo nas classes filhas
    }
    
    public boolean getCalculateDamage() { return calculateDamage; }
    public Animation getAnimation() { return ani; }
    public boolean getIsDead() { return isDead; }
    public void receiveDamage() { receiveDamage = true; }
    public void setSprite(Sprite sprite) { this.sprite = sprite; }
    public void setLV(int level) { 
        this.entityLV = level;
        calculateMHP();
        hp = entityMHP;
        calculateBaseDamage();
    }
    public void checkHP() {
        if(hp<= 0)
            isDead = true;
    }
    
    public void calculateMHP() {
        entityMHP = 50 + ((entityLV -1)*10);
    }
    
    public void calculateBaseDamage() {
        baseDamage = 5 + entityLV;
    }
    
    public int damage(int variation) {
        int damage;
        //Serve para não haver cura e sim dano
        if(variation > baseDamage)
            variation = baseDamage;
        else if(variation < 0)
            variation = 0;
        if(Math.floor(Math.random()) == 1)
            damage = baseDamage + (int)Math.floor(Math.random() * variation);
        else
            damage = baseDamage - (int)Math.floor(Math.random() * variation);
        return damage;
    }
    
    //Realiza o cálculo de dano e o efetua na entidade apropriada
    public void calculateDamage(Entity entity, int variation) {
        entity.hp -= damage(variation);
        calculateDamage = false;
    }
    
    public abstract void animate();
    
    @Override
    public void update() {
        animate();
        ani.update();
        checkHP();
    }

    @Override
    public abstract void input(KeyHandler key);

    @Override
    public void render(Graphics2D g) {
        Font.drawArray(g, PlayState.getFont(), "HP: " + (int)hp, new Vector2f(initPosX, pos.y-10), 32, 32, 24, 0);
    };
}
