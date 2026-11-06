package com.pluralsight;
// reusable display utility
public class TerminalUI {
    private static final String LEFT_MARKER = "<|:|>";
    private static final String RIGHT_MARKER = "<|:|>";

    public static void printSeparator(int totalWidth) {
        int separatorWidth = totalWidth - LEFT_MARKER.length() - RIGHT_MARKER.length();

        System.out.println();
        System.out.println(LEFT_MARKER + "=".repeat(separatorWidth) + RIGHT_MARKER);
        System.out.println();
    }
}
