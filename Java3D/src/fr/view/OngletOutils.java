package fr.view;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class OngletOutils extends JPanel{

	public OngletOutils(MyDeskTopPane dp,BarreVerticale bv){
		this.setBackground(new Color(190,190,190));
		JTabbedPane tabbedPane = new JTabbedPane();
		PanelEdit pe=new PanelEdit(dp,bv);
		tabbedPane.addTab("Edition",new ImageIcon(new ImageIcon("ressources/icones/description.png").getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT)), pe);
		tabbedPane.addTab("Recherche Avancee",new ImageIcon(new ImageIcon("ressources/icones/description.png").getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT)), new PanelRecherche());
		this.add(tabbedPane);
	}
}
