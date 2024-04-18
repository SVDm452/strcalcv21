import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String result = calc(input);
        if (result.length() <= 40) {
            System.out.println("\"" + result + "\"");
        } else {
            String result1 = result.substring(0, 39);
            System.out.println("\"" + result1 + "...\"");
        }

    }
    public static String calc(String input) throws Exception {

        String result = null;
        String operand = findOperation(input);
        int x = input.indexOf(operand);
        String p1 = input.substring(0, x - 1);
        String p2 = input.substring(x + 2);
        //System.out.println(str1);
        //System.out.println(str2);
        int l1 = p1.length();
        int l2 = p2.length();
        int st2;

        if (p1.contains("\"")) {
            String str1 = p1.replace("\"", "");
            if (l1 <= 12 && l2 <= 12) {
                switch (operand) {
                    case "+":
                        String str2 = p2.replace("\"", "");
                        result = str1 + str2;
                        break;
                    case "-":
                        str2 = p2.replace("\"", "");
                        if (str1.contains(str2)) {
                            int i1 = str1.indexOf(str2);
                            String s1 = str1.substring(0, i1);
                            result = s1;
                        } else {
                            result = str1;
                        }
                        break;
                    case "*":
                        if (p2.contains("\"")) { throw new Exception("Строку можно умножать только на числа");}
                        else {
                            st2 = parseInt(p2);
                            if (st2 >= 1 && st2 <= 10) {
                                result = str1.repeat(st2);
                            } else throw new Exception("Строку можно умножать только на числа от 1 до 10");
                        }
                        break;
                    case "/":
                        if (p2.contains("\"")) { throw new Exception("Строку можно делить только на числа");}
                        else {
                            st2 = parseInt(p2);
                            if (st2 >= 1 && st2 <= 10) {
                                int newLen = (l1-2) / st2;
                                result = str1.substring(0, newLen);
                            } else throw new Exception("Строку можно делить только на числа от 1 до 10");
                        }
                        break;
                }
            } else throw new Exception("Вводимые строки больше 10 символов");
        } else throw new Exception("Первым аргументом должна быть строка");
        
        return result;
    }


    static String findOperation(String input) throws Exception {
        if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("*")) return "*";
        else if (input.contains("/")) return "/";
        else throw new Exception("Несоответствие арифметической операции");
    }
}