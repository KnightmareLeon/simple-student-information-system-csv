package app.input.fields;

import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import app.input.filters.UpperCaseDocumentFilter;

public class UpperCaseTextField extends JTextField{
    private UpperCaseDocumentFilter filter = new UpperCaseDocumentFilter();

    public UpperCaseTextField(){
        ((AbstractDocument) this.getDocument()).setDocumentFilter(filter);
        this.setPreferredSize(new Dimension(100,20));
    }

}
