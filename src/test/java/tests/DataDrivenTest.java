package tests;

import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.DriverFactory;
import utils.ExcelReader;
import utils.TestDataThreadManager;

import java.util.List;
import java.util.Map;

public class DataDrivenTest extends BaseTest {

    @DataProvider(name = "excelData", parallel = true)
    public Object[][] getData() {
        List<Map<String, String>> dataList = ExcelReader.getTestData("Sheet1");
        Object[][] dataArray = new Object[dataList.size()][1];
        for (int i = 0; i < dataList.size(); i++) {
            dataArray[i][0] = dataList.get(i);
        }
        return dataArray;
    }

    @Test(dataProvider = "excelData")
    public void runTest(Map<String, String> data) {
        TestDataThreadManager.setData(data);

        String browser = data.get("Browser");
        String url = data.get("URL");
        String searchTerm = data.get("SearchTerm");

        DriverFactory.initDriver(browser);
        DriverFactory.getDriver().get(url);

        System.out.println("Running on: " + browser + " | URL: " + url + " | Search: " + searchTerm);

        // Add page interactions here...

        DriverFactory.quitDriver();
        TestDataThreadManager.removeData();
    }
}

