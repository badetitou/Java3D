package fr.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import fr.model.OutilsBdd;

public class PanelTag extends JPanel implements KeyListener{
	private final OutilsBdd obdd;
	private final JList liste;
	private final DefaultListModel listModel;
	private final JTextField jt;
	private final JScrollPane scroll;
	private final JLabel jl;
	private final JPanel panel;
	public PanelTag(){
		this.setLayout(new FlowLayout(0,30,20));
		panel=new JPanel();
		panel.setLayout(new GridLayout(2,1));
		this.setBackground(new Color(215,215,215));
		obdd=new OutilsBdd("Database.db");
		listModel=new DefaultListModel();
		liste=new JList(listModel);
		jl=new JLabel("Saisissez un tag : ");
		panel.setBackground(new Color(215,215,215));
		liste.setBorder(BorderFactory.createLoweredBevelBorder());
		scroll=new JScrollPane(liste);
		scroll.setPreferredSize(new Dimension(Window.outil.getScreenSize().width/3,Window.outil.getScreenSize().height/6));
		//scroll.add(liste);
		jt=new JTextField();
		jt.setPreferredSize(new Dimension(200,70));
		jt.addKeyListener(this);
		panel.add(jl);
		panel.add(jt);
		this.add(scroll);
		this.add(panel);

	}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar()==Event.ENTER){
			listModel.addElement(jt.getText());
			jt.setText("");
		}

	}
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
