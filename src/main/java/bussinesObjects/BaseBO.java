package bussinesObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjects.BasePO;
import utils.Instance;

public abstract class BaseBO {

    protected BaseBO() {
        Instance.create(this);
    }
}
