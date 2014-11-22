package entity;

// TODO: Auto-generated Javadoc
/**
 * Representasi dari sebuah pasangan koordinat baris dan kolom.
 *
 * @author Johanes
 */
public class RowColLocation {
	
	/** The row. */
	private int row;
	
	/** The col. */
	private int col;
	
	/**
	 * constructor.
	 *
	 * @param row baris
	 * @param col kolom
	 */
	public RowColLocation(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

	/**
	 * Gets the row.
	 *
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Sets the row.
	 *
	 * @param row the new row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Gets the col.
	 *
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Sets the col.
	 *
	 * @param col the new col
	 */
	public void setCol(int col) {
		this.col = col;
	}
}
