package com.example.application.views.asset;



import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Range Slider")
@Route(value = "/range")
public class RangeSlider extends Div{
    private final Text sliderValue = new Text("Value");
    public RangeSlider(){

        var label = new Label("Range");

//        HorizontalLayout sliderLine = new HorizontalLayout();
//        var slider = new PaperSlider(0, 100, 50);
//        slider.addValueChangeListener(e->sliderValue.setText("Slider value: "+e.getValue()));
//        sliderLine.add(slider,sliderValue);

        add(label);
    }
}
