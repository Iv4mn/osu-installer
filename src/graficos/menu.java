package graficos;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

class Menu extends JFrame implements ActionListener {

    private static final long serialVersionUID = 2191982509771087126L;
    private final Cursor hand = new Cursor(Cursor.HAND_CURSOR);

    public Menu() {
        final JMenuBar menuBar;
        final JMenuItem menuItemNegro;
        final JMenuItem menuItemRojo; 
        final JMenuItem menuItemBlanco; 
        final JMenuItem menuItemSalir;
        final JButton botonAbrir; 
        final JButton botonDescargar;
        final JButton botonDesinstalar;
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu1 = new JMenu("Options");
        menuBar.add(menu1);

        JMenu menu3 = new JMenu("Color de fondo");
        menu1.add(menu3);

        menuItemNegro = new JMenuItem("Negro");
        menu3.add(menuItemNegro);
        menuItemNegro.addActionListener(evt -> getContentPane().setBackground(Color.BLACK));

        menuItemRojo = new JMenuItem("Rojo");
        menu3.add(menuItemRojo);
        menuItemRojo.addActionListener(evt -> getContentPane().setBackground(Color.RED));

        menuItemBlanco = new JMenuItem("Blanco");
        menu3.add(menuItemBlanco);
        menuItemBlanco.addActionListener(evt -> getContentPane().setBackground(Color.WHITE));

        menuItemSalir = new JMenuItem("Salir");
        menu1.add(menuItemSalir);
        menuItemSalir.addActionListener(evt -> System.exit(0));

        botonAbrir = new JButton("Abrir osu!lazer");
        botonAbrir.setCursor(hand);
        add(botonAbrir);
        botonAbrir.addActionListener(evt -> {
        	
        	try {
				new OsuOpen();
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
        	
        });

        botonDescargar = new JButton("Descargar osu!lazer");
        botonDescargar.setCursor(hand);
        add(botonDescargar);
        botonDescargar.addActionListener(evt -> {
        	
        	new OsuInstaller();
        	
        });

        botonDesinstalar = new JButton("Desinstalar osu!lazer");
        botonDesinstalar.setCursor(hand);
        add(botonDesinstalar);
        botonDesinstalar.addActionListener(evt -> {
			try {
				new UninstallOsu(JOptionPane.showConfirmDialog(null, "¿Quieres borrar todos tus datos?", "Desinstalar osu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE));
			} catch (HeadlessException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

        botonAbrir.setBounds(80, 10, 200, 50);
        botonDescargar.setBounds(370, 10, 200, 50);
        botonDesinstalar.setBounds(650, 10, 200, 50);

    }

    public void initWindow() {
        setResizable(false);
        setLayout(null);
        setTitle("Open it up...");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    @Override
    public void actionPerformed(final ActionEvent e) {}
}