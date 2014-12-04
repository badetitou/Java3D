package fr.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import fr.model.OutilsBdd;

public class OngletMenu extends JPanel {
	
	private PanelCrit pCrit;
	private PanelListebdd pListebdd;
	private PanelArboPreview pArboPrev;
	
	public OngletMenu(){
		this.setLayout(new GridLayout(1,3));
		this.add(new PanelCrit());
		this.add(new PanelListebdd());
		this.add(new PanelArboPreview());
	}
	
	
	public class PanelCrit extends JPanel{
		
		private JButton valider;
		private JButton sensASC;
		private JButton sensDESC;
		private JLabel jt1;
		private JLabel jt2;
		private JLabel jt3;
		
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
		
		private JList bdd;
		private final OutilsBdd obdd;
		
		public PanelListebdd(){
			obdd = new OutilsBdd("Database.db");
			String[] data = obdd.getData();
			bdd = new JList(data);
			
		}
	}
	
	public class PanelArboPreview extends JPanel{
		
		private JPanel PanelPreview;
		public PanelArboPreview(){
			/* POUR LOIC GGWP */
		}
	}
}
