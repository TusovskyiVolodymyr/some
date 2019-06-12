package pageObjects;

import org.openqa.selenium.By;
import utils.Verify;

public class SearchPO extends HeaderPO {

    private enum SearchElements {
        IPF_SEARCH(By.xpath("//*[@name='q']")),
        BTN_SEARCH(By.xpath("//*[@aria-label='Search' and @type='submit']"));

        private By by;

        SearchElements(By by) {
            this.by = by;
        }

        public By get() {
            return by;
        }

    }

    public SearchPO() {
        super();
    }


    public SearchPO act_typeSearchWorld(String text) {
        typeText(SearchElements.IPF_SEARCH.get(), text);
        return this;
    }

    public SearchPO act_clickSearchButton() {
        click(SearchElements.BTN_SEARCH.get());
        return this;
    }

    public SearchPO ver_textTyped(String text) {
        Verify.isTrue(getInputValue(SearchElements.IPF_SEARCH.get()).equals(text), String.format("Text [%s] is typed is search field", text));
        return this;
    }
}
