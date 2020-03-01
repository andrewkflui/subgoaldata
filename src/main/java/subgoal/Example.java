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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Example extends HashMap implements Serializable {

    static final long serialVersionUID = 0L;

    public Example(String id) {
        setID(id);
    }

    public Example copy() {
        Example newExample = (Example) this.clone();
        List<Label> newLabelList = new ArrayList();
        List<Label> labelList = (List) getOrDefault("Labels", new ArrayList());
        for (Label m : labelList) {
            newLabelList.add(m.copy());
        }
        put("Labels", newLabelList);
        return newExample;
    }

    public String getID() {
        return (String) get("ID");
    }

    public void setID(String id) {
        put("ID", id);
    }

    public String toString() {
        return getID();
    }

    public static Comparator<Example> idComparator = new Comparator<Example>() {
        @Override
        public int compare(Example c1, Example c2) {
            String id1 = c1.getID();
            String id2 = c2.getID();
            return id1.compareTo(id2);
        }
    };

    public StringBuilder getXmlExample() {
        StringBuilder sb = new StringBuilder();
        String title = (String) getOrDefault("Title", "");
        String program = (String) getOrDefault("Program", "");
        String desc = (String) getOrDefault("Desc", "");
        String type = (String) getOrDefault("Type", "");
        String keywords = (String) getOrDefault("Keywords", "");
        String keywordArray[] = keywords.split(",");
        List<Label> labelList = (List) getOrDefault("Labels", new ArrayList());

        sb.append("<example>\n");
        sb.append("  <title>").append(title.trim()).append("</title>\n");
        sb.append("  <program>\n").append(program).append("\n  </program>\n");
        sb.append("  <description>").append(desc.trim()).append("</description>\n");
        sb.append("  <topics>\n");
        for (String topic : keywordArray) {
            sb.append("    <topic>").append(topic.trim()).append("</topic>\n");
        }
        sb.append("  </topics>\n");
        sb.append("  <explanations>\n");
        for (Label label : labelList) {
            sb.append(label.getXmlExplain("    "));
        }
        sb.append("  </explanations>\n");
        sb.append("  <subgoals>\n");
        for (Label label : labelList) {
            sb.append(label.getXmlSubgoal("    "));
        }
        sb.append("  </subgoals>\n");
        sb.append("  </example>\n");
        return sb;
    }

}
