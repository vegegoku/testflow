package org.dominokit.samples;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexJustifyContent;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.utils.DominoElement;
import org.dominokit.samples.mvp.SearchView;

import static org.jboss.elemento.Elements.h;

public class SearchViewImpl implements SearchView {

    private final DominoElement<HTMLDivElement> nameElement;
    private final DominoElement<HTMLDivElement> statusElement;
    private final NavigationComponent navigationComponent;
    private HTMLDivElement element;

    public SearchViewImpl(SearchViewUiHandlers uiHandlers) {
        nameElement = DominoElement.div();
        statusElement = DominoElement.div();
        navigationComponent = new NavigationComponent(uiHandlers);
        element = Card.create()
                .css("demo-card")
                .appendChild(Row.create()
                        .appendChild(Column.span2()
                                .appendChild(navigationComponent)
                        )
                        .appendChild(Column.span10()
                                .appendChild(FlexLayout.create()
                                        .setJustifyContent(FlexJustifyContent.SPACE_AROUND)
                                        .appendChild(FlexItem.create()
                                                .appendChild(nameElement
                                                        .css("page-content", "search-title"))
                                        )
                                        .appendChild(FlexItem.create()
                                                .appendChild(statusElement
                                                        .css("page-content", "search-title"))
                                        )
                                )
                        )
                )
                .element();
    }

    @Override
    public void setName(String name) {
        nameElement.setTextContent(name);
        navigationComponent.getNameBox().setValue(name);
    }

    @Override
    public void setStatus(String status) {
        statusElement.setTextContent(status);
        navigationComponent.getStatusSelect().setValue(status);
    }

    @Override
    public HTMLDivElement getContent() {
        return element;
    }
}
