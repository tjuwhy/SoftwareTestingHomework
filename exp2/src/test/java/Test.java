import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Test {

    WebDriver driver;
    Map<String, String> map = new HashMap<String, String>();

    @Before
    public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://103.120.226.190/selenium-demo/git-repo");
        driver.manage().window().setSize(new Dimension(974, 1080));

        readExcel();
    }

    @org.junit.Test
    public void test() {
        for (String key : map.keySet()) {
            driver.findElement(By.name("user_number")).sendKeys(key);
            driver.findElement(By.name("password")).sendKeys(map.get(key));
            driver.findElement(By.cssSelector(".btn")).click();
            System.out.println(map.get(key));
            System.out.println(driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div[2]/div/form/div[5]/code")).getText());
            System.out.println("key：" + key + ":" + map.get(key).equals(driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div[2]/div/form/div[5]/code")).getText()));
            Assert.assertTrue(map.get(key).equals(driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div[2]/div/form/div[5]/code")).getText()));
        }
    }

    public void readExcel() throws IOException {
        String file = "Selenium+Lab2020.xlsx";
        File f = new File(file);
        if (!f.exists()) {
            System.out.println("文件不存在");
        }
        FileInputStream fi = new FileInputStream(f);
        Workbook wb = getWorkbook(fi, "xlsx");
        for (int sheetNum = 0; sheetNum < wb.getNumberOfSheets(); sheetNum++) {
            Sheet sheet = wb.getSheetAt(sheetNum);
            if (sheet == null) {
                continue;
            }
            int rowStart = sheet.getFirstRowNum();
            int rowEnd = sheet.getPhysicalNumberOfRows();
            for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
                Row r = sheet.getRow(rowNum);
                if (r == null) {
                    continue;
                }
                if (!r.getCell(1).getStringCellValue().equals("")) {
                    map.put(r.getCell(1).getStringCellValue(), r.getCell(2).getStringCellValue());
                }
            }
        }
    }

    public static Workbook getWorkbook(InputStream inputStream, String fileType) throws IOException {
        Workbook workbook = null;
        if (fileType.equalsIgnoreCase("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileType.equalsIgnoreCase("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }
}



