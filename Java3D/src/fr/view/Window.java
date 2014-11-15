package fr.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


/**
 * 
 * @author Loic
 * Fenêtre du logiciel après l'écran du chargement. Elle contient tous les JPanel.
 *
 */
@SuppressWarnings("serial")
public class Window extends JFrame implements MouseListener{

	Panneau panel;
	public static Toolkit outil;
	private final JPanel container;
	public static JFrame frame;
	private final MyDeskTopPane dp;
	private final JTabbedPane tabbedPane;
	private final JLabel closeButon;

	public Window(MyDeskTopPane dp) {
		super("3D Lib");
		Window.frame=this;
		outil = getToolkit();
		this.setIconImage(new ImageIcon("ressources/image/logoforreal2.png").getImage());
		this.setVisible(true);
		this.setSize(outil.getScreenSize());
		this.setResizable(false);
		this.setState(Frame.NORMAL);

		this.dp=dp;
		//onglets
		tabbedPane = new JTabbedPane();
		closeButon = new JLabel(new ImageIcon(new ImageIcon("ressources/icones/fermer.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		closeButon.addMouseListener(this);
		JPanel p1=new JPanel();
		p1.setOpaque(false);
		JLabel ic = new JLabel(new ImageIcon(new ImageIcon("ressources/screenshot.png").getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT)));
		JLabel lbTitle=new JLabel("Icosa");
		p1.add(ic);
		p1.add(lbTitle);
		p1.add(closeButon);


		tabbedPane.addTab("Icosa", new Onglet(dp));
		//tabbedPane.addTab("Head",new ImageIcon(new ImageIcon("ressources/icones/description.png").getImage().getScaledInstance(26, 26, Image.SCALE_DEFAULT)), new Onglet(new MyDeskTopPane("ressources/image/head.gts")));
		tabbedPane.setTabComponentAt(0,p1);
		JPanel jp2=new JPanel();
		jp2.add(new BarreVerticale(this.dp));
		jp2.setBackground(new Color(190,190,190));

		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.add(new Barre(),BorderLayout.NORTH);
		container.add(tabbedPane,BorderLayout.CENTER);



		this.setJMenuBar(new Menu());
		this.getContentPane().add(container);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(closeButon)){
			System.out.println("fermer");
			int i = 0;
			if (i != -1) {
				tabbedPane.remove(i);
			}
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
