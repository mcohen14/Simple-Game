import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JFrame {
    private final int WIDTH = 700;
    private final int BTN_LABEL_HEIGHT = 30;
    private final int GAME_HEIGHT = 500;
    private final int PANEL_HEIGHT = GAME_HEIGHT + 2*BTN_LABEL_HEIGHT;

    private Game game = new Game( WIDTH, GAME_HEIGHT );
    private JButton btn = new JButton( "Start" );
    private JLabel lbl = new JLabel("");
    private JLabel lblGO = new JLabel("");
    private GamePanel gp = new GamePanel( game );
    private javax.swing.Timer timer;

    public GameFrame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle( "Simple Game" );
        setResizable( false );
        JPanel jp = new JPanel();
        jp.setBackground( Color.YELLOW );
        jp.setPreferredSize( new Dimension( WIDTH, PANEL_HEIGHT ) );
        jp.setLayout( null );

        timer = new javax.swing.Timer( 40, new Clock() );

        lbl.setBounds(5, 520, 200, 50);
        jp.add(lbl);

        lblGO.setBounds(300, 520, 100, 50);
        jp.add(lblGO);

        btn.setBounds(300, 5, 100, 25);
        btn.setBackground(Color.YELLOW);
        btn.addActionListener(new ButtonListener());
        jp.add(btn);

        gp.setBounds( 0, BTN_LABEL_HEIGHT, WIDTH, GAME_HEIGHT );
        jp.add( gp );

        getContentPane().add( jp );
        pack();

    }

    public void display() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                setVisible(true);
            }
        });
    }

    private class Clock implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            game.update();
            gp.repaint();   // redraw the panel
            int score = game.getScore();
            if(game.keepPlaying() == false){
                timer.stop();
                lbl.setText("Score: " +game.getScore());
                lblGO.setText("GAME OVER");
            }else{
                lbl.setText("Score: " +game.getScore());
            }
        }
    }

    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            // if the button says Start, start the timer and change the text to Reset
            if(btn.getText().equals("Start")){
                timer.start();
                btn.setText("Reset");
            }else if(btn.getText().equals("Reset")){
                timer.stop();
                btn.setText("Start");
                game.init();
                lbl.setText("");
                lblGO.setText("");
                repaint();
            }
            gp.requestFocusInWindow();
        }
    }
}