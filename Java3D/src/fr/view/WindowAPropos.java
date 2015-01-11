package fr.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class WindowAPropos extends JFrame {

	public WindowAPropos() {
		PanelAPropos pAP = new PanelAPropos(this);
		this.setTitle("A Propos");
		this.setSize(500, 300);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setFocusable(false);
		this.setContentPane(pAP);
		this.setVisible(true);
		this.setIconImage(new ImageIcon("ressources/image/logoforreal2.png").getImage());
	}

	public class PanelAPropos extends JPanel implements ActionListener {
		private final JLabel text;
		private final JFrame windowAP;
		private final JPanel panOk;
		private final JButton ok;	
		private final JPanel panImage;

		public PanelAPropos(JFrame windowAP) {
			this.windowAP = windowAP;
			this.text = new JLabel("<html>Le logiciel 3DLib permet de gérer une bibliothèque d'objets 3D, permettant l'affichage <br> de ces modèles ainsi que des fonctionnalités spécifiques aux besoins des FabLab. <br> Il a été conçu par les auteurs suivants :<br> Benoît Verhaeghe, Loïc Delwaulle, Robin Dauchy et Benoît Froment. <br>Copyright(©) 3DLib, 2015. Tous droits réservés.</html>");
			this.ok = new JButton("Ok");
			this.panOk = new JPanel();
			this.panImage = new JPanel();
			String path="ressources/image/800x400.png";
			JLabel l = new JLabel();
			l.setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(Window.outil.getScreenSize().width/9, Window.outil.getScreenSize().width/9, Image.SCALE_SMOOTH)));
			panImage.add(l);
			this.setLayout(new GridLayout(3,1));
			this.add(panImage);
			this.add(text);
			this.add(panOk);
			panOk.setLayout(new FlowLayout());
			panOk.add(ok);
			this.ok.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(ok)){
				windowAP.dispose();
			}
			
		}
	}

}