package model;

import java.util.ArrayList;
import java.util.regex.Pattern;

import java.util.List;

public class Scanner {
    private LanguageSpecification languageSpecification = new LanguageSpecification();

    public Boolean isIdentifier(String token) {
        return Pattern.matches("^[a-zA-Z]([a-zA-Z]|[0-9]){0,7}$", token);
    }

    public Boolean isConstant(String token) {
        return Pattern.matches("^(0|[+\\-]?[1-9][0-9]*)$|^'.'$|^\".*\"$", token);
        // ^ - beginning of line
        // * - 0 or more times
        // $ - end of line
        // \" - escaped quote
        // . - any character
    }

    public Boolean isPartOfOperator(char op) {
        String s = String.valueOf(op);//converting to string because operators is a list of strings

        for (int i = 0; i < languageSpecification.getOperators().size(); i++) {
            if (languageSpecification.getOperators().get(i).equals(s)) {
                return true;
            }
        }
        return false;
    }

    public Pair<String, Integer> getStringToken(String line, int index) {
        StringBuilder token = new StringBuilder();
        int quoteCount = 0;

        while (index < line.length() && quoteCount < 2) {
            if (line.charAt(index) == '"') {
                quoteCount++;
            }
            token.append(line.charAt(index));
            index++;

        }
        return new Pair<>(token.toString(), index);
    }

    public Pair<String, Integer> getOperatorToken(String line, int index) {
        StringBuilder token = new StringBuilder();

        while (index < line.length() && isPartOfOperator(line.charAt(index))) {
            token.append(line.charAt(index));
            index++;

        }
        return new Pair<>(token.toString(), index);
    }

    //returns a list of tokens from a line
    public List<String> tokenListGenerator(String line) {
        List<String> listOfTokens = new ArrayList<>();
        List<String> separators = languageSpecification.getSeparators();
        String token = "";
        int index = 0;

        while (index < line.length()) {
            if (line.charAt(index) == '"') {
                Pair<String, Integer> result = getStringToken(line, index);
                listOfTokens.add(result.key);
                index = result.value;
                token = "";
            } else if (isPartOfOperator(line.charAt(index))) {
                if (!token.equals("")) {
                    listOfTokens.add(token);
                }
                Pair<String, Integer> result = getOperatorToken(line, index);
                listOfTokens.add(result.key);
                index = result.value;
                token = "";
            } else if (separators.contains(String.valueOf(line.charAt(index)))) {
                if (!token.equals("")) {
                    listOfTokens.add(token);
                }
                token = String.valueOf(line.charAt(index));
                listOfTokens.add(token);
                index = index + 1;
                token = "";
            } else if (String.valueOf(line.charAt(index)).equals(" ")) {
                if (!token.equals("")) {
                    listOfTokens.add(token);
                }
                index++;
                token = "";

            } else {
                token += String.valueOf(line.charAt(index));
                index++;
            }

        }
        return listOfTokens;
    }

    public List<Pair<String, Integer>> getCodificationTable() {
        return this.languageSpecification.getCodificationTable();
    }

    public LanguageSpecification getLanguageSpecification() {
        return this.languageSpecification;
    }
}
