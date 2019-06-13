package bussinesObjects.androidBussinesObjects;

import annotations.Instance;
import utils.LoggerWrapper;

public abstract class BaseBO {
    protected LoggerWrapper log = LoggerWrapper.getLogger(this.getClass());

    protected BaseBO() {
        Instance.create(this);
    }
}
