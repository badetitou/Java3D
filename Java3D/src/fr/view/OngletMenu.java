package fr.view;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import fr.model.OutilsBdd;

public class OngletMenu extends JPanel implements MouseListener{

	private final JLabel closeButon;
	private final JLabel ic;
	private final JPanel p1;
	private final JTabbedPane tabbedPane;
	private final ArrayList<Object>listeOnglets;

	public OngletMenu(JTabbedPane tabbedPane,ArrayList<Object>listeOnglets){
		this.listeOnglets=listeOnglets;
		this.tabbedPane=tabbedPane;
		this.setLayout(new GridLayout(1,3));
		this.add(new PanelCrit());
		this.add(new PanelListebdd());
		this.add(new PanelArboPreview());
		listeOnglets.add(this);
		closeButon = new JLabel(new ImageIcon(new ImageIcon("ressources/icones/fermer.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		closeButon.addMouseListener(this);
		ic = new JLabel(new ImageIcon(new ImageIcon("ressources/icones/iconeMenu.png").getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH)));
		p1=new JPanel();
		this.setVisible(true);
	}

	public void dessineOnglet(){
		p1.setOpaque(false);
		JLabel lbTitle=new JLabel("Menu");
		p1.add(ic);
		p1.add(lbTitle);
		p1.add(closeButon);
		this.tabbedPane.setTabComponentAt(rechercheOnglet(),p1);
		this.repaint();
		this.revalidate();
	}


	public int rechercheOnglet(){
		for(int i=0;i<listeOnglets.size();i++){
			if(listeOnglets.get(i).equals(this))
				return i;
		}
		return -1;
	}


	public class PanelCrit extends JPanel{

		private final JButton valider;
		private final JButton sensASC;
		private final JButton sensDESC;
		private final JLabel jt1;
		private final JLabel jt2;
		private final JLabel jt3;

		public PanelCrit(){
			this.valider = new JButton("Valider");
			this.jt1 = new JLabel("Recherche Avancée: ");
			this.jt2 = new JLabel("Critère: ");
			this.jt3 = new JLabel("Sens: ");
			this.sensASC = new JButton();
			this.sensDESC = new JButton();
		}

	}

	public class PanelListebdd extends JPanel{

		private final JList bdd;
		private final OutilsBdd obdd;

		public PanelListebdd(){
			obdd = new OutilsBdd("Database.db");
			String[] data = obdd.getData();
			bdd = new JList(data);

		}
	}

	public class PanelArboPreview extends JPanel{

		public PanelArboPreview(){
			/* POUR LOIC GGWP */
		}
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(closeButon)){
			tabbedPane.remove(this);
			listeOnglets.remove(this);
		}

	}

	public void mouseEntered(MouseEvent arg0) {
		closeButon.setIcon(new ImageIcon(new ImageIcon("ressources/icones/fermer2.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));

	}

	public void mouseExited(MouseEvent arg0) {
		closeButon.setIcon(new ImageIcon(new ImageIcon("ressources/icones/fermer.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));

	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
