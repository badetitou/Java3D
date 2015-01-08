package fr.view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
