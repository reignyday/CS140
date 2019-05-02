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
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import project.Instruction;
import project.Loader;
import project.Machine;
import project.Memory;

public class CodeViewPanel
{
    private Machine machine;
    private Instruction instr;
    private JScrollPane scroller;
    private JTextField[] codeText = new JTextField[Memory.CODE_SIZE];
    private JTextField[] codeBinHex = new JTextField[Memory.CODE_SIZE];
    private int previousColor = -1;

    public CodeViewPanel(Machine m)
    {
        this.machine = m;
    }

    public JComponent createCodeDisplay()
    {
        JPanel panel = new JPanel();

        panel.setLayout(new BorderLayout());

        Border border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK), "Code Memory View",
                TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);

        panel.setBorder(border);

        JPanel innerPanel = new JPanel();

        innerPanel.setLayout(new BorderLayout());

        JPanel numPanel = new JPanel();
        JPanel textPanel = new JPanel();
        JPanel hexPanel = new JPanel();

        numPanel.setLayout(new GridLayout(0, 1));
        textPanel.setLayout(new GridLayout(0, 1));
        hexPanel.setLayout(new GridLayout(0, 1));

        innerPanel.add(numPanel, BorderLayout.LINE_START);
        innerPanel.add(textPanel, BorderLayout.CENTER);
        innerPanel.add(hexPanel, BorderLayout.LINE_END);

        for (int i = 0; i < Memory.CODE_SIZE; ++i)
        {
            numPanel.add(new JLabel(i + ": ", SwingConstants.RIGHT));

            this.codeText[i] = new JTextField(10);
            this.codeBinHex[i] = new JTextField(12);

            textPanel.add(this.codeText[i]);
            hexPanel.add(this.codeBinHex[i]);
        }

        this.scroller = new JScrollPane(innerPanel);

        panel.add(this.scroller);

        return panel;
    }

    public void update(String arg)
    {
        System.out.println(arg);
        if ("Load Code".equals(arg))
        {
            for (int i = 0; i < this.machine.getProgramSize(); i++)
            {
                this.instr = this.machine.getCode(i);

                this.codeText[i].setText(this.instr.getText());
                this.codeBinHex[i].setText(this.instr.getBinHex());
            }

            this.previousColor = this.machine.getPC();

            this.codeBinHex[this.previousColor].setBackground(Color.YELLOW);
            this.codeText[this.previousColor].setBackground(Color.YELLOW);
        }
        else if ("Clear".equals(arg))
        {
            for (int i = 0; i < this.machine.getProgramSize(); i++)
            {
                this.codeText[i].setText("");
                this.codeBinHex[i].setText("");
            }

            if (this.previousColor >= 0 && this.previousColor < Memory.CODE_SIZE)
            {
                this.codeText[this.previousColor].setBackground(Color.WHITE);
                this.codeBinHex[this.previousColor].setBackground(Color.WHITE);
            }

            this.previousColor = -1;
        }

        if (this.previousColor >= 0 && this.previousColor < Memory.CODE_SIZE)
        {
            this.codeText[this.previousColor].setBackground(Color.WHITE);
            this.codeBinHex[this.previousColor].setBackground(Color.WHITE);
        }

        this.previousColor = this.machine.getPC();

        if (this.previousColor >= 0 && this.previousColor < Memory.CODE_SIZE)
        {
            this.codeText[this.previousColor].setBackground(Color.YELLOW);
            this.codeBinHex[this.previousColor].setBackground(Color.YELLOW);
        }

        if (this.scroller != null && this.instr != null && this.machine != null)
        {
            JScrollBar bar = this.scroller.getVerticalScrollBar();

            int pc = this.machine.getPC();

            if (pc >= 0 && pc < Memory.CODE_SIZE && this.codeBinHex[pc] != null)
            {
                Rectangle bounds = this.codeBinHex[pc].getBounds();

                bar.setValue(Math.max(0, bounds.y - 15 * bounds.height));
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        Machine machine = new Machine(() -> System.exit(0));

        CodeViewPanel panel = new CodeViewPanel(machine);

        JFrame frame = new JFrame("TEST");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null);

        frame.add(panel.createCodeDisplay());

        frame.setVisible(true);

        System.out.println(Loader.load(machine, new File("factorial.pexe")));

        panel.update("Load Code");
    }
}
