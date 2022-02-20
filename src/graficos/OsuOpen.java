package graficos;

import java.io.IOException;

public class OsuOpen extends OsuOptions {
	
	
	public OsuOpen() throws IOException, InterruptedException {
		start();
	}
	
	private void start() throws IOException, InterruptedException {
		
		super.addCommands("bash", "-c", "echo $HOME");
		super.execCommandPB();
		
		String input = super.getInputCommand();
		
		if (super.getExitCode() == 0) {
			
				super.execCommandRT(new String[] {
						input + "/osu-folder/osu.AppImage"
				});
		}
	}
	
}
