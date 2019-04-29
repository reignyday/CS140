package projectview;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ControlPanel
{
    private Mediator mediator;
    private JButton stepButton = new JButton("Step");
    private JButton clearButton = new JButton("Clear");
    private JButton runButton = new JButton("Run/Pause");
    private JButton reloadButton = new JButton("Reload");
    
    public ControlPanel(Mediator mediator)
    {
        this.mediator = mediator;
    }
    
    public JComponent createControlDisplay()
    {
        JPanel panel = new JPanel();
        
        panel.setLayout(new GridLayout(1, 0));
        
        this.stepButton.setBackground(Color.WHITE);
        this.stepButton.addActionListener(e -> this.mediator.step());
        
        this.clearButton.setBackground(Color.WHITE);
        this.clearButton.addActionListener(e -> this.mediator.clear());
        
        this.runButton.setBackground(Color.WHITE);
        this.runButton.addActionListener(e -> this.mediator.toggleAutoStep());
        
        this.reloadButton.setBackground(Color.WHITE);
        this.reloadButton.addActionListener(e -> this.mediator.reload());
        
        panel.add(this.stepButton);
        panel.add(this.clearButton);
        panel.add(this.runButton);
        panel.add(this.reloadButton);
        
        JSlider slider = new JSlider(5, 1000);
        
        slider.addChangeListener(e -> this.mediator.setPeriod(slider.getValue()));

        panel.add(slider);
        
        return panel;
    }
    
    public void update()
    {
    }
}
