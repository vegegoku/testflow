package org.dominokit.samples;

import com.google.gwt.core.client.EntryPoint;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLElement;
import jsinterop.base.Js;
import org.dominokit.domino.gwt.history.StateHistory;
import org.dominokit.domino.ui.utils.DominoElement;
import org.dominokit.samples.mvp.HomePresenter;
import org.dominokit.samples.mvp.MiniMVP;
import org.dominokit.samples.mvp.MiniMVP.View;
import org.dominokit.samples.mvp.PagePresenter;
import org.dominokit.samples.mvp.SearchPresenter;

import static java.util.Objects.nonNull;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        MiniMVP miniMVP = new MiniMVP(new GwtRootPanel(), new StateHistory());
        miniMVP.bindView(HomePresenter.INSTANCE, new HomeViewImpl(HomePresenter.INSTANCE));
        miniMVP.bindView(PagePresenter.INSTANCE, new PageViewImpl(PagePresenter.INSTANCE));
        miniMVP.bindView(SearchPresenter.INSTANCE, new SearchViewImpl(SearchPresenter.INSTANCE));

        miniMVP.run();

    }

    public static class GwtRootPanel implements MiniMVP.RootPanel{

        private View currentView;

        @Override
        public void clear() {
            if(nonNull(currentView)){
                DominoElement.of(Js.<HTMLElement>uncheckedCast(currentView.getContent())).remove();
            }
        }

        @Override
        public void appendView(View view) {
            clear();
            DomGlobal.document.body.appendChild(Js.uncheckedCast(view.getContent()));
            this.currentView = view;
        }
    }
}
