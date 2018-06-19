package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchResultsPO extends BasePO {

    public enum SearchType {

        ALL("All"), POSTS("Posts"), PEOPLE("People"), PHOTOS("Photos"), VIDEOS("Videos"), PAGES("Pages"),
        PLACES("Places"), GROUPS("Groups"), APPS("Apps");

        private String type;

        SearchType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public SearchResultsPO act_chooseSearchType(SearchType searchType) {
        WebElement type = driver.findElement(By.xpath("//*[@aria-label='Search Results']//*[contains(text(),'" + searchType.getType() + "')]"));
        click(type);
        return this;
    }
}