package selenide;

public abstract class PageBase {

    public static final long PAGE_CHECK_TIMEOUT = 10000;

    public PageBase() {
        check();
    }

    protected abstract void check();
}