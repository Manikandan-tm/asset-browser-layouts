package com.example.application.views.asset;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Asset Details")
@Route("/asset-details")
@CssImport(value = "./styles/home-screen-style.css")
@StyleSheet("https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap")
public class AssetDetails extends VerticalLayout {

    public AssetDetails(){
        setPadding(false);
        setSizeFull();
        getElement().getStyle().set("background", "#EFF6F9");
        addClassName("asset-main-layout");

        getHeader();
        var contentDiv = new Div();
        contentDiv.add(createCardLayout("My Invoice", "4.5"),getPageDetailsLayout());
        contentDiv.add(getFileDetailsLayout(),getProcessingDetails());
        contentDiv.setSizeFull();

        add(contentDiv);
        add(getFooter());
    }

    private void  getHeader(){
            var previousPageImg = new Image("images/previous-page.svg","back");
            previousPageImg.addClassName("previous-page-img");
            var previousPageLabel = new Label("Add Files");
            previousPageLabel.addClassName("previous-page-label");
            var previousContent = new Div(previousPageImg,previousPageLabel);
            var pageHeaderLabel = new Label("Asset Details");
            var pageHeaderLayout = new HorizontalLayout(previousContent,pageHeaderLabel);
            pageHeaderLayout.addClassName("asset-page-header");
            pageHeaderLayout.getElement().getStyle().set("padding", "10px");
            add(pageHeaderLayout);
    }
    private Div createCardLayout(String title, String selectedFileSize) {
        Label cardTitle = new Label(title);
        cardTitle.addClassName("upload-card-title");

        var fileSize = new Label(selectedFileSize + " KB");
        fileSize.addClassName("file-size-details");
        var fileStorage = new Label("Phone Storage");
        fileStorage.addClassName("file-size-details");

        var fileDimension = new Label(" | 700 X 1200 px");
        fileDimension.addClassName("file-size-details");

        var fileSizeDetails = new FlexLayout(fileSize,fileDimension);
        fileSizeDetails.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        fileSizeDetails.setAlignContent(FlexLayout.ContentAlignment.CENTER);
        fileSizeDetails.getElement().getStyle().set("margin-top", "5px");

        var cardDetails = new FlexLayout(cardTitle, fileStorage);
        cardDetails.setFlexDirection(FlexLayout.FlexDirection.COLUMN);

        var fileTypeImage = new Image("images/Group 4.png", "type");
        fileTypeImage.addClassName("file-img");

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
        uploadStatus.setAlignItems(Alignment.CENTER);

        var cardContentDiv = getFlexLayout(cardDetails,fileSizeDetails);
        cardContentDiv.getElement().getStyle().set("margin-left", "15px");

        var cardMainDiv = new Div(new FlexLayout(fileTypeImage,cardContentDiv),uploadProgressBar,uploadStatus);
        cardMainDiv.getElement().getStyle().set("margin", "15px");
        return cardMainDiv;
    }

    private HorizontalLayout getPageDetailsLayout(){
        final Label totalPages = new Label("Total Pages");
        totalPages.addClassName("home-page-labels");
        final Label totalPagesCount = new Label("75");
        totalPagesCount.getElement().getStyle().set("margin-left", "auto");
        totalPagesCount.addClassName("home-page-values");

        final Label candidates = new Label("Candidates");
        candidates.addClassName("home-page-labels");
        final Label candidatesCount = new Label("50");
        candidatesCount.getElement().getStyle().set("margin-left", "auto");
        candidatesCount.addClassName("home-page-values");

        var progressLayout = new HorizontalLayout(totalPages,totalPagesCount,candidates,candidatesCount);
        progressLayout.getElement().getStyle().set("margin", "15px");
        progressLayout.getElement().getStyle().set("padding", "20px");
        progressLayout.getElement().getStyle().set("border-radius", "15px");
        progressLayout.getElement().getStyle().set("background", "white");
        progressLayout.getElement().getStyle().set("box-shadow", "3px 3px 12px #00000029");
        return progressLayout;
    }

