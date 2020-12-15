
package states;

import util.KeyHandler;
import java.awt.Graphics2D;

public interface State {
    
    public void update();
    
    public void input(KeyHandler key);
    
    public void render(Graphics2D g);
}
