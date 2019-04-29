package project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FullAssembler implements Assembler
{
    private boolean readingCode = true;

    @Override
    public int assemble(String inputFileName, String outputFileName, StringBuilder error)
    {
        if (error == null)
            throw new IllegalArgumentException("Coding error: the error buffer is null");

        int ret = 0;

        try
        {
            List<String> lines = Files.readAllLines(Paths.get(inputFileName));

            int blank = -1;

            for (int i = 0; i < lines.size(); ++i)
            {
                String line = lines.get(i);

                // 2
                if (line.startsWith(" ") || line.startsWith("\t"))
                {
                    error.append("\nError on line " + (i + 1)
                            + ": Line starts with illegal white space");

                    ret = i + 1;
                }

                line = line.trim();

                // 1
                if (blank == -1 && line.length() == 0)
                    blank = i;
                else if (blank != -1 && line.length() != 0)
                {
                    error.append(
                            "\nError on line " + (blank + 1) + ": Blank line in the source file");

                    ret = blank;

                    blank = -1;
                }

                if (line.length() == 0)
                    continue;

                if (line.toUpperCase().equals("DATA"))
                {
                    // 3
                    if (!this.readingCode)
                    {
                        error.append(
                                "\nError on line " + (i + 1) + ": Cannot have two DATA separators");

                        ret = i + 1;
                    }

                    if (!line.equals("DATA"))
                    {
                        error.append("\nError on line " + (i + 1)
                                + ": Line does not have DATA in upper case");

                        ret = i + 1;
                    }

                    this.readingCode = false;

                    continue;
                }

                String[] parts = line.split("\\s+");

                if (this.readingCode)
                {
                    String opcode = parts[0];

                    // 4
                    if (!Instruction.OPCODES.containsKey(opcode.toUpperCase()))
                    {
                        error.append("\nError on line " + (i + 1) + ": illegal mnemonic");

                        ret = i + 1;
                    }

                    if (opcode != opcode.toUpperCase())
                    {
                        error.append(
                                "\nError on line " + (i + 1) + ": mnemonic must be upper case");

                        ret = i + 1;
                    }

                    // 5
                    if (Assembler.noArgument.contains(opcode.toUpperCase()))
                    {
                        if (parts.length != 1)
                        {
                            error.append("\nError on line " + (i + 1)
                                    + ": this mnemonic cannot take arguments");

                            ret = i + 1;
                        }
                    }
                    else if (parts.length == 2)
                        // 6
                        try
                        {
                            String arg = parts[1];

                            if (arg.startsWith("M") || arg.startsWith("N") || arg.startsWith("A"))
                                arg = arg.substring(1);

                            Integer.parseInt(arg, 16);
                        }
                        catch (NumberFormatException e)
                        {
                            error.append("\nError on line " + (i + 1)
                                    + ": argument is not a hex number");

                            ret = i + 1;
                        }
                    else
                    {
                        error.append("\nError on line " + (i + 1)
                                + ": this mnemonic has too many arguments");

                        ret = i + 1;
                    }
                }
                else
                    // 7
                    try
                    {
                        if (parts.length != 2)
                        {
                            error.append("\nError on line " + (i + 1)
                                    + ": DATA lines must be in the format [addr val]");

                            ret = i + 1;
                        }
                        else
                        {
                            Integer.parseInt(parts[0], 16);
                            Integer.parseInt(parts[1], 16);
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        error.append("\nError on line " + (i + 1)
                                + ": data has non-numeric memory address");

                        ret = i + 1;
                    }
            }
        }
        catch (FileNotFoundException e)
        {
            error.append("\nError: Unable to open the input file");

            return -1;
        }
        catch (IOException e)
        {
            error.append("\nUnexplained IO Exception");

            return -1;
        }

        if (ret == 0)
            return new SimpleAssembler().assemble(inputFileName, outputFileName, error);

        return ret;
    }
}
