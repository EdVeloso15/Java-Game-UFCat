
package graphics;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import util.Vector2f;


public class Font extends Sprite {

    public Font(String file) {
        super(file);
    }

    public Font(String file, int w, int h) {
        super(file, w, h);
    }
    
    public void loadFontArray() {
        spriteArray = new BufferedImage[wSprite][hSprite];
        for(int x = 0; x < wSprite; x++) {
            for(int y = 0; y < hSprite; y++) {
                spriteArray[x][y] = getSprite(x, y);
            }
        }
    }
    
    public BufferedImage getFont(char letter) {
        int value = letter; 
        int x = value % wSprite;
        int y = value / wSprite;
        return getSprite(x,y);
    }
    
    public static void drawArray(Graphics2D g, Font f, String word, Vector2f pos, 
                                int width, int height, int xOffset, int yOffset) {
        float x = pos.x;
        float y = pos.y;
        
        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) != 32)
                g.drawImage(f.getFont(word.charAt(i)), (int) x, (int) y, width, height, null);
            x += xOffset;
            y += yOffset;
        }    
    }
    
}

