package com.example.application.views.asset;

import com.example.application.views.Slider.SliderComponent;
import com.github.appreciated.card.Card;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@PageTitle("Asset Browser")
@Route(value = "/asset")
@CssImport(value = "./styles/asset-browser-style.css")
@CssImport(value = "./styles/radio-button.css", themeFor = "vaadin-radio-group")
@CssImport(value = "./styles/asset-textfield.css",themeFor = "vaadin-text-field")
@CssImport("./styles/slider.css")
@StyleSheet("https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap")
public class AssetBrowser extends VerticalLayout {
    final SliderComponent slider = SliderComponent.builder().build();
    public static final String ASSET_BROWSER_VALUES = "asset-browser-values";
    public static final String ASSET_BROWSER_LABEL = "asset-browser-label";
    public static final String DETAIL_LIST_DIV = "detail-list-div";
    public static final String DETAIL_ELEMENT = "aggregate-div";
    public static final String DETAIL_LABEL = "summary-header-label";
    public static final String DETAIL_VALUE = "summary-content-label";
    public AssetBrowser(){
        addClassName("asset-main-layout");
        setSizeFull();
        slider.getContent().add(getDialogLayout());
        add(slider);


        var pageHeader = getPageHeader();
        pageHeader.addClassName("asset-page-header");

        HorizontalLayout searchLayout = new HorizontalLayout(getSearchField(),getSort(),getFilter());
        searchLayout.addClassName("search-layout");

        var resultsCount = new Label("results found");
        resultsCount.addClassName("asset-browser-label");
        var searchResultLayout = new HorizontalLayout(resultsCount,createFilterBadge("PDF"));
        searchResultLayout.setPadding(true);
        searchResultLayout.addClassName("search-result-layout");

        var card = createCardLayout("My invoice", "10", "Invoice");

        var contentDiv = new Div(searchLayout,searchResultLayout,card);
        contentDiv.addClassName("asset-browser-content");

        add(pageHeader,createDetailLayout(),createWebDetailLayout(),contentDiv);

    }

    private Header getPageHeader(){
        var previousPageImg = new Image("images/previous-page.svg","back");
        previousPageImg.addClassName("previous-page-img");
        var previousPageLabel = new Label("Home");
        previousPageLabel.addClassName("previous-page-label");
        var previousContent = new Div(previousPageImg,previousPageLabel);
        var pageHeaderLabel = new Label("My Assets");
        pageHeaderLabel.addClassName("assest-page-header");
        var pageHeaderLayout = new HorizontalLayout(previousContent,pageHeaderLabel);
        pageHeaderLayout.addClassName("page-header-layout");
        return new Header(pageHeaderLayout);
    }
    private Div createDetailLayout() {
        var detailList = new Div();
        detailList.addClassName("details-list-layout");

        var file = new Label("Files");
        file.addClassName("asset-browser-label");
        var fileCount = new Label("50");
        fileCount.addClassName("asset-browser-values");
        detailList.add(getFlexLayout(file,fileCount));

        var wip = new Label("WIP");
        wip.addClassName("asset-browser-label");
        var wipValue = new Label("10");
        wipValue.addClassName("asset-browser-values");
        detailList.add(getFlexLayout(wip,wipValue));

        var avgSize = new Label("Avg.Size");
        avgSize.addClassName("asset-browser-label");
        var avgSizeValue = new Label("10"+"MB");
        avgSizeValue.addClassName("asset-browser-values");
        detailList.add(getFlexLayout(avgSize,avgSizeValue));

        var avgPages = new Label("Avg.Pages");
        avgPages.addClassName("asset-browser-label");
        var avgPagesCount = new Label("30");
        avgPagesCount.addClassName("asset-browser-values");
        detailList.add(getFlexLayout(avgPages,avgPagesCount));

        var actuals = new Label("Actuals");
        actuals.addClassName("asset-browser-label");
        var actualsValue = new Label("450");
        actualsValue.addClassName("asset-browser-values");
        detailList.add(getFlexLayout(actuals,actualsValue));

        var blanks = new Label("Blanks");
        blanks.addClassName("asset-browser-label");
        var blanksValue = new Label("30");
        blanksValue.addClassName("asset-browser-values");
        detailList.add(getFlexLayout(blanks,blanksValue));

        var detailListContent = new Div(detailList);
        detailListContent.addClassName("detail-list-div");
        return detailListContent;
    }

