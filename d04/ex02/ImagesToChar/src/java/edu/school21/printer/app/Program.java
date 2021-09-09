package edu.school21.printer.app;
import com.beust.jcommander.*;
import edu.school21.printer.logic.*;
import com.diogonunes.jcolor.*;

import java.util.*;

@Parameters(separators = "=")
class Arguments {
    @Parameter
    public List<String> parameters = new ArrayList<>();

    @Parameter(names = { "-w", "--white" }, description = "Character for white color")
    public String white = "WHITE";

    @Parameter(names = { "-b", "--black" }, description = "Character for black color")
    public String black = "BLACK";

    @Parameter(names = { "-h", "--help" }, description = "Help")
    public boolean help;
}

public class Program {

    private static final String def = "BLACK";

    private static final String path = "/resources/image.bmp";

    public static void main(String[] args) {
        Arguments arguments = new Arguments();
        JCommander jCmd = JCommander.newBuilder().addObject(arguments).build();
        try {
            jCmd.parse(args);
        } catch (ParameterException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        if (arguments.help) {
            jCmd.usage();
            System.exit(0);
        }
        Converter converter = new Converter();
        Attribute[][] list = converter.convert(path, arguments.white, arguments.black, def);
        for (Attribute[] row : list) {
            for (Attribute c : row)
                System.out.print(Ansi.colorize("  ", c));
            System.out.println();
        }
    }
}
