package game;

import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;


public class Window extends JFrame{

    public Window() throws HeadlessException {
        setTitle("RPG - JAVA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Setar o conteúdo
        setContentPane(new GamePanel(1200,800));
        setPreferredSize(new Dimension(1200, 800));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    
}
