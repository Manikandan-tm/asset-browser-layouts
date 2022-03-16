package com.example.application.views.helloworld;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import java.util.LinkedHashMap;
import java.util.Map;

@Route("/tab")
@CssImport("./styles/filter-style.css")
@CssImport(value = "./styles/vaadin-tabs.css",themeFor = "vaadin-tabs")
@CssImport(value = "./styles/vaadin-tab.css",themeFor = "vaadin-tab")
@CssImport(value = "./styles/vaadin-checkbox.css",themeFor = "vaadin-checkbox")
@CssImport(value = "./styles/vaadin-datepicker.css",themeFor = "vaadin-date-picker")
public class TabSheetView extends FlexLayout {
    /**
     * Contents of the TabSheet.
     */
    private final Map<Tab, Component> contents = new LinkedHashMap<>();
    private final VerticalLayout datePickerLayout = new VerticalLayout();
    private Component currentContent = new FlexLayout();

    public TabSheetView() {
        this.buildContentAndTabs();

        // tabs component
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setWidthFull();


        // show components on the screen
        this.add(tabs);

        tabs.addSelectedChangeListener(event -> showTab(event.getSelectedTab()));


        // add tabs to the component

        contents.keySet().forEach(tabs::add);
    }

    /**
     * Builds contents to be displayed and the corresponding tabs. Uses the first
     * articles from <a href=
     * "https://www.un.org/en/universal-declaration-human-rights/index.html">the
     * Universal Declaration of Human Rights</a>.
     */
    private void buildContentAndTabs() {

        for (Tab tab : getTab()) {
            if(tab.getLabel().equalsIgnoreCase("Template")){
                contents.put(tab, new Div(new Label("template")));
            }
            else {
                contents.put(tab, getUploadDateContent());
            }
        }

    }
    private Div getUploadDateContent() {
        Checkbox today = new Checkbox("Today");
        today.addClassName("filter-checkbox");
        Checkbox yesterday = new Checkbox("Yesterday");
        yesterday.addClassName("filter-checkbox");
        Checkbox lastWeek = new Checkbox("Last Week");
        lastWeek.addClassName("filter-checkbox");
        Checkbox customDate = new Checkbox("Custom Date");
        customDate.addClassName("filter-checkbox");

        customDate.addValueChangeListener(e -> datePickerLayout.setVisible(e.getValue()));
        var contentDiv = new FlexLayout();
        contentDiv.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        contentDiv.add(today,yesterday,lastWeek,customDate,getDatePickerLayout());

        return new Div(contentDiv);
    }
    private VerticalLayout getDatePickerLayout(){
        DatePicker fromDate = new DatePicker();
        fromDate.setPlaceholder("From");
        fromDate.addThemeName("asset-date-picker");
        DatePicker toDate = new DatePicker();
        toDate.setPlaceholder("To");
        toDate.addThemeName("asset-date-picker");
        fromDate.addValueChangeListener(e -> toDate.setMin(e.getValue()));
        toDate.addValueChangeListener(e -> fromDate.setMax(e.getValue()));

        datePickerLayout.getElement().getStyle().set("margin-left", "15px");
        datePickerLayout.removeAll();
        datePickerLayout.setVisible(false);
        datePickerLayout.add(fromDate,toDate);
        return datePickerLayout;
    }
    private Tab[] getTab() {
        return new Tab[]{
                getTabDetails("Upload Date"),
                getTabDetails("Status"),
                getTabDetails("Template"),
                getTabDetails("File Format"),
                getTabDetails("Accuracy"),
                getTabDetails("File Size"),
                getTabDetails("Page Count"),
                getTabDetails("Attributes"),
        };
    }

    private Tab getTabDetails(String tabLabel) {
        var tab = new Tab(tabLabel);
        tab.addThemeName("filter-tab");
        return tab;
    }
    private void showTab(Tab tab) {
        Component newContent = contents.get(tab);
        replace(currentContent, newContent);
        currentContent = newContent;
    }

}
