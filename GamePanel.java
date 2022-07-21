import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    private Game game;
    private boolean [] key = new boolean[4];

    public GamePanel( Game g ) {
        game = g;
        setBackground( Color.ORANGE );
        setBorder( BorderFactory.createLineBorder(Color.BLACK ) );

        addKeyListener( new Listen() );
        setFocusable(true);
    }

    @Override
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );
        game.draw( g );
    }

    private class Listen extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            if (e.getKeyCode() == KeyEvent.VK_LEFT)
                key[0] = true;
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                key[1] = true;
            else if (e.getKeyCode() == KeyEvent.VK_UP)
                key[2] = true;
            else if (e.getKeyCode() == KeyEvent.VK_DOWN)
                key[3] = true;

            game.updatePlayerMotion( calculateDx(), calculateDy() );
        }

        @Override
        public void keyReleased(KeyEvent e){
            if (e.getKeyCode() == KeyEvent.VK_LEFT)
                key[0] = false;
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                key[1] = false;
            else if (e.getKeyCode() == KeyEvent.VK_UP)
                key[2] = false;
            else if (e.getKeyCode() == KeyEvent.VK_DOWN)
                key[3] = false;

            game.updatePlayerMotion( calculateDx(), calculateDy() );
        }

        private int calculateDx(){
            int dx = 0;
            if ( key[0] )
                dx = dx - 3;
            if ( key[1] )
                dx = dx + 3;

            return dx;
        }

        private int calculateDy(){
            int dy = 0;
            if ( key[2] )
                dy = dy - 3;
            if ( key[3] )
                dy = dy + 3;

            return dy;
        }

    }
}