package org.dominokit.samples;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.utils.DominoElement;
import org.dominokit.samples.mvp.HomeView;

public class HomeViewImpl implements HomeView {

    private HTMLDivElement element;

    public HomeViewImpl(HomeViewUiHandlers uiHandlers) {
        element = Card.create()
                .css("demo-card")
                .appendChild(Row.create()
                        .appendChild(Column.span2()
                                .appendChild(new NavigationComponent(uiHandlers))
                        )
                        .appendChild(Column.span10()
                                .appendChild(DominoElement.div()
                                        .css("page-content", "home-title")
                                        .setTextContent("--HOME--"))
                        )
                )
                .element();
    }

    @Override
    public HTMLDivElement getContent() {
        return element;
    }
}
