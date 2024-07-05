import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Tetris extends JFrame {

    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 20;
    private static final int BLOCK_SIZE = 30; // 블록 크기 (픽셀 단위)

    private int[][] board = new int[BOARD_HEIGHT][BOARD_WIDTH]; // 게임 보드 배열
    private int[][][][] blocks = new int[7][4][4][4];

    
    private int currentBlockPosY = 0;  // y축
    private int currentBlockPosX = 0;  // x축
    private int[][][] currentBlock;

    public Tetris() {
        setTitle("Tetris");
        setSize(BOARD_WIDTH * BLOCK_SIZE, BOARD_HEIGHT * BLOCK_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BoardPanel boardPanel = new BoardPanel();
        add(boardPanel);
        
        // 현재블록 한개 뽑아오기
        
        blocks[0][0][0][0] = 1;
        blocks[0][0][0][1] = 1;
        blocks[0][0][1][1] = 1;
        blocks[0][0][2][1] = 1;
        
        currentBlock = blocks[0];
        
    	Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	currentBlockPosY++;
            }
        });
    	 timer.start();
    }

    private class BoardPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawBoard(g);
        }

        private void drawBoard(Graphics g) {
        	board[0][0] = 1;
        	board[0][1] = 1;
        	board[1][1] = 1;
        	board[2][1] = 1;
        	
        	
        	
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                for (int x = 0; x < BOARD_WIDTH; x++) {
                	if(currentBlockPosY == y && currentBlockPosX == x) {
                		// 4x4 반복하면 1인얘들만 y축은 by+y x축은 bx+x 그 위치에 해당하는 board[by+y][bx+x] = 1
                	}
                    if (board[y][x] == 0) {
                        g.setColor(Color.BLACK); // 빈 칸은 검정색으로 표시
                    } else {
                        g.setColor(Color.BLUE); // 블록이 있는 칸은 파란색으로 표시
                    }
                    g.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                    g.setColor(Color.GRAY);
                    g.drawRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Tetris game = new Tetris();
            game.setVisible(true);
        });
    }
}