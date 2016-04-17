package es.ucm.fdi.tp.practica5.swings;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class PlayerInfo extends JPanel {
	private JTable info;
	
	public PlayerInfo(List<Piece> pieces, Piece viewPiece, Board board) {
		super();
		setBorder(new TitledBorder("Player Information"));
		
		String [] columnNames = { "Player", "Mode", "#Pieces"  };
		Object[][] data = new Object[pieces.size()][3];
		for (int i = 0; i < pieces.size(); i++) {
			data[i][0] = pieces.get(i).getId();
			if (viewPiece == null || viewPiece.getId().equals(pieces.get(i).getId())) {
				// Si viewPiece es null (no es multiview) o es la pieza correspondiente a 
				// la multiventana abierta ponemos el tipo de jugador.
				data[i][1] = null;
			} else {
				data[i][1] = null;
			}
			data[i][2] = board.getPieceCount(pieces.get(i));
		}
		 info = new JTable(data,columnNames);
		 info.setFillsViewportHeight(true);
		 add(new JScrollPane(info));
	}
	
	public boolean isCellEditable(int row, int col)
    { return false; }
	
}
