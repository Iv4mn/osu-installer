package graficos;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

class ProgressBar extends JFrame implements WindowListener {
	private static final long serialVersionUID = -171014759624138058L;
	private final JProgressBar jb;
	private Process p;
	private boolean flag;

	public ProgressBar(Process p) {
		this.setSize(250, 150);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.addWindowListener(this);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.jb = new JProgressBar(0, 100);
		this.jb.setBounds(40, 40, 160, 30);
		this.jb.setValue(0);
		this.jb.setStringPainted(true);
		this.add(jb);
		final JLabel jl = new JLabel("Descargando...");
		jl.setBounds(65, 10, 300, 30);
		this.add(jl);
		this.p = p;
	}

	public void setprogress(final int progress) {

		jb.setValue(progress);
	}

	@Override
	public void windowOpened(final WindowEvent e) {/*Does nothing*/}

	@Override
	public void windowClosing(final WindowEvent e) {/*Does nothing*/}

	@Override
	public void windowIconified(final WindowEvent e) {/*Does nothing*/}

	@Override
	public void windowDeiconified(final WindowEvent e) {/*Does nothing*/}

	@Override
	public void windowActivated(final WindowEvent e) {/*Does nothing*/}

	@Override
	public void windowDeactivated(final WindowEvent e) {/*Does nothing*/}

	@Override
	public void windowClosed(WindowEvent e) {
		
		p.destroyForcibly();
		
		setFlag(true);

	}
	
	public boolean getFlag() {
		
		return this.flag;
		
	}
	
	public void setFlag(boolean flag) {
		
		this.flag = flag;
		
	}
	
	
	
}
