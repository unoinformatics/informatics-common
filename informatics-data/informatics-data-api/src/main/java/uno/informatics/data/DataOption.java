package uno.informatics.data;

public class DataOption {
    
    private String key ;
    private Object value ;
    
    public DataOption(String key) {
        super();
        
        if (key == null) {
            throw new IllegalArgumentException("Key not defined.");
        }
        
        this.key = key;
    }

    public DataOption(String key, Object value) {
        super();
        
        if (key == null) {
            throw new IllegalArgumentException("Key not defined.");
        }
        
        if (value == null) {
            throw new IllegalArgumentException("Value not defined.");
        }
        
        this.key = key;
        this.value = value;
    }
    
    public final String getKey() {
        return key;
    }
    
    public final void setKey(String key) {
        this.key = key;
    }
    
    public final Object getValue() {
        return value;
    }

    public final void setValue(Object value) {
        this.value = value;
    }
    
    public static final boolean hasOption(DataOption[] options, String key) {

        boolean found = false ;
        
        if (options != null && key != null) {
            int i = 0 ;
            
            while (!found && i < options.length) {
                if (options[i] != null && key.equals(options[i].getKey())) {
                    found = true ;
                }
                ++i ;
            }   
        }
        
        return found ;
    }

    public static final <T> T findValue(DataOption[] options, String key, Class<T> type) {
        
        return findValue(options, key, type, null);
    }

    @SuppressWarnings("unchecked")
    public static final <T> T findValue(DataOption[] options, String key, Class<T> type, T defaultValue) {
        
        Object value = null ;
        
        if (options != null && key != null) {
            int i = 0 ;
            
            while (value == null && i < options.length) {
                if (options[i] != null && key.equals(options[i].getKey())) {
                    value = options[i].getValue() ;
                }
                ++i ;
            }   
        }
        
        if (type.isInstance(value)) {
            return (T)value ;
        } else {
            return defaultValue ;
        }
    }

}
