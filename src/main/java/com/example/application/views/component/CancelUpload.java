package com.example.application.views.component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Upload Cancel")
@Route("/cancel-upload")
public class CancelUpload extends VerticalLayout {


    private final ConfirmDialog confirmDialog = new ConfirmDialog();
    public CancelUpload(){
        HorizontalLayout layout = new HorizontalLayout();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);


        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setText("Are you sure you want to permanently delete this item?");

        dialog.setCancelable(true);
        dialog.setCancelText("No");

        dialog.setConfirmText("Yes");
        dialog.setConfirmButtonTheme("primary");

        Button button = new Button("Open confirm dialog");
        button.addClickListener(event -> {
            dialog.open();
        });

        layout.add(button);
        add(layout);
    }



}
