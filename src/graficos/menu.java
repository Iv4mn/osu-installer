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
    private final JMenuItem menuItem31;
    private final JMenuItem menuItem32; 
    private final JMenuItem menuItem33; 
    private final JMenuItem menuItem;
    private final JButton button1; 
    private final JButton button2;
    private final JButton button3;
    private final Cursor hand = new Cursor(Cursor.HAND_CURSOR);

    public Menu() {
        final JMenuBar menuBar;
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu1 = new JMenu("Options");
        menuBar.add(menu1);

        JMenu menu3 = new JMenu("Color de fondo");
        menu1.add(menu3);

        menuItem31 = new JMenuItem("Negro");
        menu3.add(menuItem31);
        menuItem31.addActionListener(this);

        menuItem32 = new JMenuItem("Rojo");
        menu3.add(menuItem32);
        menuItem32.addActionListener(this);

        menuItem33 = new JMenuItem("Blanco");
        menu3.add(menuItem33);
        menuItem33.addActionListener(this);

        menuItem = new JMenuItem("Salir");
        menu1.add(menuItem);
        menuItem.addActionListener(this);

        button1 = new JButton("Abrir osu!lazer");
        button1.setCursor(hand);
        add(button1);
        button1.addActionListener(this);

        button2 = new JButton("Descargar osu!lazer");
        button2.setCursor(hand);
        add(button2);
        button2.addActionListener(this);

        button3 = new JButton("Desinstalar osu!lazer");
        button3.setCursor(hand);
        add(button3);
        button3.addActionListener(this);

        button1.setBounds(80, 10, 200, 50);
        button2.setBounds(370, 10, 200, 50);
        button3.setBounds(650, 10, 200, 50);

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
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource().equals(button1)) {
        	try {
				new OsuOpen();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
        }
        if (e.getSource().equals(button2)) {
        	new OsuInstaller();
        }
        if (e.getSource().equals(button3)) {
        	
        	try {
				new UninstallOsu(
						JOptionPane.showConfirmDialog(null, "¿Quieres eliminar tus datos de usuario?", "Desinstalar osu",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
						);
			} catch (HeadlessException | IOException e1) {
				e1.printStackTrace();
			}
        	
        }
        if (e.getSource().equals(menuItem31)) {
            getContentPane().setBackground(Color.BLACK);
        } 
        if (e.getSource().equals(menuItem32)) {
            getContentPane().setBackground(Color.RED);
        } 
        if (e.getSource().equals(menuItem33)) {
            getContentPane().setBackground(Color.WHITE);
        } 
        if (e.getSource().equals(menuItem)) {
            System.exit(0);
        }
    }
}