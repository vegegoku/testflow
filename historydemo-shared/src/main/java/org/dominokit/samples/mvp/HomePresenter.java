package org.dominokit.samples.mvp;

import org.dominokit.domino.history.AppHistory;

public class HomePresenter implements MiniMVP.Presenter<HomeView<?>>, HomeView.HomeViewUiHandlers {

    private HomeView view;
    public final static HomePresenter INSTANCE = new HomePresenter();
    private AppHistory history;

    HomePresenter() {
    }


    @Override
    public void onGoHome() {
        this.history.fireState("home", "HOME");
    }

    @Override
    public void onSearch(String name, String status) {
        history.fireState(history.currentToken()
                .clear()
                .appendPath("search/employee")
                .appendParameter("name", name)
                .appendParameter("status", status)
                , "Search - employee"
        );
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
    public void injectView(HomeView view) {
        this.view = view;
    }

    @Override
    public void onPageSelected(int index) {
        this.history.fireState(history.currentToken()
                .clear()
                .appendPath("page")
                .appendPath(index + ""),
                "PAGE "+index);
    }
}
