package com.pluralsight;

public class StartupScreen {
    public static void display() {
        System.out.println("""
                
                ================================================================
                
                                  GALACTIC LEDGER
                             SHIPBOARD CREDIT REGISTRY
                
                ----------------------------------------------------------------
                
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
                
                ----------------------------------------------------------------
                
                                  SYSTEMS ONLINE
                                  ARCHIVE READY
                
                ================================================================
                """);
    }

    public static void animateSeparator() throws InterruptedException {
        String[] frames = {
                "<|:|>..............................<|:|>",
                "<|:|>====..........................<|:|>",
                "<|:|>==========....................<|:|>",
                "<|:|>================..............<|:|>",
                "<|:|>==========================....<|:|>",
                "<|:|>================================<|:|>"
        };

        for (String frame : frames) {
            System.out.print("\r" + frame);
            Thread.sleep(120);
        }

        System.out.println();
    }
}
