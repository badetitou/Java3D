package fr.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;

import fr.model.OutilsBdd;

public class WindowOuvrir extends JFrame {

	private PanelInformations panelInfos;

	public PanelInformations getPanelInfos() {
		return panelInfos;
	}

	public WindowOuvrir(JTabbedPane tabbedPane, ArrayList<Onglet> listeOnglets) {
		PanelOuvrir pO = new PanelOuvrir(this, tabbedPane, listeOnglets);
		this.setTitle("Ouvrir");
		this.setSize(500, 300);
		this.setResizable(false);
		this.setAlwaysOnTop(true);

		this.setLocationRelativeTo(null);
		this.setContentPane(pO);
		this.setVisible(true);
	}

	public class PanelOuvrir extends JPanel implements MouseListener{
		private final JTextArea rTag;
		private final JTextArea nFichier;
		private final JLabel rAvancee;
		private final JButton ouvrir;
		private final JButton annuler;
		private final JList bdd;
		private final JLabel jlb1;
		private final JLabel jlb2;
		private final JFrame windowO;
		private final OutilsBdd obdd;
		private final JTabbedPane tabbedPane;
		private final ArrayList<Onglet> listeOnglets;

		public PanelOuvrir(JFrame windowO, JTabbedPane tabbedPane, ArrayList<Onglet> listeOnglets) {
			this.windowO = windowO;
			this.tabbedPane = tabbedPane;
			this.listeOnglets = listeOnglets;
			this.setLayout(new GridLayout(5,1));
			this.setPreferredSize(new Dimension(500, 300));
			rTag = new JTextArea();
			rTag.setPreferredSize(new Dimension(100, 20));
			rTag.setEditable(true);
			nFichier = new JTextArea();
			nFichier.setPreferredSize(new Dimension(100, 20));
			nFichier.setEditable(true);
			rAvancee = new JLabel("Recherche Avanc�e >>");


			ouvrir = new JButton("Ouvrir");
			annuler = new JButton("Annuler");

			JPanel p = new JPanel();
			p.setLayout(new FlowLayout(0,70,0));
			p.add(ouvrir);
			p.add(annuler);

			obdd = new OutilsBdd("Database.db");
			String[] data = obdd.getData();
			//	String[] data = { "green", "red", "orange", "dark blue" };
			/*		for(int i=0; i<data.length; ++i){
				System.out.println(data[i]);
			}
			 */
			JPanel p3 = new JPanel();
			bdd = new JList(data);
			bdd.setPreferredSize(new Dimension(300,120));
			jlb1 = new JLabel("Recherche par mots cl�s: ");
			jlb2 = new JLabel("Nom du fichier: ");

			JPanel p1=new JPanel();
			p1.setLayout(new FlowLayout(0,30,10));
			p1.add(jlb1);
			p1.add(rTag);

			JPanel p2=new JPanel();
			p2.setLayout(new FlowLayout(0,50,0));
			p2.add(jlb2);
			p2.add(nFichier);

			JScrollPane scroll = new JScrollPane(bdd);
			//scroll.setPreferredSize(new Dimension(Window.outil.getScreenSize().width-(Window.outil.getScreenSize().width/3),Window.outil.getScreenSize().height/6));

			this.add(p1);
			this.add(p2);
			this.add(scroll);
			this.add(rAvancee);
			this.add(p);

			bdd.addMouseListener(this);
			annuler.addMouseListener(this);
			ouvrir.addMouseListener(this);
		}

		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				int index = bdd.locationToIndex(e.getPoint());
				ListModel dlm = bdd.getModel();
				Object item = dlm.getElementAt(index);;
				bdd.ensureIndexIsVisible(index);
				nFichier.setText(null);
				nFichier.setText((String) item);
			}
			else if(e.getSource().equals(ouvrir)){
				String ouvrir = nFichier.getText();
				if(obdd.estPresent(ouvrir)){
					Onglet onglet = new Onglet(new MyDeskTopPane(obdd.getLinkFile(ouvrir)),tabbedPane,ouvrir,obdd.getAuthor(ouvrir),false,listeOnglets);
					//System.out.println(obdd.getLinkFile(ouvrir));
					tabbedPane.addTab(ouvrir, onglet);
					onglet.dessineOnglet();
					tabbedPane.setSelectedComponent(onglet);
					panelInfos = onglet.getPinfos();
					windowO.dispose();
				}
				else{
					nFichier.setText(null);
				}
			}
			else if(e.getSource().equals(annuler)){
				windowO.dispose();
			}
			//windowO.dispose();
		}

		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	}
}
