package org.dominokit.samples.mvp;

import org.dominokit.domino.history.AppHistory;
import org.dominokit.domino.history.TokenFilter;

public class MiniMVP {

    private final RootPanel rootPanel;
    private AppHistory history;

    public MiniMVP(RootPanel rootPanel, AppHistory history) {
        this.rootPanel = rootPanel;
        this.history = history;

        history.listen(TokenFilter.exactMatch("home"), state -> activatePresenter(HomePresenter.INSTANCE));

        history.listen(TokenFilter.startsWithPathFilter("page/:pageIndex"), state -> {
            PagePresenter.INSTANCE.setPageIndex(Integer.parseInt(state.normalizedToken().getPathParameter("pageIndex")));
            activatePresenter(PagePresenter.INSTANCE);
        });
        history.listen(TokenFilter.startsWithPathFilter("search/employee"), state -> {
            SearchPresenter.INSTANCE.setSearchParams(state.token().getQueryParameter("name"), state.token().getQueryParameter("status"));
            activatePresenter(SearchPresenter.INSTANCE);
        });
    }

    public void run() {
        if (history.currentToken().isEmpty()) {
            history.fireState("home", "HOME");
        } else {
            history.fireCurrentStateHistory();
        }
    }

    private void activatePresenter(Presenter<?> presenter) {
        rootPanel.appendView(presenter.getView());
    }

    public <V extends View<?>> void bindView(Presenter<V> presenter, V view) {
        presenter.injectView(view);
        presenter.setHistory(history);
    }

    public interface View<T> {
        T getContent();
    }

    public interface Presenter<V extends View<?>> {
        void injectView(V view);

        View<?> getView();

        void setHistory(AppHistory history);
    }

    public interface RootPanel {
        void clear();

        void appendView(View<?> view);
    }
}
