package fr.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WindowContacts extends JFrame {

	public WindowContacts() {
		PanelContacts pC = new PanelContacts(this);
		this.setTitle("Contacts");
		this.setSize(300, 200);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setFocusable(false);
		this.setContentPane(pC);
		this.setVisible(true);
		this.setIconImage(new ImageIcon("ressources/image/logoforreal2.png").getImage());
	}

	public class PanelContacts extends JPanel implements ActionListener {
		private final JLabel text;
		private final JLabel ao;
		private final JLabel bade;
		private final JLabel robin;
		private final JLabel loic;
		private final JFrame windowC;
		private final JPanel panOk;
		private final JButton ok;

		public PanelContacts(JFrame windowC) {
			this.windowC = windowC;
			this.text = new JLabel("Voici les contacts des auteurs de ce logiciel: ");
			this.ao = new JLabel("Benoît Froment : aorewin@gmail.com");
			this.bade = new JLabel("Benoît Verhaeghe : badetitou@gmail.com");
			this.robin = new JLabel("Robin Dauchy : dauchyrobin@gmail.com");
			this.loic = new JLabel("Loïc Delwaulle : delwaulle.loic@hotmail.fr");
			this.ok = new JButton("Ok");
			this.panOk = new JPanel();
			//this.setLayout(new GridLayout(6,1));
			this.add(text);
			this.add(ao);
			this.add(bade);
			this.add(robin);
			this.add(loic);
			this.add(panOk);
			panOk.setLayout(new FlowLayout());
			panOk.add(ok);
			this.ok.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(ok)){
				windowC.dispose();
			}

		}
	}

}