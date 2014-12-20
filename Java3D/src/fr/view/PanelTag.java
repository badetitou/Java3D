package fr.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import fr.model.OutilsBdd;

public class PanelTag extends JPanel implements KeyListener, ActionListener{
	private final OutilsBdd obdd;
	private final JList liste;
	private final DefaultListModel listModel;
	private final JTextField jt;
	private final JScrollPane scroll;
	private final JLabel jl;
	private final JPanel panel;
	private final JButton valider;
	public PanelTag(String nomFichier){
		this.setLayout(new FlowLayout(0,30,20));
		panel=new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		this.setBackground(new Color(215,215,215));
		obdd=new OutilsBdd("Database.db");
		listModel=new DefaultListModel();
		//listModel.addElement(nomFichier);
		liste=new JList(listModel);
		jl=new JLabel("Saisissez un mot-clé : ");
		panel.setBackground(new Color(215,215,215));
		liste.setBorder(BorderFactory.createLoweredBevelBorder());
		scroll=new JScrollPane(liste);
		scroll.setPreferredSize(new Dimension(Window.outil.getScreenSize().width/3,Window.outil.getScreenSize().height/6));
		jt=new JTextField();
		jt.setPreferredSize(new Dimension(200,70));
		jt.addKeyListener(this);
		valider=new JButton("Valider");
		valider.addActionListener(this);
		valider.setEnabled(false);
		panel.add(jl);
		panel.add(jt);
		panel.add(valider);
		this.add(panel);
		this.add(scroll);

	}

	public void ajoutElementTop(){
		DefaultListModel mod=new DefaultListModel();
		int k=listModel.size();
		for (int i=0;i<k;i++){
			mod.addElement(listModel.getElementAt(i));
		}
		listModel.removeAllElements();
		listModel.addElement(jt.getText());
		int j=mod.size();
		for (int i=0;i<j;i++){
			listModel.addElement(mod.getElementAt(i));
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		valider.setEnabled(true);
		if(e.getKeyChar()==Event.ENTER){
			ajoutElementTop();
			jt.setText("");
			valider.setEnabled(false);
		}

	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(valider)){
			ajoutElementTop();
			jt.setText("");
			valider.setEnabled(false);
		}

	}

}
