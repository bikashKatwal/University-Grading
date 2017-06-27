import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class RestrictiveTextField extends TextField {
	private IntegerProperty maxLength = new SimpleIntegerProperty(this, "maxLength", -1);
	private StringProperty restrict = new SimpleStringProperty(this, "restrict");

	//Restrict the non numeric and the count the digit.
	public RestrictiveTextField(){
		textProperty().addListener(new ChangeListener<String>() {
			private boolean ignore;
			
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				if(ignore || newValue==null)
					return;
				if(maxLength.get()>-1&&newValue.length()>maxLength.get()){
					ignore=true;
					setText(newValue.substring(0, maxLength.get()));
					ignore=false;
				}
				if (restrict.get() != null && !restrict.get().equals("") && !newValue.matches(restrict.get() + "*")) {
                    ignore = true;
                    setText(oldValue);
                    ignore = false;
                }			 				
			}
		});
	}
	
	
	 /**
     * The max length property.
     *
     * @return The max length property.
     */
    public IntegerProperty maxLengthProperty() {
        return maxLength;
    }
 
    /**
     * Gets the max length of the text field.
     *
     * @return The max length.
     */
    public int getMaxLength() {
        return maxLength.get();
    }
 
    /**
     * Sets the max length of the text field.
     *
     * @param maxLength The max length.
     */
    public void setMaxLength(int maxLength) {
        this.maxLength.set(maxLength);
    }
 
    /**
     * The restrict property.
     *
     * @return The restrict property.
     */
    public StringProperty restrictProperty() {
        return restrict;
    }
 
    /**
     * Gets a regular expression character class which restricts the user input.
 
     *
     * @return The regular expression.
     * @see #getRestrict()
     */
    public String getRestrict() {
        return restrict.get();
    }
 
    /**
     * Sets a regular expression character class which restricts the user input.
 
     * E.g. [0-9] only allows numeric values.
     *
     * @param restrict The regular expression.
     */
    public void setRestrict(String restrict) {
        this.restrict.set(restrict);
    }
}
