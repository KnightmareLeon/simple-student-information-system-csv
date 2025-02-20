package app.input.filters;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class UpperCaseOnlyDocumentFilter extends UpperCaseDocumentFilter{
    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        super.insertString(fb, offset, text.replaceAll("[$&+,:;=?@#|\"<>.^*()\\[\\]{}\\/%!0-9]", ""), attr);
    }
    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text != null) {
              text = text.replaceAll("[$&+,:;=?@#|\"<>.^*()\\[\\]{}\\/%!0-9]", "");
        }
        super.replace(fb, offset, length, text, attrs);
    }
}
