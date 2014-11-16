package fr.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Onglet extends JPanel implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final MyDeskTopPane dp;
	private final BarreVerticale bv;
	private final JTabbedPane tabbedPane;
	private JLabel closeButon;
	private final String nomFichier;
	private JLabel ic;
	private final ArrayList<Onglet>listeOnglets;
	public Onglet(MyDeskTopPane dp, int num, JTabbedPane tabbedPane,String nomFichier,ArrayList<Onglet>listeOnglets){
		this.listeOnglets=listeOnglets;
		this.dp=dp;
		this.tabbedPane=tabbedPane;
		this.nomFichier=nomFichier;
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

	public void dessineOnglet(){
		closeButon = new JLabel(new ImageIcon(new ImageIcon("ressources/icones/fermer.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		closeButon.addMouseListener(this);
		JPanel p1=new JPanel();
		p1.setOpaque(false);
		ic = new JLabel(new ImageIcon(new ImageIcon("ressources/screenshot.png").getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT)));
		JLabel lbTitle=new JLabel(nomFichier);
		p1.add(ic);
		p1.add(lbTitle);
		p1.add(closeButon);
		listeOnglets.add(this);
		this.tabbedPane.setTabComponentAt(rechercheOnglet(),p1);
	}

	public int rechercheOnglet(){
		for(int i=0;i<listeOnglets.size();i++){
			if(listeOnglets.get(i).equals(this))
				return i;
		}
		return -1;
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(closeButon)){
			System.out.println("fermer");
			tabbedPane.remove(this);
			listeOnglets.remove(this);
			Window.nbOnglets--;
		}

	}
	public void mouseEntered(MouseEvent arg0) {
		closeButon.setBorder(BorderFactory.createLineBorder(new Color(150,150,150)));
	}
	public void mouseExited(MouseEvent arg0) {
		closeButon.setBorder(null);
	}
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
