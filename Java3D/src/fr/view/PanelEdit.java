package fr.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;

public class PanelEdit extends JPanel implements ChangeListener, PropertyChangeListener {

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
	private final JFormattedTextField hauteurField;
	private final JFormattedTextField profondeurField;
	private final JFormattedTextField largeurField;

	public PanelEdit(MyDeskTopPane dp, BarreVerticale bv) {
		this.bv = bv;
		this.dp = dp;
		this.setBackground(new Color(190, 190, 190));
		this.setLayout(new BorderLayout());
		jcc = new JColorChooser();

		/*
		 * Couleur
		 */
		AbstractColorChooserPanel[] oldPanels = jcc.getChooserPanels();
		jcc.removeChooserPanel(oldPanels[0]);
		jcc.removeChooserPanel(oldPanels[1]);
		jcc.removeChooserPanel(oldPanels[2]);
		jcc.removeChooserPanel(oldPanels[4]);
		jcc.setPreviewPanel(new JPanel());
		jcc.getSelectionModel().addChangeListener(this);
		bv.setJcc(jcc);
		jcc.setEnabled(false);
		jcc.setVisible(false);
		this.add(jcc, BorderLayout.CENTER);
		
		/*
		 * Dimension
		 */
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    // If you want the value to be committed on each keystroke instead of focus lost
	    formatter.setCommitsOnValidEdit(true);
		
		hauteurLabel = new JLabel("Hauteur : ");
		largeurLabel = new JLabel("Largeur : ");
		profondeurLabel = new JLabel("Profondeur : ");
		hauteurField = new JFormattedTextField(formatter);
		largeurField = new JFormattedTextField(formatter);
		profondeurField = new JFormattedTextField(formatter);
		hauteurField.setValue(0);
		profondeurField.setValue(0);
		largeurField.setValue(0);
		hauteurField.addPropertyChangeListener(this);
		largeurField.addPropertyChangeListener(this);
		profondeurField.addPropertyChangeListener(this);
		JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hauteurField.setValue(0);
				largeurField.setValue(0);
				profondeurField.setValue(0);
			}
		});
		
		JPanel contentDimension = new JPanel();
		contentDimension.setLayout(new BoxLayout(contentDimension, BoxLayout.Y_AXIS));
		JPanel dimension = new JPanel();
		dimension.setLayout(new GridLayout(3,2));
		dimension.add(hauteurLabel);
		dimension.add(hauteurField);
		dimension.add(largeurLabel);
		dimension.add(largeurField);
		dimension.add(profondeurLabel);
		dimension.add(profondeurField);
		contentDimension.add(dimension);
		contentDimension.add(reset);
		this.add(contentDimension, BorderLayout.NORTH);
	}

	public void stateChanged(ChangeEvent arg0) {
		dp.getModel().changeColor(jcc.getColor());
		dp.getPanel().repaint();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource() == largeurField){
			profondeurField.setValue(0);
			hauteurField.setValue(0);
			dp.getModel().setLargeurFixed((Integer)largeurField.getValue());
		}else if (evt.getSource() == hauteurField){
			largeurField.setValue(0);
			profondeurField.setValue(0);
			dp.getModel().setHauteurFixed((Integer)hauteurField.getValue());
		}else{
			largeurField.setValue(0);
			hauteurField.setValue(0);
			dp.getModel().setProfondeurFixed((Integer)profondeurField.getValue());
		}
		dp.repaint();
	}
}
