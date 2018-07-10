package bussinesObjects;

import annotations.Instance;

public abstract class BaseBO {

    protected BaseBO() {
        Instance.create(this);
    }
}
