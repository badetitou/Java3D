package fr.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.BorderFactory;
import javax.swing.DefaultRowSorter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import fr.model.OutilsBdd;

public class OngletMenu extends JPanel implements MouseListener{


	private final JLabel closeButon;
	private final JLabel ic;
	private final JPanel p1;
	private final JTabbedPane tabbedPane;
	private final ArrayList<Object>listeOnglets;
	private final PanelListebdd plbdd;

	public OngletMenu(JTabbedPane tabbedPane,ArrayList<Object>listeOnglets){
		this.listeOnglets=listeOnglets;
		this.tabbedPane=tabbedPane;
		this.setLayout(new GridLayout(1,3));
		this.add(new PanelCrit());
		this.plbdd = new PanelListebdd();
		this.add(plbdd);
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


	public class PanelCrit extends JPanel implements ActionListener{

		private final JButton valider;
		private final JLabel sensASC;
		private final JLabel sensDESC;
		private final JLabel jt1;
		private final JLabel jt2;
		private final JLabel jt3;
		private final JTextArea jta1;
		private final JTextArea jta2;


		public PanelCrit(){
			this.setLayout(new FlowLayout());
			this.valider = new JButton("Valider");
			this.jt1 = new JLabel("Recherche Avanc�e: ");
			this.jt2 = new JLabel("Crit�re: ");
			this.jt3 = new JLabel("Sens: ");
			this.sensASC = new JLabel(new ImageIcon(new ImageIcon("ressources/icones/flecheHaut.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
			this.sensDESC = new JLabel(new ImageIcon(new ImageIcon("ressources/icones/flecheBas.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
			/*this.add(jt1);
			this.add(jt2);
			this.add(jt3);
			this.add(sensASC);
			this.add(sensDESC);
			*/
			this.jta1 = new JTextArea("Filtre1");
			this.jta1.setPreferredSize(new Dimension(40,20));
			this.add(jta1);
			this.jta2 = new JTextArea("Filtre2");
			this.jta2.setPreferredSize(new Dimension(40,20));
			this.add(jta2);
			this.add(valider);
			this.setBorder(BorderFactory.createLoweredBevelBorder());
			this.valider.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(valider)){
				plbdd.filtre1 = jta1.getText();
				plbdd.filtre2 = jta2.getText();
				plbdd.removeAll();
				plbdd.initialise();
				plbdd.revalidate();
				plbdd.repaint();
			}
		}


	}

	public class PanelListebdd extends JPanel{

		private JTable bdd;
		private final OutilsBdd obdd;
		private Object[][] data;
		private String filtre1;
		private String filtre2;
		private RowSorter<MyTableModel> sorter;
		private RowFilter<MyTableModel, Object> rf1;
		private RowFilter<MyTableModel, Object> rf2;
		RowFilter<MyTableModel, Object> compoundRowFilter = null;
		List<RowFilter<MyTableModel,Object>> filters;
		private MyTableModel mtm;

		public PanelListebdd(){
			obdd = new OutilsBdd("Database.db");
			data = obdd.getAllData();
			String title[] = { "Nom", "Auteur", "Derni�re Modif", "Nb ouverture", "Nb images"};
			this.mtm = new MyTableModel(data, title);
			this.sorter = new TableRowSorter<>(mtm);
			this.rf1 = null;
			this.rf2 = null;
			this.filtre1 = "";
			this.filtre2 = "";
			this.initialise();
			this.setBorder(BorderFactory.createLoweredBevelBorder());

		}
		
		public void initialise(){
			
			this.bdd = new JTable(mtm);
			this.filters = new ArrayList<RowFilter<MyTableModel,Object>>();
			bdd.setRowSorter(sorter);
			try {
				if (filtre1 != "" || filtre1 != null){
			    rf1 = RowFilter.regexFilter("(?i)" +filtre1, 0);}
				if (filtre2 != "" || filtre2 != null){
			    rf2 = RowFilter.regexFilter("(?i)" +filtre2, 1);}
			    filters.add(rf1);
			    filters.add(rf2);
			    compoundRowFilter = RowFilter.andFilter(filters); // you may also choose the OR filter
		    } catch (PatternSyntaxException pse) {
		        return;
		    }
			((DefaultRowSorter<MyTableModel, Integer>) sorter).setRowFilter(compoundRowFilter);

			add(new JScrollPane(bdd), BorderLayout.CENTER );
			bdd.getTableHeader().setReorderingAllowed(false);
			bdd.getTableHeader().setResizingAllowed(false);
			bdd.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        bdd.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	                //N� de la ligne s�l�ctionn�e
	                int row = bdd.getSelectedRow();
	                //N� de ligne du tableau tri�
	                int sortedRow = bdd.convertRowIndexToModel(row);
	                Object row1 = bdd.getModel().getValueAt(sortedRow, 0);
	                Object row2 = bdd.getModel().getValueAt(sortedRow, 1);
	                Object row3 = bdd.getModel().getValueAt(sortedRow, 2);
	                Object row4 = bdd.getModel().getValueAt(sortedRow, 3);
	                Object row5 = bdd.getModel().getValueAt(sortedRow, 4);
	            }
	        });
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
	    
	    public boolean isCellEditable(int rowIndex, int columnIndex){
	    	return false;
	    }
	}
}