
package states;
import graphics.Font;
import util.KeyHandler;
import java.awt.Graphics2D;


public abstract class GameState implements State{

    private GameStateManager gsm;
    protected static Font whiteFont;
    protected static Font blackFont;
    
    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
        whiteFont = new Font("resources/font/font.png", 10, 10);
        blackFont = new Font("resources/font/galaxyfont.png", 20, 20);
    }
    
    public abstract void update();
    public abstract void stateChange();
    public abstract void input(KeyHandler key);
    public abstract void render(Graphics2D g);
    
    public GameStateManager getGSM() { return gsm; }
}
