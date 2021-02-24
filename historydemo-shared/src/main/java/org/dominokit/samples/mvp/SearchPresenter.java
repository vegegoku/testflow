package org.dominokit.samples.mvp;

import org.dominokit.domino.history.AppHistory;
import org.dominokit.domino.history.TokenParameter;

import java.util.logging.Logger;

public class SearchPresenter implements MiniMVP.Presenter<SearchView<?>>, SearchView.SearchViewUiHandlers {

    private static final Logger LOGGER = Logger.getLogger(SearchPresenter.class.getName());

    private SearchView view;
    public final static SearchPresenter INSTANCE = new SearchPresenter();
    private AppHistory history;

    SearchPresenter() {
    }

    @Override
    public void setHistory(AppHistory history) {
        this.history = history;
    }

    public void setSearchParams(String name, String status){
        view.setName(name);
        view.setStatus(status);
    }

    @Override
    public MiniMVP.View<?> getView() {
        return view;
    }

    @Override
    public void injectView(SearchView view) {
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

    @Override
    public void onGoHome() {
        this.history.fireState("home", "HOME");
    }

    @Override
    public void onSearch(String name, String status) {
        history.fireState("search/employee?name=:name&status=:status",
                TokenParameter.of("name", name),
                TokenParameter.of("status", status));
    }
}
