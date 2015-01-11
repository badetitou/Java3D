package fr.view;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WindowAPropos extends JFrame {

	public WindowAPropos() {
		PanelAPropos pAP = new PanelAPropos(this);
		this.setTitle("A Propos");
		this.setSize(Window.outil.getScreenSize().height/2, Window.outil.getScreenSize().width/5);
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
			this.text = new JLabel("<html>Le logiciel 3DLib permet de g�rer une biblioth�que d'objets 3D, permettant l'affichage <br> de ces mod�les ainsi que des fonctionnalit�s sp�cifiques aux besoins des FabLab. <br> Il a �t� con�u par les auteurs suivants :<br> Beno�t Verhaeghe, Lo�c Delwaulle, Robin Dauchy et Beno�t Froment. <br>Copyright(�) 3DLib, 2015. Tous droits r�serv�s.</html>");
			this.ok = new JButton("Ok");
			this.panOk = new JPanel();
			this.panImage = new JPanel();
			String path="ressources/image/800x400.png";
			JLabel l = new JLabel();
			l.setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH)));
			panImage.add(l);
			//this.setLayout(new GridLayout(3,1));
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