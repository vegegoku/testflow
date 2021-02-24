package org.dominokit.samples;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.forms.Select;
import org.dominokit.domino.ui.forms.SelectOption;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.grid.flex.FlexDirection;
import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexJustifyContent;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.samples.mvp.HasNavigationHandlers;

public class NavigationComponent extends BaseDominoElement<HTMLDivElement, NavigationComponent> {

    private HTMLDivElement root;
    private final TextBox nameBox;
    private final Select<String> statusSelect;

    public NavigationComponent(HasNavigationHandlers uiHandlers) {
        nameBox = TextBox.create("name")
                .value("DominoKit");
        statusSelect = Select.<String>create("status")
                .appendChild(SelectOption.create("active", "active"))
                .appendChild(SelectOption.create("inactive", "inactive"))
                .selectAt(0);

        root= FlexLayout.create()
                .setHeight("500px")
                .setDirection(FlexDirection.TOP_TO_BOTTOM)
                .setJustifyContent(FlexJustifyContent.SPACE_BETWEEN)
                .appendChild(FlexItem.create()
                        .appendChild(Button.create("Back home")
                                .addClickListener(evt -> uiHandlers.onGoHome())
                        )
                )
                .appendChild(FlexItem.create()
                        .appendChild(Button.create("Navigate to page 1")
                                .addClickListener(evt -> uiHandlers.onPageSelected(1))
                        )
                )
                .appendChild(FlexItem.create()
                        .appendChild(Button.create("Navigate to page 2")
                                .addClickListener(evt -> uiHandlers.onPageSelected(2))
                        )
                )
                .appendChild(FlexItem.create()
                        .appendChild(Button.create("Navigate to page 3")
                                .addClickListener(evt -> uiHandlers.onPageSelected(3))
                        )
                )
                .appendChild(FlexItem.create()
                        .appendChild(nameBox)
                )
                .appendChild(FlexItem.create()
                        .appendChild(statusSelect)
                )
                .appendChild(FlexItem.create()
                        .appendChild(Button.create("Navigate with query params")
                                .addClickListener(evt -> uiHandlers.onSearch(nameBox.getValue(), statusSelect.getValue()))
                        )
                ).element();

        init(this);
    }

    public TextBox getNameBox() {
        return nameBox;
    }

    public Select<String> getStatusSelect() {
        return statusSelect;
    }

    @Override
    public HTMLDivElement element() {
        return root;
    }
}