    private FlexLayout getFileDetailsLayout(){
        var blankChart = new Icon(VaadinIcon.CHECK_CIRCLE_O);
        blankChart.getElement().getStyle().set("width", "35px");
        blankChart.getElement().getStyle().set("height", "35px");
        blankChart.getElement().getStyle().set("color", "#027FFF");
        blankChart.getElement().getStyle().set("margin", "20px 0 0 10px");

        var blanks = new Label("Blanks");
        blanks.addClassName("home-page-labels");
        blanks.getElement().getStyle().set("margin-left", "10px");

        var blankDetails = getFlexLayout(blankChart, blanks);
        blankDetails.setWidthFull();
        blankDetails.getElement().getStyle().set("padding", "10px");
        blankDetails.getElement().getStyle().set("background", "white");
        blankDetails.getElement().getStyle().set("border-radius", "15px");
        blankDetails.getElement().getStyle().set("box-shadow", "3px 3px 12px #00000029");
        blankDetails.getElement().getStyle().set("margin-right", "5px");
        blankDetails.getElement().getStyle().set("justify-content", "space-between");


        var noisyOnes = new Label("Noisyones");
        noisyOnes.addClassName("home-page-labels");
        noisyOnes.getElement().getStyle().set("color","#FFFFFFDE");
        noisyOnes.getElement().getStyle().set("margin-left","10px");

        var noisyValue = new Label("50");
        noisyValue.getElement().getStyle().set("color","#FFFFFFDE");
        noisyValue.addClassName("home-page-values");
        var noisyPercent = new Label("%");
        noisyPercent.getElement().getStyle().set("color","#FFFFFFDE");
        noisyPercent.addClassName("home-page-values");

        Chart chart = new Chart(ChartType.PIE);
        chart.getElement().getStyle().set("height", "80px");
        chart.getElement().getStyle().set("width", "80px");
        Configuration conf = chart.getConfiguration();
        PlotOptionsPie options = new PlotOptionsPie();
        options.getDataLabels().setEnabled(false);
        options.setInnerSize("70%");
        options.setSize("100%");  // Default
        options.setCenter("50%", "50%"); // Default
        conf.setPlotOptions(options);
        conf.getChart().setBackgroundColor(new SolidColor(255,255,255,0));

        DataSeries series = new DataSeries();
        DataSeriesItem processedItem = new DataSeriesItem("Processed", Integer.parseInt(noisyValue.getText()));
        processedItem.setColor(new SolidColor("#027FFF"));
        DataSeriesItem processingItem = new DataSeriesItem("Processing", 100-Integer.parseInt(noisyValue.getText()));
        processingItem.setColor(new SolidColor("#EFF5F9"));
        series.add(processedItem);
        series.add(processingItem);
        conf.addSeries(series);

        var chartLayout = new FlexLayout(chart, new FlexLayout(noisyValue,noisyPercent));
        chartLayout.setAlignItems(Alignment.CENTER);

        var noisyDetails = new Div(chartLayout,noisyOnes);
        noisyDetails.setWidthFull();
        noisyDetails.getElement().getStyle().set("padding", "10px");
        noisyDetails.getElement().getStyle().set("background", "#0157AE");
        noisyDetails.getElement().getStyle().set("border-radius", "15px");
        noisyDetails.getElement().getStyle().set("box-shadow", "3px 3px 12px #00000029");
        noisyDetails.getElement().getStyle().set("margin-left", "5px");

        var fileDetailsLayout = new FlexLayout(blankDetails, noisyDetails);
        fileDetailsLayout.getElement().getStyle().set("margin", "15px");
        fileDetailsLayout.getElement().getStyle().set("justify-content", "space-around");
        return fileDetailsLayout;
    }

    private HorizontalLayout getProcessingDetails(){
        var stateIcon = new Icon(VaadinIcon.CIRCLE_THIN);
        stateIcon.getElement().getStyle().set("font-size", "22px");
        stateIcon.getElement().getStyle().set("color", "#E7E7E7");

        var startDetail = new Label("Yet to Start");
        startDetail.addClassName("file-size-details");
        startDetail.getElement().getStyle().set("color", "#00000099");

        var angledLabel = new Label("Angled Ones");
        angledLabel.addClassName("home-page-labels");

        var processingDetails = new HorizontalLayout(stateIcon,getFlexLayout(startDetail,angledLabel));
        processingDetails.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        processingDetails.getElement().getStyle().set("background", "white");
        processingDetails.getElement().getStyle().set("border-radius", "15px");
        processingDetails.getElement().getStyle().set("margin", "15px");
        processingDetails.getElement().getStyle().set("padding", "15px");
        processingDetails.getElement().getStyle().set("box-shadow","3px 3px 12px #00000029");
        return processingDetails;
    }
    private FlexLayout getFlexLayout(Component labelDetail, Component labelValues) {
        var flexLayout = new FlexLayout(labelDetail,labelValues);
        flexLayout.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        flexLayout.setAlignContent(FlexLayout.ContentAlignment.CENTER);
        return flexLayout;
    }
    private Footer getFooter() {
        var footer = new Footer();

        var homeIcon = new Icon(VaadinIcon.HOME_O);
        homeIcon.getElement().getStyle().set("padding", "0");
        homeIcon.getElement().getStyle().set("width", "24px");
        homeIcon.getElement().getStyle().set("height", "24px");
        var homeButton = new Button(homeIcon);
        homeButton.addThemeVariants(ButtonVariant.LUMO_LARGE);
        homeButton.addClassName("home-button");

        var myAssets = new Label("My Assets");
        myAssets.addClassName("home-page-labels");
        myAssets.getElement().getStyle().set("color", "#FFFFFF");
        var myAssetsButton = new Button(myAssets);
        myAssetsButton.addThemeVariants(ButtonVariant.LUMO_LARGE);
        myAssetsButton.addClassName("apply-button");

        footer.addClassName("button-footer");
        footer.add(homeButton,myAssetsButton);
        return footer;
    }
}
