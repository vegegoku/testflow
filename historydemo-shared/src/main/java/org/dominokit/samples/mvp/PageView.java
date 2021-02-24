package org.dominokit.samples.mvp;

public interface PageView<T> extends MiniMVP.View<T> {

    void setPageIndex(int index);

    interface PageViewUiHandlers extends HasNavigationHandlers{

    }
}
