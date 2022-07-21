import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Guy {
    private int x;
    private int y;
    private int dx;
    private boolean good;
    private BufferedImage img;
    private int img_width, img_height;

    public Guy( int x, int y, boolean good ){
        this.x = x;
        this.y = y;
        this.good = good;
        dx = -1*((int)(3*Math.random()+1));

        if ( good ) {
            try {
                img = ImageIO.read(new File("/Users/mattc/IdeaProjects/Game/src/good.png"));
                img_width = 18;
                img_height = 18;
            } catch (IOException e) {
                System.out.println ( e );
            }
        } else {
            try {
                img = ImageIO.read(new File("/Users/mattc/IdeaProjects/Game/src/bad.png"));
                img_width = 30;
                img_height = 30;
            } catch (IOException e) {
                System.out.println ( e );
            }
        }
    }

    public void move(){
        x += dx;
    }

    public int getX(){
        return x;
    }

    public boolean isGood(){
        return good;
    }

    public void draw(  Graphics g ){
        g.drawImage( img, x, y, null );
    }

    public Rectangle getShape(){
        return new Rectangle( x, y, img_width, img_height );
    }
}