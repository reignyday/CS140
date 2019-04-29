package projectview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import project.Machine;
import project.Memory;

public class Mediator
{
    private Machine machine;
    private JFrame frame;
    private TimerUnit tUnit;
    private CodeViewPanel codeViewPanel;
    private MemoryViewPanel memoryViewPanel1;
    private MemoryViewPanel memoryViewPanel2;
    private MemoryViewPanel memoryViewPanel3;
    private ControlPanel controlPanel;
    private ProcessorViewPanel processorPanel;

    private void notify(String str)
    {
        this.codeViewPanel.update(str);
        this.memoryViewPanel1.update(str);
        this.memoryViewPanel2.update(str);
        this.memoryViewPanel3.update(str);
        this.controlPanel.update();
        this.processorPanel.update();
    }

    private void createAndShowGUI()
    {
        this.tUnit = new TimerUnit(this);

        //this.ioUnit = new IOUnit(this);
        //this.ioUnit.initialize();

        this.codeViewPanel = new CodeViewPanel(this.machine);
        this.memoryViewPanel1 = new MemoryViewPanel(this.machine, 0, 160);
        this.memoryViewPanel2 = new MemoryViewPanel(this.machine, 160, Memory.DATA_SIZE / 2);
        this.memoryViewPanel3 = new MemoryViewPanel(this.machine, Memory.DATA_SIZE / 2,
                Memory.DATA_SIZE);
        this.controlPanel = new ControlPanel(this);
        this.processorPanel = new ProcessorViewPanel(this.machine);

        //this.menuBuilder = new MenuBarBuilder(this);

        this.frame = new JFrame("Simulator");

        //JMenuBar bar = new JMenuBar();

        //this.frame.setJMenuBar(bar);

        //bar.add(menuBuilder.createFileMenu());
        //bar.add(menuBuilder.createExecuteMenu());

        Container content = this.frame.getContentPane();

        content.setLayout(new BorderLayout(1, 1));
        content.setBackground(Color.BLACK);

        this.frame.setSize(1200, 600);

        this.frame.add(this.codeViewPanel.createCodeDisplay(), BorderLayout.LINE_START);
        this.frame.add(this.processorPanel.createProcessorDisplay(), BorderLayout.PAGE_START);

        JPanel center = new JPanel();

        center.setLayout(new GridLayout(1, 3));

        center.add(this.memoryViewPanel1.createMemoryDisplay());
        center.add(this.memoryViewPanel2.createMemoryDisplay());
        center.add(this.memoryViewPanel3.createMemoryDisplay());

        this.frame.add(center, BorderLayout.CENTER);
        this.frame.add(this.controlPanel.createControlDisplay(), BorderLayout.PAGE_END);

        // the next line will be commented or deleted later
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //this.frame.addWindowListener(WindowListenerFactory.windowClosingFactory(e -> exit()));

        this.frame.setLocationRelativeTo(null);

        this.tUnit.start();

        //this.currentState().enter();

        this.frame.setVisible(true);

        this.notify("");
    }

    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(() ->
        {
            Mediator mediator = new Mediator();

            Machine machine = new Machine(
                    () -> mediator.setCurrentState(States.PROGRAM_HALTED));

            mediator.setMachine(machine);

            mediator.createAndShowGUI();
        });
    }

    public void step()
    {
    }

    public void clear()
    {
    }

    public void toggleAutoStep()
    {
    }

    public void reload()
    {
    }

    public void setPeriod(int value)
    {
    }

    Machine getMachine()
    {
        return this.machine;
    }

    void setMachine(Machine m)
    {
        this.machine = m;
    }

    JFrame getFrame()
    {
        return this.frame;
    }
}
