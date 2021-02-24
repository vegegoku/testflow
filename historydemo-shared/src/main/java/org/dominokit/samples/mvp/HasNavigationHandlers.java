package org.dominokit.samples.mvp;

public interface HasNavigationHandlers {
    void onGoHome();
    void onPageSelected(int pageIndex);

    void onSearch(String name, String status);
}
