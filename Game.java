import java.awt.*;
import java.util.*;

public class Game{
    private boolean playing = false;
    private int width, height;
    private Player player;
    private ArrayList<Guy> list = new ArrayList<Guy>();
    private int score;

    public Game( int w, int h ){
        width = w;
        height = h;
        player = new Player( 5, height/2 - 10, width, height );
    }

    // sets up the game
    public void init(){
        score = 0;
        player = new Player( 5, height/2 - 10, width, height );
        list.clear();
    }

    public void updatePlayerMotion( int dx, int dy  ){
        player.setSpeed( dx, dy );
    }

    public void update(){
        // update the player's position
        player.move();
        int y = (int) (5*Math.random()) + 1;
        double x = Math.random();
        if(y == 1){
            if(x>0.5){
                list.add(new Guy(710, (int)(Math.random()*500), true));
            }else{
                list.add(new Guy(710, (int)(Math.random()*500), false));
            }
        }
        for(Guy g : list){
            g.move();
        }


        Rectangle player_r = player.getShape();
        for ( int k = list.size() - 1; k >= 0; k-- ){
            Guy guy = list.get( k );
            Rectangle guy_r = guy.getShape();
            if ( player_r.intersects( guy_r ) ){
                if(guy.isGood()){
                    score += 1;
                }else{
                    player.hurt();
                }
                list.remove(guy);
            }
            if(guy.getX()<-20){
                list.remove(guy);
            }
        }
    }

    public boolean keepPlaying(){
        return player.isAlive();
    }

    public int getScore(){
        return score;
    }

    public void draw( Graphics g ){
        player.draw( g );
        for ( Guy guy : list )
            guy.draw( g );

    }
}