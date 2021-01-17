public class Calculator {
    private static final String errorMessage = "Ошибка ввода данных!";
    private static final String[] arrayRomeNumbers = {
            "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
    private static final String[] arrayArabicNumbers = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
            "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
            "51", "52", "53", "54", "55", "56", "57", "58", "59", "60",
            "61", "62", "63", "64", "65", "66", "67", "68", "69", "70",
            "71", "72", "73", "74", "75", "76", "77", "78", "79", "80",
            "81", "82", "83", "84", "85", "86", "87", "88", "89", "90",
            "91", "92", "93", "94", "95", "96", "97", "98", "99", "100"};

    public static String calculate(String line) throws DataException {
        StringBuilder startNumber1 = new StringBuilder();
        StringBuilder startNumber2 = new StringBuilder();
        char operator = '+';
        boolean isLeftPart = true;

        if (line.equals("exit")) {
            return line;
        }

        for (int i = 0; i < line.length(); ++i) {
            if (Character.toString(line.charAt(i)).equals("+") || Character.toString(line.charAt(i)).equals("-") || Character.toString(line.charAt(i)).equals("*") || Character.toString(line.charAt(i)).equals("/")) {
                operator = line.charAt(i);
                isLeftPart = false;
            } else if (isLeftPart) {
                startNumber1.append(line.charAt(i));
            } else {
                startNumber2.append(line.charAt(i));
            }
        }

        String numberType = defineNumberType(startNumber1.toString(), startNumber2.toString()); // Определение типа числа и проверка что оба числа одного типа

        if (numberType.equals(errorMessage)) {
            throw new DataException(errorMessage);
        } else if (numberType.equals("romeNumbers")) {  // Если числа римские, конвертируем их в арабские
            startNumber1 = new StringBuilder(convertRomeNumberToArabicNumber(startNumber1.toString()));
            startNumber2 = new StringBuilder(convertRomeNumberToArabicNumber(startNumber2.toString()));
        }

        int finishNumber1 = Integer.parseInt(startNumber1.toString());    // Преобразование из строки в число
        int finishNumber2 = Integer.parseInt(startNumber2.toString());    // Преобразование из строки в число

        if (!(isCorrectNumber(finishNumber1) && isCorrectNumber(finishNumber2))) { //   Проверка что числа от 1 до 10
            throw new DataException(errorMessage);
        }

        int result;

        if (operator == '+') {
            result = finishNumber1 + finishNumber2;
        } else if (operator == '-') {
            result = finishNumber1 - finishNumber2;
        } else if (operator == '*') {
            result = finishNumber1 * finishNumber2;
        } else if (operator == '/') {
            result = finishNumber1 / finishNumber2;
        } else {
            throw new DataException(errorMessage);
        }

        if (numberType.equals("romeNumbers")) {
            return convertArabicNumberToRomeNumber(result);
        }

        return Integer.toString(result);
    }

    private static boolean isCorrectNumber(int number) {
        return number >= 1 && number <= 10;
    }

    private static String defineNumberType(String number1, String number2) {    // Определение римские или арабские, и что оба числа одного типа

        String numberType = errorMessage;

        for (String i : Calculator.arrayRomeNumbers) {
            if (i.equals(number1)) {
                for (String j : Calculator.arrayRomeNumbers) {
                    if (j.equals(number2)) {
                        numberType = "romeNumbers";
                        break;
                    }
                }
            }
        }

        for (String i : Calculator.arrayArabicNumbers) {
            if (i.equals(number1)) {
                for (String j : Calculator.arrayArabicNumbers) {
                    if (j.equals(number2)) {
                        numberType = "arabicNumbers";
                        break;
                    }
                }
            }
        }

        return numberType;
    }

    private static String convertRomeNumberToArabicNumber(String number) {  // Конвертирует римские числа в арабские
        String result = "";

        for (int i = 0; i < Calculator.arrayRomeNumbers.length; ++i) {
            if (Calculator.arrayRomeNumbers[i].equals(number)) {
                result = Calculator.arrayArabicNumbers[i];
            }
        }

        return result;
    }

    private static String convertArabicNumberToRomeNumber(int number) { // Конвертируем арабские числа в римские
        String result = "";

        for (int i = 0; i < Calculator.arrayArabicNumbers.length; ++i) {
            if (Calculator.arrayArabicNumbers[i].equals(Integer.toString(number))) {
                result = Calculator.arrayRomeNumbers[i];
            }
        }

        return result;
    }
}

