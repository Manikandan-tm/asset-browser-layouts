package com.example.application.views.asset;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.addon.sliders.PaperSlider;

import java.util.LinkedHashMap;
import java.util.Map;

@PageTitle("Filter Div")
@Route("/filter")
@CssImport("./styles/filter-style.css")
@CssImport(value = "./styles/vaadin-tabs.css",themeFor = "vaadin-tabs")
@CssImport(value = "./styles/vaadin-tab.css",themeFor = "vaadin-tab")
@CssImport(value = "./styles/vaadin-checkbox.css",themeFor = "vaadin-checkbox")
@CssImport(value = "./styles/vaadin-datepicker.css",themeFor = "vaadin-date-picker")
@CssImport(value = "./styles/vaadin-paper-slider.css", themeFor = "vaadin-paper-slider")
@StyleSheet("https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap")
public class FilterDiv extends Div {
    private final VerticalLayout datePickerLayout = new VerticalLayout();
    private final Map<Tab,Component> tabComponentMap = new LinkedHashMap<>();
    private final FlexLayout currentContent = new FlexLayout();
    public FilterDiv(){

        this.buildContentAndTabs();
        setSizeFull();
        datePickerLayout.setPadding(false);

        var pageHeader = getPageHeader();
        pageHeader.addClassName("asset-page-header");

        final Tabs tabs = new Tabs();
        tabs.addThemeName("filter-tabs");
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.getElement().getStyle().set("background", "#EFF5F9");

        var flexContent = new FlexLayout(tabs,currentContent);
        flexContent.getElement().getStyle().set("margin-top", "5px");

        this.add(pageHeader,flexContent,getFooter());

        tabs.addSelectedChangeListener(
                event -> {
                    // remove old contents, if there was a previously selected tab
                    if (event.getPreviousTab() != null) currentContent.remove(this.tabComponentMap.get(event.getPreviousTab()));
                    // add new contents, if there is a currently selected tab
                    if (event.getSelectedTab() != null) currentContent.add(this.tabComponentMap.get(event.getSelectedTab()));
                }
        );
        tabComponentMap.keySet().forEach(tabs::add);
    }

