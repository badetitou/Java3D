package fr.view;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelDescription extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JTextArea textArea;
	public PanelDescription(){
		textArea=new JTextArea();
		textArea.setPreferredSize(new Dimension(100,600));
	}

}
