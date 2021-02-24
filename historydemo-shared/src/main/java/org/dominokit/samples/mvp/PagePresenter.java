package org.dominokit.samples.mvp;

import org.dominokit.domino.history.AppHistory;

public class PagePresenter implements MiniMVP.Presenter<PageView<?>>, PageView.PageViewUiHandlers {

    private PageView view;
    public final static PagePresenter INSTANCE = new PagePresenter();
    private AppHistory history;

    PagePresenter() {
    }

    public void setPageIndex(int index) {
        view.setPageIndex(index);
    }

    @Override
    public void setHistory(AppHistory history) {
        this.history = history;
    }

    @Override
    public MiniMVP.View<?> getView() {
        return view;
    }

    @Override
    public void injectView(PageView view) {
        this.view = view;
    }

    @Override
    public void onGoHome() {
        history.fireState("home", "HOME");
    }

    @Override
    public void onPageSelected(int pageIndex) {
        history.fireState(history.currentToken()
                        .clear()
                        .appendPath("page")
                        .appendPath(pageIndex + ""),
                "PAGE " + pageIndex);
    }

    @Override
    public void onSearch(String name, String status) {
        history.fireState(history.currentToken()
                        .clear()
                        .appendPath("search/employee")
                        .appendParameter("name", name)
                        .appendParameter("status", status)
                , "Search - Employee"
        );
    }
}
