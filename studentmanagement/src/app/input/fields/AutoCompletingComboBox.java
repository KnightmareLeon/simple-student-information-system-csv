package app.input.fields;

import java.awt.Dimension;

import javax.swing.JComboBox;

import app.input.filters.AutoCompletion;

public class AutoCompletingComboBox extends JComboBox<String> {
    public AutoCompletingComboBox(String[] arg0){
        super(arg0);
        AutoCompletion.enable(this);
        this.setPreferredSize(new Dimension(100,20));
    }
}
