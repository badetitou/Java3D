package fr.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.BorderFactory;
import javax.swing.DefaultRowSorter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import fr.model.OutilsBdd;

public class OngletMenu extends JPanel{


	//private final JLabel closeButon;
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
		//closeButon = new JLabel(new ImageIcon(new ImageIcon("ressources/icones/fermer.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		//closeButon.addMouseListener(this);
		ic = new JLabel(new ImageIcon(new ImageIcon("ressources/icones/iconeMenu.png").getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH)));
		p1=new JPanel();
	}

	public void dessineOnglet(){
		p1.setOpaque(false);
		JLabel lbTitle=new JLabel("Menu");
		p1.add(ic);
		p1.add(lbTitle);
		//p1.add(closeButon);
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


	public class PanelCrit extends JPanel implements ActionListener, ItemListener{

		private final JButton valider;
		private final JTextField jtfNom;
		private final JTextField jtfAuteur;
		private final JTextField jtfModif;
		private final JTextField jtfOuverture;
		private final JTextField jtfImages;
		private JCheckBox jcbModif;
		private JCheckBox jcbOuverture;
		private JCheckBox jcbImages;
		private final JLabel jlbNom;
		private final JLabel jlbAuteur;
		private String modifCheck;
		private String ouvertureCheck;
		private String imagesCheck;

		public PanelCrit(){
			this.setLayout(new FlowLayout());
			this.valider = new JButton("Valider");
			this.jcbModif = new JCheckBox("Date dernière modif");
			this.jcbOuverture = new JCheckBox("Nb ouverture");
			this.jcbImages = new JCheckBox("Nb images");
			this.jlbNom = new JLabel("Nom");
			this.add(jlbNom);
			this.jtfNom = new JTextField("");
			this.jtfNom.setPreferredSize(new Dimension(50,20));
			this.add(jtfNom);
			this.jlbAuteur = new JLabel("Auteur");
			this.add(jlbAuteur);
			this.jtfAuteur = new JTextField("");
			this.jtfAuteur.setPreferredSize(new Dimension(50,20));
			this.add(jtfAuteur);
			this.add(jcbModif);
			this.jtfModif = new JTextField("");
			this.jtfModif.setPreferredSize(new Dimension(50,20));
			this.add(jtfModif);
			this.add(jcbOuverture);
			this.jtfOuverture = new JTextField("");
			this.jtfOuverture.setPreferredSize(new Dimension(50,20));
			this.add(jtfOuverture);
			this.add(jcbImages);
			this.jtfImages = new JTextField("");
			this.jtfImages.setPreferredSize(new Dimension(50,20));
			this.add(jtfImages);
			this.modifCheck = "unchecked";
			this.ouvertureCheck = "unchecked";
			this.imagesCheck = "unchecked";
			this.add(valider);
			this.setBorder(BorderFactory.createLoweredBevelBorder());
			this.jcbModif.addItemListener(this);
			this.jcbOuverture.addItemListener(this);
			this.jcbImages.addItemListener(this);
			this.valider.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(valider)){
				if(modifCheck == "checked" && ouvertureCheck == "checked" && imagesCheck == "checked"){
					plbdd.filtreNom = jtfNom.getText();
					plbdd.filtreAuteur = jtfAuteur.getText();
					plbdd.filtreModif = jtfModif.getText();
					plbdd.filtreOuverture = jtfOuverture.getText();
					plbdd.filtreImages = jtfImages.getText();
					plbdd.removeAll();
					plbdd.initialiseCombo(true, true, true);
					plbdd.revalidate();
					plbdd.repaint();
				}
				else if(modifCheck == "unchecked" && ouvertureCheck == "unchecked" && imagesCheck == "unchecked"){
					plbdd.filtreNom = jtfNom.getText();
					plbdd.filtreAuteur = jtfAuteur.getText();
					plbdd.removeAll();
					plbdd.initialiseCombo(false, false, false);
					plbdd.revalidate();
					plbdd.repaint();
				}
				else if(modifCheck == "checked" && ouvertureCheck == "unchecked" && imagesCheck == "unchecked"){
					plbdd.filtreNom = jtfNom.getText();
					plbdd.filtreAuteur = jtfAuteur.getText();
					plbdd.filtreModif = jtfModif.getText();
					plbdd.removeAll();
					plbdd.initialiseCombo(true, false, false);
					plbdd.revalidate();
					plbdd.repaint();
				}
				else if(modifCheck == "checked" && ouvertureCheck == "checked" && imagesCheck == "unchecked"){
					plbdd.filtreNom = jtfNom.getText();
					plbdd.filtreAuteur = jtfAuteur.getText();
					plbdd.filtreModif = jtfModif.getText();
					plbdd.filtreOuverture = jtfOuverture.getText();
					plbdd.removeAll();
					plbdd.initialiseCombo(true, true, false);
					plbdd.revalidate();
					plbdd.repaint();
				}
				else if(modifCheck == "checked" && ouvertureCheck == "unchecked" && imagesCheck == "checked"){
					plbdd.filtreNom = jtfNom.getText();
					plbdd.filtreAuteur = jtfAuteur.getText();
					plbdd.filtreModif = jtfModif.getText();
					plbdd.filtreImages = jtfImages.getText();
					plbdd.removeAll();
					plbdd.initialiseCombo(true, false, true);
					plbdd.revalidate();
					plbdd.repaint();
				}
				else if(modifCheck == "unchecked" && ouvertureCheck == "checked" && imagesCheck == "unchecked"){
					plbdd.filtreNom = jtfNom.getText();
					plbdd.filtreAuteur = jtfAuteur.getText();
					plbdd.filtreOuverture = jtfOuverture.getText();
					plbdd.removeAll();
					plbdd.initialiseCombo(false, true, false);
					plbdd.revalidate();
					plbdd.repaint();
				}
				else if(modifCheck == "unchecked" && ouvertureCheck == "checked" && imagesCheck == "checked"){
					plbdd.filtreNom = jtfNom.getText();
					plbdd.filtreAuteur = jtfAuteur.getText();
					plbdd.filtreOuverture = jtfOuverture.getText();
					plbdd.filtreImages = jtfImages.getText();
					plbdd.removeAll();
					plbdd.initialiseCombo(false, true, true);
					plbdd.revalidate();
					plbdd.repaint();
				}
				else if(modifCheck == "unchecked" && ouvertureCheck == "unchecked" && imagesCheck == "checked"){
					plbdd.filtreNom = jtfNom.getText();
					plbdd.filtreAuteur = jtfAuteur.getText();
					plbdd.filtreImages = jtfImages.getText();
					plbdd.removeAll();
					plbdd.initialiseCombo(false, false, true);
					plbdd.revalidate();
					plbdd.repaint();
				}
			}
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			JCheckBox cb = (JCheckBox)e.getItem();
			if(e.getStateChange() == ItemEvent.SELECTED){
			//	System.out.println("Item: " + cb.getText());
				if(jcbModif.isSelected() && jcbOuverture.isSelected() && jcbImages.isSelected()){
					modifCheck = "checked";
					ouvertureCheck = "checked";
					imagesCheck = "checked";
				}
				else if(jcbModif.isSelected() && jcbOuverture.isSelected()){
					modifCheck = "checked";
					ouvertureCheck = "checked";
				}
				else if(jcbModif.isSelected() && jcbImages.isSelected()){
					modifCheck = "checked";
					imagesCheck = "checked";
				}
				else if(jcbOuverture.isSelected() && jcbImages.isSelected()){
					ouvertureCheck = "checked";
					imagesCheck = "checked";
				}
				else if(jcbModif.isSelected()){
					modifCheck = "checked";
				}
				else if(jcbOuverture.isSelected()){
					ouvertureCheck = "checked";
				}
				else if(jcbImages.isSelected()){
					imagesCheck = "checked";
				}
			}
			else{
			//	System.out.println("Item deselect: " + cb.getText());
				if(!jcbModif.isSelected() && !jcbOuverture.isSelected() && !jcbImages.isSelected()){
					modifCheck = "unchecked";
					ouvertureCheck = "unchecked";
					imagesCheck = "unchecked";
				}
				else if(!jcbModif.isSelected() && !jcbOuverture.isSelected()){
					modifCheck = "unchecked";
					ouvertureCheck = "unchecked";
				}
				else if(!jcbModif.isSelected() && !jcbImages.isSelected()){
					modifCheck = "unchecked";
					imagesCheck = "unchecked";
				}
				else if(!jcbOuverture.isSelected() && !jcbImages.isSelected()){
					ouvertureCheck = "unchecked";
					imagesCheck = "unchecked";
				}
				else if(!jcbModif.isSelected()){
					modifCheck = "unchecked";
				}
				else if(!jcbOuverture.isSelected()){
					ouvertureCheck = "unchecked";
				}
				else if(!jcbImages.isSelected()){
					imagesCheck = "unchecked";
				}
			}
		}

	}

	public class PanelListebdd extends JPanel{

		private JTable bdd;
		private OutilsBdd obdd;
		private Object[][] data;
		private String filtreNom;
		private String filtreAuteur;
		private String filtreModif;
		private String filtreOuverture;
		private String filtreImages;
		private RowSorter<MyTableModel> sorter;
		private RowFilter<MyTableModel, Object> rf0;
		private RowFilter<MyTableModel, Object> rf1;
		private RowFilter<MyTableModel, Object> rf2;
		private RowFilter<MyTableModel, Object> rf3;
		private RowFilter<MyTableModel, Object> rf4;
		RowFilter<MyTableModel, Object> compoundRowFilter = null;
		List<RowFilter<MyTableModel,Object>> filters;
		private MyTableModel mtm;
		private boolean b1;
		private boolean b2;
		private boolean b3;

		public PanelListebdd(){
			this.filtreNom = "";
			this.filtreAuteur = "";
			this.filtreModif = "";
			this.filtreOuverture = "";
			this.filtreImages = "";
			this.b1 = false;
			this.b2 = false;
			this.b3 = false;
			this.initialiseCombo(b1, b2, b3);
			this.setBorder(BorderFactory.createLoweredBevelBorder());

		}
		public void initialiseCombo(boolean b1, boolean b2, boolean b3){
			obdd = new OutilsBdd("Database.db");
			data = obdd.getComboData(b1, b2, b3);
			if(b1 == true && b2 == true && b3 == true){
				String title[] = { "Nom", "Auteur", "Derniere Modif", "Nb ouverture", "Nb images"};
				this.mtm = new MyTableModel(data, title);
				this.sorter = new TableRowSorter<>(mtm);
				this.rf0 = null;
				this.rf1 = null;
				this.rf2 = null;
				this.rf3 = null;
				this.rf4 = null;
				this.bdd = new JTable(mtm);
				this.filters = new ArrayList<RowFilter<MyTableModel,Object>>();
				bdd.setRowSorter(sorter);
				try {
					if (filtreNom != "" || filtreNom != null){
						rf0 = RowFilter.regexFilter("(?i)" +filtreNom, 0);}
					if (filtreAuteur != "" || filtreAuteur != null){
						rf1 = RowFilter.regexFilter("(?i)" +filtreAuteur, 1);}
					if (filtreModif != "" || filtreModif != null){
						rf2 = RowFilter.regexFilter("(?i)" +filtreModif, 2);}
					if (filtreOuverture != "" || filtreOuverture != null){
						rf3 = RowFilter.regexFilter("(?i)" +filtreOuverture, 3);}
					if (filtreImages != "" || filtreImages != null){
						rf4 = RowFilter.regexFilter("(?i)" +filtreImages, 4);}
					filters.add(rf0);
					filters.add(rf1);
					filters.add(rf2);
					filters.add(rf3);
					filters.add(rf4);
					compoundRowFilter = RowFilter.andFilter(filters); // you may also choose the OR filter
				} catch (PatternSyntaxException pse) {
					return;
				}
			}
			else if(b1 == false && b2 == false && b3 == false){
				String title[] = { "Nom", "Auteur"};
				this.mtm = new MyTableModel(data, title);
				this.sorter = new TableRowSorter<>(mtm);
				this.rf0 = null;
				this.rf1 = null;
				this.rf2 = null;
				this.rf3 = null;
				this.rf4 = null;
				this.bdd = new JTable(mtm);
				this.filters = new ArrayList<RowFilter<MyTableModel,Object>>();
				bdd.setRowSorter(sorter);
				try {
					if (filtreNom != "" || filtreNom != null){
						rf0 = RowFilter.regexFilter("(?i)" +filtreNom, 0);}
					if (filtreAuteur != "" || filtreAuteur != null){
						rf1 = RowFilter.regexFilter("(?i)" +filtreAuteur, 1);}
					filters.add(rf0);
					filters.add(rf1);
					compoundRowFilter = RowFilter.andFilter(filters); // you may also choose the OR filter
				} catch (PatternSyntaxException pse) {
					return;
				}
			}
			else if(b1 == true && b2 == false && b3 == false){
				String title[] = { "Nom", "Auteur", "Derniere Modif"};
				this.mtm = new MyTableModel(data, title);
				this.sorter = new TableRowSorter<>(mtm);
				this.rf0 = null;
				this.rf1 = null;
				this.rf2 = null;
				this.rf3 = null;
				this.rf4 = null;
				this.bdd = new JTable(mtm);
				this.filters = new ArrayList<RowFilter<MyTableModel,Object>>();
				bdd.setRowSorter(sorter);
				try {
					if (filtreNom != "" || filtreNom != null){
						rf0 = RowFilter.regexFilter("(?i)" +filtreNom, 0);}
					if (filtreAuteur != "" || filtreAuteur != null){
						rf1 = RowFilter.regexFilter("(?i)" +filtreAuteur, 1);}
					if (filtreModif != "" || filtreModif != null){
						rf2 = RowFilter.regexFilter("(?i)" +filtreModif, 2);}
					filters.add(rf0);
					filters.add(rf1);
					filters.add(rf2);
					compoundRowFilter = RowFilter.andFilter(filters); // you may also choose the OR filter
				} catch (PatternSyntaxException pse) {
					return;
				}
			}
			else if(b1 == true && b2 == true && b3 == false){
				String title[] = { "Nom", "Auteur", "Derniere Modif", "Nb ouverture"};
				this.mtm = new MyTableModel(data, title);
				this.sorter = new TableRowSorter<>(mtm);
				this.rf0 = null;
				this.rf1 = null;
				this.rf2 = null;
				this.rf3 = null;
				this.rf4 = null;
				this.bdd = new JTable(mtm);
				this.filters = new ArrayList<RowFilter<MyTableModel,Object>>();
				bdd.setRowSorter(sorter);
				try {
					if (filtreNom != "" || filtreNom != null){
						rf0 = RowFilter.regexFilter("(?i)" +filtreNom, 0);}
					if (filtreAuteur != "" || filtreAuteur != null){
						rf1 = RowFilter.regexFilter("(?i)" +filtreAuteur, 1);}
					if (filtreModif != "" || filtreModif != null){
						rf2 = RowFilter.regexFilter("(?i)" +filtreModif, 2);}
					if (filtreOuverture != "" || filtreOuverture != null){
						rf3 = RowFilter.regexFilter("(?i)" +filtreOuverture, 3);}
					filters.add(rf0);
					filters.add(rf1);
					filters.add(rf2);
					filters.add(rf3);
					compoundRowFilter = RowFilter.andFilter(filters); // you may also choose the OR filter
				} catch (PatternSyntaxException pse) {
					return;
				}
			}
			else if(b1 == true && b2 == false && b3 == true){
				String title[] = { "Nom", "Auteur", "Derniere Modif", "Nb images"};
				this.mtm = new MyTableModel(data, title);
				this.sorter = new TableRowSorter<>(mtm);
				this.rf0 = null;
				this.rf1 = null;
				this.rf2 = null;
				this.rf3 = null;
				this.rf4 = null;
				this.bdd = new JTable(mtm);
				this.filters = new ArrayList<RowFilter<MyTableModel,Object>>();
				bdd.setRowSorter(sorter);
				try {
					if (filtreNom != "" || filtreNom != null){
						rf0 = RowFilter.regexFilter("(?i)" +filtreNom, 0);}
					if (filtreAuteur != "" || filtreAuteur != null){
						rf1 = RowFilter.regexFilter("(?i)" +filtreAuteur, 1);}
					if (filtreModif != "" || filtreModif != null){
						rf2 = RowFilter.regexFilter("(?i)" +filtreModif, 2);}
					if (filtreImages != "" || filtreImages != null){
						rf3 = RowFilter.regexFilter("(?i)" +filtreImages, 3);}
					filters.add(rf0);
					filters.add(rf1);
					filters.add(rf2);
					filters.add(rf3);
					compoundRowFilter = RowFilter.andFilter(filters); // you may also choose the OR filter
				} catch (PatternSyntaxException pse) {
					return;
				}
			}
			else if(b1 == false && b2 == true && b3 == false){
				String title[] = { "Nom", "Auteur", "Nb ouverture"};
				this.mtm = new MyTableModel(data, title);
				this.sorter = new TableRowSorter<>(mtm);
				this.rf0 = null;
				this.rf1 = null;
				this.rf2 = null;
				this.rf3 = null;
				this.rf4 = null;
				this.bdd = new JTable(mtm);
				this.filters = new ArrayList<RowFilter<MyTableModel,Object>>();
				bdd.setRowSorter(sorter);
				try {
					if (filtreNom != "" || filtreNom != null){
						rf0 = RowFilter.regexFilter("(?i)" +filtreNom, 0);}
					if (filtreAuteur != "" || filtreAuteur != null){
						rf1 = RowFilter.regexFilter("(?i)" +filtreAuteur, 1);}
					if (filtreOuverture != "" || filtreOuverture != null){
						rf2 = RowFilter.regexFilter("(?i)" +filtreOuverture, 2);}
					filters.add(rf0);
					filters.add(rf1);
					filters.add(rf2);
					compoundRowFilter = RowFilter.andFilter(filters); // you may also choose the OR filter
				} catch (PatternSyntaxException pse) {
					return;
				}
			}
			else if(b1 == false && b2 == true && b3 == true){
				String title[] = { "Nom", "Auteur",  "Nb ouverture", "Nb images"};
				this.mtm = new MyTableModel(data, title);
				this.sorter = new TableRowSorter<>(mtm);
				this.rf0 = null;
				this.rf1 = null;
				this.rf2 = null;
				this.rf3 = null;
				this.rf4 = null;
				this.bdd = new JTable(mtm);
				this.filters = new ArrayList<RowFilter<MyTableModel,Object>>();
				bdd.setRowSorter(sorter);
				try {
					if (filtreNom != "" || filtreNom != null){
						rf0 = RowFilter.regexFilter("(?i)" +filtreNom, 0);}
					if (filtreAuteur != "" || filtreAuteur != null){
						rf1 = RowFilter.regexFilter("(?i)" +filtreAuteur, 1);}
					if (filtreOuverture != "" || filtreOuverture != null){
						rf2 = RowFilter.regexFilter("(?i)" +filtreOuverture, 2);}
					if (filtreImages != "" || filtreImages != null){
						rf3 = RowFilter.regexFilter("(?i)" +filtreImages, 3);}
					filters.add(rf0);
					filters.add(rf1);
					filters.add(rf2);
					filters.add(rf3);
					compoundRowFilter = RowFilter.andFilter(filters); // you may also choose the OR filter
				} catch (PatternSyntaxException pse) {
					return;
				}
			}
			else if(b1 == false && b2 == false && b3 == true){
				String title[] = { "Nom", "Auteur", "Nb images"};
				this.mtm = new MyTableModel(data, title);
				this.sorter = new TableRowSorter<>(mtm);
				this.rf0 = null;
				this.rf1 = null;
				this.rf2 = null;
				this.rf3 = null;
				this.rf4 = null;
				this.bdd = new JTable(mtm);
				this.filters = new ArrayList<RowFilter<MyTableModel,Object>>();
				bdd.setRowSorter(sorter);
				try {
					if (filtreNom != "" || filtreNom != null){
						rf0 = RowFilter.regexFilter("(?i)" +filtreNom, 0);}
					if (filtreAuteur != "" || filtreAuteur != null){
						rf1 = RowFilter.regexFilter("(?i)" +filtreAuteur, 1);}
					if (filtreImages != "" || filtreImages != null){
						rf2 = RowFilter.regexFilter("(?i)" +filtreImages, 2);}
					filters.add(rf0);
					filters.add(rf1);
					filters.add(rf2);
					compoundRowFilter = RowFilter.andFilter(filters); // you may also choose the OR filter
				} catch (PatternSyntaxException pse) {
					return;
				}
			}
			((DefaultRowSorter<MyTableModel, Integer>) sorter).setRowFilter(compoundRowFilter);
			add(new JScrollPane(bdd), BorderLayout.CENTER );
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