    private void buildContentAndTabs() {
        for (Tab tab : getTab()) {
            if(tab.getLabel().equalsIgnoreCase("Upload Date"))
                tabComponentMap.put(tab, getUploadDateContent());
            else if(tab.getLabel().equalsIgnoreCase("Status"))
                tabComponentMap.put(tab, getStatusContent());
            else if(tab.getLabel().equalsIgnoreCase("Template"))
                tabComponentMap.put(tab, getTemplateContent());
            else if(tab.getLabel().equalsIgnoreCase("File Format"))
                tabComponentMap.put(tab, getFileFormatContent());
            else if(tab.getLabel().equalsIgnoreCase("Accuracy"))
                tabComponentMap.put(tab, getAccuracyContent());
            else if(tab.getLabel().equalsIgnoreCase("File Size"))
                tabComponentMap.put(tab, getFileSizeContent());
            else if(tab.getLabel().equalsIgnoreCase("Page Count"))
                tabComponentMap.put(tab, getPageCountContent());
            else
                tabComponentMap.put(tab, getAttributesContent());
        }
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

    private Header getPageHeader(){
        var previousPageImg = new Image("images/previous-page.svg","back");
        previousPageImg.addClassName("previous-page-img");
        previousPageImg.addClickListener(imageClickEvent -> UI.getCurrent().navigate(AssetBrowser.class));
        var previousPageLabel = new Label("My Assets");
        previousPageLabel.addClassName("previous-page-label");
        var previousContent = new Div(previousPageImg,previousPageLabel);

        var pageHeaderLabel = new Label("Filter By");
        pageHeaderLabel.addClassName("asset-page-label");

        var pageHeaderLayout = new HorizontalLayout(previousContent,pageHeaderLabel);
        pageHeaderLayout.addClassName("page-header-layout");
        return new Header(pageHeaderLayout);
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
        var uploadDateLayout = new FlexLayout();
        uploadDateLayout.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        uploadDateLayout.add(today,yesterday,lastWeek,customDate,getDatePickerLayout());

        return new Div(uploadDateLayout);
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

    private FlexLayout getStatusContent(){
        Checkbox processing = new Checkbox("Processing");
        processing.addClassName("filter-checkbox");
        Checkbox uploaded = new Checkbox("Uploaded");
        uploaded.addClassName("filter-checkbox");
        Checkbox cancelled = new Checkbox("Cancelled");
        cancelled.addClassName("filter-checkbox");
        Checkbox deleted = new Checkbox("Deleted");
        deleted.addClassName("filter-checkbox");

        var templateLayout = new FlexLayout(processing,uploaded,cancelled,deleted);
        templateLayout.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        return templateLayout;
    }

    private FlexLayout getTemplateContent(){
        Checkbox invoice = new Checkbox("Invoice");
        invoice.addClassName("filter-checkbox");
        Checkbox insurance = new Checkbox("Insurance");
        insurance.addClassName("filter-checkbox");
        Checkbox agreement = new Checkbox("Agreement");
        agreement.addClassName("filter-checkbox");
        Checkbox scanningReport = new Checkbox("Scanning Report");
        scanningReport.addClassName("filter-checkbox");

        var statusLayout = new FlexLayout(invoice,insurance,agreement,scanningReport);
        statusLayout.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        return statusLayout;
    }


    private FlexLayout getFileFormatContent(){
        Checkbox pdf = new Checkbox("PDF");
        pdf.addClassName("filter-checkbox");
        Checkbox jpeg = new Checkbox("JPEG");
        jpeg.addClassName("filter-checkbox");
        Checkbox png = new Checkbox("PNG");
        png.addClassName("filter-checkbox");
        Checkbox tiff = new Checkbox("TIFF");
        tiff.addClassName("filter-checkbox");

        var fileFormatLayout = new FlexLayout(pdf,jpeg,png,tiff);
        fileFormatLayout.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        return fileFormatLayout;
    }

    private Div getAccuracyContent(){
        var accuracyValue = new Label();
        var accuracySlider = new PaperSlider(0, 100, 50);
        accuracySlider.addClassName("accuracy-slider");
        accuracySlider.addValueChangeListener(e -> accuracyValue.setText("Accuracy Percentage: "+e.getValue()+" %"));
        var accuracyLayout = new Div(accuracySlider,accuracyValue);
        accuracyLayout.getElement().getStyle().set("margin", "10px");
        return accuracyLayout;
    }

    private Div getFileSizeContent(){
        var fileSizeLayout = new Div();
        return fileSizeLayout;
    }

    private Div getPageCountContent(){
        var pageCount = new Label();
        var pageCountSlider = new PaperSlider(0, 100, 50);
        pageCountSlider.addClassName("accuracy-slider");
        pageCountSlider.addValueChangeListener(e -> pageCount.setText("Page Count: "+e.getValue()));


        var fileSizeLayout = new Div(pageCountSlider,pageCount);
        return fileSizeLayout;
    }

    private FlexLayout getAttributesContent(){
        var attributesLayout = new FlexLayout();
        Checkbox images = new Checkbox("Images - (Photo, Map, table, Graph)");
        images.addClassName("filter-checkbox");
        Checkbox keyValue = new Checkbox("Key value pair table");
        keyValue.addClassName("filter-checkbox");
        Checkbox multiLine = new Checkbox("Multi line item table");
        multiLine.addClassName("filter-checkbox");
        Checkbox signature = new Checkbox("Signature");
        signature.addClassName("filter-checkbox");
        Checkbox barcode = new Checkbox("Bar code");
        barcode.addClassName("filter-checkbox");
        Checkbox textExtracted = new Checkbox("Text Extracted");
        textExtracted.addClassName("filter-checkbox");
        attributesLayout.add(images,keyValue,multiLine,signature,barcode,textExtracted);
        attributesLayout.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        return attributesLayout;
    }

    private Footer getFooter() {
        var footer = new Footer();
        var applyButton = new Button("Apply");
        applyButton.addThemeVariants(ButtonVariant.LUMO_LARGE);
        applyButton.addClassName("apply-button");

        var clearButton = new Button("Clear All");
        clearButton.addThemeVariants(ButtonVariant.LUMO_LARGE);
        clearButton.addClassName("clear-button");

        footer.addClassName("button-footer");
        footer.add(clearButton,applyButton);
        return footer;
    }

}
