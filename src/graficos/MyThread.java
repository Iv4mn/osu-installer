package graficos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

class MyThread implements Runnable {
                Thread t;
                int c;
                String[] comandos;
                ProgressBar pb;

                MyThread(final String[] comandos) {
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