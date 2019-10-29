import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FillLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;

public class mainWindow {

	protected Shell shell;
	/**
	 * @wbp.nonvisual location=30,48
	 */
	private final JPanel panel = new JPanel();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			mainWindow window = new mainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		panel.setBackground(Color.RED);
		shell = new Shell();
		shell.setLayout(new FillLayout(SWT.VERTICAL));

	}
}
