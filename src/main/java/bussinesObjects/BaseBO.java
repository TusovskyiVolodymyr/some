package bussinesObjects;

import utils.Instance;

public abstract class BaseBO {

    protected BaseBO() {
        Instance.create(this);
    }
}
