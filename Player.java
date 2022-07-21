import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Player{
    private int x, y;
    private int dx = 0, dy = 0;
    private BufferedImage imgGreen, imgYellow, imgRed;
    private int health = 2;

    private static final int IMG_WIDTH = 18;
    private static final int IMG_HEIGHT = 24;
    private int panel_width, panel_height;

    public Player( int x, int y, int panel_w, int panel_h ){
        this.x = x;
        this.y = y;
        panel_width = panel_w;
        panel_height = panel_h;

        try {
            imgGreen = ImageIO.read(new File("/Users/mattc/IdeaProjects/Game/src/green_face.png"));
            imgYellow = ImageIO.read(new File("/Users/mattc/IdeaProjects/Game/src/yellow_face.png"));
            imgRed = ImageIO.read(new File("/Users/mattc/IdeaProjects/Game/src/red_face.png"));
        } catch (IOException e) {
            System.out.println ( e );
        }
    }

    public void setSpeed( int dx, int dy ){
        this.dx = dx;
        this.dy = dy;
    }

    public void hurt(){
        health--;
    }

    public boolean isAlive(){
        return health > 0;
    }

    public void move(){
        x += dx;
        y += dy;
        if(x > panel_width-IMG_WIDTH){
            x = panel_width - IMG_WIDTH;
        }
        if(x < 0){
            x = 0;
        }
        if(y > panel_height - IMG_HEIGHT){
            y = panel_height - IMG_HEIGHT;
        }
        if(y < 0){
            y = 0;
        }
    }

    public void draw(  Graphics g ){
        if ( health == 2 )
            g.drawImage( imgGreen, x, y, null );
        else if ( health == 1 )
            g.drawImage( imgYellow, x, y, null );
        else
            g.drawImage( imgRed, x, y, null );
    }

    public Rectangle getShape(){
        return new Rectangle( x, y, IMG_WIDTH, IMG_HEIGHT );
    }
}