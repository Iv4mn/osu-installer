package graficos;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

class menu extends JFrame implements ActionListener {

    private static final long serialVersionUID = 2191982509771087126L;
    private final JMenuBar menuBar;
    private final JMenu menu1, menu3;
    private final JMenuItem menuItem31, menuItem32, menuItem33, menuItem;
    private final JButton button1, button2, button3;
    private final Cursor hand = new Cursor(Cursor.HAND_CURSOR);

    public menu() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menu1 = new JMenu("Options");
        menuBar.add(menu1);

        menu3 = new JMenu("Color de fondo");
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

        if (e.getSource() == button1) {

            System.out.println("Opening...\n");
            try {

                final Process process = new ProcessBuilder("bash", "-c", "echo $HOME").start();

                final BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

                final String outputline = br.readLine();
                
                if (process.waitFor() == 0) {
                    Runtime.getRuntime().exec(outputline + "/osu-folder/osu.AppImage");

                }
            } catch (final IOException e1) {

                JOptionPane.showMessageDialog(null, "osu!lazer no encontrado. Asegurese de tenerlo instalado",
                        "Error al abrir", JOptionPane.ERROR_MESSAGE);

            } catch (InterruptedException e1) {
				e1.printStackTrace();
			}
        }

        if (e.getSource() == button2) {
            class myThread implements Runnable {
                Thread t;
                int c;
                String[] comandos;
                ProgressBar pb;

                myThread(final String[] comandos) {
                    t = new Thread(this);
                    c = 0;
                    this.comandos = comandos;
                    t.start();
                }

                @Override
                public void run() {
                        System.out.println("Installing...\n");
                        try {
                            for (final String x : comandos) {
                            	
                                final Process process = new ProcessBuilder("bash", "-c", x).start();
                            	if (c == 1 || c == 2) {
                                    this.pb = new ProgressBar(process);
                            		this.pb.setVisible(true);
                            	}
                                final InputStream inputStream = process.getInputStream();

                                final InputStream errorStream = process.getErrorStream();
                                                                
                                BufferedReader br= new BufferedReader(new InputStreamReader(inputStream));
                                String outputline;
                                while ( (outputline = br.readLine()) !=null ) {
                                	
                                	outputline = outputline.substring(0, outputline.length()-1);
                                	pb.setprogress(Integer.parseInt(outputline));
                                	System.out.flush();
                                	
                                }
                                
                                BufferedReader er = new BufferedReader(new InputStreamReader(errorStream));
                                String err;
                                while ( (err = er.readLine()) !=null ) {
                                	
                                	System.out.println("Error: " + err);
                                	System.out.flush();
                                }
                                
                                if (c == 1) {
                                	this.pb.dispose();
                                }

                                System.out.println("Process " + c++ + " finished with exit code: " + process.waitFor());
                                
                            }
                        } catch (final IOException e1) {
                            e1.printStackTrace();
                        } catch (InterruptedException e2) {
							e2.printStackTrace();
						} finally {
                            if (!this.pb.getFlag()) {
                                this.pb.dispose();
                            	JOptionPane.showMessageDialog(null, "Descarga finalizada");
                            	
                            }
                            
                        }
                }

            }
            final String[] commands = { "mkdir $HOME/osu-folder",
                    "wget -nc -O $HOME/osu-folder/osu.AppImage https://github.com/ppy/osu/releases/download/2022.205.0/osu.AppImage 2>&1 | grep -Eo -w '[0-9]{1,3}%'",
                    "wget -nc -O $HOME/osu-folder/lazer https://raw.githubusercontent.com/ppy/osu/master/assets/lazer.png 2>&1 | grep -Eo -w '[0-9]{1,3}%'",
                    "cat > $HOME/.local/share/applications/osu.desktop << EOF\n[Desktop Entry]\nType=Application\nTerminal=false\nEncoding=UTF-8\nVersion=2022.205.0\nName=Osu!\nExec=$HOME/osu-folder/osu.AppImage\nCategories=Osu\nIcon=$HOME/osu-folder/lazer.png\nEOF" };
            new myThread(commands);
        }

        if (e.getSource() == button3) {

            final String[] commands = { "rm -r $HOME/osu-folder/", "rm $HOME/.local/share/applications/osu.desktop",
                    "rm -r $HOME/.local/share/osu/" };
            final int option = JOptionPane.showConfirmDialog(null, "¿Quieres eliminar todos tus archivos de usuario?",
                    "Uninstaller", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);

            int counter;

            if (option == 0) {
                counter = 2;
            } else {
                counter = 0;
            }

            System.out.println("Deleting...\n");
            for (int i = 0; i <= counter; i++) {

                try {
                    final Process process = new ProcessBuilder("bash", "-c", commands[i]).start();

                    final InputStream inputStream = process.getInputStream();

                    final InputStream errorStream = process.getErrorStream();

                    final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

                    final BufferedReader er = new BufferedReader(new InputStreamReader(errorStream));

                    String outputline;

                    while ((outputline = br.readLine()) != null) {

                        System.out.println("Input: " + outputline);

                    }
                    String errorline;
                    while ((errorline = er.readLine()) != null) {

                        System.out.println("Error: " + errorline);

                    }

                    System.out.println("Process " + i + " exited with exit code: " + process.waitFor());

                } catch (final IOException e1) {
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
					e1.printStackTrace();
				}
            }
        }

        if (e.getSource() == menuItem31) {
            getContentPane().setBackground(Color.BLACK);
        } if (e.getSource() == menuItem32) {
            getContentPane().setBackground(Color.RED);
        } if (e.getSource() == menuItem33) {
            getContentPane().setBackground(Color.WHITE);
        } if (e.getSource() == menuItem) {
            System.exit(0);
        }

    }
}