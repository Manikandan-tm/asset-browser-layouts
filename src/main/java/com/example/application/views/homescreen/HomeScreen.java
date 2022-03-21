package com.example.application.views.homescreen;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Home Screen")
@Route("/home")
@CssImport(value = "./styles/home-screen-style.css")
@StyleSheet("https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap")
public class HomeScreen extends VerticalLayout {
    public HomeScreen(){
        setSizeFull();
        setPadding(false);
        getElement().getStyle().set("background", "#0157AE");

        var pageHeader = getPageHeader();
        pageHeader.addClassName("asset-page-header");

        var userLabel = new Label("Hello John");
        userLabel.getElement().getStyle().set("color", "white");
        userLabel.getElement().getStyle().set("margin", "15px");

        var uploadFileDetails = getUploadFileDetails();
        uploadFileDetails.getElement().getStyle().set("background", "white");

        var accuracyLayout = getAccuracyLayout();
        accuracyLayout.getElement().getStyle().set("background", "white");

        var processingTime = getProcessTimeDetails();
        processingTime.getElement().getStyle().set("background", "white");

        var addedFilesLayout = getAddedFilesDetails();

        var contentDiv = new Div(userLabel,uploadFileDetails,accuracyLayout,processingTime,addedFilesLayout);
        contentDiv.setWidthFull();

        var footerDiv = getFooter();
        footerDiv.setWidthFull();
        footerDiv.addClassName("footer-div");

        add(pageHeader,contentDiv,footerDiv);
    }

    private Header getPageHeader(){

        var hfrLogo = new Image("images/hfr-logo-1.png", "hfr");
        var userLogo = new Image("images/user.svg", "user");
        userLogo.getElement().getStyle().set("margin-left", "auto");
        var pageHeaderLayout = new HorizontalLayout(hfrLogo,userLogo);
        pageHeaderLayout.addClassName("page-header-layout");
        return new Header(pageHeaderLayout);
    }
    private Div getUploadFileDetails(){

        var totalFiles = new Label("Total Files Uploaded");
        totalFiles.addClassName("home-page-labels");
        totalFiles.getElement().getStyle().set("color", "#000000DE");
        var totalFileCount = new Label("40");
        totalFileCount.addClassName("home-page-values");
        totalFileCount.getElement().getStyle().set("margin-left", "auto");

        Chart chart = new Chart(ChartType.PIE);
        chart.getElement().getStyle().set("min-height", "150px");
        chart.getElement().getStyle().set("min-width", "150px");
        Configuration conf = chart.getConfiguration();
        PlotOptionsPie options = new PlotOptionsPie();
        options.getDataLabels().setEnabled(false);
        options.setInnerSize("60%");
        options.setSize("100%");  // Default
        options.setCenter("50%", "50%"); // Default
        conf.setPlotOptions(options);


        DataSeries series = new DataSeries();
        DataSeriesItem processedItem = new DataSeriesItem("Processed", 30);
        processedItem.setColor(new SolidColor("#027FFF"));
        DataSeriesItem processingItem = new DataSeriesItem("Processing", 10);
        processingItem.setColor(new SolidColor("#EFF5F9"));
        series.add(processedItem);
        series.add(processingItem);
        conf.addSeries(series);

        var processed = new Label("Processed");
        processed.addClassName("home-page-labels");
        var processedValue = new Label("30");


        var processing = new Label("Processing");
        processing.addClassName("home-page-labels");
        var processingValue = new Label("10");
        var chartDetailsDiv = new FlexLayout(getProcessingLayout(processed, processedValue, "#027FFF"),
                getProcessingLayout(processing, processingValue, "#EFF5F9"));
        chartDetailsDiv.getElement().getStyle().set("justify-content", "center");
        chartDetailsDiv.setFlexDirection(FlexLayout.FlexDirection.COLUMN);

        var uploadFileDetails = new Div(getFlexLayout(totalFiles, totalFileCount),getFlexLayout(chart,chartDetailsDiv));
        uploadFileDetails.getElement().getStyle().set("border-radius", "10px");
        uploadFileDetails.getElement().getStyle().set("padding", "15px");
        uploadFileDetails.getElement().getStyle().set("margin", "15px");

        return uploadFileDetails;
    }

    private HorizontalLayout getAccuracyLayout(){
        final Label accuracy = new Label("Accuracy");
        accuracy.addClassName("home-page-labels");

        var progressBar = new ProgressBar();
        progressBar.setValue(0.8);
        progressBar.setWidth("100px");
        progressBar.setHeight("10px");
        progressBar.getStyle().set("margin-left", "auto");

        var accuracyValue = new Label(("85") + "%");
        accuracyValue.addClassName("home-page-values");
        accuracyValue.getElement().getStyle().set("margin-left", "auto");
        var progressLayout = new HorizontalLayout(accuracy, progressBar,accuracyValue);

        progressLayout.getElement().getStyle().set("margin", "15px");
        progressLayout.getElement().getStyle().set("padding", "15px");
        progressLayout.getElement().getStyle().set("border-radius", "10px");
        return progressLayout;
    }

