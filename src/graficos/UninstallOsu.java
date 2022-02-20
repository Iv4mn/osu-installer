package graficos;

import java.io.IOException;

public class UninstallOsu extends OsuOptions {
	
	public UninstallOsu(int opc) throws IOException {
		
		start(opc);
		
	}
	
	private void start(int opc) throws IOException {
		
		int counter;
		
		counter = opc == 0 ? 2 : 0;
		
        final String[] commands = { "rm -r $HOME/osu-folder/", "rm $HOME/.local/share/applications/osu.desktop",
        "rm -r $HOME/.local/share/osu/" };
		
		for (int i = 0; i <= counter; i++) {
			
			super.execCommandRT(new String[] {
				"bash", "-c", commands[i]
			});
			
		}
		
	}

}
