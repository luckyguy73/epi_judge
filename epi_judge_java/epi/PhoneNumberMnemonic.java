package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PhoneNumberMnemonic {

    @EpiTest(testDataFile = "phone_number_mnemonic.tsv")
    public static List<String> phoneMnemonic(String phoneNumber) {
        List<String> mnemonics = new ArrayList<>();
        phoneMnemonic(phoneNumber, 0, new char[phoneNumber.length()], mnemonics);
        return mnemonics;
    }

    private static final String[] MAPPING = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

    private static void phoneMnemonic(String phoneNumber, int digit, char[] partial, List<String> mnemonics) {
        if (digit == phoneNumber.length()) mnemonics.add(new String(partial));
        else
            for (int i = 0; i < MAPPING[phoneNumber.charAt(digit) - '0'].length(); ++i) {
                char c = MAPPING[phoneNumber.charAt(digit) - '0'].charAt(i);
                partial[digit] = c;
                phoneMnemonic(phoneNumber, digit + 1, partial, mnemonics);
            }
    }

    @EpiTestComparator
    public static boolean comp(List<String> expected, List<String> result) {
        if (result == null) return false;
        Collections.sort(expected);
        Collections.sort(result);
        return expected.equals(result);
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "PhoneNumberMnemonic.java", new Object() {
        }.getClass().getEnclosingClass()).ordinal());
    }

}
