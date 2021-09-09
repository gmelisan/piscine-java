package ex03;

class WrongFormatException extends Exception {
    public WrongFormatException(String msg) {
        super(msg);
    }
}
public class NumberedUrl {
    public int number;
    public String url;

    public NumberedUrl(String line) throws WrongFormatException {
        String[] tokens = line.split(" ");
        if (tokens.length != 2)
            throw new WrongFormatException("Space not found");
        try {
            number = Integer.parseInt(tokens[0]);
        } catch (NumberFormatException e) {
            throw new WrongFormatException("Can't convert '" + tokens[0] + "' to number");
        }
        url = tokens[1];
    }
}
