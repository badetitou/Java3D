package fr.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

public class BarreVerticale extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JButton b1;
	private final JButton b2;
	private final JButton b3;
	private final JButton b4;

	private final ImageIcon ic1;
	private final ImageIcon ic2;
	private final ImageIcon ic3;
	private final ImageIcon ic4;

	private final ImageIcon ic6;
	private final ImageIcon ic7;
	private final ImageIcon ic8;
	private final ImageIcon ic9;

	private boolean bb1 = true;
	private boolean bb2 = false;
	private boolean modeEdit = false;
	private boolean modeSquelette=false;
	private boolean dot = false;
	private final MyDeskTopPane dp;

	private JColorChooser jcc=null;

	public void setJcc(JColorChooser jcc) {
		this.jcc = jcc;
	}

	public BarreVerticale(MyDeskTopPane dp) {
		this.dp = dp;
		this.setLayout(new GridLayout(4, 1, 0, 4));
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		this.setBackground(new Color(190, 190, 190));

		b1 = new JButton();
		b2 = new JButton();
		b3 = new JButton();
		b4 = new JButton();

		b1.setToolTipText("Mode 1 vue");
		b2.setToolTipText("Mode 4 vues");
		b3.setToolTipText("Mode edit");
		b4.setToolTipText("Mode squelette");

		ic1 = new ImageIcon(new ImageIcon("ressources/icones/oneview.png")
		.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		ic2 = new ImageIcon(new ImageIcon("ressources/icones/multipanel.png")
		.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		ic3 = new ImageIcon(new ImageIcon("ressources/icones/edit.png")
		.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		ic4=new ImageIcon(new ImageIcon("ressources/icones/squelette.png")
		.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));

		ic6 = new ImageIcon(new ImageIcon("ressources/icones/oneviewclic.png")
		.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		ic7 = new ImageIcon(new ImageIcon(
				"ressources/icones/multipanelclic.png").getImage()
				.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		ic8 = new ImageIcon(new ImageIcon("ressources/icones/editclic.png")
		.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		ic9 =new ImageIcon(new ImageIcon("ressources/icones/squeletteclic.png")
		.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));

		b1.setMargin(new Insets(0, 0, 0, 0));
		b2.setMargin(new Insets(0, 0, 0, 0));
		b3.setMargin(new Insets(0, 0, 0, 0));
		b4.setMargin(new Insets(0, 0, 0, 0));

		b1.setBorder(null);
		b2.setBorder(null);
		b3.setBorder(null);
		b4.setBorder(null);

		b1.addMouseListener(this);
		b2.addMouseListener(this);
		b3.addMouseListener(this);
		b4.addMouseListener(this);

		b1.setIcon(ic6);
		bb1 = true;
		b2.setIcon(ic2);
		b3.setIcon(ic3);
		b4.setIcon(ic4);

		this.add(b1);
		this.add(b2);
		this.add(b3);
		this.add(b4);


	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(b1) && !bb1) {
			b1.setIcon(ic6);
			b2.setIcon(ic2);
			b3.setIcon(ic3);
			b4.setIcon(ic4);
			modeSquelette=false;
			bb1 = true;
			bb2 = false;
			modeEdit = false;
			this.dp.getiFrameMain().setPreferredSize(
					new Dimension(MyDeskTopPane.dimension));
			this.dp.getiFrameDessous().setVisible(false);
			this.dp.getiFrameDessus().setVisible(false);
			this.dp.getiFrameProfil().setVisible(false);
			this.jcc.setEnabled(false);
			jcc.setVisible(false);
			this.dp.getModel().setDimension(MyDeskTopPane.dimension);
		} else if (e.getSource().equals(b2) && !bb2) {
			b2.setIcon(ic7);
			b1.setIcon(ic1);
			b3.setIcon(ic3);
			b4.setIcon(ic4);
			bb2 = true;
			bb1 = false;
			modeEdit = false;
			modeSquelette=false;
			this.dp.getiFrameMain().setPreferredSize(MyDeskTopPane.dimmini);
			this.dp.getiFrameDessous().setVisible(true);
			this.dp.getiFrameDessus().setVisible(true);
			this.dp.getiFrameProfil().setVisible(true);
			this.jcc.setEnabled(false);
			jcc.setVisible(false);
			this.dp.getModel().setDimension(MyDeskTopPane.dimmini);

		} else if (e.getSource().equals(b3) && !modeEdit) {
			b3.setIcon(ic8);
			b2.setIcon(ic2);
			b1.setIcon(ic1);
			b4.setIcon(ic4);
			bb1 = false;
			bb2 = false;
			modeEdit = true;
			modeSquelette=false;
			this.dp.getiFrameMain().setPreferredSize(MyDeskTopPane.dimension);
			this.dp.getiFrameDessous().setVisible(false);
			this.dp.getiFrameDessus().setVisible(false);
			this.dp.getiFrameProfil().setVisible(false);
			this.dp.getModel().setDimension(MyDeskTopPane.dimension);
			this.jcc.setEnabled(true);
			jcc.setVisible(true);

		}

		else if (e.getSource().equals(b4) && !modeSquelette){
			b3.setIcon(ic3);
			b2.setIcon(ic2);
			b1.setIcon(ic1);
			b4.setIcon(ic9);
			bb1 = false;
			bb2 = false;
			modeEdit = false;
			modeSquelette=true;
			this.dp.getiFrameMain().setPreferredSize(MyDeskTopPane.dimension);
			this.dp.getiFrameDessous().setVisible(false);
			this.dp.getiFrameDessus().setVisible(false);
			this.dp.getiFrameProfil().setVisible(false);
			this.dp.getModel().setDimension(MyDeskTopPane.dimension);
			this.jcc.setEnabled(false);
			jcc.setVisible(false);

		}
		this.dp.getPanel().getM().recentrer();
		this.dp.getPanel().getM().zoomAuto();
		this.dp.getPanel().repaint();
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

	public void setModeEdit(boolean modeEdit) {
		this.modeEdit = modeEdit;
	}

	public boolean getModeEdit() {
		return modeEdit;
	}

	public boolean isSquelette(){
		return modeSquelette;
	}

	public boolean isBb1() {
		return bb1;
	}

	public boolean isBb2() {
		return bb2;
	}

	public void setBb1(boolean bb1) {
		this.bb1 = bb1;
	}

	public boolean isModeEdit() {
		return modeEdit;
	}

	public boolean isDot() {
		return dot;
	}

}
