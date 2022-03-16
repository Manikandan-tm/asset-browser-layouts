package com.example.application.views.Slider;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@CssImport(value = "./styles/slidercomponent.css",themeFor = "vaadin-dialog-overlay")
public class SliderComponent extends Dialog {

    final Div content = new Div();
    public SliderComponent() {
        addThemeName("slider");
        add(content);
    }


}
