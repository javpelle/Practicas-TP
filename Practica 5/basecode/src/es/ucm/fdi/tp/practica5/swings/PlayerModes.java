package es.ucm.fdi.tp.practica5.swings;

import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class PlayerModes extends JPanel {
	
	private JButton set;
	private JComboBox <Piece> infoPieces;
	private JComboBox <String> playerMode;
	
	public PlayerModes (List<Piece> pieces) {
		setBorder(new TitledBorder("Player Modes"));
		set = new JButton ("set");
		infoPieces = new JComboBox();
		for (int i = 0; i < pieces.size(); i++) {
			infoPieces.addItem(pieces.get(i));
		}
		playerMode = new JComboBox();
		playerMode.addItem("Manual");
		playerMode.addItem("Random");
		add(infoPieces);
		add(playerMode);
		add(set);
	}
}
