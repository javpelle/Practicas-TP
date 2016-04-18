package es.ucm.fdi.tp.practica5.swings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;







import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class PlayerInfo extends JPanel {
	private JTable info;
	private Dimension preferred;
	
	public PlayerInfo(List<Piece> pieces, Piece viewPiece, Board board, Color[] colors) {
		setBorder(new TitledBorder("Player Information"));
		setLayout(new BorderLayout());
		
		String [] columnNames = { "Player", "Mode", "#Pieces"};
		 info = new JTable(new MyTableModel(pieces, columnNames, board));
			info.setDefaultRenderer(String.class, new DefaultTableCellRenderer() {

				@Override
				public Component getTableCellRendererComponent(JTable table,
						Object value, boolean isSelected, boolean hasFocus,
						int row, int column) {
					JComponent c = (JComponent)super.getTableCellRendererComponent(
							table, value, isSelected, hasFocus, row, column);
					c.setBackground(colors[row]);
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
	
	public void setColors(Color[] colors) {
		
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

		@Override
		public int getRowCount() {return pieces.size();}

		public Class getColumnClass(int col){ return String.class; }
		
		@Override
		public int getColumnCount() {return columnNames.length;}

		@Override
		public Object getValueAt(int row, int col) {
			switch(col) {
			case 0: return pieces.get(row);
			case 1: return "RANDOM";
			default: return board.getPieceCount(pieces.get(row));
			}		
		}

	}
}
