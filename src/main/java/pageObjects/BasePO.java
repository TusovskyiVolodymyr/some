package pageObjects;

import annotations.Instance;
import driver.WebDriverManager;
import elements.CustomFieldDecorator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import utils.PropertiesUtill;

import java.io.IOException;
import java.util.Arrays;

public abstract class BasePO {

    private static Logger log = LogManager.getLogger(BasePO.class);
    protected PropertiesUtill utill;

    protected BasePO() {
        PageFactory.initElements(new CustomFieldDecorator(WebDriverManager.getDriver()), this);
        try {
            utill = new PropertiesUtill();
        } catch (IOException e) {
            log.error(Arrays.toString(e.getStackTrace()));
        }
        Instance.create(this);
    }
}
