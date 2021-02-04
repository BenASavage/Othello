package othello;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.border.BevelBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

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
    private Board board = new Board();
    private JPanel mainPanel;
    private JPanel boardPanel = new JPanel();

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
     *
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
        JLabel lblWhitePlayer = new JLabel("White Discs");
        whitePanel.add(lblWhitePlayer);
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
        JLabel lblBlkPlayer = new JLabel("Black Discs");
        blkPanel.add(lblBlkPlayer);
        return blkPanel;
    }

    /**
     * Creates a board of 64 JButtons, representing a game board.
     *
     * On click the buttons paint an icon if the JButton coordinate is a playable
     * one.
     *
     * @return
     */
    private JPanel initBoardPanel() {
        ArrayList<Coordinate> playableTiles = board.getPlayableTiles();
        if (playableTiles.isEmpty()) {
            //TODO show who wins
            JOptionPane.showMessageDialog(mainPanel, "GAME OVER");
        }

        boardPanel.removeAll();

        boardPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
        boardPanel.setBackground(new Color(65, 156, 124));
        boardPanel.setLayout(new GridLayout(8, 8, 4, 4));



        for (int row = 0; row < board.getTiles().length; row++) {
            for (int col = 0; col < board.getTiles()[row].length; col++) {
                JButton btnTile = new JButton("");
                btnTile.setEnabled(false);

                btnTile.setOpaque(true);
                btnTile.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                btnTile.setBackground(new Color(65, 156, 124));

                if (board.getTiles()[row][col] != null) {
                    //TODO
                    btnTile.setBackground(board.getTiles()[row][col].getColor());
                }


                if (playableTiles.contains(new Coordinate(row, col))) {
                    btnTile.setBackground(Color.YELLOW);
                    btnTile.setContentAreaFilled(false);
                    btnTile.setBorderPainted(true);
                    btnTile.setEnabled(true);
                }

                int finalRow = row;
                int finalCol = col;
                btnTile.addActionListener(e -> {
                    //TODO
                    Coordinate cord = new Coordinate(finalRow, finalCol);
                    if (board.getPlayableTiles().contains(cord)) {
                        board.placeDisc(cord);
                        initBoardPanel();
                        boardPanel.repaint();
                        boardPanel.revalidate();
                    }

                });

                boardPanel.add(btnTile);
            }

        }
        return boardPanel;
    }

    /**
     * Instantiates a control panel of type JPanel.
     *
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
            JButton btnStart = new JButton("Start");
            controlPanel.add(btnStart, BorderLayout.EAST);
        }
        {
            JLabel lblGameState = new JLabel("Turn");
            lblGameState.setBorder(new EmptyBorder(2, 0, 2, 5));
            lblGameState.setHorizontalAlignment(SwingConstants.RIGHT);
            controlPanel.add(lblGameState, BorderLayout.SOUTH);
        }
        return controlPanel;
    }

}
