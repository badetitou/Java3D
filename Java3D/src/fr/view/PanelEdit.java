package fr.view;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelEdit extends JPanel implements ChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final MyDeskTopPane dp;
	private final BarreVerticale bv;
	private final JColorChooser jcc;
	private final JLabel hauteurLabel;
	private final JLabel largeurLabel;
	private final JLabel profondeurLabel;
	private final JTextField hauteurField;
	private final JTextField profondeurField;
	private final JTextField largeurField;

	public PanelEdit(MyDeskTopPane dp, BarreVerticale bv) {
		this.bv = bv;
		this.dp = dp;
		this.setBackground(new Color(190, 190, 190));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		jcc = new JColorChooser();

		/*
		 * Couleur
		 */
		AbstractColorChooserPanel[] oldPanels = jcc.getChooserPanels();
		jcc.removeChooserPanel(oldPanels[0]);
		jcc.getSelectionModel().addChangeListener(this);
		bv.setJcc(jcc);
		jcc.setEnabled(false);
		jcc.setVisible(false);
		this.add(jcc);
	}

	public void stateChanged(ChangeEvent arg0) {
		dp.getModel().changeColor(jcc.getColor());
		dp.getPanel().repaint();
	}
}
