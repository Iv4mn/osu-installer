package graficos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class OsuInstaller extends OsuOptions implements Runnable {
   Thread t;
   int c;
   final String[] commands = {
      "mkdir $HOME/osu-folder",
      "wget -nc -O $HOME/osu-folder/osu.AppImage https://github.com/ppy/osu/releases/download/2022.205.0/osu.AppImage 2>&1 | grep -Eo -w '[0-9]{1,3}%'",
      "wget -nc -O $HOME/osu-folder/lazer https://raw.githubusercontent.com/ppy/osu/master/assets/lazer.png 2>&1 | grep -Eo -w '[0-9]{1,3}%'",
      "cat > $HOME/.local/share/applications/osu.desktop << EOF\n[Desktop Entry]\nType=Application\nTerminal=false\nEncoding=UTF-8\nVersion=2022.205.0\nName=Osu!\nExec=$HOME/osu-folder/osu.AppImage\nCategories=Osu\nIcon=$HOME/osu-folder/lazer.png\nEOF"
   };
   ProgressBar pb;

   OsuInstaller() {
      t = new Thread(this);
      c = 0;
      t.start();
   }

   @Override
   public void run() {

      try {
         for (final String x: commands) {

            super.addCommands("bash", "-c", x);
            final Process process = super.execCommandPB();
            if (c == 1 || c == 2) {
               this.pb = new ProgressBar(process);
               this.pb.setVisible(true);

	       		InputStream inp = process.getInputStream();
	    		
	    		BufferedReader br;
	    		br = new BufferedReader(new InputStreamReader(inp));
	    		
	    		br.readLine();
               
               String outputline;
               while ((outputline = br.readLine()) != null) {

                  outputline = outputline.substring(0, outputline.length() - 1);
                  System.out.println(outputline);
                  pb.setprogress(Integer.parseInt(outputline));

               }

               String err;
               while ((err = super.getInputCommand()) != null) {

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
    	  
    	  if (!pb.getFlag()) {
    		  
    		  JOptionPane.showMessageDialog(null, "Descarga finalizada");
    		  
    	  }
    	  
      }
      }
   }