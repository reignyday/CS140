package project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

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

            final Pattern blankLine = Pattern.compile("^\\s*$");
            final Pattern startsBlank = Pattern.compile("^\\s+\\S");

            for (int i = 0; i < lines.size(); ++i)
            {
                String line = lines.get(i);

                // 1
                if (blank == -1 && blankLine.matcher(line).find())
                    blank = i;
                else if (blank != -1 && !blankLine.matcher(line).find())
                {
                    error.append(
                            "\nError on line " + (blank + 1) + ": Blank line in the source file");

                    ret = blank;

                    blank = -1;
                }

                // 2
                if (startsBlank.matcher(line).find())
                {
                    error.append("\nError on line " + (i + 1)
                            + ": Line starts with illegal white space");

                    ret = i + 1;

                    continue;
                }

                line = line.trim();

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
                    if (Instruction.NO_ARG_MNEMONICS.contains(opcode.toUpperCase()))
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

                            boolean prepended = false;

                            if (arg.startsWith("M"))
                            {
                                prepended = true;

                                if (!Instruction.IMM_MNEMONICS.contains(parts[0]))
                                {
                                    error.append("\nError on line " + (i + 1)
                                            + ": this mnemonic does not allow immediate mode");

                                    ret = i + 1;
                                }
                            }
                            else if (arg.startsWith("N"))
                            {
                                prepended = true;

                                if (!Instruction.IND_MNEMONICS.contains(parts[0]))
                                {
                                    error.append("\nError on line " + (i + 1)
                                            + ": this mnemonic does not allow indirect mode");

                                    ret = i + 1;
                                }
                            }
                            else if (arg.startsWith("J"))
                            {
                                prepended = true;

                                if (!Instruction.JMP_MNEMONICS.contains(parts[0]))
                                {
                                    error.append("\nError on line " + (i + 1)
                                            + ": this mnemonic does not allow special jump mode");

                                    ret = i + 1;
                                }
                            }

                            if (prepended)
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

    public static void main(String[] args)
    {
        StringBuilder error = new StringBuilder();

        System.out.print("Enter the name of the file without extension: ");

        try (Scanner keyboard = new Scanner(System.in))
        {
            String filename = keyboard.nextLine();

            int i = new FullAssembler().assemble(filename + ".pasm", filename + ".pexe", error);

            System.out.println("result = " + i);
        }
    }
}
