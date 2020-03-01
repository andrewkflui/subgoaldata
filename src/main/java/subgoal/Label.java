package subgoal;

/**
 * *************************************************************
 *
 * Subgoal Data Preparation Written by Dr. Andrew Kwok-Fai LUI On 28/2/2020
 *
 * Copyright Andrew Kwok-Fai LUI 2020
 *
 */
import java.io.Serializable;
import java.util.HashMap;

public class Label extends HashMap implements Serializable {

    static final long serialVersionUID = 0L;

    public Label(String Label) {
        setLabel(Label);
    }
    
    public Label copy() {
        Label newLabel = (Label)this.clone();
        //newLabel.put("Label", "New");
        return newLabel;
    }

    public String getLabel() {
        return (String) get("Label");
    }

    public void setLabel(String id) {
        put("Label", id);
    }

    public String toString() {
        return getLabel();
    }
    
    
    public StringBuilder getXmlSubgoal() {
        return getXmlSubgoal("");
    }

    public StringBuilder getXmlSubgoal(String prefix) {
        StringBuilder sb = new StringBuilder();
        String rowrange = (String) getOrDefault("RowRange", "");
        String colrange = (String) getOrDefault("ColRange", "");        
        String label = (String) getOrDefault("Label", "");
        String type = (String) getOrDefault("Type", "");        
        if (rowrange.isEmpty() || label.isEmpty()) {
            return sb;
        }
        sb.append(prefix).append("<subgoal>\n");
        sb.append(prefix).append("  <label>").append(label.trim()).append("</label>\n");
        sb.append(prefix).append("  <position>").append(rowrange.trim()).append("</position>\n");
        sb.append(prefix).append("  <colrange>").append(colrange.trim()).append("</colrange>\n");        
        sb.append(prefix).append("  <type>").append(type.trim()).append("</type>\n");        
        sb.append(prefix).append("</subgoal>\n");
        return sb;
    }

    public StringBuilder getXmlExplain() {
        return getXmlExplain("");
    }

    public StringBuilder getXmlExplain(String prefix) {
        StringBuilder sb = new StringBuilder();
        String rowrange = (String) getOrDefault("RowRange", "");
        String desc = (String) getOrDefault("Desc", "");
        if (rowrange.isEmpty() || desc.isEmpty()) {
            return sb;
        }
        sb.append(prefix).append("<explain>\n");
        sb.append(prefix).append("  <position>").append(rowrange.trim()).append("</position>\n");
        sb.append(prefix).append("  <description>").append(desc.trim()).append("</description>\n");
        sb.append(prefix).append("</explain>\n");
        return sb;
    }
}