    private FlexLayout getFlexLayout(Component labelDetail, Component labelValues) {
        var flexLayout = new FlexLayout(labelDetail,labelValues);
        flexLayout.setFlexDirection(FlexLayout.FlexDirection.ROW);
        flexLayout.setAlignContent(FlexLayout.ContentAlignment.CENTER);
        return flexLayout;
    }

    private Button getSort() {
        var sortButton = new Button(new Image("images/sort.svg", "sort"));
        sortButton.addClassName("sort-button");
        sortButton.addClickListener(buttonClickEvent -> slider.open());
        sortButton.getElement().getStyle().set("margin-left", "25px");
        return sortButton;
    }

    private Button getFilter() {
        var filterButton = new Button(new Image("images/filter.svg", "filter"));
        filterButton.addClassName("sort-button");
        filterButton.addClickListener( e -> UI.getCurrent().navigate(FilterDiv.class));
        return filterButton;
    }

    private TextField getSearchField() {
        var searchField = new TextField();
        searchField.setPlaceholder("Search");
        searchField.addThemeName("search-field");
        searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        return searchField;
    }

    private Card createCardLayout(String title, String selectedFileSize, String description) {

        Label cardTitle = new Label(title);
        cardTitle.addClassName("upload-card-title");

        Span cardDescription = new Span(description);
        cardDescription.addClassName("upload-card-details");
        cardDescription.getElement().getThemeList().add("badge contrast");
        cardDescription.getElement().getStyle().set("width", "fit-content");

        var fileSize = new Label(selectedFileSize + " KB");
        fileSize.addClassName("file-size-details");
        var fileStorage = new Label("| Phone Storage");
        fileStorage.addClassName("file-size-details");
//
        var uploadDate = new Label("21/02/2022");
        uploadDate.addClassName("file-size-details");
        uploadDate.getElement().getStyle().set("margin-left", "auto");
//
        var fileSizeDetails = new FlexLayout(fileSize, fileStorage,uploadDate);
        fileSizeDetails.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        fileSizeDetails.setAlignContent(FlexLayout.ContentAlignment.CENTER);
        fileSizeDetails.getElement().getStyle().set("margin-top", "5px");

        var cardDetails = new FlexLayout(cardTitle, cardDescription);
        cardDetails.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        cardDetails.getElement().getStyle().set("margin-left", "15px");


        var detailsDiv = new Div();
        detailsDiv.addClassName("card-details-div");

        var pages = new Label("Pages");
        pages.addClassName("asset-browser-label");
        var avgPagesCount = new Label("8");
        avgPagesCount.addClassName("asset-browser-values");
        detailsDiv.add(getFlexLayout(pages, avgPagesCount));


        var actuals = new Label("Actuals");
        actuals.addClassName("asset-browser-label");
        var actualsValue = new Label("7");
        actualsValue.addClassName("asset-browser-values");
        detailsDiv.add(getFlexLayout(actuals, actualsValue));

        var blanks = new Label("Blanks");
        blanks.addClassName("asset-browser-label");
        var blanksValue = new Label("1");
        blanksValue.addClassName("asset-browser-values");
        detailsDiv.add(getFlexLayout(blanks, blanksValue));

        var misses = new Label("Misses");
        misses.addClassName("asset-browser-label");
        var missesValue = new Label("0");
        missesValue.addClassName("asset-browser-values");
        detailsDiv.add(getFlexLayout(misses, missesValue));


        var card = new Card();
        var fileTypeImage = new Image("images/Group 4.png", "type");
        fileTypeImage.addClassName("file-img");

        final Label accuracy = new Label("Accuracy");
        accuracy.addClassName("upload-labels");
        var progressBar = new ProgressBar();
        progressBar.setValue(0.8);
        progressBar.setWidth("100px");
        progressBar.setHeight("10px");
        var accuracyValue = new Label(("85") + "%");
        accuracyValue.addClassName("upload-label-values");
        var progressLayout = new HorizontalLayout(accuracy, accuracyValue);

        var progressDiv = new Div(progressLayout,progressBar);
        var cardContentDiv = new Div(getFlexLayout(detailsDiv, progressDiv));
        cardContentDiv.addClassName("card-content-div");
//
        var uploadProgressBar = new ProgressBar();
        uploadProgressBar.setValue(0.5);

        var remainingTime = new Label("Time Remaining "+"8h");
        remainingTime.addClassName("file-size-details");

        var processedPercent = new Label("30"+"% Processed");
        processedPercent.addClassName("file-size-details");

        var cancelUpload = new Button("Cancel");
        cancelUpload.addThemeVariants(ButtonVariant.LUMO_TERTIARY,ButtonVariant.LUMO_ERROR,ButtonVariant.LUMO_SMALL);

        var processedState = new FlexLayout(processedPercent,cancelUpload);
        processedState.setAlignItems(Alignment.CENTER);
        processedState.getElement().getStyle().set("margin-left", "auto");

        var uploadStatus = new FlexLayout(remainingTime,processedState);
//        uploadStatus.setFlexDirection(FlexLayout.FlexDirection.ROW);
        uploadStatus.setAlignItems(Alignment.CENTER);

        var cardMainDiv = new Div(getFlexLayout(fileTypeImage, cardDetails),cardContentDiv,fileSizeDetails,uploadProgressBar,uploadStatus);
        card.add(cardMainDiv);
        card.addClassName("upload-card");
        card.setBorderRadius("15px");
        return card;
    }

