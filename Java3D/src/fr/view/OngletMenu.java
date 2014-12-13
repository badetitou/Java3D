package fr.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableModel;

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
		this.add(new PanelListebdd(null));
		this.add(new PanelArboPreview());
		listeOnglets.add(this);
		closeButon = new JLabel(new ImageIcon(new ImageIcon("ressources/icones/fermer.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		closeButon.addMouseListener(this);
		ic = new JLabel(new ImageIcon(new ImageIcon("ressources/icones/iconeMenu.png").getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH)));
		p1=new JPanel();
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
		private final JLabel sensASC;
		private final JLabel sensDESC;
		private final JLabel jt1;
		private final JLabel jt2;
		private final JLabel jt3;

		public PanelCrit(){
			this.setLayout(new FlowLayout());
			this.valider = new JButton("Valider");
			this.jt1 = new JLabel("Recherche Avancée: ");
			this.jt2 = new JLabel("Critère: ");
			this.jt3 = new JLabel("Sens: ");
			this.sensASC = new JLabel(new ImageIcon(new ImageIcon("ressources/icones/flecheHaut.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
			this.sensDESC = new JLabel(new ImageIcon(new ImageIcon("ressources/icones/flecheBas.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
			this.add(jt1);
			this.add(jt2);
			this.add(jt3);
			this.add(sensASC);
			this.add(sensDESC);
			this.add(valider);
			this.setBorder(BorderFactory.createLoweredBevelBorder());

		}

	}

	public class PanelListebdd extends JPanel{

		private final JTable bdd;
		private final OutilsBdd obdd;

		public PanelListebdd(TableModel model){
			obdd = new OutilsBdd("Database.db");
			bdd = obdd.getDataAll();
			add(new JScrollPane(bdd), BorderLayout.CENTER );
			this.setBorder(BorderFactory.createLoweredBevelBorder());

		}
	}

	public class PanelArboPreview extends JPanel{

		private final JPanel panelTree;
		private final JPanel panelImage;
		private final JTree tree;
		public PanelArboPreview(){
			File repertoire = new File("fichiers"+File.separator);
			File[] listefichiers;
			listefichiers=repertoire.listFiles();

			tree=new JTree(listefichiers);

			this.setLayout(new BorderLayout());
			this.setBorder(BorderFactory.createLoweredBevelBorder());
			panelTree=new JPanel();

			panelTree.add(tree);
			panelImage=new JPanel();
			JPanel panelPreview=new JPanel();
			JLabel l=new JLabel();
			String path="ressources/image/800x400.png";
			l.setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(Window.outil.getScreenSize().width/9, Window.outil.getScreenSize().width/9, Image.SCALE_SMOOTH)));
			panelPreview.add(l);
			panelPreview.setBorder(BorderFactory.createLoweredBevelBorder());
			panelImage.add(panelPreview);
			panelTree.setBorder(BorderFactory.createLoweredBevelBorder());

			this.add(panelTree,BorderLayout.CENTER);
			this.add(panelImage,BorderLayout.SOUTH);
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
