import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.Line;
import javax.swing.*;
class Start extends JFrame implements ActionListener {
    int x = 1500;
    int y = 550;
    boolean touch = true;
    int direction = 1;

    int cx = 250;
    int cy = 250;

    public Start(){
        JFrame window = new JFrame("Drawing Board");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlowLayout myL = new FlowLayout(0);
        window.setLayout(myL);
        window.setSize(new Dimension(x, y));
        window.setVisible(true);

        JPanel board = new JPanel();
        board.setBackground(Color.yellow);
        window.add(board);
        board.setPreferredSize(new Dimension(500, 500));
        board.setVisible(true);

        JLabel angle = new JLabel();
        angle.setText("kierunek: " + (90 * (direction - 1)));
        window.add(angle);

        JButton left = new JButton("Left");
        window.add(left);
        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(direction == 1){
                    direction = 4;
                }
                else{
                    direction--;
                }

                angle.setText("kierunek: " + (90 * (direction - 1)));
            }
        });

        JButton right = new JButton("Right");
        window.add(right);
        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(direction == 4){
                    direction = 1;
                }
                else{
                    direction++;
                }

                angle.setText("kierunek: " + (90 * (direction - 1)));
            }
        });

        JLabel location = new JLabel();
        location.setText("x: " + cx + ", y: " + cy);
        window.add(location);

        JButton forward = new JButton("Forward");
        window.add(forward);
        forward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Graphics gr = board.getGraphics();
                if(direction == 1){
                    if(touch == true){
                        paintComponent(gr, cx, cy, cx, cy - 25);
                    }
                    cy -= 25;
                }
                if(direction == 2){
                    if(touch == true) {
                        paintComponent(gr, cx, cy, cx + 25, cy);
                    }
                    cx += 25;
                }
                if(direction == 3) {
                    if (touch == true){
                        paintComponent(gr, cx, cy, cx, cy + 25);
                    }
                    cy += 25;
                }
                if(direction == 4){
                    if(touch == true) {
                        paintComponent(gr, cx, cy, cx - 25, cy);
                    }
                    cx -= 25;
                }
                location.setText("x: " + cx + ", y: " + cy);
            }
        });

        JLabel status = new JLabel();
        status.setText("pióro opuszczone");
        window.add(status);

        JButton penUp = new JButton("Pen Up");
        window.add(penUp);
        penUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                touch = false;
                status.setText("pióro podniesione");
            }
        });

        JButton penDown = new JButton("Pen Down");
        window.add(penDown);
        penDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                touch = true;
                status.setText("pióro opuszczone");
            }
        });

        JButton clean = new JButton("Clean");
        window.add(clean);
        clean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.removeAll();
                board.repaint();
                cx = 250;
                cy= 250;
                location.setText("x: " + cx + ", y: " + cy);
                direction = 1;
                angle.setText("kierunek: " + (90 * (direction - 1)));
                touch = true;
                status.setText("pióro opuszczone");
            }
        });

        JButton quit = new JButton("Quit");
        window.add(quit);
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


    }

    public void paintComponent(Graphics g, int a, int b, int c, int d){
        super.paint(g);
        g.drawLine(a, b, c, d);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }


    public static void main( String[] argv ) {
        Start test = new Start();
        test.repaint();
    }


}


