package fr.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Onglet extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final MyDeskTopPane dp;
	private final BarreVerticale bv;
	public Onglet(MyDeskTopPane dp){
		this.dp=dp;
		Toolkit tk=getToolkit();
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(tk.getScreenSize().height,tk.getScreenSize().width));

		bv=new BarreVerticale(this.dp);
		JPanel jp2=new JPanel();
		jp2.add(bv);
		jp2.setBackground(new Color(190,190,190));
		this.add(dp,BorderLayout.CENTER);
		this.add(jp2,BorderLayout.WEST);
		this.add(new PanelEdit(this.dp,bv),BorderLayout.EAST);
		this.add(new PanelBdd(),BorderLayout.SOUTH);

	}
}
