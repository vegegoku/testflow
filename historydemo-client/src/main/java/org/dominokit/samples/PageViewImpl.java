package org.dominokit.samples;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.Typography.Paragraph;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.style.Styles;
import org.dominokit.domino.ui.utils.DominoElement;
import org.dominokit.samples.mvp.PageView;

public class PageViewImpl implements PageView {

    private final DominoElement<HTMLDivElement> pageElement;
    private HTMLDivElement element;

    public PageViewImpl(PageViewUiHandlers uiHandlers) {

        pageElement = DominoElement.div();
        element = Card.create()
                .css("demo-card")
                .appendChild(Row.create()
                        .appendChild(Column.span2()
                                .appendChild(new NavigationComponent(uiHandlers))
                        )
                        .appendChild(Column.span10()
                                .appendChild(pageElement
                                        .css("page-content", "page-title"))
                        )
                )
                .element();
    }

    @Override
    public void setPageIndex(int index) {
        pageElement.setTextContent("--PAGE."+index+"--");
    }

    @Override
    public HTMLDivElement getContent() {
        return element;
    }
}
