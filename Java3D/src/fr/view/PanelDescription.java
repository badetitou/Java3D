package fr.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import fr.model.OutilsBdd;

public class PanelDescription extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JTextArea textArea;

	private final JPanel panelBouton;
	private final JButton valider;
	private final JButton modifier;
	private String description;
	private final OutilsBdd obdd;
	public void actualiserDesc(String desc){
		this.description=desc;
		this.textArea.removeAll();
		this.dessinerDesc();
		this.revalidate();
	}
	private boolean nouveau;
	public void setNouveau(boolean nouveau) {
		this.nouveau = nouveau;
	}
	public PanelDescription(String nomFichier,boolean nouveau){
		this.setLayout(new FlowLayout(0,30,20));
		this.nouveau=nouveau;
		this.setBackground(new Color(215,215,215));
		obdd=new OutilsBdd("Database.db");
		if(this.nouveau)
			description="";
		else
			description=obdd.getDesc(nomFichier);

		JPanel pDesc=new JPanel();
		pDesc.setLayout(new BorderLayout());
		pDesc.setBackground(new Color(215,215,215));
		JLabel label = new JLabel("Ajouter une description");
		pDesc.add(label,BorderLayout.NORTH);
		textArea=new JTextArea();
		pDesc.add(textArea,BorderLayout.CENTER);
		this.dessinerDesc();
		panelBouton=new JPanel();
		panelBouton.setLayout(new GridLayout(2,1,0,20));

		valider=new JButton("Valider");
		valider.setEnabled(false);
		modifier=new JButton("Modifier");

		valider.addMouseListener(this);
		modifier.addMouseListener(this);


		panelBouton.add(modifier);
		panelBouton.add(valider);
		panelBouton.setBackground(new Color(215,215,215));
		description=textArea.getText();
		this.add(pDesc);
		this.add(panelBouton);
	}

	public void dessinerDesc(){
		textArea.setText(description);
		textArea.setPreferredSize(new Dimension(Window.outil.getScreenSize().width-(Window.outil.getScreenSize().width/3),Window.outil.getScreenSize().height/6));
		textArea.setEditable(false);
		textArea.setBackground(new Color(230,230,230));
		Border border = BorderFactory.createLineBorder(new Color(190,190,190));
		textArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		textArea.setForeground(new Color(130,130,130));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(modifier)){
			textArea.setEditable(true);
			valider.setEnabled(true);
			modifier.setEnabled(false);
			textArea.setBackground(new Color(255,255,255));
			textArea.setForeground(new Color(0,0,0));
		}


		if(e.getSource().equals(valider)){
			textArea.setEditable(false);
			valider.setEnabled(false);
			modifier.setEnabled(true);
			textArea.setBackground(new Color(230,230,230));
			textArea.setForeground(new Color(130,130,130));
			description=textArea.getText();
		}

	}

	public String getDescription() {
		return description;
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
