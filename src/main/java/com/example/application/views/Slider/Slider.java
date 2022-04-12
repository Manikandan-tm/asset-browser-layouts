package com.example.application.views.Slider;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

@PageTitle("Slider Animation")
@Route("/css-slider")
@CssImport(value = "./styles/vaadin-dialog-overlay.css",themeFor = "vaadin-dialog-overlay")
public class Slider extends Div {
    private Dialog dialog = new Dialog();
    public Slider(){

            Button open = new Button("Open dialog");
            open.addClickListener(e -> dialog.open());
            add(open);

            Button close = new Button("Close dialog");
            close.addClickListener(e -> dialog.close());
            dialog.add(close);
//
//        Grid<String> grid = new Grid<>();
//        grid.addColumn("Type");
//
//        List<String> data = new ArrayList<>();
//        data.add("element1");
//        data.add("element2");
//        data.add("element3");
//
//        grid.setItems(data);
//        add(grid);
//
//        final WebBrowser webBrowser = VaadinSession.getCurrent().getBrowser();
//        var a= webBrowser.isAndroid() || webBrowser.isWindowsPhone() || webBrowser.isIPhone();





//
//        Grid<ArrayList> grid = new Grid<>();
//        grid.addColumn(item->item).setHeader("Value1");
//        grid.addColumn(item->item).setHeader("Value2");
//
//        add(grid);
    }
}
