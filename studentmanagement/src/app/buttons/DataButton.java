package app.buttons;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import app.errors.NoRowSelectedException;
import app.frames.DataFrame;
import app.tables.ManagementTable;

public abstract class DataButton extends JButton{
    private DataFrame dFrame;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private JButton actionButton;

    public DataButton(ManagementTable mTable){
        this.setSize(100, 10);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                setUp(mTable);
            }
        });
        
    }

    private void setUp(ManagementTable mTable){
        this.dFrame = new DataFrame(mTable.getCTM(), mTable.getDMap());
        this.gbl = new GridBagLayout();
        this.gbc = new GridBagConstraints();
        this.dFrame.setLayout(gbl);
        this.actionButton = new JButton();
        try {
            this.setUpComponents(mTable);
            this.dFrame.pack();
            this.dFrame.setLocationRelativeTo(null);
            this.dFrame.setVisible(true);
        } catch (NoRowSelectedException e) {
            e.startErrorWindow(mTable);
            e.printStackTrace();
        }
    }

    protected abstract void setUpComponents(ManagementTable mTable) throws NoRowSelectedException;

    protected DataFrame getDataFrame(){return this.dFrame;}
    protected GridBagConstraints getGBC(){return this.gbc;}
    protected JButton getActionButton(){return this.actionButton;};
}
