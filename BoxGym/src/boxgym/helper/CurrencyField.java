package boxgym.helper;

import java.text.NumberFormat;
import java.util.Locale;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.TextField;

public class CurrencyField extends TextField{

    private NumberFormat format;
    private SimpleDoubleProperty price;
    
    public CurrencyField() {
        this(new Locale("pt","BR"), 0.0);
    }
    
    public CurrencyField(Locale locale) {
        this(locale, 0.00);
    }

    public CurrencyField(Locale locale, Double initialPrice) {
        setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        price = new SimpleDoubleProperty(this, "price", initialPrice);
        format = NumberFormat.getCurrencyInstance(locale);
        setText(format.format(initialPrice));

        // Remove selection when textfield gets focus
        focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            Platform.runLater(() -> {
                int lenght = getText().length();
                selectRange(lenght, lenght);
                positionCaret(lenght);
            });
        });

        // Listen the text's changes
        textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                formatText(newValue);
            }
        });
    }

    /**
     * Get the current price value
     * @return Total price
     */
    public Double getPrice() {
        return price.get();
    }

    /**
     * Property getter
     * @return SimpleDoubleProperty
     */
    public SimpleDoubleProperty priceProperty() {
        return this.price;
    }

    /**
     * Change the current price value
     * @param newPrice
     */
    public void setPrice(Double newPrice) {
        if(newPrice >= 0.0) {
            price.set(newPrice);
            formatText(format.format(newPrice));
        }
    }

    /**
     * Set Currency format
     * @param locale
     */
    public void setCurrencyFormat(Locale locale) {
        format = NumberFormat.getCurrencyInstance(locale);
        formatText(format.format(getPrice()));
    }

    private void formatText(String text) {
        if(text != null && !text.isEmpty()) {
            String plainText = text.replaceAll("[^0-9]", "");

            while(plainText.length() < 3) {
                plainText = "0" + plainText;
            }

            StringBuilder builder = new StringBuilder(plainText);
            builder.insert(plainText.length() - 2, ".");

            Double newValue = Double.parseDouble(builder.toString());
            price.set(newValue);
            setText(format.format(newValue));
        }
    }

    @Override
    public void deleteText(int start, int end) {
        StringBuilder builder = new StringBuilder(getText());
        builder.delete(start, end);
        formatText(builder.toString());
        selectRange(start, start);
    }

}