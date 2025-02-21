package app.input.fields;

import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import app.input.filters.UpperCaseOnlyDocumentFilter;

public class UpperCaseOnlyTextField extends JTextField{
    private UpperCaseOnlyDocumentFilter filter = new UpperCaseOnlyDocumentFilter();

    public UpperCaseOnlyTextField(){
        ((AbstractDocument) this.getDocument()).setDocumentFilter(filter);
        this.setPreferredSize(new Dimension(250,20));
    }
}
