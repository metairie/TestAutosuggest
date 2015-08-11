package org.fxpart;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.fxpart.combobox.AutosuggestComboBoxList;
import org.fxpart.combobox.KeyValueString;
import org.fxpart.mockserver.MockDatas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private final static Logger LOG = LoggerFactory.getLogger(Controller.class);

    @FXML
    AutosuggestComboBoxList<KeyValueString> autosuggest = new AutosuggestComboBoxList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        autosuggest.setIgnoreCase(true);
        autosuggest.setIsFullSearch(true); // search is applied to key+value
        autosuggest.setEditable(true);
        autosuggest.setAcceptFreeTextValue(true);
        autosuggest.setLazyMode(false); // if no item is selected, lazy is useless
        autosuggest.setDelay(300);

        // override default Graphical Item Formatter
        //autosuggest.setupAndStart(o -> new MockDatas().loadLocation(),
        //        item -> String.format("%s", item.getValue()),
        //        item -> String.format("%s%s%s", item.getKey(), autosuggest.getKeyValueSeparator(), item.getValue()));

        // default Graphical Item Formatter
        autosuggest.setupAndStart(o -> new MockDatas().loadLocation(),
                item -> String.format("%s", item.getValue()),
                item -> String.format("%s%s%s", item.getKey(), "-", item.getValue()));

        // override default Graphical Item Formatter
//        autosuggest.setupAndStart(o -> new MockDatas().loadLocation(), item -> String.format("%s", item.getValue()), null);

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        autosuggest.stopSearch();
    }

}