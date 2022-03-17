package com.example.application.views.asset;



import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.addon.sliders.PaperRangeSlider;
import org.vaadin.addon.sliders.PaperSlider;

@PageTitle("Range Slider")
@Route("global")
public class RangeSlider extends Div{
    private final Label sliderValue = new Label();
    public RangeSlider(){



//                var label = new Label("Range");
//        Button button = new Button("Click me",
//                event -> Notification.show("Clicked!"));
//        button.setId("hello-world");
//        button.getElement().setAttribute("data-tooltip", "My tooltip");
//        add(button);
//
//        UI.getCurrent().getPage().executeJs(" var referenceElement = document.getElementById(\"hello-world\");\n" +
//                "    /* 2/3. Create a new Tooltip.js instance */\n" +
//                "    var instance = new TooltipGlobal(referenceElement, {\n" +
//                "      title: referenceElement.getAttribute('data-tooltip'),\n" +
//                "      trigger: \"hover\",\n" +
//                "      placement: \"top\",\n" +
//                "      /* more option her */\n" +
//                "      /* #list of options: */\n" +
//                "      /* https://popper.js.org/tooltip-documentation.html#new_Tooltip_new*/\n" +
//                "    });");
//
//        add(label);

//        HorizontalLayout sliderLine = new HorizontalLayout();
//
//        PaperSlider paperSlider = new PaperSlider(0,100,50);
//        paperSlider.addValueChangeListener(e -> sliderValue.setText("Slider value: "+e.getValue()));
//        sliderLine.add(paperSlider,sliderValue);
//        add(sliderLine);
        // Single slider
        HorizontalLayout sliderLine = new HorizontalLayout();
        var slider = new PaperSlider(0, 100, 50);
        slider.addValueChangeListener(e -> sliderValue.setText("Slider value: "+e.getValue()));
        sliderLine.add(slider, sliderValue);
        add(sliderLine);

        Label rangeValues = new Label("Range values");
        PaperRangeSlider rangeSlider = new PaperRangeSlider(0, 100, 40, 60);
        rangeSlider.addValueChangeListener(e -> rangeValues.setText("Range values: " +
                e.getValue().getLowerValue()+ ", "+e.getValue().getUpperValue()));
        var rangeSliderLine = new HorizontalLayout();
        rangeSliderLine.add(rangeSlider, rangeValues);
        add(rangeSliderLine);
    }
}
