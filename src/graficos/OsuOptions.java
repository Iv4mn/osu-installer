package graficos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class OsuOptions {
	
	ProcessBuilder processBuilder;
	Process process;
	
	public OsuOptions() {
		processBuilder = new ProcessBuilder();
	}

	public void addCommands(String... command) {
		processBuilder.command(command);
	}

	public Process execCommandPB() throws IOException {
		try{
			this.process = processBuilder.start();
			return process;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Process execCommandRT(String[] commands) throws IOException {
		try {
			this.process = Runtime.getRuntime().exec(commands);
			return process;
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

	public String getInputCommand() throws IOException {
		
		InputStream inp = process.getInputStream();
		
		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(inp));
		
		return br.readLine();
		
	}

	public String getErrorCommand() throws IOException {
		
		InputStreamReader inp;
		inp = new InputStreamReader(process.getErrorStream());
		
		BufferedReader br;
		br = new BufferedReader(inp);
		
		return br.readLine();
		
	}

	public int getExitCode() throws InterruptedException {
		return process.waitFor();
	}
	
}
