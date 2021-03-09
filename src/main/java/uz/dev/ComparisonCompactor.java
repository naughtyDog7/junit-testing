package uz.dev;

public class ComparisonCompactor {

    private static final String ELLIPSIS = "...";
    private static final String DELTA_END = "]";
    private static final String DELTA_START = "[";

    private final int contextLength;
    private final String expected;
    private final String actual;
    private int prefixLength;
    private int suffixLength;

    public ComparisonCompactor(int contextLength,
                               String expected,
                               String actual) {
        this.contextLength = contextLength;
        this.expected = expected;
        this.actual = actual;
    }

    public String formatCompactedComparison(String message) {
        if (canBeCompacted()) {
            findCommonPrefixAndSuffix();
            String compactExpected = compact(expected);
            String compactActual = compact(actual);
            return Assert.format(message, compactExpected, compactActual);
        } else {
            return Assert.format(message, expected, actual);
        }
    }

    private boolean canBeCompacted() {
        return !shouldNotBeCompacted();
    }

    private boolean shouldNotBeCompacted() {
        return expected == null || actual == null || areStringsEqual();
    }

    private void findCommonPrefixAndSuffix() {
        findCommonPrefix();
        suffixLength = 0;
        for (; !suffixOverlapsPrefix(suffixLength); suffixLength++) {
            if (charFromEnd(expected, suffixLength) != charFromEnd(actual, suffixLength))
                break;
        }
    }

    private void findCommonPrefix() {
        prefixLength = 0;
        int end = Math.min(expected.length(), actual.length());
        for (; prefixLength < end; prefixLength++) {
            if (expected.charAt(prefixLength) != actual.charAt(prefixLength))
                break;
        }
    }

    private boolean suffixOverlapsPrefix(int suffixLength) {
        return Math.min(actual.length() - suffixLength, expected.length() - suffixLength)
                <= prefixLength;
    }

    private char charFromEnd(String s, int i) {
        return s.charAt(s.length() - 1 - i);
    }

    private String compact(String s) {
        return startingEllipsis() +
                startingContext() +
                DELTA_START +
                delta(s) +
                DELTA_END +
                endingContext() +
                endingEllipsis();
    }

    private String startingEllipsis() {
        return prefixLength > contextLength ? ELLIPSIS : "";
    }

    private String startingContext() {
        int contextStart = Math.max(0, prefixLength - contextLength);
        int contextEnd = prefixLength;
        return expected.substring(contextStart, contextEnd);
    }

    private String delta(String s) {
        int deltaStart = prefixLength;
        int deltaEnd = s.length() - suffixLength;
        return s.substring(deltaStart, deltaEnd);
    }

    private String endingContext() {
        int contextStart = expected.length() - suffixLength;
        int contextEnd = Math.min(contextStart + contextLength, expected.length());
        return expected.substring(contextStart, contextEnd);
    }

    private String endingEllipsis() {
        return suffixLength > contextLength ? ELLIPSIS : "";
    }

    private boolean areStringsEqual() {
        return expected.equals(actual);
    }

    private static class Assert {
        public static String format(String message, String expected, String actual) {
            message = message == null
                    ? ""
                    : message + " ";
            return message + "expected:<" + expected + "> but was:<" + actual + ">";
        }
    }
}
