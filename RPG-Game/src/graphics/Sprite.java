
package graphics;

import util.Vector2f;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class Sprite {
    protected BufferedImage SPRITESHEET = null;
    protected BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 128;
    public int w;
    public int h;
    protected int wSprite;
    protected int hSprite;
    
    public Sprite(String file) {
        w = TILE_SIZE;
        h = TILE_SIZE;
        
        //System.out.println("Loading: " + file + "...");  //Debug
        SPRITESHEET = loadSprite(file);
        
        wSprite = SPRITESHEET.getWidth() / w;
        hSprite = SPRITESHEET.getHeight() / h;
        loadSpriteArray();
    }
    
    public Sprite(String file, int w, int h) {
        this.w = w;
        this.h = h;
        
        // System.out.println("Loading: " + file + "..."); //Debug
        SPRITESHEET = loadSprite(file);
        
        wSprite = SPRITESHEET.getWidth() / w;
        hSprite = SPRITESHEET.getHeight() / h;
        loadSpriteArray();
    }
    
    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }
    
    public void setWidth(int value) {
        w = value;
        wSprite = SPRITESHEET.getWidth() / w;
    }
    
    public void setHeight(int value) {
        h = value;
        hSprite = SPRITESHEET.getHeight() / h;
    }
    
    public int getWidth() { return w; }
    public int getHeight() { return h; }
    
    private BufferedImage loadSprite(String file) {
        BufferedImage sprite = null;
        try { 
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        } catch(Exception e) {
            System.out.println("ERROR: could not load file: " + file);
        }
        return sprite;
        
    }
    
    public void loadSpriteArray() {
        spriteArray = new BufferedImage[hSprite][wSprite];
        for(int y = 0; y < hSprite; y++) {
            for(int x = 0; x < wSprite; x++) {
                spriteArray[y][x] = getSprite(x, y);
            }
        }
    }
    
    public BufferedImage getSpriteSheet() { 
        return SPRITESHEET; 
    }
    
    public BufferedImage getSprite(int x, int y) {
        return SPRITESHEET.getSubimage(x * w, y * h, w, h);
    }
    //Retorna a todos os sprites da linha do SPRITESHEET
    public BufferedImage[] getSpriteArray(int i) {
        return spriteArray[i];
    }
    //Retorna todos os sprites do SPRITESHEET
    public BufferedImage[][] getSpriteArray2(int i) {
        return spriteArray;
    }
    
    public static void drawArray(Graphics2D g, ArrayList<BufferedImage> img, Vector2f pos,
                                 int width, int height, int xOffset, int yOffset) {
        float x = pos.x;
        float y = pos.y;
        
        for(int i = 0; i < img.size(); i++) {
            if(img.get(i) != null) {
                g.drawImage(img.get(i), (int) x, (int) y, width, height, null);
                x += xOffset;
                y += yOffset;
            }
            
            
        }
    }
    
    
}

