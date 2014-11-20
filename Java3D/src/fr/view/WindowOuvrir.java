package fr.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
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
		private final JButton rAvancee;
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
			this.setPreferredSize(new Dimension(500, 300));
			rTag = new JTextArea();
			rTag.setPreferredSize(new Dimension(100, 20));
			rTag.setEditable(true);
			nFichier = new JTextArea();
			nFichier.setPreferredSize(new Dimension(100, 20));
			nFichier.setEditable(true);
			rAvancee = new JButton("Recherche Avancée");
			ouvrir = new JButton("Ouvrir");
			annuler = new JButton("Annuler");
			obdd = new OutilsBdd("Database.db");
			String[] data = obdd.getData();
			//	String[] data = { "green", "red", "orange", "dark blue" };
			/*		for(int i=0; i<data.length; ++i){
				System.out.println(data[i]);
			}
			 */
			bdd = new JList(data);
			jlb1 = new JLabel();
			jlb1.setText("Recherche par mots clés: ");
			jlb2 = new JLabel();
			jlb2.setText("Nom du fichier: ");

			//	this.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			this.add(jlb1);
			gbc.gridx = 1;
			this.add(rTag);
			gbc.gridx = 2;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			this.add(rAvancee);
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			this.add(bdd);
			gbc.gridx = 0;
			gbc.gridy = 4;
			this.add(jlb2);
			gbc.gridx = 1;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			this.add(nFichier);
			gbc.gridx = 0;
			gbc.gridy = 5;
			this.add(ouvrir);
			gbc.gridx = 1;
			this.add(annuler);

			bdd.addMouseListener(this);
			annuler.addMouseListener(this);
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
					panelInfos = onglet.getPinfos();
					tabbedPane.addTab(ouvrir, onglet);
					onglet.dessineOnglet();
					tabbedPane.setSelectedComponent(onglet);
					windowO.dispose();
				}
				else{
					nFichier.setText(null);
				}
			}
			else if(e.getSource().equals(annuler)){
				windowO.dispose();
			}

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
