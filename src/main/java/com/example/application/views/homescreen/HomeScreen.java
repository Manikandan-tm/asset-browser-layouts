package com.example.application.views.homescreen;

import com.flowingcode.vaadin.addons.fontawesome.FontAwesome;
import com.github.appreciated.card.Card;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.contextmenu.ContextMenu;
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
        var userLogo = FontAwesome.Solid.CIRCLE_USER.create();
        userLogo.getElement().getStyle().set("margin-left", "auto");

        ContextMenu userMenu = new ContextMenu(userLogo);
        userMenu.setOpenOnClick(true);
        userMenu.addItem("Logout");

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

//        var progressBar = new ProgressBar();
//        progressBar.setValue(0.8);
//        progressBar.setWidth("100px");
//        progressBar.setHeight("10px");
//        progressBar.getStyle().set("margin-left", "auto");
        var progressBarLayout = new Div();
        progressBarLayout.getElement().getStyle().set("margin-left", "auto");
        progressBarLayout.addClassName("my-progress");
        var progressBar1 = new Div();
        progressBar1.addClassName("my-progress-bar");
        var progressBar2 = new Div();
        progressBar2.addClassName("my-progress-bar");
        var progressBar3 = new Div();
        progressBar3.addClassName("my-progress-bar");
        var progressBar4 = new Div();
        progressBar4.addClassName("my-progress-bar");
        var progressBar5 = new Div();
        progressBar5.addClassName("my-progress-bar");
        progressBarLayout.add(progressBar1,progressBar2,progressBar3,progressBar4,progressBar5);
        var fill = Integer.parseInt("70");
        if(fill>=0 && fill<20){
            progressBar1.removeClassNames("active","semi");
            progressBar2.removeClassNames("active","semi");
            progressBar3.removeClassNames("active","semi");
            progressBar4.removeClassNames("active","semi");
            progressBar5.removeClassNames("active","semi");

            progressBar1.getClassNames().add("semi");
        }
        else if(fill>=20 && fill<=30){
            progressBar1.removeClassNames("active","semi");
            progressBar2.removeClassNames("active","semi");
            progressBar3.removeClassNames("active","semi");
            progressBar4.removeClassNames("active","semi");
            progressBar5.removeClassNames("active","semi");
            progressBar1.getClassNames().add("active");
            if(fill>20)
                progressBar2.getClassNames().add("semi");
        }

        else if(fill>= 40&& fill<60){
            progressBar1.removeClassNames("active","semi");
            progressBar2.removeClassNames("active","semi");
            progressBar3.removeClassNames("active","semi");
            progressBar4.removeClassNames("active","semi");
            progressBar5.removeClassNames("active","semi");

            progressBar1.getClassNames().add("active");
            progressBar2.getClassNames().add("active");

            if(fill>40)
                progressBar3.getClassNames().add("semi");
        }
        else if(fill>=60 && fill<80){
            progressBar1.removeClassNames("active","semi");
            progressBar2.removeClassNames("active","semi");
            progressBar3.removeClassNames("active","semi");
            progressBar4.removeClassNames("active","semi");
            progressBar5.removeClassNames("active","semi");

            progressBar1.getClassNames().add("active");
            progressBar2.getClassNames().add("active");
            progressBar3.getClassNames().add("active");
            if(fill>60)
                progressBar4.getClassNames().add("semi");
        }
        else if(fill>=80 && fill<100){
            progressBar1.removeClassNames("active","semi");
            progressBar2.removeClassNames("active","semi");
            progressBar3.removeClassNames("active","semi");
            progressBar4.removeClassNames("active","semi");
            progressBar5.removeClassNames("active","semi");

            progressBar1.getClassNames().add("active");
            progressBar2.getClassNames().add("active");
            progressBar3.getClassNames().add("active");
            progressBar4.getClassNames().add("active");
            if(fill>80)
                progressBar5.getClassNames().add("semi");
        }
        else if(fill==100){
            progressBar1.removeClassNames("active","semi");
            progressBar2.removeClassNames("active","semi");
            progressBar3.removeClassNames("active","semi");
            progressBar4.removeClassNames("active","semi");
            progressBar5.removeClassNames("active","semi");

            progressBar1.getClassNames().add("active");
            progressBar2.getClassNames().add("active");
            progressBar3.getClassNames().add("active");
            progressBar4.getClassNames().add("active");
            progressBar5.getClassNames().add("active");
        }


        var accuracyValue = new Label(("85") + "%");
        accuracyValue.addClassName("home-page-values");
        accuracyValue.getElement().getStyle().set("margin-left", "auto");
        var progressLayout = new HorizontalLayout(accuracy, progressBarLayout,accuracyValue);
        progressLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);

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
        addedFilesDiv.add(getRecentlyAddedFiles(),getRecentlyProcessedFiles());
        addedFilesDiv.addClassName("home-page-values");
        return addedFilesDiv;
    }
    private FlexLayout getRecentlyAddedFiles(){
        var addedLabel = new Label("Recently added");
        addedLabel.getElement().getStyle().set("margin-bottom", "15px");
        var recentlyAdded = new FlexLayout(addedLabel,createCardLayout("My Invoices", "100"));
        recentlyAdded.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        recentlyAdded.getElement().getStyle().set("margin-bottom", "15px");
        return recentlyAdded;
    }

    private FlexLayout getRecentlyProcessedFiles(){
        var processingLabel = new Label("Recently Processed");
        processingLabel.getElement().getStyle().set("margin-bottom", "15px");
        var recentlyProcessed = new FlexLayout(processingLabel,createCardLayout("My Invoices", "100"));
        recentlyProcessed.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        recentlyProcessed.getElement().getStyle().set("margin-bottom", "15px");
        return recentlyProcessed;
    }

    private Card createCardLayout(String title, String selectedFileSize) {
        Label cardTitle = new Label(title);
        cardTitle.addClassName("upload-card-title");

        var fileSize = new Label(selectedFileSize + " KB");
        fileSize.addClassName("file-size-details");
        var fileStorage = new Label("Phone Storage");
        fileStorage.addClassName("home-page-labels");

        var fileType = FontAwesome.Solid.FILE_PDF.create();
        fileType.setClassName("pdf-icon");

        var fileDimension = new Label(" | 700 X 1200 px");
        fileDimension.addClassName("file-size-details");

        var fileSizeDetails = new FlexLayout(fileType,fileSize,fileDimension);
        fileSizeDetails.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        fileSizeDetails.setAlignContent(FlexLayout.ContentAlignment.CENTER);
        fileSizeDetails.getElement().getStyle().set("margin-top", "5px");

        var cardDetails = new FlexLayout(cardTitle, fileStorage);
        cardDetails.setFlexDirection(FlexLayout.FlexDirection.COLUMN);

        var fileTypeImage = new Image("images/Group4.png", "type");
        //fileTypeImage.addClassName("file-img");

        fileTypeImage.addClassName("scrollbar-image");

        var fullScreenIcon = new Image("images/full-screen.svg", "expand");
        fullScreenIcon.addClassName("full-screen-icon");
//        fullScreenIcon.addClickListener(imageClickEvent -> {
//            var previewComponent = Util.getPreview();
//            //previewComponent.setFileInfo(fileInfo);
//            try {
//                add(previewComponent);
//                previewComponent.init();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
        var fullScreenDiv = new Div(fullScreenIcon);
        fullScreenDiv.addClassName("full-screen-div");

        var imageDiv = new Div(fileTypeImage, fullScreenDiv);
        imageDiv.addClassName("scrollbar-image-container");

        var uploadProgressBar = new ProgressBar();
        uploadProgressBar.setValue(0.5);

        var remainingTime = new Label("8.00 PM");
        remainingTime.addClassName("home-page-labels");

        var processedPercent = new Label("30"+"% Processed");
        processedPercent.addClassName("home-page-labels");

        var cancelUpload = new Button("Cancel");
        cancelUpload.addThemeVariants(ButtonVariant.LUMO_TERTIARY,ButtonVariant.LUMO_ERROR,ButtonVariant.LUMO_SMALL);

        var processedState = new FlexLayout(processedPercent,cancelUpload);
        processedState.setAlignItems(Alignment.CENTER);
        processedState.getElement().getStyle().set("margin-left", "auto");

        var uploadStatus = new FlexLayout(remainingTime,processedState);
        uploadStatus.setAlignItems(Alignment.CENTER);

        var cardContentDiv = getFlexLayout(cardDetails,fileSizeDetails);
        cardContentDiv.getElement().getStyle().set("margin-left", "15px");
        cardContentDiv.setFlexWrap(FlexLayout.FlexWrap.WRAP);

        var cardMainDiv = new Div(new FlexLayout(imageDiv,cardContentDiv),uploadProgressBar,uploadStatus);
        cardMainDiv.getElement().getStyle().set("margin", "15px");

        var card = new Card(cardMainDiv);
        card.getElement().getStyle().set("margin-bottom", "10px");
        return card;
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
