package com.pluralsight;

public class StartupScreen {
    public static void display() {
        System.out.println("""
                
                ==============================================================
                
                                  GALACTIC LEDGER
                             SHIPBOARD CREDIT REGISTRY
                
                --------------------------------------------------------------
                
                                      .-.
                                      | |
                                   .--| |--.
                                  /  .---.  \\
                                 |  | o o |  |
                                 |  |  ^  |  |
                                 |  '-----'  |
                                 | .-------. |
                                 | | R2-67 | |
                                 | | [|||] | |
                                 | '-------' |
                                 /|_________|\\
                                /_/         \\_\\
                
                                  R2-67 // UNIT 67
                            FINANCIAL ARCHIVE ASSISTANT
                
                       "All credits accounted for. Probably."
                
                --------------------------------------------------------------
                
                                  SYSTEMS ONLINE
                                  ARCHIVE READY
                
                ==============================================================
                """);
    }

    public static void animateSeparator() {
        String left = "<|:|>";
        String right = "<|:|>";
        int totalWidth = 38;
        // this was a fun leetcode style solution
        for (int i = 0; i <= totalWidth; i++) {
            String separator = "=".repeat(i)
                    + ".".repeat(totalWidth - i);

            System.out.print("\r" + left + separator + right);

            try {
                Thread.sleep(67);
            } catch (InterruptedException e) {
                return;
            }
        }
        System.out.println();
    }
}
