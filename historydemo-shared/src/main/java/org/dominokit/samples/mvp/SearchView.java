package org.dominokit.samples.mvp;

public interface SearchView<T> extends MiniMVP.View<T> {

    void setName(String name);

    void setStatus(String status);

    interface SearchViewUiHandlers extends HasNavigationHandlers {
    }
}
