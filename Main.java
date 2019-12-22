import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    //returns lines of file as a list
    public static List<String> readFile(String filename) {
        List<String> records = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                records.add(line);
            }
            reader.close();
            return records;
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner();
        SymbolTable symbolTableForIdentifiers = new SymbolTable();
        SymbolTable symbolTableForConstants = new SymbolTable();
        ProgramInternalForm programInternalForm = new ProgramInternalForm();

        List<String> file = readFile("H:/Limbaje formale si tehnici de compilare/Lab1/lftc/src/file2.txt");

        for (String line : file) {
            List<String> tokens = scanner.tokenListGenerator(line);
            //System.out.println("tokenListGenerator result" + tokens);
            for (String token : tokens) {
                if (scanner.getLanguageSpecification().getLanguage().contains(token)) {
                    for (int j = 0; j < scanner.getCodificationTable().size(); j++) {
                        if (scanner.getCodificationTable().get(j).key.equals(token)) {
                            programInternalForm.add(scanner.getCodificationTable().get(j).value, -1);
                            break;
                        }
                    }
                } else if (scanner.isIdentifier(token)) {
                    int id = symbolTableForIdentifiers.add(token);
                    programInternalForm.add(0, id);
                } else if (scanner.isConstant(token)) {
                    int id = symbolTableForConstants.add(token);
                    programInternalForm.add(1, id);
                } else {
                    System.out.println("Unknown token:" + token);
                    break;
                }
            }
        }

        System.out.println("\nProgram Internal Form:");

        for (int i = 0; i < programInternalForm.getContent().size(); i++) {
            System.out.println(programInternalForm.getContent().get(i));
        }

        System.out.println("SymbolTable for identifiers:");

        for (Integer key : symbolTableForIdentifiers.getMap().keySet()) {
            System.out.println(key + " : " + symbolTableForIdentifiers.getMap().get(key));
        }

        System.out.println("SymbolTable for constants:");
        for (Integer key : symbolTableForConstants.getMap().keySet()) {
            System.out.println(key + " : " + symbolTableForConstants.getMap().get(key));
        }

        System.out.println("Codification Table:");
        for (int j = 0; j < scanner.getCodificationTable().size(); j++) {
            System.out.println(scanner.getCodificationTable().get(j));
        }
    }
}

