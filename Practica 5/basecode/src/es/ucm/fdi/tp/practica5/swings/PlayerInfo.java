package es.ucm.fdi.tp.practica5.swings;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class PlayerInfo extends JPanel {
	private JTable info;
	private JScrollPane p;
	
	public PlayerInfo(int players) {
		super();
		info = new JTable(players, 3);
		JTableHeader th = info.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc = tcm.getColumn(0);
		tc.setHeaderValue( "Player" );
		tc = tcm.getColumn(1);
		tc.setHeaderValue( "Mode" );
		tc = tcm.getColumn(2);
		tc.setHeaderValue( "#Pieces" );
		th.repaint();
		this.p = new JScrollPane(info);
        add(p);	
	}
}
