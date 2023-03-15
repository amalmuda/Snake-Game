import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI extends JFrame {
	private static JFrame vindu;
	JButton ned;
	private static JPanel panel;
	int x = 0;
	int y = 0;
	static int poengForrige = 0;
	static int poeng = 0;
	static Slange[][] kordinater = new Slange[12][12];
	static JPanel panel1;
	static ArrayList<Slange> slangeKropp = new ArrayList<>();
	static JLabel visP;
	static JLabel visPF;
	static JLabel visX;
	int r = 0;
	int k = 0;

	GUI() {
		// Standars Swing utseende
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			System.exit(1);
		}

		// Vindu med tittel
		JFrame vindu = new JFrame("Slangespillet");
		vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		vindu.add(panel);
		panel.setLayout(new BorderLayout());

		JPanel panel2 = new JPanel();
		panel.add(panel2, BorderLayout.NORTH);
		panel2.setLayout(new BorderLayout());

		visP = new JLabel("Poeng = " + Integer.toString(poeng));
		panel2.add(visP);

		panel1 = new JPanel();
		panel.add(panel1, BorderLayout.SOUTH);
		panel1.setLayout(new GridLayout(12, 12));

		for (int r = 0; r < 12; r++) {
			for (int k = 0; k < 12; k++) {
				Slange slange = new Slange(r, k);
				kordinater[r][k] = slange;
			}
		}

		slangeKropp.add(0, kordinater[5][5]);

		int a = 0;
		int b = 11;
		for (int i = 0; i < 10; i++) {
			kordinater[(int) (Math.random() * (b - a + 1)) + a][(int) (Math.random() * (b - a + 1)) + a].skatt = true;
		}

		for (int i = 0; i < slangeKropp.size(); i++) {
			if (i == 0) {
				slangeKropp.get(i).hode = true;
			} else {
				slangeKropp.get(i).kropp = true;
			}
		}

		// Opp knapp
		JButton opp = new JButton("Opp");
		class Opp implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				r = -1;
				k = 0;
			}
		}
		opp.addActionListener(new Opp());

		// Ned knapp
		ned = new JButton("Ned");
		class Ned implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				r = 1;
				k = 0;
			}
		}
		ned.addActionListener(new Ned());

		// Hoyre knapp
		JButton hoyre = new JButton("Hoyre");
		class Hoyre implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				r = 0;
				k = 1;
			}
		}
		hoyre.addActionListener(new Hoyre());

		// Venstre knapp
		JButton venstre = new JButton("Venstre");
		class Venstre implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				r = 0;
				k = -1;
			}
		}
		venstre.addActionListener(new Venstre());

		JButton avslutt = new JButton("Avslutt");
		class Avslutt implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		avslutt.addActionListener(new Avslutt());

		panel2.add(opp, BorderLayout.NORTH);
		panel2.add(ned, BorderLayout.SOUTH);
		panel2.add(avslutt, BorderLayout.CENTER);
		panel2.add(hoyre, BorderLayout.EAST);
		panel2.add(venstre, BorderLayout.WEST);

		opp.setPreferredSize(new Dimension(5, 20));
		ned.setPreferredSize(new Dimension(5, 20));
		opp.setPreferredSize(new Dimension(5, 20));
		ned.setPreferredSize(new Dimension(5, 20));

		frame();
		vindu.pack();
		vindu.setVisible(true);

		// Pakke alt sammen og gjore vindu synlig
		Traad traad = new Traad();
		this.addKeyListener(traad);
		while (true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vindu.remove(panel);
			panel = new JPanel();
			vindu.add(panel);
			panel.setLayout(new BorderLayout());

			panel2 = new JPanel();
			panel.add(panel2, BorderLayout.NORTH);
			panel2.setLayout(new BorderLayout());

			visP = new JLabel("Poeng = " + Integer.toString(poeng));
			panel2.add(visP);

			panel1 = new JPanel();
			panel.add(panel1, BorderLayout.SOUTH);
			panel1.setLayout(new GridLayout(12, 12));

			panel2.add(opp, BorderLayout.NORTH);
			panel2.add(ned, BorderLayout.SOUTH);
			panel2.add(avslutt, BorderLayout.CENTER);
			panel2.add(hoyre, BorderLayout.EAST);
			panel2.add(venstre, BorderLayout.WEST);

			opp.setPreferredSize(new Dimension(5, 20));
			ned.setPreferredSize(new Dimension(5, 20));
			opp.setPreferredSize(new Dimension(5, 20));
			ned.setPreferredSize(new Dimension(5, 20));

			refresh(r, k);
			vindu.pack();
			vindu.setVisible(true);
		}
	}

	public static void frame() {
		for (int r = 0; r < 12; r++) {
			for (int k = 0; k < 12; k++) {
				if (kordinater[r][k].hode == true && kordinater[r][k].kropp == false) {
					JLabel label = new JLabel("       O");
					label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					label.setPreferredSize(new Dimension(50, 50));
					label.setOpaque(true);
					label.setBackground(new Color(0, 204, 0));
					panel1.add(label);
				} else if (kordinater[r][k].kropp == true) {
					JLabel label = new JLabel("       X");
					label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					label.setPreferredSize(new Dimension(50, 50));
					label.setOpaque(true);
					label.setBackground(new Color(0, 204, 0));
					panel1.add(label);
				} else if (kordinater[r][k].skatt == true) {
					JLabel label = new JLabel("  $");
					label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					label.setPreferredSize(new Dimension(50, 50));
					label.setForeground(new Color(218, 41, 28));
					label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
					panel1.add(label);
				} else {
					JLabel label = new JLabel();
					label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					label.setPreferredSize(new Dimension(50, 50));
					panel1.add(label);
				}
			}
		}
	}

	public static void refresh(int x, int y) {
		skattSjekk();
		slangeKropp.get(0).hode = false;
		slangeKropp.get(0).kropp = true;
		kordinater[slangeKropp.get(0).rad + x][slangeKropp.get(0).kolonne + y].hode = true;
		slangeKropp.add(0, kordinater[slangeKropp.get(0).rad + x][slangeKropp.get(0).kolonne + y]);

		if (poeng == poengForrige) {
			kordinater[slangeKropp.get(slangeKropp.size() - 1).rad][slangeKropp
					.get(slangeKropp.size() - 1).kolonne].kropp = false;
			slangeKropp.remove(slangeKropp.size() - 1);
		} else {
			poengForrige++;
		}
		if (slangeKropp.get(0).hode == true && slangeKropp.get(0).skatt == true) {
			slangeKropp.get(0).skatt = false;
			poeng++;
		}
		if (slangeKropp.get(0).hode == true && slangeKropp.get(0).kropp == true) {
			System.exit(0);
		}
		frame();
	}

	public static void skattSjekk() {
		int s = 0;
		int a = 0;
		int b = 11;
		for (int r = 0; r < 12; r++) {
			for (int k = 0; k < 12; k++) {
				if (kordinater[r][k].skatt == true) {
					s++;
				}
			}
		}
		if (s < 10) {
			kordinater[(int) (Math.random() * (b - a + 1)) + a][(int) (Math.random() * (b - a + 1)) + a].skatt = true;
		}

	}
}