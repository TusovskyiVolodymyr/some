package pageObjects;

import org.openqa.selenium.By;
import utils.StringUtils;

public class SearchResultsPO extends BasePO {

    private enum SearchElements {
        DIV_SEARCH_TYPE(By.xpath("//*[@aria-label='Search Results']//*[contains(text(),'%s')]"));

        private By by;

        SearchElements(By by) {
            this.by = by;
        }

        public By get() {
            return by;
        }

        public By getWithParams(Object... params) {
            return StringUtils.getLocator(by, params);
        }

    }

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
        click(SearchElements.DIV_SEARCH_TYPE.getWithParams(searchType.getType()));
        return this;
    }
}