    private HorizontalLayout getHeaderContent() {
        final HorizontalLayout horizontalLayout = new HorizontalLayout();
        final Label title = new Label("Sort By");
        final Icon close = new Icon(VaadinIcon.CLOSE);
        close.addClassName("close-icon");
        title.addClassName("title");
        close.addClickListener(iconClickEvent -> slider.close());
        horizontalLayout.setWidthFull();
        horizontalLayout.add(title,close);
        return horizontalLayout;
    }
    private Component getDialogLayout() {
        final Div div = new Div();
        final Header header = new Header();
        header.add(getHeaderContent(),getSortList(),getFooter());
        div.add(header);
        return div;
    }

    private VerticalLayout getSortList(){
        var sortList = new VerticalLayout();
        RadioButtonGroup<String> radioGroup = new RadioButtonGroup<>();
        radioGroup.setSizeFull();
        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        radioGroup.addThemeName("sort-radio-button");
        radioGroup.addClassName("sort-radio-button");
        radioGroup.setItems("Default", "A-Z", "Z-A","Size - Small to large","Size - Large to small","File Format","Template");
        sortList.add(radioGroup);
        return sortList;
    }

    private Span createFilterBadge(String filterType) {
        Button clearButton = new Button(VaadinIcon.CLOSE_SMALL.create());
        clearButton.addThemeVariants(ButtonVariant.LUMO_CONTRAST, ButtonVariant.LUMO_TERTIARY_INLINE);
        clearButton.getStyle().set("margin-inline-start", "var(--lumo-space-xs)");
        // Accessible button name
        clearButton.getElement().setAttribute("aria-label", "Clear filter: " + filterType);
        // Tooltip
        clearButton.getElement().setAttribute("title", "Clear filter: " +filterType);

        Span badge = new Span(new Span(filterType), clearButton);
        badge.getElement().getThemeList().add("badge contrast");
        badge.getElement().getStyle().set("background", "white");

        // Add handler for removing the badge
        clearButton.addClickListener(event -> badge.getElement().removeFromParent());

        return badge;
    }
    private Div createWebDetailLayout() {
        var detailList = new Div();
        //detailList.addClassName("details-list-layout");
        detailList.getElement().getStyle().set("background","lightgray");

        var detailContainer = new Div();

        var file = new Label("Files");
        file.addClassNames(DETAIL_LABEL);
        var fileCount = new Label(String.valueOf("10"));
        fileCount.addClassNames(DETAIL_VALUE);
        var fileDiv = new Div(getColumnFlexLayout(file, fileCount));
        fileDiv.addClassName(DETAIL_ELEMENT);

        var wip = new Label("WIP");
        wip.addClassNames(DETAIL_LABEL);
        var wipValue = new Label("10");
        wipValue.addClassNames(DETAIL_VALUE);
        var wipDiv = new Div(getColumnFlexLayout(wip, wipValue));
        wipDiv.addClassName(DETAIL_ELEMENT);

            var xAvgFileSize = BigDecimal.valueOf(872652562).setScale(2, RoundingMode.HALF_DOWN);
            var avgSize = new Label("Avg.Size");
            avgSize.addClassName(ASSET_BROWSER_LABEL);
            var avgSizeValue = new Label(xAvgFileSize + "MB");
            avgSizeValue.addClassName(ASSET_BROWSER_VALUES);
            detailContainer.add(getFlexLayout(avgSize, avgSizeValue));

            var avgPages = new Label("Avg.Pages");
            var xAvgPagesCount = BigDecimal.valueOf(10).setScale(2, RoundingMode.HALF_DOWN);
            avgPages.addClassName(ASSET_BROWSER_LABEL);
            var avgPagesCountLabel = new Label(String.valueOf(xAvgPagesCount));
            avgPagesCountLabel.addClassName(ASSET_BROWSER_VALUES);
            detailContainer.add(getFlexLayout(avgPages, avgPagesCountLabel));

            var actuals = new Label("Actuals");
            actuals.addClassNames(DETAIL_LABEL);
            var xActualsValue = BigDecimal.valueOf(8)
                    .setScale(2, RoundingMode.HALF_DOWN);
            var actualsValue = new Label(String.valueOf(xActualsValue));
            actualsValue.addClassNames(DETAIL_VALUE);
            var actualsDiv = new Div(getColumnFlexLayout(actuals, actualsValue));
            actualsDiv.addClassName(DETAIL_ELEMENT);

            var blanks = new Label("Blanks");
            blanks.addClassNames(DETAIL_LABEL);
            var blanksValue = new Label(String.valueOf(7));
            blanksValue.addClassNames(DETAIL_VALUE);
            var blanksDiv = new Div(getColumnFlexLayout(blanks, blanksValue));
            blanksDiv.addClassName(DETAIL_ELEMENT);

//            summary.getAttributionCount().forEach((attribution, aDouble) -> {
//                if (aDouble > 0d) {
//                    var label = attribution.name().replace("_", " ");
//                    var attributionLabel = new Label();
//                    if (Objects.equals(label, "MULTI LINE ITEM TABLE"))
//                        attributionLabel.setText("Table");
//                    else attributionLabel.setText(label);
//                    attributionLabel.addClassName(ASSET_BROWSER_LABEL);
//                    var attributionValue = new Label(String.valueOf(BigDecimal.valueOf(aDouble)
//                            .setScale(2, RoundingMode.HALF_DOWN)));
//                    attributionValue.addClassName(ASSET_BROWSER_VALUES);
//                    detailList.add(getFlexLayout(attributionLabel, attributionValue));
//                }
//
//            });
        var detailsElement = new FlexLayout(fileDiv,wipDiv,actualsDiv,blanksDiv);
        detailsElement.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        detailList.add(detailContainer,detailsElement);
        return detailList;
    }
    private FlexLayout getColumnFlexLayout(Component labelDetail, Component labelValues) {
        var flexLayout = new FlexLayout(labelDetail,labelValues);
        flexLayout.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        flexLayout.setAlignContent(FlexLayout.ContentAlignment.CENTER);
        return flexLayout;
    }
    private Footer getFooter() {
        var footer = new Footer();
        var applyButton = new Button("Apply");
        applyButton.addThemeVariants(ButtonVariant.LUMO_LARGE);
        applyButton.addClassName("apply-button");


        footer.addClassName("button-footer");
        footer.add(applyButton);
        return footer;
    }
}
