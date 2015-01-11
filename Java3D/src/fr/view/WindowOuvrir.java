package fr.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.BoxLayout;
import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import fr.model.OutilsBdd;

public class WindowOuvrir extends JFrame {

	private PanelInformations panelInfos;

	public PanelInformations getPanelInfos() {
		return panelInfos;
	}

	public WindowOuvrir(JTabbedPane tabbedPane, ArrayList<Object> listeOnglets) {
		PanelOuvrir pO = new PanelOuvrir(this, tabbedPane, listeOnglets);
		this.setTitle("Ouvrir");
		this.setSize(500, 330);
		this.setResizable(false);
		this.setAlwaysOnTop(true);

		this.setLocationRelativeTo(null);
		this.setContentPane(pO);
		this.setVisible(true);
	}

	public class PanelOuvrir extends JPanel implements MouseListener, KeyListener{
		private final JTextField rName;
		private final JTextField nFichier;
		private final JButton ouvrir;
		private final JButton annuler;
		private String filtre;
		private JTable bdd;
		private final JLabel jlb1;
		private final JLabel jlb2;
		private final JLabel error;
		private final JFrame windowO;
		private OutilsBdd obdd;
		private Object[][] data;
		private RowSorter<MyTableModel> sorter;
		private RowFilter<MyTableModel, Object> rf1;
		RowFilter<MyTableModel, Object> compoundRowFilter = null;
		List<RowFilter<MyTableModel,Object>> filters;
		private MyTableModel mtm;
		private final JTabbedPane tabbedPane;
		private final ArrayList<Object> listeOnglets;
		private JScrollPane scroll;
		private JPanel p;
		private final JPanel pan;
		private final JPanel pan2;
		private final JPanel perror;

		public PanelOuvrir(JFrame windowO, JTabbedPane tabbedPane, ArrayList<Object> listeOnglets) {
			this.windowO = windowO;
			this.tabbedPane = tabbedPane;
			this.listeOnglets = listeOnglets;
			//this.setPreferredSize(new Dimension(500, 300));
			rName = new JTextField();
			rName.setPreferredSize(new Dimension(100, 20));
			rName.setEditable(true);
			nFichier = new JTextField();
			nFichier.setPreferredSize(new Dimension(100, 20));
			nFichier.setEditable(true);
			ouvrir = new JButton("Ouvrir");
			annuler = new JButton("Annuler");
			filtre = new String("");
			jlb1 = new JLabel("Recherche par nom : ");
			jlb2 = new JLabel("Nom du fichier: ");
			error = new JLabel ("Erreur : l'objet n'existe pas");
			error.setForeground(new Color(255, 0, 0));
			error.setVisible(false);
			this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
			pan=new JPanel(new FlowLayout());
			pan.add(jlb1);
			pan.add(rName);
			this.add(pan);
			this.initialise();
			perror=new JPanel();
			perror.setPreferredSize(new Dimension(100, 20));
			perror.add(error);
			this.add(perror);
			pan2=new JPanel(new FlowLayout());
			pan2.add(jlb2);
			pan2.add(nFichier);
			pan2.add(ouvrir);
			pan2.add(annuler);
			this.add(pan2);

			nFichier.addKeyListener(this);
			rName.addKeyListener(this);
			bdd.addMouseListener(this);
			annuler.addMouseListener(this);
			ouvrir.addMouseListener(this);
		}
		public void initialise(){
			obdd = new OutilsBdd("Database.db");
			data = obdd.getAllData();
			String title[] = { "Nom", "Auteur"};
			this.mtm = new MyTableModel(data, title);
			this.sorter = new TableRowSorter<>(mtm);
			this.rf1 = null;
			this.bdd = new JTable(mtm);
			this.filters = new ArrayList<RowFilter<MyTableModel,Object>>();
			bdd.setRowSorter(sorter);
			try {
				if (filtre != "" || filtre != null){
					rf1 = RowFilter.regexFilter("(?i)" +filtre, 0);}
				filters.add(rf1);
				compoundRowFilter = RowFilter.andFilter(filters); // you may also choose the OR filter
			} catch (PatternSyntaxException pse) {
				return;
			}
			((DefaultRowSorter<MyTableModel, Integer>) sorter).setRowFilter(compoundRowFilter);
			this.scroll = new JScrollPane(bdd);
			this.scroll.setPreferredSize(new Dimension(400,180));
			p=new JPanel();
			p.add(scroll);
			this.add(p);
			bdd.getTableHeader().setReorderingAllowed(false);
			bdd.getTableHeader().setResizingAllowed(false);
			bdd.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			bdd.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					//N? de la ligne s?l?ctionn?e
					int row = bdd.getSelectedRow();
					//N? de ligne du tableau tri?
					int sortedRow = bdd.convertRowIndexToModel(row);
					Object row1 = bdd.getModel().getValueAt(sortedRow, 0);
					Object row2 = bdd.getModel().getValueAt(sortedRow, 1);
				}
			});
			bdd.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 1) {
						JTable target = (JTable)e.getSource();
						int row = target.getSelectedRow();
						int column = target.getSelectedColumn();
						if(column == 0 || column == 1){
							nFichier.setText((String) bdd.getValueAt(row, 0));
						}
					}
				}
			});
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(ouvrir)){
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
						JOptionPane.showMessageDialog(null,"L'objet est d�j� ouvert !","Attention", JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					nFichier.setText(null);
					error.setVisible(true);
					this.revalidate();
					this.repaint();
				}
			}
			else if(e.getSource().equals(annuler)){
				windowO.dispose();
			}

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
		@Override
		public void keyPressed(KeyEvent e) {

		}
		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getSource().equals(nFichier)){
				error.setVisible(false);
			}
			else if(e.getSource().equals(rName)){
				filtre = rName.getText();
				this.remove(p);
				this.remove(perror);
				this.remove(pan2);
				this.initialise();
				this.add(perror);
				this.add(pan2);
				this.revalidate();
				this.repaint();
			}
		}
		@Override
		public void keyTyped(KeyEvent e) {
		}
	}
	public class MyTableModel extends DefaultTableModel {

		MyTableModel(Object[][] rows, String[] headers) {
			super(rows, headers);
		}

		@Override
		public Class getColumnClass(int column) {
			Class returnValue;
			if ((column >= 0) && (column < getColumnCount())) {
				returnValue = getValueAt(0, column).getClass();
			} else {
				returnValue = Object.class;
			}
			return returnValue;
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex){
			return false;
		}
	}
}
