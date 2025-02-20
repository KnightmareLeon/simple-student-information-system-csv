package app.input;

import java.awt.GridBagConstraints;

import app.errors.NoRowSelectedException;
import app.frames.DataFrame;
import app.tables.ManagementTable;
import data.maps.DataMap;

public abstract class DataInput {
    protected abstract void setUpComponents(DataFrame dFrame, DataMap dMap, GridBagConstraints frameGBC);

    protected abstract void getData(ManagementTable mTable) throws NoRowSelectedException;
}
