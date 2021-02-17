package nl.tudelft.jpacman.board;

import java.awt.Dimension;
import java.awt.Graphics;
import nl.tudelft.jpacman.ui.BoardPanel;


/**
 * A top-down view of a matrix of {@link Square}s.
 *
 * @author Jeroen Roosen 
 */
public class Board {

    /**
     * The grid of squares with board[x][y] being the square at column x, row y.
     */
    private final Square[][] board;

    /**
     * Creates a new board.
     *
     * @param grid
     *            The grid of squares with grid[x][y] being the square at column
     *            x, row y.
     */
    @SuppressWarnings("PMD.ArrayIsStoredDirectly")
    Board(Square[][] grid) {
        assert grid != null;
        this.board = grid;
        assert invariant() : "Initial grid cannot contain null squares";
    }

    /**
     * Whatever happens, the squares on the board can't be null.
     * @return false if any square on the board is null.
     */
    protected final boolean invariant() {
        for (Square[] row : board) {
            for (Square square : row) {
                if (square == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the number of columns.
     *
     * @return The width of this board.
     */
    public int getWidth() {
        return board.length;
    }

    /**
     * Returns the number of rows.
     *
     * @return The height of this board.
     */
    public int getHeight() {
        return board[0].length;
    }

    /**
     * Returns the square at the given <code>x,y</code> position.
     *
     * Precondition: The <code>(x, y)</code> coordinates are within the
     * width and height of the board.
     *
     * @param x
     *            The <code>x</code> position (column) of the requested square.
     * @param y
     *            The <code>y</code> position (row) of the requested square.
     * @return The square at the given <code>x,y</code> position (never null).
     */
     Square squareAt(int x, int y) {
        assert withinBorders(x, y);
        Square result = board[x][y];
        assert result != null : "Follows from invariant.";
        return result;
    }

    /**
     * Determines whether the given <code>x,y</code> position is on this board.
     *
     * @param x
     *            The <code>x</code> position (row) to test.
     * @param y
     *            The <code>y</code> position (column) to test.
     * @return <code>true</code> iff the position is on this board.
     */
    public boolean withinBorders(int x, int y) {
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
    }
    /**
	 * Renders the board on the given graphics context to the given dimensions.
	 * @param graphics The graphics context to draw on.
	 * @param window The dimensions to scale the rendered board to.
	 * @param boardPanel
	 */
	public void render(Graphics graphics, Dimension window, BoardPanel boardPanel) {
		int cellW = window.width / getWidth();
		int cellH = window.height / getHeight();
		graphics.setColor(BoardPanel.BACKGROUND_COLOR);
		graphics.fillRect(0, 0, window.width, window.height);
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				int cellX = x * cellW;
				int cellY = y * cellH;
				Square square = squareAt(x, y);
				boardPanel.render(square, graphics, cellX, cellY, cellW, cellH);
			}
		}
	}
}
