package projectview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import project.CodeAccessException;
import project.DataAccessException;
import project.DivideByZeroException;
import project.IllegalInstructionException;
import project.Machine;
import project.Memory;
import project.ParityCheckException;

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

    private IOUnit ioUnit;
    private MenuBarBuilder menuBuilder;

    private States currentState = States.NOTHING_LOADED;

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

        this.ioUnit = new IOUnit(this);
        this.ioUnit.initialize();

        this.codeViewPanel = new CodeViewPanel(this.machine);
        this.memoryViewPanel1 = new MemoryViewPanel(this.machine, 0, 160);
        this.memoryViewPanel2 = new MemoryViewPanel(this.machine, 160, Memory.DATA_SIZE / 2);
        this.memoryViewPanel3 = new MemoryViewPanel(this.machine, Memory.DATA_SIZE / 2,
                Memory.DATA_SIZE);
        this.controlPanel = new ControlPanel(this);
        this.processorPanel = new ProcessorViewPanel(this.machine);

        this.menuBuilder = new MenuBarBuilder(this);

        this.frame = new JFrame("Simulator");

        JMenuBar bar = new JMenuBar();

        this.frame.setJMenuBar(bar);

        bar.add(this.menuBuilder.createFileMenu());
        bar.add(this.menuBuilder.createExecuteMenu());

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

        //this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.frame.addWindowListener(WindowListenerFactory.windowClosingFactory(e -> this.exit()));

        this.frame.setLocationRelativeTo(null);

        this.tUnit.start();

        this.currentState.enter();

        this.frame.setVisible(true);

        this.notify("");
    }

    public void assembleFile()
    {
        this.ioUnit.assembleFile();
    }

    public void loadFile()
    {
        this.ioUnit.loadFile();
    }

    public void step()
    {
        if (this.currentState != States.PROGRAM_HALTED
                && this.currentState != States.NOTHING_LOADED)
        {
            try
            {
                this.machine.step();
            }
            catch (CodeAccessException e)
            {
                JOptionPane.showMessageDialog(this.frame,
                        "Illegal access to code from line " + this.machine.getPC() + "\n"
                                + "Exception message: " + e.getMessage(),
                        "Run time error", JOptionPane.OK_OPTION);

                System.out.println("Illegal access to code from line " + this.machine.getPC());
                System.out.println("Exception message: " + e.getMessage());
            }
            catch (DataAccessException e)
            {
                JOptionPane.showMessageDialog(this.frame,
                        "Illegal access to data from line " + this.machine.getPC() + "\n"
                                + "Exception message: " + e.getMessage(),
                        "Run time error", JOptionPane.OK_OPTION);

                System.out.println("Illegal access to data from line " + this.machine.getPC());
                System.out.println("Exception message: " + e.getMessage());
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                JOptionPane.showMessageDialog(this.frame,
                        "Array index OOB exception from line " + this.machine.getPC() + "\n"
                                + "Exception message: " + e.getMessage(),
                        "Run time error", JOptionPane.OK_OPTION);

                System.out.println("Array index OOB exception from line " + this.machine.getPC());
                System.out.println("Exception message: " + e.getMessage());
            }
            catch (NullPointerException e)
            {
                JOptionPane.showMessageDialog(this.frame,
                        "Null pointer exception from line " + this.machine.getPC() + "\n"
                                + "Exception message: " + e.getMessage(),
                        "Run time error", JOptionPane.OK_OPTION);

                System.out.println("Null pointer exception from line " + this.machine.getPC());
                System.out.println("Exception message: " + e.getMessage());
            }
            catch (ParityCheckException e)
            {
                JOptionPane.showMessageDialog(this.frame,
                        "Parity check exception from line " + this.machine.getPC() + "\n"
                                + "Exception message: " + e.getMessage(),
                        "Run time error", JOptionPane.OK_OPTION);

                System.out.println("Parity check exception from line " + this.machine.getPC());
                System.out.println("Exception message: " + e.getMessage());
            }
            catch (IllegalInstructionException e)
            {
                JOptionPane.showMessageDialog(this.frame,
                        "Illegal instruction exception from line " + this.machine.getPC() + "\n"
                                + "Exception message: " + e.getMessage(),
                        "Compile time error", JOptionPane.OK_OPTION);

                System.out
                        .println("Illegal instruction exception from line " + this.machine.getPC());
                System.out.println("Exception message: " + e.getMessage());
            }
            catch (IllegalArgumentException e)
            {
                JOptionPane.showMessageDialog(this.frame,
                        "Illegal argument exception from line " + this.machine.getPC() + "\n"
                                + "Exception message: " + e.getMessage(),
                        "Compile time error", JOptionPane.OK_OPTION);

                System.out.println("Illegal argument exception from line " + this.machine.getPC());
                System.out.println("Exception message: " + e.getMessage());
            }
            catch (DivideByZeroException e)
            {
                JOptionPane.showMessageDialog(this.frame,
                        "Divide by zero exception from line " + this.machine.getPC() + "\n"
                                + "Exception message: " + e.getMessage(),
                        "Compile time error", JOptionPane.OK_OPTION);

                System.out.println("Divide by zero exception from line " + this.machine.getPC());
                System.out.println("Exception message: " + e.getMessage());
            }

            this.notify("");
        }
    }

    public void execute()
    {
        // wouldn't it make more sense to just call this.step in a while loop instead of copying code?
        while (this.currentState != States.PROGRAM_HALTED
                && this.currentState != States.NOTHING_LOADED)
            try
            {
                this.machine.step();
            }
            catch (CodeAccessException e)
            {
                JOptionPane.showMessageDialog(this.frame,
                        "Illegal access to code from line " + this.machine.getPC() + "\n"
                                + "Exception message: " + e.getMessage(),
                        "Run time error", JOptionPane.OK_OPTION);

                System.out.println("Illegal access to code from line " + this.machine.getPC());
                System.out.println("Exception message: " + e.getMessage());
            }
            catch (DataAccessException e)
            {
                JOptionPane.showMessageDialog(this.frame,
                        "Illegal access to data from line " + this.machine.getPC() + "\n"
                                + "Exception message: " + e.getMessage(),
                        "Run time error", JOptionPane.OK_OPTION);

                System.out.println("Illegal access to data from line " + this.machine.getPC());
                System.out.println("Exception message: " + e.getMessage());
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                JOptionPane.showMessageDialog(this.frame,
                        "Array index OOB exception from line " + this.machine.getPC() + "\n"
                                + "Exception message: " + e.getMessage(),
                        "Run time error", JOptionPane.OK_OPTION);

                System.out.println("Array index OOB exception from line " + this.machine.getPC());
                System.out.println("Exception message: " + e.getMessage());
            }
            catch (NullPointerException e)
            {
                JOptionPane.showMessageDialog(this.frame,
                        "Null pointer exception from line " + this.machine.getPC() + "\n"
                                + "Exception message: " + e.getMessage(),
                        "Run time error", JOptionPane.OK_OPTION);

                System.out.println("Null pointer exception from line " + this.machine.getPC());
                System.out.println("Exception message: " + e.getMessage());
            }
            catch (ParityCheckException e)
            {
                JOptionPane.showMessageDialog(this.frame,
                        "Parity check exception from line " + this.machine.getPC() + "\n"
                                + "Exception message: " + e.getMessage(),
                        "Run time error", JOptionPane.OK_OPTION);

                System.out.println("Parity check exception from line " + this.machine.getPC());
                System.out.println("Exception message: " + e.getMessage());
            }
            catch (IllegalInstructionException e)
            {
                JOptionPane.showMessageDialog(this.frame,
                        "Illegal instruction exception from line " + this.machine.getPC() + "\n"
                                + "Exception message: " + e.getMessage(),
                        "Compile time error", JOptionPane.OK_OPTION);

                System.out
                        .println("Illegal instruction exception from line " + this.machine.getPC());
                System.out.println("Exception message: " + e.getMessage());
            }
            catch (IllegalArgumentException e)
            {
                JOptionPane.showMessageDialog(this.frame,
                        "Illegal argument exception from line " + this.machine.getPC() + "\n"
                                + "Exception message: " + e.getMessage(),
                        "Compile time error", JOptionPane.OK_OPTION);

                System.out.println("Illegal argument exception from line " + this.machine.getPC());
                System.out.println("Exception message: " + e.getMessage());
            }
            catch (DivideByZeroException e)
            {
                JOptionPane.showMessageDialog(this.frame,
                        "Divide by zero exception from line " + this.machine.getPC() + "\n"
                                + "Exception message: " + e.getMessage(),
                        "Compile time error", JOptionPane.OK_OPTION);

                System.out.println("Divide by zero exception from line " + this.machine.getPC());
                System.out.println("Exception message: " + e.getMessage());
            }

        this.notify("");
    }

    public void clear()
    {
        this.machine.clear();

        this.setCurrentState(States.NOTHING_LOADED);

        this.currentState.enter();

        this.notify("Clear");
    }

    public void makeReady(String s)
    {
        this.tUnit.setAutoStepOn(false);

        this.setCurrentState(States.PROGRAM_LOADED_NOT_AUTO_STEPPING);

        this.currentState.enter();

        this.notify(s);
    }

    public void setCurrentState(States s)
    {
        if (s == States.PROGRAM_HALTED)
            this.tUnit.setAutoStepOn(false);

        this.currentState = s;

        s.enter();

        this.notify("");
    }

    public void exit()
    {
        int decision = JOptionPane.showConfirmDialog(this.frame, "Do you really wish to exit?",
                "Confirmation", JOptionPane.YES_NO_OPTION);

        if (decision == JOptionPane.YES_OPTION)
            System.exit(0);
    }

    public void toggleAutoStep()
    {
        this.tUnit.toggleAutoStep();

        if (this.tUnit.isAutoStepOn())
            this.setCurrentState(States.AUTO_STEPPING);
        else
            this.setCurrentState(States.PROGRAM_LOADED_NOT_AUTO_STEPPING);
    }

    public void reload()
    {
        this.tUnit.setAutoStepOn(false);

        this.clear();

        this.ioUnit.finalLoad_ReloadStep();
    }

    public void setPeriod(int value)
    {
        this.tUnit.setPeriod(value);
    }

    public States getCurrentState()
    {
        return this.currentState;
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

    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(() ->
        {
            Mediator mediator = new Mediator();

            Machine machine = new Machine(() -> mediator.setCurrentState(States.PROGRAM_HALTED));

            mediator.setMachine(machine);

            mediator.createAndShowGUI();
        });
    }
}
