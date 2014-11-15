package fr.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Onglet extends JPanel implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final MyDeskTopPane dp;
	private final BarreVerticale bv;
	private final JTabbedPane tabbedPane;
	private JLabel closeButon;
	private final int num;
	public Onglet(MyDeskTopPane dp, int num, JTabbedPane tabbedPane,String nomFichier){
		this.dp=dp;
		this.tabbedPane=tabbedPane;
		this.num=num;
		Toolkit tk=getToolkit();
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(tk.getScreenSize().height,tk.getScreenSize().width));
		closeButon=null;
		if(num!=0){
			closeButon = new JLabel(new ImageIcon(new ImageIcon("ressources/icones/fermer.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
			closeButon.addMouseListener(this);
			JPanel p1=new JPanel();
			p1.setOpaque(false);
			JLabel ic = new JLabel(new ImageIcon(new ImageIcon("ressources/screenshot.png").getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT)));
			JLabel lbTitle=new JLabel(nomFichier);
			p1.add(ic);
			p1.add(lbTitle);
			p1.add(closeButon);
			//this.tabbedPane.addTab(nomFichier,p1);
			System.out.println(this.tabbedPane.countComponents());
			this.tabbedPane.setTabComponentAt(num,p1);
		}

		bv=new BarreVerticale(this.dp);
		JPanel jp2=new JPanel();
		jp2.add(bv);
		jp2.setBackground(new Color(190,190,190));
		this.add(dp,BorderLayout.CENTER);
		this.add(jp2,BorderLayout.WEST);
		this.add(new PanelEdit(this.dp,bv),BorderLayout.EAST);
		this.add(new PanelBdd(),BorderLayout.SOUTH);

	}
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(closeButon)){
			System.out.println("fermer");
			tabbedPane.remove(this.num);
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
