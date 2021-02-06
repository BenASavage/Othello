package othello;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.border.BevelBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Point;

/**
 * The purpose of OthelloGUI is to play the game Othello.
 *
 * @author Steven Ferguson
 */
public class GUI extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 9185851023736830551L;
    private ImageIcon[] discIcons = { new ImageIcon(("src/images/BlackIcon.png")),
            new ImageIcon(("src/images/WhiteIcon.png")) };
    private Board board = new Board();
    private JPanel mainPanel;
    private JPanel boardPanel = new JPanel();
    private JLabel lblBlkCount;
    private JLabel lblWhiteCount;
    private Color turn;
    private JLabel lblGameState = new JLabel("Black Turn");



    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI frame = new GUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructs the gui which consists of panels for controls. Also holds the
     * board and disc panels.
     * <p>
     * Create the frame.
     */
    public GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 500);
        mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainPanel.setLayout(new BorderLayout(0, 0));
        setContentPane(mainPanel);

        JPanel controlPanel = initControlPanel();
        mainPanel.add(controlPanel, BorderLayout.NORTH);

        JPanel boardPanel = initBoardPanel();
        mainPanel.add(boardPanel, BorderLayout.CENTER);

        JPanel blkPanel = createBlkPanel();
        mainPanel.add(blkPanel, BorderLayout.WEST);

        JPanel whitePanel = createWhitePanel();
        mainPanel.add(whitePanel, BorderLayout.EAST);

    }

    /**
     * Creates a panel to hold white disc icons and a border color that reflects
     * player turn
     *
     * @return
     */
    private JPanel createWhitePanel() {
        JPanel whitePanel = new JPanel();
        JLabel lblWhitePlayer = new JLabel("White Discs:");
        whitePanel.add(lblWhitePlayer);

        lblWhiteCount = new JLabel("0");
        whitePanel.add(lblWhiteCount);
        return whitePanel;
    }

    /**
     * Creates a panel to hold black disc icons and a border color that reflects
     * player turn
     *
     * @return
     */
    private JPanel createBlkPanel() {
        JPanel blkPanel = new JPanel();
        JLabel lblBlkPlayer  = new JLabel("Black Discs:");
        blkPanel.add(lblBlkPlayer);

        lblBlkCount = new JLabel("0");
        blkPanel.add(lblBlkCount);
        return blkPanel;
    }

    /**
     * Creates a board of 64 JButtons, representing a game board.
     * <p>
     * On click the buttons paint an icon if the JButton coordinate is a playable
     * one.
     *
     * @return
     */
    private JPanel initBoardPanel() {
        boardPanel.removeAll();

        //sets the board panel color, background and border.
        boardPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
        boardPanel.setBackground(new Color(65, 156, 124));
        boardPanel.setLayout(new GridLayout(8, 8, 4, 4));


        ArrayList<Coordinate> playableTiles = board.getPlayableTiles();
        for (int row = 0; row < board.getTiles().length; row++) {
            for (int col = 0; col < board.getTiles()[row].length; col++) {
                JButton btnTile = new JButton();
                btnTile.setEnabled(false);
                btnTile.setOpaque(true);
                btnTile.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                btnTile.setBackground(new Color(65, 156, 124));

                //if the button at index is not null the background gets set to that color.
                if (board.getTiles()[row][col] != null) {

                    btnTile.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

                    //btnTile.setBackground(board.getTiles()[row][col].getColor());

                    if(board.getTiles()[row][col].getColor() == Color.BLACK)
                        btnTile.setIcon(discIcons[0]);

                    else
                        btnTile.setIcon(discIcons[1]);
                }



                //iterates the tiles array, creating a JButton for each object in the array.
                //sets the tile border and background. all tiles are initially disabled.
                if (playableTiles.contains(new Coordinate(row, col))) {
                    btnTile.setBackground(Color.YELLOW);
                    btnTile.setContentAreaFilled(false);
                    btnTile.setBorderPainted(true);
                    btnTile.setEnabled(true);
                }

                int finalRow = row;
                int finalCol = col;
                btnTile.addActionListener(e -> {
                    Coordinate cord = new Coordinate(finalRow, finalCol);
                    if (playableTiles.contains(cord)) {
                        board.placeDisc(cord);
                        lblBlkCount.setText("" + board.getDiscCount()[0] + "");
                        lblWhiteCount.setText("" + board.getDiscCount()[1] + "");
                        if(board.getPlayerTurn() == Color.BLACK)
                            lblGameState.setText("Black Turn");
                        else
                            lblGameState.setText("White Turn");
                        boardPanel.repaint();
                        boardPanel.revalidate();
                        initBoardPanel();
                    }

                });

                boardPanel.add(btnTile);

            }

        }
        if (playableTiles.isEmpty()) {
            int[] discCount = board.getDiscCount();
            JOptionPane.showMessageDialog(mainPanel, "GAME OVER\n" +
                    (discCount[0] > discCount[1] ? "Black ": "White ") + "Wins!");
        }
        return boardPanel;
    }

    /**
     * Instantiates a control panel of type JPanel.
     * <p>
     * Contains a buttons to start and reset the game.
     *
     * @return controlPanel
     */
    private JPanel initControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout(0, 0));
        {
            JLabel lblTitle = new JLabel("Othello");
            lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
            controlPanel.add(lblTitle, BorderLayout.CENTER);
        }
        {
            JButton btnNewGame = new JButton("New Game");
            btnNewGame.setFocusPainted(false);
            btnNewGame.addActionListener(e -> {
                board = new Board();
                initBoardPanel();
                mainPanel.repaint();
                mainPanel.revalidate();
            });
            controlPanel.add(btnNewGame, BorderLayout.EAST);
        }
        {
            lblGameState.setBorder(new EmptyBorder(2, 0, 2, 5));
            lblGameState.setHorizontalAlignment(SwingConstants.RIGHT);
            controlPanel.add(lblGameState, BorderLayout.SOUTH);
        }
        return controlPanel;
    }

}