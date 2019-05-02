package projectview;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuBarBuilder
{
    private JMenuItem assemble = new JMenuItem("Assemble Source...");
    private JMenuItem load = new JMenuItem("Load File...");
    private JMenuItem exit = new JMenuItem("Exit");
    private JMenuItem go = new JMenuItem("Go");

    private Mediator mediator;

    public MenuBarBuilder(Mediator med)
    {
        this.mediator = med;
    }

    public void update()
    {
        this.assemble.setEnabled(this.mediator.getCurrentState().getAssembleFileActive());
        this.load.setEnabled(this.mediator.getCurrentState().getLoadFileActive());
        this.go.setEnabled(this.mediator.getCurrentState().getStepActive());
    }

    JMenu createFileMenu()
    {
        JMenu menu = new JMenu("File");

        menu.setMnemonic(KeyEvent.VK_F);

        this.assemble.setMnemonic(KeyEvent.VK_M);
        this.assemble.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        this.assemble.addActionListener(e -> this.mediator.assembleFile());

        this.load.setMnemonic(KeyEvent.VK_L);
        this.load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        this.load.addActionListener(e -> this.mediator.loadFile());

        this.exit.setMnemonic(KeyEvent.VK_E);
        this.exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        this.exit.addActionListener(e -> this.mediator.exit());

        menu.add(this.assemble);
        menu.add(this.load);

        menu.addSeparator();

        menu.add(this.exit);

        return menu;
    }

    JMenu createExecuteMenu()
    {
        JMenu menu = new JMenu("Execute");

        menu.setMnemonic(KeyEvent.VK_X);

        this.go.setMnemonic(KeyEvent.VK_G);
        this.go.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        this.go.addActionListener(e -> this.mediator.execute());

        menu.add(this.go);

        return menu;
    }
}
