package DFA;

import org.apache.commons.math3.distribution.UniformIntegerDistribution;

import java.util.ArrayList;
import java.util.List;

public class AutomationImpl implements Automation {

    private final ArrayList<String> alphabet;

    public AutomationImpl() {
        alphabet = new ArrayList<>();
        alphabet.add("([ab]+)");
    }

    @Override
    public void checkString(String ex) {

        if (ex.length() == 1) {
            System.out.println("Из данной грамматики невозможно составить строку длиной 1");
            return;
        }
        if (!isAlphabet(ex)) {
            System.out.println("Строка содержит символы не из алфавита");
            return;
        }
        if (ex.equals("aa") || ex.equals("aba") || ex.equals("abba")) {
            System.out.println("Строка соответствует");
            return;
        }
        if(ex.length() <= 4) {
            System.out.println("строка не соответствует");
            return;
        }
        String S1 = "abSb";
        String S2 = "aA";
        String A1 = "bA";
        String A2 = "a";

        String result = "";
        String tmp = ex.substring(2, 3);
        if (tmp.equals("b")) {
            result = S2;
            while (result.length() < ex.length()) {
                result = result.replace("A", A1);
            }
            result = result.replace("A", A2);
        } else if (tmp.equals("a")) {
            result = S1;
            for (int i = ex.length() - 2; i >= 0; i--) {
                tmp = ex.substring(i, i + 1);
                if (tmp.equals("b")) {
                    result = result.replace("S", S1);
                } else if (tmp.equals("a")) {
                    result = result.replace("S", S2);
                    while (result.length() < ex.length()) {
                        result = result.replace("A", A1);
                    }
                    result = result.replace("A", A2);
                    break;
                }
            }
        }
        if (ex.equals(result)) {
            System.out.println("Строка соответствует");
        } else {
            System.out.println("Строка не соответствует");
        }

    }

    @Override
    public String generateString(int quantity) {
        switch (quantity) {
            case 0 -> {
                return "Из данной грамматики невозможно составить строку длиной 0";
            }
            case 1 -> {
                return "Из данной грамматики невозможно составить строку длиной 1";
            }
            case 2 -> {
                return "aa";
            }
            case 3 -> {
                return "aba";
            }
            case 4 -> {
                return "abba";
            }
            default -> {
                UniformIntegerDistribution dist = new UniformIntegerDistribution(1, 2);
                String tmp = "S";
                switch (dist.sample()) {
                    case 1 -> {
                        int n = (quantity - 2) / 3;
                        if ((quantity - 2) % 3 == 0) {
                            for (int i = 0; i < n; i++) {
                                tmp = tmp.replace("S", "abSb");
                            }
                            tmp = tmp.replace("S", "aA");
                            tmp = tmp.replace("A", "a");
                        } else {
                            for (int i = 0; i < n; i++) {
                                tmp = tmp.replace("S", "abSb");
                            }
                            tmp = tmp.replace("S", "aA");
                            while (tmp.length() < quantity) {
                                tmp = tmp.replace("A", "bA");
                            }
                            tmp = tmp.replace("A", "a");
                        }
                    }
                    case 2 -> {
                        tmp = tmp.replace("S", "aA");
                        while (tmp.length() < quantity) {
                            tmp = tmp.replace("A", "bA");
                        }
                        tmp = tmp.replace("A", "a");
                    }
                    default -> {
                    }
                }
                return tmp;
            }
        }
    }

    private boolean isAlphabet(String ex) {
        List<Boolean> val = alphabet.stream().map(ex::matches).toList();
        return val.contains(true);
    }
}
