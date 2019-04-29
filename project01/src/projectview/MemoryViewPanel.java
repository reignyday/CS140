package projectview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import project.Loader;
import project.Machine;

public class MemoryViewPanel
{
    private Machine machine;
    private JScrollPane scroller;
    private JTextField[] dataHex;
    private JTextField[] dataDecimal;
    private int lower = -1;
    private int upper = -1;
    private int previousColor = -1;

    public MemoryViewPanel(Machine m, int low, int up)
    {
        this.machine = m;
        this.lower = low;
        this.upper = up;
    }

    public JComponent createMemoryDisplay()
    {
        JPanel panel = new JPanel();

        panel.setLayout(new BorderLayout());

        Border border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Data Memory View [" + lower + "-" + upper + "]",
                TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);

        panel.setBorder(border);

        JPanel innerPanel = new JPanel();

        innerPanel.setLayout(new BorderLayout());

        JPanel numPanel = new JPanel();
        JPanel decimalPanel = new JPanel();
        JPanel hexPanel = new JPanel();

        numPanel.setLayout(new GridLayout(0, 1));
        decimalPanel.setLayout(new GridLayout(0, 1));
        hexPanel.setLayout(new GridLayout(0, 1));

        innerPanel.add(numPanel, BorderLayout.LINE_START);
        innerPanel.add(decimalPanel, BorderLayout.CENTER);
        innerPanel.add(hexPanel, BorderLayout.LINE_END);

        this.dataDecimal = new JTextField[this.upper - this.lower];
        this.dataHex = new JTextField[this.upper - this.lower];

        for (int i = this.lower; i < this.upper; ++i)
        {
            numPanel.add(new JLabel(i + ": ", JLabel.RIGHT));

            dataDecimal[i - this.lower] = new JTextField(10);
            dataHex[i - this.lower] = new JTextField(10);

            decimalPanel.add(dataDecimal[i - this.lower]);
            hexPanel.add(dataHex[i - this.lower]);
        }

        this.scroller = new JScrollPane(innerPanel);

        panel.add(this.scroller);

        return panel;
    }

    public void update(String str)
    {
        for (int i = this.lower; i < this.upper; ++i)
        {
            int val = this.machine.getData(i);

            dataDecimal[i - this.lower].setText("" + val);

            String s = Integer.toHexString(val);

            if (val < 0)
                s = "-" + Integer.toHexString(-val);

            dataHex[i - this.lower].setText(s.toUpperCase());
        }

        if (str != null && str.equals("Clear"))
        {
            if (this.lower <= previousColor && previousColor < this.upper)
            {
                dataDecimal[previousColor - this.lower].setBackground(Color.WHITE);
                dataHex[previousColor - this.lower].setBackground(Color.WHITE);

                previousColor = -1;
            }
        }
        else
        {
            if (previousColor >= this.lower && previousColor < this.upper)
            {
                dataDecimal[previousColor - this.lower].setBackground(Color.WHITE);
                dataHex[previousColor - this.lower].setBackground(Color.WHITE);
            }

            previousColor = this.machine.getChangedDataIndex();

            if (previousColor >= this.lower && previousColor < upper)
            {
                dataDecimal[previousColor - this.lower].setBackground(Color.YELLOW);
                dataHex[previousColor - this.lower].setBackground(Color.YELLOW);
            }
        }

        if (this.scroller != null && this.machine != null)
        {
            JScrollBar bar = this.scroller.getVerticalScrollBar();

            if (this.machine.getChangedDataIndex() >= this.lower &&
                this.machine.getChangedDataIndex() < this.upper &&
                this.dataDecimal != null)
            {
                Rectangle bounds = this.dataDecimal[
                    this.machine.getChangedDataIndex() - this.lower].getBounds();

                bar.setValue(Math.max(0, bounds.y - 15*bounds.height));
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        Machine machine = new Machine(() -> System.exit(0));

        MemoryViewPanel panel = new MemoryViewPanel(machine, 0, 500);

        JFrame frame = new JFrame("TEST");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null);

        frame.add(panel.createMemoryDisplay());

        frame.setVisible(true);

        System.out.println(Loader.load(machine, new File("test.pexe")));

        panel.update("");
    }
}
