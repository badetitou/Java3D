package fr.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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

	public WindowOuvrir(JTabbedPane tabbedPane, ArrayList<Object> listeOnglets) {
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
		private final JTextArea rName;
		private final JTextArea nFichier;
		private final JButton ouvrir;
		private final JButton annuler;
		private final JList bdd;
		private final JLabel jlb1;
		private final JLabel jlb2;
		private final JFrame windowO;
		private final OutilsBdd obdd;
		private final JTabbedPane tabbedPane;
		private final ArrayList<Object> listeOnglets;

		public PanelOuvrir(JFrame windowO, JTabbedPane tabbedPane, ArrayList<Object> listeOnglets) {
			this.windowO = windowO;
			this.tabbedPane = tabbedPane;
			this.listeOnglets = listeOnglets;
			this.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			//this.setLayout(new GridLayout(5,1));
			this.setPreferredSize(new Dimension(500, 300));
			rName = new JTextArea();
			rName.setPreferredSize(new Dimension(100, 20));
			rName.setEditable(true);
			nFichier = new JTextArea();
			nFichier.setPreferredSize(new Dimension(100, 20));
			nFichier.setEditable(true);


			ouvrir = new JButton("Ouvrir");
			annuler = new JButton("Annuler");

			JPanel p = new JPanel();
			p.setLayout(new FlowLayout(0,70,0));
			p.add(ouvrir);
			p.add(annuler);

			obdd = new OutilsBdd("Database.db");
			String[] data = obdd.getData();
			bdd = new JList(data);
			bdd.setPreferredSize(new Dimension(300,120));
			jlb1 = new JLabel("Recherche par nom : ");
			jlb2 = new JLabel("Nom du fichier: ");

			JPanel p1=new JPanel();
			p1.setLayout(new FlowLayout(0,30,10));
			p1.add(jlb1);
			p1.add(rName);

			JPanel p2=new JPanel();
			p2.setLayout(new FlowLayout(0,50,0));
			p2.add(jlb2);
			p2.add(nFichier);

			JScrollPane scroll = new JScrollPane(bdd);
			scroll.setPreferredSize(new Dimension(200,150));


			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			this.add(jlb1, gbc);

			gbc.gridx = 1;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			this.add(rName, gbc);

			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.gridheight = 4;
			gbc.gridwidth = 4;
			this.add(scroll, gbc);

			gbc.gridx = 1;
			gbc.gridy = 6;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			this.add(jlb2, gbc);

			gbc.gridx = 2;
			gbc.gridy = 6;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			gbc.ipadx=0;
			this.add(nFichier, gbc);

			gbc.gridx = 2;
			gbc.gridy = 8;
			this.add(ouvrir, gbc);

			gbc.gridx = 3;
			gbc.gridy = 8;
			this.add(annuler, gbc);


			/*
			this.add(p1);
			this.add(p2);
			this.add(scroll);
			this.add(rAvancee);
			this.add(p);
			 */
			bdd.addMouseListener(this);
			annuler.addMouseListener(this);
			ouvrir.addMouseListener(this);
		}

		@Override
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
					boolean bool=false;
					for(int i=0;i<listeOnglets.size();i++){
						if(listeOnglets.get(i) instanceof Onglet){
							if(((Onglet)listeOnglets.get(i)).getNomFichier().equals(ouvrir))
								bool=true;
						}
					}
					if(!bool){
						Onglet onglet = new Onglet(new MyDeskTopPane(obdd.getLinkFile(ouvrir)),tabbedPane,ouvrir,obdd.getAuthor(ouvrir),false,listeOnglets);
						//System.out.println(obdd.getLinkFile(ouvrir));
						tabbedPane.addTab(ouvrir, onglet);
						onglet.dessineOnglet();
						tabbedPane.setSelectedComponent(onglet);
						panelInfos = onglet.getPinfos();
						windowO.dispose();
					}
					else{
						windowO.dispose();
						JOptionPane.showMessageDialog(null,"L'objet est déjà ouvert !","Attention", JOptionPane.ERROR_MESSAGE);
					}
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

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	}
}