    private HorizontalLayout getProcessTimeDetails(){
        var processTimeDetails = new HorizontalLayout();
        var processTimeLabel = new Label("Total Processing Time");
        processTimeLabel.addClassName("home-page-labels");
        var processTimeValue = new Label("239m");
        processTimeValue.addClassName("home-page-values");
        processTimeValue.getElement().getStyle().set("margin-left", "auto");

        processTimeDetails.add(processTimeLabel,processTimeValue);
        processTimeDetails.getElement().getStyle().set("margin", "15px");
        processTimeDetails.getElement().getStyle().set("padding", "15px");
        processTimeDetails.getElement().getStyle().set("border-radius", "10px");
        return processTimeDetails;
    }

    private FlexLayout getFlexLayout(Component labelDetail, Component labelValues) {
        var flexLayout = new FlexLayout(labelDetail,labelValues);
        flexLayout.setFlexDirection(FlexLayout.FlexDirection.ROW);
        flexLayout.setAlignContent(FlexLayout.ContentAlignment.CENTER);
        return flexLayout;
    }

    private HorizontalLayout getProcessingLayout(Component component1,Component component2, String color){
        component1.getElement().getStyle().set("margin", "5px");

        component2.getElement().getStyle().set("background", color);
        component2.getElement().getStyle().set("min-width", "40px");
        component2.getElement().getStyle().set("min-height", "35px");
        component2.getElement().getStyle().set("border-radius", "0 3px 3px 0");
        component2.getElement().getStyle().set("display", "flex");
        component2.getElement().getStyle().set("align-items", "center");
        component2.getElement().getStyle().set("justify-content", "center");

        var processingDetailsDiv = new HorizontalLayout(component1,component2);
        processingDetailsDiv.getElement().getStyle().set("display", "flex");
        processingDetailsDiv.getElement().getStyle().set("align-items", "center");
        processingDetailsDiv.getElement().getStyle().set("justify-content", "space-between");
        processingDetailsDiv.getElement().getStyle().set("border", "1px solid #00000078");
        processingDetailsDiv.setMargin(true);
        processingDetailsDiv.getElement().getStyle().set("border-radius", "5px");
        return processingDetailsDiv;
    }

    private Div getAddedFilesDetails(){
        var addedFilesDiv = new Div();
        addedFilesDiv.getElement().getStyle().set("border-radius", "40px");
        addedFilesDiv.getElement().getStyle().set("background", "white");
        addedFilesDiv.getElement().getStyle().set("padding", "15px");
        addedFilesDiv.getElement().getStyle().set("height", "100%");
        addedFilesDiv.add(new Label("Recently added"));
        addedFilesDiv.addClassName("home-page-values");
        return addedFilesDiv;
    }



    private Div getFooter() {
        var footer = new Footer();
        var homeIcon = new Icon(VaadinIcon.HOME);
        homeIcon.getElement().getStyle().set("color", "white");
        homeIcon.getElement().getStyle().set("padding", "0");
        homeIcon.getElement().getStyle().set("height", "24px");
        var homeButton = new Button(homeIcon);
        homeButton.addThemeVariants(ButtonVariant.LUMO_LARGE,
                ButtonVariant.LUMO_CONTRAST);
        homeButton.getElement().getStyle().set("color","white");
        homeButton.getElement().getStyle().set("width","111px");
        homeButton.getElement().getStyle().set("height", "54px");
        homeButton.getElement().getStyle().set("background", "#3AE8B6");
        homeButton.getElement().getStyle().set("border-radius", "100px");
        //homeButton.addClassName("apply-button");

        var addIcon = new Icon(VaadinIcon.PLUS_CIRCLE_O);
        addIcon.getElement().getStyle().set("color", "#00000099");
        addIcon.getElement().getStyle().set("padding", "0");
        addIcon.getElement().getStyle().set("height", "24px");
        var addFilesButton = new Button(addIcon);
        addFilesButton.addThemeVariants(ButtonVariant.LUMO_LARGE);
        addFilesButton.getElement().getStyle().set("background", "white");
        addFilesButton.getElement().getStyle().set("width","111px");
        addFilesButton.getElement().getStyle().set("height", "54px");
        addFilesButton.getElement().getStyle().set("border-radius", "100px");
        //addFilesButton.addClassName("clear-button");

        var searchIcon = new Icon(VaadinIcon.SEARCH);
        searchIcon.getElement().getStyle().set("color", "#00000099");
        searchIcon.getElement().getStyle().set("padding", "0");
        searchIcon.getElement().getStyle().set("height", "24px");
        var searchFileButton = new Button(searchIcon);
        searchFileButton.addThemeVariants(ButtonVariant.LUMO_LARGE);
        searchFileButton.getElement().getStyle().set("background","white");
        searchFileButton.getElement().getStyle().set("width","111px");
        searchFileButton.getElement().getStyle().set("height", "54px");
        searchFileButton.getElement().getStyle().set("border-radius", "100px");
        //searchFileButton.addClassName("clear-button");

        footer.addClassName("home-footer");
        footer.add(homeButton,addFilesButton,searchFileButton);

        return new Div(footer);
    }
}
