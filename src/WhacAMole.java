import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class WhacAMole {
    private final int boardWidth = 600;
    private final int boardHeight = 650; 

    private JFrame frame = new JFrame("Mario: Whac A Mole");
    private JLabel textLabel = new JLabel();
    private JPanel textPanel = new JPanel();
    private JPanel boardPanel = new JPanel(); 
	
    private JButton[] board = new JButton[9];
    private ImageIcon moleIcon;
    private ImageIcon plantIcon;

    private JButton currMoleTile;
    private JButton currPlantTile;
    private JButton restart;

    private Random random = new Random();
    private Timer setMoleTimer;
    private Timer setPlantTimer;
    private int score = 0;

    public WhacAMole() {
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Score: " + score);
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);		
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        frame.add(boardPanel);

        plantIcon = loadImageIcon("piranha.png", 150, 150);
        moleIcon = loadImageIcon("monty.png", 150, 150);

        restart = new JButton("RESTART");
        restart.setPreferredSize(new Dimension(100,40));
        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                restart_game();
            }
        });
        textPanel.add(restart, BorderLayout.EAST); 
		
        for (int i = 0; i < 9; i++) {
            JButton tile = new JButton();
            board[i] = tile;
            boardPanel.add(tile);
            tile.setFocusable(false);

            tile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton tile = (JButton) e.getSource();
                    if (tile == currMoleTile) {
                        score += 10;
                        textLabel.setText("Score: " + score);
                    } else if (tile == currPlantTile) {
                        textLabel.setText("Game Over: " + score);
                        setMoleTimer.stop();
                        setPlantTimer.stop();
                        for (JButton b : board) {
                            b.setEnabled(false);
                        }
                    }
                }
            });
        }

        setMoleTimer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currMoleTile != null) {
                    currMoleTile.setIcon(null);
                }

                int num = random.nextInt(9);
                JButton tile = board[num];

                if (currPlantTile == tile) return;

                currMoleTile = tile;
                currMoleTile.setIcon(moleIcon);
            }
        });

        setPlantTimer = new Timer(550, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currPlantTile != null) {
                    currPlantTile.setIcon(null);
                }

                int num = random.nextInt(9);
                JButton tile = board[num];

                if (currMoleTile == tile) return;

                currPlantTile = tile;
                currPlantTile.setIcon(plantIcon);
            }
        });

        setMoleTimer.start();
        setPlantTimer.start();
        frame.setVisible(true);
    }

    private void restart_game()
    {
        score = 0;
        textLabel.setText("SCORE = " + score);
        currMoleTile = null;
        currPlantTile = null;
        for(JButton tile : board)
        {
            tile.setIcon(null);
            tile.setEnabled(true);
        }
        setMoleTimer.restart();
        setPlantTimer.restart();
    }

    private ImageIcon loadImageIcon(String path, int width, int height) {
        Image img = new ImageIcon(getClass().getResource(path)).getImage();
        return new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    
}
