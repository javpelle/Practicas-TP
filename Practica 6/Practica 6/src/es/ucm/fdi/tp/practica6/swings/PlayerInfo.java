package es.ucm.fdi.tp.practica6.swings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica6.Main.PlayerMode;


public class PlayerInfo extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTable info;
	private Dimension preferred;
	private Piece viewPiece;
	private Map<Piece, PlayerMode> piecesModes;
	private MyTableModel tableModel;
	
	public PlayerInfo(final List<Piece> pieces, Piece viewPiece, Board board,
			final Map<Piece, Color> colorPieces, Map<Piece, PlayerMode> piecesModes) {
		setBorder(new TitledBorder("Player Information"));
		setLayout(new BorderLayout());
		this.piecesModes = piecesModes;
		this.viewPiece = viewPiece;
		String [] columnNames = { "Player", "Mode", "#Pieces"};
		tableModel = new MyTableModel(pieces, columnNames, board);
		info = new JTable(tableModel);
			info.setDefaultRenderer(String.class, new DefaultTableCellRenderer() {
				private static final long serialVersionUID = 1L;

				@Override
				public Component getTableCellRendererComponent(JTable table,
						Object value, boolean isSelected, boolean hasFocus,
						int row, int column) {
					JComponent c = (JComponent)super.getTableCellRendererComponent(
							table, value, isSelected, hasFocus, row, column);
					c.setBackground(colorPieces.get(pieces.get(row)));
					return c;
				}
				
			});
		 
		 
		 add(new JScrollPane(info));
	}
	
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	public Dimension getPreferredSize() {
		preferred = super.getPreferredSize();
		preferred.setSize(preferred.getWidth(), 200);
		return preferred;
	}
	
	public void updateTable(Board board) {
		tableModel.setBoard(board);
		info.updateUI();
	}
	
	private class MyTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		
		private String[] columnNames;
		private List<Piece> pieces;
		private Board board;
		
		public MyTableModel(List<Piece> pieces,String[] columnNames, Board board){
			this.pieces = pieces;
			this.columnNames = columnNames;
			this.board = board;
		}		
		
		public String getColumnName(int column) {
		    return columnNames[column];
		}

		@Override
		public int getRowCount() {return pieces.size();}

		public Class<?> getColumnClass(int col){ return String.class; }
		
		@Override
		public int getColumnCount() {return columnNames.length;}

		@Override
		public Object getValueAt(int row, int col) {
			switch(col) {
			case 0: return pieces.get(row);
			case 1: 
				if (viewPiece == null || viewPiece.equals(pieces.get(row))) {
					return piecesModes.get(pieces.get(row)).getDesc();
				} else {
					return null;
				}
			default:
			return board.getPieceCount(pieces.get(row));
			
			}		
		}
		
		public void setBoard (Board board) {
			this.board = board;
		}

	}
}
