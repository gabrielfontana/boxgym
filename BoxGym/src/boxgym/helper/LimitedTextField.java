package boxgym.helper;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class LimitedTextField extends TextField {

    private IntegerProperty maxLength = new SimpleIntegerProperty(this,
            "maxLength", -1);
    private StringProperty restrict = new SimpleStringProperty(this, "restrict");

    public LimitedTextField() {
        textProperty().addListener(new ChangeListener<String>() {
            private boolean ignore;

            @Override
            public void changed(
                    ObservableValue<? extends String> observableValue,
                    String s, String s1) {
                if (ignore || s1 == null) {
                    return;
                }
                if (maxLength.get() > -1 && s1.length() > maxLength.get()) {
                    ignore = true;
                    setText(s1.substring(0, maxLength.get()));
                    ignore = false;
                }
                if (restrict.get() != null && !restrict.get().equals("")
                        && !s1.matches(restrict.get() + "*")) {
                    ignore = true;
                    setText(s);
                    ignore = false;
                }
            }
        });
    }

    public IntegerProperty maxLengthProperty() {
        return maxLength;
    }

    public int getMaxLength() {
        return maxLength.get();
    }

    public void setMaxLength(int maxLength) {
        this.maxLength.set(maxLength);
    }

    public StringProperty restrictProperty() {
        return restrict;
    }

    public String getRestrict() {
        return restrict.get();
    }

    public void setRestrict(String restrict) {
        this.restrict.set(restrict);
    }

    public void setValidationPattern(String regex, int maxLength) {
        this.setRestrict(regex);
        this.setMaxLength(maxLength);
    }

    public void setValidationPattern(String regex, int maxLength, String tooltip) {
        this.setRestrict(regex);
        this.setMaxLength(maxLength);
        this.setTooltip(new Tooltip(tooltip));
    }

    public void setCNPJField() {
        this.setRestrict("[0-9]");
        this.setMaxLength(14);
        this.setTooltip(new Tooltip("Sem pontuação!"));
    }

    public void setStandardFieldWithSpace() {
        this.setRestrict("[A-Za-z0-9 ._-]");
        this.setMaxLength(255);
    }

    public void setEmailField() {
        this.setRestrict("[A-Za-z0-9@._-]");
        this.setMaxLength(255);
    }

    public void setPhoneField() {
        this.setRestrict("[0-9]");
        this.setMaxLength(11);
    }

}
