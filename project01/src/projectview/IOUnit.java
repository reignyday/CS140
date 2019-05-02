package projectview;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import project.Assembler;
import project.FullAssembler;
import project.Loader;
import project.Machine;

public class IOUnit
{
    private Mediator mediator;
    private Machine machine;
    private String defaultDir;
    private String sourceDir;
    private String executableDir;
    private Properties properties = null;
    private File currentlyExecutingFile;
    private Assembler assembler = new FullAssembler();

    public IOUnit(Mediator view)
    {
        this.mediator = view;
        this.machine = this.mediator.getMachine();
    }

    public void initialize()
    {
        this.locateDefaultDirectory();
        this.loadPropertiesFile();
    }

    private void locateDefaultDirectory()
    {
        // CODE TO DISCOVER THE ECLIPSE DEFAULT DIRECTORY
        // WHICH WILL STORE THE propertyfile
        File temp = new File("propertyfile.txt");

        if (!temp.exists())
        {
            PrintWriter out;

            try
            {
                out = new PrintWriter(temp);

                out.close();

                this.defaultDir = temp.getAbsolutePath();

                temp.delete();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        else
            this.defaultDir = temp.getAbsolutePath();

        // change to forward slashes
        this.defaultDir = this.defaultDir.replace('\\', '/');
        int lastSlash = this.defaultDir.lastIndexOf('/');
        this.defaultDir = this.defaultDir.substring(0, lastSlash + 1);
    }

    void loadPropertiesFile()
    {
        try
        {
            // load properties file "propertyfile.txt", if it exists
            this.properties = new Properties();
            this.properties.load(new FileInputStream("propertyfile.txt"));

            this.sourceDir = this.properties.getProperty("SourceDirectory");
            this.executableDir = this.properties.getProperty("ExecutableDirectory");

            // CLEAN UP ANY ERRORS IN WHAT IS STORED:
            if (this.sourceDir == null || this.sourceDir.length() == 0
                    || !new File(this.sourceDir).exists())
                this.sourceDir = this.defaultDir;
            if (this.executableDir == null || this.executableDir.length() == 0
                    || !new File(this.executableDir).exists())
                this.executableDir = this.defaultDir;
        }
        catch (Exception e)
        {
            // PROPERTIES FILE DID NOT EXIST
            this.sourceDir = this.defaultDir;
            this.executableDir = this.defaultDir;
        }
    }

    /**
     * Translate method reads a source "pasm" file and saves the file with the
     * extension "pexe" by collecting the input and output files and calling
     * Assembler.assemble. If the source has errors the error messages will be
     * reported in a JOptionPane.
     */
    public void assembleFile()
    {
        File source = null;
        File outputExe = null;

        JFileChooser chooser = new JFileChooser(this.sourceDir);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Pippin Source Files", "pasm");

        chooser.setFileFilter(filter);

        // CODE TO LOAD DESIRED FILE
        int openOK = chooser.showOpenDialog(null);

        if (openOK == JFileChooser.APPROVE_OPTION)
            source = chooser.getSelectedFile();

        if (source != null && source.exists())
        {
            // CODE TO IDENTIFY WHICH DIRECTORY HAS THE pasm FILES
            this.sourceDir = source.getAbsolutePath();

            this.sourceDir = this.sourceDir.replace('\\', '/');

            int lastDot = this.sourceDir.lastIndexOf('.');

            String outName = this.sourceDir.substring(0, lastDot + 1) + "pexe";

            int lastSlash = this.sourceDir.lastIndexOf('/');

            this.sourceDir = this.sourceDir.substring(0, lastSlash + 1);

            outName = outName.substring(lastSlash + 1);

            filter = new FileNameExtensionFilter("Pippin Executable Files", "pexe");

            if (this.executableDir.equals(this.defaultDir))
                chooser = new JFileChooser(this.sourceDir);
            else
                chooser = new JFileChooser(this.executableDir);

            chooser.setFileFilter(filter);

            chooser.setSelectedFile(new File(outName));

            int saveOK = chooser.showSaveDialog(null);

            if (saveOK == JFileChooser.APPROVE_OPTION)
                outputExe = chooser.getSelectedFile();

            if (outputExe != null)
            {
                // CODE TO RESTORE WHICH DIRECTORIES HAVE THE pasm AND pexe FILES
                // WHICH WE WILL ALLOW TO BE DIFFERENT
                this.executableDir = outputExe.getAbsolutePath();

                this.executableDir = this.executableDir.replace('\\', '/');

                lastSlash = this.executableDir.lastIndexOf('/');

                this.executableDir = this.executableDir.substring(0, lastSlash + 1);

                try
                {
                    // CODE TO SAVE WHICH DIRECTORIES HAVE THE pasm AND pexe FILES
                    // WHICH WE WILL ALLOW TO BE DIFFERENT
                    this.properties.setProperty("SourceDirectory", this.sourceDir);
                    this.properties.setProperty("ExecutableDirectory", this.executableDir);

                    this.properties.store(new FileOutputStream("propertyfile.txt"),
                            "File locations");
                }
                catch (Exception e)
                {
                    System.out.println("Error writing properties file");
                }

                StringBuilder errors = new StringBuilder();

                int errorLine = this.assembler.assemble(source.getAbsolutePath(),
                        outputExe.getAbsolutePath(), errors);

                if (errorLine == 0)
                    JOptionPane.showMessageDialog(this.mediator.getFrame(),
                            "The source was assembled to an executable", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(this.mediator.getFrame(), errors.toString(),
                            "Source code error ", JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(this.mediator.getFrame(),
                        "The output file has problems.\n" + "Cannot assemble the program",
                        "Warning", JOptionPane.OK_OPTION);
        }
        else
            JOptionPane.showMessageDialog(this.mediator.getFrame(),
                    "The source file has problems.\n" + "Cannot assemble the program", "Warning",
                    JOptionPane.OK_OPTION);
    }

    public void loadFile()
    {
        JFileChooser chooser = new JFileChooser(this.executableDir);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Pippin Executable Files",
                "pexe");

        chooser.setFileFilter(filter);

        // CODE TO LOAD DESIRED FILE
        int openOK = chooser.showOpenDialog(null);

        if (openOK == JFileChooser.APPROVE_OPTION)
            this.currentlyExecutingFile = chooser.getSelectedFile();

        if (openOK == JFileChooser.CANCEL_OPTION)
            this.currentlyExecutingFile = null;

        if (this.currentlyExecutingFile != null && this.currentlyExecutingFile.exists())
        {
            this.executableDir = this.currentlyExecutingFile.getAbsolutePath();

            this.executableDir = this.executableDir.replace('\\', '/');

            int lastSlash = this.executableDir.lastIndexOf('/');

            this.executableDir = this.executableDir.substring(0, lastSlash + 1);

            try
            {
                this.properties.setProperty("SourceDirectory", this.sourceDir);
                this.properties.setProperty("ExecutableDirectory", this.executableDir);

                this.properties.store(new FileOutputStream("propertyfile.txt"), "File locations");
            }
            catch (Exception e)
            {
                System.out.println("Error writing properties file");
            }
        }

        if (this.currentlyExecutingFile != null)
            this.finalLoad_ReloadStep();
        else
            JOptionPane.showMessageDialog(this.mediator.getFrame(),
                    "No file selected.\n" + "Cannot load the program", "Warning",
                    JOptionPane.OK_OPTION);
    }

    void finalLoad_ReloadStep()
    {
        this.mediator.clear();

        String str = "";

        try
        {
            str = Loader.load(this.machine, this.currentlyExecutingFile);

            this.mediator.makeReady("Load Code");
        }
        catch (NumberFormatException e)
        {
            JOptionPane
                    .showMessageDialog(
                            this.mediator.getFrame(), "The file being selected has problems.\n"
                                    + str + "\n" + "Cannot load the program",
                            "Warning", JOptionPane.OK_OPTION);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(
                    this.mediator.getFrame(), "General Exception has occurred from Loader.\n"
                            + e.getMessage() + "\n" + "Cannot load the program",
                    "Warning", JOptionPane.OK_OPTION);
        }
    }
}
