package projectview;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.Machine;

public class ProcessorViewPanel
{
    private Machine machine;
    private JTextField acc = new JTextField();
    private JTextField pc = new JTextField();
    
    public ProcessorViewPanel(Machine m)
    {
        this.machine = m;
    }
    
    public JComponent createProcessorDisplay()
    {
        JPanel panel = new JPanel();
        
        panel.setLayout(new GridLayout(1, 0));
        
        JPanel accPanel = new JPanel();
        JPanel pcPanel = new JPanel();
        
        accPanel.setLayout(new GridLayout(1, 0));
        pcPanel.setLayout(new GridLayout(1, 0));
        
        accPanel.add(new JLabel("Accumulator: ", JLabel.LEFT));
        accPanel.add(this.acc);
        
        pcPanel.add(new JLabel("Program Counter: ", JLabel.LEFT));
        pcPanel.add(this.pc);
        
        panel.add(accPanel);
        panel.add(pcPanel);
        
        return panel;
    }
    
    public void update()
    {
        if (this.machine != null)
        {
            this.acc.setText("" + this.machine.getAccum());
            this.pc.setText("" + this.machine.getPC());
        }
    }
    
    public static void main(String[] args)
    {
        Machine machine = new Machine(() -> System.exit(0));
        
        ProcessorViewPanel panel = new ProcessorViewPanel(machine);
        
        JFrame frame = new JFrame("TEST");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 60);
        frame.setLocationRelativeTo(null);
        
        frame.add(panel.createProcessorDisplay());
        
        frame.setVisible(true);
    }
}
