package utils;

import java.io.IOException;

public class AllureReportLauncher {

    public static void generateAndOpenReport() {
        try {
            // Generate the report
            ProcessBuilder generateReport = new ProcessBuilder(
                "cmd.exe", "/c", "allure generate allure-results -o allure-report --clean"
            );
            generateReport.inheritIO().start().waitFor();

            // Open the report
            ProcessBuilder openReport = new ProcessBuilder(
                "cmd.exe", "/c", "allure open allure-report"
            );
            openReport.inheritIO().start();

            System.out.println(" Allure Report generated and launched successfully.");
        } catch (IOException | InterruptedException e) {
            System.err.println(" Failed to generate or open Allure Report.");
            e.printStackTrace();
        }
    }
}
