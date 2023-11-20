import java.io.*;
import java.util.Stack;

public class SymbolChecker {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java SymbolChecker <MyDate.java>");
            System.exit(1);
        }

        String fileName = args[0];
        checkSymbols(fileName);
    }

    public static void checkSymbols(String fileName) throws IOException {
        Stack<Character> stack = new Stack<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int c;
            while ((c = reader.read()) != -1) {
                char ch = (char) c;
                switch (ch) {
                    case '(':
                    case '{':
                    case '[':
                        stack.push(ch);
                        break;
                    case ')':
                        if (stack.isEmpty() || stack.pop() != '(') {
                            error();
                        }
                        break;
                    case '}':
                        if (stack.isEmpty() || stack.pop() != '{') {
                            error();
                        }
                        break;
                    case ']':
                        if (stack.isEmpty() || stack.pop() != '[') {
                            error();
                        }
                        break;
                }
            }
        }

        if (!stack.isEmpty()) {
            error();
        }

        System.out.println("The source code file has correctly paired grouping symbols.");
    }

    private static void error() {
        System.out.println("Error: There are unnmatched grouping symbols.");
        System.exit(1);
    }
}
