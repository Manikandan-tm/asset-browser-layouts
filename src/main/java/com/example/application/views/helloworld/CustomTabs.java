package com.example.application.views.helloworld;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.tabs.Tabs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomTabs extends Tabs implements ICustom<CustomTabs> {

    private boolean widthFull;
    private String icon;

    @Override
    public CustomTabs instance() {
        this.getElement().setAttribute("icon", this.icon);
        return this;
    }
}
