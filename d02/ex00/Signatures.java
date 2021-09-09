package ex00;

import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.*;

class SignatureFormatException extends Exception {}

public class Signatures {
    private Map<String, String> map = new HashMap<>();

    public Signatures(String filename) throws FileNotFoundException, SignatureFormatException {
        File fileSig = new File("signatures.txt");
        Scanner scanner = new Scanner(fileSig);
        String s = "";
        while (scanner.hasNextLine()) {
            s = scanner.nextLine();
            String[] tokens = s.split(",");
            if (tokens.length != 2)
                throw new SignatureFormatException();
            String tmp = tokens[1].replace(" ", "").toLowerCase(Locale.ROOT);
            map.put(tmp, tokens[0]);
        }
    }

    private String bytesToString(List<Integer> bytes) {
        String s = "";
        String hex = "";
        Integer tmp = 0;
        for (Integer b : bytes) {
            hex = Integer.toHexString(b);
            if (hex.length() == 0)
                hex = "00";
            else if (hex.length() == 1)
                hex = "0" + hex;
            s += hex;
        }
        return s;
    }
    public String getFormat(List<Integer> bytes) {
        String format = "";
        List<Integer> candidate = new LinkedList<>();
        for (int i = 0; i < bytes.size(); ++i) {
            candidate.add(bytes.get(i));
            String key = bytesToString(candidate);
            if (map.get(key) != null)
                return map.get(key);
        }
        return format;
    }
}