package com.example.application.views.about;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Carousel")
@Route("/carousel")
public class CarouselView extends Div {

    public CarouselView(){


        var scrollLayout = new HorizontalLayout();
        scrollLayout.add(getImage());
        scrollLayout.add(getImage());
        scrollLayout.add(getImage());
        scrollLayout.add(getImage());
        scrollLayout.add(getImage());
        scrollLayout.add(getImage());


        scrollLayout.addClassName("scrollbar-container");
        add(scrollLayout);
    }
    private Image getImage(){
        var image = new Image("images/Group4.png","img");
        image.setWidth("90px");
        image.setHeight("90px");
        return image;
    }
}
